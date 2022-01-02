package gameobjects.objets.consommables;

import gameobjects.personnages.Hero;
import libraries.StdDraw;
import libraries.Vector2;
import resources.ImagePaths;

/**
 * Class generatrice de piece d'argent
 */
public class Coin extends ConsommableObject {

    /**
     * Attribut
     */
    private String imagePath;
    private int value;

    /**
     * Constructeur de piece
     * 
     * @param value    valeur de la piece
     * @param position position de la piece
     */
    public Coin(int value, Vector2 position) {

        super(position);
        this.value = value;

        if (this.value == 1) {
            this.imagePath = ImagePaths.COIN;
        } else if (this.value == 5) {
            this.imagePath = ImagePaths.NICKEL;

        } else if (this.value == 10) {
            this.imagePath = ImagePaths.DIME;

        }

    }

    @Override
    public void drawGameObject() {
        StdDraw.picture(getPosition().getX(), getPosition().getY(), this.imagePath, getSize().getX(),
                getSize().getY(),
                0);
        StdDraw.setPenColor();
        StdDraw.rectangle(getPosition().getX(), getPosition().getY(), getSize().getX() / 2, getSize().getY() / 2);
    }

    @Override
    public void updateHeroPerf(Hero e) {

        if (e.getStackArgent() + value <= e.getsoldeStackMax() && super.EstRamasser() == false) {
            e.AjoutStackArgent(value);
            super.setEstRamasser(true);
        }
    }

}