package appDirectory.controller;

import appDirectory.manager.GroupManager;
import appDirectory.manager.PersonManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Controller
 * Gestion de la fonction de recherche de l'application
 *
 * @author Mestrallet Alexis
 * @author Risch Philippe
 *
 * @date 23/10/2017
 * @version 1.0
 */
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

    /**
     * Attribution de la liste de groupes et/ou personnes selon le critère de recherche
     * @param request : La requête http
     * @param search : Le critère de recherche
     * @return la vue searchResult.jsp
     */
    @RequestMapping(value="/search", method = RequestMethod.GET)
	public String searchElements(HttpServletRequest request , @RequestParam(value = "personSearcher", required = false) String search){
		HttpSession session = request.getSession();
		session.setAttribute("resultSearchPerson", personManager.searchPerson(search));
		session.setAttribute("resultSearchGroup", groupManager.searchGroup(search));
        session.setAttribute("personSearcher", search);
		return "searchResult";
	}

}
