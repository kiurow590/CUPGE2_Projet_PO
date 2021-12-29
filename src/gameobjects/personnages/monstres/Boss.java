package gameobjects.personnages.monstres;

import java.util.ArrayList;
import java.util.List;

import gameobjects.personnages.Hero;
import libraries.StdDraw;
import libraries.Vector2;
import resources.ImagePaths;

public class Boss extends Monster {

    private String imagePath;
    private int compteur;

    private List<Monster> lstMonstreBoss;

    public Boss(Vector2 position, int compteur) {
        super(position, new Vector2(0.10, 0.10), 0.03, 20, 1);
        this.imagePath = ImagePaths.SPIDER;
        this.compteur = compteur;
        this.lstMonstreBoss = new ArrayList<>();
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
        if (this.compteur == 0) {
            move(e, lstMonstreBoss);
            GenereMonstre();
            this.compteur = 40;
        } else {
            this.compteur--;
        }
        for (int i = 0; i < this.lstMonstreBoss.size(); i++) {
            this.lstMonstreBoss.get(i).updateGameObject(e, lstMonstreBoss);
        }
        drawMonster(e);
    }

    private void drawMonster(Hero e) {
        for (int i = 0; i < this.lstMonstreBoss.size(); i++) {
            this.lstMonstreBoss.get(i).drawGameObject();
            ;
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

}
