package appDirectory.dao.impl;

import appDirectory.dao.PersonDao;
import appDirectory.exception.DAOException;
import appDirectory.model.Group;
import appDirectory.model.Person;
import appDirectory.utils.DaoMapper;
import appDirectory.utils.SqlTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.sql.DataSource;
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

    public int addPerson(Person person) throws DAOException {
        HashMap<String, Object> personMap = DaoMapper.instanceToMap(person) ;
        SqlTools sql = new SqlTools() ;
        sql.setConnection(DataSourceUtils.getConnection(dataSource));
        StringBuilder query = new StringBuilder("insert into Person (") ;
        for(String column : personMap.keySet()) {
            query.append(column).append(", ") ;
        }
        query.deleteCharAt(query.lastIndexOf(", ")).append(") values (") ;
        for(Object value : personMap.values()) {
            if(!value.equals("null")) {
                System.out.println("value = " + value);
                query.append("'").append(value).append("'").append(", ");
            }
            else {
                query.append("null").append(", ");
            }
        }
        query.deleteCharAt(query.lastIndexOf(", ")).append(")") ;
        System.out.println("query.toString() = " + query.toString());
        int result = sql.updateQuery(query.toString()) ;
        System.out.println(sql.selectQuery("select * from Person where name like '%Test%' ")) ;
        return result ;
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

    @Override
    public void deletePerson(Person person) {

    }

    @Override
    public void deleteGroup(Group group) {

    }

}
