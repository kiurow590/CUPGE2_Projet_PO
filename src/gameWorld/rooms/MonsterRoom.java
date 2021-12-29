package gameWorld.rooms;

import gameobjects.personnages.Hero;
import libraries.StdDraw;

/**
 * Room avec des monstres dedans.
 */
public class MonsterRoom extends Room {

    private static final String MON_TYPE = "MONSTER_ROOM";

    public MonsterRoom(Hero hero, Integer id) {
        super(hero, id);
        this.type = MON_TYPE;
        this.bgColor = StdDraw.PINK;

    }
}
