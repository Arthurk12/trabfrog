package com.FroggerGame.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.FroggerGame.game_objects.ObstaclesLane;
import com.FroggerGame.game_objects.Player;
import com.FroggerGame.game_objects.PlayerEventListener;
import com.FroggerGame.main.Main;

public class Game extends KeyAdapter implements PlayerEventListener, GameScoreboardListener {
	
	public static final Color BACKGROUND_COLOR = new Color(50, 50, 50);
	public static final Color SAFE_LANE_COLOR = new Color(130, 130, 130);
	
	public static final int LEVELS = 5;

	private GameEventListener eventListener;
	private GameScoreboard scoreboard;
	private Player player;
	private GameMap map;
	private boolean keyListenerEnabled = true;
	
	public Game(GameEventListener eventListener) {
		this.eventListener = eventListener;
	}
	
	public void start() {
		int level = 1;
		this.scoreboard = new GameScoreboard(this);
		this.map = new GameMap(level);
		this.player = new Player(map.getHandler(), this);
		scoreboard.startTimer();
	}
	
	public void enable() {
		keyListenerEnabled = true;
	}
	
	public void disable() {
		keyListenerEnabled = false;
		if (scoreboard != null) {
			scoreboard.stopTimer();
		}
	}

	public void tick() {
		if (scoreboard != null) scoreboard.tick();
		if (player != null) player.tick();
		if (map != null) map.tick();
	}
	
	// MARK: Game Actions
	
	private void levelComplete() {
		// Scoreboard checks game completed
		if (scoreboard.nextLevel()) {
			// Game not completed yet
			map.nextLevel();
			player.goToHomePosition();
			scoreboard.startTimer();
		}
	}
	
	private void quit() {
		eventListener.gameQuitted();
	}
	
	// MARK: Key Events
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		if (keyListenerEnabled) {
			// Player movements control
			if (player != null) {
				switch (key) {
				case KeyEvent.VK_UP: 	player.moveUp(); 	break;
				case KeyEvent.VK_DOWN: 	player.moveDown(); 	break;
				case KeyEvent.VK_LEFT: 	player.moveLeft(); 	break;
				case KeyEvent.VK_RIGHT: player.moveRight(); break;
				}
			}
			
			// Quit game
			if (key == KeyEvent.VK_ESCAPE) {
				quit();
			}
		}
	}
	
	// MARK: GameScoreboardListener
	
	public void lifeEnded() {
		eventListener.gameOver(scoreboard);
	}
	
	public void levelTimeEnded() {
		scoreboard.decreaseLife();
		player.goToHomePosition();
	}
	
	public void allLevelsCompleted() {
		eventListener.gameCompleted(scoreboard);
	}
	
	// MARK: PlayerEventListener
	
	public void playerCollided() {
		scoreboard.decreaseLife();
		player.goToHomePosition();
	}
	
	public void playerReachedEndLane() {
		levelComplete();
	}
	
	public void playerLaneChanged(int lane) {
		scoreboard.laneChanged(lane);
	}
	
	// MARK: Graphics
	
	private void drawBackground(Graphics graphics) {
		graphics.setColor(BACKGROUND_COLOR);
		graphics.fillRect(0, 0, Main.WIDTH, Main.HEIGHT);
	}
	
	private void drawHomeLane(Graphics graphics) {
		graphics.setColor(SAFE_LANE_COLOR);
		graphics.fillRect(0, Main.HEIGHT - ObstaclesLane.HEIGHT, Main.WIDTH, ObstaclesLane.HEIGHT);
	}
	
	private void drawEndLane(Graphics graphics) {
		graphics.setColor(SAFE_LANE_COLOR);
		graphics.fillRect(0, ObstaclesLane.HEIGHT, Main.WIDTH, GameScoreboard.HEIGHT);
	}
	
	public void render(Graphics graphics) {
		drawBackground(graphics);
		drawHomeLane(graphics);
		drawEndLane(graphics);
		
		if (scoreboard != null) scoreboard.render(graphics);
		if (player != null) player.render(graphics);
		if (map != null) map.render(graphics);
	}
	
	
}
