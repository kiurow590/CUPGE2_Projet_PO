package gameWorld;

import gameobjects.Hero;
import gameobjects.Spider;
import libraries.StdDraw;
import resources.Controls;

public class GameWorld {
	/**
	 * Attributs
	 */
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

	/**
	 * Methode qui mets le personnage en mouvement dans le salle
	 */
	public void processUserInput() {
		processKeysForMovement();
	}

	/**
	 * Methode qui retourn si le jeu est fini ou pas
	 * 
	 * @return boolean representant le game over
	 */
	public boolean gameOver() {
		return false;
	}

	/**
	 * Methode qui mets a jour la room
	 */
	public void updateGameObjects() {
		currentRoom.updateRoom();
	}

	/**
	 * Methode qui dessine la salle
	 */
	public void drawGameObjects() {
		currentRoom.drawRoom();
	}

	/*
	 * Keys processing
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
