package com.FroggerGame.game_objects;

import java.awt.Graphics;
import java.awt.Rectangle;

import com.FroggerGame.main.Main;

abstract public class Obstacle extends GameObject {
	
	protected final ObstaclesLane lane;
	
	public Obstacle(float x, float y, GameObject.Type type, ObstaclesLane lane, float vel) {
		super(x, y, type);
		
		this.velY = 0;
		this.velX = vel;
		this.lane = lane;
	}
	
	public Obstacle(float x, float y, GameObject.Type type, ObstaclesLane lane) {
		this(x, y, type, lane, 0);
	}

	public void tick() {
		x += velX;
		
		// Avoid screen overflow
		if ((x < (-width)) && velX < 0) {
			x = Main.WIDTH + lane.gapSpace;
		} else if ((x > Main.WIDTH + width) && velX > 0) {
			x = -lane.gapSpace;
		}
	}
	
	abstract public void render(Graphics graphics);
	
	public Rectangle getBounds() {	
		return new Rectangle((int)x, (int)y, width, height);
	}
	
	
}
