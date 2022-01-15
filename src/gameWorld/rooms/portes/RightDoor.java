package gameWorld.rooms.portes;

import libraries.Vector2;

/**
 * Class qui genere les portes de droite
 */
public class RightDoor extends Door {
	/**
	 * Constructeur de porte de droite
	 * 
	 * @param idSalle id de la salle d'arriver
	 */
	public RightDoor(int idSalle) {
		super(new Vector2(0.94, 0.5), idSalle, -90);

	}

}
