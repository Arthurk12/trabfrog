package com.FroggerGame.game_objects;
import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class GameObject {
	
	public enum Type {
		Player,
		Motorcycle,
		Truck,
		Car;
	}
	
	protected float x, y;
	protected int width, height;
	protected float velX, velY;
	protected GameObject.Type type;
	
	public GameObject(float x, float y, Type type) {
		this.x = x;
		this.y = y;
		this.type = type;
	}
	
	public abstract void tick();
	public abstract void render(Graphics g);
	public abstract Rectangle getBounds();
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public float getX() {
		return x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public float getY() {
		return y;
	}
	
	public GameObject.Type getType() {
		return type;
	}
	
	public void setVelX(int velX) {
		this.velX = velX;
	}
	
	public float getVelX() {
		return velX;
	}

	public void setVelY(int velY) {
		this.velY = velY;
	}
	
	public float getVelY() {
		return velY;
	}
	
}
