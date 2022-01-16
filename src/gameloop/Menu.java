package gameloop;

import java.awt.Font;

import gameobjects.personnages.hero.Hero;
import gameobjects.personnages.hero.Isaac;
import gameobjects.personnages.hero.Magdalene;
import gameobjects.personnages.hero.PyroBarbare;
import libraries.StdDraw;
import resources.HeroInfos;
import resources.ImagePaths;
import resources.RoomInfos;

/**
 * Gestion du menu de demarrage.
 */
public class Menu {

	/**
	 * Affichage du menu principal.
	 * 
	 * @param hero le hero
	 */
	public static Hero displayStartMenu(Hero hero) {

		System.out.println("Affichage menu demarrage");

		boolean readyToPlay = false;

		// affichage du menu au demarrage tant que je ne suis pas pret a jouer
		while (!readyToPlay) {
			// on efface l'affichage
			StdDraw.clear();
			// on affiche le menu de demarrage
			StdDraw.picture(0.5, 0.5, ImagePaths.LAUNCHMENU, RoomInfos.TILE_SIZE.getX() * 9,
					RoomInfos.TILE_SIZE.getY() * 9, 0);
			// on change la police d'ecriture
			Font fonte = new Font(" TimesRoman ", Font.BOLD, 30);
			StdDraw.setFont(fonte);
			// on dessine en blanc
			StdDraw.setPenColor(StdDraw.WHITE);
			// on dessine les rectangle pour les boutons
			StdDraw.filledRectangle(0.1, 0.5, 0.1, 0.05);
			StdDraw.filledRectangle(0.5, 0.2, 0.2, 0.06);
			StdDraw.filledRectangle(0.85, 0.5, 0.15, 0.05);
			// on ecrit en noir
			StdDraw.setPenColor(StdDraw.BLACK);
			// on remplit les rectangle avec un texte
			StdDraw.text(0.1, 0.5, "Jouer");
			StdDraw.text(0.5, 0.2, "Personnages");
			StdDraw.text(0.85, 0.5, "Quitter");

			// affichage de curseur
			StdDraw.picture(StdDraw.mouseX() - 0.025, StdDraw.mouseY() - 0.025, ImagePaths.STRENGTH, 0.05, 0.05, 0);
			StdDraw.show();

			// gestion du click dans les "zone"
			if (StdDraw.isMousePressed()) {

				// si on clic dans la zone de jouer
				if ((StdDraw.mouseX() >= 0 && StdDraw.mouseX() <= 0.2)
						&& (StdDraw.mouseY() >= 0.45 && StdDraw.mouseY() <= 0.55)) {
					readyToPlay = true;
				}

				// si on clic dans la zone de Personnage
				if ((StdDraw.mouseX() >= 0.35 && StdDraw.mouseX() <= 0.65)
						&& (StdDraw.mouseY() >= 0.15 && StdDraw.mouseY() <= 0.25)) {
					// affichage menu perso
					hero = displayHeroMenu(hero);

				}

				// si on clic dans la zone quitter
				if ((StdDraw.mouseX() >= 0.7 && StdDraw.mouseX() <= 1)
						&& (StdDraw.mouseY() >= 0.45 && StdDraw.mouseY() <= 0.55)) {
					System.exit(0);
				}

			}

		}

		return hero;

	}

