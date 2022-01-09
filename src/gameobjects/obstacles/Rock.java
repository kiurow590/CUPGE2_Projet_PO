package gameobjects.obstacles;

import java.util.ArrayList;
import java.util.List;

import libraries.Physics;
import libraries.StdDraw;
import libraries.Vector2;
import resources.ImagePaths;
import resources.RoomInfos;
public class Rock extends GenericObstacle {
	/*
	 * constructeur de Rock
	 * 
	 * @param position position de l'obstacle
	 * 
	 * @param size taille de l'obstacle
	 * 
	 * @param imagePaths image de l'obstacle (ici le rocher ) 
	 * 
	 * @param pointDeVie ici fixé a 1 (cassable que par les bombes ) 
	 * 
	 * @param degats ici fixé a 0 car ils ne font pas de degats
	 * 
	 * @param collision fixé a true 
	 */

	public Rock (Vector2 position , Vector2 size) {
		super (position , size,ImagePaths.ROCK,1,0,true);
	}

	

}
	
