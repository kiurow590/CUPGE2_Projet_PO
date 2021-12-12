package gameobjects;

import java.util.Random;

import libraries.StdDraw;
import libraries.Vector2;

public class Spider {

	private Vector2 position;
	private Vector2 size;
	private String imagePath;
	private double speed;
	private Vector2 direction;
	// caracteristique ajout
	private int ptDeVie;
	private int degatCorpsACorps;

	/**
	 * Constructeur de spider
	 * 
	 * @param position  position de la spider
	 * @param size      taille de la spider
	 * @param imagePath image representant la spider
	 * @param speed     vitesse de la spider
	 * @param direction direction de deplacement de la spider
	 */
	public Spider(Vector2 position, Vector2 size, String imagePath, double speed, Vector2 direction) {
		this.position = position;
		this.size = size;
		this.imagePath = imagePath;
		this.speed = speed;
		this.direction = direction;
	}

	/**
	 * Methode qui calcul si une araigné est morte
	 * 
	 * @return un boolean </br>
	 *         <ul>
	 *         <li>true - la spider est morte</li>
	 *         <li>false - la spider est vivante</li>
	 *         </ul>
	 */
	public boolean isDead() {
		return this.ptDeVie <= 0;
	}

	/**
	 * Methode qui retire les point de vie d'une araignee
	 * 
	 * @implNote Methode qui aurait dans le futur un parametre projectile indiquant
	 *           combien de pv retiré suivant l'attaque reçu
	 */
	public void retirePV() {
		// TODO: rajouter parametre pour retirer n PV
		this.ptDeVie -= 1;
	}

	/**
	 * Methode qui dessine le personnage dans le jeu
	 */
	public void drawGameObject() {
		StdDraw.picture(getPosition().getX(), getPosition().getY(), getImagePath(), getSize().getX(), getSize().getY(),
				0);
	}

	/**
	 * Methode qui mets a jour l'objet du jeu (position vitesse ...etc.)
	 */
	public void updateGameObject() {
		move();
	}

	/**
	 * Methode qui mets en mouvement l'araignee
	 */
	private void move() {
		Vector2 normalizedDirection = getNormalizedDirection();
		Vector2 positionAfterMoving = getPosition().addVector(normalizedDirection);
		setPosition(positionAfterMoving);
		direction = new Vector2();
		System.out.println(this.position.getX() + "," + this.position.getY());
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

}
