package gameWorld.rooms;

import gameobjects.personnages.Hero;
import libraries.StdDraw;

/**
 * Room avec des monstres dedans.
 */
public class RoomMonster extends Room {

    private static final String MON_TYPE = "MONSTER_ROOM";

    public RoomMonster(Hero hero) {
        super(hero);
        this.type = MON_TYPE;
        this.bgColor = StdDraw.PINK;
        this.id = 1;
    }
}
