package com.FroggerGame.gameScreen;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.FroggerGame.game.GameScoreboard;
import com.FroggerGame.main.Main;

public abstract class GameScreen extends KeyAdapter {
	
	public static Font TITLE_FONT = new Font("Arial", Font.BOLD, 50);
	public static Font BODY_FONT = new Font("Arial", Font.PLAIN, 20);
	public static Font SCORE_FONT = new Font("Arial", Font.PLAIN, 30);
	
	protected Color backgroundColor = Color.black;
	protected Color titleColor = Color.white;
	protected Color textColor = Color.white;
	
	protected Font titleFont = TITLE_FONT;
	protected Font bodyFont = BODY_FONT;
	protected Font scoreFont = SCORE_FONT;

	protected GameScreenListener listener;
	protected GameScoreboard scoreboard;
	protected boolean keyListenerEnabled = true;
	protected String title;
	
	public GameScreen(GameScreenListener listener, String title) {
		this.listener = listener;
		this.title = title;
	}
	
	public void enable() {
		keyListenerEnabled = true;
	}
	
	public void disable() {
		keyListenerEnabled = false;
	}
	
	public void setScoreboard(GameScoreboard scoreboard) {
		this.scoreboard = scoreboard;
	}
	
	// MARK: Key Events
	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		if (keyListenerEnabled) { 			
			if (key == KeyEvent.VK_ENTER) {
				listener.exitGameScreen(this);
			}
		}
	}
	
	// MARK: Graphics
	
	protected void drawBackground(Graphics graphics) {
		graphics.setColor(backgroundColor);
		graphics.fillRect(0, 0, Main.WIDTH, Main.HEIGHT);
	}
	
	protected void drawTitle(Graphics graphics) {
		graphics.setColor(titleColor);
		graphics.setFont(this.titleFont);
		
		int titleWidth = graphics.getFontMetrics().stringWidth(title);
		int centerX = (Main.WIDTH - titleWidth) / 2;
		graphics.drawString(title, centerX, 150);
	}
	
	protected void printScore(Graphics graphics) {
		graphics.setColor(textColor);
		graphics.setFont(bodyFont);
		
		String msg = "Your final score was";
		int msgWidth = graphics.getFontMetrics().stringWidth(msg);
		int msgCenterX = (Main.WIDTH - msgWidth) / 2;
		graphics.drawString(msg, msgCenterX, 250);
		
		if (scoreboard != null) {
			graphics.setFont(scoreFont);
			String score = String.valueOf(scoreboard.getScore());
			int scoreWidth = graphics.getFontMetrics().stringWidth(score);
			int scoreCenterX = (Main.WIDTH - scoreWidth) / 2;
			graphics.drawString(score, scoreCenterX, 300);
		}
	}
	
	protected void printExitMessage(Graphics graphics) {
		graphics.setColor(textColor);
		graphics.setFont(bodyFont);
		
		String msg = "Press ENTER to exit";
		int msgWidth = graphics.getFontMetrics().stringWidth(msg);
		int msgCenterX = (Main.WIDTH - msgWidth) / 2;
		graphics.drawString(msg, msgCenterX, 390);
	}
	
	public void render(Graphics graphics) {
		drawBackground(graphics);
		drawTitle(graphics);
		printScore(graphics);
		printExitMessage(graphics);
	}
	
	
}
