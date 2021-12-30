package gameobjects.personnages.monstres;

import java.util.List;

import gameobjects.personnages.Hero;
import gameobjects.projectiles.Projectile;
import libraries.StdDraw;
import libraries.Vector2;
import resources.ImagePaths;
import resources.MonstreInfo;
import resources.RoomInfos;

public class Fly extends Monster {
	private String imagePath;

	private int compteurTir;

	/**
	 * Constructeur de fly
	 * 
	 * @param position position initiale
	 */
	public Fly(Vector2 position) {
		super(position, MonstreInfo.FLY_SIZE, MonstreInfo.FLY_SPEED, MonstreInfo.FLY_PV,
				MonstreInfo.FLY_DAMMAGE);
		this.imagePath = ImagePaths.FLY;
		this.compteurTir = 50;
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
		if (this.compteurTir > 0) {
			this.compteurTir--;

		} else {

			creeLarmeFly(
					new Projectile(getPosition(), RoomInfos.TILE_SIZE.scalarMultiplication(0.2), ImagePaths.TEAR,
							0.01, new Vector2(e.getPosition().getX() - getPosition().getX(),
									e.getPosition().getY() - getPosition().getY()),
							30, 1));
			this.compteurTir = 50;
		}
	}

	/**
	 * Methode qui creer une larme et qui la stock dans la liste de larme du
	 * personnage
	 * 
	 * @param e larme
	 */
	public void creeLarmeFly(Projectile e) {
		if (this.compteurTir <= 0) {
			super.getLstProjectile().add(e);
			this.compteurTir = 50;
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

	public int getCompteurTir() {
		return compteurTir;
	}

	public void setCompteurTir(int compteurTir) {
		this.compteurTir = compteurTir;
	}

}
