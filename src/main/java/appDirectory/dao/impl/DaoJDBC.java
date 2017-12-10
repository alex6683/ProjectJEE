package appDirectory.dao.impl;

import appDirectory.dao.GroupDAO;
import appDirectory.dao.PersonDao;
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
 * @version 3.0
 */
@Repository
public class DaoJDBC extends SqlTools implements PersonDao, GroupDAO {

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
        System.out.println("true = " + true);
        return true ;
    }

    public void addGroup(Group group) throws DAOException {
        int newId = insertBean("`Group`", DaoJDBC::groupToResulSet, group) ;
        group.setIdentifier(newId);
    }

    public Collection<Person> findAllPerson() throws DAOException {
        return findBeans(
                "select * from Person",
                DaoJDBC::resultSetToPerson
        );
    }

    public Collection<Group> findAllGroups() throws DAOException {
        return findBeans(
                "select * from `Group`",
                DaoJDBC::resultSetToGroup
        );
    }

    public Collection<Person> findAllPersonInGroup(Group group) throws DAOException {
        return findBeans(
                "select * from Person where groupID = ?",
                DaoJDBC::resultSetToPerson,
                group.getIdentifier()
        );
    }

    public Collection<Person> findAllPersonInGroup(Integer groupID) throws DAOException {
        return findBeans(
                "select * from Person where groupID = ?",
                DaoJDBC::resultSetToPerson,
                groupID
        );
    }

    public Person findPerson(Person person) throws DAOException {
        return this.findPerson(person.getIdentifier()) ;
    }

    public Person findPerson(Integer personID) throws DAOException {
        Collection<Person> onePerson = findBeans(
                "select * from Person where personID = " + personID,
                DaoJDBC::resultSetToPerson
        ) ;
        return onePerson.toArray(new Person[1])[0] ;
    }

    public Collection<Person> findPerson(Object keyWord) {
        return findBeans(
                "select * from Person where name like ? or surname like ? or email like ?",
                DaoJDBC::resultSetToPerson,
                keyWord, keyWord, keyWord
        ) ;
    }

    public Group findGroup(Group group) throws DAOException {
        return this.findGroup(group.getIdentifier()) ;
    }

    public Group findGroup(Integer groupID) throws DAOException {
        Collection<Group> oneGroup = findBeans(
                "select * from `Group` where groupID = " + groupID,
                DaoJDBC::resultSetToGroup
        ) ;
        return oneGroup.toArray(new Group[1])[0] ;
    }

    public Group findOneGroup(String keyWord) throws DAOException {
        Collection<Group> oneGroup = findBeans(
                "select * from `Group` where name like ?",
                DaoJDBC::resultSetToGroup,
                keyWord
        ) ;
        return oneGroup.toArray(new Group[1])[0] ;
    }

    public Collection<Group> findGroup(String keyWord) throws DAOException {
        return findBeans(
                "select * from `Group` where name like ?",
                DaoJDBC::resultSetToGroup,
                keyWord
        ) ;
    }

    public int deletePerson(Person person) throws DAOException {
        return deleteBeans("select * from Person where personID = ?", person.getIdentifier());
    }

    public int deleteGroup(Group group) throws DAOException {
        return deleteBeans("select * from `Group` where groupID = ?", group.getIdentifier());
    }

    public boolean loginPerson(String login, String password) {
        Collection<Person> persons = findPerson(login) ;
//        if(persons.size() == 1) {
        Person person = persons.iterator().next() ;
        if(person != null && person.getPassword().equals(password)) {
            return true ;
        }
//        } else {
//            throw new IllegalStateException("login non unique") ;
//        }
        return false ;
    }


    /**
     * FOR FIND PURPOSE
     * @param resultSet
     * @return
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
     * FOR FIND PURPOSE
     * @param resultSet
     * @return
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
     * AJOUT + UPDATE
     * @param person
     * @param sql
     * @return
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
     * AJOUT + UPDATE
     * @param group
     * @param sql
     * @return
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

    public Collection<Group> findAllGroupWithGroupInFirst(Group group) throws SQLException {
        Collection<Group> listGroup= new ArrayList<Group>();
        listGroup.add(group);
        String query = "SELECT * FROM `Group` WHERE groupID!= "+ group.getIdentifier();

        try (Connection conn = newConnection()){
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Group g = new Group();

                g.setIdentifier( rs.getInt("groupID") );
                g.setName( rs.getString("name") );
                listGroup.add(g);
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException();
        }
        return listGroup;
    }
}
