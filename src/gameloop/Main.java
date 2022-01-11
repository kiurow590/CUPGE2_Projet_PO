package gameloop;

import gameWorld.GameWorld;
import gameobjects.personnages.Hero;
import libraries.StdDraw;
import libraries.Timer;
import resources.DisplaySettings;
import resources.ImagePaths;
import resources.RoomInfos;

import java.awt.Font;

public class Main {
	public static void main(String[] args) {
		// Hero, world and display initialisation.
		Hero isaac = new Hero(RoomInfos.POSITION_CENTER_OF_ROOM);
		// on genere un nouveau gameWorld
		GameWorld world = new GameWorld(isaac);
		// on initialise l'affichage
		initializeDisplay();

		// affichage du menu au demarrage
		while (true) {
			StdDraw.clear();
			StdDraw.picture(0.5, 0.5, ImagePaths.LAUNCHMENU, RoomInfos.TILE_SIZE.getX() * 9,
					RoomInfos.TILE_SIZE.getY() * 9, 0);
			Font fonte = new Font(" TimesRoman ", Font.BOLD, 30);
			StdDraw.setFont(fonte);
			StdDraw.setPenColor(StdDraw.WHITE);

			StdDraw.filledRectangle(0.1, 0.5, 0.1, 0.05);

			StdDraw.filledRectangle(0.85, 0.5, 0.15, 0.05);

			StdDraw.setPenColor(StdDraw.BLACK);

			StdDraw.text(0.1, 0.5, "Jouer");
			StdDraw.text(0.85, 0.5, "Quitter");
			// gestion du click dans les "zone"
			if (StdDraw.isMousePressed()) {
				System.out.println("Josie la best");
				if ((StdDraw.mouseX() >= 0 && StdDraw.mouseX() <= 0.2)
						&& (StdDraw.mouseY() >= 0.45 && StdDraw.mouseY() <= 0.55)) {
					break;
				}
				if ((StdDraw.mouseX() >= 0.7 && StdDraw.mouseX() <= 1)
						&& (StdDraw.mouseY() >= 0.45 && StdDraw.mouseY() <= 0.55)) {
					System.exit(0);
				}
			}
			// affichage de curseur
			StdDraw.picture(StdDraw.mouseX() - 0.025, StdDraw.mouseY() - 0.025, ImagePaths.STRENGTH, 0.05,
					0.05, 0);
			StdDraw.show();

		}

		// Tant que le jeu n'est pas fini ou que isaac n'as pas gagne
		while (!world.gameOver() && world.getCurrentRoom().getAGagner() == false) {

			StdDraw.setFont();

			processNextStep(world);

		}
		// Si isaac est mort
		if (isaac.isDead()) {

			while (true) {
				// on affiche une image indiquant que la partie est perdu
				StdDraw.clear();
				StdDraw.picture(0.5, 0.5, ImagePaths.LOSE_SCREEN, RoomInfos.TILE_SIZE.getX() * 9,
						RoomInfos.TILE_SIZE.getY() * 9, 0);
				Font fonte = new Font(" TimesRoman ", Font.BOLD, 30);
				StdDraw.setFont(fonte);
				StdDraw.setPenColor(StdDraw.WHITE);

				StdDraw.filledRectangle(0.1, 0.5, 0.1, 0.05);

				StdDraw.filledRectangle(0.85, 0.5, 0.15, 0.05);

				StdDraw.setPenColor(StdDraw.BLACK);

				StdDraw.text(0.1, 0.5, "Rejouer");
				StdDraw.text(0.85, 0.5, "Quitter");
				if (StdDraw.isMousePressed()) {
					System.out.println("Josie la best");
					if ((StdDraw.mouseX() >= 0 && StdDraw.mouseX() <= 0.2)
							&& (StdDraw.mouseY() >= 0.45 && StdDraw.mouseY() <= 0.55)) {
						break;
					}
					if ((StdDraw.mouseX() >= 0.7 && StdDraw.mouseX() <= 1)
							&& (StdDraw.mouseY() >= 0.45 && StdDraw.mouseY() <= 0.55)) {
						System.exit(0);
					}
				}
				StdDraw.picture(StdDraw.mouseX() - 0.025, StdDraw.mouseY() - 0.025, ImagePaths.STRENGTH, 0.05,
						0.05, 0);
				StdDraw.show();

			}

			// Sinon
		} else {
			// on affiche l'image indiquant que la partie est gagnee
			StdDraw.clear();
			StdDraw.picture(0.5, 0.5, ImagePaths.WIN_SCREEN, RoomInfos.TILE_SIZE.getX() * 9,
					RoomInfos.TILE_SIZE.getY() * 9, 0);
			Font fonte = new Font(" TimesRoman ", Font.BOLD, 30);
			StdDraw.setFont(fonte);
			StdDraw.setPenColor(StdDraw.WHITE);

			StdDraw.filledRectangle(0.1, 0.5, 0.1, 0.05);

			StdDraw.filledRectangle(0.85, 0.5, 0.15, 0.05);

			StdDraw.setPenColor(StdDraw.BLACK);

			StdDraw.text(0.1, 0.5, "Rejouer");
			StdDraw.text(0.85, 0.5, "QUITTER");
			if (StdDraw.isMousePressed()) {
				System.out.println("Josie la best");
				if ((StdDraw.mouseX() >= 0 && StdDraw.mouseX() <= 0.2)
						&& (StdDraw.mouseY() >= 0.45 && StdDraw.mouseY() <= 0.55)) {
					main(new String[0]);
				}
				if ((StdDraw.mouseX() >= 0.7 && StdDraw.mouseX() <= 1)
						&& (StdDraw.mouseY() >= 0.45 && StdDraw.mouseY() <= 0.55)) {
					System.exit(0);
				}
			}
			StdDraw.picture(StdDraw.mouseX() - 0.025, StdDraw.mouseY() - 0.025, ImagePaths.STRENGTH, 0.05,
					0.05, 0);
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
