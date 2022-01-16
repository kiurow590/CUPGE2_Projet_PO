package gameobjects.objets.consommables;

import gameWorld.rooms.Room;
import gameobjects.personnages.hero.Hero;
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

    /**
     * met a jour le hero en fonction des caracteristique de l'objet , ici la
     * victoire
     * 
     * @param Hero le hero sur qui l'objet serra actif
     */
    @Override
    public void updateHeroPerf(Hero e) {

        super.setEstRamasser(true);
        roomWon();

    }

    /**
     * change le statut de la partie en gagne en appelant SetAGagner
     */
    private void roomWon() {
        currentRoom.setAGagner(true);
    }

}
