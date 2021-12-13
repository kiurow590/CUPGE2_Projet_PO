package gameWorld;

import java.util.ArrayList;

import gameobjects.Fly;
import gameobjects.Hero;
import gameobjects.Larme;
import gameobjects.Spider;
import libraries.StdDraw;
import libraries.Vector2;
import resources.Controls;
import resources.ImagePaths;
import resources.RoomInfos;

/**
 * 
 * classqui genere le monde
 *
 */
public class GameWorld {
	private Room currentRoom;
	private Hero hero;
	private Spider spider;
	private Fly fly;

	/**
	 * Constructeur de monde
	 * 
	 * @param hero hero present dans le monde
	 */
	public GameWorld(Hero hero, Spider spider, Fly fly) {
		this.hero = hero;
		this.spider = spider;
		this.fly = fly;
		currentRoom = new Room(hero, spider, fly);
	}

	public void processUserInput() {
		processKeysForMovement();
		processTire();
	}

	public boolean gameOver() {
		return hero.getpV() <= 0;
	}

	public void updateGameObjects() {
		currentRoom.updateRoom();
	}

	public void drawGameObjects() {
		currentRoom.drawRoom();
	}

	/**
	 * Keys processing. <br/>
	 * Managed <i>keys</i> :
	 * <ul>
	 * <li>key UP</li>
	 * </ul>
	 */
	private void processKeysForMovement() {
		if (StdDraw.isKeyPressed(Controls.goUp) && hero.getPosition().getY() < 0.9) {
			hero.goUpNext();

		}
		if (StdDraw.isKeyPressed(Controls.goDown) && hero.getPosition().getY() > 0.1) {
			hero.goDownNext();

		}
		if (StdDraw.isKeyPressed(Controls.goRight) && hero.getPosition().getX() < 0.9) {
			hero.goRightNext();

		}
		if (StdDraw.isKeyPressed(Controls.goLeft) && hero.getPosition().getX() > 0.1) {
			hero.goLeftNext();

		}

		if (StdDraw.isKeyPressed(Controls.invincible)) {
			hero.modeInvincible();

		}

	}

	public void processTire() {
		if (StdDraw.isKeyPressed(Controls.hitUp)) {
			Larme e = new Larme(hero.getPosition(), RoomInfos.TILE_SIZE.scalarMultiplication(0.2), ImagePaths.TEAR,
					0.01, new Vector2(0, 1), 40);

			hero.creeLarme(e);
			System.out.println("Tire");

		}
		if (StdDraw.isKeyPressed(Controls.hitDown)) {
			Larme e = new Larme(hero.getPosition(), RoomInfos.TILE_SIZE.scalarMultiplication(0.2), ImagePaths.TEAR,
					0.01, new Vector2(0, -1), 40);

			hero.creeLarme(e);
			System.out.println("Tire");

		}
		if (StdDraw.isKeyPressed(Controls.hitLeft)) {
			Larme e = new Larme(hero.getPosition(), RoomInfos.TILE_SIZE.scalarMultiplication(0.2), ImagePaths.TEAR,
					0.01, new Vector2(-1, 0), 40);

			hero.creeLarme(e);
			System.out.println("Tire");
		}
		if (StdDraw.isKeyPressed(Controls.hitRight)) {

			Larme e = new Larme(hero.getPosition(), RoomInfos.TILE_SIZE.scalarMultiplication(0.2), ImagePaths.TEAR,
					0.01, new Vector2(1, 0), 40);

			hero.creeLarme(e);
			System.out.println("Tire");
		}
	}

}
