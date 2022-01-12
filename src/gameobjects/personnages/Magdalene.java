package gameobjects.personnages;

import gameobjects.projectiles.BabyProjectile;
import libraries.Vector2;
import resources.ImagePaths;

public class Magdalene extends Hero {

    public Magdalene(Vector2 position) {
        super(position, ImagePaths.MAGDALENE, 0.0085, 8, 8);
        damage = 4;
    }

    public void creeLarme(Vector2 position, String imagePath, Vector2 direction) {
        if (this.countDownTir <= 0) {
            this.lstLarme.add(new BabyProjectile(position, direction, damage));
            this.countDownTir = 50;
        }

    }

}
