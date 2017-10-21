package com.FroggerGame.game_objects;
import java.awt.Color;
import java.awt.Graphics;

public class Motorcycle extends Obstacle {
	
	public static final Color COLOR = Color.blue;
	
	public static final int WIDTH = 30;
	public static final int HEIGHT = 20;
	
	public Motorcycle(float x, float y, ObstaclesLane lane, float vel) {
		super(x, y, GameObject.Type.Motorcycle, lane, vel);
		
		this.width = WIDTH;
		this.height = HEIGHT;
	}

	public void render(Graphics g) {
		g.setColor(COLOR);
		g.fillRect((int)x, (int)y, width, height);
	}
	
}
