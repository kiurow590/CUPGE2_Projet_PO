package gameWorld;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import gameobjects.Fly;

import gameobjects.Hero;
import gameobjects.Larme;

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

	Map<Integer, Room> mapDeRoom = new HashMap<>();

	/**
	 * Constructeur de monde
	 * 
	 * @param hero hero present dans le monde
	 */
	public GameWorld(Hero hero) {
		this.hero = hero;

		// Generation des rooms
		Room spawn = new RoomSpawn(hero);
		Room monster1 = new RoomMonster(hero);

		mapDeRoom.put(spawn.getId(), spawn);
		mapDeRoom.put(monster1.getId(), monster1);
		mapDeRoom.get(spawn.getId()).getLstPorte().add(new PorteHaut(monster1.getId()));
		mapDeRoom.get(monster1.getId()).getLstPorte().add(new PorteBas(spawn.getId()));

		currentRoom = spawn;
	}

	/**
	 * Methode qui gere les entree de l'utilisateur
	 */
	public void processUserInput() {
		processKeysForMovement();
		processTire();
		cheatCode();
	}

	private void cheatCode() {
		if (StdDraw.isKeyPressed(Controls.invincible)) {
			hero.modeInvincible();
		}
		if (StdDraw.isKeyPressed(Controls.vitesse)) {
			hero.moderapide();
		}

		if (StdDraw.isKeyPressed(Controls.killMonster)) {

			for (int i = 0; currentRoom.getLsMonster() != null && i < currentRoom.getLsMonster().size(); i++) {
				currentRoom.getLsMonster().get(i).setPtDeVie(-800);

			}

		}

		if (StdDraw.isKeyPressed(Controls.puissance)) {

			hero.modePuissance();

		}
		if (StdDraw.isKeyPressed(Controls.addMoney)) {

			hero.addArgentTriche();

		}
	}

	/**
	 * Methode qui gere si le jeu est perdu ou pas
	 * 
	 * @return </br>
	 * 
	 *         <ul>
	 *         <li>true -> la partie est perdu</li>
	 *         <li>false -> la partie est pas encore perdu ou gagner</li>
	 *         </ul>
	 */
	public boolean gameOver() {
		return hero.getpV() <= 0;
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
	 * Methode qui gère les entre pour mettre en mouvement le personnage <br/>
	 * Managed <i>keys</i> :
	 * <ul>
	 * <li>key UP</li>
	 * <li>key Down</li>
	 * <li>key Left</li>
	 * <li>key Right</li>
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

	}

	/**
	 * Methode qui gère les entre pour mettre tiré une larme <br/>
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
			Larme e = new Larme(hero.getPosition(), RoomInfos.TILE_SIZE.scalarMultiplication(0.2), ImagePaths.TEAR,
					0.01, new Vector2(0, 1), 40, 1);

			hero.creeLarme(e);

		}
		if (StdDraw.isKeyPressed(Controls.hitDown)) {
			Larme e = new Larme(hero.getPosition(), RoomInfos.TILE_SIZE.scalarMultiplication(0.2), ImagePaths.TEAR,
					0.01, new Vector2(0, -1), 40, 1);

			hero.creeLarme(e);

		}
		if (StdDraw.isKeyPressed(Controls.hitLeft)) {
			Larme e = new Larme(hero.getPosition(), RoomInfos.TILE_SIZE.scalarMultiplication(0.2), ImagePaths.TEAR,
					0.01, new Vector2(-1, 0), 40, 1);

			hero.creeLarme(e);
		}
		if (StdDraw.isKeyPressed(Controls.hitRight)) {

			Larme e = new Larme(hero.getPosition(), RoomInfos.TILE_SIZE.scalarMultiplication(0.2), ImagePaths.TEAR,
					0.01, new Vector2(1, 0), 40, 1);

			hero.creeLarme(e);
		}
	}

}
