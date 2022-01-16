package gameobjects.personnages.hero;

import gameobjects.projectiles.Bomb;
import gameobjects.projectiles.NumberOne_projectiles;
import libraries.Vector2;
import resources.HeroInfos;
import resources.ImagePaths;

/**
 * Class de hero pyrobarbare
 */
public class PyroBarbare extends Hero {
	/**
	 * Constructeur pyrobarbare
	 * 
	 * @param position du pyrobarbare dans l'espace
	 */
	public PyroBarbare(Vector2 position) {
		super(position, ImagePaths.PYROBARBARE, HeroInfos.LILITH_SPEED * 2.5, HeroInfos.LILITH_LIFE,
				HeroInfos.LILITH_LIFE);
		damage = HeroInfos.LILITH_ATTACK;
	}

	@Override
	public void creeProjectile(Vector2 position, Vector2 direction) {
		if (this.countDownTir <= 0) {
			// si le bonus numberOne est activé on change l'image ,la porté ,et la cadence
			if (this.numberOne) {
				this.lstLarme.add(new NumberOne_projectiles(position, ImagePaths.NUMBER_ONE, direction, damage));
				this.countDownTir = 10;
				// sinon on met des larmes normales
			} else {
				this.lstLarme.add(new Bomb(position, direction, damage));
				this.countDownTir = 15;
			}
		}
	}

}
