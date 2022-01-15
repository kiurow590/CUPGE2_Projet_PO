package resources;

import libraries.Keybinding;
import libraries.Keybinding.SpecialKeys;

public class Controls {
	public static int goUp = Keybinding.keycodeOf('z');
	public static int goDown = Keybinding.keycodeOf('s');
	public static int goRight = Keybinding.keycodeOf('d');
	public static int goLeft = Keybinding.keycodeOf('q');
	public static int invincible = Keybinding.keycodeOf('i');
	public static int vitesse = Keybinding.keycodeOf('l');
	public static int killMonster = Keybinding.keycodeOf('k');
	public static int addMoney = Keybinding.keycodeOf('o');
	public static int numberOne = Keybinding.keycodeOf('n');

	public static int puissance = Keybinding.keycodeOf('p');

	public static int hitUp = Keybinding.keycodeOf(SpecialKeys.UP);
	public static int hitDown = Keybinding.keycodeOf(SpecialKeys.DOWN);
	public static int hitRight = Keybinding.keycodeOf(SpecialKeys.RIGHT);
	public static int hitLeft = Keybinding.keycodeOf(SpecialKeys.LEFT);
}
