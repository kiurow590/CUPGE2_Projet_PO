package gameobjects;

import libraries.Vector2;

public abstract class PassifObject extends Objets {

    public PassifObject(Vector2 position) {
        super(position);
    }

    public abstract void drawGameObject();
}
