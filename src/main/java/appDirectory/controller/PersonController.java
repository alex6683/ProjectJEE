package appDirectory.controller;

import appDirectory.model.Group;
import appDirectory.model.Person;
import appDirectory.web.GroupManager;
import appDirectory.web.PersonManager;
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

	@RequestMapping(value = "/personData", method = RequestMethod.GET)
	public String displayPersonData(@ModelAttribute Person person, HttpServletRequest request,
								  @RequestParam(value = "id") Integer personID){
		Person pers = personManager.findPerson(personID);
		Group group = groupManager.findGroup(pers.getGroupID());
		request.getSession().setAttribute("infoPerson", pers);
		request.getSession().setAttribute("groupPersonListed", group);
		return "infoPerson";
	}

	@RequestMapping(value = "/personInGroup", method = RequestMethod.GET)
	public String displayPersonInGroup(@ModelAttribute Person person, HttpServletRequest request,
                                     @RequestParam(value = "id") Integer id){
		Group group = groupManager.findGroup(id) ;
		Collection<Person> listPers = personManager.findAllPersonInGroup(id);
		request.getSession().setAttribute("groupCurrentlyViewed", group);
		request.getSession().setAttribute("persInGroup", listPers);
		return "infoGroup";
	}

}
