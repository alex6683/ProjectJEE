package appDirectory.manager;

import appDirectory.dao.impl.DaoJDBC;
import appDirectory.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * Service de gestion des connexions côté Métier de l'application, avec accès à la base de donnée par la DAO
 *
 * @author Mestrallet Alexis
 * @author Risch Philippe
 *
 * @date 23/10/2017
 * @version 1.0
 */
@Service
public class LoginManager {

	/**
	 * Accès à la base de donnée
	 */
	private DaoJDBC daoJDBC;

	@Autowired
	public void setDaoJDBC(DaoJDBC daoJDBC) {
		this.daoJDBC = daoJDBC;
	}

	/**
	 * Vérifie si l'identifiant de connexion (email) existe dans la base de donnée
	 * @param person : La personne en cours de connexion
	 * @return : vrai si l'identifiant existe, faux sinon
	 */
	public boolean checkLogin(Person person){
		return daoJDBC.findPerson(person.getEmail()) != null ;
	}

	/**
	 * Vérifie si l'authentification de la personne est valide
	 * @param person : La personne à authentifier
	 * @return Vrai si l'authentification est un succès, faux sinon
	 */
	public boolean checkAuthentication(Person person){
			return loginPerson(person.getEmail(), person.getPassword()) ;
	}

	/**
	 * Retrouve la premiere personne associée à une adresse mail selon le mail d'une personne
	 * @param person : la personne dont nous souhaitons récupérer le mail
	 * @return : La première personne dans la liste correpondant à ce mail
	 * @deprecated : Le mail deviendra à terme unique
	 */
	public Person personByMail(Person person){
		Collection<Person> persons = daoJDBC.findPerson(person.getEmail()) ;
		return persons.iterator().next() ;
	}

	/**
	 * Recupère la personne selon le login passé en paramètre,
	 * et vérifie si le mot de passe en base de donnée correspond à celui passé en paramètre
	 * @param login : l'identifiant de connexion
	 * @param password : le mot de passe de l'utilisateur
	 * @return vrai si les l'identification et le mot de passe sont valide, faux sinon
	 */
	private boolean loginPerson(String login, String password) {
		Collection<Person> persons = daoJDBC.findPerson(login) ;
		if(!persons.isEmpty()) {
			Person person = persons.iterator().next();
			if (person != null && person.getPassword().equals(password)) {
				return true;
			}
		}
		return false ;
	}

}
