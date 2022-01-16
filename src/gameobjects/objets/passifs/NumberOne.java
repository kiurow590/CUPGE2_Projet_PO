package gameobjects.objets.passifs;

import gameobjects.personnages.hero.Hero;
import libraries.Vector2;
import resources.ImagePaths;

public class NumberOne extends PassifObject {
	/**
	 * constructeur de l'objet Number One
	 * @param position , la position de l'objet dans l'espace
	 * 
	 */
    public NumberOne(Vector2 position) {
        super(position, 10, 1);
        this.imagePath = ImagePaths.NUMBER_ONE;

    }
/**
 * gere les changement a opérer lorsque l'objet number one est ramasse .
 * @param e c'est le hero qui prends l'objet en questions
 */
    @Override
    public void updateHeroPerf(Hero e) {

        if (super.EstRamasser() == false) {
            e.numberOne = true;
            super.setEstRamasser(true);

        }
    }

}
