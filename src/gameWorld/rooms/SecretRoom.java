package gameWorld.rooms;

import gameobjects.personnages.Hero;
import libraries.StdDraw;
import libraries.Vector2;
import resources.ImagePaths;
import resources.RoomInfos;

/**
 * classe de secret room
 */
public class SecretRoom extends Room {

    private static final String MON_TYPE = "SECRET_ROOM";

    /**
     * Constructeur de secret room
     * 
     * @param hero
     */
    public SecretRoom(Hero hero, int id) {
        super(hero, id);
        this.type = MON_TYPE;

        this.bgColor = StdDraw.CYAN;
        this.lstObjet.add(initObjectGift());

    }

    @Override
    public void updateRoom() {
        // TODO Auto-generated method stub

        makeHeroPlay();
        collisionReport();
        nettoyageProj();
    }

    @Override
    public void drawRoom() {
        // For every tile, set background color.
        // StdDraw.setPenColor(this.bgColor);
        // for (int i = 0; i < RoomInfos.NB_TILES; i++) {
        // for (int j = 0; j < RoomInfos.NB_TILES; j++) {
        // Vector2 position = positionFromTileIndex(i, j);
        // StdDraw.filledRectangle(position.getX(), position.getY(),
        // RoomInfos.HALF_TILE_SIZE.getX(),
        // RoomInfos.HALF_TILE_SIZE.getY());
        // }
        // }
        StdDraw.picture(0.5, 0.5, ImagePaths.SECRET_ROOM, 1, 1);

        dessinePorte();
        hero.drawGameObject();
        dessinePorte();
        dessineLarme();
        affichageViePiece();
        affichageObjets();

    }

}
