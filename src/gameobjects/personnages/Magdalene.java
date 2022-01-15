package gameobjects.personnages;

import gameobjects.projectiles.BabyProjectile;
import gameobjects.projectiles.NumberOne_projectiles;
import gameobjects.projectiles.Tear;
import libraries.Vector2;
import resources.ImagePaths;

/**
 * Class de hero magdelene
 */
public class Magdalene extends Hero {
    /**
     * Constructeur
     * 
     * @param position
     */
    public Magdalene(Vector2 position) {
        super(position, ImagePaths.MAGDALENE, 0.0085, 8, 8);
        damage = 4;
    }

    public void creeLarme(Vector2 position, String imagePath, Vector2 direction) {
        if (this.countDownTir <= 0) {
        	if (this.numberOne) {
        		this.lstLarme.add(new NumberOne_projectiles(position, ImagePaths.NUMBER_ONE, direction, damage));
        		 this.countDownTir = 30;
        	} else {
            this.lstLarme.add(new BabyProjectile(position, direction, damage));
            this.countDownTir = 50;
        	}
        }

    }

}
