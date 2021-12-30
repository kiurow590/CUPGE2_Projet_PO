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
     */
    public ConsommableObject(Vector2 position) {
        super(position);
    }

}
