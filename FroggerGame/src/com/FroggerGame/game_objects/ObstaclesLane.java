package com.FroggerGame.game_objects;

import com.FroggerGame.game.GameHandler;
import com.FroggerGame.main.Main;

public class ObstaclesLane {

	public static int HEIGHT = 40;
	
	public GameHandler handler;
	public GameObject.Type obstaclesType;
	public int obstaclesCount;
	public float gapSpace;
	
	public ObstaclesLane(GameObject.Type obsType, int obsCount, int y, float vel, GameHandler handler) {
		this.obstaclesType = obsType;
		this.obstaclesCount = obsCount;
		this.handler = handler;
		
		float x, obsY;
		switch (obstaclesType) {
		case Motorcycle:
			// Calculates gap space between obstacles
			gapSpace = (Main.WIDTH - (obsCount*Motorcycle.WIDTH)) / obsCount;
			
			// Instantiate [obsCount+1] obstacles separated by [gapSpace]
			for (int i = 0; i < obsCount+1; i++) {
				x = i * (Motorcycle.WIDTH + gapSpace);
				obsY = y + (ObstaclesLane.HEIGHT - Motorcycle.HEIGHT) / 2;
				handler.addObject(new Motorcycle(x, obsY, this, vel));
			}
			break;
		case Truck:
			// Calculates gap space between obstacles
			gapSpace = (Main.WIDTH - (obsCount*Truck.WIDTH)) / obsCount;
			
			// Instantiate [obsCount+1] obstacles separated by [gapSpace]
			for (int i = 0; i < obsCount+1; i++) {
				x = i * (Truck.WIDTH + gapSpace);
				obsY = y + (ObstaclesLane.HEIGHT - Truck.HEIGHT) / 2;
				handler.addObject(new Truck(x, obsY, this, vel));
			}
			break;
		case Car:
			// Calculates gap space between obstacles
			gapSpace = (Main.WIDTH - (obsCount*Car.WIDTH)) / obsCount;
			
			// Instantiate [obsCount+1] obstacles separated by [gapSpace]
			for (int i = 0; i < obsCount+1; i++) {
				x = i * (Car.WIDTH + gapSpace);
				obsY = y + (ObstaclesLane.HEIGHT - Car.HEIGHT) / 2;
				handler.addObject(new Car(x, obsY, this, vel));
			}
			break;
		default: break;
		}
		
	}
	
}
