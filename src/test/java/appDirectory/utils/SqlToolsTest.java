package appDirectory.utils;

import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import appDirectory.exception.DAOException;

import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/spring/spring.xml")
public class SqlToolsTest {

    @Autowired
    private DataSource dataSource ;

    private Connection connection ;

    @Before
    public void setUp() throws Exception {
        connection = DataSourceUtils.getConnection(dataSource) ;
    }

    @After
    public void tearDown() throws Exception {
        DataSourceUtils.releaseConnection(connection, dataSource);
    }

    @Test
    public void executeQueryTest() throws Exception {
        SqlTools sqlTool = new SqlTools() ;
        System.out.println("Avant le setConn : ");
        sqlTool.setConnection(connection);
        System.out.println("Après le setConn : ");
        ArrayList<ArrayList<String>> tableToTest = sqlTool.selectQuery("select * from `Group`");
        
        System.out.println("Avant le try : " + tableToTest);
        
        try {
            PreparedStatement query = connection.prepareStatement("select * from `Group`") ;
            ResultSet result = query.executeQuery() ;
            ResultSetMetaData metadata = result.getMetaData() ;
           
            while(result.next()) {
                for(int i=1 ; i<=metadata.getColumnCount() ; i++) {
                    assertEquals(result.getString(i), tableToTest.get(result.getRow()).get(i -1));
                }                    
            }
        } catch (SQLException e) {
        	e.printStackTrace();
        	throw new DAOException(e) ;
        }
    }
}
