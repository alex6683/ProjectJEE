package appDirectory.controller;

import appDirectory.model.Group;
import appDirectory.model.Person;
import appDirectory.web.GroupManager;
import appDirectory.web.PersonManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/searchBar")
public class SearchController {

    private PersonManager personManager;
    private GroupManager groupManager;

    @Autowired
    public void setPersonManager(PersonManager personManager) {
        this.personManager = personManager;
    }

    @Autowired
    public void setGroupManager(GroupManager groupManager) {
        this.groupManager = groupManager;
    }

    @RequestMapping(value="/search", method = RequestMethod.GET)
	public String searchPerson(HttpServletRequest request , @RequestParam(value = "personSearcher", required = false) String search){
		HttpSession session = request.getSession();
		session.setAttribute("resultSearchPerson", personManager.searchPerson(search));
		session.setAttribute("resultSearchGroup", groupManager.searchGroup(search));

		return "resultSearch";
	}

}
