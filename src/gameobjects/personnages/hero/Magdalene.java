package gameobjects.personnages.hero;

import gameobjects.projectiles.BabyProjectile;
import gameobjects.projectiles.NumberOne_projectiles;
import libraries.Vector2;
import resources.HeroInfos;
import resources.ImagePaths;

/**
 * Class de hero magdelene
 */
public class Magdalene extends Hero {
	/**
	 * Constructeur de Magdalene
	 * 
	 * @param position position de Magdalene dans l'espace
	 */
	public Magdalene(Vector2 position) {
		super(position, ImagePaths.MAGDALENE, HeroInfos.MAGDELENE_SPEED, HeroInfos.MAGDELENE_LIFE,
				HeroInfos.MAGDELENE_LIFE);
		damage = HeroInfos.MAGDELENE_ATTACK;
	}

	@Override
	public void creeProjectile(Vector2 position, Vector2 direction) {
		if (this.countDownTir <= 0) {
			// si le bonus numberOne est activé on change l'image ,la porté ,et la cadence
			if (this.numberOne) {
				this.lstLarme.add(new NumberOne_projectiles(position, ImagePaths.NUMBER_ONE, direction, damage));
				this.countDownTir = 30;
				// sinon on met des larme normal
			} else {
				this.lstLarme.add(new BabyProjectile(position, direction, damage));
				this.countDownTir = 50;
			}
		}

	}

}
