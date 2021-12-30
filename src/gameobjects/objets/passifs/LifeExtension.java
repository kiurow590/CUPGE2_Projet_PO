package gameobjects.objets.passifs;

import gameobjects.personnages.Hero;
import libraries.StdDraw;
import libraries.Vector2;
import resources.ImagePaths;

public class LifeExtension extends PassifObject {

    /**
     * Attribut
     */
    private String imagePath;
    private int maxValue;

    public LifeExtension(Vector2 position) {
        super(position);

        this.maxValue = 2;
        this.imagePath = ImagePaths.HP_UP;

    }

    @Override
    public void drawGameObject() {
        StdDraw.picture(getPosition().getX(), getPosition().getY(), this.imagePath, getSize().getX(),
                getSize().getY(),
                0);
        StdDraw.setPenColor();
        StdDraw.rectangle(getPosition().getX(), getPosition().getY(), getSize().getX() / 2, getSize().getY() / 2);
    }

    @Override
    public void updateHeroPerf(Hero e) {

        if (super.EstRamasser() == false) {
            e.setMaxPV(e.getMaxPV() + maxValue);
            super.setEstRamasser(true);

        }

    }

}
