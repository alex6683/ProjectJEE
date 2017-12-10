package appDirectory.web;

import appDirectory.dao.impl.DaoJDBC;
import appDirectory.model.Group;
import appDirectory.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

@Service
public class GroupManager {

	private DaoJDBC daoJDBC;

	@Autowired
	public void setDaoJDBC(DaoJDBC daoJDBC) {
		this.daoJDBC = daoJDBC;
	}

	public Collection<Group> findAllGroup(){
		return daoJDBC.findAllGroups() ;
	}

	public Collection<Group> findAllGroupWithGroupInFirst(Group group){
		try {
			return daoJDBC.findAllGroupWithGroupInFirst(group) ;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null ;
	}

	public Group findGroup(Integer groupID) {
		return daoJDBC.findGroup(groupID) ;
	}


	public Group findGroup(Group group) {
		return daoJDBC.findGroup(group) ;
	}


	public Group findGroupByName(String name){
		return daoJDBC.findOneGroup(name) ;
	}


	public int deleteGroup(Group group){
		return daoJDBC.deleteGroup(group) ;
	}

	public boolean saveGroup(Group group) {
		return daoJDBC.saveGroup(group) ;
	}

    public ArrayList<Group> searchGroup(String keyword){
        ArrayList<Group> list = new ArrayList<>();
        list.addAll(daoJDBC.findGroup(keyword));
        return list;
    }
}













