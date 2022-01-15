package gameloop;

import gameWorld.GameWorld;
import gameobjects.personnages.Hero;
import gameobjects.personnages.Isaac;
import gameobjects.personnages.Magdalene;
import gameobjects.personnages.PyroBarbare;
import libraries.StdDraw;
import libraries.Timer;
import resources.DisplaySettings;
import resources.HeroInfos;
import resources.ImagePaths;
import resources.RoomInfos;

import java.awt.Font;

/**
 * Class moteur du jeu
 */
public class Main {
	public static void main(String[] args) {
		// Hero, world and display initialisation.
		Hero hero = new Isaac(RoomInfos.POSITION_CENTER_OF_ROOM);
		// on initialise l'affichage
		initializeDisplay();

		// Affichage du menu
		hero = Menu.displayStartMenu(hero);

		// on genere un nouveau gameWorld
		GameWorld world = new GameWorld(hero);
		// Tant que le jeu n'est pas fini ou que isaac n'as pas gagne
		while (!world.gameOver() && world.getCurrentRoom().getAGagner() == false) {

			StdDraw.setFont();

			processNextStep(world);

		}

		/*
		 * Gestion de la fin de partie
		 */
		boolean replayGame = false;

		// Si isaac est mort
		if (hero.isDead()) {
			replayGame = Menu.displayEndGameMenu(ImagePaths.LOSE_SCREEN);
			// Sinon
		} else {
			replayGame = Menu.displayEndGameMenu(ImagePaths.WIN_SCREEN);
		}

		// Gestion du "Rejouer" ou fin de partie
		if (replayGame) {
			main(new String[0]);
		} else {
			System.exit(0);
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
