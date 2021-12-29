package gameWorld.rooms;

import gameobjects.personnages.Hero;
import libraries.StdDraw;
import libraries.Vector2;
import resources.RoomInfos;

/**
 * Room de depart.
 */
public class SpawnRoom extends Room {

    private static final String MON_TYPE = "SPAWN_ROOM";

    public SpawnRoom(Hero hero) {
        super(hero, 0);
        this.type = MON_TYPE;

        this.bgColor = StdDraw.GREEN;
    }

    @Override
    public void updateRoom() {
        // TODO Auto-generated method stub
        makeHeroPlay();
        nettoyageProj();
    }

    @Override
    public void drawRoom() {
        // For every tile, set background color.
        StdDraw.setPenColor(this.bgColor);
        for (int i = 0; i < RoomInfos.NB_TILES; i++) {
            for (int j = 0; j < RoomInfos.NB_TILES; j++) {
                Vector2 position = positionFromTileIndex(i, j);
                StdDraw.filledRectangle(position.getX(), position.getY(), RoomInfos.HALF_TILE_SIZE.getX(),
                        RoomInfos.HALF_TILE_SIZE.getY());
            }
        }

        dessinePorte();
        this.drawWall();

        hero.drawGameObject();
        dessinePorte();
        dessineLarme();
        affichageViePiece();
    }

    // public void updateRoom() {
    //
    // /*
    // * makeMonsterPlay();
    // *
    // * collisionReport();
    // * rammasseMonstreMort();
    // * nettoyageLarme();
    // * nettoyageProj();
    // */
    // }
}
