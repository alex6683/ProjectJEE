package appDirectory.dao;

import appDirectory.exception.DAOException;
import appDirectory.model.Group;
import appDirectory.model.Person;

import java.util.Collection;

public interface PersonDao {

    void init() ;

    void destroy() ;

    int addPerson(Person person) throws DAOException ;

    void updatePerson(Person person) throws DAOException ;

    void updateGroup(Group group) throws DAOException ;

    int addGroup(Group group) throws DAOException ;

    Collection<Group> findAllGroups() throws DAOException ;

    Collection<Person> findAllPersonInGroup(Group group) throws DAOException ;

    Person findPerson(Person person) throws DAOException ;

    Group findGroup(Group group) throws DAOException ;

    void deletePerson(Person person) ;

    void deleteGroup(Group group) ;

}
