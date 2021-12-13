package gameloop;

import gameWorld.GameWorld;
import gameobjects.Fly;
import gameobjects.Hero;
import gameobjects.Spider;
import libraries.StdDraw;
import libraries.Timer;
import libraries.Vector2;
import resources.DisplaySettings;
import resources.HeroInfos;
import resources.ImagePaths;
import resources.RoomInfos;

public class Main {
	public static void main(String[] args) {
		// Hero, world and display initialisation.
		Hero isaac = new Hero(RoomInfos.POSITION_CENTER_OF_ROOM, HeroInfos.ISAAC_SIZE, HeroInfos.ISAAC_SPEED,
				ImagePaths.ISAAC, 5, 6);

		Spider spider = new Spider(new Vector2(0.2, 0.5), RoomInfos.TILE_SIZE.scalarMultiplication(0.4),
				ImagePaths.SPIDER, 0.01, new Vector2(), 40);

		Fly fly = new Fly(new Vector2(0.8, 0.8), RoomInfos.TILE_SIZE.scalarMultiplication(0.4), ImagePaths.FLY, 0.007,
				new Vector2(), 2, 1);
		GameWorld world = new GameWorld(isaac, spider, fly);
		initializeDisplay();

		// Main loop of the game
		while (!world.gameOver()) {
			processNextStep(world);
		}

		if (isaac.getpV() <= 0) {
			Timer.beginTimer();
			StdDraw.clear();
			StdDraw.picture(0.5, 0.5, ImagePaths.LOSE_SCREEN, RoomInfos.TILE_SIZE.getX() * 9,
					RoomInfos.TILE_SIZE.getY() * 9, 0);
			StdDraw.show();
			Timer.waitToMaintainConstantFPS();
		}
	}

	/**
	 * Methode qui affiche la suite de la map
	 * 
	 * @param world map actuel
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
