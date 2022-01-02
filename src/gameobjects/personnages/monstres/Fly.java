package gameobjects.personnages.monstres;

import java.util.List;

import gameobjects.personnages.Hero;
import gameobjects.projectiles.FlyProjectile;
import libraries.StdDraw;
import libraries.Vector2;
import resources.ImagePaths;
import resources.MonstreInfo;

public class Fly extends Monster {
	private String imagePath;

	private int countDownTir;

	/**
	 * Constructeur de fly
	 * 
	 * @param position position initiale
	 */
	public Fly(Vector2 position) {
		super(position, MonstreInfo.FLY_SIZE, MonstreInfo.FLY_SPEED, MonstreInfo.FLY_PV,
				MonstreInfo.FLY_DAMMAGE);
		this.imagePath = ImagePaths.FLY;
		this.countDownTir = 50;
	}

	@Override
	public void retirePV(int i) {
		setPtDeVie(super.getPtDeVie() - i);
	}

	@Override
	public void drawGameObject() {
		StdDraw.picture(getPosition().getX(), getPosition().getY(), this.imagePath, getSize().getX(), getSize().getY(),
				0);
		StdDraw.setPenColor();
		StdDraw.rectangle(getPosition().getX(), getPosition().getY(), getSize().getX() / 2, getSize().getY() / 2);
	}

	@Override
	public void updateGameObject(Hero e, List<Monster> lsMonster) {

		if (this.getImmobilus() <= 0) {
			move(e, lsMonster);

		} else {
			this.setImmobilus(getImmobilus() - 1);
		}
		if (this.countDownTir > 0) {
			this.countDownTir--;

		} else {

			creeLarmeFly(
					getPosition(), ImagePaths.TEAR,
					new Vector2(e.getPosition().getX() - getPosition().getX(),
							e.getPosition().getY() - getPosition().getY()));
			this.countDownTir = 50;
		}
	}

	/**
	 * Methode qui creer une larme et qui la stock dans la liste de larme du
	 * personnage
	 * 
	 * @param e larme
	 */
	public void creeLarmeFly(Vector2 position, String imagePath, Vector2 direction) {
		if (this.countDownTir <= 0) {
			super.getLstProjectile().add(new FlyProjectile(position, imagePath, direction));
			this.countDownTir = 50;
		}

	}

	/**
	 * Methode qui mets en mouvement le monstre
	 */
	public void move(Hero e, List<Monster> lsMonster) {
		/**
		 * Collision entre mob ici !
		 */

		this.setDirection(new Vector2(this.getPosition().getX() - e.getPosition().getX(),
				this.getPosition().getY() - e.getPosition().getY()).reverse());

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
