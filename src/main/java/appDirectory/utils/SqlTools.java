package appDirectory.utils;

import appDirectory.exception.DAOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public ArrayList<ArrayList<String>> selectQuery(String sql) {
        ArrayList<ArrayList<String>> table = new ArrayList<>() ;
        try {
        	if(connection == null) {
        		throw new NullPointerException("Non connecté à la BD");
        	}
            PreparedStatement query = connection.prepareStatement(sql) ;
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
            e.printStackTrace();
            throw new DAOException(e) ;
        }
        return table ;
    }

    public void updateQuery(String sql, Object... params) {

    }

}
