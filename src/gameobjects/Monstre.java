package gameobjects;

import java.util.ArrayList;
import java.util.List;

import libraries.Vector2;

public abstract class Monstre {

	private Vector2 position;
	private Vector2 size;

	private double speed;
	private Vector2 direction;
	// caracteristique ajout
	private int ptDeVie;
	private int degatCorpsACorps;

	private List<FlyProjectile> lstProjectile;

	private int immobilus;

	/**
	 * Constructeur de monstre
	 * 
	 * @param position         position initiale du monstre
	 * @param size             taille du monstre
	 * @param speed            vitesse du monstre
	 * @param direction        direction initale du monstre --> souvent null
	 * @param ptDeVie          point de vie du monstre
	 * @param degatCorpsACorps degats du monstre au corps a corps
	 */
	public Monstre(Vector2 position, Vector2 size, double speed, Vector2 direction, int ptDeVie, int degatCorpsACorps) {
		this.position = position;
		this.size = size;

		this.speed = speed;
		this.direction = direction;
		this.ptDeVie = ptDeVie;
		this.degatCorpsACorps = degatCorpsACorps;
		this.immobilus = 0;
		lstProjectile = new ArrayList<FlyProjectile>();

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

	/**
	 * Methode qui retire les point de vie d'un monstre
	 * 
	 * @param i
	 * 
	 */
	public abstract void retirePV(int i);

	/**
	 * Methode qui dessine la mouche dans le jeu
	 */
	public abstract void drawGameObject();

	/**
	 * Methode qui mets a jour l'objet du jeu (position vitesse ...etc.)
	 * 
	 * @param lsMonster
	 */
	public abstract void updateGameObject(Hero e, List<Monstre> lsMonster);

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
	 * GETTERS / SETTERS
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

	public int getImmobilus() {
		return immobilus;
	}

	public void setImmobilus(int immobilus) {
		this.immobilus = immobilus;
	}

	public List<FlyProjectile> getLstProjectile() {
		return lstProjectile;
	}

	public void setLstProjectile(List<FlyProjectile> lstProjectile) {
		this.lstProjectile = lstProjectile;
	}

}
