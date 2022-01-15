package gameobjects.objets.consommables;

import gameobjects.personnages.hero.Hero;
import libraries.Vector2;
import resources.ImagePaths;

/**
 * Class generatrice de Point de Vie
 */
public class Life extends ConsommableObject {

    public Life(int value, Vector2 position) {

        super(position, 0, value);

        if (this.value == 1) {
            imagePath = ImagePaths.HALF_HEART_HUD;
            this.price = 2;
        } else if (this.value == 2) {
            imagePath = ImagePaths.HEART_HUD;
            this.price = 4;

        }
    }

    @Override
    public void updateHeroPerf(Hero e) {
        if (e.getpointVie() + value <= e.getMaxpointVie() && super.EstRamasser() == false) {
            e.addpointVie(value);
            super.setEstRamasser(true);
        }

    }

}
