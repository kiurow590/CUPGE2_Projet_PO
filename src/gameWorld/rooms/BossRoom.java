package gameWorld.rooms;

import gameobjects.objets.consommables.BoxWin;
import gameobjects.personnages.Hero;
import gameobjects.personnages.monstres.Boss;
import libraries.StdDraw;
import libraries.Vector2;
import resources.RoomInfos;

public class BossRoom extends Room {

    private static final String MON_TYPE = "BossRoom";

    public BossRoom(Hero hero, Integer id) {
        super(hero, id);
        // TODO Auto-generated constructor stub
        this.type = MON_TYPE;
        this.bgColor = StdDraw.GRAY;

        this.lsMonster.add(new Boss(new Vector2(0.5, 0.5), 40));

        this.lstObjet.add(new BoxWin(new Vector2(0.5, 0.5)));
    }

    @Override
    public void updateRoom() {
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

        dessineLarme();
        affichageViePiece();
        dessineMonstre();
        if (!this.lsMonster.isEmpty()) {
            Boss b = (Boss) this.lsMonster.get(0);
            b.drawMonster();
        }

        affichageObjets();

    }

}
