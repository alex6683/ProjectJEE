package appDirectory.utils;

import appDirectory.exception.DAOConfigurationException;
import appDirectory.exception.DAOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 *
 *
 * @author Mestrallet Alexis
 *
 * @date 19/10/2017
 * @version 1.0
 */
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

//    public ResultSet selectQuery(String sql, Object... params) throws DAOException {
//        if(StringUtils.countOccurrencesOf(sql, "?")!=params.length) {
//            throw new DAOException("Nombre d'argument sql différent du nombre de paramètre") ;
//        }
//        ResultSet result;
//        try(Connection connection = newConnection() ;  PreparedStatement query = connection.prepareStatement(sql) ) {
//            int comptParam = 1 ;
//            for(Object param : params) {
//                query.setObject(comptParam, param);
//                comptParam ++ ;
//            }
//            result = query.executeQuery() ;
//            result.close();
//        } catch (SQLException e) {
//            throw new DAOException(e) ;
//        }
//        return result ;
//    }

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

//    public <K,V> String mapToSQLinsert(Map<K,V> map) {
    //        StringBuilder sql = new StringBuilder() ;
    //        sql.append("(") ;
    //        for(K key : map.keySet())  {
    //            sql.append(key).append(", ") ;
    //        }
    //        sql.deleteCharAt(sql.lastIndexOf(", ")).append(") values (") ;
    //        for(V value : map.values()) {
    //            sql.append(value).append(", ") ;
    //        }
    //        sql.deleteCharAt(sql.lastIndexOf(", ")).append(")") ;
    //        return sql.toString() ;
//    }


//    public <K, V, T> int insertBeans(String sqlTable, BeanToMap<T> mapper, Collection<T> beans, Object...params) {
//        Map<K, V> map ;
//        int result = 0 ;
//        for(T bean : beans) {
//            String sql = "INSERT INTO " +  sqlTable + " ";
//            map = mapper.toMap(bean) ;
//            sql += mapToSQLinsert(map) ;
//            result += executeUpdate(sql, params) ;
//        }
//        return result ;
//    }
//
//    //sql doit etre egal a "update table set colonne1 = valeur1, colonne2 = valeur2 "
//    public <K, V, T> int updateBeans(String sql, BeanToMap<T> mapper, Collection<T> beans, Object...params) {
//        Map<K, V> map ;
//        int result = 0 ;
//        StringBuilder sqlBuilder = new StringBuilder(sql);
//        for(T bean : beans) {
//            map = mapper.toMap(bean) ;
//            if(!map.containsKey("identifier")) {
//                throw new IllegalStateException() ;
//            }
//            sqlBuilder.append(" where identifier = ").append("?");
//            executeUpdate(sqlBuilder.toString(), params) ;
//        }
//        return result ;
//    }
}
