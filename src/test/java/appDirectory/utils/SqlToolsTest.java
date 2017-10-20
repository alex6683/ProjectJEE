package appDirectory.utils;

import appDirectory.exception.DAOException;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import appDirectory.exception.DAOException;

import javax.sql.DataSource;

import java.sql.Array;
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
        connection.setAutoCommit(false);
    }

    @After
    public void tearDown() throws Exception {
        DataSourceUtils.releaseConnection(connection, dataSource);
    }

    @Test
    public void executeQueryTest() throws Exception {

        SqlTools sqlTool = new SqlTools() ;
        sqlTool.setConnection(connection);
        ArrayList<ArrayList<String>> tableToTest = sqlTool.selectQuery("select * from Person");
        try {
            PreparedStatement query = connection.prepareStatement("select * from Person") ;
            ResultSet result = query.executeQuery() ;
            ResultSetMetaData metadata = result.getMetaData() ;
            while(result.next()) {
                for(int i=1 ; i<=metadata.getColumnCount() ; i++) {
                    assertEquals(result.getString(i), tableToTest.get(result.getRow()-1).get(i -1));
                }                    
            }
        } catch (SQLException e) {
        	e.printStackTrace();
        	throw new DAOException(e) ;
        }
    }

    @Test(expected = DAOException.class)
    public void updateQueryTooMuchArguments() {
        SqlTools sql = new SqlTools() ;
        sql.setConnection(connection);
        int result = sql.updateQuery(
                "insert into Person" +
                        " (name, surname, groupID) values" +
                        " (?, ?, ?)",
                "nameTest",
                "surnameTest",
                "emailTest",
                "1"
        ) ;
    }

    @Test(expected = DAOException.class)
    public void updateQueryTooFewArguments() {
        SqlTools sql = new SqlTools() ;
        sql.setConnection(connection);
        int result = sql.updateQuery(
                "insert into Person" +
                        " (name, surname, groupID) values" +
                        " (?, ?, ?)",
                "nameTest"
        ) ;
    }

    @Test(expected = DAOException.class)
    public void updateQuerySQLError() {
        SqlTools sql = new SqlTools() ;
        sql.setConnection(connection);
        //Erreur de synthaxe SQL
        int result = sql.updateQuery(
                "insrt into Person" +
                        " (name, surname, groupID) values" +
                        " (?, ?, ?)",
                "nameTest",
                "surnameTest",
                "1"
        ) ;
    }

    @Test
    public void updateQueryTest() throws Exception {
        SqlTools sql = new SqlTools() ;
        sql.setConnection(connection);
        int result = sql.updateQuery(
                "insert into Person" +
                        " (name, surname, groupID) values" +
                        " (?, ?, ?)",
                "nameTest",
                "surnameTest",
                "1"
        ) ;

        //Vérifie l'insertion d'une ligne dans la base de donnée
        assertEquals(1, result) ;

        ArrayList<ArrayList<String>> resulset = sql.selectQuery("select name, surname, groupID from Person where name = 'nameTest'") ;
        ArrayList<String> expected = new ArrayList<>() ;
        expected.add("nameTest") ;
        expected.add("surnameTest") ;
        expected.add("1") ;

        //Vérifie l'intégrité de la ligne insérée
        assertEquals(expected, resulset.get(0));
    }
}
