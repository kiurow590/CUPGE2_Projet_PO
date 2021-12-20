package gameobjects;

import libraries.Vector2;
import resources.RoomInfos;

public abstract class Object {

    /**
     * Attribut
     */
    private Vector2 position;
    private Vector2 size;

    private boolean estRamasser;

    public Object(Vector2 position) {
        this.position = position;
        this.size = RoomInfos.TILE_SIZE.scalarMultiplication(0.4);
        this.estRamasser = false;
    }

    /**
     * Methode qui dessine l'objet dans le jeu
     */
    public abstract void drawGameObject();

    /**
     * Getters and Setters
     */

    public Vector2 getPosition() {
        return this.position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public Vector2 getSize() {
        return this.size;
    }

    public void setSize(Vector2 size) {
        this.size = size;
    }

    public boolean isEstRamasser() {
        return this.estRamasser;
    }

    public boolean getEstRamasser() {
        return this.estRamasser;
    }

    public void setEstRamasser(boolean estRamasser) {
        this.estRamasser = estRamasser;
    }
}
