package appDirectory.utils;

import appDirectory.dao.BeanToResultSet;
import appDirectory.dao.ResultSetToBean;
import appDirectory.exception.DAOConfigurationException;
import appDirectory.exception.DAOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 *
 * @author Mestrallet Alexis
 *
 * @date 19/10/2017
 * @version 1.0
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

    public void init() {

    }

    public void destroy() {

    }

    public Connection newConnection() throws DAOConfigurationException {
        return DataSourceUtils.getConnection(dataSource) ;
    }

    public void quietClose(Connection connection) {
        DataSourceUtils.releaseConnection(connection, dataSource);
    }


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
        } catch (SQLException e) {
            throw new DAOException(e) ;
        }
        return result ;
    }

    public int countRow(String sql, Object... params) throws DAOException {
        if(StringUtils.countOccurrencesOf(sql, "?")!=params.length) {
            throw new DAOException("Nombre d'argument sql différent du nombre de paramètre") ;
        }
        ResultSet result;
        int count ;
        try(Connection connection = newConnection() ;  PreparedStatement query = connection.prepareStatement(sql) ) {
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

    public <T> Collection<T> findBeans(String sql, ResultSetToBean<T> mapper, Object... params) {
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

    public <T> int insertBeans(String sqlTable, Collection<T> beans) {
        int result = 0 ;
        ResultSet resultSet;
        String sql = "select * from " + sqlTable ;
        try(
                Connection connection = newConnection() ;
                PreparedStatement query = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)
        ) {
            resultSet = query.executeQuery() ;
            String fieldName ;
            resultSet.moveToInsertRow();
            for(T bean : beans) {
                for(Field field : bean.getClass().getDeclaredFields()) {
                    fieldName = field.getName() ;
                    if(!fieldName.equals("identifier") && !fieldName.equals("serialVersionUID")) {
                        field.setAccessible(true);
                        System.out.println(fieldName + " = " + field.get(bean));
                        resultSet.updateObject(fieldName, field.get(bean));
                        field.setAccessible(false);
                    }
                }
                resultSet.insertRow();
                result ++ ;
            }
            resultSet.close() ;
            connection.commit() ;
        } catch (SQLException | IllegalAccessException e) {
            throw new DAOException(e) ;
        }
        return result ;
    }

    public <T> void updateBean(String sql, BeanToResultSet<T> mapper, T theBean, Object... params)
            throws SQLException {
        try(
                Connection connection = newConnection() ;
                PreparedStatement query = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)
        ) {
            int comptParam = 1 ;
            for(Object param : params) {
                query.setObject(comptParam, param);
                comptParam ++ ;
            }
            ResultSet resultSet = mapper.toResultSet(theBean, query) ;
            resultSet.updateRow();
            connection.commit();
            resultSet.close();
        } catch (SQLException e) {
            throw new DAOException(e) ;
        }
    }

    public <T> void deleteBean(String sql, BeanToResultSet<T> mapper, T theBean, Object... params)
            throws SQLException {
        if(StringUtils.countOccurrencesOf(sql, "?")!=params.length) {
            throw new DAOException("Nombre d'argument sql différent du nombre de paramètre") ;
        }
        try(
                Connection connection = newConnection() ;
                PreparedStatement query = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)
        ) {
            int comptParam = 1 ;
            for(Object param : params) {
                query.setObject(comptParam, param);
                comptParam ++ ;
            }
            ResultSet resultSet = mapper.toResultSet(theBean, query) ;
            resultSet.deleteRow();
            connection.commit();
            resultSet.close();
        } catch (SQLException e) {
            throw new DAOException(e) ;
        }
    }
}
