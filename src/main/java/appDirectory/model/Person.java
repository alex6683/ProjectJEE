package appDirectory.model;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Required;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Date;


/**
 * L'entité Personne. Représente une Personne et ses metadonnées.
 * Chaque champs est défini par des contraintes spécifiques.
 *
 * @author Mestrallet Alexis
 * @author Risch Philippe
 *
 * @date 19/10/2017
 * @version 2.0
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
    @NotNull
    @NotEmpty(message = "Un nom est attendu")
    private String name ;
    /**
     * Prenom de la Personne
     */
    @NotNull
    @NotEmpty(message = "Un prenom est attendu")
    private String surname ;
    /**
     * Email de la Personne avec valeur par défaut
     */
    @NotNull
    @Pattern(
            regexp="^[_A-Za-z0-9-+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-_]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$",
            message="Email invalide : exemple@exemple.com"
    )
    private String email = "prenom.nom@etu.univ-mrs.fr" ;
    /**
     * Site web de la Personne
     */
    @NotNull
    @Pattern(regexp="^(http://[a-zA-Z0-9.-][a-zA-Z0-9.-]*.[a-zA-Z]{2,3})?$", message="Site web invalide : http://exemple.com")
    private String webSite ;
    /**
     * Date de naissance de la Personne
     */
    @NotNull
    @Past(message = "Date de naissance anterieur a la date d'aujourd'hui attendu")
    private Date dateBirth ;
    /**
     * Mot de passe de la Personne avec valeur par défaut
     */
    @NotNull
    @Size(min = 8, message = "8 caracteres minimum requis")
    @Pattern(
            regexp="((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$",
            message="Mot de passe trop faible, Majuscule + minuscule + nombre + caractere special attendu"
    )
    private String password ;
    /**
     * Description de la Personne
     */
    private String description ;
    /**
     * Numéro de groupe auquel appartient la Personne
     */
    private Integer groupID ;

    public void init() {
        setName(null);
        setSurname(null);
        setEmail("prenom.nom@etu.univ-mrs.fr");
        setWebSite(null);
        setDateBirth(null);
        setPassword("admin");
        setGroupID(-1);
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
        return groupID ;
    }

    public void setGroupID(Integer group) {
        this.groupID = group;
    }

    public void setGroupID(Group group) {
        this.groupID = group.getIdentifier();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
