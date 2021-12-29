package gameWorld.rooms;

import gameobjects.personnages.Hero;
import libraries.StdDraw;
import libraries.Vector2;
import resources.RoomInfos;

public class ShopRoom extends Room {

    private static final String MON_TYPE = "SHOP_ROOM";

    public ShopRoom(Hero hero, Integer id) {
        super(hero, id);
        this.type = MON_TYPE;
        this.bgColor = StdDraw.BOOK_RED;
        double x = 0.2;
        for (int i = 0; i < 3; i++) {
            this.lstObjet.add(initObjectGift());
            this.lstObjet.get(i).setPosition(new Vector2(x, 0.5));
            x += 0.2;
        }
    }

    @Override
    public void updateRoom() {
        // TODO Auto-generated method stub
        makeHeroPlay();
        collisionReport();
        nettoyageLarme();
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
