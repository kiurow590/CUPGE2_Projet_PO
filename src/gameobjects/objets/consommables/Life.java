package gameobjects.objets.consommables;

import gameobjects.personnages.Hero;
import libraries.StdDraw;
import libraries.Vector2;
import resources.ImagePaths;

/**
 * Class generatrice de Point de Vie
 */
public class Life extends ConsommableObject {
    /**
     * Attribut
     */
    private String imagePath;

    public Life(int value, Vector2 position) {

        super(position, 0);

        this.value = value;

        if (this.value == 1) {
            imagePath = ImagePaths.HALF_HEART_HUD;
            this.prix = 2;
        } else if (this.value == 2) {
            imagePath = ImagePaths.HEART_HUD;
            this.prix = 4;

        }
    }

    @Override
    public void drawGameObject() {
        StdDraw.picture(getPosition().getX(), getPosition().getY(), imagePath, getSize().getX(),
                getSize().getY(),
                0);
        StdDraw.setPenColor();
        StdDraw.rectangle(getPosition().getX(), getPosition().getY(), getSize().getX() / 2, getSize().getY() / 2);
    }

    @Override
    public void updateHeroPerf(Hero e) {
        if (e.getpointVie() + value <= e.getMaxpointVie() && super.EstRamasser() == false) {
            e.addpointVie(value);
            super.setEstRamasser(true);
        }

    }

}
