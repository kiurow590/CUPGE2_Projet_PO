package gameWorld.rooms;

import gameobjects.personnages.hero.Hero;
import libraries.StdDraw;
import libraries.Vector2;
import resources.ImagePaths;

/**
 * Class qui genere des room de commerce
 */
public class ShopRoom extends Room {

    private static final String MON_TYPE = "SHOP_ROOM";

    /**
     * Constructeur de shop
     * 
     * @param hero hero de la partie
     * @param id   identifiant de la room
     */
    public ShopRoom(Hero hero, Integer id) {
        super(hero, id);
        this.type = MON_TYPE;
        double x = 0.2;
        for (int i = 0; i < 3; i++) {
            this.lstObjet.add(initObjectGift());
            this.lstObjet.get(i).setPosition(new Vector2(x, 0.5));
            x += 0.2;
        }
    }

    @Override
    public void updateRoom() {
        makeHeroPlay();
        collisionReport();
        nettoyageLarme();
    }

    @Override
    public void drawRoom() {

        StdDraw.picture(0.5, 0.5, ImagePaths.SHOP_ROOM, 1, 1);

        hero.drawGameObject();

        dessinePorte();

        dessineMonstre();
        dessineLarme();
        affichageViePiece();

        affichageObjets();
    }

}
