package appDirectory.dao;

import appDirectory.exception.DAOException;
import appDirectory.model.Group;

import java.util.Collection;

/**
 * Interface d'accès et manipulation de groupe dans la base de donnée
 *
 * @author Mestrallet Alexis
 * @author Risch Philippe
 *
 * @date 19/10/2017
 * @version 1.0
 */
public interface GroupDAO {

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
    void addGroup(Group group) throws DAOException ;

    /**
     * Retrouve et renvoie tout les groupes présents dans la base de donnée
     * @return : La liste des groupes
     * @throws DAOException
     */
    Collection<Group> findAllGroups() throws DAOException ;


    /**
     * Retrouve et renvoie un groupe de la base de donnée
     * @param group : Le groupe à retrouver
     * @return : Le groupe trouvé
     * @throws DAOException
     */
    Group findGroup(Group group) throws DAOException ;

    /**
     * Retrouve et renvoie un groupe de la base de donnée
     * @param groupID : Le groupe à retrouver
     * @return : Le groupe trouvé
     * @throws DAOException
     */
    Group findGroup(Integer groupID) throws DAOException ;

    /**
     * Supprime un groupe donnée de la base de donnée
     * @param group : Le groupe à supprimer
     * @return : le nombre de groupe supprimé
     * @throws DAOException
     */
    int deleteGroup(Group group) throws DAOException;

    /**
     * Renvoie un liste de groupes correspondant à un mot clé
     * @param keyWord : Le mot clé sur lequel la recherche se base
     * @return : La liste de groupes associé au mot clé
     */
    Collection<Group> findGroup(String keyWord) throws DAOException ;

}
