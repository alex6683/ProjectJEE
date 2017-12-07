package appDirectory.dao;

import appDirectory.exception.DAOException;
import appDirectory.model.Group;
import appDirectory.model.Person;

import java.util.Collection;

/**
 * Interface d'accès et manipulation de la base de donnée
 *
 * @author Mestrallet Alexis
 * @author Risch Philippe
 *
 * @date 19/10/2017
 * @version 1.0
 */
public interface PersonDao {

    void init() ;

    void destroy() ;

    /**
     * Ajout d'une personne dans la base de donnée
     * @param person : La personne à ajouter
     * @return : Le nombre de personne ajoutée
     * @throws DAOException
     */
    void addPerson(Person person) throws DAOException ;

    /**
     * Modifie la personne dans la base de donnée si elle existe, l'ajoute sinon
     * @param person
     * @throws DAOException
     */
    boolean savePerson(Person person) throws DAOException;

    /**
     * Retrouve et renvoie toutes les personnes présentes dans un groupe donnée
     * @param group : Le groupe donnée
     * @return : La liste des personnes appartenant à group
     * @throws DAOException
     */
    Collection<Person> findAllPersonInGroup(Group group) throws DAOException ;

    /**
     * Retrouve et renvoie une personne de la base de donnée
     * @param person : La personne à retrouver
     * @return : La personne trouvée
     * @throws DAOException
     */
    Person findPerson(Person person) throws DAOException ;

    /**
     * Retrouve et renvoie une personne de la base de donnée
     * @param personID : La personne à retrouver
     * @return : La personne trouvée
     * @throws DAOException
     */
    Person findPerson(Integer personID) throws DAOException ;

    /**
     * Supprime une personne donnée de la base de donnée
     * @param person : La personne à supprimer
     */
    int deletePerson(Person person) throws DAOException;

}
