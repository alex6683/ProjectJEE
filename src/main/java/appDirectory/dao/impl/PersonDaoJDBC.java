package appDirectory.dao.impl;

import appDirectory.dao.PersonDao;
import appDirectory.exception.DAOException;
import appDirectory.model.Group;
import appDirectory.model.Person;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public class PersonDaoJDBC implements PersonDao {

    public void init() {

    }

    public void destroy() {

    }

    public void addPerson(Person person) throws DAOException {
        
    }

    public void addGroup(Group group) throws DAOException {

    }

    public void updateIfExist(Object object) throws DAOException {

    }

    public Collection<Group> findAllGroups() throws DAOException {
        return null;
    }

    public Collection<Person> findAllPersonInGroup(int groupId) throws DAOException {
        return null;
    }

    public Person findPerson(int idPerson) throws DAOException {
        return null;
    }

    public Group findGroup(int idGroup) throws DAOException {
        return null;
    }

}
