package gameWorld.rooms;

import gameobjects.personnages.hero.Hero;
import libraries.StdDraw;
import resources.ImagePaths;

/**
 * classe de secret room
 */
public class SecretRoom extends Room {

    private static final String MON_TYPE = "SECRET_ROOM";

    /**
     * Constructeur de secret room
     * 
     * @param hero hero de la partie
     * @param id   identification de la room
     */
    public SecretRoom(Hero hero, int id) {
        super(hero, id);
        this.type = MON_TYPE;

        this.lstObjet.add(initObjectGift());

    }

    @Override
    public void updateRoom() {

        makeHeroPlay();
        collisionReport();
        nettoyageProj();
    }

    @Override
    public void drawRoom() {

        StdDraw.picture(0.5, 0.5, ImagePaths.SECRET_ROOM, 1, 1);

        dessinePorte();
        hero.drawGameObject();
        dessinePorte();
        dessineLarme();
        affichageViePiece();
        affichageObjets();

    }

}
