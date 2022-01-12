package gameobjects.personnages.monstres;

import java.util.List;

import gameobjects.personnages.Hero;
import libraries.Vector2;
import resources.DisplaySettings;
import resources.ImagePaths;
import resources.MonstreInfo;

public class Spider extends Monster {

	private int compteur;

	/**
	 * Constructeur de Spider
	 * 
	 * @param position position initiale de l'araigné
	 */
	public Spider(Vector2 position) {
		super(position, MonstreInfo.SPIDER_SIZE, ImagePaths.SPIDER, MonstreInfo.SPIDER_SPEED,
				MonstreInfo.SPIDER_pointVie,
				MonstreInfo.SPIDER_DAMMAGE);
		this.compteur = DisplaySettings.FRAME_PER_SECOND;
		this.setLastposition(new Vector2(0.5,0.5));

	}

	@Override
	public void retirepointVie(int i) {
		// TODO: rajouter parametre pour retirer n pointVie
		setPointVie(super.getPointVie() - i);
	}

	@Override
	public void updateGameObject(Hero e, List<Monster> lsMonster) {
		if (this.compteur == 0) {

			deplacementMonstre();
			move();
			this.compteur = 40;
		} else {
			this.compteur--;
		}

	}

	private void deplacementMonstre() {
		
		double i = Math.random();
		if (i >= 0 && i < 0.25 && getPosition().getY() < 0.9) {
			goUpNext();
		} else if (i >= 0.25 && i < 0.5 && getPosition().getY() > 0.1) {
			goDownNext();
		} else if (i >= 0.5 && i < 0.75 && getPosition().getX() > 0.1) {
			goLeftNext();
		} else if (i >= 0.75 && i < 1 && getPosition().getX() < 0.9) {
			goRightNext();
		}this.setLastposition(this.getPosition());
		
	}

}
