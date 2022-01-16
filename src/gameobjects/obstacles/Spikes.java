package gameobjects.obstacles;

import libraries.Vector2;
import resources.ImagePaths;

public class Spikes extends GenericObstacle {
	/**
	 * constructeur de Spikes
	 * 
	 * @param position   position de l'obstacle
	 * 
	 * @param size       taille de l'obstacle
	 * lors de l'apelle du super
	 * imagePaths image de l'obstacle
	 * 
	 * pointDeVie ici fixe a 1 (indestructible par le joueur , aucune
	 *                   fonction de réduction de point de vie)
	 * 
	 * degats     ici fixe a 1 car ils font des degats au joueur
	 * 
	 */

	public Spikes(Vector2 position, Vector2 size) {
		super(position, size, ImagePaths.SPIKES, 1, 1);
	}

}
