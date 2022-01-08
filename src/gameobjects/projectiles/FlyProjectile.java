package gameobjects.projectiles;

import libraries.Vector2;

/**
 * Class projectile
 */
public class FlyProjectile extends Projectile {
    /**
     * Constructeur de FlyProjectile
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
    public FlyProjectile(Vector2 position, String imagePath, Vector2 direction, int damage) {
        super(position, imagePath, direction, damage);
        this.size = new Vector2(0.045, 0.045);
        // TODO Auto-generated constructor stub
    }

}
