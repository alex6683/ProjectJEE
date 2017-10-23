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
     * Modification le groupe dans la base de donnée s'il existe, l'ajoute sinon
     * @param group : Le groupe à modifier
     * @throws DAOException
     */
    boolean saveGroup(Group group) throws DAOException ;

    /**
     * Ajout d'un groupe dans la base de donnée
     * @param group : Le groupe à ajouter
     * @return : Le nombre de groupe ajouté
     * @throws DAOException
     */
    int addGroup(Group group) throws DAOException ;

    /**
     * Retrouve et renvoie tout les groupes présents dans la base de donnée
     * @return : La liste des groupes
     * @throws DAOException
     */
    Collection<Group> findAllGroups() throws DAOException ;

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
     * Retrouve et renvoie un groupe de la base de donnée
     * @param group : Le groupe à retrouver
     * @return : Le groupe trouvé
     * @throws DAOException
     */
    Group findGroup(Group group) throws DAOException ;

    /**
     * Supprime une personne donnée de la base de donnée
     * @param person : La personne à supprimer
     */
    int deletePerson(Person person) throws DAOException;

    /**
     * Supprime un groupe donnée de la base de donnée
     * @param group : Le groupe à supprimer
     */
    int deleteGroup(Group group) throws DAOException ;

}
