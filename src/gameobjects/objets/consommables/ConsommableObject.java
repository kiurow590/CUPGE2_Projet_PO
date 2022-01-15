package gameobjects.objets.consommables;

import gameobjects.objets.GenericObject;
import libraries.Vector2;

/**
 * Class generique objet consommable
 */
public abstract class ConsommableObject extends GenericObject {
    /**
     * Constructeur d'Objet consommable
     * 
     * @param position position dans la room
     * @param price    price de l'objets
     * @param value    valeur de l'objet
     * 
     */
    public ConsommableObject(Vector2 position, int price, int value) {
        super(position, price, value);
    }

}
