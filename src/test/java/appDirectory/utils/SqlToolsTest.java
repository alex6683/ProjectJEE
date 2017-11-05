package appDirectory.utils;

import appDirectory.dao.BeanToResultSet;
import appDirectory.dao.ResultSetToBean;
import appDirectory.exception.DAOException;
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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * Test de la class SqlTools
 */
@SuppressWarnings("Duplicates")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/spring/spring.xml")
public class SqlToolsTest {

    @Autowired
    DataSource dataSource ;

    @Autowired
    SqlTools sql ;

    @Autowired
    Person bean1 ;

    @Autowired
    Person bean2 ;

    @Autowired
    Group group ;

    @Before
    public void setUp() {
        sql.setDataSource(dataSource);
        group.setName("groupTest");
        group.setIdentifier(5);

        bean1.setName("TestBean1");
        bean1.setGroupID(group);
        bean1.setIdentifier(-1);
        bean2.setName("TestBean2");
        bean2.setGroupID(group);
        bean2.setIdentifier(3);
    }

    @After
    public void tearDown() {
        sql.executeUpdate("delete from Person where name like '%Test%'") ;
        sql.executeUpdate("delete from `Group` where name like '%Test%'") ;
    }

    @Test(expected = DAOException.class)
    public void executeUpdateTooMuchArgumentsTest() {
        sql.executeUpdate(
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
    public void executeUpdateTooFewArgumentsTest() {
        sql.executeUpdate(
                "insert into Person" +
                        " (name, surname, groupID) values" +
                        " (?, ?, ?)",
                "nameTest"
        ) ;
    }

    @Test(expected = DAOException.class)
    public void executeUpdateSQLErrorTest() {
        sql.executeUpdate(
                "insrt into Person" +
                        " (name, surname, groupID) values" +
                        " (?, ?, ?)",
                "nameTest",
                "surnameTest",
                "1"
        ) ;
    }

    @Test
    public void executeUpdateTest() {
        int resultPerson = sql.executeUpdate(
                "insert into Person" +
                        " (name, surname, groupID) values" +
                        " (?, ?, ?)",
                "nameTest",
                "surnameTest",
                "1"
        ) ;
        //Vérifie l'insertion d'une ligne dans la base de donnée
        assertEquals(sql.countRow("select count(*) from Person where name = 'nameTest'"), resultPerson) ;
    }

    @Test(expected = DAOException.class)
    public void findBeansTooMuchArgumentsTest() {
        sql.findBeans("select ?, name, email from Person",
                (ResultSetToBean<Person>) resultSet -> null,
                "surname", "personID") ;
    }


    @Test(expected = DAOException.class)
    public void findBeansTooFewArgumentsTest() {
        sql.findBeans("select ?, ?, ? from Person",
                (ResultSetToBean<Person>) resultSet -> null,
                "surname", "personID") ;
    }

    @Test(expected = DAOException.class)
    public void findBeansSQLExceptionTest() {
        sql.findBeans("select * from group", (ResultSetToBean<Group>) resultSet -> null) ;
    }

    @Test
    public void findBeansTest() {
        Collection<Person> persons = sql.findBeans("select * from Person where groupID = ?",
                resultSet -> new Person(),
                1
        ) ;
        assertEquals(persons.size(), sql.countRow("select count(*) from Person where groupID = 1"));
    }

    @Test(expected = DAOException.class)
    public void insertBeansSQLException() {
        sql.insertBean("Group", (bean, preparedStatement) -> {
            ResultSet res ;
            try {
                res = preparedStatement.executeQuery();
            } catch (SQLException e) {
                throw new DAOException(e);
            }
            return res ;
        }, group) ;
    }

    @Test
    public void insertBeansTest() {
        int idGroup = sql.insertBean("`Group`", (bean, preparedStatement) -> {
            ResultSet res ;
            try {
                res = preparedStatement.executeQuery();
                res.moveToInsertRow();
                res.updateString("name", group.getName());
            } catch (SQLException e) {
                throw new DAOException(e);
            }
            return res ;
        }, group) ;
        int idPerson = sql.insertBean("Person", (bean, preparedStatement) -> {
            ResultSet res ;
            try {
                res = preparedStatement.executeQuery();
                res.moveToInsertRow();
                bean1.setGroupID(group);
                res.updateString("name", bean1.getName());
                res.updateInt("groupID", bean1.getGroupID()) ;
            } catch (SQLException e) {
                throw new DAOException(e);
            }
            return res ;
        }, bean1) ;

        assertEquals(sql.countRow("select count(*) from Person where name like '%Test%'"), 1);
        assertEquals(sql.countRow("select count(*) from `Group` where name like '%Test%'"), 1);
    }

    @Test
    public void updateBeanNotExistTest() {
        assertFalse(
                sql.updateBean("select * from Person where name like '%Test%'", (bean, preparedStatement) -> {
                    ResultSet res ;
                    try {
                        res = preparedStatement.executeQuery();
                        if(!res.next()) return null ;
                        res.updateString("name", bean2.getName());
                    } catch (SQLException e) {
                        throw new DAOException(e);
                    }
                    return res ;
                }, bean2));
    }

    @Test
    public void updateBeanTest() {
        sql.insertBean("Person", (bean, preparedStatement) -> {
            ResultSet res ;
            try {
                res = preparedStatement.executeQuery();
                res.moveToInsertRow();
                bean2.setGroupID(group);
                res.updateString("name", bean2.getName());
                res.updateInt("groupID", bean2.getGroupID());
            } catch (SQLException e) {
                throw new DAOException(e);
            }
            return res ;
        }, bean2) ;

        bean2.setName("updatedTestBean1");
        sql.updateBean("select * from Person where name like '%Test%'", (BeanToResultSet<Person>) (bean, preparedStatement) -> {
            ResultSet res ;
            try {
                res = preparedStatement.executeQuery();
                res.next() ;
                res.updateString("name", bean2.getName());
            } catch (SQLException e) {
                throw new DAOException(e);
            }
            return res ;
        }, bean2) ;
        assertEquals(sql.countRow("select count(*) from Person where name = 'TestBean1'"), 0);
        assertEquals(sql.countRow("select count(*) from Person where name = 'updatedTestBean1'"), 1);
    }


    @Test
    public void deleteBeanTest() {
        sql.insertBean("Person", (bean, preparedStatement) -> {
            ResultSet res ;
            try {
                res = preparedStatement.executeQuery();
                res.moveToInsertRow();
                bean2.setGroupID(group);
                res.updateString("name", bean2.getName());
                res.updateInt("groupID", bean2.getGroupID());
            } catch (SQLException e) {
                throw new DAOException(e);
            }
            return res ;
        }, bean2) ;
        sql.insertBean("Person", (bean, preparedStatement) -> {
            ResultSet res ;
            try {
                res = preparedStatement.executeQuery();
                res.moveToInsertRow();
                bean1.setGroupID(group);
                res.updateString("name", bean1.getName());
                res.updateInt("groupID", bean1.getGroupID());
            } catch (SQLException e) {
                throw new DAOException(e);
            }
            return res ;
        }, bean1) ;

        sql.deleteBeans("select * from Person where name like ?", "%Test%") ;

        assertEquals(0, sql.countRow("select count(*) from Person where name like '%Test%'"));
    }



    @Test(expected = DAOException.class)
    public void deleteBeansTooMuchArgumentsTest() {
        sql.deleteBeans("select * from Person where groupID = ?", bean1.getIdentifier(), bean1.getName()) ;
    }

    @Test(expected = DAOException.class)
    public void deleteBeansTooFewArgumentsTest() {
        sql.deleteBeans("select * from Person where groupID = ?, name = ?, surname = ?", bean1.getIdentifier(), bean1.getName()) ;
    }

    @Test(expected = DAOException.class)
    public void deleteBeansSQLErrorTest() {
        sql.deleteBeans("select * from Person where id = ?", bean1.getIdentifier(), bean1.getName()) ;

    }
}
