package appDirectory.utils;

import appDirectory.exception.DAOException;
import appDirectory.model.Group;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.util.Collection;
import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/spring/spring.xml")
public class SqlToolsTest {

    @Autowired
    private DataSource dataSource ;

    @Autowired
    private SqlTools sql ;

    @Before
    public void setUp() throws Exception {
        sql.setDataSource(dataSource);
    }

    @After
    public void tearDown() throws Exception {

    }

//    @Test
//    public void selectQueryTest() throws DAOException, SQLException {
//        ResultSet resultToTest = sql.selectQuery("select * from Person   where 'groupID' = 1");
//        assertTrue(resultToTest.isClosed());
//        while(resultToTest.next()) {
//            System.out.println("resultToTest.getObject() = " + resultToTest.getObject(resultToTest.getRow()));
//        }
//    }
//
//    @Test(expected = DAOException.class)
//    public void selectQueryTooMuchArgumentsTest() {
//        sql.selectQuery(
//                "select " +
//                        "(GroupId, name) values" +
//                        "(?, ?)",
//                "GroupId" ,
//                "name" ,
//                "fake" +
//                        "from `Group`"
//        );
//    }
//
//    @Test(expected = DAOException.class)
//    public void selectQueryTooFewArgumentsTest() {
//        sql.selectQuery(
//                "select " +
//                        "(GroupId, name) values" +
//                        "(?, ?)",
//                "GroupId" +
//                        "from `Group`"
//        );
//    }
//
//    @Test(expected = DAOException.class)
//    public void selectQuerySqlSyntaxExceptionTest() {
//        sql.selectQuery(
//                "select " +
//                        "(GroupId, name) values" +
//                        "(?, ?)",
//                "GroupId" ,
//                "name" +
//                        "from Group"
//        );
//    }

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


//    @Test
//    public void mapToSQLinsertTest() {
//        HashMap<String, Object> map = new HashMap<>() ;
//        map.put("identifier", 2) ;
//        map.put("date", Date.valueOf("1999-01-23")) ;
//        map.put("email", null) ;
//        map.put("name", "nameTest") ;
//        map.put(null, "") ;
//        assertEquals("(date, null, identifier, name, email ) values (1999-01-23, , 2, nameTest, null )", sql.mapToSQLinsert(map));
//    }

    @Test
    public void findBeansTest() {
        Collection<Group> listGroup = sql.findBeans(
                "select * from `Group`",
                resultSet -> {
                    Group group1 = new Group() ;
                    group1.setName("findBeanTest");
                    group1.setIdentifier(new Random().nextInt());
                    return group1 ;
                }
        ) ;
        assertFalse(listGroup.isEmpty());
        assertEquals(listGroup.size(), sql.countRow("select count(*) from `Group"));
    }
}
