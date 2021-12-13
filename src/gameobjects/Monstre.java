package gameobjects;

import libraries.Vector2;

public abstract class Monstre {

	private Vector2 position;
	private Vector2 size;

	private double speed;
	private Vector2 direction;
	// caracteristique ajout
	private int ptDeVie;
	private int degatCorpsACorps;

	public Monstre(Vector2 position, Vector2 size, double speed, Vector2 direction, int ptDeVie, int degatCorpsACorps) {
		this.position = position;
		this.size = size;

		this.speed = speed;
		this.direction = direction;
		this.ptDeVie = ptDeVie;
		this.degatCorpsACorps = degatCorpsACorps;
	}

	/**
	 * Methode qui calcul si une mouche est morte
	 * 
	 * @return un boolean </br>
	 *         <ul>
	 *         <li>true - la mouche est morte</li>
	 *         <li>false - la mouche est vivante</li>
	 *         </ul>
	 */
	public boolean isDead() {
		return this.ptDeVie <= 0;
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

	public int getPtDeVie() {
		return ptDeVie;
	}

	public void setPtDeVie(int ptDeVie) {
		this.ptDeVie = ptDeVie;
	}

	public int getDegatCorpsACorps() {
		return degatCorpsACorps;
	}

	public void setDegatCorpsACorps(int degatCorpsACorps) {
		this.degatCorpsACorps = degatCorpsACorps;
	}

}
