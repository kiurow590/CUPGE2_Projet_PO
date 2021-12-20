package gameobjects;

import java.util.ArrayList;
import java.util.List;

import libraries.StdDraw;
import libraries.Vector2;

public class Hero {
	/**
	 * Attribut
	 */
	private Vector2 position;
	private Vector2 size;
	private String imagePath;
	private double speed;
	private Vector2 direction;

	private List<Larme> lstLarme;
	private List<Object> lstEquipement;
	private int degats;

	private boolean estInvincible;
	private boolean estRapide;
	private boolean estPuissant;
	private int compteurInvincible;
	private int compteurRapide;
	private int compteurTir;
	private int compteurPuissance;

	private int pV;

	/**
	 * Constructeur de personnage
	 * 
	 * @param position           position initiale du personnage
	 * @param size               taille du personnage
	 * @param speed              vitesse du personnage
	 * @param imagePath          Image du personnage
	 * @param compteurInvincible compteur d'invincibilité du personnage :
	 *                           <ul>
	 *                           <li>sois le perso est toucher --> le temps ou le
	 *                           perso est invulneralble</li>
	 *                           <li>sois le perso est invincible --> le temps que
	 *                           l'on doit attendre pour pouvoir reappuyer sur la
	 *                           touche</li>
	 *                           </ul>
	 * @param pv                 pv du perso
	 */
	public Hero(Vector2 position, Vector2 size, double speed, String imagePath, int compteurInvincible, int pv,
			int degats) {
		this.position = position;
		this.size = size;
		this.speed = speed;
		this.imagePath = imagePath;
		this.direction = new Vector2();

		this.estInvincible = false;

		this.compteurInvincible = compteurInvincible;
		this.compteurTir = 20;
		this.pV = pv;

		lstLarme = new ArrayList<Larme>();

		this.estRapide = false;
		this.compteurRapide = 0;

		this.estPuissant = false;
		this.compteurPuissance = 0;

		this.degats = degats;

		this.lstEquipement = new ArrayList<>();
	}

	/**
	 * Methode qui mets a jour l'objet du jeu (position vitesse ...etc.)
	 */
	public void updateGameObject() {
		move();
		if (this.compteurTir > 0) {
			this.compteurTir--;

		}
		if (this.compteurInvincible > 0) {
			this.compteurInvincible--;

		}
		if (this.compteurRapide > 0) {
			this.compteurRapide--;

		}
		if (this.compteurPuissance > 0) {
			this.compteurPuissance--;

		}
	}

	/**
	 * Methode qui retire des pv au personnage
	 * 
	 * @param i valeur de pv retiré
	 */
	public void retirePV(int i) {
		if (!this.estInvincible) {
			this.pV -= i;
		}

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
		return this.pV <= 0;
	}

	/**
	 * Methode qui ajoute des PV au personnage
	 * 
	 * @param i la valeur de pv a rajouter
	 */
	public void addPV(int i) {
		this.pV += i;
	}

	/**
	 * Methode qui creer une larme et qui la stock dans la liste de larme du
	 * personnage
	 * 
	 * @param e larme
	 */
	public void creeLarme(Larme e) {
		if (this.compteurTir <= 0) {
			e.setDegats(degats);
			this.lstLarme.add(e);
			this.compteurTir = 20;
		}

	}

	/**
	 * Methode qui mets en mouvement le personnage
	 */
	private void move() {
		Vector2 normalizedDirection = getNormalizedDirection();
		Vector2 positionAfterMoving = getPosition().addVector(normalizedDirection);
		setPosition(positionAfterMoving);
		direction = new Vector2();

	}

	/**
	 * Methode qui dessine le personnage dans le jeu
	 */
	public void drawGameObject() {
		StdDraw.picture(getPosition().getX(), getPosition().getY(), getImagePath(), getSize().getX(), getSize().getY(),
				0);
		StdDraw.setPenColor();
		StdDraw.rectangle(getPosition().getX(), getPosition().getY(), getSize().getX() / 2, getSize().getY() / 2);
	}

	/**
	 * Passe le hero en mode invincible ou pas
	 */
	public void modeInvincible() {
		if (!this.estInvincible && this.compteurInvincible == 0) {
			setEstInvincible(true);
			this.compteurInvincible = 10;
		} else if (this.estInvincible && this.compteurInvincible == 0) {
			setEstInvincible(false);
			this.compteurInvincible = 10;
		}

	}

	/**
	 * Passe le hero en mode rapide ou pas
	 */
	public void moderapide() {
		if (!this.estRapide && this.compteurRapide == 0) {
			setEstRapide(true);
			this.compteurRapide = 40;
			this.setSpeed(this.speed * 2);
		} else if (this.estRapide && this.compteurRapide == 0) {
			setEstRapide(false);
			this.compteurRapide = 40;
			this.setSpeed(this.speed / 2);
		}

	}

	/**
	 * Passe le hero en mode puissant ou pas
	 */
	public void modePuissance() {
		if (!this.estPuissant && this.compteurPuissance == 0) {
			setEstPuissant(true);
			this.compteurPuissance = 40;
			this.degats = 5000000;
			System.out.println("Dobby Pete des cul");
		} else if (this.estRapide && this.compteurRapide == 0) {
			setEstPuissant(false);
			this.compteurPuissance = 40;
			this.degats = 1;
			System.out.println("Dobby est une merde");

		}

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

	/*
	 * Getters and Setters
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

	public boolean isEstInvincible() {
		return estInvincible;
	}

	public void setEstInvincible(boolean estInvincible) {
		this.estInvincible = estInvincible;
	}

	public int getpV() {
		return pV;
	}

	public void setpV(int pV) {
		this.pV = pV;
	}

	public List<Larme> getLstLarme() {
		return lstLarme;
	}

	public void setLstLarme(ArrayList<Larme> lstLarme) {
		this.lstLarme = lstLarme;
	}

	public boolean getEstRapide() {
		return estRapide;
	}

	public void setEstRapide(boolean estRapide) {
		this.estRapide = estRapide;
	}

	public int getCompteurInvincible() {
		return compteurInvincible;
	}

	public void setCompteurInvincible(int compteurInvincible) {
		this.compteurInvincible = compteurInvincible;
	}

	public int getCompteurTir() {
		return compteurTir;
	}

	public void setCompteurTir(int compteurTir) {
		this.compteurTir = compteurTir;
	}

	public void setLstLarme(List<Larme> lstLarme) {
		this.lstLarme = lstLarme;
	}

	public boolean isEstPuissant() {
		return estPuissant;
	}

	public void setEstPuissant(boolean estPuissant) {
		this.estPuissant = estPuissant;
	}

	public int getCompteurRapide() {
		return compteurRapide;
	}

	public void setCompteurRapide(int compteurRapide) {
		this.compteurRapide = compteurRapide;
	}

	public int getCompteurPuissance() {
		return compteurPuissance;
	}

	public void setCompteurPuissance(int compteurPuissance) {
		this.compteurPuissance = compteurPuissance;
	}

	public int getDegats() {
		return degats;
	}

	public void setDegats(int degats) {
		this.degats = degats;
	}

	public List<Object> getLstEquipement() {
		return this.lstEquipement;
	}

	public void setLstEquipement(List<Object> lstEquipement) {
		this.lstEquipement = lstEquipement;
	}

	public boolean getEstInvincible() {
		return this.estInvincible;
	}

	public boolean isEstRapide() {
		return this.estRapide;
	}

	public boolean getEstPuissant() {
		return this.estPuissant;
	}

	public int getPV() {
		return this.pV;
	}

	public void setPV(int pV) {
		this.pV = pV;
	}

}
