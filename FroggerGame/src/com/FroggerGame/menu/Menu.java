package com.FroggerGame.menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import com.FroggerGame.game.Game;
import com.FroggerGame.main.Main;

public class Menu extends MouseAdapter implements MouseMotionListener {

	public static Color BACKGROUND_COLOR = Game.SAFE_LANE_COLOR.brighter();
	public static Color TITLE_COLOR = new Color(0, 130, 0);
	public static Color BODY_COLOR = Color.black;
	public static Color HIGHLIGHT_BTN_BG_COLOR = TITLE_COLOR;
	public static Color HIGHLIGHT_BTN_COLOR = Color.white;

	public static String LOGO_IMG = "img/frog.png";
		
	public static Font TITLE_FONT = new Font("Arial", Font.BOLD, 40);
	public static Font BUTTONS_FONT = new Font("Arial", Font.PLAIN, 30);
	
	public static int LOGO_Y = 40;
	public static int LOGO_HEIGHT = 130;
	public static int TITLE_Y = LOGO_Y + LOGO_HEIGHT + 50;
	public static int MARGIN_BOTTOM = 30;
	public static int BUTTON_WIDTH = 200;
	public static int BUTTON_HEIGHT = 70;
	
	private MenuActionListener actionListener;
	private MenuButton playButton;
	private MenuButton quitButton;
	private BufferedImage logoImg;
	private boolean mouseListenerEnabled = true;
	
	public Menu(MenuActionListener actionListener) {
		this.actionListener = actionListener;
		
		try {
			this.logoImg = ImageIO.read(new File(LOGO_IMG));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		int centerX = (Main.WIDTH - BUTTON_WIDTH) / 2;
		int playButtonY = TITLE_Y + 10 + MARGIN_BOTTOM;
		int quitButtonY = playButtonY + BUTTON_HEIGHT + MARGIN_BOTTOM;
		
		this.playButton = new MenuButton(centerX, playButtonY, BUTTON_WIDTH, BUTTON_HEIGHT);
		this.playButton.setTitle("Play", BUTTONS_FONT, BODY_COLOR);
		playButton.setHighlightColor(HIGHLIGHT_BTN_BG_COLOR, HIGHLIGHT_BTN_COLOR);
		
		quitButton = new MenuButton(centerX, quitButtonY, BUTTON_WIDTH, BUTTON_HEIGHT);
		quitButton.setTitle("Quit", BUTTONS_FONT, BODY_COLOR);
		quitButton.setHighlightColor(HIGHLIGHT_BTN_BG_COLOR, HIGHLIGHT_BTN_COLOR);
	}
	
	public void enable() {
		mouseListenerEnabled = true;
	}
	
	public void disable() {
		mouseListenerEnabled = false;
		playButton.mouseOver = false;
		quitButton.mouseOver = false;
	}
	
	// MARK: Mouse Events
		
	public void mouseMoved(MouseEvent e) {
		Point cursor = e.getPoint();
		
		if (mouseListenerEnabled) {
			if (playButton.contains(cursor)) {
				playButton.mouseOver = true;
			} else {
				playButton.mouseOver = false;
			}
			
			if (quitButton.contains(cursor)) {
				quitButton.mouseOver = true;
			} else {
				quitButton.mouseOver = false;
			}
		}
	}
	
	public void mouseReleased(MouseEvent e) {
		Point cursor = e.getPoint();
		
		if (mouseListenerEnabled) {
			if (playButton.contains(cursor)) {
				actionListener.menuPlayButtonClicked();
			}
			
			if (quitButton.contains(cursor)) {
				actionListener.menuQuitButtonClicked();
			}
		}
	}
	
	// MARK: Graphics
	
	private void drawBackground(Graphics graphics) {
		graphics.setColor(BACKGROUND_COLOR);
		graphics.fillRect(0, 0, Main.WIDTH, Main.HEIGHT);
	}
	
	private void drawTitle(Graphics graphics) {
		graphics.setFont(TITLE_FONT);
		
		String title = "FROGGER GAME";
		int titleWidth = graphics.getFontMetrics().stringWidth(title);
		int centerX = (Main.WIDTH - titleWidth) / 2;
	
		graphics.setColor(TITLE_COLOR);
		graphics.drawString(title, centerX, TITLE_Y);
	}
	
	private void drawButtons(Graphics graphics) {
		graphics.setColor(BODY_COLOR);
		playButton.draw(graphics);
		quitButton.draw(graphics);
	}
	
	public void drawLogo(Graphics graphics) {
		try {
			float ratio = logoImg.getWidth() / logoImg.getHeight();
			int height = LOGO_HEIGHT;
			int width = Math.round(height * ratio);
			int centerX = (Main.WIDTH - width) / 2;
			graphics.drawImage(logoImg, centerX, LOGO_Y, width, height, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void render(Graphics graphics) {
		drawBackground(graphics);
		drawLogo(graphics);
		drawTitle(graphics);
		drawButtons(graphics);
	}

}
