package appDirectory.web;

import appDirectory.model.Person;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Service
public class PersonValidator implements Validator {


    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
    	Person person = (Person) target;

    	if(person.getIdentifier()<0)
    		errors.rejectValue("idPers", "person.idPers","Mauvais idPers");
    	
    	if(person.getGroupID()<0)
    		errors.rejectValue("idGroup", "person.idGroup","Mauvais idGroup");
    	
    	
//    	 ValidationUtils.rejectIfEmptyOrWhitespace(errors, "idPers",
//                 "person.idPers", "Vous ne pouvez pas avoir un id vide.");
//    	 
//    	 ValidationUtils.rejectIfEmptyOrWhitespace(errors, "idGroup",
//                 "person.idGroup", "Vous ne pouvez pas avoir un id de groupe vide.");
    	
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName",
                "person.firstName", "Vous ne pouvez pas avoir un prénom vide.");
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName",
        		"person.lastName", "Vous ne pouvez pas avoir un nom vide.");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "mail",
                "person.mail", "Votre mail ne peut pas être vide.");
        
//        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "type",
//                "product.web", "Field type is required.");
        
//        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "type",
//                "product.naissance", "Field type is required.");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password",
                "person.password", "Vous ne pouvez pas avoir un mot de passe vide.");

//        if (!(person .getPrice() > 0.0)) {
//            errors.rejectValue("price", "product.price.too.low",
//                    "Price too low");
//        }
    }

}
