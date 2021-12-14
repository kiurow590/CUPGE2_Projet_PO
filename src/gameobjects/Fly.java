package gameobjects;

import java.util.ArrayList;
import java.util.List;

import libraries.StdDraw;
import libraries.Vector2;
import resources.ImagePaths;
import resources.RoomInfos;

public class Fly extends Monstre {
	private String imagePath;
	private List<FlyProjectile> lstProjectile;

	private int compteurTir;

	/**
	 * Constructeur de fly
	 * 
	 * @param position         position initiale de lu monstre
	 * @param size             taille du monstre
	 * @param imagePath        image du montre
	 * @param speed            vitesse du monstre
	 * @param direction        direction initiale du montre // souvent mise a null a
	 *                         l'init
	 * @param ptDeVie          point de vie du monstre
	 * @param degatCorpsACorps degat au corps a corps du montre
	 */
	public Fly(Vector2 position, Vector2 size, String imagePath, double speed, Vector2 direction, int ptDeVie,
			int degatCorpsACorps) {
		super(position, size, speed, direction, ptDeVie, degatCorpsACorps);
		this.imagePath = imagePath;
		this.compteurTir = 50;
		lstProjectile = new ArrayList<FlyProjectile>();
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
		if (this.compteurTir > 0) {
			this.compteurTir--;

		} else {

			creeLarme(new FlyProjectile(getPosition(), RoomInfos.TILE_SIZE.scalarMultiplication(0.2), ImagePaths.TEAR,
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
	public void creeLarme(FlyProjectile e) {
		if (this.compteurTir <= 0) {
			this.lstProjectile.add(e);
			this.compteurTir = 50;
		}

	}

	/**
	 * Methode qui mets en mouvement le monstre
	 */
	public void move(Hero e) {

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

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public List<FlyProjectile> getLstProjectile() {
		return lstProjectile;
	}

	public void setLstProjectile(List<FlyProjectile> lstProjectile) {
		this.lstProjectile = lstProjectile;
	}

	public int getCompteurTir() {
		return compteurTir;
	}

	public void setCompteurTir(int compteurTir) {
		this.compteurTir = compteurTir;
	}

}
