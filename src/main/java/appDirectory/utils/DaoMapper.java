package appDirectory.utils;

import appDirectory.exception.DAOMapperException;
import java.lang.reflect.Field;
import java.util.HashMap;

/**
 * Outil permettant de crée une instance d'objet quelconque à partir d'une map,
 * ou créer une map à partir d'un objet quelconque.
 *
 * @author Mestrallet Alexis
 *
 * @date 19/10/2017
 * @version 1.0
 */
public class DaoMapper {

    /**
     * Retourne une map qui liste tous les attributs d'un objet passé en paramètre, et leur valeur respective.
     *
     * @param instance L'objet dont qui sera mapper
     * @return La map correspondante
     * @throws DAOMapperException Si une exception de type IllegalAccess est levée
     */
    public static HashMap<String, String> instanceToMap(Object instance) throws DAOMapperException {
        HashMap<String, String> mapPerson = new HashMap<>() ;
        try {
            for(Field field : instance.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                mapPerson.put(field.getName(), String.valueOf(field.get(instance))) ;
                field.setAccessible(false);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw  new DAOMapperException(e);
        }
        return mapPerson ;
    }

    /**
     * Retourne l'instance de la classe passé en paramètre, et lui affecte les valeurs de la map.
     * Si la map ne contient pas tout les attributs de l'objet en question, ceux ci prendront leur valeur par défaut
     *
     * @param theClass La classe à instancier
     * @param map Contient les valeurs des attributs à affecter à notre objet
     * @return L'instance en question
     * @throws DAOMapperException Si une exception de type Instantiation, IllegalAccess ou NoSuchField est levée
     */
    public static Object mapToInstance(Class<?> theClass, HashMap<String, String> map) throws DAOMapperException {
        //Création de l'instance de theClass
        Object instance ;
        try {
            instance = theClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            throw new DAOMapperException(e);
        }
        //Affectation des attributs
        for(String key : map.keySet()) {
            String value = map.get(key);
            try {
                Field field = instance.getClass().getDeclaredField(key);
                field.setAccessible(true);
                field.set(instance, value);
                field.setAccessible(false);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                throw new DAOMapperException(e) ;
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
                throw new DAOMapperException("Le champs " + key + " n'existe pas pour la classe " + theClass.getName()) ;
            }
        }
        return instance ;
    }
}
