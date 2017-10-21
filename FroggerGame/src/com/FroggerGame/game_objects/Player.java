package com.FroggerGame.game_objects;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Timer;
import java.util.TimerTask;

import com.FroggerGame.game.GameHandler;
import com.FroggerGame.game.GameScoreboard;
import com.FroggerGame.main.Main;

public class Player extends GameObject {
	
	public static final Color COLOR = new Color(0, 160, 0);
	public static final Color COLISION_COLOR = new Color(230, 0, 0);
	
	public static final int COLISION_DELAY = 500;
	
	public static final int WIDTH = 30;
	public static final int HEIGHT = 30;

	public static int getLaneMarginX() {
		return (ObstaclesLane.HEIGHT - WIDTH) / 2;
	}
	
	public static int getLaneMarginY() {
		return (ObstaclesLane.HEIGHT - HEIGHT) / 2;
	}
	
	private PlayerEventListener eventListener;
	private GameHandler gameObjsHandler;
	private Timer colisionTimer;
	private boolean collided = false;
	
	public Player(int x, int y, GameHandler gameObjsHandler, PlayerEventListener eventListener) {
		super(x, y, GameObject.Type.Player);
		
		this.eventListener = eventListener;
		this.gameObjsHandler = gameObjsHandler;
		this.colisionTimer = new Timer();
		
		this.width = WIDTH;
		this.height = HEIGHT;
		this.velX = 0;
		this.velY = 0;
	}
	
	public Player(GameHandler gameObjsHandler, PlayerEventListener eventListener) {
		// Creates player centralized in home lane
		this(0, 0, gameObjsHandler, eventListener);
		goToHomePosition();
	} 
	
	public void tick() {
		x += velX;
		y += velY;
		
		fixCoordsRange();
		
		// Events check and binding
		if (!collided && checkColision()) {
			collided = true;
			colisionTimer.schedule(new TimerTask() {
				public void run() {
					eventListener.playerCollided();
					collided = false;
				}
			}, COLISION_DELAY);
		}
		
		if (checkReachedEndLane()) {
			eventListener.playerReachedEndLane();
		}
	}
	
	private boolean checkReachedEndLane() {
		Rectangle endLane = new Rectangle();
		endLane.setBounds(0, GameScoreboard.HEIGHT, Main.WIDTH, ObstaclesLane.HEIGHT);
		return endLane.contains(getBounds());
	}
	
	private boolean checkColision() {
		for (GameObject obj : gameObjsHandler.objects) {
			if (obj.getType() != this.type) {
				if (this.getBounds().intersects(obj.getBounds())) {
					return true;
				}
			}
		}
		return false;
	}
	
	private void fixCoordsRange() {
		x = Main.clamp(x, 
				Player.getLaneMarginX(),
				Main.WIDTH - width - Player.getLaneMarginX());
		
		y = Main.clamp(y,
				GameScoreboard.HEIGHT + Player.getLaneMarginY(),
				Main.HEIGHT - height - Player.getLaneMarginY());
	}
	
	public int getCurrentLane() {
		int lane = (int)((Main.HEIGHT - y) / ObstaclesLane.HEIGHT) - 1;
		return lane;
	}
	
	public Rectangle getBounds() {	
		return new Rectangle((int)x, (int)y, width, width);
	}
	
	// MARK: Movement and Positioning
	
	public void goToHomePosition() {
		x = (Main.WIDTH - Player.WIDTH) / 2;
		y = Main.HEIGHT - ObstaclesLane.HEIGHT +  Player.getLaneMarginY();
	}
	
	public void moveUp() {
		if (!collided) {
			y -= ObstaclesLane.HEIGHT;
			fixCoordsRange();
			eventListener.playerLaneChanged(getCurrentLane());
		}
	}
	
	public void moveDown() {
		if (!collided) {
			y += ObstaclesLane.HEIGHT;
			fixCoordsRange();
			eventListener.playerLaneChanged(getCurrentLane());
		}
	}
	
	public void moveLeft() {
		if (!collided) {
			x -= ObstaclesLane.HEIGHT;
			fixCoordsRange();
		}
	}
	
	public void moveRight() {
		if (!collided) {	
			x += ObstaclesLane.HEIGHT;
			fixCoordsRange();
		}
	}

	// MARK: Graphics
	
	public void render(Graphics graphics) {
		if (collided) {
			graphics.setColor(COLISION_COLOR);
		} else {
			graphics.setColor(COLOR);
		}
		graphics.fillOval((int)x, (int)y, width, height);
	}

	
}
