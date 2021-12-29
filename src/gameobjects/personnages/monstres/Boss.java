package gameobjects.personnages.monstres;

import java.util.ArrayList;
import java.util.List;

import gameobjects.personnages.Hero;
import libraries.StdDraw;
import libraries.Vector2;
import resources.ImagePaths;

public class Boss extends Monstre {

    private String imagePath;
    private int compteur;

    private List<Monstre> lstMonstre;

    /**
     * Constructeur de fly
     * 
     * @param position         position initiale de lu monstre
     * @param size             taille du monstre
     * @param imagePath        image du montre
     * @param speed            vitesse du monstre
     * @param direction        direction initiale du montre // souvent mise a null a
     *                         l'init
     * @param ptDeVie          point de vie du monstre
     * @param degatCorpsACorps degat au corps a corps du montre
     */
    public Boss(Vector2 position, Vector2 size, String imagePath, double speed, Vector2 direction, int ptDeVie,
            int degatCorpsACorps, int compteur) {
        super(position, size, speed, ptDeVie, degatCorpsACorps);
        this.imagePath = ImagePaths.SPIDER;
        this.compteur = compteur;
        lstMonstre = new ArrayList<>();
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
    public void updateGameObject(Hero e, List<Monstre> lsMonster) {
        if (this.compteur == 0) {
            move(e, lsMonster);
            this.compteur = 40;
        } else {
            this.compteur--;
        }

    }

    /**
     * Methode qui mets en mouvement l'araignee
     */
    private void move(Hero e, List<Monstre> lsMonster) {
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
            lstMonstre.add(new Spider(getPosition()));
        } else {
            lstMonstre.add(new Fly(getPosition()));
        }

    }

}
