package gameWorld.rooms.portes;

import resources.ImagePaths;

/**
 * Class qui genere la porte du haut avec clee
 */
public class TopKeyDoor extends TopDoor {

	/**
	 * constructeur de porte avec necessite d'une clee en haut de la room
	 * 
	 * @param idSalle identifiant de la salle vers qui mene la porte
	 */
	public TopKeyDoor(int idSalle) {
		super(idSalle);
		// la porte est initialement ferme
		this.imagePaths = ImagePaths.PORTE_OBJET_FERME;
		estOuvert = false;
	}

}
