package resources;

import libraries.Vector2;

public class HeroInfos {
	public static Vector2 ISAAC_SIZE = RoomInfos.TILE_SIZE.scalarMultiplication(0.7);
	public static final double ISAAC_SPEED = 0.01;
	public static final int ISAAC_ATTACK = 1;

	public static final int ISAAC_TIR_DELAY = 20;
	public static final int ISAAC_LIFE = 6;

	// Magdelene
	public static Vector2 MAGDELENE_SIZE = RoomInfos.TILE_SIZE.scalarMultiplication(0.7);
	public static final double MAGDELENE_SPEED = 0.0085;
	public static final int MAGDELENE_ATTACK = 1;
	public static final int MAGDELENE_TIR_DELAY = 20;
	public static final int MAGDELENE_LIFE = 8;
	// Lilith
	public static Vector2 LILITH_SIZE = RoomInfos.TILE_SIZE.scalarMultiplication(0.7);
	public static final double LILITH_SPEED = 0.01;
	public static final int LILITH_ATTACK = 3;
	public static final int LILITH_TIR_DELAY = 20;
	public static final int LILITH_LIFE = 2;

	public static final int HERO_MAX_STACK = 100;
	public static final int HERO_INVINCIBILITY_DELAY = 50;
}
