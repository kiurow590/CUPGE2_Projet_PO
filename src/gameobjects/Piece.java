package gameobjects;

import libraries.StdDraw;
import libraries.Vector2;

public class Piece extends ConsommableObject {
    /**
     * Attribut
     */

    private String imagePath;
    private int value;

    public Piece(int value, Vector2 position) {

        super(position);
        if (this.value == 1) {
            // set ImagePath
        } else if (this.value == 5) {

        } else if (this.value == 10) {

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
        StdDraw.picture(getPosition().getX(), getPosition().getY(), getImagePath(), getSize().getX(), getSize().getY(),
                0);
        StdDraw.setPenColor();
        StdDraw.rectangle(getPosition().getX(), getPosition().getY(), getSize().getX() / 2, getSize().getY() / 2);
    }

}