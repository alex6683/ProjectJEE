package appDirectory.dao.impl;

import appDirectory.dao.PersonDao;
import appDirectory.exception.DAOException;
import appDirectory.model.Group;
import appDirectory.model.Person;
import appDirectory.utils.DaoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.sql.DataSource;
import java.sql.Connection;
import java.util.Collection;
import java.util.HashMap;

@Repository
public class PersonDaoJDBC implements PersonDao {

    private DataSource dataSource ;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @PostConstruct
    public void init() {

    }

    @PreDestroy
    public void destroy() {

    }

    public void addPerson(Person person) throws DAOException {
        HashMap<String, String> personMap = DaoMapper.instanceToMap(person) ;

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
