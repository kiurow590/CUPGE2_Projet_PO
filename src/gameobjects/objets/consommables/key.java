package gameobjects.objets.consommables;

import gameobjects.personnages.Hero;
import libraries.Vector2;
import resources.ImagePaths;

public class key extends ConsommableObject {
	public key (Vector2 position, int prix, int value){
		super(position, prix,value);
		this.imagePath=ImagePaths.KEY;
	}
	 @Override
	    public void updateHeroPerf(Hero e) {

	        super.setEstRamasser(true);
	        
	 }
}
