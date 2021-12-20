package gameobjects;

import libraries.StdDraw;
import libraries.Vector2;
import resources.ImagePaths;

public class Piece extends ConsommableObject {
    /**
     * Attribut
     */

    private String imagePath;
    private int value;

    /**
     * Constructeur de piece
     * 
     * @param value
     * @param position
     */
    public Piece(int value, Vector2 position) {

        super(position);
        this.value = value;

        if (this.value == 1) {
            this.imagePath = ImagePaths.COIN;
        } else if (this.value == 5) {
            this.imagePath = ImagePaths.NICKEL;

        } else if (this.value == 10) {
            this.imagePath = ImagePaths.DIME;

        }

    }

    public String getImagePath() {
        return this.imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public int getValue() {
        return this.value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public void drawGameObject() {
        StdDraw.picture(getPosition().getX(), getPosition().getY(), getImagePath(), getSize().getX(),
                getSize().getY(),
                0);
        StdDraw.setPenColor();
        StdDraw.rectangle(getPosition().getX(), getPosition().getY(), getSize().getX() / 2, getSize().getY() / 2);
    }

    @Override
    public String toString() {
        return "Piece = {" +
                " imagePath='" + getImagePath() + "'" +
                ", value='" + getValue() + "'" +
                "}";
    }

}