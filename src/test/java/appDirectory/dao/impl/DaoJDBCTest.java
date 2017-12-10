package appDirectory.dao.impl;

import appDirectory.model.Group;
import appDirectory.model.Person;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Classe de Test de la class PersonDaoJDBC
 */
@SuppressWarnings("Duplicates")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/spring.xml")
public class DaoJDBCTest {

    @Autowired
    DataSource dataSource ;

    @Autowired
    DaoJDBC jdbc ;

    @Autowired
    Person person1 ;

    @Autowired
    Group group ;


    @Before
    public void setUp() {
        jdbc.setDataSource(dataSource);

        group.setName("groupTest");

        person1.setName("TestBean1");
        person1.setIdentifier(-1);
    }

    @After
    public void tearDown() {
        jdbc.executeUpdate("delete from Person where name like '%Test%'") ;
        jdbc.executeUpdate("delete from `Group` where name like '%Test%'") ;
    }

    @Test
    public void addPersonTest() {
        jdbc.addGroup(group);
        person1.setGroupID(group);
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
        person1.setGroupID(group);
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
        person1.setGroupID(group);
        jdbc.addPerson(person1);
        Person person2 = new Person() ;
        person2.setName("PersonTest2");
        person2.setGroupID(group);
        jdbc.addPerson(person2);
        Group group1 = jdbc.findGroup(group) ;
        assertEquals(group1.getName(), group.getName());
        assertEquals(group1.getIdentifier(), group.getIdentifier());
    }


    @Test
    public void deleteGroupTest() {
        jdbc.addGroup(group);
        jdbc.deleteGroup(group) ;
        assertEquals(jdbc.countRow("select count(*) from `Group` where groupID = ?", group.getIdentifier()), 0);
    }


    @Test
    public void deletePersonTest() {
        jdbc.addGroup(group);
        person1.setGroupID(group);
        jdbc.addPerson(person1);
        jdbc.deletePerson(person1) ;
        assertEquals(jdbc.countRow("select count(*) from Person where personID = ?", person1.getIdentifier()), 0);
    }

    @Test
    public void savePerson() {
        jdbc.saveGroup(group);
        person1.setGroupID(group);
        assertFalse(jdbc.savePerson(person1));
        assertEquals(1, jdbc.countRow("select count(*) from Person where name like '%Test%'"));
        person1.setName("newNameSavedTest");
        assertTrue(jdbc.savePerson(person1)) ;
        assertEquals(jdbc.findPerson(person1).getName(), "newNameSavedTest");
    }

    @Test
    public void saveGroup() {
        assertFalse(jdbc.saveGroup(group));
        assertEquals(1,jdbc.countRow("select count(*) from `Group` where name like '%Test%'"));
        group.setName("newNameSavedTest");
        assertTrue(jdbc.saveGroup(group)) ;
        assertEquals(jdbc.findGroup(group).getName(), "newNameSavedTest");
    }
    
    @Test
    public void findPersonWithKeyWord() {
        jdbc.addGroup(group);
        person1.setGroupID(group);
        jdbc.addPerson(person1);

        Person person2 = new Person() ;
        person2.setName("TestBean1");
        person2.setGroupID(group);
        jdbc.addPerson(person2);

        Collection<Person> persons = jdbc.findPerson("TestBean1") ;
        assertEquals(2, persons.size());
    }

}