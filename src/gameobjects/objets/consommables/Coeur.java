package gameobjects.objets.consommables;

import gameobjects.personnages.Hero;
import libraries.StdDraw;
import libraries.Vector2;
import resources.ImagePaths;

public class Coeur extends ConsommableObject {
    /**
     * Attribut
     */
    private String imagePath;
    private int value;

    public Coeur(int value, Vector2 position) {
        super(position);

        this.value = value;

        if (this.value == 1) {
            imagePath = ImagePaths.HALF_HEART_HUD;
        } else if (this.value == 2) {
            imagePath = ImagePaths.HEART_HUD;
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
        return "Coeur = {" +
                " imagePath='" + getImagePath() + "'" +
                ", value='" + getValue() + "'" +
                "}";
    }

    @Override
    public void updateHeroPerf(Hero e) {
        if (e.getpV() + value <= e.getMaxPV() && super.isEstRamasser() == false) {
            e.addPV(value);
            super.setEstRamasser(true);
        }

    }

}
