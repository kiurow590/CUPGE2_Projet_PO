package gameobjects.objets;

import gameobjects.personnages.hero.Hero;
import libraries.StdDraw;
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
    protected int price;
    protected int value;

    protected String imagePath;

    /**
     * Constructeur d'objet
     * 
     * @param position la position de l'objet dans la salle
     * @param price sa valeur en terme de piece
     * @param value valeur des caractestiques additionel de l'objet
     */
    public GenericObject(Vector2 position, int price, int value) {
        this.position = position;
        this.size = RoomInfos.TILE_SIZE.scalarMultiplication(0.4);
        this.estRamasser = false;
        this.price = price;

        this.value = value;
    }

    /**
     * dessine l'objet dans le jeu
     */
    public void drawGameObject() {
        StdDraw.picture(getPosition().getX(), getPosition().getY(), this.imagePath, getSize().getX(),
                getSize().getY(),
                0);
    }

    /**
     * gere les performance du hero
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

    public boolean EstRamasser() {
        return this.estRamasser;
    }

    public void setEstRamasser(boolean estRamasser) {
        this.estRamasser = estRamasser;
    }

    public boolean isEstRamasser() {
        return this.estRamasser;
    }

    public int getprice() {
        return this.price;
    }

    public int getValue() {
        return this.value;
    }

}
