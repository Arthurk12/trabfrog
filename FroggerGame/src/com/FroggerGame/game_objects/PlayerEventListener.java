package com.FroggerGame.game_objects;

public interface PlayerEventListener {

	public void playerCollided();
	public void playerReachedEndLane();
	public void playerLaneChanged(int lane);
	
}
