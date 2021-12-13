package gameobjects;

import libraries.StdDraw;
import libraries.Vector2;

public class Larme {

	private Vector2 position;
	private Vector2 size;
	private String imagePath;
	private double speed;
	private Vector2 direction;
	private int portee;

	/**
	 * 
	 * @param position
	 * @param size
	 * @param imagePath
	 * @param speed
	 * @param direction
	 * @param portee
	 */
	public Larme(Vector2 position, Vector2 size, String imagePath, double speed, Vector2 direction, int portee) {
		super();
		this.position = position;
		this.size = size;
		this.imagePath = imagePath;
		this.speed = speed;
		this.direction = direction;
		this.portee = portee;
	}

	/**
	 * Methode qui mets a jour l'objet du jeu (position vitesse ...etc.)
	 */
	public void updateGameObject() {
		move();
		this.portee--;
	}

	/**
	 * Methode qui mets en mouvement le personnage
	 */
	private void move() {
		Vector2 normalizedDirection = getNormalizedDirection();
		Vector2 positionAfterMoving = getPosition().addVector(normalizedDirection);
		setPosition(positionAfterMoving);
		direction = new Vector2();

	}

	/**
	 * Methode qui dessine le personnage dans le jeu
	 */
	public void drawGameObject() {
		StdDraw.picture(getPosition().getX(), getPosition().getY(), getImagePath(), getSize().getX(), getSize().getY(),
				0);
	}

	/**
	 * Methode qui normalise le vecteur direction du personnage
	 * 
	 * @return le vexteur normaliser
	 */
	public Vector2 getNormalizedDirection() {
		Vector2 normalizedVector = new Vector2(direction);
		normalizedVector.euclidianNormalize(speed);
		return normalizedVector;
	}

	/*
	 * Moving from key inputs. Direction vector is later normalised.
	 */
	public void goUpNext() {
		getDirection().addY(1);
	}

	public void goDownNext() {
		getDirection().addY(-1);
	}

	public void goLeftNext() {
		getDirection().addX(-1);
	}

	public void goRightNext() {
		getDirection().addX(1);
	}

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

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public Vector2 getDirection() {
		return direction;
	}

	public void setDirection(Vector2 direction) {
		this.direction = direction;
	}

	public int getPortee() {
		return portee;
	}

	public void setPortee(int portee) {
		this.portee = portee;
	}

}
