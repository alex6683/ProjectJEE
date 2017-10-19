package appDirectory.utils;

import java.sql.Connection;

public class SqlTools {

    private Connection connection ;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public Object[] executeQuery(String sql) {

    }

}
