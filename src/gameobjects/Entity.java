package gameobjects;

import libraries.StdDraw;
import libraries.Vector2;

public class Entity {

    /**
     * Attribut
     */
    protected Vector2 position;
    protected Vector2 size;
    protected String imagePath;
    protected double speed;
    protected Vector2 direction;

    public Entity(Vector2 position, Vector2 size, String imagePath, double speed, Vector2 direction) {
        this.position = position;
        this.size = size;
        this.imagePath = imagePath;
        this.speed = speed;
        this.direction = direction;
    }

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

}
