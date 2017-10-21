package com.FroggerGame.gameScreen;

import java.awt.Color;

import com.FroggerGame.menu.Menu;

public class GameCompletedScreen extends GameScreen {
	
	public static final Color BACKGROUND_COLOR = Menu.BACKGROUND_COLOR;
	public static final Color TITLE_COLOR = Menu.TITLE_COLOR;
	public static final Color BODY_COLOR = Color.black;

	public GameCompletedScreen(GameScreenListener listener) {
		super(listener, "GAME COMPLETED");
		
		this.backgroundColor = BACKGROUND_COLOR;
		this.titleColor = TITLE_COLOR;
		this.textColor = BODY_COLOR;
	}
		
	
}
