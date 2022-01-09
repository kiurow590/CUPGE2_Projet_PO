package gameWorld.rooms;

import gameobjects.objets.consommables.BoxWin;
import gameobjects.personnages.Hero;
import gameobjects.personnages.monstres.Boss;
import libraries.StdDraw;
import libraries.Vector2;
import resources.ImagePaths;
import resources.RoomInfos;

/**
 * Class qui genere une room avec un boss dedans
 */
public class BossRoom extends Room {

    private static final String MON_TYPE = "BossRoom";

    /**
     * Constructeur de Boss Romm
     * 
     * @param hero le hero
     * @param id   id de la room
     */
    public BossRoom(Hero hero, Integer id) {
        super(hero, id);
        // TODO Auto-generated constructor stub
        this.type = MON_TYPE;
        this.bgColor = StdDraw.GRAY;

        this.lsMonster.add(new Boss(new Vector2(0.5, 0.5), this));

        this.lstObjet.add(new BoxWin(new Vector2(0.5, 0.5), this));
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
        // StdDraw.setPenColor(this.bgColor);
        // for (int i = 0; i < RoomInfos.NB_TILES; i++) {
        // for (int j = 0; j < RoomInfos.NB_TILES; j++) {
        // Vector2 position = positionFromTileIndex(i, j);
        // StdDraw.filledRectangle(position.getX(), position.getY(),
        // RoomInfos.HALF_TILE_SIZE.getX(),
        // RoomInfos.HALF_TILE_SIZE.getY());
        // }
        // }

        StdDraw.picture(0.5, 0.5, ImagePaths.BOSS_ROOM, 1, 1);

        // this.drawWall();

        hero.drawGameObject();
        dessinePorte();

        dessineLarme();
        affichageViePiece();
        dessineMonstre();

        affichageObjets();

    }

}
