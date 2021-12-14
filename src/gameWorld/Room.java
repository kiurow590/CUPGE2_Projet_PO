package gameWorld;

import java.util.List;

import gameobjects.Fly;
import gameobjects.Hero;
import gameobjects.Monstre;
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

	private int compteurInvincibiliteHero;

	private List<Monstre> lsMonster;

	/**
	 * Constructeur de room
	 * 
	 * @param hero personnage de la room
	 */
	public Room(Hero hero, List<Monstre> lsMonster) {
		this.hero = hero;
		this.lsMonster = lsMonster;
		this.compteurInvincibiliteHero = 10;
	}

	/*
	 * Make every entity that compose a room process one step
	 */
	public void updateRoom() {
		makeHeroPlay();
		makeMonsterPlay();
		collisionReport();
		rammasseMonstreMort();

	}

	public void rammasseMonstreMort() {
		for (int i = 0; i < this.lsMonster.size(); i++) {
			if (this.lsMonster.get(i).getPtDeVie() <= 0) {

				this.lsMonster.remove(i);
			}
		}

	}

	public void collisionReport() {

		for (int i = 0; i < this.lsMonster.size(); i++) {

			if (this.compteurInvincibiliteHero == 0 && Physics.rectangleCollision(this.hero.getPosition(),
					this.hero.getSize(), this.lsMonster.get(i).getPosition(), this.lsMonster.get(i).getSize())) {
				this.hero.retirePV(this.lsMonster.get(i).getDegatCorpsACorps());
				this.compteurInvincibiliteHero = 50;
			} else if (this.compteurInvincibiliteHero != 0) {
				this.compteurInvincibiliteHero--;
			}

			for (int j = 0; j < hero.getLstLarme().size(); j++) {
				try {
					if (Physics.rectangleCollision(this.hero.getLstLarme().get(j).getPosition(),
							this.hero.getLstLarme().get(j).getSize(), this.lsMonster.get(j).getPosition(),
							this.lsMonster.get(j).getSize())) {
						this.lsMonster.get(j).retirePV(this.hero.getLstLarme().get(j).getDegats());
						this.hero.getLstLarme().remove(j);

					}
				} catch (Exception e) {
					// TODO: handle exception
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

		for (int i = 0; i < this.lsMonster.size(); i++) {
			this.lsMonster.get(i).updateGameObject(this.hero);
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
		for (int i = 0; i < this.lsMonster.size(); i++) {
			this.lsMonster.get(i).drawGameObject();
		}

		for (int i = 0; i < hero.getLstLarme().size(); i++) {
			if (hero.getLstLarme().get(i).getPortee() > 0) {
				hero.getLstLarme().get(i).updateGameObject();
				System.out.println("Portee : " + hero.getLstLarme().get(i).getPortee());
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
}
