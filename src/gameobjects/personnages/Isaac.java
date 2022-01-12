package gameobjects.personnages;

import gameobjects.projectiles.Tear;
import libraries.Vector2;
import resources.HeroInfos;
import resources.ImagePaths;
import resources.RoomInfos;

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
    public void creeLarme(Vector2 position, String imagePath, Vector2 direction) {
        if (this.countDownTir <= 0) {
            this.lstLarme.add(new Tear(position, imagePath, direction, damage));
            this.countDownTir = 20;
        }
    }

}
