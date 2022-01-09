package gameobjects.objets.passifs;

import gameobjects.objets.GenericObject;
import libraries.Vector2;

/**
 * Class Generique d'objet passif.
 */
public abstract class PassifObject extends GenericObject {
    /**
     * Constructeur d'objet passif
     * 
     * @param position position de l'objets
     * @param prix     prix de l'objets
     * @param value    valeur de l'objets
     * 
     * 
     */
    public PassifObject(Vector2 position, int prix, int value) {
        super(position, prix, value);
    }

}
