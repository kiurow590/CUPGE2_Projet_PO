package gameobjects.projectiles;

import libraries.StdDraw;
import libraries.Vector2;
import resources.RoomInfos;

/**
 * Class Generique projectile
 */
public class Projectile {
    Vector2 position;
    Vector2 size;
    String imagePath;
    double speed;
    Vector2 direction;
    int portee;
    int damage;

    /**
     * Constructeur de Projectile
     * 
     * @param position  position initiale de la larme
     * 
     * @param imagePath image representant la larme
     * 
     * @param direction direction de la larme --> 4 cas de figure :
     *                  <ul>
     *                  <li>(1,0) --> la larme sera en mouvement vers la gauche</li>
     *                  <li>(-1,0) --> la larme sera en mouvement vers la droite
     *                  </li>
     *                  <li>(0,1) --> la larme sera en mouvement vers le haut</li>
     *                  <li>(0,-1) --> la larme sera en mouvement vers la bas</li>
     *                  </ul>
     */
    public Projectile(Vector2 position, String imagePath, Vector2 direction, int damage) {
        this.position = position;
        this.size = RoomInfos.TILE_SIZE.scalarMultiplication(0.2);
        this.imagePath = imagePath;
        this.speed = 0.01;
        this.direction = direction;
        this.portee = 40;
        this.damage = damage;
    }

    /**
     * Methode qui mets a jour l'objet du jeu (position vitesse ...etc.)
     */
    public void updateGameObject() {
        if (this.portee > 0) {
            move();
            verifPositionValide();
        }

    }

    /**
     * Methode qui verifie si le projectile n'est pas hors de l'ecran
     */
    private void verifPositionValide() {
        if (getPosition().getX() < 0.08 || getPosition().getX() > 0.92 || getPosition().getY() < 0.08
                || getPosition().getY() > 0.92) {
            this.portee = 0;
        }

    }

    /**
     * Methode qui mets en mouvement de la larme
     */
    private void move() {
        Vector2 normalizedDirection = getNormalizedDirection();
        Vector2 positionAfterMoving = getPosition().addVector(normalizedDirection);
        setPosition(positionAfterMoving);
        this.portee--;

    }

    /**
     * Methode qui dessine la larme dans le jeu
     */
    public void drawGameObject() {
        StdDraw.picture(getPosition().getX(), getPosition().getY(), imagePath, getSize().getX(), getSize().getY(),
                0);
    }

    /**
     * Methode qui normalise le vecteur direction de la larme
     * 
     * @return le vecteur normaliser
     */
    public Vector2 getNormalizedDirection() {
        Vector2 normalizedVector = new Vector2(direction);
        normalizedVector.euclidianNormalize(speed);
        return normalizedVector;
    }

    /*
     * Moving from key inputs. Direction vector is later normalised.
     */
    public void goUpNext() {
        direction.addY(1);
    }

    public void goDownNext() {
        direction.addY(-1);
    }

    public void goLeftNext() {
        direction.addX(-1);
    }

    public void goRightNext() {
        direction.addX(1);
    }

    /**
     * GETTERS / SETTERS
     */

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public Vector2 getSize() {
        return size;
    }

    public int getPortee() {
        return portee;
    }

    public void setPortee(int portee) {
        this.portee = portee;
    }

    public int getdamage() {
        return damage;
    }

    public void setdamage(int damage) {
        this.damage = damage;
    }
}
