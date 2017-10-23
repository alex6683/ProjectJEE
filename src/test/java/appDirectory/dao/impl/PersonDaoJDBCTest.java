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
        person1.setGroup(group);
        person1.setIdentifier(-1);
    }

    @After
    public void tearDown() {
//        jdbc.executeUpdate("delete from Person where name like '%Test%'") ;
//        jdbc.executeUpdate("delete from `Group` where name like '%Test%'") ;
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
//        assertEquals(sql.countRow("select count(*) from `Group`"), all.size());
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

    @Test
    public void deleteGroup() {

    }


    @Test
    public void deletePerson() {

    }

    @Test
    public void groupToResultSetTest() {
        ResultSet resultSet;
        String sql = "select * from `Group` where groupID = ?";
        try(
                Connection connection = jdbc.newConnection() ;
                PreparedStatement query = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)
        ) {
            resultSet = PersonDaoJDBC.groupToResulSet(group, query) ;
            assertNotNull(resultSet);
            assertEquals(resultSet.getString("name"), group.getName());
        } catch (SQLException e) {
            throw new DAOException(e) ;
        }
    }

    @Test
    public void personToResulSetTest() {
        ResultSet resultSet;
        String sql = "select * from Person where personID = ?";
        try(
                Connection connection = jdbc.newConnection() ;
                PreparedStatement query = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)
        ) {
            resultSet = PersonDaoJDBC.personToResulSet(person1, query) ;
            assertNotNull(resultSet);
            assertEquals(resultSet.getString("name"), person1.getName());
            assertEquals(resultSet.getInt("groupID"), person1.getGroup().getIdentifier(), 0);
        } catch (SQLException e) {
            throw new DAOException(e) ;
        }
    }

    @Test
    public void resultSetToGroupTest() {
        ResultSet resultSet;
        String sql = "select * from `Group` where groupID = ?";
        try(
                Connection connection = jdbc.newConnection() ;
                PreparedStatement query = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)
        ) {
            query.setInt(1, group.getIdentifier());
            resultSet = query.executeQuery() ;
            Group group = PersonDaoJDBC.resultSetToGroup(resultSet) ;
            System.out.println("group.getIdentifier() = " + group.getIdentifier());
        } catch (SQLException e) {
            throw new DAOException(e) ;
        }
    }

    @Test
    public void resultSetToPersonTest() {
        jdbc.addGroup(group);
        jdbc.addPerson(person1);
        ResultSet resultSet;
        String sql = "select * from Person where name like ?";
        try(
                Connection connection = jdbc.newConnection() ;
                PreparedStatement query = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)
        ) {
            query.setString(1, "%Test%");
            resultSet = query.executeQuery() ;
            Person person = PersonDaoJDBC.resultSetToPerson(resultSet) ;
            assertEquals("TestBean1", person.getName());
            assertEquals(person.getGroup().getIdentifier(), group.getIdentifier());
        } catch (SQLException e) {
            throw new DAOException(e) ;
        }
    }


}