package gameWorld.rooms;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import gameWorld.rooms.portes.Door;
import gameobjects.objets.GenericObject;
import gameobjects.objets.consommables.Coin;
import gameobjects.objets.consommables.Life;
import gameobjects.objets.passifs.BloodOfMartyr;
import gameobjects.objets.passifs.LifeExtension;
import gameobjects.obstacles.GenericObstacle;
import gameobjects.obstacles.Rock;
import gameobjects.obstacles.Poop;
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

public abstract class Room {

	/**
	 * attributs
	 */
	Hero hero;

	String type;

	Color bgColor;

	Integer id;

	List<Door> lstPorte;

	int compteurInvincibiliteHero;

	List<Monster> lsMonster;

	List<GenericObject> lstObjet;

	List<GenericObstacle> lsObstacle;

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

		this.bgColor = StdDraw.GRAY;

		this.lstPorte = new ArrayList<>();

		this.lstObjet = new ArrayList<>();

		this.aGagner = false;

		this.lsObstacle = new ArrayList<>();
	}

	/*
	 * Make every entity that compose a room process one step
	 */
	public abstract void updateRoom();

	/*
	 * Drawing
	 */
	public abstract void drawRoom();

	public void dessinePorte() {

		for (int i = 0; i < lstPorte.size(); i++) {
			if (lsMonster.isEmpty()) {
				lstPorte.get(i).setImagePaths(ImagePaths.OPENED_DOOR);
			}
			lstPorte.get(i).drawGameObject();
		}
	}

	/**
	 * Methode qui gÃ©nÃ¨re une objet random en respectant les probabilitÃ©
	 * d'apparition
	 */
	public GenericObject initObjectGift() {
		double objectRandom = Math.random();
		GenericObject objectReturn;
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
			objectReturn = new BloodOfMartyr(new Vector2(5, 5));
		} else {
			objectReturn = new LifeExtension(new Vector2(5, 5));
		}

		return objectReturn;

	}

	void drawWall() {

		// on construit les murs sur le cotÃ©
		for (int colone = 0; colone < RoomInfos.NB_TILES; colone++) {

			if (colone == 1 | colone == 0.0) {
				for (double j = 0; j < RoomInfos.NB_TILES; j = j + 0.5) {
					StdDraw.picture(colone, j, ImagePaths.WALL, 0.7, 0.1, 90);
				}
			}
			// On contruit les mur du haut et du bas de la room
			for (int j = 0; j < RoomInfos.NB_TILES; j++) {
				if (j == 0.0 | j == 1) {
					for (double i = 0; i < RoomInfos.NB_TILES; i = i + 0.5) {
						StdDraw.picture(i, j, ImagePaths.WALL, 0.7, 0.1);
					}

				}

			}
		}

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
	 * Methode qui initialise le nb de monstre au demarrage de la room
	 */
	void initMonster() {
		for (int i = 0; i < 4; i++) {
			// on evite que un monstre spawn sur un obstacle
			// ainsi lors de la génération on fait en sorte que la position du monstre
			// n'est jamais la même que celui d'un obstacle.
			double x = Math.random();
			double y = Math.random();
			boolean jeSuisSurUnobstacle = false;
			while (jeSuisSurUnobstacle) {
				 x = Math.random();
				 y = Math.random();
				if (x < 0.08) {
					x += 0.2;
				}
				if (x > 0.92) {
					x -= 0.2;
				}
				if (y < 0.08) {
					y += 0.2;
				}
				if (y > 0.92) {
					y -= 0.2;

				}
				for (int numeroObstacle = 0; !lsObstacle.isEmpty()
						&& numeroObstacle < lsObstacle.size(); numeroObstacle++) {
					Vector2 vecteurTampon = new Vector2(x, y);
					if (!(lsObstacle.get(numeroObstacle).getPosition() == vecteurTampon)) {
						jeSuisSurUnobstacle = true;
					} else {
						jeSuisSurUnobstacle = false;
					}
				}
			}
			if (Math.random() < 0.5) {

				this.lsMonster.add(new Spider(new Vector2(x, y)));
			} else {
				this.lsMonster.add(new Fly(new Vector2(Math.random(), Math.random())));
			}

			
		}
	}

	/**
	 * Affiche l'objet en rÃ©compense de la salle
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
	 * Methode qui retire des listes tous les monstre qui sont supposer mort
	 */
	public void rammasseMonstreMort() {

		for (int i = 0; this.lsMonster != null && i < this.lsMonster.size(); i++) {
			if (this.lsMonster.get(i).isDead()) {

				this.lsMonster.remove(i);
			}
		}

	}

	/**
	 * Methode qui nettoie de l'afficheage les larme
	 */
	public void nettoyageLarme() {
		for (int k = 0; k < hero.getLstLarme().size(); k++) {

			if (this.hero.getLstLarme().get(k).getPortee() <= 0) {
				this.hero.getLstLarme().remove(k);

			}

		}
	}

	/**
	 * Methode qui nettoie de l'afficheage les larme
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
	 * Methode qui gere les collision entre differente entitÃ©
	 */
	void collisionReport() {

		// Pour chaque monstre (vivant ou mort)
		for (int i = 0; this.lsMonster != null && i < this.lsMonster.size(); i++) {

			collisionHero(this.lsMonster.get(i));
			collisionLarme(this.lsMonster.get(i));
			collisionProjectileFly(this.lsMonster.get(i));

		}
		collisionObstacle(this.lsObstacle);
		for (int i = 0; i < lstObjet.size(); i++) {

			if (this.lsMonster.size() == 0 && Physics.rectangleCollision(hero.getPosition(), hero.getSize(),
					lstObjet.get(i).getPosition(), lstObjet.get(i).getSize())) {
				lstObjet.get(i).updateHeroPerf(hero);
			}

		}

	}

	/**
	 * Methode qui gere la collision entre monstre et le Hero
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
	 * Methode qui gere la collision entre les monstre et les larmes
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
				System.out.println(monstre.getPointVie());
			}

		}

	}

	/**
	 * Methode qui gere la collision entre le hero est les projectile lancer par des
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
		}
	}

	public void collisionObstacle(List<GenericObstacle> lsObstacle) {
		// On décremante le potentielle compteur d'invisibilité du personnage si il a
		// touché des pikes
		if (this.compteurInvincibiliteHero > 0) {
			this.compteurInvincibiliteHero--;
		}
		// Gestion des collision avec les obstacles des monstre , on parcours tout les
		// obstacles et tout les monstres
		for (int numeroObstacles = 0; !lsObstacle.isEmpty() && numeroObstacles < lsObstacle.size(); numeroObstacles++) {
			for (int numeroMonstre = 0; !lsMonster.isEmpty() && numeroMonstre < lsMonster.size(); numeroMonstre++) {
				if (Physics.rectangleCollision(lsMonster.get(numeroMonstre).getPosition(),
						lsMonster.get(numeroMonstre).getSize(), lsObstacle.get(numeroObstacles).getPosition(),
						lsObstacle.get(numeroObstacles).getSize())) {
					if (lsObstacle.get(numeroObstacles) instanceof Poop
							|| lsObstacle.get(numeroObstacles) instanceof Rock) {
						if (lsMonster.get(numeroMonstre) instanceof Spider
								|| lsMonster.get(numeroMonstre) instanceof Boss) {
							lsMonster.get(numeroMonstre).setPosition(lsMonster.get(numeroMonstre).getLastposition());
						}
					}
				}
			}
			for (int numeroLarmeHero = 0; !this.hero.getLstLarme().isEmpty()
					&& numeroLarmeHero < this.hero.getLstLarme().size(); numeroLarmeHero++) {
				if (Physics.rectangleCollision(this.hero.getLstLarme().get(numeroLarmeHero).getPosition(),
						this.hero.getLstLarme().get(numeroLarmeHero).getSize(),
						lsObstacle.get(numeroObstacles).getPosition(), lsObstacle.get(numeroObstacles).getSize())) {
					if (lsObstacle.get(numeroObstacles) instanceof Rock) {
						this.hero.getLstLarme().remove(numeroLarmeHero);
					}
					if (lsObstacle.get(numeroObstacles) instanceof Poop) {
						lsObstacle.get(numeroObstacles).retirepointVie(this.hero.getdamage());
						this.hero.getLstLarme().remove(numeroLarmeHero);
					}
				}

			}
			// colision du hero avec les obstacles
			if (Physics.rectangleCollision(this.hero.getPosition(), this.hero.getSize(),
					lsObstacle.get(numeroObstacles).getPosition(), lsObstacle.get(numeroObstacles).getSize())) {

				if (lsObstacle.get(numeroObstacles) instanceof Spikes && this.compteurInvincibiliteHero == 0) {
					this.hero.retirepointVie(lsObstacle.get(numeroObstacles).getDegats());
					this.compteurInvincibiliteHero = 50;

				} else if (lsObstacle.get(numeroObstacles) instanceof Poop
						|| lsObstacle.get(numeroObstacles) instanceof Rock) {

					this.hero.setPosition(this.hero.getLastposition());

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
	 * Methode qui affiche les information du joueur
	 */
	public void affichageViePiece() {
		// Affichage nb piece
		StdDraw.picture(0.75, 0.9, ImagePaths.DIME, RoomInfos.TILE_SIZE.scalarMultiplication(0.4).getX(),
				RoomInfos.TILE_SIZE.scalarMultiplication(0.4).getY());
		StdDraw.setPenRadius();
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.text(0.8, 0.9, ": " + hero.getStackArgent() + "");
		// ----------------------------------------------------------------
		StdDraw.setPenRadius();
		int pointVieView = this.hero.getpointVie();
		if (pointVieView % 2 == 0) {
			double x = 0.1;
			while (pointVieView != 0) {
				StdDraw.picture(x, 0.9, ImagePaths.HEART_HUD, RoomInfos.TILE_SIZE.scalarMultiplication(0.5).getX(),
						RoomInfos.TILE_SIZE.scalarMultiplication(0.5).getY());

				x += 0.1;
				pointVieView -= 2;
			}

		} else {
			double x = 0.1;
			while (pointVieView != 1) {
				StdDraw.picture(x, 0.9, ImagePaths.HEART_HUD, RoomInfos.TILE_SIZE.scalarMultiplication(0.5).getX(),
						RoomInfos.TILE_SIZE.scalarMultiplication(0.5).getY());

				x += 0.1;
				pointVieView -= 2;

			}

			StdDraw.picture(x, 0.9, ImagePaths.HALF_HEART_HUD, RoomInfos.TILE_SIZE.scalarMultiplication(0.5).getX(),
					RoomInfos.TILE_SIZE.scalarMultiplication(0.5).getY());
		}
	}

	/**
	 * Methode qui dessine les monstre
	 */
	void dessineMonstre() {

		for (int i = 0; this.lsMonster != null && i < this.lsMonster.size(); i++) {
			this.lsMonster.get(i).drawGameObject();

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
	 * Methode qui dessine les larmes
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
