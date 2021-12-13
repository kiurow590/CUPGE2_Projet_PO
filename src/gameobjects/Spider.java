package gameobjects;


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

	/**
	 * Methode qui retire les point de vie d'une araignee
	 * 
	 * @implNote Methode qui aurait dans le futur un parametre projectile indiquant
	 *           combien de pv retiré suivant l'attaque reçu
	 */
	public void retirePV() {
		// TODO: rajouter parametre pour retirer n PV
		setPtDeVie(super.getPtDeVie() - 1);
	}

	/**
	 * Methode qui dessine l araignee dans le jeu
	 */
	public void drawGameObject() {
		StdDraw.picture(getPosition().getX(), getPosition().getY(), getImagePath(), getSize().getX(), getSize().getY(),
				0);
	}

	/**
	 * Methode qui mets a jour l'objet du jeu (position vitesse ...etc.)
	 */
	public void updateGameObject() {
		if (this.compteur == 0) {
			move();
			this.compteur = 40;
		} else {
			this.compteur--;
		}

	}

	/**
	 * Methode qui mets en mouvement l'araignee
	 */
	private void move() {
		double i = Math.random();
		if (i >= 0 && i < 0.25) {
			goUpNext();
		} else if (i >= 0.25 && i < 0.5) {
			goDownNext();
		} else if (i >= 0.5 && i < 0.75) {
			goLeftNext();
		} else if (i >= 0.75 && i < 1) {
			goRightNext();
		}
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

	public int getCompteur() {
		return compteur;
	}

	public void setCompteur(int compteur) {
		this.compteur = compteur;
	}

}
