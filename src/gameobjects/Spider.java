package gameobjects;

import java.util.List;

import libraries.StdDraw;
import libraries.Vector2;

public class Spider extends Monstre {

	private String imagePath;
	private int compteur;

	/**
	 * Constructeur de spider
	 * 
	 * @param position  position de la spider
	 * @param size      taille de la spider
	 * @param imagePath image representant la spider
	 * @param speed     vitesse de la spider
	 * @param direction direction de deplacement de la spider
	 */
	public Spider(Vector2 position, Vector2 size, String imagePath, double speed, Vector2 direction, int ptDeVie,
			int degatCorpsACorps, int compteur) {
		super(position, size, speed, direction, ptDeVie, degatCorpsACorps);
		this.imagePath = imagePath;
		this.compteur = compteur;

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
		StdDraw.rectangle(getPosition().getX(), getPosition().getY(), getSize().getX() / 2, getSize().getY() / 2);
	}

	@Override
	public void updateGameObject(Hero e, List<Monstre> lsMonster) {
		if (this.compteur == 0) {
			move(lsMonster);
			this.compteur = 40;
		} else {
			this.compteur--;
		}

	}

	/**
	 * Methode qui mets en mouvement l'araignee
	 */
	private void move(List<Monstre> lsMonster) {
		/**
		 * Methode qui mets en mouvement le monstre
		 */

		double i = Math.random();
		if (i >= 0 && i < 0.25 && getPosition().getY() < 0.9) {
			goUpNext();
		} else if (i >= 0.25 && i < 0.5 && getPosition().getY() > 0.1) {
			goDownNext();
		} else if (i >= 0.5 && i < 0.75 && getPosition().getX() > 0.1) {
			goLeftNext();
		} else if (i >= 0.75 && i < 1 && getPosition().getX() < 0.9) {
			goRightNext();
		}
		Vector2 normalizedDirection = getNormalizedDirection();
		Vector2 positionAfterMoving = getPosition().addVector(normalizedDirection);
		setPosition(positionAfterMoving);
		super.setDirection(new Vector2());
	}

	/**
	 * GETTERS / SETTERS
	 */

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public int getCompteur() {
		return compteur;
	}

	public void setCompteur(int compteur) {
		this.compteur = compteur;
	}

}
