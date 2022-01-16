package gameWorld.rooms.portes;

import resources.ImagePaths;

public class RightKeyDoor extends RightDoor{
	

	public RightKeyDoor(int idSalle) {
		super(idSalle);
		this.imagePaths = ImagePaths.PORTE_OBJET_FERME;
		estOuvert = false;
	}

}
