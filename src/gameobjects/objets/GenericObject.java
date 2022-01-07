package gameobjects.objets;

import gameobjects.personnages.Hero;
import libraries.Vector2;
import resources.RoomInfos;

/**
 * Class generique d'objets
 */
public abstract class GenericObject {

    /**
     * Attribut
     */
    Vector2 position;
    protected Vector2 size;
    boolean estRamasser;
    protected int prix;
    protected int value;

    /**
     * Constructeur d'objet
     * 
     * @param position la position de l'objet dans la salle
     */
    public GenericObject(Vector2 position, int prix, int value) {
        this.position = position;
        this.size = RoomInfos.TILE_SIZE.scalarMultiplication(0.4);
        this.estRamasser = false;
        this.prix = prix;

        this.value = value;
    }

    /**
     * Methode qui dessine l'objet dans le jeu
     */
    public abstract void drawGameObject();

    /**
     * Methode qui gere les performance du hero
     * 
     * @param e le hero
     */
    public abstract void updateHeroPerf(Hero e);

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

    public boolean EstRamasser() {
        return this.estRamasser;
    }

    public void setEstRamasser(boolean estRamasser) {
        this.estRamasser = estRamasser;
    }

    public boolean isEstRamasser() {
        return this.estRamasser;
    }

    public boolean getEstRamasser() {
        return this.estRamasser;
    }

    public int getPrix() {
        return this.prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public int getValue() {
        return this.value;
    }

    public void setValue(int value) {
        this.value = value;
    }

}
