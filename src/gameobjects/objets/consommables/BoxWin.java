package gameobjects.objets.consommables;

import gameobjects.personnages.Hero;
import libraries.StdDraw;
import libraries.Vector2;
import resources.ImagePaths;
import resources.RoomInfos;

/**
 * Class generant le Pentacle de la victoire
 */
public class BoxWin extends ConsommableObject {
    /**
     * Attributs
     */
    private String imagePath;

    /**
     * Constructeur de Pentacle de la victoire
     * 
     * @param position position voulu
     */
    public BoxWin(Vector2 position) {
        super(position);
        this.size = RoomInfos.TILE_SIZE.scalarMultiplication(0.6);
        this.imagePath = ImagePaths.PENTAGRAM;
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

        super.setEstRamasser(true);
        e.setAGagner(true);
    }

}
