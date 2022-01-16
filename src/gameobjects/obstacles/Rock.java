package gameobjects.obstacles;

import libraries.Vector2;
import resources.ImagePaths;

public class Rock extends GenericObstacle {
	/**
	 * constructeur de Rock
	 * 
	 * @param position position de l'obstacle
	 * 
	 * @param size taille de l'obstacle
	 * lors de l'appelle du super
	 * imagePaths image de l'obstacle (ici le rocher )
	 * 
	 * pointDeVie ici fixe a 1 (incassable aucune fonction de réduction de PV)
	 * 
	 * degats ici fixe a 0 car ils ne font pas de degats
	 * 
	 * 
	 */

	public Rock(Vector2 position, Vector2 size) {
		super(position, size, ImagePaths.ROCK, 1, 0);
	}

}
