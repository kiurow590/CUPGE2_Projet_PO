package gameWorld.rooms.portes;

import resources.ImagePaths;

/**
 * Class qui genere la porte de gauche avec clee
 */
public class LeftKeyDoor extends LeftDoor {
	/**
	 * constructeur de porte avec necessité d'une clee a gauche de la room
	 * 
	 * @param idSalle identifiant de la salle vers qui mene la porte
	 */

	public LeftKeyDoor(int idSalle) {
		super(idSalle);
		// la porte est initialement ferme
		this.imagePaths = ImagePaths.PORTE_OBJET_FERME;
		estOuvert = false;
	}

}
