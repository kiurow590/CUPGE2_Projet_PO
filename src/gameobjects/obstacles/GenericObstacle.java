package gameobjects.obstacles;

import java.util.ArrayList;
import java.util.List;

import libraries.Physics;
import libraries.StdDraw;
import libraries.Vector2;
import resources.ImagePaths;
import resources.RoomInfos;

public abstract class GenericObstacle {
	/*
	 * Attributs Des obstacles
	 */
	private Vector2 position;
	private Vector2 size;
	private String imagePaths;
	private int pointDeVie;

	public GenericObstacle(Vector2 position, Vector2 size ,String image,int pointdeVie) {
		this.position = position;
		this.size =size;
		this.imagePaths=image;
		this.pointDeVie =pointdeVie ;
	}

	public void drawGameObject() {
		StdDraw.picture(getPosition().getX(), getPosition().getY(), getimagePaths(), getSize().getX(), getSize().getY(),
				0);
		StdDraw.setPenColor();
		StdDraw.rectangle(getPosition().getX(), getPosition().getY(), getSize().getX() / 2, getSize().getY() / 2);
	}
	
	/*
	 * getter et setter
	 */
	public Vector2 getPosition() {
		return position;
	}

	public void setPosition(Vector2 position) {
		this.position = position;
	}

	public Vector2 getSize() {
		return size;
	}

	public void setSize(Vector2 size) {
		this.size = size;
	}
	public String getimagePaths() {
		return imagePaths;
	}
	public int getpointDeVie() {
		return pointDeVie;}
	
	public boolean estVivant() {
		if (this.getpointDeVie() <=0 ) { 
	 return false ;
		}
		return true ;
}
	
} 