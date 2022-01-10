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
		// choixpatternObstacle();

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
		// on choisi un pattern possible parmis les 6 ( construit a la main
		Double numeropattern = Math.random() * 6;
		if (numeropattern <= 1) {
			this.lsObstacle.add(new Rock(new Vector2(0.1, 0.1), new Vector2(0.1, 0.1)));
			this.lsObstacle.add(new Rock(new Vector2(0.9, 0.9), new Vector2(0.1, 0.1)));
			this.lsObstacle.add(new Rock(new Vector2(0.9, 0.1), new Vector2(0.1, 0.1)));
			this.lsObstacle.add(new Rock(new Vector2(0.1, 0.9), new Vector2(0.1, 0.1)));
			this.lsObstacle.add(new Rock(new Vector2(0.4, 0.5), new Vector2(0.1, 0.1)));
			this.lsObstacle.add(new Poop(new Vector2(0.6, 0.5), new Vector2(0.1, 0.1)));
			this.lsObstacle.add(new Rock(new Vector2(0.5, 0.4), new Vector2(0.1, 0.1)));
			this.lsObstacle.add(new Rock(new Vector2(0.5, 0.6), new Vector2(0.1, 0.1)));
			this.lsObstacle.add(new Spikes(new Vector2(0.2, 0.1), new Vector2(0.1, 0.1)));
			this.lsObstacle.add(new Spikes(new Vector2(0.1, 0.2), new Vector2(0.1, 0.1)));
			this.lsObstacle.add(new Spikes(new Vector2(0.1, 0.2), new Vector2(0.1, 0.1)));
			this.lsObstacle.add(new Spikes(new Vector2(0.8, 0.9), new Vector2(0.1, 0.1)));
			this.lsObstacle.add(new Spikes(new Vector2(0.9, 0.8), new Vector2(0.1, 0.1)));
		} else if (numeropattern > 1 && numeropattern <= 2) {
			this.lsObstacle.add(new Poop(new Vector2(0.4, 0.5), new Vector2(0.1, 0.1)));
			this.lsObstacle.add(new Poop(new Vector2(0.6, 0.5), new Vector2(0.1, 0.1)));
			this.lsObstacle.add(new Poop(new Vector2(0.5, 0.4), new Vector2(0.1, 0.1)));
			this.lsObstacle.add(new Poop(new Vector2(0.5, 0.6), new Vector2(0.1, 0.1)));
			this.lsObstacle.add(new Spikes(new Vector2(0.4, 0.6), new Vector2(0.1, 0.1)));
			this.lsObstacle.add(new Spikes(new Vector2(0.6, 0.6), new Vector2(0.1, 0.1)));
			this.lsObstacle.add(new Spikes(new Vector2(0.4, 0.4), new Vector2(0.1, 0.1)));
			this.lsObstacle.add(new Spikes(new Vector2(0.6, 0.4), new Vector2(0.1, 0.1)));
			this.lsObstacle.add(new Rock(new Vector2(0.1, 0.1), new Vector2(0.1, 0.1)));
			this.lsObstacle.add(new Rock(new Vector2(0.1, 0.2), new Vector2(0.1, 0.1)));
			this.lsObstacle.add(new Rock(new Vector2(0.2, 0.1), new Vector2(0.1, 0.1)));
			this.lsObstacle.add(new Rock(new Vector2(0.9, 0.9), new Vector2(0.1, 0.1)));
			this.lsObstacle.add(new Rock(new Vector2(0.8, 0.9), new Vector2(0.1, 0.1)));
			this.lsObstacle.add(new Rock(new Vector2(0.9, 0.8), new Vector2(0.1, 0.1)));
		} else if (numeropattern > 2 && numeropattern <= 3) {
			this.lsObstacle.add(new Rock(new Vector2(0.4, 0.4), new Vector2(0.1, 0.1)));
			this.lsObstacle.add(new Rock(new Vector2(0.5, 0.4), new Vector2(0.1, 0.1)));
			this.lsObstacle.add(new Rock(new Vector2(0.6, 0.4), new Vector2(0.1, 0.1)));
			this.lsObstacle.add(new Rock(new Vector2(0.7, 0.4), new Vector2(0.1, 0.1)));
			this.lsObstacle.add(new Poop(new Vector2(0.4, 0.5), new Vector2(0.1, 0.1)));
			this.lsObstacle.add(new Poop(new Vector2(0.4, 0.6), new Vector2(0.1, 0.1)));
			this.lsObstacle.add(new Rock(new Vector2(0.4, 0.7), new Vector2(0.1, 0.1)));
			this.lsObstacle.add(new Rock(new Vector2(0.5, 0.7), new Vector2(0.1, 0.1)));
			this.lsObstacle.add(new Rock(new Vector2(0.6, 0.7), new Vector2(0.1, 0.1)));
			this.lsObstacle.add(new Rock(new Vector2(0.7, 0.7), new Vector2(0.1, 0.1)));
			this.lsObstacle.add(new Poop(new Vector2(0.7, 0.6), new Vector2(0.1, 0.1)));
			this.lsObstacle.add(new Poop(new Vector2(0.7, 0.5), new Vector2(0.1, 0.1)));
			this.lsObstacle.add(new Spikes(new Vector2(0.9, 0.9), new Vector2(0.1, 0.1)));
			this.lsObstacle.add(new Spikes(new Vector2(0.1, 0.1), new Vector2(0.1, 0.1)));
			this.lsObstacle.add(new Spikes(new Vector2(0.9, 0.1), new Vector2(0.1, 0.1)));
			this.lsObstacle.add(new Spikes(new Vector2(0.1, 0.9), new Vector2(0.1, 0.1)));
		} else if (numeropattern > 3 && numeropattern <= 4) {
			this.lsObstacle.add(new Rock(new Vector2(0.4, 0.4), new Vector2(0.1, 0.1)));
			this.lsObstacle.add(new Rock(new Vector2(0.6, 0.6), new Vector2(0.1, 0.1)));
			this.lsObstacle.add(new Rock(new Vector2(0.7, 0.7), new Vector2(0.1, 0.1)));
			this.lsObstacle.add(new Rock(new Vector2(0.8, 0.8), new Vector2(0.1, 0.1)));
			this.lsObstacle.add(new Rock(new Vector2(0.9, 0.9), new Vector2(0.1, 0.1)));
			this.lsObstacle.add(new Rock(new Vector2(0.2, 0.2), new Vector2(0.1, 0.1)));
			this.lsObstacle.add(new Rock(new Vector2(0.1, 0.1), new Vector2(0.1, 0.1)));
			this.lsObstacle.add(new Rock(new Vector2(0.3, 0.3), new Vector2(0.1, 0.1)));
			this.lsObstacle.add(new Rock(new Vector2(0.7, 0.7), new Vector2(0.1, 0.1)));
		} else if (numeropattern > 4 && numeropattern <= 5) {
			this.lsObstacle.add(new Rock(new Vector2(0.2, 0.8), new Vector2(0.1, 0.1)));
			this.lsObstacle.add(new Rock(new Vector2(0.2, 0.6), new Vector2(0.1, 0.1)));
			this.lsObstacle.add(new Rock(new Vector2(0.2, 0.4), new Vector2(0.1, 0.1)));
			this.lsObstacle.add(new Rock(new Vector2(0.2, 0.2), new Vector2(0.1, 0.1)));
			this.lsObstacle.add(new Rock(new Vector2(0.4, 0.8), new Vector2(0.1, 0.1)));
			this.lsObstacle.add(new Rock(new Vector2(0.4, 0.6), new Vector2(0.1, 0.1)));
			this.lsObstacle.add(new Rock(new Vector2(0.4, 0.4), new Vector2(0.1, 0.1)));
			this.lsObstacle.add(new Rock(new Vector2(0.4, 0.2), new Vector2(0.1, 0.1)));
			this.lsObstacle.add(new Rock(new Vector2(0.6, 0.8), new Vector2(0.1, 0.1)));
			this.lsObstacle.add(new Rock(new Vector2(0.6, 0.6), new Vector2(0.1, 0.1)));
			this.lsObstacle.add(new Rock(new Vector2(0.6, 0.4), new Vector2(0.1, 0.1)));
			this.lsObstacle.add(new Rock(new Vector2(0.6, 0.2), new Vector2(0.1, 0.1)));
			this.lsObstacle.add(new Rock(new Vector2(0.8, 0.8), new Vector2(0.1, 0.1)));
			this.lsObstacle.add(new Rock(new Vector2(0.8, 0.6), new Vector2(0.1, 0.1)));
			this.lsObstacle.add(new Rock(new Vector2(0.8, 0.4), new Vector2(0.1, 0.1)));
			this.lsObstacle.add(new Rock(new Vector2(0.8, 0.2), new Vector2(0.1, 0.1)));
			this.lsObstacle.add(new Poop(new Vector2(0.1, 0.8), new Vector2(0.1, 0.1)));
			this.lsObstacle.add(new Poop(new Vector2(0.2, 0.9), new Vector2(0.1, 0.1)));
			this.lsObstacle.add(new Poop(new Vector2(0.8, 0.1), new Vector2(0.1, 0.1)));
			this.lsObstacle.add(new Poop(new Vector2(0.9, 0.2), new Vector2(0.1, 0.1)));
			this.lsObstacle.add(new Spikes(new Vector2(0.9, 0.9), new Vector2(0.1, 0.1)));
			this.lsObstacle.add(new Spikes(new Vector2(0.1, 0.1), new Vector2(0.1, 0.1)));
			this.lsObstacle.add(new Spikes(new Vector2(0.9, 0.1), new Vector2(0.1, 0.1)));
			this.lsObstacle.add(new Spikes(new Vector2(0.1, 0.9), new Vector2(0.1, 0.1)));
		} else if (numeropattern > 5 && numeropattern <= 6) {
			this.lsObstacle.add(new Rock(new Vector2(0.2, 0.8), new Vector2(0.1, 0.1)));
			this.lsObstacle.add(new Rock(new Vector2(0.2, 0.7), new Vector2(0.1, 0.1)));
			this.lsObstacle.add(new Rock(new Vector2(0.3, 0.8), new Vector2(0.1, 0.1)));
			this.lsObstacle.add(new Rock(new Vector2(0.2, 0.2), new Vector2(0.1, 0.1)));
			this.lsObstacle.add(new Rock(new Vector2(0.2, 0.3), new Vector2(0.1, 0.1)));
			this.lsObstacle.add(new Rock(new Vector2(0.3, 0.2), new Vector2(0.1, 0.1)));
			this.lsObstacle.add(new Rock(new Vector2(0.8, 0.2), new Vector2(0.1, 0.1)));
			this.lsObstacle.add(new Rock(new Vector2(0.8, 0.3), new Vector2(0.1, 0.1)));
			this.lsObstacle.add(new Rock(new Vector2(0.7, 0.2), new Vector2(0.1, 0.1)));
			this.lsObstacle.add(new Rock(new Vector2(0.8, 0.8), new Vector2(0.1, 0.1)));
			this.lsObstacle.add(new Rock(new Vector2(0.8, 0.7), new Vector2(0.1, 0.1)));
			this.lsObstacle.add(new Rock(new Vector2(0.7, 0.8), new Vector2(0.1, 0.1)));
			this.lsObstacle.add(new Spikes(new Vector2(0.9, 0.9), new Vector2(0.1, 0.1)));
			this.lsObstacle.add(new Spikes(new Vector2(0.1, 0.1), new Vector2(0.1, 0.1)));
			this.lsObstacle.add(new Spikes(new Vector2(0.9, 0.1), new Vector2(0.1, 0.1)));
			this.lsObstacle.add(new Spikes(new Vector2(0.1, 0.9), new Vector2(0.1, 0.1)));
			this.lsObstacle.add(new Spikes(new Vector2(0.3, 0.7), new Vector2(0.1, 0.1)));
			this.lsObstacle.add(new Spikes(new Vector2(0.3, 0.3), new Vector2(0.1, 0.1)));
			this.lsObstacle.add(new Spikes(new Vector2(0.7, 0.3), new Vector2(0.1, 0.1)));
			this.lsObstacle.add(new Spikes(new Vector2(0.7, 0.7), new Vector2(0.1, 0.1)));

		}

	}
}
