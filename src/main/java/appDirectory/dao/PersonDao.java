package appDirectory.dao;

import appDirectory.exception.DAOException;
import appDirectory.model.Group;
import appDirectory.model.Person;

import java.util.Collection;

public interface PersonDao {

    void init() ;

    void destroy() ;

    int addPerson(Person person) throws DAOException ;

    void updateIfExist(Object object) throws DAOException ;

    void addGroup(Group group) throws DAOException ;

    Collection<Group> findAllGroups() throws DAOException ;

    Collection<Person> findAllPersonInGroup(int groupId) throws DAOException ;

    Person findPerson(int idPerson) throws DAOException ;

    Group findGroup(int idGroup) throws DAOException ;

}
