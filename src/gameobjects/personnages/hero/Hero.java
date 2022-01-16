package gameobjects.personnages.hero;

import java.util.ArrayList;
import java.util.List;

import gameobjects.Entity;
import gameobjects.projectiles.Projectile;
import libraries.Vector2;
import resources.HeroInfos;
import resources.ImagePaths;

/**
 * Classe de hero
 */
public abstract class Hero extends Entity {
	/**
	 * Attribut
	 */

	List<Projectile> lstLarme;
	int damage;

	boolean estInvincible;
	boolean estRapide;
	boolean estPuissant;
	public boolean numberOne;
	int countDownInvincible;
	int countDownSpped;
	int countDownTir;
	int countDownPower;

	int stackArgent;
	int soldeStackMax;
	int soldeKey;

	int countDownGiveStack;

	/**
	 * Constructeur de hero
	 * 
	 * @param position position initiale du Hero
	 * @param image    image du hero
	 * @param speed    vitesse du hero
	 * @param vie      le nombre de point de vie du hero
	 * @param maxlife  le nombre maximum de point de vie
	 */
	public Hero(Vector2 position, String image, double speed, int vie, int maxlife) {

		super(position, HeroInfos.ISAAC_SIZE, image, speed, new Vector2(), vie, maxlife);

		this.estInvincible = false;

		this.countDownInvincible = HeroInfos.HERO_INVINCIBILITY_DELAY;
		this.countDownTir = HeroInfos.ISAAC_TIR_DELAY;

		lstLarme = new ArrayList<Projectile>();

		this.estRapide = false;
		this.countDownSpped = 0;

		this.estPuissant = false;
		this.countDownPower = 0;
		this.numberOne = false;

		this.damage = HeroInfos.ISAAC_ATTACK;

		this.stackArgent = 0;
		soldeStackMax = HeroInfos.HERO_MAX_STACK;
		this.soldeKey = 0;

	}

	/**
	 * mets a jour l'objet du jeu (position, vitesse, Compteurs ...etc.)
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
	 * ajoute dix piece au personnage
	 * 
	 */
	public void addArgentTriche() {
		if (this.countDownGiveStack <= 0) {
			this.stackArgent += 10;
			this.countDownGiveStack = 50;
		}

	}

	/**
	 * calcul si le hero est vivant
	 * 
	 * @return un boolean </br>
	 *         <ul>
	 *         <li>true - le heros est mort</li>
	 *         <li>false - le heros est vivant</li>
	 *         </ul>
	 */
	public boolean isDead() {
		return this.pointVie <= 0;
	}

	/**
	 * creer une larme et qui la stock dans la liste de larme du personnage
	 * 
	 * @param position  position initiale larme
	 * @param direction direction du projectile
	 */
	public abstract void creeProjectile(Vector2 position, Vector2 direction);

	/**
	 * Passe le hero en mode invincible ou pas
	 */
	public void modeInvincible() {
		if (!this.estInvincible && this.countDownInvincible == 0) {
			this.estInvincible = true;
			this.imagePath = ImagePaths.GODMODE;
			this.countDownInvincible = 40;
		} else if (this.estInvincible && this.countDownInvincible == 0) {
			this.estInvincible = false;
			if (this instanceof Isaac ) {
				this.imagePath = ImagePaths.ISAAC;
			}
			if (this instanceof Magdalene ) {
				this.imagePath = ImagePaths.MAGDALENE;
			}
			if (this instanceof PyroBarbare ) {
				this.imagePath = ImagePaths.PYROBARBARE;
			}
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
		} else if (this.estPuissant && this.countDownPower == 0) {
			this.estPuissant = false;
			this.countDownPower = 40;
			this.damage = 1;

		}

	}

	/**
	 * Getters / Setters
	 */

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

	public void setsoldeKey(int nombreDeKey) {
		this.soldeKey = nombreDeKey;
	}

	public int getsoldeKey() {
		return this.soldeKey;
	}
}
