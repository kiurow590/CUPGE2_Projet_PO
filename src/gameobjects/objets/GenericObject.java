package gameobjects.objets;

import gameobjects.personnages.Hero;
import libraries.Vector2;
import resources.RoomInfos;

public abstract class GenericObject {

    /**
     * Attribut
     */
    Vector2 position;
    protected Vector2 size;
    boolean estRamasser;

    public GenericObject(Vector2 position) {
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

    /**
     * Methode qui gere les performance du hero
     * 
     * @param e le hero
     */
    public abstract void updateHeroPerf(Hero e);
}
