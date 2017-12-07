package appDirectory.dao;

import appDirectory.exception.DAOException;
import appDirectory.model.Group;

import java.util.Collection;

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
     */
    int deleteGroup(Group group) throws DAOException;

}
