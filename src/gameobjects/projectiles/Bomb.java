package gameobjects.projectiles;

import libraries.Vector2;
import resources.ImagePaths;

/**
 * Class de bomb
 */
public class Bomb extends Projectile {
    /**
     * Constructeur
     * 
     * @param position
     * @param imagePath
     * @param direction
     * @param damage
     */
    public Bomb(Vector2 position, Vector2 direction, int damage) {
        super(position, ImagePaths.BOMB, direction, damage);
        speed = 0.05;
        // TODO Auto-generated constructor stub
    }
}
