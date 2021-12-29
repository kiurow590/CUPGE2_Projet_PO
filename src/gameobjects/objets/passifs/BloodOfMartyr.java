package gameobjects.objets.passifs;

import gameobjects.personnages.Hero;
import libraries.StdDraw;
import libraries.Vector2;
import resources.ImagePaths;

public class BloodOfMartyr extends PassifObject {

    /**
     * Attribut
     */
    private String imagePath;
    private int strength;

    public BloodOfMartyr(Vector2 position) {
        super(new Vector2(0.5, 0.5));

        this.strength = 1;
        this.imagePath = ImagePaths.BLOOD_OF_THE_MARTYR;

    }

    public String getImagePath() {
        return this.imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public int getStrength() {
        return this.strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
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
        return "BloodOfMartyr = {" +
                " imagePath='" + getImagePath() + "'" +
                "}";
    }

    @Override
    public void updateHeroPerf(Hero e) {

        if (super.isEstRamasser() == false) {
            e.setDegats(e.getDegats() + 1);
            super.setEstRamasser(true);

        }
    }

}
