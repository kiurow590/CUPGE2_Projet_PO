package gameobjects.objets.passifs;

import gameobjects.personnages.Hero;
import libraries.StdDraw;
import libraries.Vector2;
import resources.ImagePaths;

/**
 * Class BloodOfMartyr qui ajoute de la force au hero
 */
public class BloodOfMartyr extends PassifObject {

    /**
     * Attribut
     */
    private String imagePath;
    private int strength;

    /**
     * Constructeur de BloodOfMartyr
     * 
     * @param position position dans la salle
     */
    public BloodOfMartyr(Vector2 position) {
        super(new Vector2(0.5, 0.5), 10);
        this.strength = 1;
        this.imagePath = ImagePaths.BLOOD_OF_THE_MARTYR;

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
            e.setdamage(e.getdamage() + this.strength);
            super.setEstRamasser(true);

        }
    }

}
