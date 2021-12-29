package gameWorld.rooms;

import gameobjects.personnages.Hero;
import libraries.StdDraw;

public class ShopRoom extends Room {

    private static final String MON_TYPE = "SHOP_ROOM";

    public ShopRoom(Hero hero, Integer id) {
        super(hero, id);
        this.type = MON_TYPE;
        this.bgColor = StdDraw.BOOK_RED;
    }

}
