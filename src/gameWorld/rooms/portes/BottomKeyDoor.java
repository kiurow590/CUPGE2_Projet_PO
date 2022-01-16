package gameWorld.rooms.portes;

import resources.ImagePaths;

public class BottomKeyDoor extends BottomDoor {
	

	public BottomKeyDoor(int idSalle) {
		super(idSalle);
		this.imagePaths = ImagePaths.PORTE_OBJET_FERME;
		estOuvert = false;
	}
}
