package gameWorld.rooms.portes;

import libraries.Vector2;

/**
 * Class qui genere les porte de gauche
 */
public class LeftDoor extends Door {
    /**
     * Constructeur de porte de gauche
     * 
     * @param idSalle id de la salle d'arriver
     */
    public LeftDoor(int idSalle) {
        super(new Vector2(0.06, 0.5), idSalle, 90);
        // TODO Auto-generated constructor stub
    }

}
