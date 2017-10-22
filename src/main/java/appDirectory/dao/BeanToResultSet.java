package appDirectory.dao;

import appDirectory.exception.DAOMapperException;

import java.sql.ResultSet;
import java.util.Map;

public interface BeanToResultSet<T> {
    <K,V> Map<K,V> toResultSet(T bean, ResultSet resultSet) throws DAOMapperException;
}
