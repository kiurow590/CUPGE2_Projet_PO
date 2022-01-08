package gameobjects.personnages.monstres;

import java.util.List;

import gameWorld.rooms.Room;
import gameobjects.personnages.Hero;
import libraries.Vector2;
import resources.ImagePaths;
import resources.MonstreInfo;

/**
 * Class generant un boss
 */
public class Boss extends Monster {
    /**
     * Attributs
     */

    private int compteurDeplacement;
    private int compteurGeneration;

    private Room currentRoom;

    /**
     * Constructeur de boss
     * 
     * @param position position initiale du Boss
     */
    public Boss(Vector2 position, Room currentRoom) {
        super(position, MonstreInfo.BOSS_SIZE, ImagePaths.BLUBBER, MonstreInfo.BOSS_SPEED, MonstreInfo.BOSS_pointVie,
                MonstreInfo.BOSS_DAMMAGE);
        this.compteurDeplacement = MonstreInfo.BOSS_Move_Delay;
        this.currentRoom = currentRoom;
        this.compteurGeneration = 120;
    }

    @Override
    public void retirepointVie(int i) {
        this.setPointVie(getPointVie() - i);

    }

    @Override
    public void updateGameObject(Hero e, List<Monster> lstMonstreBoss) {
        if (this.compteurDeplacement == 0) {
            move(e, lstMonstreBoss);

            this.compteurDeplacement = 40;
        } else {
            this.compteurDeplacement--;
        }
        if (compteurGeneration <= 0) {
            GenereMonstre();
            compteurGeneration = 200;
        } else {
            compteurGeneration--;
        }

    }

    /**
     * Methode qui mets en mouvement le boss
     */
    private void move(Hero e, List<Monster> lstMonstreBoss) {
        this.setDirection(new Vector2(this.getPosition().getX() - e.getPosition().getX(),
                this.getPosition().getY() - e.getPosition().getY()).reverse());

        Vector2 normalizedDirection = getNormalizedDirection();
        Vector2 positionAfterMoving = getPosition().addVector(normalizedDirection);
        setPosition(positionAfterMoving);
        super.setDirection(new Vector2());
    }

    /**
     * Methode qui genere des monstres
     */
    public void GenereMonstre() {
        double randomNumber = Math.random();

        if (randomNumber < 0.5) {
            currentRoom.getLsMonster().add(new Spider(getPosition()));
        } else {
            currentRoom.getLsMonster().add(new Fly(getPosition()));
        }

    }

}
