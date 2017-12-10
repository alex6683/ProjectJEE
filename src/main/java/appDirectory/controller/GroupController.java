package appDirectory.controller;

import appDirectory.model.Group;
import appDirectory.web.GroupManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping("/group")
public class GroupController {

	private GroupManager groupManager;

	@Autowired
	public void setGroupManager(GroupManager groupManager) {
		this.groupManager = groupManager;
	}

	@RequestMapping(value = "/addGroup", method = RequestMethod.GET)
	public String signUpPersonGet(Group group) {
	    return "ajoutGroupe";
	}
	
	@RequestMapping(value = "/addGroup", method = RequestMethod.POST)
	public String signUpPerson(@ModelAttribute @Valid Group g, BindingResult result, HttpServletRequest request) {
	    if (result.hasErrors()) {
	        return "ajoutGroupe";
	    }
	    groupManager.saveGroup(g);
	    return "redirect:/actions/lists/groupList";
	}
}
