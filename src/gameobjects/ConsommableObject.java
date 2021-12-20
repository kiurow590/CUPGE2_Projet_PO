package gameobjects;

import libraries.Vector2;

public abstract class ConsommableObject extends Object {

    public ConsommableObject(Vector2 position) {
        super(position);
    }

    public abstract void drawGameObject();
}
