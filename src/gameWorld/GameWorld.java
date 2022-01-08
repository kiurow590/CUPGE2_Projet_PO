package gameWorld;

import java.util.HashMap;
import java.util.Map;

import gameWorld.rooms.BossRoom;
import gameWorld.rooms.MonsterRoom;
import gameWorld.rooms.Room;
import gameWorld.rooms.ShopRoom;
import gameWorld.rooms.SpawnRoom;
import gameWorld.rooms.portes.BottomDoor;
import gameWorld.rooms.portes.CarriesAway;
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
		generateStage1();
		generateStage2();
		generateStage3();

		mapDeRoom.get(5).getLstPorte().add(new CarriesAway(6));

		mapDeRoom.get(14).getLstPorte().add(new CarriesAway(15));

		currentRoom = mapDeRoom.get(0);
	}

	/**
	 * Methode qui genere le premiere etage du jeu
	 */
	public void generateStage1() {
		// Generation des rooms
		Room spawn = new SpawnRoom(hero, 0);
		Room monster1 = new MonsterRoom(hero, 1);
		Room monster2 = new MonsterRoom(hero, 2);
		Room monster3 = new MonsterRoom(hero, 3);
		Room commerce1 = new ShopRoom(hero, 4);
		Room boss = new BossRoom(hero, 5);

		// on ajoute les porte au differente salle
		spawn.getLstPorte().add(new LeftDoor(monster1.getId()));
		monster1.getLstPorte().add(new RightDoor(spawn.getId()));
		monster1.getLstPorte().add(new LeftDoor(monster2.getId()));
		monster2.getLstPorte().add(new RightDoor(monster1.getId()));
		monster2.getLstPorte().add(new RightDoor(monster1.getId()));

		monster1.getLstPorte().add(new BottomDoor(monster3.getId()));

		monster3.getLstPorte().add(new TopDoor(monster1.getId()));
		monster3.getLstPorte().add(new BottomDoor(commerce1.getId()));

		commerce1.getLstPorte().add(new TopDoor(monster3.getId()));

		commerce1.getLstPorte().add(new RightDoor(boss.getId()));
		boss.getLstPorte().add(new LeftDoor(commerce1.getId()));
		boss.getLstObjet().remove(0);

		// on ajoute a la map
		mapDeRoom.put(spawn.getId(), spawn);
		mapDeRoom.put(monster1.getId(), monster1);
		mapDeRoom.put(monster2.getId(), monster2);
		mapDeRoom.put(monster3.getId(), monster3);
		mapDeRoom.put(commerce1.getId(), commerce1);
		mapDeRoom.put(boss.getId(), boss);
	}

	/**
	 * Methode qui genere le deuxieme etage du jeu
	 */
	public void generateStage2() {
		// Generation des rooms
		Room spawn = new SpawnRoom(hero, 6);
		Room monster1 = new MonsterRoom(hero, 7);
		Room monster2 = new MonsterRoom(hero, 8);
		Room monster3 = new MonsterRoom(hero, 9);
		Room commerce1 = new ShopRoom(hero, 10);
		Room monster4 = new MonsterRoom(hero, 11);
		Room monster5 = new MonsterRoom(hero, 12);
		Room commerce2 = new ShopRoom(hero, 13);
		Room boss = new BossRoom(hero, 14);

		// on ajoute les porte au differente salle
		spawn.getLstPorte().add(new RightDoor(monster1.getId()));
		monster1.getLstPorte().add(new LeftDoor(spawn.getId()));

		monster1.getLstPorte().add(new BottomDoor(monster2.getId()));
		monster2.getLstPorte().add(new TopDoor(monster1.getId()));

		monster2.getLstPorte().add(new RightDoor(monster3.getId()));
		monster3.getLstPorte().add(new LeftDoor(monster2.getId()));

		monster3.getLstPorte().add(new BottomDoor(commerce1.getId()));
		commerce1.getLstPorte().add(new TopDoor(monster3.getId()));

		commerce1.getLstPorte().add(new BottomDoor(monster4.getId()));
		monster4.getLstPorte().add(new TopDoor(commerce1.getId()));

		monster4.getLstPorte().add(new LeftDoor(monster5.getId()));
		monster5.getLstPorte().add(new RightDoor(monster4.getId()));

		monster5.getLstPorte().add(new BottomDoor(commerce2.getId()));
		commerce2.getLstPorte().add(new TopDoor(monster5.getId()));

		commerce2.getLstPorte().add(new BottomDoor(boss.getId()));
		boss.getLstPorte().add(new TopDoor(commerce2.getId()));
		boss.getLstObjet().remove(0);

		// on ajoute a la map
		mapDeRoom.put(spawn.getId(), spawn);
		mapDeRoom.put(monster1.getId(), monster1);
		mapDeRoom.put(monster2.getId(), monster2);
		mapDeRoom.put(monster3.getId(), monster3);
		mapDeRoom.put(monster4.getId(), monster4);
		mapDeRoom.put(monster5.getId(), monster5);
		mapDeRoom.put(commerce1.getId(), commerce1);
		mapDeRoom.put(commerce2.getId(), commerce2);
		mapDeRoom.put(boss.getId(), boss);
	}

	/**
	 * Methode qui genere le troisieme etage du jeu
	 */
	public void generateStage3() {
		// Generation des rooms
		Room spawn = new SpawnRoom(hero, 15);
		Room monster1 = new MonsterRoom(hero, 16);
		Room monster2 = new MonsterRoom(hero, 17);
		Room monster3 = new MonsterRoom(hero, 18);
		Room monster4 = new MonsterRoom(hero, 19);
		Room monster5 = new MonsterRoom(hero, 20);
		Room commerce1 = new ShopRoom(hero, 21);
		Room monster6 = new MonsterRoom(hero, 22);
		Room monster7 = new MonsterRoom(hero, 23);
		Room monster8 = new MonsterRoom(hero, 24);
		Room monster9 = new MonsterRoom(hero, 25);
		Room commerce2 = new ShopRoom(hero, 26);
		Room boss = new BossRoom(hero, 27);

		// on ajoute les porte au differente salle
		spawn.getLstPorte().add(new TopDoor(monster1.getId()));
		monster1.getLstPorte().add(new BottomDoor(spawn.getId()));

		monster1.getLstPorte().add(new RightDoor(monster2.getId()));
		monster2.getLstPorte().add(new LeftDoor(monster1.getId()));

		monster2.getLstPorte().add(new BottomDoor(monster3.getId()));
		monster3.getLstPorte().add(new TopDoor(monster2.getId()));

		monster3.getLstPorte().add(new BottomDoor(monster4.getId()));
		monster4.getLstPorte().add(new TopDoor(monster3.getId()));

		monster4.getLstPorte().add(new LeftDoor(monster5.getId()));
		monster5.getLstPorte().add(new RightDoor(monster4.getId()));

		monster5.getLstPorte().add(new LeftDoor(commerce1.getId()));
		commerce1.getLstPorte().add(new RightDoor(monster5.getId()));

		commerce1.getLstPorte().add(new TopDoor(monster6.getId()));
		monster6.getLstPorte().add(new BottomDoor(commerce1.getId()));

		monster6.getLstPorte().add(new TopDoor(monster7.getId()));
		monster7.getLstPorte().add(new BottomDoor(monster6.getId()));

		monster7.getLstPorte().add(new TopDoor(monster8.getId()));
		monster8.getLstPorte().add(new BottomDoor(monster7.getId()));

		monster8.getLstPorte().add(new RightDoor(monster9.getId()));
		monster9.getLstPorte().add(new LeftDoor(monster8.getId()));

		monster9.getLstPorte().add(new RightDoor(commerce2.getId()));
		commerce2.getLstPorte().add(new LeftDoor(monster9.getId()));

		commerce2.getLstPorte().add(new RightDoor(boss.getId()));
		boss.getLstPorte().add(new LeftDoor(commerce2.getId()));

		// on ajoute a la map
		mapDeRoom.put(spawn.getId(), spawn);
		mapDeRoom.put(monster1.getId(), monster1);
		mapDeRoom.put(monster2.getId(), monster2);
		mapDeRoom.put(monster3.getId(), monster3);
		mapDeRoom.put(monster4.getId(), monster4);
		mapDeRoom.put(monster5.getId(), monster5);
		mapDeRoom.put(commerce1.getId(), commerce1);
		mapDeRoom.put(monster6.getId(), monster6);
		mapDeRoom.put(monster7.getId(), monster7);
		mapDeRoom.put(monster8.getId(), monster8);
		mapDeRoom.put(monster9.getId(), monster9);
		mapDeRoom.put(commerce2.getId(), commerce2);
		mapDeRoom.put(boss.getId(), boss);
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

	/**
	 * Methode qui permet de modifier la room lors du passage d'un porte
	 */
	private void changeCurrentRoom() {

		if (currentRoom.getLsMonster().isEmpty()) {

			for (Door door : currentRoom.getLstPorte()) {

				if (Physics.rectangleCollision(hero.getPosition(), hero.getSize(),
						door.getPosition(), door.getSize())) {

					if (door instanceof BottomDoor) {
						hero.setPosition(new Vector2(0.5, 0.85));
					} else if (door instanceof TopDoor) {
						hero.setPosition(new Vector2(0.5, 0.15));
					} else if (door instanceof LeftDoor) {
						hero.setPosition(new Vector2(0.85, 0.5));
					} else if (door instanceof RightDoor) {
						hero.setPosition(new Vector2(0.15, 0.5));
					}
					currentRoom = mapDeRoom.get(door.getIdSalle());
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
				currentRoom.getLsMonster().get(i).setPointVie(-800);

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

	public Room getCurrentRoom() {
		return this.currentRoom;
	}

	public void setCurrentRoom(Room currentRoom) {
		this.currentRoom = currentRoom;
	}

}
