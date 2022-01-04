package gameobjects.obstacles;

import libraries.Vector2;

public class Poop extends GenericObstacle {
	

	/*
	 * constructeur de rocher
	 * 
	 * @param position position de l'obstacle
	 * 
	 * @param size taille de l'obstacle
	 * 
	 * @param imagePaths image de l'obstacle
	 * 
	 * @param pointDeVie
	 */
	public Poop (Vector2 position , Vector2 size) {
		super (position , size ,resources.ImagePaths.POOP,4);
	}
		
}
