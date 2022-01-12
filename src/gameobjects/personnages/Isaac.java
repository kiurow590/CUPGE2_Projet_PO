package gameobjects.personnages;

import libraries.Vector2;
import resources.HeroInfos;
import resources.ImagePaths;
import resources.RoomInfos;

public class Isaac extends Hero {

    public Isaac(Vector2 position) {
        super(position, ImagePaths.ISAAC, HeroInfos.ISAAC_SPEED, HeroInfos.ISAAC_LIFE, HeroInfos.ISAAC_LIFE);
    }

}
