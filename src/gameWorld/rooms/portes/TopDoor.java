package gameWorld.rooms.portes;

import libraries.Vector2;

/**
 * Class qui genere les porte du haut
 */
public class TopDoor extends Door {

    public TopDoor(int idSalle) {
        super(new Vector2(0.5, 0.89), idSalle, 0);
    }

}
