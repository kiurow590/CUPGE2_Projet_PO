package gameWorld.rooms.portes;

import libraries.Vector2;

/**
 * Class qui genere les porte du haut
 */
public class TopDoor extends Door {
    /**
     * Constructeur de porte du haut
     * 
     * @param idSalle id salle d'arriver
     */
    public TopDoor(int idSalle) {
        super(new Vector2(0.5, 0.89), idSalle, 0);
    }

}
