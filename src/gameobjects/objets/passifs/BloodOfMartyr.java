package gameobjects.objets.passifs;

import gameobjects.personnages.Hero;
import libraries.Vector2;
import resources.ImagePaths;

/**
 * Class BloodOfMartyr qui ajoute de la force au hero
 */
public class BloodOfMartyr extends PassifObject {

    /**
     * Constructeur de BloodOfMartyr
     * 
     * @param position position dans la salle
     */
    public BloodOfMartyr(Vector2 position) {
        super(new Vector2(0.5, 0.5), 10, 1);
        this.imagePath = ImagePaths.BLOOD_OF_THE_MARTYR;

    }

    @Override
    public void updateHeroPerf(Hero e) {

        if (super.EstRamasser() == false) {
            e.setdamage(e.getdamage() + this.value);
            super.setEstRamasser(true);

        }
    }

}
