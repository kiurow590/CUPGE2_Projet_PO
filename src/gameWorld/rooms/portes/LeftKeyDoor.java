package gameWorld.rooms.portes;

import resources.ImagePaths;

public class LeftKeyDoor extends LeftDoor {
	

	public LeftKeyDoor(int idSalle) {
		super(idSalle);
		this.imagePaths = ImagePaths.PORTE_OBJET_FERME;
		estOuvert = false;
	}

}
