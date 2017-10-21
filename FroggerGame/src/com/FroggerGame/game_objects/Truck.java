package com.FroggerGame.game_objects;
import java.awt.Color;
import java.awt.Graphics;

public class Truck extends Obstacle {
	
	public static final Color COLOR = new Color(250, 230, 0);
	
	public static final int WIDTH = 40;
	public static final int HEIGHT = 30;
	
	public Truck(float x, float y, ObstaclesLane lane, float vel) {
		super(x, y, GameObject.Type.Truck, lane, vel);
		
		this.width = WIDTH;
		this.height = HEIGHT;
	}

	public void render(Graphics g) {
		g.setColor(COLOR);
		g.fillRect((int)x, (int)y, width, height);
	}
	
}
