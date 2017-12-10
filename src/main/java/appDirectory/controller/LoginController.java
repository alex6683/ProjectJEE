package appDirectory.controller;


import appDirectory.model.Group;
import appDirectory.model.Person;
import appDirectory.web.GroupManager;
import appDirectory.web.LoginManager;
import appDirectory.web.PersonManager;
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
		logger.info("Running as projet " + this);
		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@ModelAttribute Person person, HttpServletRequest request){
		if(loginManager.checkLogin(person) && loginManager.checkAuthentication(person) ){
			Person pers = loginManager.personByMail(person);
			HttpSession maSession = request.getSession();
			maSession.setAttribute("personLogged", pers);
			maSession.setAttribute("groupPersonLogged", groupManager.findGroup(pers.getGroupID()));
			return "redirect:user";
		}
		return "login";
	}

	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public String displayUserInformation(@ModelAttribute Person p, @ModelAttribute Group g, HttpServletRequest request) {
		if(request.getSession().getAttribute("personLogged") == null)
			return "redirect:login";
		return "user";
	}

	@RequestMapping(value = "/editUser", method = RequestMethod.GET)
	public String editUserInformation(@ModelAttribute Person p, HttpServletRequest request) {
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
	public String updatePerson(@ModelAttribute @Valid Person person, BindingResult result, HttpServletRequest request) {
		HttpSession session = request.getSession();
	    Person personSession = (Person) session.getAttribute("personLogged");
        person.setIdentifier(personSession.getIdentifier());


	    if (result.hasErrors()) {
            return "editUser";
	    }

		Group group = groupManager.findGroupByName(request.getParameter("groups"));

        person.setGroupID(group.getIdentifier());

	    personManager.savePerson(person) ;

		session.setAttribute("groupPersonLogged", group);
	    session.setAttribute("personLogged", person);
	    return "user";
	}


	@RequestMapping(value = "/log_out", method = RequestMethod.GET)
	public String logOutUser(HttpServletRequest request) {
		request.getSession().setAttribute("personLogged", null);
		return "redirect:login";
	}

	@RequestMapping(value = "/deleteAccount", method = RequestMethod.GET)
	public String deletePerson(@ModelAttribute Person person, HttpServletRequest request,
                               @RequestParam(value = "id") Integer id) {
		person.setIdentifier(id);
		personManager.deletePerson(person);
		logOutUser(request);
		return "redirect:login";
	}

	@RequestMapping(value = "/inscription", method = RequestMethod.GET)
	public String signUpPersonGet(@ModelAttribute Person person, HttpServletRequest request) {
		Collection<Group> allGroup = groupManager.findAllGroup();
		request.getSession().setAttribute("listGroup", allGroup);
		return "inscription";
	}

	@RequestMapping(value = "/inscription", method = RequestMethod.POST)
	public String signUpPerson(@ModelAttribute @Valid Person person, BindingResult result,
                               HttpServletRequest request) {

		person.setGroupID(groupManager.findGroupByName(request.getParameter("groupSignUp")).getIdentifier());

		if (result.hasErrors()) {
			return "inscription";
		}
		personManager.savePerson(person);
		return "redirect:login";
	}

}