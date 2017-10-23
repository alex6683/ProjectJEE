package appDirectory.utils;

import appDirectory.dao.BeanToResultSet;
import appDirectory.dao.ResultSetToBean;
import appDirectory.exception.DAOConfigurationException;
import appDirectory.exception.DAOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Outil de manipulation SQL.
 *
 * @author Mestrallet Alexis
 * @author Risch Philippe
 *
 * @date 21/10/2017
 * @version 2.0
 */
@SuppressWarnings("Duplicates")
@Service
public class SqlTools {


    private DataSource dataSource ;

    public DataSource getDataSource() {
        return dataSource;
    }

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @PostConstruct
    public void init() {

    }

    @PreDestroy
    public void destroy() {

    }

    /**
     * Créer une nouvelle connection
     * @return : La connection créée
     * @throws DAOConfigurationException Si la connection est impossible
     */
    public Connection newConnection() throws DAOConfigurationException {
        return DataSourceUtils.getConnection(dataSource) ;
    }

    /**
     * Ferme de maniere silencieuse le connection passée en paramètre
     * @param connection : La connection à fermer
     */
    public void quietClose(Connection connection) {
        DataSourceUtils.releaseConnection(connection, dataSource);
    }

    /**
     * Execute la modification de la base de donnée.
     * @param sql : L'update SQL à exécuter
     * @param params : Les paramètres de l'update
     * @return : Le nombre de ligne modifiée
     * @throws DAOException
     */
    public int executeUpdate(String sql, Object... params) throws DAOException {
        if(StringUtils.countOccurrencesOf(sql, "?")!=params.length) {
            throw new DAOException("Nombre d'argument sql différent du nombre de paramètre") ;
        }
        int result ;
        try(Connection connection = newConnection() ; PreparedStatement query = connection.prepareStatement(sql)) {
            int comptParam = 1 ;
            for(Object param : params) {
                query.setObject(comptParam, param);
                comptParam ++ ;
            }
            result = query.executeUpdate() ;
            connection.commit();
        } catch (SQLException e) {
            throw new DAOException(e) ;
        }
        return result ;
    }

    /**
     * Prévue pour compter le nombre de ligne d'une requête SQL
     * @param sql : La requête SQL en question
     * @param params : Les paramètres de la requête
     * @return : Le nombre de ligne
     * @throws DAOException
     */
    public int countRow(String sql, Object... params) throws DAOException {
        if(StringUtils.countOccurrencesOf(sql, "?")!=params.length) {
            throw new DAOException("Nombre d'argument sql différent du nombre de paramètre") ;
        }
        ResultSet result;
        int count ;
        try(Connection connection = newConnection() ; PreparedStatement query = connection.prepareStatement(sql) ) {
            int comptParam = 1 ;
            for(Object param : params) {
                query.setObject(comptParam, param);
                comptParam ++ ;
            }
            result = query.executeQuery() ;
            result.next() ;
            count = result.getInt(1) ;
            result.close();
        } catch (SQLException e) {
            throw new DAOException(e) ;
        }
        return count ;
    }

    /**
     * Renvoie les Java beans associé à une requête SQL.
     *
     * @param sql : La requête SQL en question
     * @param mapper : L'objet qui convertie le résultat de la requête en javabeans
     * @param params: Les paramètres de la requête
     * @param <T> : La classe du javaBean
     * @return : La liste de JavaBean récupéré
     * @throws DAOException
     */
    public <T> Collection<T> findBeans(String sql, ResultSetToBean<T> mapper, Object... params) throws DAOException {
        Collection<T> beans = new ArrayList<>() ;
        if(StringUtils.countOccurrencesOf(sql, "?")!=params.length) {
            throw new DAOException("Nombre d'argument sql différent du nombre de paramètre") ;
        }
        ResultSet result;
        try(Connection connection = newConnection() ; PreparedStatement query = connection.prepareStatement(sql)) {
            int comptParam = 1 ;
            for(Object param : params) {
                query.setObject(comptParam, param);
                comptParam ++ ;
            }
            result = query.executeQuery() ;
            while(result.next()) {
                beans.add(mapper.toBean(result)) ;
            }
            result.close();
        } catch (SQLException e) {
            throw new DAOException(e) ;
        }
        return beans ;
    }

    /**
     * Insert les Javabeans dans la base de donnée pour une table SQL donnée.
     *
     * @param sqlTable : La table SQL où insérer
     * @param bean : Le javabean à insérer
     * @param <T> : La classe du javaBean
     * @throws DAOException
     */
    public <T> void insertBean(String sqlTable, BeanToResultSet<T> mapper, T bean) throws DAOException  {
        ResultSet resultSet;
        String sql = "select * from " + sqlTable ;
        try(
                Connection connection = newConnection() ;
                PreparedStatement query = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)
        ) {
            resultSet = mapper.toResultSet(bean, query) ;
            if(resultSet == null) {
                return ;
            }
            resultSet.insertRow();
            resultSet.close() ;
            connection.commit() ;
        } catch (SQLException e) {
            throw new DAOException(e) ;
        }
    }

    /**
     * Modification du javabean dans la base de donnée
     *
     * @param sql : Requête sql pour récupérer la ligne à modifier
     * @param mapper : L'objet qui récupère le resulset attendu selon le javabean
     * @param theBean : le javaBean à modifer
     * @param <T> : La classe du javabean
     * @throws DAOException
     */
    public <T> boolean updateBean(String sql, BeanToResultSet<T> mapper, T theBean) throws DAOException {
        try(
                Connection connection = newConnection() ;
                PreparedStatement query = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)
        ) {
            ResultSet resultSet = mapper.toResultSet(theBean, query) ;
            if(resultSet == null) {
                return false ;
            }
            resultSet.updateRow();
            connection.commit();
            resultSet.close();
        } catch (SQLException e) {
            throw new DAOException(e) ;
        }
        return true ;
    }

    /**
     * Suppression du javabean dans la base de donnée
     *
     * @param sql : Requête sql pour récupérer la ligne à modifier
     * @param params : Les paramètres éventuels de la requête
     * @throws DAOException
     */
    public int deleteBeans(String sql, Object... params) throws DAOException {
        int nbDeleted = 0 ;
        try(
                Connection connection = newConnection() ;
                PreparedStatement query = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)
        ) {
            int comptParam = 1 ;
            for(Object param : params) {
                query.setObject(comptParam, param);
                comptParam ++ ;
            }
            ResultSet resultSet = query.executeQuery() ;
            while(resultSet.next()) {
                resultSet.deleteRow();
                nbDeleted ++ ;
            }
            connection.commit();
            resultSet.close();
        } catch (SQLException e) {
            throw new DAOException(e) ;
        }
        return nbDeleted ;
    }
}
