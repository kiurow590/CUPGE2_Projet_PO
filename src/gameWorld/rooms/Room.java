package gameWorld.rooms;

import java.util.ArrayList;
import java.util.List;

import gameWorld.rooms.portes.CarriesAway;
import gameWorld.rooms.portes.Door;
import gameobjects.objets.GenericObject;
import gameobjects.objets.consommables.BoxWin;
import gameobjects.objets.consommables.Coin;
import gameobjects.objets.consommables.Life;
import gameobjects.objets.passifs.BloodOfMartyr;
import gameobjects.objets.passifs.LifeExtension;
import gameobjects.obstacles.GenericObstacle;
import gameobjects.obstacles.Poop;
import gameobjects.obstacles.Rock;
import gameobjects.obstacles.Spikes;
import gameobjects.personnages.Hero;
import gameobjects.personnages.monstres.Boss;
import gameobjects.personnages.monstres.Fly;
import gameobjects.personnages.monstres.Monster;
import gameobjects.personnages.monstres.Spider;
import libraries.Physics;
import libraries.StdDraw;
import libraries.Vector2;
import resources.ImagePaths;
import resources.RoomInfos;

/**
 * Methode abstraite de generation de room
 */
public abstract class Room {

	/**
	 * attributs
	 */
	Hero hero;

	String type;

	Integer id;

	List<Door> lstPorte;

	int compteurInvincibiliteHero;

	List<Monster> lsMonster;

	List<GenericObject> lstObjet;

	List<GenericObstacle> lsObstacle;

	int countDownObject;

	boolean aGagner;

	/**
	 * Constructeur de room
	 * 
	 * @param hero personnage de la room
	 */
	public Room(Hero hero, Integer id) {
		this.id = id;
		this.hero = hero;
		this.lsMonster = new ArrayList<Monster>();
		this.compteurInvincibiliteHero = 10;
		this.type = "DEFAULT_ROOM";

		this.lstPorte = new ArrayList<>();

		this.lstObjet = new ArrayList<>();

		this.aGagner = false;

		this.lsObstacle = new ArrayList<>();
		this.countDownObject = 0;
	}

	/*
	 * Make every entity that compose a room process one step
	 */
	public abstract void updateRoom();

	/**
	 * dessine la room
	 */
	public abstract void drawRoom();

	/**
	 * dessine les porte de la room
	 */
	public void dessinePorte() {

		for (int i = 0; i < lstPorte.size(); i++) {
			if (lsMonster.isEmpty() && !(lstPorte.get(i) instanceof CarriesAway)
					&& !(lstPorte.get(i).getImagePaths().equals(ImagePaths.SECRET_ENTRY))) {
				if (lstPorte.get(i).getImagePaths().compareTo(ImagePaths.PORTE_BOSS_OUVERTE)==1) {
					lstPorte.get(i).setImagePaths(ImagePaths.PORTE_BOSS_OUVERTE);
				}
				else {
				lstPorte.get(i).setImagePaths(ImagePaths.OPENED_DOOR);
				
			}
			lstPorte.get(i).drawGameObject();
			}

		}
	}

	/**
	 * génère une objet random en respectant les probabilité
	 * d'apparition
	 */
	public GenericObject initObjectGift() {
		double objectRandom = Math.random();
		GenericObject objectReturn = new BoxWin(RoomInfos.POSITION_CENTER_OF_ROOM, this);
		if (objectRandom < 0.35) {
			double randomPiece = Math.random();
			if (randomPiece < 0.45) {
				objectReturn = new Coin(1, RoomInfos.POSITION_CENTER_OF_ROOM);
			} else if (randomPiece >= 0.45 && randomPiece < 0.8) {
				objectReturn = new Coin(5, RoomInfos.POSITION_CENTER_OF_ROOM);

			} else {
				objectReturn = new Coin(10, RoomInfos.POSITION_CENTER_OF_ROOM);

			}
		} else if (objectRandom >= 0.35 && objectRandom < 0.75) {
			double randomCoeur = Math.random();

			if (randomCoeur < 0.6) {
				objectReturn = new Life(1, RoomInfos.POSITION_CENTER_OF_ROOM);

			} else {
				objectReturn = new Life(2, RoomInfos.POSITION_CENTER_OF_ROOM);

			}
		} else if (objectRandom >= 0.75 && objectRandom < 0.875) {
			objectReturn = new BloodOfMartyr(RoomInfos.POSITION_CENTER_OF_ROOM);
		} else if (objectRandom >= 0.875) {
			objectReturn = new LifeExtension(RoomInfos.POSITION_CENTER_OF_ROOM);
		}

		return objectReturn;

	}

