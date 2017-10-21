package com.FroggerGame.menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

import com.FroggerGame.main.Main;

public class MenuButton extends Rectangle {

	private static final long serialVersionUID = -7909097742361572272L;
	
	public boolean mouseOver = false;
	public Font font;
	public String title;
	public Color color;
	public Color highlightBgColor;
	public Color highlightColor;
	
	public MenuButton(int x, int y, int width, int height) {
		super(x, y, width, height);
	}
	
	public void setTitle(String title, Font font) {
		this.title = title;
		this.font = font;
	}
	
	public void setTitle(String title, Font font, Color color) {
		setTitle(title, font);
		setColor(color);
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
	
	public void setHighlightColor(Color bgColor, Color color) {
		this.highlightBgColor = bgColor;
		this.highlightColor = color;
	}
	
	public void draw(Graphics graphics) {
		if (mouseOver && highlightColor != null) {
			graphics.setColor(highlightBgColor);
			graphics.fillRect(x, y, width, height);
			graphics.setColor(highlightColor);
		} else if (color != null) {
			graphics.setColor(color);
			graphics.drawRect(x, y, width, height);
		}
		
		if (title != null && font != null) {
			graphics.setFont(font);
			
			Rectangle2D strBounds = graphics.getFontMetrics().getStringBounds(title, graphics);
			int marginX = (width - (int)strBounds.getWidth()) / 2;
			int marginY = (height - (int)strBounds.getHeight()) / 2;
			
			graphics.drawString(title, (x + marginX), (y + marginY + Main.GRAPHICS_Y_FIX));
		}
		
	}
	
}
