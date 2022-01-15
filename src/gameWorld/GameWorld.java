package gameWorld;

import java.util.HashMap;
import java.util.Map;

import gameWorld.rooms.Etage;
import gameWorld.rooms.Room;
import gameWorld.rooms.portes.BottomDoor;
import gameWorld.rooms.portes.Door;
import gameWorld.rooms.portes.LeftDoor;
import gameWorld.rooms.portes.RightDoor;
import gameWorld.rooms.portes.TopDoor;
import gameobjects.personnages.Hero;
import libraries.Physics;
import libraries.StdDraw;
import libraries.Vector2;
import resources.Controls;
import resources.ImagePaths;

/**
 * 
 * classqui genere le monde
 *
 */
public class GameWorld {
	private Room currentRoom;
	private Hero hero;

	Map<Integer, Room> mapDeRoom = new HashMap<>();

	/**
	 * Constructeur de monde
	 * 
	 * @param hero hero present dans le monde
	 */
	public GameWorld(Hero hero) {
		this.hero = hero;
		// generation de la map
		mapDeRoom = new Etage(mapDeRoom, hero).getMapDeRoom();

		currentRoom = mapDeRoom.get(0);
	}

	/**
	 * gere les entree de l'utilisateur
	 */
	public void processUserInput() {
		processKeysForMovement();
		processTire();
		cheatCode();
		changeCurrentRoom();
	}

	/**
	 * permet de modifier la room lors du passage d'un porte
	 */
	private void changeCurrentRoom() {

		if (currentRoom.getLsMonster().isEmpty()) {

			for (Door door : currentRoom.getLstPorte()) {

				if (Physics.rectangleCollision(hero.getPosition(), hero.getSize(),
						door.getPosition(), door.getSize())) {

					if (door instanceof BottomDoor) {
						hero.setPosition(new Vector2(0.5, 0.78));
					} else if (door instanceof TopDoor) {
						hero.setPosition(new Vector2(0.5, 0.22));
					} else if (door instanceof LeftDoor) {
						hero.setPosition(new Vector2(0.84, 0.5));
					} else if (door instanceof RightDoor) {
						hero.setPosition(new Vector2(0.16, 0.5));
					}
					currentRoom = mapDeRoom.get(door.getIdSalle());
				}
			}

		}

	}

	/**
	 * gestion CheatCode
	 */
	private void cheatCode() {
		if (StdDraw.isKeyPressed(Controls.invincible)) {
			hero.modeInvincible();
		}
		if (StdDraw.isKeyPressed(Controls.vitesse)) {
			hero.moderapide();
		}

		if (StdDraw.isKeyPressed(Controls.killMonster)) {

			for (int i = 0; currentRoom.getLsMonster() != null && i < currentRoom.getLsMonster().size(); i++) {
				currentRoom.getLsMonster().get(i).setPointVie(-800);

			}

		}

		if (StdDraw.isKeyPressed(Controls.puissance)) {

			hero.modePuissance();

		}
		if (StdDraw.isKeyPressed(Controls.addMoney)) {

			hero.addArgentTriche();

		}
		if (StdDraw.isKeyPressed(Controls.numberOne)) {
			if(hero.numberOne==true) {
				hero.numberOne=false;
			}
			hero.numberOne=true;
			
		}
	}

	/**
	 * gere si le jeu est perdu ou pas
	 * 
	 * @return </br>
	 * 
	 *         <ul>
	 *         <li>true -> la partie est perdu</li>
	 *         <li>false -> la partie est pas encore perdu ou gagner</li>
	 *         </ul>
	 */
	public boolean gameOver() {
		return hero.getpointVie() <= 0;
	}

	/**
	 * Methode mettant a jour la room
	 */
	public void updateGameObjects() {
		currentRoom.updateRoom();
	}

	/**
	 * Methode dessinant la room
	 */
	public void drawGameObjects() {
		currentRoom.drawRoom();
	}

	/**
	 * gère les entre pour mettre en mouvement le personnage <br/>
	 * Managed <i>keys</i> :
	 * <ul>
	 * <li>key UP</li>
	 * <li>key Down</li>
	 * <li>key Left</li>
	 * <li>key Right</li>
	 * </ul>
	 */
	private void processKeysForMovement() {
		if (StdDraw.isKeyPressed(Controls.goUp) && hero.getPosition().getY() < 0.85) {
			hero.goUpNext();

		}
		if (StdDraw.isKeyPressed(Controls.goDown) && hero.getPosition().getY() > 0.2) {
			hero.goDownNext();

		}
		if (StdDraw.isKeyPressed(Controls.goRight) && hero.getPosition().getX() < 0.85) {
			hero.goRightNext();

		}
		if (StdDraw.isKeyPressed(Controls.goLeft) && hero.getPosition().getX() > 0.15) {
			hero.goLeftNext();

		}

	}

	/**
	 * gère les entre pour mettre tiré une larme <br/>
	 * Managed <i>keys</i> :
	 * <ul>
	 * <li>key UP</li>
	 * <li>key Down</li>
	 * <li>key Left</li>
	 * <li>key Right</li>
	 * </ul>
	 */
	public void processTire() {
		if (StdDraw.isKeyPressed(Controls.hitUp)) {

			hero.creeLarme(hero.getPosition(), ImagePaths.TEAR, new Vector2(0, 1));

		}
		if (StdDraw.isKeyPressed(Controls.hitDown)) {

			hero.creeLarme(hero.getPosition(),
					ImagePaths.TEAR,
					new Vector2(0, -1));

		}
		if (StdDraw.isKeyPressed(Controls.hitLeft)) {

			hero.creeLarme(hero.getPosition(),
					ImagePaths.TEAR,
					new Vector2(-1, 0));
		}
		if (StdDraw.isKeyPressed(Controls.hitRight)) {

			hero.creeLarme(hero.getPosition(),
					ImagePaths.TEAR, new Vector2(1, 0));
		}
	}

	/*
	 * Getters Setters
	 */

	public Room getCurrentRoom() {
		return this.currentRoom;
	}

	public void setCurrentRoom(Room currentRoom) {
		this.currentRoom = currentRoom;
	}

}