	/**
	 * met a jour le hero
	 */
	void makeHeroPlay() {
		hero.updateGameObject();
	}

	/**
	 * affiche les obstacles
	 */
	public void dessineObstacles() {
		if (!lsObstacle.isEmpty()) {
			for (int i = 0; i < lsObstacle.size(); i++) {
				if (!lsObstacle.get(i).estVivant()) {
					lsObstacle.remove(i);
				} else {
					lsObstacle.get(i).drawGameObject();
				}
			}
		}
	}

	/**
	 * initialise le nb de monstre au demarrage de la room
	 */
	void initMonster() {
		int nbMonstre;

		if (this.id <= 5) {
			nbMonstre = 4;
		} else if (this.id > 5 && this.id <= 14) {
			nbMonstre = 8;

		} else {
			nbMonstre = 10;

		}

		for (int i = 0; i < nbMonstre; i++) {
			// on evite que un monstre spawn sur un obstacle
			// ainsi lors de la g�n�ration on fait en sorte que la position du monstre
			// n'est jamais la m�me que celui d'un obstacle.
			double x = Math.random();
			double y = Math.random();
			if (x < 0.3) {
				x += 0.1;
			}
			if (x > 0.7) {
				x -= 0.2;
			}
			if (y < 0.35) {
				y += 0.14;
			}
			if (y > 0.75) {
				y -= 0.2;

			}
			if (Math.random() < 0.5) {

				this.lsMonster.add(new Spider(new Vector2(x, y)));

				if (nbMonstre == 4) {
					this.lsMonster.get(i).setDegatCorpsACorps(1);

				} else if (nbMonstre == 8) {
					this.lsMonster.get(i).setDegatCorpsACorps(2);

				} else {
					this.lsMonster.get(i).setDegatCorpsACorps(4);

				}

			} else {

				this.lsMonster.add(new Fly(new Vector2(x, y)));
				if (nbMonstre == 4) {
					this.lsMonster.get(i).setDegatCorpsACorps(1);

				} else if (nbMonstre == 8) {
					this.lsMonster.get(i).setDegatCorpsACorps(2);

				} else {
					this.lsMonster.get(i).setDegatCorpsACorps(4);

				}

			}

		}
	}

	/**
	 * Affiche l'objet en récompense de la salle
	 */
	public void affichageObjets() {

		for (int i = 0; i < lstObjet.size(); i++) {
			if (!(this instanceof ShopRoom)) {

				if (this.lsMonster.size() == 0 && lstObjet.get(i).EstRamasser() == false) {

					lstObjet.get(i).drawGameObject();

				}

			} else {
				if (this.lsMonster.size() == 0 && lstObjet.get(i).EstRamasser() == false) {

					lstObjet.get(i).drawGameObject();

					StdDraw.setPenRadius();
					StdDraw.setPenColor(StdDraw.BLACK);
					StdDraw.text(lstObjet.get(i).getPosition().getX() + 0.02,
							lstObjet.get(i).getPosition().getY() + 0.02, +lstObjet.get(i).getPrix() + "");

				}
			}

		}

	}

