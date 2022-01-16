package gameobjects.objets.consommables;

import gameobjects.personnages.hero.Hero;
import libraries.Vector2;
import resources.ImagePaths;

public class Key extends ConsommableObject {
	/**
	 * constructeur de clee 
	 * @param position , la position de l'objet
	 * @param prix	 , le prix de la clé
	 * @param value , la valeur additionel de l'objet
	 */
	public Key (Vector2 position, int prix, int value){
		super(position, prix,value);
		this.imagePath=ImagePaths.KEY;
	}
	/**
	 * gère l'incrémentation du compteur quand on la récupère (la clee)
	 * @param hero e , le hero qui joue 
	 */
	 @Override
	    public void updateHeroPerf(Hero e) {
		 if (!isEstRamasser()) {
		 e.setsoldeKey(e.getsoldeKey()+1);
	        super.setEstRamasser(true);
		 }
	        
	 }
}
