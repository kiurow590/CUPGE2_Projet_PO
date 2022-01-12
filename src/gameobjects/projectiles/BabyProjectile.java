package gameobjects.projectiles;

import libraries.Vector2;
import resources.ImagePaths;

/**
 * Class babyProjectile
 */
public class BabyProjectile extends Projectile {
    /**
     * Constructeur
     * 
     * @param position
     * @param direction
     * @param damage
     */
    public BabyProjectile(Vector2 position, Vector2 direction, int damage) {
        super(position, ImagePaths.GAPER, direction, damage);
        portee = 20;
    }

}
