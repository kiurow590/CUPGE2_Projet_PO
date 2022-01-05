package gameobjects.obstacles;

import resources.ImagePaths;
import java.util.ArrayList;
import java.util.List;

import libraries.Physics;
import libraries.StdDraw;
import libraries.Vector2;
import resources.ImagePaths;
import resources.RoomInfos;

public class Spikes extends GenericObstacle {
	/*
	 * constructeur de Poop
	 * 
	 * @param position position de l'obstacle
	 * 
	 * @param size taille de l'obstacle
	 * 
	 * @param imagePaths image de l'obstacle
	 * 
	 * @param pointDeVie ici fixé a 4
	 * 
	 * @param degats ici fixé a 0 car ils ne font pas de degats
	 * 
	 * @param collision fixé a true 
	 */

	public Spikes(Vector2 position , Vector2 size) {
		super(position , size ,ImagePaths.SPIKES,4,1,false);
	}

}
