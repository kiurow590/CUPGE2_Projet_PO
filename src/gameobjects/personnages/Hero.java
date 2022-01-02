package gameobjects.personnages;

import java.util.ArrayList;
import java.util.List;

import gameobjects.Entity;
import gameobjects.projectiles.Projectile;
import gameobjects.projectiles.Tear;
import libraries.StdDraw;
import libraries.Vector2;
import resources.HeroInfos;
import resources.ImagePaths;

public class Hero extends Entity {
	/**
	 * Attribut
	 */
	private Vector2 position;
	private Vector2 size;
	private String imagePath;
	private double speed;
	private Vector2 direction;

	private List<Projectile> lstLarme;
	private int degats;

	private boolean estInvincible;
	private boolean estRapide;
	private boolean estPuissant;
	private int compteurInvincible;
	private int compteurRapide;
	private int compteurTir;
	private int compteurPuissance;

	private int pV;
	private int maxPV;

	private int stackArgent;
	private int soldePieceMax;

	private int compteurArgentTriche;

	/**
	 * Constructeur de hero
	 * 
	 * @param position position initiale du Hero
	 */
	public Hero(Vector2 position) {
		this.position = position;
		this.size = HeroInfos.ISAAC_SIZE;
		this.speed = HeroInfos.ISAAC_SPEED;
		this.imagePath = ImagePaths.ISAAC;
		this.direction = new Vector2();

		this.estInvincible = false;

		this.compteurInvincible = HeroInfos.ISAAC_INVINCIBILITY_DELAY;
		this.compteurTir = HeroInfos.ISAAC_TIR_DELAY;
		this.pV = HeroInfos.ISAAC_LIFE;

		lstLarme = new ArrayList<Projectile>();

		this.estRapide = false;
		this.compteurRapide = 0;

		this.estPuissant = false;
		this.compteurPuissance = 0;

		this.degats = HeroInfos.ATTACK;

		this.maxPV = HeroInfos.ISAAC_LIFE;

		this.stackArgent = 0;
		soldePieceMax = HeroInfos.ISAAC_MAX_STACK;
	}

	/**
	 * Methode qui mets a jour l'objet du jeu (position, vitesse, Compteurs ...etc.)
	 */
	public void updateGameObject() {
		move();
		if (this.compteurTir > 0) {
			this.compteurTir--;
		}
		if (this.compteurInvincible > 0) {
			this.compteurInvincible--;

		}
		if (this.compteurRapide > 0) {
			this.compteurRapide--;

		}
		if (this.compteurPuissance > 0) {
			this.compteurPuissance--;

		}
		if (this.compteurArgentTriche > 0) {
			this.compteurArgentTriche--;

		}
	}

	/**
	 * Methode qui retire des pv au personnage
	 * 
	 * @param i valeur de pv retiré
	 */
	public void retirePV(int i) {
		if (!this.estInvincible) {
			this.pV -= i;
			this.compteurInvincible = 50;
		}

	}

	/**
	 * Methode qui ajoute dix piece au personnage
	 * 
	 */
	public void addArgentTriche() {
		if (this.compteurArgentTriche <= 0) {
			this.stackArgent += 10;
			this.compteurArgentTriche = 50;
		}

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
		return this.pV <= 0;
	}

	/**
	 * Methode qui ajoute des PV au personnage
	 * 
	 * @param i la valeur de pv a rajouter
	 */
	public void addPV(int i) {
		this.pV += i;
	}

	/**
	 * Methode qui creer une larme et qui la stock dans la liste de larme du
	 * personnage
	 * 
	 * @param e larme
	 */
	public void creeLarme(Vector2 position, String imagePath, Vector2 direction) {
		if (this.compteurTir <= 0) {
			this.lstLarme.add(new Tear(position, imagePath, direction));
			this.compteurTir = 20;
		}

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
		StdDraw.picture(this.position.getX(), this.position.getY(), this.imagePath, this.size.getX(), this.size.getY(),
				0);
		StdDraw.setPenColor();
		StdDraw.rectangle(getPosition().getX(), getPosition().getY(), getSize().getX() / 2, getSize().getY() / 2);
	}

	/**
	 * Passe le hero en mode invincible ou pas
	 */
	public void modeInvincible() {
		if (!this.estInvincible && this.compteurInvincible == 0) {
			this.estInvincible = true;

			this.compteurInvincible = 10;
		} else if (this.estInvincible && this.compteurInvincible == 0) {
			this.estInvincible = false;

			this.compteurInvincible = 10;
		}

	}

	/**
	 * Passe le hero en mode rapide ou pas
	 */
	public void moderapide() {
		if (!this.estRapide && this.compteurRapide == 0) {
			this.estRapide = true;
			this.compteurRapide = 40;
			this.speed = this.speed * 2;
		} else if (this.estRapide && this.compteurRapide == 0) {
			this.estRapide = false;
			this.compteurRapide = 40;
			this.speed = this.speed / 2;
		}

	}

	/**
	 * Passe le hero en mode puissant ou pas
	 */
	public void modePuissance() {
		if (!this.estPuissant && this.compteurPuissance == 0) {
			this.estPuissant = true;
			this.compteurPuissance = 40;
			this.degats = 5000000;
			System.out.println("Dobby Pete des cul");
		} else if (this.estRapide && this.compteurRapide == 0) {
			this.estPuissant = false;
			this.compteurPuissance = 40;
			this.degats = 1;
			System.out.println("Dobby est une merde");

		}

	}

	/*
	 * Methodes qui permette de mettre en mvt le Hero
	 */
	public void goUpNext() {
		this.direction.addY(1);
	}

	public void goDownNext() {
		this.direction.addY(-1);
	}

	public void goLeftNext() {
		this.direction.addX(-1);
	}

	public void goRightNext() {
		this.direction.addX(1);
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

	public int getpV() {
		return pV;
	}

	public List<Projectile> getLstLarme() {
		return lstLarme;
	}

	public int getDegats() {
		return degats;
	}

	public void setDegats(int degats) {
		this.degats = degats;
	}

	public int getPV() {
		return this.pV;
	}

	public int getMaxPV() {
		return this.maxPV;
	}

	public void setMaxPV(int maxPV) {
		this.maxPV = maxPV;
	}

	public int getStackArgent() {
		return this.stackArgent;
	}

	public void AjoutStackArgent(int stackArgent) {
		if (this.stackArgent + stackArgent <= soldePieceMax) {
			this.stackArgent += stackArgent;
		}

	}

	public void setStackArgent(int stackArgent) {

		this.stackArgent += stackArgent;

	}

	public int getSoldePieceMax() {
		return this.soldePieceMax;
	}

}
