package resources;

import libraries.Vector2;

public class MonstreInfo {

    /**
     * FLY
     */

    public static final double FLY_SPEED = 0.005;
    public static final int FLY_pointVie = 3;
    public static final int FLY_DAMMAGE = 1;
    public static final Vector2 FLY_SIZE = RoomInfos.TILE_SIZE.scalarMultiplication(0.4);

    /**
     * SPIDER
     */
    public static final double SPIDER_SPEED = 0.02;
    public static final int SPIDER_pointVie = 5;
    public static final int SPIDER_DAMMAGE = 1;
    public static final Vector2 SPIDER_SIZE = RoomInfos.TILE_SIZE.scalarMultiplication(0.4);

    /**
     * BOSS
     */
    public static final double BOSS_SPEED = 0.03;
    public static final int BOSS_pointVie = 20;
    public static final int BOSS_DAMMAGE = 1;
    public static final Vector2 BOSS_SIZE = new Vector2(0.10, 0.10);
    public static final int BOSS_Move_Delay = 40;

}
