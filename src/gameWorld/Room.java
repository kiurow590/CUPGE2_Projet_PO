package gameWorld;

import java.util.ArrayList;
import java.util.List;

import gameobjects.Fly;
import gameobjects.Hero;
import gameobjects.Monstre;
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

		for (int i = 0; this.lsMonster != null && i < this.lsMonster.size(); i++) {

			if (this.compteurInvincibiliteHero == 0 && Physics.rectangleCollision(this.hero.getPosition(),
					this.hero.getSize(), this.lsMonster.get(i).getPosition(), this.lsMonster.get(i).getSize())) {
				this.hero.retirePV(this.lsMonster.get(i).getDegatCorpsACorps());
				this.compteurInvincibiliteHero = 50;
				this.lsMonster.get(i).setImmobilus(35);
			} else if (this.compteurInvincibiliteHero > 0) {
				this.compteurInvincibiliteHero--;
			}

			for (int j = 0; j < hero.getLstLarme().size(); j++) {

				if (Physics.rectangleCollision(this.hero.getLstLarme().get(j).getPosition(),
						this.hero.getLstLarme().get(j).getSize(), this.lsMonster.get(i).getPosition(),
						this.lsMonster.get(i).getSize())) {
					this.lsMonster.get(i).retirePV(this.hero.getLstLarme().get(j).getDegats());
					this.hero.getLstLarme().get(j).setPortee(0);
				}

			}

			if (this.lsMonster.get(i) instanceof Fly) {
				Fly f = (Fly) this.lsMonster.get(i);
				for (int j = 0; j < f.getLstProjectile().size(); j++) {
					if (Physics.rectangleCollision(this.hero.getPosition(), this.hero.getSize(),
							f.getLstProjectile().get(j).getPosition(), f.getLstProjectile().get(j).getSize())) {
						this.hero.retirePV(f.getLstProjectile().get(j).getDegats());
						f.getLstProjectile().get(j).setPortee(0);
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
