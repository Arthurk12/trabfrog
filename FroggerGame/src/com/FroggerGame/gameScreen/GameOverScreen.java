package com.FroggerGame.gameScreen;

import java.awt.Color;

public class GameOverScreen extends GameScreen {
	
	public static final Color BACKGROUND_COLOR = Color.black;
	public static final Color TITLE_COLOR = Color.red;
	public static final Color BODY_COLOR = Color.white;

	public GameOverScreen(GameScreenListener listener) {
		super(listener, "GAME OVER");
		
		this.backgroundColor = BACKGROUND_COLOR;
		this.titleColor = TITLE_COLOR;
		this.textColor = BODY_COLOR;
	}
	
}
