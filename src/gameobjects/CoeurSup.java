package gameobjects;

import libraries.StdDraw;
import libraries.Vector2;
import resources.ImagePaths;

public class CoeurSup extends PassifObject {

    /**
     * Attribut
     */
    private String imagePath;
    private int maxValue;

    public CoeurSup(Vector2 position) {
        super(position);
        this.maxValue = 2;
        this.imagePath = ImagePaths.HP_UP;
    }

    public String getImagePath() {
        return this.imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public int getMaxValue() {
        return this.maxValue;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }

    @Override
    public void drawGameObject() {
        StdDraw.picture(getPosition().getX(), getPosition().getY(), getImagePath(), getSize().getX(), getSize().getY(),
                0);
        StdDraw.setPenColor();
        StdDraw.rectangle(getPosition().getX(), getPosition().getY(), getSize().getX() / 2, getSize().getY() / 2);
    }

}
