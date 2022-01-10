package gameobjects.personnages.monstres;

import java.util.ArrayList;
import java.util.List;

import gameobjects.Entity;
import gameobjects.personnages.Hero;
import gameobjects.projectiles.Projectile;
import libraries.Vector2;

/**
 * Classe de monstre
 */
public abstract class Monster extends Entity {

	// caracteristique ajout
	int degatCorpsACorps;

	List<Projectile> lstProjectile;

	int immobilus;

	/**
	 * Constructeur de monstre
	 * 
	 * @param position         position initiale du monstre
	 * @param size             taille du monstre
	 * @param speed            vitesse du monstre
	 * @param direction        direction initale du monstre --> souvent null
	 * @param ptDeVie          point de vie du monstre
	 * @param degatCorpsACorps damage du monstre au corps a corps
	 */
	public Monster(Vector2 position, Vector2 size, String imagePath, double speed, int ptDeVie, int degatCorpsACorps) {
		super(position, size, imagePath, speed, new Vector2(), ptDeVie, ptDeVie);

		this.degatCorpsACorps = degatCorpsACorps;
		this.immobilus = 0;
		lstProjectile = new ArrayList<Projectile>();

	}

	/**
	 * retire les point de vie d'un monstre
	 * 
	 * @param i le nb de pointVie a retiré
	 * 
	 */
	public abstract void retirepointVie(int i);

	/**
	 * mets a jour l'objet du jeu (position vitesse ...etc.)
	 * 
	 * @param lsMonster
	 */
	public abstract void updateGameObject(Hero e, List<Monster> lsMonster);

	/**
	 * GETTERS / SETTERS
	 */

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

	public List<Projectile> getLstProjectile() {
		return lstProjectile;
	}

	public void setLstProjectile(List<Projectile> lstProjectile) {
		this.lstProjectile = lstProjectile;
	}

}
