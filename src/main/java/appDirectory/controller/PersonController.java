package appDirectory.controller;

import appDirectory.model.Group;
import appDirectory.model.Person;
import appDirectory.manager.GroupManager;
import appDirectory.manager.PersonManager;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

/**
 * Controller
 * Gestion de l'affichage des informations d'une personne de l'application
 *
 * @author Mestrallet Alexis
 * @author Risch Philippe
 *
 * @date 23/10/2017
 * @version 1.0
 */
@Controller
@RequestMapping("/person")
public class PersonController {

	private PersonManager personManager;

	private GroupManager groupManager ;

	protected final Log logger = LogFactory.getLog(getClass());

	@Autowired
	public void setPersonManager(PersonManager personManager) {
		this.personManager = personManager;
	}

	@Autowired
	public void setGroupManager(GroupManager groupManager) {
		this.groupManager = groupManager;
	}

	/**
	 * Affiche les informations détaillées d'une personne
	 * @param person : Le modele Person
	 * @param request : La requête http
	 * @param personID : L'identification de la personne
	 * @return la vue infoPerson.jsp
	 */
	@RequestMapping(value = "/personData", method = RequestMethod.GET)
	public String displayPersonData(@ModelAttribute Person person, HttpServletRequest request,
								  @RequestParam(value = "id") Integer personID
	){
		Person newPerson = personManager.findPerson(personID);
		Group newGroup = groupManager.findGroup(newPerson.getGroupID());
		request.getSession().setAttribute("personData", newPerson);
		request.getSession().setAttribute("groupData", newGroup);
		return "infoPerson";
	}

	/**
	 * Liste les personne présente dans un groupe donnée
	 * @param person Le modele Person
	 * @param request La requete http
	 * @param groupID l'identifiant du groupe donnée
	 * @return la vue infoGroup.jsp
	 */
	@RequestMapping(value = "/personInGroup", method = RequestMethod.GET)
	public String displayPersonInGroup(@ModelAttribute Person person, HttpServletRequest request,
                                     @RequestParam(value = "id") Integer groupID
	){
		Group group = groupManager.findGroup(groupID) ;
		Collection<Person> personList = personManager.findAllPersonInGroup(groupID);
		request.getSession().setAttribute("currentGroup", group);
		request.getSession().setAttribute("personsInGroup", personList);
		return "infoGroup";
	}

}
