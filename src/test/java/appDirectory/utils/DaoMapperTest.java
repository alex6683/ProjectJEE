package appDirectory.utils;

import appDirectory.exception.DAOMapperException;
import appDirectory.model.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Date;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/spring/spring.xml")
public class DaoMapperTest {

    @Autowired
    ApplicationContext context ;

    @Autowired
    Person person ;

    /*
    TODO: Gerer la levé d'exception instanceToMapper
    */

    @Test
    public void instanceToMapTest() {
        HashMap<String, Object> mapToTest = DaoMapper.instanceToMap(person) ;

        //Création de l'instance Person sans injection pour comparer les maps
        Person personToMap = new Person() ;
        personToMap.init();
        personToMap.setIdentifier(-1);

        assertEquals(mapToTest, DaoMapper.instanceToMap(personToMap));

        personToMap.setName("notSameName");
        assertNotEquals(mapToTest, DaoMapper.instanceToMap(personToMap));
    }

    @Test
    public void mapToInstanceTest() {
        HashMap<String, Object> mapToObject = new HashMap<>() ;
        mapToObject.put("identifier", 1) ;
        mapToObject.put("name", "MESTRALLET") ;
        mapToObject.put("surname", "Alexis") ;
        mapToObject.put("dateBirth", Date.valueOf("1995-01-01")) ;
        mapToObject.put("webSite", "") ;
        mapToObject.put("password", null) ;
        mapToObject.put("groupID", null) ;

        Person personToTest = (Person) DaoMapper.mapToInstance(Person.class, mapToObject);

        assertEquals(mapToObject.get("name"), personToTest.getName());
        assertEquals(mapToObject.get("password"), personToTest.getPassword());
        assertEquals(mapToObject.get("webSite"), personToTest.getWebSite());

        //Test la valeur par default
        assertEquals("prenom.nom@etu.univ-amu.fr", personToTest.getEmail());
    }

    @Test(expected = DAOMapperException.class)
    public void mapToInstanceNoSuchFieldTest() {
        HashMap<String, Object> fieldNotExist = new HashMap<>() ;
        fieldNotExist.put("notExist", "value") ;
        DaoMapper.mapToInstance(Person.class, fieldNotExist) ;
    }
}