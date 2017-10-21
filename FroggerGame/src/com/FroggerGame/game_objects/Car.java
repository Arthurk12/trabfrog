package com.FroggerGame.game_objects;

import java.awt.Color;
import java.awt.Graphics;

public class Car extends Obstacle {

	public static final Color COLOR = new Color(250, 100, 0);
	
	public static final int WIDTH = 35;
	public static final int HEIGHT = 26;
	
	public Car(float x, float y, ObstaclesLane lane, float vel) {
		super(x, y, GameObject.Type.Car, lane, vel);
		
		this.width = WIDTH;
		this.height = HEIGHT;
	}

	public void render(Graphics g) {
		g.setColor(COLOR);
		g.fillRect((int)x, (int)y, width, height);
	}
	
}
