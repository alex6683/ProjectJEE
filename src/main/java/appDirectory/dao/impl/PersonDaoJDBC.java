package appDirectory.dao.impl;

import appDirectory.dao.PersonDao;
import appDirectory.exception.DAOException;
import appDirectory.exception.DAOMapperException;
import appDirectory.model.Group;
import appDirectory.model.Person;
import appDirectory.utils.SqlTools;
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
 * @version 3.0
 */
@Repository
public class PersonDaoJDBC extends SqlTools implements PersonDao {

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
        insertBean("Person", PersonDaoJDBC::personToResulSet, person) ;
    }

    @Override
    public boolean savePerson(Person person) throws DAOException {
        if(!updateBean(
                "select * from Person where personID = ?",
                PersonDaoJDBC::personToResulSet,
                person
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
                PersonDaoJDBC::groupToResulSet,
                group
        )) {
            addGroup(group) ;
            return false ;
        }
        return true ;
    }

    public int addGroup(Group group) throws DAOException {
        Collection<Group> groups = new ArrayList<>() ;
        groups.add(group) ;
        return 0 ;
    }

    public Collection<Person> findAllPerson() throws DAOException {
        return findBeans(
                "select * from Person",
                PersonDaoJDBC::resultSetToPerson
        );
    }

    public Collection<Group> findAllGroups() throws DAOException {
        return findBeans(
                "select * from `Group`",
                PersonDaoJDBC::resultSetToGroup
        );
    }

    public Collection<Person> findAllPersonInGroup(Group group) throws DAOException {
        return findBeans(
                "select * from Person where groupID = ?",
                PersonDaoJDBC::resultSetToPerson,
                group.getIdentifier()
        );
    }

    public Person findPerson(Person person) throws DAOException {
        Collection<Person> onePerson = findBeans(
                "select * from Person where personID = ?",
                PersonDaoJDBC::resultSetToPerson,
                person.getIdentifier()
        ) ;
        return onePerson.toArray(new Person[1])[0] ;
    }

    public Group findGroup(Group group) throws DAOException {
        Collection<Group> oneGroup = findBeans(
                "select * from `Group` where groupID = ?",
                PersonDaoJDBC::resultSetToGroup,
                group.getIdentifier()
        ) ;
        return oneGroup.toArray(new Group[1])[0] ;
    }

    public int deletePerson(Person person) throws DAOException {
        return deleteBeans("select * from Person where personID = ?", person.getIdentifier());
    }

    public int deleteGroup(Group group) throws DAOException {
         return deleteBeans("select * from `Group` where groupID = ?", group.getIdentifier());
    }

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
            person.setPassword(resultSet.getString("description"));
        } catch (SQLException e) {
            throw new DAOMapperException(e) ;
        }
        try(
                Connection connection = new SqlTools().newConnection() ;
                PreparedStatement newStatement = connection.prepareStatement(
                        "select * from `Group` where groupID = ?"
                )
        ) {
            newStatement.setInt(1, resultSet.getInt("groupID"));
            person.setGroup(resultSetToGroup(newStatement.executeQuery()));
            newStatement.close();
        } catch (SQLException e) {
            throw new DAOMapperException(e) ;
        }
        return person ;
    }

    static public Group resultSetToGroup(ResultSet resultSet) {
        Group group = new Group();
        try {
            group.setIdentifier(resultSet.getInt("groupID"));
            group.setName(resultSet.getString("name"));
        } catch (SQLException e) {
            throw new DAOMapperException(e);
        }
        Collection<Person> persons = new PersonDaoJDBC().findAllPersonInGroup(group);
        group.setPersons(persons);
        return group ;
    }

    static public ResultSet personToResulSet(Person person, PreparedStatement sql) {
        ResultSet resultSet = null;
        try {
            sql.setInt(1, person.getIdentifier());
            resultSet = sql.executeQuery();
            if(!resultSet.next()) {
                resultSet.close();
                return null ;
            }
            resultSet.updateString("name", person.getName()) ;
            resultSet.updateString("surname", person.getSurname());
            resultSet.updateString("email", person.getEmail());
            resultSet.updateString("webSite", person.getWebSite());
            resultSet.updateDate("dateBirth", (Date) person.getDateBirth());
            resultSet.updateString("password", person.getPassword());
            resultSet.updateString("description", person.getDescription());
            resultSet.updateInt("groupID", person.getGroup().getIdentifier());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet ;
    }


    static public ResultSet groupToResulSet(Group group, PreparedStatement sql) {
        ResultSet resultSet = null;
        try {
            sql.setInt(1, group.getIdentifier());
            resultSet = sql.executeQuery();
            if(!resultSet.next()) {
                resultSet.close();
                return null ;
            }
            resultSet.updateString("name", group.getName()) ;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet ;
    }
}
