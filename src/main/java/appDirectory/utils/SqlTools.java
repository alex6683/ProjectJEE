package appDirectory.utils;

import appDirectory.exception.DAOException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.sql.*;
import java.util.ArrayList;

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

    private Connection connection ;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    /**
     *
     * @param sql
     * @return
     */
    public ArrayList<ArrayList<String>> selectQuery(String sql, Object... params) {
        if(StringUtils.countOccurrencesOf(sql, "?")!=params.length) {
            throw new DAOException("Nombre d'argument sql différent du nombre de paramètre") ;
        }
        ArrayList<ArrayList<String>> table = new ArrayList<>() ;
        try {
            PreparedStatement query = connection.prepareStatement(sql) ;
            int comptParam = 1 ;
            for(Object param : params) {
                query.setObject(comptParam, param);
                comptParam ++ ;
            }
            ResultSet result = query.executeQuery() ;
            ResultSetMetaData metadata = result.getMetaData() ;
            while(result.next()) {
                ArrayList<String> row = new ArrayList<>() ;
                for(int i=1 ; i<=metadata.getColumnCount() ; i++) {
                    row.add(result.getString(i));
                }
                table.add(row) ;
            }
        } catch (SQLException e) {
            throw new DAOException(e) ;
        }
        return table ;
    }

    /**
     *
     *
     * @param sql
     * @param params
     * @return
     */
    public int updateQuery(String sql, Object... params) {
        if(StringUtils.countOccurrencesOf(sql, "?")!=params.length) {
            throw new DAOException("Nombre d'argument sql différent du nombre de paramètre") ;
        }
        int result ;
        try {
            PreparedStatement query = connection.prepareStatement(sql) ;
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

}
