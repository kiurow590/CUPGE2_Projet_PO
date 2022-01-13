package gameWorld.rooms;

import gameobjects.obstacles.Poop;
import gameobjects.obstacles.Rock;
import gameobjects.obstacles.Spikes;
import gameobjects.personnages.Hero;
import libraries.StdDraw;
import libraries.Vector2;
import resources.ImagePaths;

/**
 * Room avec des monstres dedans.
 */
public class MonsterRoom extends Room {

	private static final String MON_TYPE = "MONSTER_ROOM";


	/**
	 * Constructeur de monsterRoom
	 * 
	 * @param hero hero de la partie
	 * @param id   identification
	 */
	public MonsterRoom(Hero hero, Integer id) {
		super(hero, id);
		this.type = MON_TYPE;
		initMonster();
		this.lstObjet.add(initObjectGift());
		choixpatternObstacle();

	}

	@Override
	public void updateRoom() {
		// TODO Auto-generated method stub
		makeHeroPlay();
		makeMonsterPlay();
		collisionReport();
		rammasseMonstreMort();
		nettoyageLarme();
		nettoyageProj();
	}

	@Override
	public void drawRoom() {

		StdDraw.picture(0.5, 0.5, ImagePaths.MONSTER_ROOM, 1, 1);

		hero.drawGameObject();
		dessinePorte();

		dessineMonstre();
		dessineLarme();
		dessineObstacles();
		affichageViePiece();
		affichageObjets();

	}
	public void choixpatternObstacle() {
		// on choisi un pattern possible parmis les 6 ( construit a la main)
		Double numeropattern = Math.random() * 6;
		if (numeropattern <= 1) {
			this.lsObstacle.add(new Rock(new Vector2(0.16, 0.22), new Vector2(0.09, 0.09)));
			this.lsObstacle.add(new Rock(new Vector2(0.84, 0.78), new Vector2(0.09, 0.09)));
			this.lsObstacle.add(new Rock(new Vector2(0.84, 0.22), new Vector2(0.09, 0.09)));
			this.lsObstacle.add(new Rock(new Vector2(0.16, 0.78), new Vector2(0.09, 0.09)));
			this.lsObstacle.add(new Rock(new Vector2(0.4, 0.5), new Vector2(0.09, 0.09)));
			this.lsObstacle.add(new Poop(new Vector2(0.6, 0.5), new Vector2(0.09, 0.09)));
			this.lsObstacle.add(new Rock(new Vector2(0.5, 0.4), new Vector2(0.09, 0.09)));
			this.lsObstacle.add(new Rock(new Vector2(0.5, 0.6), new Vector2(0.09, 0.09)));
			this.lsObstacle.add(new Spikes(new Vector2(0.24, 0.21), new Vector2(0.07, 0.07)));
			this.lsObstacle.add(new Spikes(new Vector2(0.16, 0.30), new Vector2(0.07, 0.07)));
			this.lsObstacle.add(new Spikes(new Vector2(0.76, 0.79), new Vector2(0.07, 0.07)));
			this.lsObstacle.add(new Spikes(new Vector2(0.84, 0.70), new Vector2(0.07, 0.07)));
		} else if (numeropattern > 1 && numeropattern <= 2) {
			this.lsObstacle.add(new Poop(new Vector2(0.4, 0.5), new Vector2(0.09, 0.09)));
			this.lsObstacle.add(new Poop(new Vector2(0.6, 0.5), new Vector2(0.09, 0.09)));
			this.lsObstacle.add(new Poop(new Vector2(0.5, 0.4), new Vector2(0.09, 0.09)));
			this.lsObstacle.add(new Poop(new Vector2(0.5, 0.6), new Vector2(0.09, 0.09)));
			this.lsObstacle.add(new Spikes(new Vector2(0.4, 0.6), new Vector2(0.07, 0.07)));
			this.lsObstacle.add(new Spikes(new Vector2(0.6, 0.6), new Vector2(0.07, 0.07)));
			this.lsObstacle.add(new Spikes(new Vector2(0.4, 0.4), new Vector2(0.07, 0.07)));
			this.lsObstacle.add(new Spikes(new Vector2(0.6, 0.4), new Vector2(0.07, 0.07)));
			this.lsObstacle.add(new Rock(new Vector2(0.16, 0.22), new Vector2(0.09, 0.09)));
			this.lsObstacle.add(new Rock(new Vector2(0.16, 0.31), new Vector2(0.09, 0.09)));
			this.lsObstacle.add(new Rock(new Vector2(0.25, 0.22), new Vector2(0.09, 0.09)));
			this.lsObstacle.add(new Rock(new Vector2(0.84, 0.78), new Vector2(0.09, 0.09)));
			this.lsObstacle.add(new Rock(new Vector2(0.75, 0.78), new Vector2(0.09, 0.09)));
			this.lsObstacle.add(new Rock(new Vector2(0.84, 0.69), new Vector2(0.09, 0.09)));

		} else if (numeropattern > 2 && numeropattern <= 3) {
			this.lsObstacle.add(new Rock(new Vector2(0.4, 0.35), new Vector2(0.09, 0.09)));
			this.lsObstacle.add(new Rock(new Vector2(0.5, 0.35), new Vector2(0.09, 0.09)));
			this.lsObstacle.add(new Rock(new Vector2(0.6, 0.35), new Vector2(0.09, 0.09)));
			this.lsObstacle.add(new Poop(new Vector2(0.3, 0.45), new Vector2(0.09, 0.09)));
			this.lsObstacle.add(new Poop(new Vector2(0.3, 0.55), new Vector2(0.09, 0.09)));
			this.lsObstacle.add(new Rock(new Vector2(0.4, 0.65), new Vector2(0.09, 0.09)));
			this.lsObstacle.add(new Rock(new Vector2(0.5, 0.65), new Vector2(0.09, 0.09)));
			this.lsObstacle.add(new Rock(new Vector2(0.6, 0.65), new Vector2(0.09, 0.09)));
			this.lsObstacle.add(new Poop(new Vector2(0.7, 0.55), new Vector2(0.09, 0.09)));
			this.lsObstacle.add(new Poop(new Vector2(0.7, 0.45), new Vector2(0.09, 0.09)));
		} else if (numeropattern > 3 && numeropattern <= 4) {
			this.lsObstacle.add(new Rock(new Vector2(0.16, 0.22), new Vector2(0.09, 0.09)));
			this.lsObstacle.add(new Rock(new Vector2(0.25, 0.31), new Vector2(0.09, 0.09)));
			this.lsObstacle.add(new Rock(new Vector2(0.34, 0.40), new Vector2(0.09, 0.09)));
			this.lsObstacle.add(new Rock(new Vector2(0.84, 0.78), new Vector2(0.09, 0.09)));
			this.lsObstacle.add(new Rock(new Vector2(0.75, 0.69), new Vector2(0.09, 0.09)));
			this.lsObstacle.add(new Rock(new Vector2(0.66, 0.60), new Vector2(0.09, 0.09)));
			this.lsObstacle.add(new Spikes(new Vector2(0.23, 0.78), new Vector2(0.07, 0.07)));
			this.lsObstacle.add(new Spikes(new Vector2(0.23, 0.71), new Vector2(0.07, 0.07)));
			this.lsObstacle.add(new Spikes(new Vector2(0.16, 0.71), new Vector2(0.07, 0.07)));
			this.lsObstacle.add(new Spikes(new Vector2(0.16, 0.78), new Vector2(0.07, 0.07)));
			this.lsObstacle.add(new Spikes(new Vector2(0.84, 0.22), new Vector2(0.07, 0.07)));
			this.lsObstacle.add(new Spikes(new Vector2(0.77, 0.22), new Vector2(0.07, 0.07)));
			this.lsObstacle.add(new Spikes(new Vector2(0.84, 0.29), new Vector2(0.07, 0.07)));
			this.lsObstacle.add(new Spikes(new Vector2(0.77, 0.29), new Vector2(0.07, 0.07)));

		} else if (numeropattern > 4 && numeropattern <= 5) {
			this.lsObstacle.add(new Rock(new Vector2(0.16, 0.8), new Vector2(0.09, 0.09)));
			this.lsObstacle.add(new Rock(new Vector2(0.16, 0.6), new Vector2(0.09, 0.09)));
			this.lsObstacle.add(new Rock(new Vector2(0.16, 0.4), new Vector2(0.09, 0.09)));
			this.lsObstacle.add(new Rock(new Vector2(0.16, 0.22), new Vector2(0.09, 0.09)));
			this.lsObstacle.add(new Rock(new Vector2(0.4, 0.8), new Vector2(0.09, 0.09)));
			this.lsObstacle.add(new Rock(new Vector2(0.4, 0.6), new Vector2(0.09, 0.09)));
			this.lsObstacle.add(new Rock(new Vector2(0.4, 0.4), new Vector2(0.09, 0.09)));
			this.lsObstacle.add(new Rock(new Vector2(0.4, 0.22), new Vector2(0.09, 0.09)));
			this.lsObstacle.add(new Rock(new Vector2(0.6, 0.8), new Vector2(0.09, 0.09)));
			this.lsObstacle.add(new Rock(new Vector2(0.6, 0.6), new Vector2(0.09, 0.09)));
			this.lsObstacle.add(new Rock(new Vector2(0.6, 0.4), new Vector2(0.09, 0.09)));
			this.lsObstacle.add(new Rock(new Vector2(0.6, 0.22), new Vector2(0.09, 0.09)));
			this.lsObstacle.add(new Rock(new Vector2(0.84, 0.8), new Vector2(0.09, 0.09)));
			this.lsObstacle.add(new Rock(new Vector2(0.84, 0.6), new Vector2(0.09, 0.09)));
			this.lsObstacle.add(new Rock(new Vector2(0.84, 0.4), new Vector2(0.09, 0.09)));
			this.lsObstacle.add(new Rock(new Vector2(0.84, 0.22), new Vector2(0.09, 0.09)));
		} else if (numeropattern > 5 && numeropattern <= 6) {
			this.lsObstacle.add(new Rock(new Vector2(0.16, 0.78), new Vector2(0.09, 0.09)));
			this.lsObstacle.add(new Rock(new Vector2(0.16, 0.69), new Vector2(0.09, 0.09)));
			this.lsObstacle.add(new Rock(new Vector2(0.25, 0.78), new Vector2(0.09, 0.09)));
			this.lsObstacle.add(new Rock(new Vector2(0.16, 0.22), new Vector2(0.09, 0.09)));
			this.lsObstacle.add(new Rock(new Vector2(0.16, 0.31), new Vector2(0.09, 0.09)));
			this.lsObstacle.add(new Rock(new Vector2(0.25, 0.22), new Vector2(0.09, 0.09)));
			this.lsObstacle.add(new Rock(new Vector2(0.84, 0.31), new Vector2(0.09, 0.09)));
			this.lsObstacle.add(new Rock(new Vector2(0.84, 0.22), new Vector2(0.09, 0.09)));
			this.lsObstacle.add(new Rock(new Vector2(0.75, 0.22), new Vector2(0.09, 0.09)));
			this.lsObstacle.add(new Rock(new Vector2(0.84, 0.78), new Vector2(0.09, 0.09)));
			this.lsObstacle.add(new Rock(new Vector2(0.84, 0.69), new Vector2(0.09, 0.09)));
			this.lsObstacle.add(new Rock(new Vector2(0.75, 0.78), new Vector2(0.09, 0.09)));
			this.lsObstacle.add(new Spikes(new Vector2(0.25, 0.69), new Vector2(0.07, 0.07)));
			this.lsObstacle.add(new Spikes(new Vector2(0.25, 0.31), new Vector2(0.07, 0.07)));
			this.lsObstacle.add(new Spikes(new Vector2(0.75, 0.31), new Vector2(0.07, 0.07)));
			this.lsObstacle.add(new Spikes(new Vector2(0.75, 0.69), new Vector2(0.07, 0.07)));

		}

	}
}
