package appDirectory.dao.impl;

import appDirectory.exception.DAOException;
import appDirectory.model.Group;
import appDirectory.model.Person;
import appDirectory.utils.SqlTools;
import appDirectory.utils.SqlToolsTest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Classe de Test de la class PersonDaoJDBC
 */
@SuppressWarnings("Duplicates")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/spring/spring.xml")
public class PersonDaoJDBCTest {

    @Autowired
    DataSource dataSource ;

    @Autowired
    PersonDaoJDBC jdbc ;

    @Autowired
    Person person1 ;

    @Autowired
    Group group ;


    @Before
    public void setUp() {
        jdbc.setDataSource(dataSource);

        group.setName("groupTest");
        group.setIdentifier(5);

        person1.setName("TestBean1");
        person1.setGroupID(group);
        person1.setIdentifier(-1);
    }

    @After
    public void tearDown() {
        jdbc.executeUpdate("delete from Person where name like '%Test%'") ;
        jdbc.executeUpdate("delete from `Group` where name like '%Test%'") ;
    }

    @Test
    public void addPersonTest() {
        jdbc.addPerson(person1) ;
        assertEquals(jdbc.countRow("select count(*) from Person where name like '%Test%'"), 1);
    }

    @Test
    public void addGroupTest() {
        jdbc.addGroup(group) ;
        assertEquals(jdbc.countRow("select count(*) from `Group` where name like '%Test%'"), 1);
    }

    @Test
    public void findAllGroupsTest()  {
        Collection<Group> all = jdbc.findAllGroups() ;
        assertEquals(jdbc.countRow("select count(*) from `Group`"), all.size());
    }

    @Test
    public void findAllPersonTest() {
        Collection<Person> all = jdbc.findAllPerson() ;
        for(Person person : all) {
            System.out.println("person = " + person.getName()) ;
            System.out.println("person = " + person.getIdentifier()) ;
        }
    }

    @Test
    public void findAllPersonInGroupTest() {
        group.setIdentifier(1);
        Collection<Person> all = jdbc.findAllPersonInGroup(group) ;
        assertEquals(jdbc.countRow("select count(*) from Person where groupID = 1"), all.size());
    }

    @Test
    public void findPersonTest() {
        jdbc.addGroup(group);
        jdbc.addPerson(person1);
        Person person = jdbc.findPerson(person1) ;
        assertEquals(person.getName(), person1.getName());
        assertEquals(person.getIdentifier(), person1.getIdentifier());
        assertEquals(person.getSurname(), person1.getSurname());
        assertEquals(person.getEmail(), person1.getEmail());
        assertEquals(person.getDateBirth(), person1.getDateBirth());
        assertEquals(person.getWebSite(), person1.getWebSite());
        assertEquals(person.getPassword(), person1.getPassword());
        assertEquals(person.getDescription(), person1.getDescription());
        assertEquals(person.getGroupID(), person1.getGroupID());
    }

    @Test
    public void findGroupTest() {
        jdbc.addGroup(group);
        jdbc.addPerson(person1);
        Person person2 = new Person() ;
        person2.setGroupID(group);
        jdbc.addPerson(person2);
        Group group1 = jdbc.findGroup(group) ;
        assertEquals(group1.getName(), group.getName());
        assertEquals(group1.getIdentifier(), group.getIdentifier());
    }



    @Test
    public void deleteGroup() {

    }


    @Test
    public void deletePerson() {

    }

}