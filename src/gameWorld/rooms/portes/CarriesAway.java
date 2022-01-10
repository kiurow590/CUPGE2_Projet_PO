package gameWorld.rooms.portes;

import resources.ImagePaths;
import resources.RoomInfos;

/**
 * class de porteAuLoin
 */
public class CarriesAway extends Door {
    /**
     * Constructeur de porte au loin
     * 
     * @param idSalle id de la destination
     */
    public CarriesAway(int idSalle) {
        super(RoomInfos.POSITION_CENTER_OF_ROOM, idSalle, 0);
        this.size = RoomInfos.TILE_SIZE.scalarMultiplication(0.6);
        this.imagePaths = ImagePaths.STIGMATA;
    }

}
