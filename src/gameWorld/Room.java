package gameWorld;

import gameobjects.Fly;
import gameobjects.Hero;
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

	private Spider spider;

	private Fly fly;

	private int compteurInvincibiliteHero;

	/**
	 * Constructeur de room
	 * 
	 * @param hero personnage de la room
	 */
	public Room(Hero hero, Spider spider, Fly fly) {
		this.hero = hero;
		this.spider = spider;
		this.fly = fly;
		this.compteurInvincibiliteHero = 10;
	}

	/*
	 * Make every entity that compose a room process one step
	 */
	public void updateRoom() {
		makeHeroPlay();
		makeMonsterPlay();
		collisionReport();

	}

	public void collisionReport() {
		System.out.println(Physics.rectangleCollision(this.hero.getPosition(), this.hero.getSize(),
				this.spider.getPosition(), this.spider.getSize()));

		System.out.println(Physics.rectangleCollision(this.hero.getPosition(), this.hero.getSize(),
				this.fly.getPosition(), this.fly.getSize()));
		System.out.println(this.compteurInvincibiliteHero);
		if (this.compteurInvincibiliteHero == 0 && Physics.rectangleCollision(this.hero.getPosition(),
				this.hero.getSize(), this.spider.getPosition(), this.spider.getSize())) {
			this.hero.retirePV(this.spider.getDegatCorpsACorps());
			System.out.println(this.hero.getpV());
			this.compteurInvincibiliteHero = 50;
		} else if (this.compteurInvincibiliteHero == 0 && Physics.rectangleCollision(this.hero.getPosition(),
				this.hero.getSize(), this.fly.getPosition(), this.fly.getSize())) {
			this.hero.retirePV(this.fly.getDegatCorpsACorps());
			System.out.println(this.hero.getpV());
			this.compteurInvincibiliteHero = 50;
		} else if (this.compteurInvincibiliteHero != 0) {
			this.compteurInvincibiliteHero--;
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
		spider.updateGameObject();
		fly.updateGameObject(this.hero);
	}

	/*
	 * Drawing
	 */
	public void drawRoom() {

		// For every tile, set background color.
		// StdDraw.setPenColor(StdDraw.BLUE);
		// on construit les murs sur le coté
		for (

				int colone = 0; colone < RoomInfos.NB_TILES; colone++) {

			if (colone == 1 | colone == 0.0) {
				for (double j = 0; j < RoomInfos.NB_TILES; j = j + 0.1) {
					StdDraw.picture(colone, j, ImagePaths.MUR, 0.1, 0.1, 90);
				}
			}
			// On contruit les mur du haut et du bas de la room
			for (int j = 0; j < RoomInfos.NB_TILES; j++) {
				if (j == 0.0 | j == 1) {
					for (double i = 0; i < RoomInfos.NB_TILES; i = i + 0.1) {
						StdDraw.picture(i, j, ImagePaths.MUR, 0.1, 0.1);
//			if ((j == 0 && i == 0) || (j == 1 && i == 1)) {
//						StdDraw.picture(0.9, 0.1, ImagePaths.MUR_angle, 0.1, 0.1, 180);
//					}

					}

				}
				//this.geneSol();
				Vector2 position = positionFromTileIndex(colone, j);
				// StdDraw.filledRectangle(position.getX(), position.getY(),
				// RoomInfos.HALF_TILE_SIZE.getX(),
				// RoomInfos.HALF_TILE_SIZE.getY());
			}
		}
		hero.drawGameObject();
		spider.drawGameObject();
		fly.drawGameObject();
	}

	public void geneSol() {
		// fonction de generation de sol aleatoirement entre plusieur image possible
		for (double y = 0.1; y < 0.9; y = y + 0.1) {
			for (double x = 0.1; x < 0.9; x = x + 0.1) {
				double tempRandom = Math.random() * (4 - 0);
				String sol = "";
				if (tempRandom < 1) {
					sol = ImagePaths.SOL_milieux1;
				} else if (tempRandom >= 1 && (tempRandom < 2)) {
					sol = ImagePaths.SOL_milieux2;
				} else if (tempRandom >= 2 && tempRandom < 3) {
					sol = ImagePaths.SOL_milieux3;
				} else if (tempRandom >= 3 && tempRandom < 4) {
					sol = ImagePaths.SOL_milieux4;
				}
				StdDraw.picture(x, y, sol, 0.1, 0.1);
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
}
