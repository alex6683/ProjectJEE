package appDirectory.web;

import appDirectory.dao.impl.DaoJDBC;
import appDirectory.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class LoginManager {

	private DaoJDBC daoJDBC;

	@Autowired
	public void setDaoJDBC(DaoJDBC daoJDBC) {
		this.daoJDBC = daoJDBC;
	}

	public boolean checkLogin(Person person){
		return daoJDBC.findPerson(person.getEmail()) != null ;
	}

	public boolean checkAuthentication(Person person){
			return daoJDBC.loginPerson(person.getEmail(), person.getPassword()) ;
	}

	public Person personByMail(Person person){
		Collection<Person> persons = daoJDBC.findPerson(person.getEmail()) ;
//		if(persons.size()!=1) {
//			throw new IllegalStateException("email de " + person.getName() + "non unique") ;
//		}
		return persons.iterator().next() ;
	}

	public Person personByID(Integer personID){
		return daoJDBC.findPerson(personID);
	}

	public Collection<Person> getAllPersons(){
		return daoJDBC.findAllPerson();
	}


}
