package gameobjects.personnages.monstres;

import java.util.ArrayList;
import java.util.List;

import gameobjects.personnages.Hero;
import libraries.StdDraw;
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
    private String imagePath;
    private int compteurDeplacement;
    private int compteurGeneration;
    private List<Monster> lstMonstreBoss;

    /**
     * Constructeur de boss
     * 
     * @param position position initiale du Boss
     */
    public Boss(Vector2 position) {
        super(position, MonstreInfo.BOSS_SIZE, MonstreInfo.BOSS_SPEED, MonstreInfo.BOSS_PV, MonstreInfo.BOSS_DAMMAGE);
        this.imagePath = ImagePaths.SPIDER;
        this.compteurDeplacement = MonstreInfo.BOSS_Move_Delay;
        this.lstMonstreBoss = new ArrayList<>();

        this.compteurGeneration = 200;
    }

    @Override
    public void retirePV(int i) {
        this.setPtDeVie(getPtDeVie() - i);

    }

    @Override
    public void drawGameObject() {
        StdDraw.picture(getPosition().getX(), getPosition().getY(), this.imagePath, getSize().getX(), getSize().getY(),
                0);
        StdDraw.setPenColor();
        StdDraw.rectangle(getPosition().getX(), getPosition().getY(), getSize().getX() / 2, getSize().getY() / 2);

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
        // pour chaque monstre de ma liste
        for (int i = 0; i < this.lstMonstreBoss.size(); i++) {
            // je les mets a jour
            this.lstMonstreBoss.get(i).updateGameObject(e, lstMonstreBoss);
        }
    }

    /**
     * Methode qui dessine les monstre du Boss et leur projectile si il y en a
     */
    public void drawMonster() {
        for (int i = 0; this.lstMonstreBoss != null && i < this.lstMonstreBoss.size(); i++) {
            this.lstMonstreBoss.get(i).drawGameObject();

            for (int j = 0; this.lstMonstreBoss.get(i).getLstProjectile() != null
                    && j < this.lstMonstreBoss.get(i).getLstProjectile().size(); j++) {
                if (this.lstMonstreBoss.get(i).getLstProjectile().get(j).getPortee() > 0) {
                    this.lstMonstreBoss.get(i).getLstProjectile().get(j).updateGameObject();
                    this.lstMonstreBoss.get(i).getLstProjectile().get(j).drawGameObject();
                } else {
                    this.lstMonstreBoss.get(i).getLstProjectile().remove(j);
                }
            }

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
            this.lstMonstreBoss.add(new Spider(getPosition()));
        } else {
            this.lstMonstreBoss.add(new Fly(getPosition()));
        }

    }

    public List<Monster> getLstMonstreBoss() {
        return this.lstMonstreBoss;
    }

}
