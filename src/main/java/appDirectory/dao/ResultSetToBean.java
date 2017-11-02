package appDirectory.dao;

import appDirectory.exception.DAOMapperException;

import java.sql.ResultSet;

/**
 * Interface permettant la conversion d'un ResulSet d'une requête sql en un bean présent dans la
 * base de donnée
 *
 * @author Mestrallet Alexis
 * @author Risch Philippe
 *
 * @date 21/10/2017
 * @version 1.0
 *
 * @param <T> : La classe du bean
 */
public interface ResultSetToBean<T> {
    /**
     * Methode de conversion du ResulSet en bean
     * @param resultSet : Le ResulSet à convertir
     * @return : Le bean correspondant
     * @throws DAOMapperException
     */
    T toBean(ResultSet resultSet) throws DAOMapperException ;
}
