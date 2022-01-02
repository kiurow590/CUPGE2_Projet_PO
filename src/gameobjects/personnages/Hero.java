package gameobjects.personnages;

import java.util.ArrayList;
import java.util.List;

import gameobjects.Entity;
import gameobjects.projectiles.Projectile;
import gameobjects.projectiles.Tear;
import libraries.Vector2;
import resources.HeroInfos;
import resources.ImagePaths;

public class Hero extends Entity {
	/**
	 * Attribut
	 */

	private List<Projectile> lstLarme;
	private int degats;

	private boolean estInvincible;
	private boolean estRapide;
	private boolean estPuissant;
	private int countDownInvincible;
	private int countDownSpped;
	private int countDownTir;
	private int countDownPower;

	private int pV;
	private int maxPV;

	private int stackArgent;
	private int soldePieceMax;

	private int countDownGiveStack;

	/**
	 * Constructeur de hero
	 * 
	 * @param position position initiale du Hero
	 */
	public Hero(Vector2 position) {

		super(position, HeroInfos.ISAAC_SIZE, ImagePaths.ISAAC, HeroInfos.ISAAC_SPEED, new Vector2());

		this.estInvincible = false;

		this.countDownInvincible = HeroInfos.ISAAC_INVINCIBILITY_DELAY;
		this.countDownTir = HeroInfos.ISAAC_TIR_DELAY;
		this.pV = HeroInfos.ISAAC_LIFE;

		lstLarme = new ArrayList<Projectile>();

		this.estRapide = false;
		this.countDownSpped = 0;

		this.estPuissant = false;
		this.countDownPower = 0;

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
		if (this.countDownTir > 0) {
			this.countDownTir--;
		}
		if (this.countDownInvincible > 0) {
			this.countDownInvincible--;

		}
		if (this.countDownSpped > 0) {
			this.countDownSpped--;

		}
		if (this.countDownPower > 0) {
			this.countDownPower--;

		}
		if (this.countDownGiveStack > 0) {
			this.countDownGiveStack--;

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
			this.countDownInvincible = 50;
		}

	}

	/**
	 * Methode qui ajoute dix piece au personnage
	 * 
	 */
	public void addArgentTriche() {
		if (this.countDownGiveStack <= 0) {
			this.stackArgent += 10;
			this.countDownGiveStack = 50;
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
		if (this.countDownTir <= 0) {
			this.lstLarme.add(new Tear(position, imagePath, direction));
			this.countDownTir = 20;
		}

	}

	/**
	 * Passe le hero en mode invincible ou pas
	 */
	public void modeInvincible() {
		if (!this.estInvincible && this.countDownInvincible == 0) {
			this.estInvincible = true;

			this.countDownInvincible = 10;
		} else if (this.estInvincible && this.countDownInvincible == 0) {
			this.estInvincible = false;

			this.countDownInvincible = 10;
		}

	}

	/**
	 * Passe le hero en mode rapide ou pas
	 */
	public void moderapide() {
		if (!this.estRapide && this.countDownSpped == 0) {
			this.estRapide = true;
			this.countDownSpped = 40;
			this.speed = this.speed * 2;
		} else if (this.estRapide && this.countDownSpped == 0) {
			this.estRapide = false;
			this.countDownSpped = 40;
			this.speed = this.speed / 2;
		}

	}

	/**
	 * Passe le hero en mode puissant ou pas
	 */
	public void modePuissance() {
		if (!this.estPuissant && this.countDownPower == 0) {
			this.estPuissant = true;
			this.countDownPower = 40;
			this.degats = 5000000;
			System.out.println("Dobby Pete des cul");
		} else if (this.estRapide && this.countDownSpped == 0) {
			this.estPuissant = false;
			this.countDownPower = 40;
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

	/*
	 * Getters and Setters
	 */

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