	/**
	 * retire des listes tous les monstre qui sont supposer mort
	 */
	public void rammasseMonstreMort() {

		for (int i = 0; this.lsMonster != null && i < this.lsMonster.size(); i++) {
			if (this.lsMonster.get(i).isDead()) {

				this.lsMonster.remove(i);
			}
		}

	}

	/**
	 * nettoie de l'afficheage les larme
	 */
	public void nettoyageLarme() {
		for (int k = 0; k < hero.getLstLarme().size(); k++) {

			if (this.hero.getLstLarme().get(k).getPortee() <= 0) {
				this.hero.getLstLarme().remove(k);

			}

		}
	}

	/**
	 * nettoie de l'afficheage les larme
	 */
	void nettoyageProj() {
		for (int k = 0; this.lsMonster != null && k < this.lsMonster.size(); k++) {
			if (this.lsMonster.get(k) instanceof Fly) {
				Fly f = (Fly) this.lsMonster.get(k);
				for (int i = 0; i < f.getLstProjectile().size(); i++) {
					if (f.getLstProjectile().get(i).getPortee() <= 0) {
						f.getLstProjectile().remove(i);

					}
				}
			}

		}
	}

	/**
	 * gere les collision entre differente entité
	 */
	void collisionReport() {

		// Pour chaque monstre (vivant ou mort)
		for (int i = 0; this.lsMonster != null && i < this.lsMonster.size(); i++) {

			collisionHero(this.lsMonster.get(i));
			collisionLarme(this.lsMonster.get(i));
			collisionProjectileFly(this.lsMonster.get(i));
			collisionObstacleMonstre(this.lsMonster.get(i));

		}
		collisionObstacleLarmeHero();
		collisionObstacleHero();
		collisionObjet();
	}

	/**
	 * calcul les collision entre le personnage et les objet
	 */
	public void collisionObjet() {
		// pour chacun de mes objet
		for (int i = 0; i < lstObjet.size(); i++) {
			// si je ne suis pas dans une shopRoom
			// que la liste de monstre n'est pas vide
			// que je ne suis pas en collision
			if (!(this instanceof ShopRoom) && this.lsMonster.isEmpty() && Physics.rectangleCollision(
					hero.getPosition(), hero.getSize(), lstObjet.get(i).getPosition(), lstObjet.get(i).getSize())) {
				lstObjet.get(i).updateHeroPerf(hero);
				// Sinon si je suis dans une shop room
			} else if (this instanceof ShopRoom) {
				// si mon compteur pour recup l'objet est null
				// que la lst de monstre est vide et que je suis en collision et que mon objet
				// n'est pas ramasser
				if (this.countDownObject == 0 && this.lsMonster.isEmpty()
						&& Physics.rectangleCollision(hero.getPosition(), hero.getSize(), lstObjet.get(i).getPosition(),
								lstObjet.get(i).getSize())
						&& hero.getStackArgent() >= lstObjet.get(i).getPrix()
						&& (lstObjet.get(i).EstRamasser() == false)) {
					// je mets a jour mes perf
					lstObjet.get(i).updateHeroPerf(hero);
					// si mon objet c'est pas de l'ajout de vie
					if (!(lstObjet.get(i) instanceof Life)) {
						// je retire de l'argetnt au hero a hauteur du prix de l'objet prix
						hero.setStackArgent(hero.getStackArgent() - lstObjet.get(i).getPrix());
						// si mon objet c'est de la vie et que l'ajout de pv ne depasse pas les pc Max
					} else if ((lstObjet.get(i) instanceof Life)
							&& lstObjet.get(i).getValue() + hero.getPointVie() <= hero.getMaxpointVie()) {
						hero.setStackArgent(hero.getStackArgent() - lstObjet.get(i).getPrix());
					}
					countDownObject = 20;
				}

				if (this.countDownObject > 0) {
					this.countDownObject--;
				}
			}

		}

	}

