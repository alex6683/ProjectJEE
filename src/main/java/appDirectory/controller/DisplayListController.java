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
        HttpSession session = request.getSession();
        session.setAttribute("listPersons", listPerson);
        return "listPerson";
    }

    @RequestMapping(value = "/deletePerson", method = RequestMethod.GET)
    public String deletePerson(@ModelAttribute Person person,
                               @RequestParam(value = "id") Integer personID
    ) {
        person.setIdentifier(personID);
        personManager.deletePerson(person);
        return "redirect:personList";
    }

    @RequestMapping(value = "/groupList", method = RequestMethod.GET)
    public String displayGroups(@ModelAttribute Group group, HttpServletRequest request) {
        Collection<Group> listGroup = groupManager.findAllGroup();
        HttpSession session = request.getSession();
        session.setAttribute("listGroups", listGroup);
        return "listGroup";
    }

    @RequestMapping(value = "/deleteGroup", method = RequestMethod.GET)
    public String deleteGroup(@ModelAttribute Group group,
            @RequestParam(value = "id") Integer groupID
    ) {
        group.setIdentifier(groupID);
        groupManager.deleteGroup(group);
        return "redirect:groupList";
    }
}
