package appDirectory.utils;

import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;

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
        SqlTools sql = new SqlTools() ;
        sql.setConnection(connection);
        ArrayList<ArrayList<String>> table = sql.selectQuery("select * from Person") ;
    }

    @Test
    public void updateQueryTest() throws Exception {
        SqlTools sql = new SqlTools() ;
        sql.setConnection(connection);
        //Test d'insertion SQL avec parametres
        int result = sql.updateQuery(
                "insert into Person" +
                        " (name, surname, groupID) values" +
                        " (?, ?, ?)",
                "nameTest",
                "surnameTest",
                "1"
        ) ;
        assertEquals(1, result) ;


    }
}