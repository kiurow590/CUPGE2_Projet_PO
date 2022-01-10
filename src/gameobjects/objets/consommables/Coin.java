package gameobjects.objets.consommables;

import gameobjects.personnages.Hero;
import libraries.Vector2;
import resources.ImagePaths;

/**
 * Class generatrice de piece d'argent
 */
public class Coin extends ConsommableObject {

    /**
     * Constructeur de piece
     * 
     * @param value    valeur de la piece
     * @param position position de la piece
     */
    public Coin(int value, Vector2 position) {

        super(position, 0, value);

        if (this.value == 1) {
            this.imagePath = ImagePaths.COIN;
        } else if (this.value == 5) {
            this.imagePath = ImagePaths.NICKEL;

        } else if (this.value == 10) {
            this.imagePath = ImagePaths.DIME;

        }

    }

    @Override
    public void updateHeroPerf(Hero e) {
        // si je peux encore stocker de l'argent dans ma stack et que l'objet est pas
        // encore ramasser
        if (e.getStackArgent() + value <= e.getsoldeStackMax() && super.EstRamasser() == false) {
            e.AjoutStackArgent(value);
            super.setEstRamasser(true);
        }
    }

}