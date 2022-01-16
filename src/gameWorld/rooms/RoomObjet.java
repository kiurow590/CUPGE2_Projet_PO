package gameWorld.rooms;

import gameobjects.objets.passifs.BloodOfMartyr;
import gameobjects.objets.passifs.NumberOne;
import gameobjects.personnages.hero.Hero;
import libraries.StdDraw;
import libraries.Vector2;
import resources.ImagePaths;

/**
 * class de Roomobjet
 */
public class RoomObjet extends Room {
	private static final String MON_TYPE = "OBJECT_ROOM";

	/**
	 * constructeur de RoomObjet
	 * 
	 * @param hero    le hero qui joue
	 * @param idSalle id de la salle
	 */
	public RoomObjet(Hero hero, Integer idSalle) {
		super(hero, idSalle);
		this.type = MON_TYPE;
		// une chance sur 2 par objet
		if (Math.random() <= 0.5) {
			this.lstObjet.add(new NumberOne(new Vector2(0.5, 0.5)));

		} else {
			this.lstObjet.add(new BloodOfMartyr(new Vector2(0.5, 0.5)));
		}

	}

	@Override
	public void updateRoom() {
		makeHeroPlay();
		collisionReport();
		nettoyageLarme();

	}

	@Override
	public void drawRoom() {
		StdDraw.picture(0.5, 0.5, ImagePaths.SHOP_ROOM, 1, 1);

		hero.drawGameObject();

		dessinePorte();

		dessineMonstre();
		dessineLarme();
		affichageViePiece();

		affichageObjets();
	}

}
