package appDirectory.web;

import appDirectory.dao.impl.DaoJDBC;
import appDirectory.model.Group;
import appDirectory.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class PersonManager {

	private DaoJDBC daoJDBC;

    @Autowired
    public void setDaoJDBC(DaoJDBC daoJDBC) {
        this.daoJDBC = daoJDBC;
    }

    public PersonManager() { }

	public Collection<Person> findAllPerson(){
		return daoJDBC.findAllPerson();
	}

	public Person findPerson(Integer personID){
		return daoJDBC.findPerson(personID);
	}

	public Person findPerson(Person person){
		return daoJDBC.findPerson(person);
	}

	public Collection<Person> findAllPersonInGroup(Integer groupID){
		return daoJDBC.findAllPersonInGroup(groupID) ;
	}

	public int deletePerson(Person person){
			return daoJDBC.deletePerson(person);
	}

	public boolean savePerson(Person person){
			return daoJDBC.savePerson(person);
	}

//	public Group findGroupNameFromPerson(String email) {
//		Group group;
//		try {
//			group = dao.findGroupNameFromPerson(email) ;
//		} catch (SQLException e) {
//			e.printStackTrace();
//			return null;
//		}
//		return group;
//	}

//	public Group findGroup(Integer groupID) {
//		return daoJDBC.findGroup(groupID) ;
//	}
//
//
//	public Group findGroup(Group group) {
//		return daoJDBC.findGroup(group) ;
//	}

	public ArrayList<Person> searchPerson(String keyword){
		ArrayList<Person> list = new ArrayList<>();
		list.addAll(daoJDBC.findPerson(keyword));
		return list;
	}

//	public void addGroupsTests(int numberOfGroups) {
//		dao.addRandomizedGroup(numberOfGroups);
//
//	}

//	public void deleteGroupsTests(int numberOfGroups) {
//		dao.deleteRandomizedGroup(numberOfGroups);
//
//	}

//	public void addPersonsTests(int numberOfPersons, ArrayList<Integer> idsGroups) {
//		dao.addRandomizedPersons(numberOfPersons,idsGroups);
//
//	}

//	public void deleteTestPersons(int numberOfPersons) {
//		dao.deleteRandomizedPersons(numberOfPersons);
//	}
}
