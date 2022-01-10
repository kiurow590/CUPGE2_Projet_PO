package gameloop;

import gameWorld.GameWorld;
import gameobjects.personnages.Hero;
import libraries.StdDraw;
import libraries.Timer;
import resources.DisplaySettings;
import resources.ImagePaths;
import resources.RoomInfos;

public class Main {
	public static void main(String[] args) {
		// Hero, world and display initialisation.
		Hero isaac = new Hero(RoomInfos.POSITION_CENTER_OF_ROOM);
		// on genere un nouveau gameWorld
		GameWorld world = new GameWorld(isaac);
		// on initialise l'affichage
		initializeDisplay();

		// Tant que le jeu n'est pas fini ou que isaac n'as pas gagne
		while (!world.gameOver() && world.getCurrentRoom().getAGagner() == false) {
			// le jeu continue de tourner
			processNextStep(world);

		}
		// Si isaac est mort
		if (isaac.isDead()) {
			// on affiche une image indiquant que la partie est perdu
			StdDraw.clear();
			StdDraw.picture(0.5, 0.5, ImagePaths.LOSE_SCREEN, RoomInfos.TILE_SIZE.getX() * 9,
					RoomInfos.TILE_SIZE.getY() * 9, 0);
			StdDraw.show();
			// Sinon
		} else {
			// on affiche l'image indiquant que la partie est gagnee
			StdDraw.clear();
			StdDraw.picture(0.5, 0.5, ImagePaths.WIN_SCREEN, RoomInfos.TILE_SIZE.getX() * 9,
					RoomInfos.TILE_SIZE.getY() * 9, 0);
			StdDraw.show();
		}
	}

	/**
	 * affiche la suite de la map
	 * 
	 * @param world monde actuel
	 */
	private static void processNextStep(GameWorld world) {
		Timer.beginTimer();
		StdDraw.clear();
		world.processUserInput();
		world.updateGameObjects();
		world.drawGameObjects();
		StdDraw.show();
		Timer.waitToMaintainConstantFPS();
	}

	/**
	 * Initialise le canvas de jeu
	 */
	private static void initializeDisplay() {
		// Set the window's size, in pixels.
		// It is strongly recommended to keep a square window.
		StdDraw.setCanvasSize(RoomInfos.NB_TILES * DisplaySettings.PIXEL_PER_TILE,
				RoomInfos.NB_TILES * DisplaySettings.PIXEL_PER_TILE);

		// Enables double-buffering.
		// https://en.wikipedia.org/wiki/Multiple_buffering#Double_buffering_in_computer_graphics
		StdDraw.enableDoubleBuffering();
	}
}
