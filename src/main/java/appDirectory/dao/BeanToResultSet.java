package appDirectory.dao;

import appDirectory.exception.DAOMapperException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;

public interface BeanToResultSet<T> {
    ResultSet toResultSet(T bean, PreparedStatement preparedStatement, Object... params) throws DAOMapperException;
}
