package com.FroggerGame.game;

import java.awt.Graphics;

import com.FroggerGame.game_objects.GameObject;
import com.FroggerGame.game_objects.ObstaclesLane;
import com.FroggerGame.main.Main;

public class GameMap {
	
	private GameHandler handler;
	private int level;
	
	public GameMap(int level) {
		this.handler = new GameHandler();
		this.level = level;
		
		generateMap();
	}
	
	private void generateMap() {		
		// Generate random lanes scheme
		float vel = (float) (level * 0.1);
		
		int y0 = Main.HEIGHT - 2*ObstaclesLane.HEIGHT;
		new ObstaclesLane(GameObject.Type.Car, 2, y0, (float) -0.4 - vel, handler);
		
		int y1 = Main.HEIGHT - 3*ObstaclesLane.HEIGHT;
		new ObstaclesLane(GameObject.Type.Truck, 3, y1, (float) 0.6 + vel, handler);
		
		int y2 = Main.HEIGHT - 4*ObstaclesLane.HEIGHT;
		new ObstaclesLane(GameObject.Type.Motorcycle, 3, y2, (float) -0.6 - vel, handler);
		
		int y3 = Main.HEIGHT - 5*ObstaclesLane.HEIGHT;
		new ObstaclesLane(GameObject.Type.Car, 3, y3, (float) 0.7 + vel, handler);
		
		int y4 = Main.HEIGHT - 6*ObstaclesLane.HEIGHT;
		new ObstaclesLane(GameObject.Type.Truck, 3, y4, (float) 0.8 + vel, handler);
		
		int y5 = Main.HEIGHT - 7*ObstaclesLane.HEIGHT;
		new ObstaclesLane(GameObject.Type.Motorcycle, 4, y5, (float) -0.8 - vel, handler);
		
		int y6 = Main.HEIGHT - 8*ObstaclesLane.HEIGHT;
		new ObstaclesLane(GameObject.Type.Truck, 4, y6, (float) 0.8 + vel, handler);
		
		int y7 = Main.HEIGHT - 9*ObstaclesLane.HEIGHT;
		new ObstaclesLane(GameObject.Type.Motorcycle, 4, y7, (float) -0.9 - vel, handler);
	
		int y8 = Main.HEIGHT - 10*ObstaclesLane.HEIGHT;
		new ObstaclesLane(GameObject.Type.Car, 5, y8, (float) 0.4 + vel, handler);		
	}
	
	private void cleanMapElements() {
		handler.removeAllObjects();
	}
	
	public void nextLevel() {
		cleanMapElements();
		level++;
		generateMap();
	}
	
	public void tick() {
		handler.tick();
	}
	
	public void render(Graphics graphics) {
		handler.render(graphics);
	}
	
	// MARK: Getters and Setters
	
	public GameHandler getHandler() {
		return handler;
	}
	
}
