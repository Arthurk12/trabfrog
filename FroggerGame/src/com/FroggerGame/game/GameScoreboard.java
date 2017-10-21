package com.FroggerGame.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Timer;
import java.util.TimerTask;

import com.FroggerGame.main.Main;

public class GameScoreboard {
		
	public static int HEIGHT = 60;
	public static int BORDER_WIDTH = 0;
	public static int TIMER_BAR_WIDTH = 184;	
	
	public static Color BACKGROUND_COLOR = Color.black;
	public static Color BORDER_COLOR = Color.blue;
	public static Color FONT_COLOR = Color.white;
	public static Color FULL_LIFE_COLOR = new Color(0, 180, 0);
	public static Color MED_LIFE_COLOR = new Color(255, 220, 0);
	public static Color LOW_LIFE_COLOR = Color.red;
	
	public static Font FONT = new Font("Arial", Font.BOLD, 13);
	
	public static int FIRST_LEVEL_TIME = 40;
	public static int LEVELS_TIME_DIF = 5;
	public static int SCORE_STEP = 10;
	public static int LEVEL_END_BONUS_SCORE = 50;
	public static int INIT_LIFE = 3;
	
	public static int getLevelTime(int level) {
		if (level <= Game.LEVELS) {
			return FIRST_LEVEL_TIME - (level-1)*LEVELS_TIME_DIF;
		} else {
			return 0;
		}
	}
	
	private GameScoreboardListener listener;
	private Timer timer;

	private int level;
	private int life;
	private int totalScore;
	private int maxLane;
	private int levelTime;
	private int remainingTime;
	
	public GameScoreboard(GameScoreboardListener listener) {
		this.listener = listener;
		this.level = 1;
		this.life = INIT_LIFE;
		this.totalScore = 0;
		this.maxLane = -1;
		this.resetTimer();
	}

 	public void decreaseLife() {
		life--;		
		
		if (life > 0) {
			resetTimer();
			startTimer();
		} else {
			listener.lifeEnded();
		}
	}
 	
 	public void laneChanged(int lane) {
 		if (lane > maxLane) {
 			if (lane < 9) {
 				totalScore += SCORE_STEP;
 			} else {
 				float timeBonusFactor = (1 + (float) remainingTime/levelTime);
 				System.out.println(timeBonusFactor);
 				totalScore += LEVEL_END_BONUS_SCORE * timeBonusFactor;
 			}
 			maxLane = lane;
 		}
 	}
 		
	public boolean nextLevel() {
		level++;
		
		if (level <= Game.LEVELS) {
			resetTimer();
			maxLane = -1;
			return true;
		} else {
			listener.allLevelsCompleted();
			return false;
		}
	}
	
	public void tick() {
		
	}
	
	// MARK: Time Management
	
	public void resetTimer() {
		if (timer != null)
			stopTimer();
		timer = new Timer();
		levelTime = GameScoreboard.getLevelTime(level);
		remainingTime = GameScoreboard.getLevelTime(level);
	}
	
	public void startTimer() {
		timer.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				remainingTime--;
				
				if (remainingTime == 0) {
					timer.cancel();
					levelTimeEnded();
				}
			}
		}, 500, 1000);
	}
	
	public void stopTimer() {
		if (timer != null) {
			timer.cancel();
		}
	}
	
	private void levelTimeEnded() {
 		listener.levelTimeEnded();
 	}
	
	// MARK: Graphics
	
	private void drawBackground(Graphics graphics) {
		graphics.setColor(BACKGROUND_COLOR);
		graphics.fillRect(0, 0, Main.WIDTH, HEIGHT);
	}
	
	private void drawBorder(Graphics graphics) {
		graphics.setColor(BORDER_COLOR);
	
		for (int i = 0; i < BORDER_WIDTH; i++) {
			graphics.drawRect(i, i, Main.WIDTH - (2*i+1), HEIGHT - (2*i+1));
		}
	}
	
	private void printLevel(Graphics graphics) {
		graphics.setColor(FONT_COLOR);
		graphics.setFont(FONT);
		graphics.drawString("Level: " + level, 15, 25);
	}
	
	private void printScore(Graphics graphics) {
		graphics.setColor(FONT_COLOR);
		graphics.setFont(FONT);
		graphics.drawString("Score: " + totalScore, 15, 45);
	}
	
	private void drawLife(Graphics graphics) {
		graphics.setColor(FONT_COLOR);
		graphics.setFont(FONT);
		graphics.drawString("Life: " + life, 150, 25);
		
		switch (life) {
		case 1: graphics.setColor(LOW_LIFE_COLOR); break;
		case 2: graphics.setColor(MED_LIFE_COLOR); break;
		case 3: graphics.setColor(FULL_LIFE_COLOR); break;
		}
		
		for (int i = 0; i < life; i++) {
			graphics.fillOval(150 + (i*15), 35, 10, 10);
		}
	}
	
	private void drawTimer(Graphics graphics) {
		graphics.setColor(FONT_COLOR);
		graphics.setFont(FONT);
		graphics.drawString("Remaining Time: " + remainingTime + " seconds", 
				Main.WIDTH - 200, 25);
		
		float percent = (float) remainingTime / levelTime;
		graphics.drawRect(Main.WIDTH - 200, 35, TIMER_BAR_WIDTH, 10);
		graphics.fillRect(Main.WIDTH - 200, 35, (int) (TIMER_BAR_WIDTH * percent), 10);
	}
	
	public void render(Graphics graphics) {
		drawBackground(graphics);
		drawBorder(graphics);
		
		printLevel(graphics);
		printScore(graphics);
		drawLife(graphics);
		drawTimer(graphics);
	}
	
	// MARK: Getters
	
	public int getLevel() {
		return level;
	}
	
	public int getLife() {
		return life;
	}
	
	public int getScore() {
		return totalScore;
	}
	
}
