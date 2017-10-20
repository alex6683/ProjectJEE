package appDirectory.model;

import org.springframework.beans.factory.annotation.Required;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.Date;

public class Person implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String identifier ;

    private String name ;

    private String surname ;

    private String email = "prenom.nom@etu.univ-amu.fr" ;

    private String webSite ;

    private String dateBirth ;

    private String password = "admin" ;

    private String groupID ;

    public void init() {
        setName(null);
        setSurname(null);
        setEmail("prenom.nom@etu.univ-amu.fr");
        setWebSite(null);
        setDateBirth(null);
        setPassword("admin");
        setGroup(null);
    }

    public String getIdentifier() {
        return identifier;
    }

    @Required
    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebSite() {
        return webSite;
    }

    public void setWebSite(String webSite) {
        this.webSite = webSite;
    }

    public String getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(String dateBirth) {
        this.dateBirth = dateBirth;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGroup() {
        return groupID;
    }

    public void setGroup(String group) {
        this.groupID = group;
    }
}
