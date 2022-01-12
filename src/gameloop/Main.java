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

public class Main {
	public static void main(String[] args) {
		// Hero, world and display initialisation.
		Hero hero = new Isaac(RoomInfos.POSITION_CENTER_OF_ROOM);
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
			StdDraw.filledRectangle(0.5, 0.2, 0.2, 0.06);

			StdDraw.filledRectangle(0.85, 0.5, 0.15, 0.05);

			StdDraw.setPenColor(StdDraw.BLACK);

			StdDraw.text(0.1, 0.5, "Jouer");
			StdDraw.text(0.5, 0.2, "Personnages");

			StdDraw.text(0.85, 0.5, "Quitter");

			// affichage de curseur
			StdDraw.picture(StdDraw.mouseX() - 0.025, StdDraw.mouseY() - 0.025, ImagePaths.STRENGTH, 0.05,
					0.05, 0);
			StdDraw.show();

			// gestion du click dans les "zone"
			if (StdDraw.isMousePressed()) {
				System.out.println("Josie la best");
				if ((StdDraw.mouseX() >= 0 && StdDraw.mouseX() <= 0.2)
						&& (StdDraw.mouseY() >= 0.45 && StdDraw.mouseY() <= 0.55)) {
					break;
				}
				if ((StdDraw.mouseX() >= 0.35 && StdDraw.mouseX() <= 0.65)
						&& (StdDraw.mouseY() >= 0.15 && StdDraw.mouseY() <= 0.25)) {

					while (true) {
						StdDraw.clear();
						StdDraw.setPenColor(StdDraw.BOOK_BLUE);

						StdDraw.picture(0.5, 0.5, ImagePaths.MENUPERSO, RoomInfos.TILE_SIZE.getX() * 9,
								RoomInfos.TILE_SIZE.getY() * 9, 0);

						StdDraw.picture(0.3, 0.8, ImagePaths.ISAAC, RoomInfos.TILE_SIZE.getX(),
								RoomInfos.TILE_SIZE.getY(), 0);
						StdDraw.filledRectangle(0.3, 0.55, 0.08, 0.15);

						StdDraw.picture(0.5, 0.8, ImagePaths.MAGDALENE, RoomInfos.TILE_SIZE.getX(),
								RoomInfos.TILE_SIZE.getY(), 0);
						StdDraw.filledRectangle(0.5, 0.55, 0.08, 0.15);

						StdDraw.picture(0.7, 0.8, ImagePaths.PYROBARBARE, RoomInfos.TILE_SIZE.getX(),
								RoomInfos.TILE_SIZE.getY(), 0);
						StdDraw.filledRectangle(0.7, 0.55, 0.08, 0.15);

						StdDraw.setPenColor(StdDraw.BLACK);
						StdDraw.setFont(new Font(" TimesRoman ", Font.BOLD, 12));

						StdDraw.text(0.3, 0.65, "ISAAC");
						StdDraw.text(0.3, 0.60, "LIFE : 6");
						StdDraw.text(0.3, 0.55, "DAMMAGE : 1");
						StdDraw.text(0.3, 0.5, "SPEED : " + HeroInfos.ISAAC_SPEED);

						StdDraw.text(0.5, 0.65, "MAGDELENE");
						StdDraw.text(0.5, 0.60, "LIFE : 8");
						StdDraw.text(0.5, 0.55, "DAMMAGE : 4");
						StdDraw.text(0.5, 0.5, "SPEED : " + HeroInfos.ISAAC_SPEED);

						StdDraw.text(0.7, 0.65, "LILITH");
						StdDraw.text(0.7, 0.60, "LIFE : 2");
						StdDraw.text(0.7, 0.55, "DAMMAGE : 1");
						StdDraw.text(0.7, 0.5, "SPEED : " + HeroInfos.ISAAC_SPEED);
						// affichage de curseur
						StdDraw.picture(StdDraw.mouseX() - 0.025, StdDraw.mouseY() - 0.025, ImagePaths.STRENGTH, 0.05,
								0.05, 0);
						StdDraw.show();
						if (StdDraw.isMousePressed()) {
							if ((StdDraw.mouseX() >= 0.3 - RoomInfos.TILE_SIZE.getX()
									&& StdDraw.mouseX() <= 0.3 + RoomInfos.TILE_SIZE.getX())
									&& (StdDraw.mouseY() >= 0.8 - RoomInfos.TILE_SIZE.getX()
											&& StdDraw.mouseY() <= 0.8 + RoomInfos.TILE_SIZE.getX())) {
								hero = new Isaac(RoomInfos.POSITION_CENTER_OF_ROOM);
								break;
							}
							if ((StdDraw.mouseX() >= 0.5 - RoomInfos.TILE_SIZE.getX()
									&& StdDraw.mouseX() <= 0.5 + RoomInfos.TILE_SIZE.getX())
									&& (StdDraw.mouseY() >= 0.8 - RoomInfos.TILE_SIZE.getX()
											&& StdDraw.mouseY() <= 0.8 + RoomInfos.TILE_SIZE.getX())) {
								hero = new Magdalene(RoomInfos.POSITION_CENTER_OF_ROOM);

								break;
							}
							if ((StdDraw.mouseX() >= 0.7 - RoomInfos.TILE_SIZE.getX()
									&& StdDraw.mouseX() <= 0.7 + RoomInfos.TILE_SIZE.getX())
									&& (StdDraw.mouseY() >= 0.8 - RoomInfos.TILE_SIZE.getX()
											&& StdDraw.mouseY() <= 0.8 + RoomInfos.TILE_SIZE.getX())) {
								hero = new PyroBarbare(RoomInfos.POSITION_CENTER_OF_ROOM);

								break;
							}
						}
					}
				}

				if ((StdDraw.mouseX() >= 0.7 && StdDraw.mouseX() <= 1)
						&& (StdDraw.mouseY() >= 0.45 && StdDraw.mouseY() <= 0.55)) {
					System.exit(0);
				}

			}

		}
		// on genere un nouveau gameWorld
		GameWorld world = new GameWorld(hero);
		// Tant que le jeu n'est pas fini ou que isaac n'as pas gagne
		while (!world.gameOver() && world.getCurrentRoom().getAGagner() == false) {

			StdDraw.setFont();

			processNextStep(world);

		}
		// Si isaac est mort
		if (hero.isDead()) {

			while (true) {
				AffichageMenu(ImagePaths.LOSE_SCREEN);
			}

			// Sinon
		} else {
			while (true) {
				AffichageMenu(ImagePaths.WIN_SCREEN);
			}
		}

	}

	/**
	 * Affichage du menu de sortie de jeu
	 * 
	 * @param imageFond image de fond suivant la situation
	 */
	public static void AffichageMenu(String imageFond) {
		// on affiche l'image indiquant que la partie est gagnee
		StdDraw.clear();
		StdDraw.picture(0.5, 0.5, imageFond, RoomInfos.TILE_SIZE.getX() * 9,
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
