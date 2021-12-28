package gameobjects;

import java.util.ArrayList;
import java.util.List;

import libraries.Physics;
import libraries.StdDraw;
import libraries.Vector2;
import resources.ImagePaths;
import resources.RoomInfos;

public class Obstacles {
	/*
	 * Attributs Des obstacles
	 */
	private Vector2 position;
	private Vector2 size;

	public Obstacles(Vector2 position, Vector2 size) {
		this.position = position;
		this.size = size;
	}

	/*
	 * getter et setter
	 */
	public Vector2 getPosition() {
		return position;
	}

	public void setPosition(Vector2 position) {
		this.position = position;
	}

	public Vector2 getSize() {
		return size;
	}

	public void setSize(Vector2 size) {
		this.size = size;
	}
}
