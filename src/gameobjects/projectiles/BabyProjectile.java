package gameobjects.projectiles;

import libraries.Vector2;
import resources.ImagePaths;

/**
 * Class babyProjectile
 */
public class BabyProjectile extends Projectile {
    /**
     * Constructeur de BabyProjectile
     * 
     * @param position position initiale de l'objet 
     * @param direction direction du projectile
     * @param damage damage du projectiles
     */
    public BabyProjectile(Vector2 position, Vector2 direction, int damage) {
        super(position, ImagePaths.GAPER, direction, damage);
        portee = 20;
    }

}
