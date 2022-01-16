package gameobjects.personnages.monstres;

import java.util.List;

import gameobjects.personnages.hero.Hero;
import gameobjects.projectiles.FlyProjectile;
import libraries.Vector2;
import resources.ImagePaths;
import resources.MonstreInfo;

/**
 * Class de mouche
 */
public class Fly extends Monster {

	private int countDownTir;

	/**
	 * Constructeur de fly
	 * 
	 * @param position position initiale
	 */
	public Fly(Vector2 position) {
		super(position, MonstreInfo.FLY_SIZE, ImagePaths.FLY, MonstreInfo.FLY_SPEED, MonstreInfo.FLY_pointVie,
				MonstreInfo.FLY_DAMMAGE);
		this.countDownTir = 50;
	}

	@Override
	public void retirepointVie(int i) {
		setPointVie(getPointVie() - i);
	}

	@Override
	public void updateGameObject(Hero e, List<Monster> lsMonster) {

		if (this.freezeMouvement <= 0) {
			move(e, lsMonster);

		} else {
			this.setfreezeMouvement(freezeMouvement - 1);
		}
		if (this.countDownTir > 0) {
			this.countDownTir--;

		} else {

			creeProjectileFly(
					getPosition(), ImagePaths.FLYPROJECTILE,
					new Vector2(e.getPosition().getX() - getPosition().getX(),
							e.getPosition().getY() - getPosition().getY()));
			this.countDownTir = 50;
		}
	}

	/**
	 * creer une larme et qui la stock dans la liste de larme du
	 * personnage
	 * 
	 * @param e larme
	 */
	public void creeProjectileFly(Vector2 position, String imagePath, Vector2 direction) {
		if (this.countDownTir <= 0) {
			super.getLstProjectile().add(new FlyProjectile(position, direction, degatCorpsACorps));
			this.countDownTir = 50;
		}

	}

	/**
	 * mets en mouvement le monstre
	 */
	public void move(Hero e, List<Monster> lsMonster) {

		this.setDirection(new Vector2(this.getPosition().getX() - e.getPosition().getX(),
				this.getPosition().getY() - e.getPosition().getY()).reverse());
		this.setLastposition(this.getPosition());
		Vector2 normalizedDirection = getNormalizedDirection();
		Vector2 positionAfterMoving = getPosition().addVector(normalizedDirection);
		setPosition(positionAfterMoving);
		super.setDirection(new Vector2());
	}

	/**
	 * GETTERS / SETTERS
	 */

	public int getcountDownTir() {
		return countDownTir;
	}

	public void setcountDownTir(int countDownTir) {
		this.countDownTir = countDownTir;
	}

}
