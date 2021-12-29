package gameWorld.rooms;

import gameobjects.personnages.Hero;
import libraries.StdDraw;

/**
 * Room de depart.
 */
public class RoomSpawn extends Room {

    private static final String MON_TYPE = "SPAWN_ROOM";

    public RoomSpawn(Hero hero) {
        super(hero);
        this.type = MON_TYPE;
        this.id = 0;

        this.bgColor = StdDraw.GREEN;
    }
}
