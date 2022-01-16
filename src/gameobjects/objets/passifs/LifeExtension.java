package gameobjects.objets.passifs;

import gameobjects.personnages.hero.Hero;
import libraries.Vector2;
import resources.ImagePaths;

/**
 * Class generatrice d'extension de vie
 */
public class LifeExtension extends PassifObject {

    /**
     * constructeur d'extension de vie
     * 
     * @param position
     */
    public LifeExtension(Vector2 position) {
        super(position, 20, 2);

        this.imagePath = ImagePaths.HP_UP;

    }

    @Override
    public void updateHeroPerf(Hero e) {

        if (super.EstRamasser() == false) {
            e.setMaxpointVie(e.getMaxpointVie() + value);
            e.setPointVie(e.getPointVie() + 2);

            super.setEstRamasser(true);

        }

    }

}
