package gameWorld;

import java.util.HashMap;
import java.util.Map;

import gameWorld.rooms.BossRoom;
import gameWorld.rooms.MonsterRoom;
import gameWorld.rooms.Room;
import gameWorld.rooms.ShopRoom;
import gameWorld.rooms.SpawnRoom;
import gameWorld.rooms.portes.BottomDoor;
import gameWorld.rooms.portes.LeftDoor;
import gameWorld.rooms.portes.RightDoor;
import gameWorld.rooms.portes.TopDoor;
import gameobjects.personnages.Hero;
import gameobjects.projectiles.Projectile;
import libraries.Physics;
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
		Room spawn = new SpawnRoom(hero);
		Room monster1 = new MonsterRoom(hero, 1);
		Room monster2 = new MonsterRoom(hero, 2);
		Room monster3 = new MonsterRoom(hero, 3);
		Room commerce1 = new ShopRoom(hero, 4);
		Room Boss = new BossRoom(hero, 5);

		// on ajoute a la map
		mapDeRoom.put(spawn.getId(), spawn);
		mapDeRoom.put(monster1.getId(), monster1);
		mapDeRoom.put(monster2.getId(), monster2);
		mapDeRoom.put(monster3.getId(), monster3);
		mapDeRoom.put(commerce1.getId(), commerce1);
		mapDeRoom.put(Boss.getId(), Boss);

		// on ajoute les porte au differente salle
		mapDeRoom.get(spawn.getId()).getLstPorte().add(new LeftDoor(monster1.getId()));
		mapDeRoom.get(monster1.getId()).getLstPorte().add(new RightDoor(spawn.getId()));
		mapDeRoom.get(monster1.getId()).getLstPorte().add(new LeftDoor(monster2.getId()));
		mapDeRoom.get(monster2.getId()).getLstPorte().add(new RightDoor(monster1.getId()));
		mapDeRoom.get(monster2.getId()).getLstPorte().add(new RightDoor(monster1.getId()));

		mapDeRoom.get(monster1.getId()).getLstPorte().add(new BottomDoor(monster3.getId()));

		mapDeRoom.get(monster3.getId()).getLstPorte().add(new TopDoor(monster1.getId()));
		mapDeRoom.get(monster3.getId()).getLstPorte().add(new BottomDoor(commerce1.getId()));

		mapDeRoom.get(commerce1.getId()).getLstPorte().add(new TopDoor(monster3.getId()));

		mapDeRoom.get(commerce1.getId()).getLstPorte().add(new RightDoor(Boss.getId()));
		mapDeRoom.get(Boss.getId()).getLstPorte().add(new LeftDoor(commerce1.getId()));

		currentRoom = spawn;
	}

	/**
	 * Methode qui gere les entree de l'utilisateur
	 */
	public void processUserInput() {
		processKeysForMovement();
		processTire();
		cheatCode();
		changeCurrentRoom();
	}

	private void changeCurrentRoom() {

		if (currentRoom.getLsMonster().isEmpty()) {

			for (int i = 0; currentRoom.getLstPorte() != null && i < currentRoom.getLstPorte().size(); i++) {
				if (Physics.rectangleCollision(hero.getPosition(), hero.getSize(),
						currentRoom.getLstPorte().get(i).getPosition(), currentRoom.getLstPorte().get(i).getSize())) {

					if (currentRoom.getLstPorte().get(i) instanceof BottomDoor) {
						hero.setPosition(new Vector2(0.5, 0.85));
					} else if (currentRoom.getLstPorte().get(i) instanceof TopDoor) {
						hero.setPosition(new Vector2(0.5, 0.15));
					} else if (currentRoom.getLstPorte().get(i) instanceof LeftDoor) {
						hero.setPosition(new Vector2(0.85, 0.5));
					} else if (currentRoom.getLstPorte().get(i) instanceof RightDoor) {
						hero.setPosition(new Vector2(0.15, 0.5));
					}
					currentRoom = mapDeRoom.get(currentRoom.getLstPorte().get(i).getIdSalle());
				}
			}

		}

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
		if (StdDraw.isKeyPressed(Controls.goUp) && hero.getPosition().getY() < 0.92) {
			hero.goUpNext();

		}
		if (StdDraw.isKeyPressed(Controls.goDown) && hero.getPosition().getY() > 0.08) {
			hero.goDownNext();

		}
		if (StdDraw.isKeyPressed(Controls.goRight) && hero.getPosition().getX() < 0.92) {
			hero.goRightNext();

		}
		if (StdDraw.isKeyPressed(Controls.goLeft) && hero.getPosition().getX() > 0.08) {
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
			Projectile e = new Projectile(hero.getPosition(), RoomInfos.TILE_SIZE.scalarMultiplication(0.2),
					ImagePaths.TEAR,
					0.01, new Vector2(0, 1), 40, 1);

			hero.creeLarme(e);

		}
		if (StdDraw.isKeyPressed(Controls.hitDown)) {
			Projectile e = new Projectile(hero.getPosition(), RoomInfos.TILE_SIZE.scalarMultiplication(0.2),
					ImagePaths.TEAR,
					0.01, new Vector2(0, -1), 40, 1);

			hero.creeLarme(e);

		}
		if (StdDraw.isKeyPressed(Controls.hitLeft)) {
			Projectile e = new Projectile(hero.getPosition(), RoomInfos.TILE_SIZE.scalarMultiplication(0.2),
					ImagePaths.TEAR,
					0.01, new Vector2(-1, 0), 40, 1);

			hero.creeLarme(e);
		}
		if (StdDraw.isKeyPressed(Controls.hitRight)) {

			Projectile e = new Projectile(hero.getPosition(), RoomInfos.TILE_SIZE.scalarMultiplication(0.2),
					ImagePaths.TEAR,
					0.01, new Vector2(1, 0), 40, 1);

			hero.creeLarme(e);
		}
	}

}