	/**
	 * Gestion du menu de selection du hero.
	 * 
	 * @param hero le hero
	 */
	private static Hero displayHeroMenu(Hero hero) {

		System.out.println("Affichage menu Selection Hero");

		// On force la selection du hero
		hero = null;
		// tant que le nouveau hero n'est pas determiner
		while (hero == null) {

			// on remet a blanc l'ecran
			StdDraw.clear();
			// on affiche l'image de fond
			StdDraw.picture(0.5, 0.5, ImagePaths.MENUPERSO, RoomInfos.TILE_SIZE.getX() * 9,
					RoomInfos.TILE_SIZE.getY() * 9, 0);
			// on dessine en bleu des rectangle
			StdDraw.setPenColor(StdDraw.BOOK_BLUE);
			StdDraw.filledRectangle(0.3, 0.55, 0.08, 0.15);
			StdDraw.filledRectangle(0.5, 0.55, 0.08, 0.15);
			StdDraw.filledRectangle(0.7, 0.55, 0.08, 0.15);

			// on affiche les hero au dessus du rectangle
			StdDraw.picture(0.3, 0.8, ImagePaths.ISAAC, RoomInfos.TILE_SIZE.getX(), RoomInfos.TILE_SIZE.getY(), 0);

			StdDraw.picture(0.5, 0.8, ImagePaths.MAGDALENE, RoomInfos.TILE_SIZE.getX(), RoomInfos.TILE_SIZE.getY(), 0);

			StdDraw.picture(0.7, 0.8, ImagePaths.PYROBARBARE, RoomInfos.TILE_SIZE.getX(), RoomInfos.TILE_SIZE.getY(),
					0);
			// on ecrit en noir les information de chaque hero
			StdDraw.setPenColor(StdDraw.BLACK);
			StdDraw.setFont(new Font(" TimesRoman ", Font.BOLD, 12));

			StdDraw.text(0.3, 0.65, "ISAAC");
			StdDraw.text(0.3, 0.60, "LIFE : " + HeroInfos.ISAAC_LIFE);
			StdDraw.text(0.3, 0.55, "DAMMAGE : " + HeroInfos.ISAAC_ATTACK);
			StdDraw.text(0.3, 0.5, "SPEED : " + HeroInfos.ISAAC_SPEED);

			StdDraw.text(0.5, 0.65, "MAGDELENE");
			StdDraw.text(0.5, 0.60, "LIFE : " + HeroInfos.MAGDELENE_LIFE);
			StdDraw.text(0.5, 0.55, "DAMMAGE : 4" + HeroInfos.MAGDELENE_ATTACK);
			StdDraw.text(0.5, 0.5, "SPEED : " + HeroInfos.MAGDELENE_SPEED);

			StdDraw.text(0.7, 0.65, "LILITH");
			StdDraw.text(0.7, 0.60, "LIFE : " + HeroInfos.LILITH_LIFE);
			StdDraw.text(0.7, 0.55, "DAMMAGE : " + HeroInfos.LILITH_ATTACK);
			StdDraw.text(0.7, 0.5, "SPEED : " + HeroInfos.LILITH_SPEED);
			// affichage de curseur
			StdDraw.picture(StdDraw.mouseX() - 0.025, StdDraw.mouseY() - 0.025, ImagePaths.STRENGTH, 0.05, 0.05, 0);
			StdDraw.show();

			// si on clique dans l'une des zone du personnage, le personnage cliquer est
			// initialise
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

		return hero;

	}

	/**
	 * Gestion du menu de sortie.
	 * 
	 * @param imageFond image de fond suivant la situation
	 */
	public static boolean displayEndGameMenu(String imageFond) {

		System.out.println("Affichage menu Fin partie");

		// Gestion de la fin de la boucle
		boolean exitMenu = false;

		// Resultat pour rejouer ou finir la partie
		boolean replayGame = false;

		while (!exitMenu) {

			StdDraw.clear();

			// on affiche l'image indiquant que la partie est gagnee
			StdDraw.picture(0.5, 0.5, imageFond, RoomInfos.TILE_SIZE.getX() * 9, RoomInfos.TILE_SIZE.getY() * 9, 0);
			// on change la police
			Font fonte = new Font(" TimesRoman ", Font.BOLD, 30);
			StdDraw.setFont(fonte);
			StdDraw.setPenColor(StdDraw.WHITE);
			// on trace les rectangle representant les bouton
			StdDraw.filledRectangle(0.1, 0.7, 0.1, 0.05);
			StdDraw.filledRectangle(0.85, 0.7, 0.15, 0.05);

			StdDraw.setPenColor(StdDraw.BLACK);
			// on indique la fonction des boutons
			StdDraw.text(0.1, 0.7, "Rejouer");
			StdDraw.text(0.85, 0.7, "QUITTER");
			// gestion de l'endoit cliquer
			if (StdDraw.isMousePressed()) {

				if ((StdDraw.mouseX() >= 0 && StdDraw.mouseX() <= 0.2)
						&& (StdDraw.mouseY() >= 0.65 && StdDraw.mouseY() <= 0.75)) {
					replayGame = true;
					exitMenu = true;
				}
				if ((StdDraw.mouseX() >= 0.7 && StdDraw.mouseX() <= 1)
						&& (StdDraw.mouseY() >= 0.65 && StdDraw.mouseY() <= 0.75)) {
					exitMenu = true;
				}
			}
			// on affiche le curseur
			StdDraw.picture(StdDraw.mouseX() - 0.025, StdDraw.mouseY() - 0.025, ImagePaths.STRENGTH, 0.05, 0.05, 0);
			StdDraw.show();

		}

		return replayGame;

	}

}
