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

	/**
	 * Methode qui retire les point de vie d'une mouche
	 * 
	 * @implNote Methode qui aurait dans le futur un parametre projectile indiquant
	 *           combien de pv retiré suivant l'attaque reçu
	 */
	public void retirePV() {
		// TODO: rajouter parametre pour retirer n PV
		setPtDeVie(super.getPtDeVie() - 1);
	}

	/**
	 * Methode qui dessine la mouche dans le jeu
	 */
	public void drawGameObject() {
		StdDraw.picture(getPosition().getX(), getPosition().getY(), getImagePath(), getSize().getX(), getSize().getY(),
				0);
	}

	/**
	 * Methode qui mets a jour l'objet du jeu (position vitesse ...etc.)
	 */
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
