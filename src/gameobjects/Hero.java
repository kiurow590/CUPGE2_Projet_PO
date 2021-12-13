package gameobjects;

import java.util.ArrayList;
import java.util.List;

import libraries.StdDraw;
import libraries.Vector2;

public class Hero {
	/**
	 * Attribut
	 */
	private Vector2 position;
	private Vector2 size;
	private String imagePath;
	private double speed;
	private Vector2 direction;
	
	private List lstLarme;

	private boolean estInvincible;
	private int compteurInvincible;

	private int pV;

	/**
	 * Constructeur Hero
	 * 
	 * @param position  position du hero
	 * @param size      taille du hero
	 * @param speed     vitesse de deplacement
	 * @param imagePath image du personnage
	 */
	public Hero(Vector2 position, Vector2 size, double speed, String imagePath, int compteurInvincible, int pv) {
		this.position = position;
		this.size = size;
		this.speed = speed;
		this.imagePath = imagePath;
		this.direction = new Vector2();

		this.estInvincible = false;

		this.compteurInvincible = compteurInvincible;

		this.pV = pv;
		
		lstLarme = new ArrayList<Larme>();
	}

	/**
	 * Methode qui mets a jour l'objet du jeu (position vitesse ...etc.)
	 */
	public void updateGameObject() {
		move();
	}

	public void retirePV(int i) {
		if (!this.estInvincible) {
			this.pV -= i;
		}

	}

	public void addPV(int i) {
		this.pV += i;
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
	 * Passe le hero en mode invincible ou pas
	 */
	public void modeInvincible() {
		if (!this.estInvincible && this.compteurInvincible == 0) {
			setEstInvincible(true);
			System.out.println("Dobby est invincible");
			this.compteurInvincible = 5;
		} else if (this.estInvincible && this.compteurInvincible == 0) {
			setEstInvincible(false);
			this.compteurInvincible = 5;
			System.out.println("Dobby est une merde");
		} else {
			this.compteurInvincible--;
		}
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

	/*
	 * Getters and Setters
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

	public boolean isEstInvincible() {
		return estInvincible;
	}

	public void setEstInvincible(boolean estInvincible) {
		this.estInvincible = estInvincible;
	}

	public int getpV() {
		return pV;
	}

	public void setpV(int pV) {
		this.pV = pV;
	}

	public List<Larme> getLstLarme() {
		return lstLarme;
	}

	public void setLstLarme(ArrayList<Larme> lstLarme) {
		this.lstLarme = lstLarme;
	}

}
