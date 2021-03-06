package appDirectory.controller;


import appDirectory.model.Group;
import appDirectory.model.Person;
import appDirectory.manager.GroupManager;
import appDirectory.manager.LoginManager;
import appDirectory.manager.PersonManager;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Collection;

/**
 * Controller
 * Gestion de l'utilisateur et de son authentification dans l'application
 *
 * @author Mestrallet Alexis
 * @author Risch Philippe
 *
 * @date 23/10/2017
 * @version 1.0
 */
@Controller
@RequestMapping("/connexion")
public class LoginController {

	private LoginManager loginManager;

	private PersonManager personManager;

	private GroupManager groupManager;

	@Autowired
	public void setGroupManager(GroupManager groupManager) {
		this.groupManager = groupManager;
	}

	@Autowired
	public void setPersonManager(PersonManager personManager) {
		this.personManager = personManager;
	}

	@Autowired
	public void setLoginManager(LoginManager loginManager) {
		this.loginManager = loginManager;
	}

	protected final Log logger = LogFactory.getLog(getClass());

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(@ModelAttribute Person person, BindingResult result) {
		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@ModelAttribute Person person, HttpServletRequest request){
		if(loginManager.checkLogin(person) && loginManager.checkAuthentication(person) ){
			Person pers = loginManager.personByMail(person);
			HttpSession session = request.getSession();
			session.setAttribute("personLogged", pers);
			session.setAttribute("groupPersonLogged", groupManager.findGroup(pers.getGroupID()));
			return "redirect:user";
		}
		return "login";
	}

	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public String displayUserData(@ModelAttribute Person person, @ModelAttribute Group group, HttpServletRequest request) {
		if(request.getSession().getAttribute("personLogged") == null)
			return "redirect:login";
		return "user";
	}

	@RequestMapping(value = "/editUser", method = RequestMethod.GET)
	public String editUserData(@ModelAttribute Person p, HttpServletRequest request) {
		if(request.getSession().getAttribute("personLogged") == null)
			return "redirect:login";
		HttpSession session = request.getSession();
	    Person personSession = (Person) session.getAttribute("personLogged");
		Group group = groupManager.findGroup(personSession.getGroupID());
		Collection<Group> allGroup = groupManager.findAllGroupWithGroupInFirst(group);
		request.getSession().setAttribute("listGroup", allGroup);
		return "editUser";
	}


	@RequestMapping(value = "/editUser", method = RequestMethod.POST)
	public String editUserData(@ModelAttribute @Valid Person person, BindingResult result, HttpServletRequest request) {
		HttpSession session = request.getSession();
        Person personSession = (Person) session.getAttribute("personLogged");
        person.setIdentifier(personSession.getIdentifier());
	    if (result.hasErrors()) {
            return "editUser";
	    }
		Group group = groupManager.findGroupByName(request.getParameter("group"));
        person.setGroupID(group.getIdentifier());
	    personManager.savePerson(person) ;
		session.setAttribute("groupPersonLogged", group);
	    session.setAttribute("personLogged", person);
	    return "user";
	}


	@RequestMapping(value = "/logOut", method = RequestMethod.GET)
	public String logOutUser(HttpServletRequest request) {
		request.getSession().setAttribute("personLogged", null);
		return "redirect:login";
	}

	@RequestMapping(value = "/deleteUser", method = RequestMethod.GET)
	public String deleteUser(@ModelAttribute Person person, HttpServletRequest request,
                               @RequestParam(value = "id") Integer id) {
		person.setIdentifier(id);
		personManager.deletePerson(person);
		logOutUser(request);
		return "redirect:login";
	}

	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public String signUp(@ModelAttribute Person person, HttpServletRequest request) {
		Collection<Group> allGroup = groupManager.findAllGroup();
		request.getSession().setAttribute("listGroup", allGroup);
		return "registration";
	}

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public String signUp(@ModelAttribute @Valid Person person, BindingResult result,
                               HttpServletRequest request) {
		person.setGroupID(groupManager.findGroupByName(request.getParameter("groupSignUp")).getIdentifier());
		if (result.hasErrors()) {
			return "registration";
		}
		personManager.savePerson(person);
		return "redirect:login";
	}

}