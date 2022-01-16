package gameobjects.objets.consommables;

import gameobjects.personnages.Hero;
import libraries.Vector2;
import resources.ImagePaths;

public class Key extends ConsommableObject {
	/*constructeur de clef 
	 * @param position , la position de l'objet
	 * @param prix	 , le prix de la clé
	 * @param value , la valeur de l'objet
	 */
	public Key (Vector2 position, int prix, int value){
		super(position, prix,value);
		this.imagePath=ImagePaths.KEY;
	}
	/*
	 * fonction qui gère l'incrémentation du compteur quand on la récupère
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
