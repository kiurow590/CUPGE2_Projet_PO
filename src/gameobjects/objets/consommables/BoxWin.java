package gameobjects.objets.consommables;

import gameobjects.personnages.Hero;
import libraries.StdDraw;
import libraries.Vector2;
import resources.ImagePaths;
import resources.RoomInfos;

public class BoxWin extends ConsommableObject {

    private String imagePath;

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
