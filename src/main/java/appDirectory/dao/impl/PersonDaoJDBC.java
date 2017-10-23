package appDirectory.dao.impl;

import appDirectory.dao.BeanToResultSet;
import appDirectory.dao.PersonDao;
import appDirectory.exception.DAOException;
import appDirectory.exception.DAOMapperException;
import appDirectory.model.Group;
import appDirectory.model.Person;
import appDirectory.utils.SqlTools;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

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
    public int addPerson(Person person) throws DAOException {

        return 0;
    }

    public void addGroup(Group group) throws DAOException {

    }

    public void updateIfExist(Object object) throws DAOException {

    }

    public Collection<Person> findAllPerson() throws DAOException {
        return findBeans(
                "select * from Person",
                resultSet -> {
                    try {
                        Person bean = new Person() ;
                        String fieldName ;
                        Object fieldValue ;
                        for(int i=1 ; i<=resultSet.getMetaData().getColumnCount() ; i++) {
                            fieldName = resultSet.getMetaData().getColumnName(i).endsWith("ID") ?
                                    "identifier" : resultSet.getMetaData().getColumnName(i) ;
                            fieldValue = resultSet.getObject(i) ;
                            Field field = Person.class.getDeclaredField(fieldName) ;
                            field.setAccessible(true);
                            field.set(bean, fieldValue);
                            field.setAccessible(false);
                        }
                        return bean ;
                    } catch (SQLException e) {
                        e.printStackTrace();
                        throw new DAOMapperException(e) ;
                    } catch (NoSuchFieldException | IllegalAccessException e) {
                        e.printStackTrace();
                        throw new  DAOException(e) ;
                    }
                }
        );
    }

    public Collection<Group> findAllGroups() throws DAOException {
        return findBeans(
                "select * from `Group`",
                resultSet -> {
                    try {
                        Group bean = new Group() ;
                        String fieldName ;
                        Object fieldValue ;
                        for(int i=1 ; i<=resultSet.getMetaData().getColumnCount() ; i++) {
                            fieldName = resultSet.getMetaData().getColumnName(i).endsWith("ID") ?
                                    "identifier" : resultSet.getMetaData().getColumnName(i) ;
                            fieldValue = resultSet.getObject(i) ;
                            Field field = Group.class.getDeclaredField(fieldName) ;
                            field.setAccessible(true);
                            field.set(bean, fieldValue);
                            field.setAccessible(false);
                        }
                        return bean ;
                    } catch (SQLException e) {
                        e.printStackTrace();
                        throw new DAOMapperException(e) ;
                    } catch (NoSuchFieldException | IllegalAccessException e) {
                        e.printStackTrace();
                        throw new DAOException(e) ;
                    }
                }
        );
    }

    public Collection<Person> findAllPersonInGroup(Group group) throws DAOException {
        return findBeans(
                "select * from Person where groupID = ?",
                resultSet -> {
                    try {
                        Person bean = new Person() ;
                        String fieldName ;
                        Object fieldValue ;
                        for(int i=1 ; i<=resultSet.getMetaData().getColumnCount() ; i++) {
                            fieldName = resultSet.getMetaData().getColumnName(i).endsWith("ID") ?
                                    "identifier" : resultSet.getMetaData().getColumnName(i) ;
                            fieldValue = resultSet.getObject(i) ;
                            Field field = Person.class.getDeclaredField(fieldName) ;
                            field.setAccessible(true);
                            field.set(bean, fieldValue);
                            field.setAccessible(false);
                        }
                        return bean ;
                    } catch (SQLException e) {
                        e.printStackTrace();
                        throw new DAOMapperException(e) ;
                    } catch (NoSuchFieldException | IllegalAccessException e) {
                        e.printStackTrace();
                        throw new DAOException(e) ;
                    }
                },
                group.getIdentifier()
        );
    }

    public Person findPerson(Person person) throws DAOException {
        Collection<Person> onePerson = findBeans(
                "select * from Person where personID = ?",
                resultSet -> {
                    try {
                        Person bean = new Person() ;
                        String fieldName ;
                        Object fieldValue ;
                        for(int i=1 ; i<=resultSet.getMetaData().getColumnCount() ; i++) {
                            fieldName = resultSet.getMetaData().getColumnName(i).endsWith("ID") ?
                                    "identifier" : resultSet.getMetaData().getColumnName(i) ;
                            fieldValue = resultSet.getObject(i) ;
                            Field field = Person.class.getDeclaredField(fieldName) ;
                            field.setAccessible(true);
                            field.set(bean, fieldValue);
                            field.setAccessible(false);
                        }
                        return bean ;
                    } catch (SQLException e) {
                        e.printStackTrace();
                        throw new DAOMapperException(e) ;
                    } catch (NoSuchFieldException | IllegalAccessException e) {
                        e.printStackTrace();
                        throw new  DAOException(e) ;
                    }
                },
                person.getIdentifier()
        ) ;
        return onePerson.toArray(new Person[1])[0] ;
    }

    public Group findGroup(Group group) throws DAOException {
        Collection<Group> oneGroup = findBeans(
                "select * from `Group` where groupID = ?",
                resultSet -> {
                    try {
                        Group bean = new Group() ;
                        String fieldName ;
                        Object fieldValue ;
                        for(int i=1 ; i<=resultSet.getMetaData().getColumnCount() ; i++) {
                            fieldName = resultSet.getMetaData().getColumnName(i).endsWith("ID") ?
                                    "identifier" : resultSet.getMetaData().getColumnName(i) ;
                            fieldValue = resultSet.getObject(i) ;
                            Field field = Group.class.getDeclaredField(fieldName) ;
                            field.setAccessible(true);
                            field.set(bean, fieldValue);
                            field.setAccessible(false);
                        }
                        return bean ;
                    } catch (SQLException e) {
                        e.printStackTrace();
                        throw new DAOMapperException(e) ;
                    } catch (NoSuchFieldException | IllegalAccessException e) {
                        e.printStackTrace();
                        throw new  DAOException(e) ;
                    }
                },
                group.getIdentifier()
        ) ;
        return oneGroup.toArray(new Group[1])[0] ;
    }

    public void deletePerson(Person person) {
        try {
            deleteBean(
                    "select * from Person where personID = ?",
                    (bean, preparedStatement, params) -> {
                        ResultSet resultSet = null;
                        try {
                            preparedStatement.setInt(1, bean.getIdentifier());
                            resultSet = preparedStatement.executeQuery() ;
                            if(!resultSet.next()) {
                                return null ;
                            }
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        return resultSet ;
                    },
                    person
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteGroup(Group group) {
        try {
            deleteBean(
                    "select * from `Group` where groupID = ?",
                    (bean, preparedStatement, params) -> {
                        ResultSet resultSet = null;
                        try {
                            preparedStatement.setInt(1, bean.getIdentifier());
                            resultSet = preparedStatement.executeQuery() ;
                            if(!resultSet.next()) {
                                return null ;
                            }
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        return resultSet ;
                    },
                    group
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
