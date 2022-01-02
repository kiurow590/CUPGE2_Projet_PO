package gameobjects;

import libraries.StdDraw;
import libraries.Vector2;

public abstract class Entity {

    /**
     * Attribut
     */
    protected Vector2 position;
    protected Vector2 size;
    protected String imagePath;
    protected double speed;
    protected Vector2 direction;

    protected int pointVie;
    protected int maxpointVie;

    public Entity(Vector2 position, Vector2 size, String imagePath, double speed, Vector2 direction, int pointVie,
            int maxpointVie) {
        this.position = position;
        this.size = size;
        this.imagePath = imagePath;
        this.speed = speed;
        this.direction = direction;
        this.pointVie = pointVie;
        this.maxpointVie = maxpointVie;
    }

    /**
     * Methode qui calcul si une mouche est morte
     * 
     * @return un boolean </br>
     *         <ul>
     *         <li>true - la mouche est morte</li>
     *         <li>false - la mouche est vivante</li>
     *         </ul>
     */
    public boolean isDead() {
        return this.pointVie <= 0;
    }

    /**
     * Methode qui ajoute des pointVie au personnage
     * 
     * @param i la valeur de pointVie a rajouter
     */
    public void addpointVie(int i) {
        this.pointVie += i;
    }

    public abstract void retirepointVie(int i);

    /**
     * Methode qui mets en mouvement l'objet
     */
    protected void move() {
        Vector2 normalizedDirection = getNormalizedDirection();
        Vector2 positionAfterMoving = getPosition().addVector(normalizedDirection);
        setPosition(positionAfterMoving);
        this.direction = new Vector2();

    }

    /**
     * Methode qui dessine l'objet dans le jeu
     */
    public void drawGameObject() {
        StdDraw.picture(this.position.getX(), this.position.getY(), this.imagePath, this.size.getX(), this.size.getY(),
                0);
        StdDraw.setPenColor();
        StdDraw.rectangle(getPosition().getX(), getPosition().getY(), getSize().getX() / 2, getSize().getY() / 2);
    }

    /**
     * Methode qui normalise le vecteur direction de l'objet
     * 
     * @return le vexteur normaliser
     */
    public Vector2 getNormalizedDirection() {
        Vector2 normalizedVector = new Vector2(direction);
        normalizedVector.euclidianNormalize(speed);
        return normalizedVector;
    }

    /*
     * Methodes qui permette de mettre en mvt le Hero
     */
    public void goUpNext() {
        this.direction.addY(1);
    }

    public void goDownNext() {
        this.direction.addY(-1);
    }

    public void goLeftNext() {
        this.direction.addX(-1);
    }

    public void goRightNext() {
        this.direction.addX(1);
    }

    /**
     * Getters / setters
     */

    public Vector2 getPosition() {
        return this.position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public Vector2 getSize() {
        return this.size;
    }

    public void setSize(Vector2 size) {
        this.size = size;
    }

    public String getImagePath() {
        return this.imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public double getSpeed() {
        return this.speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public Vector2 getDirection() {
        return this.direction;
    }

    public void setDirection(Vector2 direction) {
        this.direction = direction;
    }

    public int getPointVie() {
        return this.pointVie;
    }

    public void setPointVie(int pointVie) {
        this.pointVie = pointVie;
    }

    public int getMaxpointVie() {
        return this.maxpointVie;
    }

    public void setMaxpointVie(int maxpointVie) {
        this.maxpointVie = maxpointVie;
    }

}
