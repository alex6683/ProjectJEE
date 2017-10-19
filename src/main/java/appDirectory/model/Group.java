package appDirectory.model;

import org.springframework.beans.factory.annotation.Required;

import java.io.Serializable;
import java.util.ArrayList;

public class Group implements Serializable {

    private String identifier ;

    private String name ;

    private ArrayList<Person> persons ;

    public void init() {
        setName(null) ;
        setPersons(new ArrayList<Person>());
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

    public ArrayList<Person> getPersons() {
        return persons;
    }

    public void setPersons(ArrayList<Person> persons) {
        this.persons = persons;
    }
}
