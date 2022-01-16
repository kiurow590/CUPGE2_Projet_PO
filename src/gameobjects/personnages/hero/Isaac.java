package gameobjects.personnages.hero;

import gameobjects.projectiles.NumberOne_projectiles;
import gameobjects.projectiles.Tear;
import libraries.Vector2;
import resources.HeroInfos;
import resources.ImagePaths;

/**
 * Class generant le hero isaac
 */
public class Isaac extends Hero {

    /**
     * Constructeur de isaac
     * 
     * @param position
     */
    public Isaac(Vector2 position) {
        super(position, ImagePaths.ISAAC, HeroInfos.ISAAC_SPEED, HeroInfos.ISAAC_LIFE, HeroInfos.ISAAC_LIFE);
    }

    @Override
    public void creeProjectile(Vector2 position, Vector2 direction) {
        if (this.countDownTir <= 0) {
            // si le bonus numberOne est actif l'image/ la porte est change
            if (this.numberOne) {
                this.lstLarme.add(new NumberOne_projectiles(position, ImagePaths.NUMBER_ONE, direction, damage));
                this.countDownTir = 12;
            } else {
                this.lstLarme.add(new Tear(position, direction, damage));

                this.countDownTir = 20;
            }
        }
    }

}