	/**
	 * gere la collision entre monstre et le Hero
	 * 
	 * @param monstre monstre avec lequel la colision est possible
	 */
	private void collisionHero(Monster monstre) {
		// Retrait des points de vie d'Isaac s'il est touche par un monstre
		if (this.compteurInvincibiliteHero == 0 && Physics.rectangleCollision(this.hero.getPosition(),
				this.hero.getSize(), monstre.getPosition(), monstre.getSize())) {
			this.hero.retirepointVie(monstre.getDegatCorpsACorps());
			this.compteurInvincibiliteHero = 50;
			monstre.setImmobilus(35);

			// Decrementation du compteur d'invicibilite d'Isaac
		} else if (this.compteurInvincibiliteHero > 0) {
			this.compteurInvincibiliteHero--;
		}

	}

	/**
	 * gere la collision entre les monstre et les larmes
	 * 
	 * @param monstre
	 */
	private void collisionLarme(Monster monstre) {
		// Gestion des larmes tirees par Isaac
		for (int j = 0; j < hero.getLstLarme().size(); j++) {

			// Gestion de la collision d'une larme
			if (Physics.rectangleCollision(this.hero.getLstLarme().get(j).getPosition(),
					this.hero.getLstLarme().get(j).getSize(), monstre.getPosition(), monstre.getSize())) {
				monstre.retirepointVie(this.hero.getLstLarme().get(j).getdamage());
				this.hero.getLstLarme().get(j).setPortee(0);
			}

		}

	}

	/**
	 * gere la collision entre le hero est les projectile lancer par des
	 * monstres
	 * 
	 * @param monstre
	 */
	public void collisionProjectileFly(Monster monstre) {
		// Gestion des projectiles de la mouche

		for (int j = 0; !monstre.getLstProjectile().isEmpty() && j < monstre.getLstProjectile().size(); j++) {
			if (Physics.rectangleCollision(this.hero.getPosition(), this.hero.getSize(),
					monstre.getLstProjectile().get(j).getPosition(), monstre.getLstProjectile().get(j).getSize())) {
				this.hero.retirepointVie(monstre.getLstProjectile().get(j).getdamage());
				monstre.getLstProjectile().get(j).setPortee(0);
			}
			// gestion collision projectileFly avec les obstacles
			for (int numeroObstacles = 0; !this.lsObstacle.isEmpty()
					&& numeroObstacles < this.lsObstacle.size(); numeroObstacles++) {
				if (this.lsObstacle.get(numeroObstacles) instanceof Poop
						|| this.lsObstacle.get(numeroObstacles) instanceof Rock) {

					if (Physics.rectangleCollision(this.lsObstacle.get(numeroObstacles).getPosition(),
							this.lsObstacle.get(numeroObstacles).getSize(),
							monstre.getLstProjectile().get(j).getPosition(),
							monstre.getLstProjectile().get(j).getSize())) {
						monstre.getLstProjectile().get(j).setPortee(0);
					}
				}
			}
		}
	}
	public void collisionObstacleHero() {
		for (int numeroObstacles = 0; !this.lsObstacle.isEmpty()
				&& numeroObstacles < this.lsObstacle.size(); numeroObstacles++) {

			if (Physics.rectangleCollision(this.hero.getPosition(), this.hero.getSize(),
					this.lsObstacle.get(numeroObstacles).getPosition(),
					this.lsObstacle.get(numeroObstacles).getSize())) {

				if (this.lsObstacle.get(numeroObstacles) instanceof Spikes && this.compteurInvincibiliteHero == 0) {
					this.hero.retirepointVie(this.lsObstacle.get(numeroObstacles).getDegats());
					this.compteurInvincibiliteHero = 50;
				} else if (lsObstacle.get(numeroObstacles) instanceof Poop
						|| lsObstacle.get(numeroObstacles) instanceof Rock) {

					this.hero.setPosition(this.hero.getLastposition());
				}
			}
		}
	}

