package gameobjects.objets.passifs;

import gameobjects.personnages.Hero;
import libraries.Vector2;
import resources.ImagePaths;

public class NumberOne extends PassifObject {
	public NumberOne(Vector2 position) {
        super(new Vector2(0.5, 0.5), 10, 1);
        this.imagePath = ImagePaths.NUMBER_ONE;

    }

    @Override
    public void updateHeroPerf(Hero e) {

        if (super.EstRamasser() == false) {
            e.numberOne = true;
            super.setEstRamasser(true);

        }
    }

}
