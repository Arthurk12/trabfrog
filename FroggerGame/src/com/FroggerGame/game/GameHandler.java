package com.FroggerGame.game;

import java.awt.Graphics;
import java.util.Iterator;
import java.util.LinkedList;

import com.FroggerGame.game_objects.GameObject;

public class GameHandler {

	public LinkedList<GameObject> objects = new LinkedList<GameObject>();
	
	public void tick() {
		for (int i = 0; i < objects.size(); i++) {
			GameObject tempObj = objects.get(i);
			tempObj.tick();
		}
	}
	
	public void render(Graphics graphics) {
		for (int i = 0; i < objects.size(); i++) {
			GameObject tempObj = objects.get(i);
			tempObj.render(graphics);
		}
	}
	
	public void addObject(GameObject object) {
		objects.add(object);
	}
	
	public void removeObject(GameObject object) {
		objects.remove(object);
	}
	
	public void removeAllObjects() {
		Iterator<GameObject> iterator = objects.iterator();
		while (iterator.hasNext()) {
			iterator.next();
			iterator.remove();
		}
	}
	
}
