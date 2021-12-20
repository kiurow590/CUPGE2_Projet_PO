package gameWorld;

import java.util.ArrayList;
import java.util.List;

import gameobjects.BloodOfMartyr;
import gameobjects.Coeur;
import gameobjects.CoeurSup;
import gameobjects.Fly;
import gameobjects.Hero;
import gameobjects.Monstre;
import gameobjects.Objets;
import gameobjects.Piece;
import gameobjects.Spider;
import libraries.Physics;
import libraries.StdDraw;
import libraries.Vector2;
import resources.ImagePaths;
import resources.RoomInfos;

public class Room {

	/**
	 * attributs
	 */
	private Hero hero;

	private int compteurInvincibiliteHero;

	private List<Monstre> lsMonster;

	private Objets objet;

	/**
	 * Constructeur de room
	 * 
	 * @param hero personnage de la room
	 */
	public Room(Hero hero) {
		this.hero = hero;
		this.lsMonster = new ArrayList<Monstre>();
		this.compteurInvincibiliteHero = 10;

		initMonster();

		double objectRandom = Math.random();

		if (objectRandom < 0.35) {
			double randomPiece = Math.random();
			if (randomPiece < 0.45) {
				this.objet = new Piece(1, RoomInfos.POSITION_CENTER_OF_ROOM);
			} else if (randomPiece >= 0.45 && randomPiece < 0.8) {
				this.objet = new Piece(5, RoomInfos.POSITION_CENTER_OF_ROOM);

			} else if (randomPiece >= 0.8) {
				this.objet = new Piece(10, RoomInfos.POSITION_CENTER_OF_ROOM);

			}
		} else if (objectRandom >= 0.35 && objectRandom < 0.75) {
			double randomCoeur = Math.random();

			if (randomCoeur < 0.6) {
				this.objet = new Coeur(1, RoomInfos.POSITION_CENTER_OF_ROOM);

			} else if (randomCoeur >= 0.6) {
				this.objet = new Coeur(2, RoomInfos.POSITION_CENTER_OF_ROOM);

			}
		} else if (objectRandom >= 0.75 && objectRandom < 0.875) {
			this.objet = new BloodOfMartyr(new Vector2(5, 5));
		} else if (objectRandom >= 0.875) {
			this.objet = new CoeurSup(new Vector2(5, 5));
		}
	}

	/**
	 * Methode qui initialise le nb de monstre au demarrage de la room
	 */
	private void initMonster() {
		for (int i = 0; i < 4; i++) {
			if (Math.random() < 0.5) {
				this.lsMonster.add(new Spider(new Vector2(Math.random(), Math.random()),
						RoomInfos.TILE_SIZE.scalarMultiplication(0.4), ImagePaths.SPIDER, 0.02, new Vector2(), 5, 1,
						40));
			} else {
				this.lsMonster.add(new Fly(new Vector2(Math.random(), Math.random()),
						RoomInfos.TILE_SIZE.scalarMultiplication(0.4), ImagePaths.FLY, 0.005, new Vector2(), 5, 1));

			}
		}
	}

	/*
	 * Make every entity that compose a room process one step
	 */
	public void updateRoom() {
		makeHeroPlay();
		makeMonsterPlay();
		collisionReport();
		rammasseMonstreMort();
		nettoyageLarme();
		nettoyageProj();

	}

	/**
	 * Affiche l'objet en récompense de la salle
	 */
	public void affichageObjets() {

		if (this.lsMonster.size() == 0) {

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

	}

	/**
	 * Methode qui gere la collision entre monstre et le Hero
	 * 
	 * @param monstre monstre avec lequel la colision est possible
	 */
	private void collisionHero(Monstre monstre) {
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
	private void collisionLarme(Monstre monstre) {
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
	public void collisionProjectileFly(Monstre monstre) {
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

	/**
	 * met a jour le hero
	 */
	private void makeHeroPlay() {
		hero.updateGameObject();
	}

	/**
	 * met a jour le monstre
	 */
	private void makeMonsterPlay() {

		for (int i = 0; this.lsMonster != null && i < this.lsMonster.size(); i++) {
			this.lsMonster.get(i).updateGameObject(this.hero, lsMonster);
		}

	}

	/*
	 * Drawing
	 */
	public void drawRoom() {
		// For every tile, set background color.
		StdDraw.setPenColor(StdDraw.GRAY);
		for (int i = 0; i < RoomInfos.NB_TILES; i++) {
			for (int j = 0; j < RoomInfos.NB_TILES; j++) {
				Vector2 position = positionFromTileIndex(i, j);
				StdDraw.filledRectangle(position.getX(), position.getY(), RoomInfos.HALF_TILE_SIZE.getX(),
						RoomInfos.HALF_TILE_SIZE.getY());
			}
		}
		hero.drawGameObject();
		dessineMonstre();
		dessineLarme();

		affichageObjets();

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

	public List<Monstre> getLsMonster() {
		return lsMonster;
	}

	public void setLsMonster(List<Monstre> lsMonster) {
		this.lsMonster = lsMonster;
	}

}
