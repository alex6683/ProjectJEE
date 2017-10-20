package appDirectory.dao.impl;

import appDirectory.model.Group;
import appDirectory.model.Person;
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
@ContextConfiguration(locations = "/spring/spring.xml")
public class PersonDaoJDBCTest {

    @Autowired
    ApplicationContext context ;

    @Autowired
    PersonDaoJDBC daoJDBC ;

    @Autowired
    Person person ;

    @Before
    public void setUp() throws Exception {
        person.setIdentifier("1");
        person.setName("testNameJDBC");
        person.setGroup("1");
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void addPersonTest() throws Exception {
        daoJDBC.addPerson(person);
    }

    @Test
    public void addGroupTest() throws Exception {
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