package gameWorld.rooms.portes;

import resources.ImagePaths;

/**
 * Class qui genere la porte de droite avec clee
 */
public class RightKeyDoor extends RightDoor {
	/**
	 * constructeur de porte avec necessité d'une clee a droite de la room
	 * 
	 * @param idSalle identifiant de la salle vers qui mene la porte
	 */

	public RightKeyDoor(int idSalle) {
		super(idSalle);
		// la porte est initialement ferme
		this.imagePaths = ImagePaths.PORTE_OBJET_FERME;
		estOuvert = false;
	}

}
