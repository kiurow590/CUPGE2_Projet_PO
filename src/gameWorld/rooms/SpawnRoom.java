package gameWorld.rooms;

import gameobjects.personnages.hero.Hero;
import libraries.StdDraw;
import resources.ImagePaths;

/**
 * Room de depart.
 */
public class SpawnRoom extends Room {

    private static final String MON_TYPE = "SPAWN_ROOM";

    /**
     * Constructeur de Spawn
     * 
     * @param hero hero du jeu
     * @param id   identfaiant de la room
     */
    public SpawnRoom(Hero hero, int id) {
        super(hero, id);
        this.type = MON_TYPE;

    }

    @Override
    public void updateRoom() {
        makeHeroPlay();
        nettoyageProj();
    }

    @Override
    public void drawRoom() {
        // on affiche le fond
        StdDraw.picture(0.5, 0.5, ImagePaths.SPAWN_ROOM, 1, 1);

        dessinePorte();

        hero.drawGameObject();
        dessinePorte();
        dessineLarme();
        affichageViePiece();
    }

}
