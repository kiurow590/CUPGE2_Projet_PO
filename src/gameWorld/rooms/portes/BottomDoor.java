package gameWorld.rooms.portes;

import libraries.Vector2;

/**
 * Class qui genere la porte du bas
 */
public class BottomDoor extends Door {
    /**
     * Constructeur de porte du bas
     * 
     * @param idSalle salle de destination
     */
    public BottomDoor(int idSalle) {
        super(new Vector2(0.5, 0.11), idSalle, 180);
        // TODO Auto-generated constructor stub
    }

}
