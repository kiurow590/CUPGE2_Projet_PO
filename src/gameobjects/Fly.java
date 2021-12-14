package gameobjects;

import libraries.StdDraw;
import libraries.Vector2;

public class Fly extends Monstre {
	private String imagePath;

	/**
	 * Constructeur de fly
	 * 
	 * @param position
	 * @param size
	 * @param imagePath
	 * @param speed
	 * @param direction
	 * @param ptDeVie
	 * @param degatCorpsACorps
	 */
	public Fly(Vector2 position, Vector2 size, String imagePath, double speed, Vector2 direction, int ptDeVie,
			int degatCorpsACorps) {
		super(position, size, speed, direction, ptDeVie, degatCorpsACorps);
		this.imagePath = imagePath;
	}

	@Override
	public void retirePV(int i) {
		// TODO: rajouter parametre pour retirer n PV
		setPtDeVie(super.getPtDeVie() - i);
	}

	@Override
	public void drawGameObject() {
		StdDraw.picture(getPosition().getX(), getPosition().getY(), getImagePath(), getSize().getX(), getSize().getY(),
				0);
	}

	@Override
	public void updateGameObject(Hero e) {

		move(e);

	}

	/**
	 * Methode qui mets en mouvement l'araignee
	 */

	public void move(Hero e) {

		this.setDirection(new Vector2(this.getPosition().getX() - e.getPosition().getX(),
				this.getPosition().getY() - e.getPosition().getY()).reverse());

		Vector2 normalizedDirection = getNormalizedDirection();
		Vector2 positionAfterMoving = getPosition().addVector(normalizedDirection);
		setPosition(positionAfterMoving);
		super.setDirection(new Vector2());
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

}
