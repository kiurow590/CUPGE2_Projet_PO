package gameWorld.rooms;

import gameobjects.objets.consommables.BoxWin;
import gameobjects.obstacles.Poop;
import gameobjects.obstacles.Rock;
import gameobjects.obstacles.Spikes;
import gameobjects.personnages.hero.Hero;
import gameobjects.personnages.monstres.Boss;
import libraries.StdDraw;
import libraries.Vector2;
import resources.ImagePaths;

/**
 * Class qui genere une room avec un boss dedans
 */
public class BossRoom extends Room {

	private static final String MON_TYPE = "BossRoom";

	/**
	 * Constructeur de Boss Romm
	 * 
	 * @param hero le hero
	 * @param id   id de la room
	 */
	public BossRoom(Hero hero, Integer id) {
		super(hero, id);
		this.type = MON_TYPE;

		this.lsMonster.add(new Boss(new Vector2(0.5, 0.5), this));

		this.lstObjet.add(new BoxWin(new Vector2(0.5, 0.5), this));
		choixpattern();
	}

	@Override
	public void updateRoom() {
		makeHeroPlay();
		makeMonsterPlay();
		collisionReport();
		rammasseMonstreMort();
		nettoyageLarme();
		nettoyageProj();
	}

	@Override
	public void drawRoom() {
		// For every tile, set background color.
		// StdDraw.setPenColor(this.bgColor);
		// for (int i = 0; i < RoomInfos.NB_TILES; i++) {
		// for (int j = 0; j < RoomInfos.NB_TILES; j++) {
		// Vector2 position = positionFromTileIndex(i, j);
		// StdDraw.filledRectangle(position.getX(), position.getY(),
		// RoomInfos.HALF_TILE_SIZE.getX(),
		// RoomInfos.HALF_TILE_SIZE.getY());
		// }
		// }

		StdDraw.picture(0.5, 0.5, ImagePaths.BOSS_ROOM, 1, 1);

		hero.drawGameObject();
		dessinePorte();
		dessineObstacles();
		dessineLarme();
		affichageViePiece();
		dessineMonstre();

		affichageObjets();

	}

	public void choixpattern() {
		double numeropattern = Math.random() * 3;
		if (numeropattern <= 1) {
			this.lsObstacle.add(new Rock(new Vector2(0.16, 0.22), new Vector2(0.09, 0.09)));
			this.lsObstacle.add(new Rock(new Vector2(0.84, 0.22), new Vector2(0.09, 0.09)));
			this.lsObstacle.add(new Rock(new Vector2(0.16, 0.78), new Vector2(0.09, 0.09)));
			this.lsObstacle.add(new Rock(new Vector2(0.84, 0.78), new Vector2(0.09, 0.09)));
			this.lsObstacle.add(new Rock(new Vector2(0.25, 0.31), new Vector2(0.09, 0.09)));
			this.lsObstacle.add(new Rock(new Vector2(0.75, 0.69), new Vector2(0.09, 0.09)));
			this.lsObstacle.add(new Spikes(new Vector2(0.75, 0.78), new Vector2(0.07, 0.07)));
			this.lsObstacle.add(new Spikes(new Vector2(0.84, 0.69), new Vector2(0.07, 0.07)));
			this.lsObstacle.add(new Spikes(new Vector2(0.16, 0.31), new Vector2(0.07, 0.07)));
			this.lsObstacle.add(new Spikes(new Vector2(0.25, 0.22), new Vector2(0.07, 0.07)));
		}
		if (numeropattern > 1 && numeropattern <= 2) {
			this.lsObstacle.add(new Rock(new Vector2(0.4, 0.22), new Vector2(0.09, 0.09)));
			this.lsObstacle.add(new Rock(new Vector2(0.6, 0.22), new Vector2(0.09, 0.09)));
			this.lsObstacle.add(new Poop(new Vector2(0.3, 0.78), new Vector2(0.09, 0.09)));
			this.lsObstacle.add(new Poop(new Vector2(0.7, 0.78), new Vector2(0.09, 0.09)));
			this.lsObstacle.add(new Poop(new Vector2(0.7, 0.22), new Vector2(0.09, 0.09)));
			this.lsObstacle.add(new Poop(new Vector2(0.3, 0.22), new Vector2(0.09, 0.09)));
			this.lsObstacle.add(new Rock(new Vector2(0.4, 0.78), new Vector2(0.09, 0.09)));
			this.lsObstacle.add(new Rock(new Vector2(0.6, 0.78), new Vector2(0.09, 0.09)));
			this.lsObstacle.add(new Spikes(new Vector2(0.4, 0.5), new Vector2(0.07, 0.07)));
			this.lsObstacle.add(new Spikes(new Vector2(0.84, 0.78), new Vector2(0.07, 0.07)));
			this.lsObstacle.add(new Spikes(new Vector2(0.3, 0.7), new Vector2(0.07, 0.07)));
			this.lsObstacle.add(new Spikes(new Vector2(0.7, 0.3), new Vector2(0.07, 0.07)));
		}
		if (numeropattern > 2 && numeropattern <= 3) {
			this.lsObstacle.add(new Rock(new Vector2(0.16, 0.22), new Vector2(0.09, 0.09)));
			this.lsObstacle.add(new Poop(new Vector2(0.5, 0.65), new Vector2(0.07, 0.07)));
			this.lsObstacle.add(new Poop(new Vector2(0.5, 0.35), new Vector2(0.07, 0.07)));
			this.lsObstacle.add(new Poop(new Vector2(0.65, 0.5), new Vector2(0.07, 0.07)));
			this.lsObstacle.add(new Poop(new Vector2(0.35, 0.5), new Vector2(0.07, 0.07)));
			this.lsObstacle.add(new Rock(new Vector2(0.84, 0.78), new Vector2(0.09, 0.09)));
			this.lsObstacle.add(new Spikes(new Vector2(0.23, 0.78), new Vector2(0.07, 0.07)));
			this.lsObstacle.add(new Spikes(new Vector2(0.23, 0.71), new Vector2(0.07, 0.07)));
			this.lsObstacle.add(new Spikes(new Vector2(0.16, 0.71), new Vector2(0.07, 0.07)));
			this.lsObstacle.add(new Spikes(new Vector2(0.16, 0.78), new Vector2(0.07, 0.07)));
			this.lsObstacle.add(new Spikes(new Vector2(0.84, 0.22), new Vector2(0.07, 0.07)));
			this.lsObstacle.add(new Spikes(new Vector2(0.77, 0.22), new Vector2(0.07, 0.07)));
			this.lsObstacle.add(new Spikes(new Vector2(0.84, 0.29), new Vector2(0.07, 0.07)));
			this.lsObstacle.add(new Spikes(new Vector2(0.77, 0.29), new Vector2(0.07, 0.07)));
		}
	}
}
