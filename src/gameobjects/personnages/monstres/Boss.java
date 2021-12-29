package gameobjects.personnages.monstres;

import java.util.ArrayList;
import java.util.List;

import gameobjects.personnages.Hero;
import libraries.StdDraw;
import libraries.Vector2;
import resources.ImagePaths;

public class Boss extends Monster {

    private String imagePath;
    private int compteurDeplacement;
    private int compteurGeneration;

    private List<Monster> lstMonstreBoss;

    public Boss(Vector2 position, int compteurDeplacement) {
        super(position, new Vector2(0.10, 0.10), 0.03, 20, 1);
        this.imagePath = ImagePaths.SPIDER;
        this.compteurDeplacement = compteurDeplacement;
        this.lstMonstreBoss = new ArrayList<>();

        this.compteurGeneration = 200;
    }

    @Override
    public void retirePV(int i) {
        this.setPtDeVie(getPtDeVie() - i);

    }

    @Override
    public void drawGameObject() {
        StdDraw.picture(getPosition().getX(), getPosition().getY(), getImagePath(), getSize().getX(), getSize().getY(),
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
        for (int i = 0; i < this.lstMonstreBoss.size(); i++) {
            this.lstMonstreBoss.get(i).updateGameObject(e, lstMonstreBoss);
        }
    }

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
     * Methode qui mets en mouvement l'araignee
     */
    private void move(Hero e, List<Monster> lstMonstreBoss) {
        /**
         * Collision entre mob ici !
         */

        this.setDirection(new Vector2(this.getPosition().getX() - e.getPosition().getX(),
                this.getPosition().getY() - e.getPosition().getY()).reverse());

        Vector2 normalizedDirection = getNormalizedDirection();
        Vector2 positionAfterMoving = getPosition().addVector(normalizedDirection);
        setPosition(positionAfterMoving);
        super.setDirection(new Vector2());
    }

    public String getImagePath() {
        return this.imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public void GenereMonstre() {
        double randomNumber = Math.random();

        if (randomNumber < 0.5) {
            this.lstMonstreBoss.add(new Spider(getPosition()));
        } else {
            this.lstMonstreBoss.add(new Fly(getPosition()));
        }

    }

    public int getCompteurDeplacement() {
        return this.compteurDeplacement;
    }

    public void setCompteurDeplacement(int compteurDeplacement) {
        this.compteurDeplacement = compteurDeplacement;
    }

    public int getCompteurGeneration() {
        return this.compteurGeneration;
    }

    public void setCompteurGeneration(int compteurGeneration) {
        this.compteurGeneration = compteurGeneration;
    }

    public List<Monster> getLstMonstreBoss() {
        return this.lstMonstreBoss;
    }

    public void setLstMonstreBoss(List<Monster> lstMonstreBoss) {
        this.lstMonstreBoss = lstMonstreBoss;
    }

}
