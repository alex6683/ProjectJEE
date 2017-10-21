package appDirectory.model;

import org.springframework.beans.factory.annotation.Required;

import java.io.Serializable;
import java.util.Date;

public class Person implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer identifier ;

    private String name ;

    private String surname ;

    private String email = "prenom.nom@etu.univ-amu.fr" ;

    private String webSite ;

    private Date dateBirth ;

    private String password = "admin" ;

    private String description ;

    private Integer groupID ;

    public void init() {
        setName(null);
        setSurname(null);
        setEmail("prenom.nom@etu.univ-amu.fr");
        setWebSite(null);
        setDateBirth(null);
        setPassword("admin");
        setGroupID(null);
        setDescription("");
    }

    public Integer getIdentifier() {
        return identifier;
    }

    @Required
    public void setIdentifier(Integer identifier) {
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

    public Date getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(Date dateBirth) {
        this.dateBirth = dateBirth;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getGroupID() {
        return groupID;
    }

    public void setGroupID(Integer groupID) {
        this.groupID = groupID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
