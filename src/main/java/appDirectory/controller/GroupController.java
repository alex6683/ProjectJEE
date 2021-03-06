package appDirectory.controller;

import appDirectory.model.Group;
import appDirectory.manager.GroupManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;


/**
 * Controller
 * Gestion de l'ajout de groupe de l'application
 *
 * @author Mestrallet Alexis
 * @author Risch Philippe
 *
 * @date 23/10/2017
 * @version 1.0
 */
@Controller
@RequestMapping("/group")
public class GroupController {

	private GroupManager groupManager;

	@Autowired
	public void setGroupManager(GroupManager groupManager) {
		this.groupManager = groupManager;
	}

	@RequestMapping(value = "/addGroup", method = RequestMethod.GET)
	public String addGroup(Group group) {
	    return "addGroup";
	}
	
	@RequestMapping(value = "/addGroup", method = RequestMethod.POST)
	public String addGroup(@ModelAttribute @Valid Group group, BindingResult result, HttpServletRequest request) {
	    if (result.hasErrors()) {
	        return "addGroup";
	    }
	    groupManager.saveGroup(group);
	    return "redirect:/actions/lists/groupList";
	}
}
