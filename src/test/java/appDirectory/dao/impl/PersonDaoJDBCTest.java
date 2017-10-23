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

/**
 * Classe de Test de la class PersonDaoJDBC
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/spring/spring.xml")
public class PersonDaoJDBCTest extends SqlTools {

    @Autowired
    private PersonDaoJDBC jdbc ;

    @Autowired
    private Person person1 ;


    @Autowired
    private Person person2 ;

    @Autowired
    Group group ;

    @Before
    public void setUp() throws Exception {
        group.setIdentifier(5);
        group.setName("FSITest") ;

        person1.setName("MestralletTest");
        person1.setGroupID(3) ;

        person2.setName("RischTest");
        person2.setGroupID(3);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void addPersonTest() throws Exception {
        jdbc.addPerson(person1) ;
    }

    @Test
    public void addGroupTest() throws Exception {
        jdbc.addGroup(group) ;
    }

    @Test
    public void findAllGroupsTest() throws Exception {
        Collection<Group> all = jdbc.findAllGroups() ;
    }

    @Test
    public void findAllPersonInGroupTest() throws Exception {
        jdbc.findAllPersonInGroup(group) ;
    }

    @Test
    public void findPersonTest() throws Exception {
        jdbc.findPerson(person1) ;
    }

    @Test
    public void findGroupTest() throws Exception {
        jdbc.findGroup(group) ;
    }

    @Test
    public void findAllPersonTest() throws Exception {
        Collection<Person> all = jdbc.findAllPerson() ;
    }

}