package appDirectory.dao.impl;

import appDirectory.model.Group;
import appDirectory.model.Person;
import appDirectory.utils.SqlTools;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/spring/spring.xml")
public class PersonDaoJDBCTest extends SqlTools {

    @Autowired
    private PersonDaoJDBC jdbc ;

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void addPersonTest() throws Exception {

    }

    @Test
    public void addGroupTest() throws Exception {
    }

    @Test
    public void updateIfExist() throws Exception {
    }

    @Test
    public void findAllGroups() throws Exception {
        Collection<Group> all = jdbc.findAllGroups() ;
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

    @Test
    public void findAllPersonTest() throws Exception {
        Collection<Person> all = jdbc.findAllPerson() ;
    }

}