package gameobjects;

import libraries.StdDraw;
import libraries.Vector2;

public class Fly {

	private Vector2 position;
	private Vector2 size;
	private String imagePath;
	private double speed;
	private Vector2 direction;
	// caracteristique ajout
	private int ptDeVie;
	private int degatCorpsACorps;

	/**
	 * Constructeur de fly
	 * 
	 * @param position
	 * @param size
	 * @param imagePath
	 * @param speed
	 * @param direction
	 * @param ptDeVie
	 * @param degatCorpsACorps
	 */
	public Fly(Vector2 position, Vector2 size, String imagePath, double speed, Vector2 direction, int ptDeVie,
			int degatCorpsACorps) {
		super();
		this.position = position;
		this.size = size;
		this.imagePath = imagePath;
		this.speed = speed;
		this.direction = direction;
		this.ptDeVie = ptDeVie;
		this.degatCorpsACorps = degatCorpsACorps;
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
		return this.ptDeVie <= 0;
	}

	/**
	 * Methode qui retire les point de vie d'une mouche
	 * 
	 * @implNote Methode qui aurait dans le futur un parametre projectile indiquant
	 *           combien de pv retiré suivant l'attaque reçu
	 */
	public void retirePV() {
		// TODO: rajouter parametre pour retirer n PV
		this.ptDeVie -= 1;
	}

	/**
	 * Methode qui dessine la mouche dans le jeu
	 */
	public void drawGameObject() {
		StdDraw.picture(getPosition().getX(), getPosition().getY(), getImagePath(), getSize().getX(), getSize().getY(),
				0);
	}

	/**
	 * Methode qui mets a jour l'objet du jeu (position vitesse ...etc.)
	 */
	public void updateGameObject(Hero e) {

		move(e);

	}

	/**
	 * Methode qui mets en mouvement l'araignee
	 */
	private void move(Hero e) {

		this.setDirection(new Vector2(this.getPosition().getX() - e.getPosition().getX(),
				this.getPosition().getY() - e.getPosition().getY()).reverse());

		Vector2 normalizedDirection = getNormalizedDirection();
		Vector2 positionAfterMoving = getPosition().addVector(normalizedDirection);
		setPosition(positionAfterMoving);
		direction = new Vector2();
		System.out.println(this.position.getX() + "," + this.position.getY());
	}

	/*
	 * Moving from key inputs. Direction vector is later normalised.
	 */
	public void goUpNext() {
		getDirection().addY(1);
	}

	public void goDownNext() {
		getDirection().addY(-1);
	}

	public void goLeftNext() {
		getDirection().addX(-1);
	}

	public void goRightNext() {
		getDirection().addX(1);
	}

	/**
	 * Methode qui normalise le vecteur direction du personnage
	 * 
	 * @return le vexteur normaliser
	 */
	public Vector2 getNormalizedDirection() {
		Vector2 normalizedVector = new Vector2(direction);
		normalizedVector.euclidianNormalize(speed);
		return normalizedVector;
	}

	public Vector2 getPosition() {
		return position;
	}

	public void setPosition(Vector2 position) {
		this.position = position;
	}

	public Vector2 getSize() {
		return size;
	}

	public void setSize(Vector2 size) {
		this.size = size;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public Vector2 getDirection() {
		return direction;
	}

	public void setDirection(Vector2 direction) {
		this.direction = direction;
	}

	public int getPtDeVie() {
		return ptDeVie;
	}

	public void setPtDeVie(int ptDeVie) {
		this.ptDeVie = ptDeVie;
	}

	public int getDegatCorpsACorps() {
		return degatCorpsACorps;
	}

	public void setDegatCorpsACorps(int degatCorpsACorps) {
		this.degatCorpsACorps = degatCorpsACorps;
	}

}
