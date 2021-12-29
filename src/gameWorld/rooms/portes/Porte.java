package gameWorld.rooms.portes;

import libraries.StdDraw;
import libraries.Vector2;
import resources.ImagePaths;
import resources.RoomInfos;

/**
 * @author Aubry TONNERRE
 * 
 *         Class Porte pour se deplacer entre les salles.
 */
public abstract class Porte {

    private Vector2 position;
    private Vector2 size;
    private int idSalle;
    private int rotation;

    public Porte(Vector2 position, int idSalle, int rotation) {
        this.position = position;
        this.size = RoomInfos.TILE_SIZE.scalarMultiplication(0.7);
        this.idSalle = idSalle;
        this.rotation = rotation;
    }

    /**
     * Methode qui dessine l'objet dans le jeu
     */
    public void drawGameObject() {

        StdDraw.picture(this.position.getX(), this.position.getY(), ImagePaths.OPENED_DOOR, this.size.getX(),
                this.size.getY(),
                this.rotation);
        ;

        StdDraw.setPenColor();
        StdDraw.rectangle(position.getX(), position.getY(), size.getX() / 2, size.getY() / 2);
    }

    public int getIdSalle() {
        return this.idSalle;
    }

    public void setIdSalle(int idSalle) {
        this.idSalle = idSalle;
    }

    public Vector2 getPosition() {
        return this.position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public Vector2 getSize() {
        return this.size;
    }

    public void setSize(Vector2 size) {
        this.size = size;
    }

    public int getRotation() {
        return this.rotation;
    }

    public void setRotation(int rotation) {
        this.rotation = rotation;
    }

}
