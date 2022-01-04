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
	 * constructeur de rocher 
	 * @param position 		position de l'obstacle 
	 * @param size			taille de l'obstacle
	 * @param imagePaths 	image de l'obstacle
	 */
	public Rock (Vector2 position , Vector2 size) {
		super (position , size,resources.ImagePaths.ROCK,1);
	}

	

}
	
