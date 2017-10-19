package appDirectory.utils;

import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;

import java.sql.Connection;
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
        System.out.println("data = " + table);
    }

}