	public void collisionObstacleMonstre(Monster monster) {
		for (int numeroObstacles = 0; !this.lsObstacle.isEmpty()
				&& numeroObstacles < this.lsObstacle.size(); numeroObstacles++) {

			if (Physics.rectangleCollision(monster.getPosition(), monster.getSize(),
					this.lsObstacle.get(numeroObstacles).getPosition(),
					this.lsObstacle.get(numeroObstacles).getSize())) {

				// pour les Spider et boss uniquement ( les mouches passent au dessus des
				// obstacles )
				if (monster instanceof Spider || monster instanceof Boss) {
					// uniquement les Rocks et les Poops
					if (this.lsObstacle.get(numeroObstacles) instanceof Rock
							|| this.lsObstacle.get(numeroObstacles) instanceof Poop) {
						monster.setPosition(monster.getLastposition());
						

					}
				}

			}
		}
	}

	/*
	 * collision larme et obstacles .
	 */
	public void collisionObstacleLarmeHero() {
		for (int numeroObstacles = 0; !this.lsObstacle.isEmpty()
				&& numeroObstacles < this.lsObstacle.size(); numeroObstacles++) {
			for (int numeroLarmeHero = 0; !this.hero.getLstLarme().isEmpty()
					&& numeroLarmeHero < this.hero.getLstLarme().size(); numeroLarmeHero++) {
				if (Physics.rectangleCollision(this.hero.getLstLarme().get(numeroLarmeHero).getPosition(),
						this.hero.getLstLarme().get(numeroLarmeHero).getSize(),
						this.lsObstacle.get(numeroObstacles).getPosition(),
						this.lsObstacle.get(numeroObstacles).getSize())) {
					if (this.lsObstacle.get(numeroObstacles) instanceof Rock) {
						this.hero.getLstLarme().remove(numeroLarmeHero);
					}
					if (this.lsObstacle.get(numeroObstacles) instanceof Poop) {
						this.lsObstacle.get(numeroObstacles).retirepointVie(this.hero.getdamage());
						this.hero.getLstLarme().remove(numeroLarmeHero);
					}

				}
			}
		}
	}

	/**
	 * met a jour le monstre
	 */
	void makeMonsterPlay() {

		for (int i = 0; this.lsMonster != null && i < this.lsMonster.size(); i++) {
			this.lsMonster.get(i).updateGameObject(this.hero, lsMonster);
		}

	}

	/**
	 * affiche les information du joueur
	 */
	public void affichageViePiece() {
		// Affichage nb piece
		StdDraw.picture(0.05, 0.9, ImagePaths.DIME, RoomInfos.TILE_SIZE.scalarMultiplication(0.3).getX(),
				RoomInfos.TILE_SIZE.scalarMultiplication(0.3).getY());
		StdDraw.setPenRadius();
		StdDraw.setPenColor(StdDraw.WHITE);
		StdDraw.text(0.1, 0.9, ": " + hero.getStackArgent() + "");
		// affiche puissance
		StdDraw.picture(0.05, 0.85, ImagePaths.STRENGTH, RoomInfos.TILE_SIZE.scalarMultiplication(0.3).getX(),
				RoomInfos.TILE_SIZE.scalarMultiplication(0.3).getY());
		StdDraw.setPenRadius();
		StdDraw.setPenColor(StdDraw.WHITE);
		StdDraw.text(0.1, 0.85, ": " + hero.getdamage() + "");
		//------------------------------affichage key---------------------------------
		StdDraw.picture(0.05, 0.80, ImagePaths.KEY, RoomInfos.TILE_SIZE.scalarMultiplication(0.3).getX(),
				RoomInfos.TILE_SIZE.scalarMultiplication(0.3).getY());
		StdDraw.setPenRadius();
		StdDraw.setPenColor(StdDraw.WHITE);
		StdDraw.text(0.1, 0.80, ": " + hero.getsoldeKey() + "");
		// ----------------------------------------------------------------
		StdDraw.setPenRadius();
		int pointVieView = this.hero.getpointVie();
		if (pointVieView % 2 == 0) {
			double x = 0.05;
			while (pointVieView != 0) {
				StdDraw.picture(x, 0.95, ImagePaths.HEART_HUD, RoomInfos.TILE_SIZE.scalarMultiplication(0.5).getX(),
						RoomInfos.TILE_SIZE.scalarMultiplication(0.5).getY());

				x += 0.05;
				pointVieView -= 2;
			}

		} else {
			double x = 0.05;
			while (pointVieView != 1) {
				StdDraw.picture(x, 0.95, ImagePaths.HEART_HUD, RoomInfos.TILE_SIZE.scalarMultiplication(0.5).getX(),
						RoomInfos.TILE_SIZE.scalarMultiplication(0.5).getY());

				x += 0.05;
				pointVieView -= 2;

			}

			StdDraw.picture(x, 0.95, ImagePaths.HALF_HEART_HUD, RoomInfos.TILE_SIZE.scalarMultiplication(0.5).getX(),
					RoomInfos.TILE_SIZE.scalarMultiplication(0.5).getY());
		}
	}

