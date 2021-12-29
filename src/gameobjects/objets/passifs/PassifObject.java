package gameobjects.objets.passifs;

import gameobjects.objets.GenericObject;
import libraries.Vector2;

public abstract class PassifObject extends GenericObject {

    public PassifObject(Vector2 position) {
        super(position);
    }

    public abstract void drawGameObject();
}
