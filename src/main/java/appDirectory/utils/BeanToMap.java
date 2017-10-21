package appDirectory.utils;

import appDirectory.exception.DAOMapperException;

import java.util.Map;

public interface BeanToMap<T> {
    <K,V> Map<K,V> toMap(T bean) throws DAOMapperException;
}
