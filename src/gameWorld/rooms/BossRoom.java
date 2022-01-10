package gameWorld.rooms;

import gameobjects.objets.consommables.BoxWin;
import gameobjects.obstacles.Rock;
import gameobjects.obstacles.Spikes;
import gameobjects.obstacles.Poop;
import gameobjects.personnages.Hero;
import gameobjects.personnages.monstres.Boss;
import libraries.StdDraw;
import libraries.Vector2;
import resources.ImagePaths;
import resources.RoomInfos;

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
        // TODO Auto-generated constructor stub
        this.type = MON_TYPE;
        this.bgColor = StdDraw.GRAY;

        this.lsMonster.add(new Boss(new Vector2(0.5, 0.5), this));

        this.lstObjet.add(new BoxWin(new Vector2(0.5, 0.5), this));
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

        dessineLarme();
        affichageViePiece();
        dessineMonstre();

        affichageObjets();

    }

	public void choixpattern() {
		double numeropattern = Math.random() * 3;
		if (numeropattern <= 1) {
			this.lsObstacle.add(new Rock(new Vector2(0.1, 0.1), new Vector2(0.1, 0.1)));
			this.lsObstacle.add(new Rock(new Vector2(0.9, 0.1), new Vector2(0.1, 0.1)));
			this.lsObstacle.add(new Rock(new Vector2(0.1, 0.9), new Vector2(0.1, 0.1)));
			this.lsObstacle.add(new Rock(new Vector2(0.9, 0.9), new Vector2(0.1, 0.1)));
			this.lsObstacle.add(new Rock(new Vector2(0.2, 0.2), new Vector2(0.1, 0.1)));
			this.lsObstacle.add(new Rock(new Vector2(0.8, 0.8), new Vector2(0.1, 0.1)));
			this.lsObstacle.add(new Spikes(new Vector2(0.9, 0.9), new Vector2(0.1, 0.1)));
			this.lsObstacle.add(new Spikes(new Vector2(0.8, 0.7), new Vector2(0.1, 0.1)));
			this.lsObstacle.add(new Spikes(new Vector2(0.1, 0.2), new Vector2(0.1, 0.1)));
			this.lsObstacle.add(new Spikes(new Vector2(0.2, 0.1), new Vector2(0.1, 0.1)));
			if (numeropattern > 1 && numeropattern <= 2) {
				this.lsObstacle.add(new Rock(new Vector2(0.4, 0.1), new Vector2(0.1, 0.1)));
				this.lsObstacle.add(new Rock(new Vector2(0.6, 0.1), new Vector2(0.1, 0.1)));
				this.lsObstacle.add(new Poop(new Vector2(0.5, 0.9), new Vector2(0.1, 0.1)));
				this.lsObstacle.add(new Poop(new Vector2(0.5, 0.9), new Vector2(0.1, 0.1)));
				this.lsObstacle.add(new Rock(new Vector2(0.4, 0.9), new Vector2(0.1, 0.1)));
				this.lsObstacle.add(new Rock(new Vector2(0.6, 0.9), new Vector2(0.1, 0.1)));
				this.lsObstacle.add(new Spikes(new Vector2(0.4, 0.5), new Vector2(0.1, 0.1)));
				this.lsObstacle.add(new Spikes(new Vector2(0.8, 0.8), new Vector2(0.1, 0.1)));
				if (numeropattern > 2 && numeropattern <= 3) {
					this.lsObstacle.add(new Rock(new Vector2(0.2, 0.2), new Vector2(0.1, 0.1)));
					this.lsObstacle.add(new Rock(new Vector2(0.2, 0.8), new Vector2(0.1, 0.1)));
					this.lsObstacle.add(new Poop(new Vector2(0.8, 0.8), new Vector2(0.1, 0.1)));
					this.lsObstacle.add(new Poop(new Vector2(0.5, 0.9), new Vector2(0.1, 0.1)));
					this.lsObstacle.add(new Poop(new Vector2(0.7, 0.3), new Vector2(0.1, 0.1)));
					this.lsObstacle.add(new Rock(new Vector2(0.8, 0.2), new Vector2(0.1, 0.1)));
					this.lsObstacle.add(new Rock(new Vector2(0.6, 0.9), new Vector2(0.1, 0.1)));
					this.lsObstacle.add(new Spikes(new Vector2(0.4, 0.5), new Vector2(0.1, 0.1)));
					this.lsObstacle.add(new Spikes(new Vector2(0.8, 0.8), new Vector2(0.1, 0.1)));
				}
			}

		}
	}
}
