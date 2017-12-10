package appDirectory.controller;

import appDirectory.model.Group;
import appDirectory.web.GroupManager;
import appDirectory.web.PersonManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collection;

@Controller
@RequestMapping("/tests")
public class TestController {

    private PersonManager personManager ;
    private GroupManager groupManager ;

    @Autowired
    public void setGroupManager(GroupManager groupManager) {
        this.groupManager = groupManager;
    }

    @Autowired
    public void setPersonManager(PersonManager personManager) {
        this.personManager = personManager;
    }

    @RequestMapping(value = "/testAddPersonsAndGroups", method = RequestMethod.GET)
	public String addPersons(HttpServletRequest request){
		ArrayList<Integer> groupsId = new ArrayList<>();
//		personManager.addGroupsTests(300);
		Collection<Group> allGroups = groupManager.findAllGroup();
		for(Group group: allGroups)
			groupsId.add(group.getIdentifier());
//		personManager.addPersonsTests(3000,groupsId);
		return "redirect:test";
	}

	@RequestMapping(value = "/deleteTestPersonsAndGroups", method = RequestMethod.GET)
	public String deleteTestPersons(HttpServletRequest request){
//		personManager.deleteTestPersons(3000);
//		personManager.deleteGroupsTests(300);
		return "redirect:test";
	}

	@RequestMapping(value="/test", method = RequestMethod.GET)
	public String testMapping(HttpServletRequest request , @RequestParam(value = "personSearcher", required = false) String search){
		return "accueilTest";
	}

}
