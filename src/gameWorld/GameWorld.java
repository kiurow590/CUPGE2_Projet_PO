package gameWorld;

import gameobjects.Hero;
import gameobjects.Spider;
import libraries.StdDraw;
import resources.Controls;

/**
 * 
 * classqui genere le monde
 *
 */
public class GameWorld {
	private Room currentRoom;
	private Hero hero;
	private Spider spider;

	/**
	 * Constructeur de monde
	 * 
	 * @param hero hero present dans le monde
	 */
	public GameWorld(Hero hero, Spider spider) {
		this.hero = hero;
		this.spider = spider;
		currentRoom = new Room(hero, spider);
	}

	public void processUserInput() {
		processKeysForMovement();
	}

	public boolean gameOver() {
		return false;
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
		if (StdDraw.isKeyPressed(Controls.goLeft) && hero.getPosition().getX() > 0.1 ) {
			hero.goLeftNext();

		}
	}
}
