package gameobjects.projectiles;

import libraries.Vector2;
import resources.ImagePaths;

public class BabyProjectile extends Projectile {

    public BabyProjectile(Vector2 position, Vector2 direction, int damage) {
        super(position, ImagePaths.GAPER, direction, damage);
        portee = 20;
    }

}
