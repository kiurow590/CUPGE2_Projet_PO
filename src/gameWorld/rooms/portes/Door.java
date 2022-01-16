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
public abstract class Door {

    private Vector2 position;
    protected Vector2 size;
    private int idSalle;
    private int rotation;
    protected boolean estOuvert;

    String imagePaths;

    /**
     * Methode Generic qui genere les portes
     * 
     * @param position position de la porte
     * @param idSalle  id de la salle de destination
     * @param rotation
     */
    public Door(Vector2 position, int idSalle, int rotation) {
        this.position = position;
        this.size = RoomInfos.TILE_SIZE.scalarMultiplication(1);
        this.idSalle = idSalle;
        this.rotation = rotation;
        this.imagePaths = ImagePaths.CLOSED_DOOR;
    }

    /**
     * dessine l'objet dans le jeu
     */
    public void drawGameObject() {

        StdDraw.picture(this.position.getX(), this.position.getY(), imagePaths, this.size.getX(),
                this.size.getY(),
                this.rotation);
        ;

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

    public void setImagePaths(String imagePaths) {
        this.imagePaths = imagePaths;
    }

    public String getImagePaths() {
        return this.imagePaths;
    }

	public boolean isEstOuvert() {
		return estOuvert;
	}

	public void setEstOuvert(boolean estOuvert) {
		this.estOuvert = estOuvert;
	}


}
