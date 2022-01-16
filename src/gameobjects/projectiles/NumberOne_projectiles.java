package gameobjects.projectiles;

import libraries.Vector2;
/**
 * Class projectile NumberOne_projectiles
 */
public class NumberOne_projectiles extends Projectile {
/**
 * constructeur de projectiles number one
 * @param position position initiale du projectile
 * @param imagePath image du projectiles
 * @param direction direction du projectiles
 * @param damage damage du projectiles
 */
	public NumberOne_projectiles (Vector2 position, String imagePath, Vector2 direction, int damage) {
		super( position,imagePath,direction,damage);
		// portee des projectiles
		this.portee=27;
		// taille de l'image
		this.size=new Vector2(0.05,0.05);
	}
}
