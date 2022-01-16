package gameWorld.rooms;
import java.util.ArrayList;
import java.util.List;

import gameWorld.rooms.portes.CarriesAway;
import gameWorld.rooms.portes.Door;
import gameobjects.objets.GenericObject;
import gameobjects.objets.consommables.BoxWin;
import gameobjects.objets.consommables.Coin;
import gameobjects.objets.consommables.Life;
import gameobjects.objets.passifs.BloodOfMartyr;
import gameobjects.objets.passifs.LifeExtension;
import gameobjects.objets.passifs.NumberOne;
import gameobjects.obstacles.GenericObstacle;
import gameobjects.obstacles.Poop;
import gameobjects.obstacles.Rock;
import gameobjects.obstacles.Spikes;
import gameobjects.personnages.Hero;
import gameobjects.personnages.monstres.Boss;
import gameobjects.personnages.monstres.Fly;
import gameobjects.personnages.monstres.Monster;
import gameobjects.personnages.monstres.Spider;
import libraries.Physics;
import libraries.StdDraw;
import libraries.Vector2;
import resources.ImagePaths;
import resources.RoomInfos;
/*
 * contructeur de RoomObjet , une room particuliere pour entrer dedans .
 */

public class RoomObjet extends Room {
	private static final String MON_TYPE = "OBJECT_ROOM";
	public RoomObjet (Hero hero , Integer idSalle) {
		super(hero, idSalle);
		this.type=MON_TYPE;
		if(Math.random()<=0.5) {
			this.lstObjet.add(new NumberOne(new Vector2(0.5,0.5)));
			
		}else {
			this.lstObjet.add(new BloodOfMartyr(new Vector2(0.5,0.5)));
		}
		
	}
	@Override
	public void updateRoom() {
	        makeHeroPlay();
	        collisionReport();
	        nettoyageLarme();
	    
	}
	@Override
	public void drawRoom() {
		StdDraw.picture(0.5, 0.5, ImagePaths.SHOP_ROOM, 1, 1);

        hero.drawGameObject();

        dessinePorte();

        dessineMonstre();
        dessineLarme();
        affichageViePiece();

        affichageObjets();
	}
	
	
	
	
	
	
}
