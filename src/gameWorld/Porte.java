package gameWorld;

import libraries.Vector2;

/**
 * @author Aubry TONNERRE
 * 
 *         Class Porte pour se deplacer entre les salles.
 */
public class Porte {

    private Vector2 position;
    private Vector2 size;
    private int idSalle;
    private int rotation;

    public Porte(Vector2 position, Vector2 size, int idSalle, int rotation) {
        this.position = position;
        this.size = size;
        this.idSalle = idSalle;
        this.rotation = rotation;
    }

}
