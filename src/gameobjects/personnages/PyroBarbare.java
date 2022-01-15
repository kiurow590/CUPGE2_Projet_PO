package gameobjects.personnages;

import gameobjects.projectiles.Bomb;
import gameobjects.projectiles.NumberOne_projectiles;
import gameobjects.projectiles.Tear;
import libraries.Vector2;
import resources.HeroInfos;
import resources.ImagePaths;

/**
 * Class de hero pyrobarbare
 */
public class PyroBarbare extends Hero {
    /**
     * Constructeur pyrobarbare
     * 
     * @param position
     */
    public PyroBarbare(Vector2 position) {
        super(position, ImagePaths.PYROBARBARE, HeroInfos.ISAAC_SPEED * 3, 2, 2);
        damage = 1;
    }

    @Override
    public void creeLarme(Vector2 position, String imagePath, Vector2 direction) {
        if (this.countDownTir <= 0) {
        	if (this.numberOne) {
        		this.lstLarme.add(new NumberOne_projectiles(position, ImagePaths.NUMBER_ONE, direction, damage));
        		 this.countDownTir = 10;
        	} else {
            this.lstLarme.add(new Bomb(position, ImagePaths.BOMB, direction, damage));
            this.countDownTir = 15;
        	}
        }
    }

}
