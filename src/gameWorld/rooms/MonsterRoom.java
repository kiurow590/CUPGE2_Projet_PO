package gameWorld.rooms;

import gameobjects.personnages.Hero;
import libraries.StdDraw;
import libraries.Vector2;
import resources.RoomInfos;

/**
 * Room avec des monstres dedans.
 */
public class MonsterRoom extends Room {

    private static final String MON_TYPE = "MONSTER_ROOM";

    public MonsterRoom(Hero hero, Integer id) {
        super(hero, id);
        this.type = MON_TYPE;
        this.bgColor = StdDraw.PINK;
        initMonster();
        this.lstObjet.add(initObjectGift());
    }

    @Override
    public void updateRoom() {
        // TODO Auto-generated method stub
        makeHeroPlay();
        makeMonsterPlay();
        collisionReport();
        rammasseMonstreMort();
        nettoyageLarme();
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

        this.drawWall();

        hero.drawGameObject();
        dessinePorte();

        dessineMonstre();
        dessineLarme();
        affichageViePiece();

        affichageObjets();
    }
}
