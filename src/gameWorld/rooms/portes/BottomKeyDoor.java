package gameWorld.rooms.portes;

import resources.ImagePaths;

/**
 * Class qui genere la porte du bas avec clee
 */
public class BottomKeyDoor extends BottomDoor {

	/**
	 * constructeur de porte avec necessite d'une clee en bas de la room
	 * 
	 * @param idSalle identifiant de la salle vers qui mene la porte
	 */
	public BottomKeyDoor(int idSalle) {
		super(idSalle);
		// la porte est initialement ferme
		this.imagePaths = ImagePaths.PORTE_OBJET_FERME;
		estOuvert = false;
	}
}
