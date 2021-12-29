package gameWorld.rooms;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import gameWorld.rooms.portes.Door;
import gameobjects.objets.GenericObject;
import gameobjects.objets.consommables.Life;
import gameobjects.objets.consommables.Coin;
import gameobjects.objets.passifs.BloodOfMartyr;
import gameobjects.objets.passifs.LifeExtension;
import gameobjects.obstacles.GenericObstacle;
import gameobjects.personnages.Hero;
import gameobjects.personnages.monstres.Fly;
import gameobjects.personnages.monstres.Monster;
import gameobjects.personnages.monstres.Spider;
import libraries.Physics;
import libraries.StdDraw;
import libraries.Vector2;
import resources.ImagePaths;
import resources.RoomInfos;

public class Room {

	/**
	 * attributs
	 */
	Hero hero;

	String type;

	Color bgColor;

	Integer id;

	List<Door> lstPorte;

	private int compteurInvincibiliteHero;

	private List<Monster> lsMonster;
	private GenericObstacle obstacle;

	private GenericObject objet;

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

		// initMonster();
		// initObjectGift();
	}

	/*
	 * Make every entity that compose a room process one step
	 */
	public void updateRoom() {
		makeHeroPlay();
		/*
		 * makeMonsterPlay();
		 * 
		 * collisionReport();
		 * rammasseMonstreMort();
		 * nettoyageLarme();
		 * nettoyageProj();
		 */
	}

	/*
	 * Drawing
	 */
	public void drawRoom() {
		// For every tile, set background color.
		StdDraw.setPenColor(this.bgColor);
		for (int i = 0; i < RoomInfos.NB_TILES; i++) {
			for (int j = 0; j < RoomInfos.NB_TILES; j++) {
				Vector2 position = positionFromTileIndex(i, j);
				StdDraw.filledRectangle(position.getX(), position.getY(), RoomInfos.HALF_TILE_SIZE.getX(),
						RoomInfos.HALF_TILE_SIZE.getY());
			}
		}

		dessinePorte();
		this.drawWall();

		hero.drawGameObject();
		dessinePorte();

		// dessineMonstre();
		// dessineLarme();
		// affichageViePiece();

		// affichageObjets();
	}

	private void dessinePorte() {
		for (int i = 0; i < lstPorte.size(); i++) {
			lstPorte.get(i).drawGameObject();
		}
	}

	/**
	 * Methode qui génère une objet random en respectant les probabilité
	 * d'apparition
	 */
	public void initObjectGift() {
		double objectRandom = Math.random();

		if (objectRandom < 0.35) {
			double randomPiece = Math.random();
			if (randomPiece < 0.45) {
				this.objet = new Coin(1, RoomInfos.POSITION_CENTER_OF_ROOM);
			} else if (randomPiece >= 0.45 && randomPiece < 0.8) {
				this.objet = new Coin(5, RoomInfos.POSITION_CENTER_OF_ROOM);

			} else if (randomPiece >= 0.8) {
				this.objet = new Coin(10, RoomInfos.POSITION_CENTER_OF_ROOM);

			}
		} else if (objectRandom >= 0.35 && objectRandom < 0.75) {
			double randomCoeur = Math.random();

			if (randomCoeur < 0.6) {
				this.objet = new Life(1, RoomInfos.POSITION_CENTER_OF_ROOM);

			} else if (randomCoeur >= 0.6) {
				this.objet = new Life(2, RoomInfos.POSITION_CENTER_OF_ROOM);

			}
		} else if (objectRandom >= 0.75 && objectRandom < 0.875) {
			this.objet = new BloodOfMartyr(new Vector2(5, 5));
		} else if (objectRandom >= 0.875) {
			this.objet = new LifeExtension(new Vector2(5, 5));
		}

	}

	private void drawWall() {

		// on construit les murs sur le coté
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
	private void makeHeroPlay() {
		hero.updateGameObject();
	}

	/**
	 * Methode qui initialise le nb de monstre au demarrage de la room
	 */
	private void initMonster() {
		for (int i = 0; i < 4; i++) {
			if (Math.random() < 0.5) {
				this.lsMonster.add(new Spider(new Vector2(Math.random(), Math.random())));
			} else {
				this.lsMonster.add(new Fly(new Vector2(Math.random(), Math.random())));

			}
		}
	}

	/**
	 * Affiche l'objet en récompense de la salle
	 */
	public void affichageObjets() {

		if (this.lsMonster.size() == 0 && objet.isEstRamasser() == false) {

			objet.drawGameObject();

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
	public void nettoyageProj() {
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
	 * Methode qui gere les collision entre differente entité
	 */
	public void collisionReport() {

		// Pour chaque monstre (vivant ou mort)
		for (int i = 0; this.lsMonster != null && i < this.lsMonster.size(); i++) {

			collisionHero(this.lsMonster.get(i));
			collisionLarme(this.lsMonster.get(i));
			collisionProjectileFly(this.lsMonster.get(i));

		}

		if (this.lsMonster.size() == 0 && Physics.rectangleCollision(hero.getPosition(), hero.getSize(),
				objet.getPosition(), objet.getSize())) {
			objet.updateHeroPerf(hero);
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
			this.hero.retirePV(monstre.getDegatCorpsACorps());
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
					this.hero.getLstLarme().get(j).getSize(), monstre.getPosition(),
					monstre.getSize())) {
				monstre.retirePV(this.hero.getLstLarme().get(j).getDegats());
				this.hero.getLstLarme().get(j).setPortee(0);
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

		for (int j = 0; !monstre.getLstProjectile().isEmpty()
				&& j < monstre.getLstProjectile().size(); j++) {
			if (Physics.rectangleCollision(this.hero.getPosition(), this.hero.getSize(),
					monstre.getLstProjectile().get(j).getPosition(),
					monstre.getLstProjectile().get(j).getSize())) {
				this.hero.retirePV(monstre.getLstProjectile().get(j).getDegats());
				monstre.getLstProjectile().get(j).setPortee(0);
			}
		}
	}

	/*
	 * public void collisionObstacle() {
	 * // Gestion des collision avec les rochers
	 * if (Physics.rectangleCollision(this.hero.getPosition(),
	 * this.hero.getSize(), obstacle.getPosition(), obstacle.getSize())) {
	 * // hero.position=new Vector2(0,0);
	 * }
	 * for (int i = 0; i < lsMonster.size(); i++) {
	 * // on evite les mouches car elles non pas de collision avec les rochers
	 * if (this.lsMonster.get(i) instanceof Fly == false) {
	 * if (Physics.rectangleCollision(this.lsMonster.get(i).getPosition(),
	 * this.hero.getSize(), obstacle.getPosition(), obstacle.getSize())) {
	 * }
	 * }
	 * }
	 * }
	 */

	/**
	 * met a jour le monstre
	 */
	private void makeMonsterPlay() {

		for (int i = 0; this.lsMonster != null && i < this.lsMonster.size(); i++) {
			this.lsMonster.get(i).updateGameObject(this.hero, lsMonster);
		}

	}

	public void affichageViePiece() {
		// Affichage nb piece
		StdDraw.picture(0.75, 0.9, ImagePaths.DIME, RoomInfos.TILE_SIZE.scalarMultiplication(0.4).getX(),
				RoomInfos.TILE_SIZE.scalarMultiplication(0.4).getY());
		StdDraw.setPenRadius();
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.text(0.8, 0.9, ": " + hero.getStackArgent() + "");
		// ----------------------------------------------------------------
		StdDraw.setPenRadius();
		int pvView = this.hero.getPV();
		if (pvView % 2 == 0) {
			double x = 0.1;
			while (pvView != 0) {
				StdDraw.picture(x, 0.9, ImagePaths.HEART_HUD, RoomInfos.TILE_SIZE.scalarMultiplication(0.5).getX(),
						RoomInfos.TILE_SIZE.scalarMultiplication(0.5).getY());

				x += 0.1;
				pvView -= 2;
			}

		} else {
			double x = 0.1;
			while (pvView != 1) {
				StdDraw.picture(x, 0.9, ImagePaths.HEART_HUD, RoomInfos.TILE_SIZE.scalarMultiplication(0.5).getX(),
						RoomInfos.TILE_SIZE.scalarMultiplication(0.5).getY());

				x += 0.1;
				pvView -= 2;

			}

			StdDraw.picture(x, 0.9, ImagePaths.HALF_HEART_HUD, RoomInfos.TILE_SIZE.scalarMultiplication(0.5).getX(),
					RoomInfos.TILE_SIZE.scalarMultiplication(0.5).getY());
		}
	}

	/**
	 * Methode qui dessine les monstre
	 */
	private void dessineMonstre() {

		for (int i = 0; this.lsMonster != null && i < this.lsMonster.size(); i++) {
			this.lsMonster.get(i).drawGameObject();
			if (this.lsMonster.get(i) instanceof Fly) {
				Fly f = (Fly) this.lsMonster.get(i);
				for (int j = 0; j < f.getLstProjectile().size(); j++) {
					if (f.getLstProjectile().get(j).getPortee() > 0) {
						f.getLstProjectile().get(j).updateGameObject();
						f.getLstProjectile().get(j).drawGameObject();
					} else {
						f.getLstProjectile().remove(j);
					}
				}
			}

		}

	}

	/**
	 * Methode qui dessine les larmes
	 */
	private void dessineLarme() {
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
	private static Vector2 positionFromTileIndex(int indexX, int indexY) {
		return new Vector2(indexX * RoomInfos.TILE_WIDTH + RoomInfos.HALF_TILE_SIZE.getX(),
				indexY * RoomInfos.TILE_HEIGHT + RoomInfos.HALF_TILE_SIZE.getY());
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

	public GenericObject getObjet() {
		return this.objet;
	}

	public void setObjet(GenericObject objet) {
		this.objet = objet;
	}

}
