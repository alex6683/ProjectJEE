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
import javax.servlet.http.HttpSession;
import java.util.Collection;

@Controller
@RequestMapping("/lists")
public class DisplayListController {

	private PersonManager personManager;

	private GroupManager groupManager;

	protected final Log logger = LogFactory.getLog(getClass());

	@Autowired
    public void setGroupManager(GroupManager groupManager) {
        this.groupManager = groupManager;
    }

    @Autowired
    public void setPersonManager(PersonManager personManager) {
        this.personManager = personManager;
    }

    @RequestMapping(value = "/personList", method = RequestMethod.GET)
    public String displayPersons(@ModelAttribute Person person, HttpServletRequest request) {
		Collection<Person> listPerson = personManager.findAllPerson();
    	HttpSession maSession = request.getSession();
    	maSession.setAttribute("personsList", listPerson);
    	return "personList";
    }
    
    @RequestMapping(value = "/deletePerson", method = RequestMethod.GET)
    public String deletePerson(@ModelAttribute Person person,
                               @RequestParam(value = "id") Integer id) {
    	person.setIdentifier(id);
		personManager.deletePerson(person);
    	return "redirect:personList";
    }
    
    @RequestMapping(value = "/groupList", method = RequestMethod.GET)
    public String displayGroups(@ModelAttribute Group g, HttpServletRequest request) {
    	Collection<Group> listGroup = groupManager.findAllGroup();
    	HttpSession maSession = request.getSession();
    	maSession.setAttribute("groupsList", listGroup);
    	return "groupList";
    }
    
    @RequestMapping(value = "/deleteGroup", method = RequestMethod.GET)
    public String deleteGroup(@ModelAttribute Group group, HttpServletRequest request,
                              @RequestParam(value = "id") Integer id) {
    	System.out.println("DeleteGroup in DisplayListController");
    	group.setIdentifier(id);
		groupManager.deleteGroup(group);
    	return "redirect:groupList";
    }
    
    

//    @RequestMapping(value = "/editPerson", method = RequestMethod.GET)
//    public String editPerson(@ModelAttribute Group g, HttpServletRequest request) {
//System.out.println("GET");
//    	return "editPerson";
//    }
//    
//    @RequestMapping(value = "/editPerson", method = RequestMethod.POST)
//    public String editPerson(@ModelAttribute Group g, BindingResult result,  HttpServletRequest request) {
////    	Collection<Person> listPerson = personManager.findAllPerson();
////    	System.out.println("COUCOUECOIJOKEJKLCJEZKL");
////    	HttpSession maSession = request.getSession();
////		maSession.setAttribute("listPersons", listPerson);
//    	System.out.println("POST");
//		return "personList";
//    }

}
