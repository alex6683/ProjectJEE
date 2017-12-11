package appDirectory.dao;

import appDirectory.exception.DAOException;
import appDirectory.model.Group;
import appDirectory.model.Person;

import java.util.Collection;

/**
 * Interface d'accès et manipulation de personnes dans la base de donnée
 *
 * @author Mestrallet Alexis
 * @author Risch Philippe
 *
 * @date 19/10/2017
 * @version 1.0
 */
public interface PersonDAO {

    void init() ;

    void destroy() ;

    /**
     * Ajout d'une personne dans la base de donnée
     * @param person : La personne à ajouter
     * @throws DAOException
     */
    void addPerson(Person person) throws DAOException ;

    /**
     * Modifie la personne dans la base de donnée si elle existe, l'ajoute sinon
     * @param person : La personne à ajouter ou modifier.
     * @throws DAOException
     */
    boolean savePerson(Person person) throws DAOException;

    /**
     * Liste les personnes présentes dans la base de données
     * @return : La liste de personnes
     * @throws DAOException
     */
    public Collection<Person> findAllPerson() throws DAOException ;

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
     * @throws DAOException
     */
    int deletePerson(Person person) throws DAOException;

    /**
     * Renvoie un liste de personnes correspondant à un mot clé
     * @param keyWord : Le mot clé sur lequel la recherche se base
     * @return : La liste de personnes associé au mot clé
     */
    Collection<Person> findPerson(Object keyWord) ;
}
