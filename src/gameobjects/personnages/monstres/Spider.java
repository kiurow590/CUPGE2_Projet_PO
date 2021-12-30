package gameobjects.personnages.monstres;

import java.util.List;

import gameobjects.personnages.Hero;
import libraries.StdDraw;
import libraries.Vector2;
import resources.DisplaySettings;
import resources.ImagePaths;
import resources.MonstreInfo;

public class Spider extends Monster {

	private String imagePath;
	private int compteur;

	/**
	 * Constructeur de Spider
	 * 
	 * @param position position initiale de l'araigné
	 */
	public Spider(Vector2 position) {
		super(position, MonstreInfo.SPIDER_SIZE, MonstreInfo.SPIDER_SPEED, MonstreInfo.SPIDER_PV,
				MonstreInfo.SPIDER_DAMMAGE);
		this.imagePath = ImagePaths.SPIDER;
		this.compteur = DisplaySettings.FRAME_PER_SECOND;

	}

	@Override
	public void retirePV(int i) {
		// TODO: rajouter parametre pour retirer n PV
		setPtDeVie(super.getPtDeVie() - i);
	}

	@Override
	public void drawGameObject() {
		StdDraw.picture(getPosition().getX(), getPosition().getY(), imagePath, getSize().getX(), getSize().getY(),
				0);
		StdDraw.rectangle(getPosition().getX(), getPosition().getY(), getSize().getX() / 2, getSize().getY() / 2);
	}

	@Override
	public void updateGameObject(Hero e, List<Monster> lsMonster) {
		if (this.compteur == 0) {
			move(lsMonster);
			this.compteur = 40;
		} else {
			this.compteur--;
		}

	}

	/**
	 * Methode qui mets en mouvement l'araignee de maniere aleatoire
	 */
	private void move(List<Monster> lsMonster) {

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

}
