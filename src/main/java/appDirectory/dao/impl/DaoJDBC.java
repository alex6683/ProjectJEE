package appDirectory.dao.impl;

import appDirectory.dao.GroupDAO;
import appDirectory.dao.PersonDAO;
import appDirectory.exception.DAOException;
import appDirectory.exception.DAOMapperException;
import appDirectory.model.Group;
import appDirectory.model.Person;
import appDirectory.utils.SqlTools;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Classe d'implentation de l'interface PersonDAO via le driver JDBC.
 * Etends la classe SqlTools pour les manipulations bean et sql.
 *
 * @author Mestrallet Alexis
 * @author Risch Philippe
 *
 * @date 23/10/2017
 * @version 3.5
 */
@Repository
public class DaoJDBC extends SqlTools implements PersonDAO, GroupDAO {

    /**
     * Context pour la création de beans
     */
    private static AbstractApplicationContext context =
            new ClassPathXmlApplicationContext("/spring/spring.xml");

    @PostConstruct
    public void init() {
        super.init();
    }

    @PreDestroy
    public void destroy() {
        super.destroy();
    }

    @Override
    public void addPerson(Person person) throws DAOException {
        int newId = insertBean("Person", DaoJDBC::personToResulSet, person) ;
        person.setIdentifier(newId);
    }

    @Override
    public boolean savePerson(Person person) throws DAOException {
        if(!updateBean(
                "select * from Person where personID = ?",
                DaoJDBC::personToResulSet,
                person,
                person.getIdentifier()
        )) {
            addPerson(person) ;
            return false ;
        }
        return true ;
    }

    @Override
    public boolean saveGroup(Group group) throws DAOException {
        if(!updateBean(
                "select * from `Group` where groupID = ?",
                DaoJDBC::groupToResulSet,
                group,
                group.getIdentifier()
        )) {
            addGroup(group) ;
            return false ;
        }
        return true ;
    }

    @Override
    public void addGroup(Group group) throws DAOException {
        int newId = insertBean("`Group`", DaoJDBC::groupToResulSet, group) ;
        group.setIdentifier(newId);
    }

    @Override
    public Collection<Person> findAllPerson() throws DAOException {
        return findBeans(
                "select * from Person ORDER BY name, surname",
                DaoJDBC::resultSetToPerson
        );
    }

    @Override
    public Collection<Group> findAllGroups() throws DAOException {
        return findBeans(
                "select * from `Group` ORDER BY name",
                DaoJDBC::resultSetToGroup
        );
    }

    @Override
    public Collection<Person> findAllPersonInGroup(Group group) throws DAOException {
        return findBeans(
                "select * from Person where groupID = ? ORDER BY name, surname",
                DaoJDBC::resultSetToPerson,
                group.getIdentifier()
        );
    }

    /**
     * Retrouve et renvoie toutes les personnes présentes dans un groupe donnée
     * @param groupID : L'identifiant du groupe donné
     * @return : La liste des personnes appartenant à group
     * @throws DAOException
     */
    public Collection<Person> findAllPersonInGroup(Integer groupID) throws DAOException {
        return findBeans(
                "select * from Person where groupID = ? ORDER BY name, surname",
                DaoJDBC::resultSetToPerson,
                groupID
        );
    }

    @Override
    public Person findPerson(Person person) throws DAOException {
        return this.findPerson(person.getIdentifier()) ;
    }

    @Override
    public Person findPerson(Integer personID) throws DAOException {
        Collection<Person> onePerson = findBeans(
                "select * from Person where personID = " + personID,
                DaoJDBC::resultSetToPerson
        ) ;
        return onePerson.toArray(new Person[1])[0] ;
    }

    @Override
    public Collection<Person> findPerson(Object keyWord) {
        return findBeans(
                "select * from Person where name like ? or surname like ? or email like ? ORDER BY name, surname",
                DaoJDBC::resultSetToPerson,
                keyWord, keyWord, keyWord
        ) ;
    }

    @Override
    public Group findGroup(Group group) throws DAOException {
        return this.findGroup(group.getIdentifier()) ;
    }

    @Override
    public Group findGroup(Integer groupID) throws DAOException {
        Collection<Group> oneGroup = findBeans(
                "select * from `Group` where groupID = " + groupID,
                DaoJDBC::resultSetToGroup
        ) ;
        return oneGroup.toArray(new Group[1])[0] ;
    }

    /**
     * Renvoie le groupe correspondant au nom passé en paramètre
     * @param name : le nom du groupe
     * @return : le groupe correspondant
     * @throws DAOException
     */
    public Group findOneGroup(String name) throws DAOException {
        Collection<Group> oneGroup = findBeans(
                "select * from `Group` where name like ?",
                DaoJDBC::resultSetToGroup,
                name
        ) ;
        return oneGroup.toArray(new Group[1])[0] ;
    }

