package appDirectory.utils;

import appDirectory.dao.BeanToResultSet;
import appDirectory.dao.ResultSetToBean;
import appDirectory.exception.DAOException;
import appDirectory.exception.DAOMapperException;
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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/spring/spring.xml")
public class SqlToolsTest {

    @Autowired
    private DataSource dataSource ;

    @Autowired
    private SqlTools sql ;

    @Autowired
    private Person bean1 ;

    @Autowired
    private Person bean2 ;

    @Before
    public void setUp() throws Exception {
        sql.setDataSource(dataSource);
        bean1.setName("insertBean1");
        bean1.setGroupID(1);
        bean1.setIdentifier(-1);
        bean2.setName("insertBean2");
        bean2.setGroupID(2);
        bean2.setIdentifier(3);
    }

    @After
    public void tearDown() throws Exception {

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
    public void executeUpdateTest() throws Exception {
        int result = sql.executeUpdate(
                "insert into Person" +
                        " (name, surname, groupID) values" +
                        " (?, ?, ?)",
                "nameTest",
                "surnameTest",
                "1"
        ) ;
        //Vérifie l'insertion d'une ligne dans la base de donnée
        assertEquals(1, result) ;
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
        Collection<Group> listGroup = sql.findBeans(
                "select * from `Group` where groupID = ?",
                resultSet -> {
                    Group group1 = new Group() ;
                    group1.setName("findBeanTest");
                    group1.setIdentifier(new Random().nextInt());
                    return group1 ;
                },
                1
        ) ;
        assertFalse(listGroup.isEmpty());
        assertEquals(listGroup.size(), sql.countRow("select count(*) from `Group` where groupID = 1"));
    }

    @Test(expected = DAOException.class)
    public void insertBeansSQLException() {
        sql.insertBeans("Personne", new ArrayList<Person>()) ;
    }

    @Test
    public void insertBeansTest() {
        Collection<Person> beans = new ArrayList<>() ;
        beans.add(bean1) ;
        beans.add(bean2) ;

        assertEquals(2, sql.insertBeans("Person", beans)) ;
    }

    @Test
    public void updateBeanTest() {
        try {
            bean1.setIdentifier(150);
            sql.updateBean("select * from Person where personID = ?", (bean, preparedStatement, params) -> {
                        ResultSet resultSet = null;
                        try {
                            preparedStatement.setObject(1, bean.getIdentifier());
                            resultSet = preparedStatement.executeQuery();
                            resultSet.next() ;
                            resultSet.updateString("name", "updatedName");
                            resultSet.updateObject("surname", "updatedSurname");
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        return resultSet ;
                    },
                    bean1
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void deleteBeanTest() {
        Collection<Person> beans = new ArrayList<>() ;
        bean1.setIdentifier(150);
        sql.insertBeans("Person", beans) ;

        try {
            sql.deleteBean("select * from Person where personID = ?", (BeanToResultSet<Person>) (bean, preparedStatement, params) -> {
                        ResultSet resultSet = null;
                        try {
                            preparedStatement.setObject(1, bean.getIdentifier());
                            resultSet = preparedStatement.executeQuery() ;
                            resultSet.next() ;
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        return resultSet ;
                    },
                    bean1
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
