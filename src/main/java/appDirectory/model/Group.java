package appDirectory.model;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Required;

import javax.annotation.PostConstruct;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 * L'entité Group. Représente un groupe de Personne.
 * Chaque champs est défini par des contraintes spécifiques.
 *
 * @author Mestrallet Alexis
 * @author Risch Philippe
 *
 * @date 19/10/2017
 * @version 2.0
 */
public class Group implements Serializable {

	private static final long serialVersionUID = 1L;
    /**
     * Identification du groupe
     */
	private Integer identifier = -1 ;
    /**
     * Nom du groupe
     */
    @NotNull
    @NotEmpty
    private String name ;
    /**
     * Liste des personnes appartenant au groupe
     */
    private Collection<Person> persons ;

    @PostConstruct
    public void init() {
        setName(null) ;
        setPersons(new ArrayList<>());
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

    public Collection<Person> getPersons() {
        return persons;
    }

    public void setPersons(Collection<Person> persons) {
        this.persons = persons;
    }
}
