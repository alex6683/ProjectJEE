package appDirectory.manager;

import appDirectory.dao.impl.DaoJDBC;
import appDirectory.exception.DAOException;
import appDirectory.model.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Service de gestion des groupes côté Métier de l'application, avec accès et gestion de la base de donnée par la DAO
 *
 * @author Mestrallet Alexis
 * @author Risch Philippe
 *
 * @date 23/10/2017
 * @version 1.0
 */
@Service
public class GroupManager {

	/**
	 * Accès et gestion de la base de donnée
	 */
	private DaoJDBC daoJDBC;

	@Autowired
	public void setDaoJDBC(DaoJDBC daoJDBC) {
		this.daoJDBC = daoJDBC;
	}

	/**
	 * Retrouve et renvoie tout les groupes présents dans la base de donnée
	 * @return : La liste des groupes
	 */
	public Collection<Group> findAllGroup(){
		return daoJDBC.findAllGroups() ;
	}

	/**
	 * Renvoie la liste de groupes avec le groupe passé en paramètre en tête de liste
	 * @param group : Le groupe en tête de liste
	 * @return : La liste de groupe
	 * @throws SQLException
	 */
	public Collection<Group> findAllGroupWithGroupInFirst(Group group){
		try {
			return daoJDBC.findAllGroupWithParamInFirst(group) ;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null ;
	}

	/**
	 * Retrouve et renvoie un groupe de la base de donnée
	 * @param groupID : Le groupe à retrouver
	 * @return : Le groupe trouvé
	 */
	public Group findGroup(Integer groupID) {
		return daoJDBC.findGroup(groupID) ;
	}

	/**
	 * Retrouve et renvoie un groupe de la base de donnée
	 * @param group : Le groupe à retrouver
	 * @return : Le groupe trouvé
	 */
	public Group findGroup(Group group) {
		return daoJDBC.findGroup(group) ;
	}

	/**
	 * Renvoie le groupe correspondant au nom passé en paramètre
	 * @param name : le nom du groupe
	 * @return : le groupe correspondant
	 */
	public Group findGroupByName(String name){
		return daoJDBC.findOneGroup(name) ;
	}

	/**
	 * Supprime un groupe donnée de la base de donnée
	 * @param group : Le groupe à supprimer
	 * @return : le nombre de groupe supprimé
	 */
	public int deleteGroup(Group group){
		return daoJDBC.deleteGroup(group) ;
	}

	/**
	 * Modification le groupe dans la base de donnée s'il existe, l'ajoute sinon
	 * @param group : Le groupe à modifier
	 * @throws DAOException
	 */
	public boolean saveGroup(Group group) {
		return daoJDBC.saveGroup(group) ;
	}

	/**
	 * Renvoie un liste de groupes correspondant à un mot clé
	 * @param keyword : Le mot clé sur lequel la recherche se base
	 * @return : La liste de groupes associé au mot clé
	 */
	public ArrayList<Group> searchGroup(String keyword){
		ArrayList<Group> list = new ArrayList<>();
		list.addAll(daoJDBC.findGroup(keyword));
		return list;
	}
}













