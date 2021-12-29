package gameWorld.rooms;

import gameobjects.personnages.Hero;
import libraries.StdDraw;

public class BossRoom extends Room {

    private static final String MON_TYPE = "BossRoom";

    public BossRoom(Hero hero, Integer id) {
        super(hero, id);
        // TODO Auto-generated constructor stub
        this.type = MON_TYPE;
        this.bgColor = StdDraw.GRAY;

    }

}
