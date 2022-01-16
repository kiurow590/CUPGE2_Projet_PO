package gameobjects.obstacles;

import libraries.Vector2;
import resources.ImagePaths;

public class Poop extends GenericObstacle {
	

	/**
	 * constructeur de Poop
	 * 
	 * @param position position de l'obstacle
	 * 
	 * @param size taille de l'obstacle
	 * lors de l'apelle du super
	 * imagePaths image de l'obstacle ( ici la Poop)
	 * 
	 * pointDeVie ici fixe a 4
	 * 
	 * degats ici fixe a 0 car ils ne font pas de degats
	 * 
	 *  
	 */
	public Poop (Vector2 position , Vector2 size) {
		super (position , size ,ImagePaths.POOP,4,0);
	}
		
}
