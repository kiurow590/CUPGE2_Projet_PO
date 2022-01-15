package gameobjects.projectiles;

import libraries.Vector2;

public class NumberOne_projectiles extends Projectile {

	public NumberOne_projectiles (Vector2 position, String imagePath, Vector2 direction, int damage) {
		super( position,imagePath,direction,damage);
		this.portee=27;
		this.size=new Vector2(0.05,0.05);
	}
}
