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
	private int damage;

	private boolean estInvincible;
	private boolean estRapide;
	private boolean estPuissant;
	private int countDownInvincible;
	private int countDownSpped;
	private int countDownTir;
	private int countDownPower;

	private int stackArgent;
	private int soldeStackMax;

	private int countDownGiveStack;

	/**
	 * Constructeur de hero
	 * 
	 * @param position position initiale du Hero
	 */
	public Hero(Vector2 position) {

		super(position, HeroInfos.ISAAC_SIZE, ImagePaths.ISAAC, HeroInfos.ISAAC_SPEED, new Vector2(),
				HeroInfos.ISAAC_LIFE, HeroInfos.ISAAC_LIFE);

		this.estInvincible = false;

		this.countDownInvincible = HeroInfos.ISAAC_INVINCIBILITY_DELAY;
		this.countDownTir = HeroInfos.ISAAC_TIR_DELAY;

		lstLarme = new ArrayList<Projectile>();

		this.estRapide = false;
		this.countDownSpped = 0;

		this.estPuissant = false;
		this.countDownPower = 0;

		this.damage = HeroInfos.ATTACK;

		this.stackArgent = 0;
		soldeStackMax = HeroInfos.ISAAC_MAX_STACK;
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

	@Override
	public void retirepointVie(int i) {
		if (!this.estInvincible) {
			this.pointVie -= i;
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
		return this.pointVie <= 0;
	}

	/**
	 * Methode qui creer une larme et qui la stock dans la liste de larme du
	 * personnage
	 * 
	 * @param e larme
	 */
	public void creeLarme(Vector2 position, String imagePath, Vector2 direction) {
		if (this.countDownTir <= 0) {
			this.lstLarme.add(new Tear(position, imagePath, direction, damage));
			this.countDownTir = 20;
		}

	}

	/**
	 * Passe le hero en mode invincible ou pas
	 */
	public void modeInvincible() {
		if (!this.estInvincible && this.countDownInvincible == 0) {
			this.estInvincible = true;

			this.countDownInvincible = 40;
		} else if (this.estInvincible && this.countDownInvincible == 0) {
			this.estInvincible = false;

			this.countDownInvincible = 40;
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
			this.damage = 5000000;
			System.out.println("Dobby Pete des cul");
		} else if (this.estPuissant && this.countDownPower == 0) {
			this.estPuissant = false;
			this.countDownPower = 40;
			this.damage = 1;
			System.out.println("Dobby est une merde");

		}

	}

	public List<Projectile> getLstLarme() {
		return lstLarme;
	}

	public int getdamage() {
		return damage;
	}

	public void setdamage(int damage) {
		this.damage = damage;
	}

	public int getpointVie() {
		return this.pointVie;
	}

	public int getMaxpointVie() {
		return this.maxpointVie;
	}

	public void setMaxpointVie(int maxpointVie) {
		this.maxpointVie = maxpointVie;
	}

	public int getStackArgent() {
		return this.stackArgent;
	}

	public void AjoutStackArgent(int stackArgent) {
		if (this.stackArgent + stackArgent <= soldeStackMax) {
			this.stackArgent += stackArgent;
		}

	}

	public void setStackArgent(int stackArgent) {

		this.stackArgent = stackArgent;

	}

	public int getsoldeStackMax() {
		return this.soldeStackMax;
	}

}
