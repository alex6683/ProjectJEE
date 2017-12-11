package appDirectory.dao;

import appDirectory.exception.DAOMapperException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;

/**
 * Interface permettant la conversion d'un bean donnée en un ResulSet correspondant
 *
 * @author Mestrallet Alexis
 * @author Risch Philippe
 *
 * @date 21/10/2017
 * @version 1.1
 *
 * @param <T> : La classe du bean
 */
public interface BeanToResultSet<T> {
    /**
     * La méthode de conversion bean en ResulSet
     * @param bean : Le bean à convertir
     * @param preparedStatement : Le PreparedStatement afin de crée le ResultSet selon les paramètres données
     * @return : Le ResulSet correspondant aux informations relatives au bean dans la base de donnée
     * @throws DAOMapperException
     */
    ResultSet toResultSet(T bean, PreparedStatement preparedStatement, Object... params) throws DAOMapperException;
}
