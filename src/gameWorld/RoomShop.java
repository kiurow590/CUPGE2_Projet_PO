package gameWorld;

import gameobjects.Hero;
import libraries.StdDraw;

public class RoomShop extends Room {

    private static final String MON_TYPE = "SHOP_ROOM";

    public RoomShop(Hero hero) {
        super(hero);
        this.type = MON_TYPE;
        this.bgColor = StdDraw.BOOK_RED;
        this.id = 2;
    }

}
