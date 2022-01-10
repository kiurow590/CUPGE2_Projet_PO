package gameobjects.objets.consommables;

import gameWorld.rooms.Room;
import gameobjects.personnages.Hero;
import libraries.StdDraw;
import libraries.Vector2;
import resources.ImagePaths;
import resources.RoomInfos;

/**
 * Class generant le Pentacle de la victoire
 */
public class BoxWin extends ConsommableObject {
    /**
     * Attributs
     */
    private String imagePath;

    private Room currentRoom;

    /**
     * Constructeur de Pentacle de la victoire
     * 
     * @param position position voulu
     */
    public BoxWin(Vector2 position, Room currentRoom) {
        super(position, 0, 0);
        this.size = RoomInfos.TILE_SIZE.scalarMultiplication(0.6);
        this.imagePath = ImagePaths.BOX_WIN;
        this.currentRoom = currentRoom;
    }

    @Override
    public void drawGameObject() {
        StdDraw.picture(getPosition().getX(), getPosition().getY(), this.imagePath, getSize().getX(),
                getSize().getY(),
                0);
        StdDraw.setPenColor();
        StdDraw.rectangle(getPosition().getX(), getPosition().getY(), getSize().getX() / 2, getSize().getY() / 2);
    }

    @Override
    public void updateHeroPerf(Hero e) {

        super.setEstRamasser(true);
        roomWon();

    }

    private void roomWon() {
        currentRoom.setAGagner(true);
    }

}
