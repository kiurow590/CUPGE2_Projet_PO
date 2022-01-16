package gameWorld.rooms.portes;

import resources.ImagePaths;

public class TopKeyDoor extends TopDoor {
	

	public TopKeyDoor(int idSalle) {
		super(idSalle);
		this.imagePaths = ImagePaths.PORTE_OBJET_FERME;
		estOuvert = false;
	}

}
