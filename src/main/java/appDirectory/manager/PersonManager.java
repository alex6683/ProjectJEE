package appDirectory.manager;

import appDirectory.dao.impl.DaoJDBC;
import appDirectory.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Service de gestion des personnes côté Métier de l'application, avec accès et gestion de la base de donnée par la DAO
 *
 * @author Mestrallet Alexis
 * @author Risch Philippe
 *
 * @date 23/10/2017
 * @version 1.0
 */
@Service
public class PersonManager {

	/**
	 * Accès et gestion de la base de donnée
	 */
	private DaoJDBC daoJDBC;

    @Autowired
    public void setDaoJDBC(DaoJDBC daoJDBC) {
        this.daoJDBC = daoJDBC;
    }

	/**
	 * Retrouve et renvoie toutes les personnes présentes dans la base de donnée
	 * @return : La liste des groupes
	 */
	public Collection<Person> findAllPerson(){
		return daoJDBC.findAllPerson();
	}

	/**
	 * Retrouve et renvoie une personne de la base de donnée
	 * @param personID : L'identifiant de la personne à retrouver
	 * @return : La personne trouvée
	 */
	public Person findPerson(Integer personID){
		return daoJDBC.findPerson(personID);
	}

	/**
	 * Retrouve et renvoie une personne de la base de donnée
	 * @param person: La personne à retrouver
	 * @return : La personne trouvée
	 */
	public Person findPerson(Person person){
		return daoJDBC.findPerson(person);
	}

	/**
	 * Retrouve et renvoie toutes les personnes présentes dans un groupe donnée
	 * @param groupID : L'identifiant du groupe donné
	 * @return : La liste des personnes appartenant à group
	 */
	public Collection<Person> findAllPersonInGroup(Integer groupID){
		return daoJDBC.findAllPersonInGroup(groupID) ;
	}

	/**
	 * Supprime une personne donnée de la base de donnée
	 * @param person : La personne à supprimer
	 */
	public int deletePerson(Person person){
			return daoJDBC.deletePerson(person);
	}

	/**
	 * Modifie la personne dans la base de donnée si elle existe, l'ajoute sinon
	 * @param person : La personne à ajouter ou modifier.
	 */
	public boolean savePerson(Person person){
			return daoJDBC.savePerson(person);
	}

	/**
	 * Renvoie un liste de personnes correspondant à un mot clé
	 * @param keyword : Le mot clé sur lequel la recherche se base
	 * @return : La liste de personnes associé au mot clé
	 */
	public ArrayList<Person> searchPerson(String keyword){
		ArrayList<Person> list = new ArrayList<>();
		list.addAll(daoJDBC.findPerson(keyword));
		return list;
	}
}
