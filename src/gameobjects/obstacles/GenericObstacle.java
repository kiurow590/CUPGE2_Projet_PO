package gameobjects.obstacles;

import libraries.StdDraw;
import libraries.Vector2;

public abstract class GenericObstacle {
	/*
	 * Attributs Des obstacles
	 */
	private Vector2 position;
	private Vector2 size;
	private String imagePaths;
	private int pointDeVie;
	private int degats;
	private boolean collision;

	/*
	 * fonction generatrice
	 * 
	 * @param position position de l'obstacle
	 * 
	 * @param size taille de l'obstacle
	 * 
	 * @param image image qui represente l'obstacle
	 * 
	 * @param pointdeVie Point de vie de l'obstacle
	 * 
	 * @param degats degats de l'obstacle ( pour les Spikes)
	 * 
	 * @param collision boolean qui indique la presence de collision
	 * 
	 */
	public GenericObstacle(Vector2 position, Vector2 size, String image, int pointdeVie, int degats,
			Boolean collision) {
		this.position = position;
		this.size = size;
		this.imagePaths = image;
		this.pointDeVie = pointdeVie;
		this.degats = degats;
		this.collision = collision;
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
		return pointDeVie;
	}

	public int getDegats() {
		return degats;
	}

	public boolean getcollision() {
		return collision;
	}

	public boolean estVivant() {
		if (this.getpointDeVie() <= 0) {
			return false;
		}
		return true;
	}

	public void retirepointVie(int i) {
		this.pointDeVie -= i;
	}
}