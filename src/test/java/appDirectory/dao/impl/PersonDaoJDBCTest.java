package appDirectory.dao.impl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.Connection;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/spring/dao.xml")
public class PersonDaoJDBCTest {

    @Autowired
    ApplicationContext context ;

    @Autowired
    DataSource dataSource ;

    Connection connect ;

    @Before
    public void setUp() throws Exception {
        connect = DataSourceUtils.getConnection(dataSource) ;
    }

    @After
    public void tearDown() throws Exception {
        DataSourceUtils.releaseConnection(connect, dataSource);
    }

    @Test
    public void addPerson() throws Exception {
    }

    @Test
    public void addGroup() throws Exception {
    }

    @Test
    public void updateIfExist() throws Exception {
    }

    @Test
    public void findAllGroups() throws Exception {
    }

    @Test
    public void findAllPersonInGroup() throws Exception {
    }

    @Test
    public void findPerson() throws Exception {
    }

    @Test
    public void findGroup() throws Exception {
    }

}