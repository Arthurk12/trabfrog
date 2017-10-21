package com.FroggerGame.game;

public interface GameEventListener {

	public void gameQuitted();
	public void gameOver(GameScoreboard scoreboard);
	public void gameCompleted(GameScoreboard scoreboard);
	
}
