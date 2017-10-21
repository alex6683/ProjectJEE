package appDirectory.utils;

import appDirectory.exception.DAOMapperException;

import java.sql.ResultSet;

public interface ResultSetToBean<T> {
    T toBean(ResultSet resultSet) throws DAOMapperException ;
}
