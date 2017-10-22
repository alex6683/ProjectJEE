package appDirectory.dao;

import appDirectory.exception.DAOMapperException;

import java.sql.ResultSet;

public interface ResultSetToBean<T> {
    T toBean(ResultSet resultSet) throws DAOMapperException ;
}