	/**
	 * dessine les monstre
	 */
	void dessineMonstre() {

		for (int i = 0; !this.lsMonster.isEmpty() && i < this.lsMonster.size(); i++) {
			if (this.lsMonster.get(i) != null) {
				this.lsMonster.get(i).drawGameObject();
			}
			for (int j = 0; this.lsMonster.get(i).getLstProjectile() != null
					&& j < this.lsMonster.get(i).getLstProjectile().size(); j++) {
				if (this.lsMonster.get(i).getLstProjectile().get(j).getPortee() > 0) {
					this.lsMonster.get(i).getLstProjectile().get(j).updateGameObject();
					this.lsMonster.get(i).getLstProjectile().get(j).drawGameObject();
				} else {
					this.lsMonster.get(i).getLstProjectile().remove(j);
				}
			}

		}

	}

	/**
	 * dessine les larmes
	 */
	void dessineLarme() {
		for (int i = 0; i < hero.getLstLarme().size(); i++) {
			if (hero.getLstLarme().get(i).getPortee() > 0) {
				hero.getLstLarme().get(i).updateGameObject();
				hero.getLstLarme().get(i).drawGameObject();
			} else {
				hero.getLstLarme().remove(i);
			}

		}
	}

	/**
	 * Convert a tile index to a 0-1 position.
	 * 
	 * @param indexX
	 * @param indexY
	 * @return
	 */
	static Vector2 positionFromTileIndex(int indexX, int indexY) {
		return new Vector2(indexX * RoomInfos.TILE_WIDTH + RoomInfos.HALF_TILE_SIZE.getX(),
				indexY * RoomInfos.TILE_HEIGHT + RoomInfos.HALF_TILE_SIZE.getY());
	}

	/**
	 * GETTERS SETTERS
	 */

	public List<GenericObstacle> getLsObstacle() {
		return lsObstacle;
	}

	public void setLsObstacle(List<GenericObstacle> lsObstacle) {
		this.lsObstacle = lsObstacle;
	}

	public List<Monster> getLsMonster() {
		return lsMonster;
	}

	public void setLsMonster(List<Monster> lsMonster) {
		this.lsMonster = lsMonster;
	}

	public String getType() {
		return this.type;
	}

	public Integer getId() {
		return this.id;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<Door> getLstPorte() {
		return this.lstPorte;
	}

	public void setLstPorte(List<Door> lstPorte) {
		this.lstPorte = lstPorte;
	}

	public Hero getHero() {
		return this.hero;
	}

	public void setHero(Hero hero) {
		this.hero = hero;
	}

	public List<GenericObject> getLstObjet() {
		return this.lstObjet;
	}

	public void setLstObjet(List<GenericObject> lstObjet) {
		this.lstObjet = lstObjet;
	}

	public boolean getAGagner() {
		return this.aGagner;
	}

	public void setAGagner(boolean aGagner) {
		this.aGagner = aGagner;
	}
}
