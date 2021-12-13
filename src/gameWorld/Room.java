package gameWorld;

import gameobjects.Fly;
import gameobjects.Hero;
import gameobjects.Spider;
import libraries.Physics;
import libraries.StdDraw;
import libraries.Vector2;
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

		System.out.println(this.compteurInvincibiliteHero);
		if (this.compteurInvincibiliteHero == 0 && Physics.rectangleCollision(this.hero.getPosition(),
				this.hero.getSize(), this.spider.getPosition(), this.spider.getSize())) {
			this.hero.retirePV(this.spider.getDegatCorpsACorps());
			this.compteurInvincibiliteHero = 50;
		} else if (this.compteurInvincibiliteHero == 0 && Physics.rectangleCollision(this.hero.getPosition(),
				this.hero.getSize(), this.fly.getPosition(), this.fly.getSize())) {
			this.hero.retirePV(this.fly.getDegatCorpsACorps());
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
		StdDraw.setPenColor(StdDraw.GRAY);
		for (int i = 0; i < RoomInfos.NB_TILES; i++) {
			for (int j = 0; j < RoomInfos.NB_TILES; j++) {
				Vector2 position = positionFromTileIndex(i, j);
				StdDraw.filledRectangle(position.getX(), position.getY(), RoomInfos.HALF_TILE_SIZE.getX(),
						RoomInfos.HALF_TILE_SIZE.getY());
			}
		}
		hero.drawGameObject();
		spider.drawGameObject();
		fly.drawGameObject();
		for (int i = 0; i < hero.getLstLarme().size(); i++) {
			if (hero.getLstLarme().get(i).getPortee() > 0) {
			hero.getLstLarme().get(i).updateGameObject();
			System.out.println("Portee : "+hero.getLstLarme().get(i).getPortee());
			hero.getLstLarme().get(i).drawGameObject();
			}else  {
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
}
