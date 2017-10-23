package appDirectory.model;

import org.springframework.beans.factory.annotation.Required;

import java.io.Serializable;
import java.util.Date;

/**
 * L'entité Personne. Représente une Personne et ses metadonnées.
 *
 * @author Mestrallet Alexis
 * @author Risch Philippe
 *
 * @date 19/10/2017
 * @version 1.0
 */
public class Person implements Serializable {

	private static final long serialVersionUID = 1L;

    /**
     * Identification de la Personne
     */
	private Integer identifier = -1 ;
    /**
     * Nom de la Personne
     */
    private String name ;
    /**
     * Prénom de la Personne
     */
    private String surname ;
    /**
     * Email de la Personne avec valeur par défaut
     */
    private String email = "prenom.nom@etu.univ-amu.fr" ;
    /**
     * Site web de la Personne
     */
    private String webSite ;
    /**
     * Date de naissance de la Personne
     */
    private Date dateBirth ;
    /**
     * Mot de passe de la Personne avec valeur par défaut
     */
    private String password = "admin" ;
    /**
     * Description de la Personne
     */
    private String description ;
    /**
     * Numéro de groupe auquel appartient la Personne
     */
    private Group group ;

    public void init() {
        setName(null);
        setSurname(null);
        setEmail("prenom.nom@etu.univ-amu.fr");
        setWebSite(null);
        setDateBirth(null);
        setPassword("admin");
        setGroup(null);
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

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