    @Override
    public Collection<Group> findGroup(String keyWord) throws DAOException {
        return findBeans(
                "select * from `Group` where name like ? order by name",
                DaoJDBC::resultSetToGroup,
                keyWord
        ) ;
    }

    @Override
    public int deletePerson(Person person) throws DAOException {
        return deleteBeans("select * from Person where personID = ?", person.getIdentifier());
    }

    @Override
    public int deleteGroup(Group group) throws DAOException {
        return deleteBeans("select * from `Group` where groupID = ?", group.getIdentifier());
    }

    /**
     * Renvoie la liste de groupes avec le groupe passé en paramètre en tête de liste
     * @param group : Le groupe en tête de liste
     * @return : La liste de groupe
     * @throws SQLException
     */
    public Collection<Group> findAllGroupWithParamInFirst(Group group) throws SQLException {
        Collection<Group> listGroup= new ArrayList<Group>();
        listGroup.add(group);
        listGroup.addAll(findBeans(
                "select * from `Group` where groupID != ?",
                DaoJDBC::resultSetToGroup,
                group.getIdentifier())
        ) ;
        return listGroup;
    }

    /**
     * Map d'un resultSet à une personne
     * @param resultSet : Le resultSet à mapper
     * @return : La personne correspondante
     */
    static public Person resultSetToPerson(ResultSet resultSet) {
        Person person = new Person();
        try {
            person.setIdentifier(resultSet.getInt("personID"));
            person.setName(resultSet.getString("name"));
            person.setSurname(resultSet.getString("surname"));
            person.setEmail(resultSet.getString("email"));
            person.setWebSite(resultSet.getString("webSite"));
            person.setDateBirth(resultSet.getDate("dateBirth"));
            person.setPassword(resultSet.getString("password"));
            person.setDescription(resultSet.getString("description"));
            person.setGroupID(resultSet.getInt("groupID"));
        } catch (SQLException e) {
            throw new DAOMapperException(e) ;
        }
        return person ;
    }

    /**
     * Map d'un resultSet à un groupe
     * @param resultSet : Le resultSet à mapper
     * @return : Le groupe correspondante
     */
    static public Group resultSetToGroup(ResultSet resultSet) {
        Group group = new Group();
        context.registerShutdownHook();
        try {
            group.setIdentifier(resultSet.getInt("groupID"));
            group.setName(resultSet.getString("name"));
        } catch (SQLException e) {
            throw new DAOMapperException(e);
        }
        DaoJDBC jdbc = context.getBean(DaoJDBC.class) ;
        Collection<Person> persons = jdbc.findAllPersonInGroup(group) ;
        group.setPersons(persons) ;
        return group ;
    }

    /**
     * Map d'une personne à insérer ou modifier en ResultSet
     * @param person : La personne à mapper
     * @param sql : La requête SQL correpondante
     * @param params : Les paramètres de la requête SQL
     * @return : le ResultSet correpondant
     * @throws DAOMapperException
     */
    static public ResultSet personToResulSet(Person person, PreparedStatement sql, Object... params) throws DAOMapperException {
        ResultSet resultSet;
        try {
            int countParam = 1 ;
            for(Object param : params) {
                sql.setObject(countParam++, param);
            }
            resultSet = sql.executeQuery();
            if(!resultSet.next()) {
                resultSet.close();
                return null ;
            }
            if(params.length==0) {
                resultSet.moveToInsertRow();
            }
            resultSet.updateString("name", person.getName()) ;
            resultSet.updateString("surname", person.getSurname());
            resultSet.updateString("email", person.getEmail());
            resultSet.updateString("webSite", person.getWebSite());
            resultSet.updateDate("dateBirth", person.getDateBirth());
            resultSet.updateString("password", person.getPassword());
            resultSet.updateString("description", person.getDescription());
            resultSet.updateInt("groupID", person.getGroupID());
        } catch (SQLException e) {
            throw new DAOMapperException(e) ;
        }
        return resultSet ;
    }

    /**
     * Map d'un groupe à modifier ou insérer en ResultSet
     * @param group : Le groupe à mapper
     * @param sql : La requête sql correspondante
     * @param params : Les paramètres de la requête SQL
     * @return : le ResultSet correspondant
     * @throws DAOMapperException
     */
    static public ResultSet groupToResulSet(Group group, PreparedStatement sql, Object... params) throws DAOMapperException {
        ResultSet resultSet;
        try {
            int countParam = 1 ;
            for(Object param : params) {
                sql.setObject(countParam++, param);
            }
            resultSet = sql.executeQuery();
            if(!resultSet.next()) {
                resultSet.close();
                return null ;
            }
            if(params.length==0) {
                resultSet.moveToInsertRow();
            }
            resultSet.updateString("name", group.getName()) ;
        } catch (SQLException e) {
            throw new DAOMapperException(e) ;
        }
        return resultSet ;
    }
}
