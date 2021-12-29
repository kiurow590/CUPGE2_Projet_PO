package gameobjects.objets.consommables;

import gameobjects.objets.GenericObject;
import libraries.Vector2;

public abstract class ConsommableObject extends GenericObject {

    public ConsommableObject(Vector2 position) {
        super(position);
    }

    public abstract void drawGameObject();
}
