package gameobjects;

import java.util.ArrayList;
import java.util.List;

import libraries.Physics;
import libraries.StdDraw;
import libraries.Vector2;
import resources.ImagePaths;
import resources.RoomInfos;
public class Rocher extends Obstacles {
	private String imagePaths;
	/*
	 * constructeur de rocher 
	 * @param position 		position de l'obstacle 
	 * @param size			taille de l'obstacle
	 * @param imagePaths 	image de l'obstacle
	 */
	public Rocher (Vector2 position , Vector2 size) {
		super (position , size);
		this.imagePaths= resources.ImagePaths.ROCK;
	}

	
	public void drawGameObject() {
		StdDraw.picture(getPosition().getX(), getPosition().getY(), getimagePaths(), getSize().getX(), getSize().getY(),
				0);
		StdDraw.setPenColor();
		StdDraw.rectangle(getPosition().getX(), getPosition().getY(), getSize().getX() / 2, getSize().getY() / 2);
	}
	public String getimagePaths() {
		return imagePaths;
	}
	
}
