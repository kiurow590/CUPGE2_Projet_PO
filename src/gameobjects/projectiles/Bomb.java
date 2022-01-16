package gameobjects.projectiles;

import libraries.Vector2;
import resources.ImagePaths;

/**
 * Class de bomb
 */
public class Bomb extends Projectile {
    /**
     * Constructeur de Bomb
     * 
     * @param position position initiale de l'objet
     * @param direction direction du projectile
     * @param damage damage du projectile
     */
    public Bomb(Vector2 position, Vector2 direction, int damage) {
        super(position, ImagePaths.BOMB, direction, damage);
        speed = 0.05;
    }
}
