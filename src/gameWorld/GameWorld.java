package gameWorld;

import gameobjects.Hero;
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

	// A world needs a hero
	public GameWorld(Hero hero) {
		this.hero = hero;
		currentRoom = new Room(hero);
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
		if (StdDraw.isKeyPressed(Controls.goUp)) {
			hero.goUpNext();
		}
		if (StdDraw.isKeyPressed(Controls.goDown)) {
			hero.goDownNext();
		}
		if (StdDraw.isKeyPressed(Controls.goRight)) {
			hero.goRightNext();
		}
		if (StdDraw.isKeyPressed(Controls.goLeft)) {
			hero.goLeftNext();
		}
	}
}
