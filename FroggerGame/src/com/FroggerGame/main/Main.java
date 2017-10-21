package com.FroggerGame.main;
import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import com.FroggerGame.game.Game;
import com.FroggerGame.game.GameEventListener;
import com.FroggerGame.game.GameScoreboard;
import com.FroggerGame.gameScreen.GameCompletedScreen;
import com.FroggerGame.gameScreen.GameOverScreen;
import com.FroggerGame.gameScreen.GameScreen;
import com.FroggerGame.gameScreen.GameScreenListener;
import com.FroggerGame.game_objects.ObstaclesLane;
import com.FroggerGame.menu.Menu;
import com.FroggerGame.menu.MenuActionListener;

public class Main extends Canvas
				  implements Runnable, MenuActionListener, GameEventListener, GameScreenListener {
	
	public enum State {
		Menu,
		Playing,
		GameOver,
		GameCompleted,
	}
	
	private static final long serialVersionUID = -6816454035439792285L;
	
	public static int GRAPHICS_Y_FIX = 29;
	
	public static final int WIDTH = ObstaclesLane.HEIGHT * 15;
	public static final int HEIGHT = ObstaclesLane.HEIGHT * 11 + GameScoreboard.HEIGHT;
	
	public static float clamp(float var, float min, float max) {
		if (var > max) {
			return var = max;
		} else if (var < min) {
			return var = min;
		} else {
			return var;
		}
	}
	
	private Thread thread;
	private GameOverScreen gameOverScreen;
	private GameCompletedScreen gameCompletedScreen;
	private Menu menu;
	private Game game;
	
	private boolean running = false;
	private State state;
	
	public Main() {
		new Window(WIDTH, HEIGHT, "Frogger Classic", this);
		
		menu = new Menu(this);	
		addMouseListener(menu);
		addMouseMotionListener(menu);
		
		game = new Game(this);
		addKeyListener(game);
		
		gameOverScreen = new GameOverScreen(this);
		addKeyListener(gameOverScreen);
		
		gameCompletedScreen = new GameCompletedScreen(this);
		addKeyListener(gameCompletedScreen);
		
		setState(State.Menu);
		start();
	}
	
	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
		this.requestFocus();
	}
	
	public void stop() {
		System.exit(0);
	}
	
	public synchronized void run() {
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				tick();
				delta--;
			}
			
			if (running) {
				render();
			}
			
			frames++;
			
			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println("FPS: " + frames);
				frames = 0;
			}
		}
		stop();
	}
	
	private void tick() {
		switch (state) {
		case Playing: game.tick(); break;
		default: break;
		}
	}
	
	private void render() {		
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics graphics = bs.getDrawGraphics();
		
		switch (state) {
		case Playing: 		game.render(graphics); break;
		case Menu:			menu.render(graphics); break;
		case GameOver:		gameOverScreen.render(graphics); break;
		case GameCompleted: gameCompletedScreen.render(graphics); break;
		default: break;
		}
	
		graphics.dispose();
		bs.show();
	}

	// MARK: MenuActionListener
	
	public void menuPlayButtonClicked() {
		setState(State.Playing);
	}

	public void menuQuitButtonClicked() {
		stop();
	}

	// MARK: GameEventListener
	
	public void gameQuitted() {
		setState(State.Menu);
	}
	
	public void gameOver(GameScoreboard scoreboard) {
		gameOverScreen.setScoreboard(scoreboard);
		setState(State.GameOver);
	}
	
	public void gameCompleted(GameScoreboard scoreboard) {
		gameCompletedScreen.setScoreboard(scoreboard);
		setState(State.GameCompleted);
	}
	
	// MARK: GameScreenListener
	
	public void exitGameScreen(GameScreen screen) {
		setState(State.Menu);
	}
	
	// MARK: Getters and Setter
	
	public void setState(State state) {			
		this.state = state;
		
		if (state == State.GameCompleted) {
			gameCompletedScreen.enable();
		} else {
			gameCompletedScreen.disable();
		}
		
		if (state == State.GameOver) {
			gameOverScreen.enable();
		} else {
			gameOverScreen.disable();
		}
		
		if (state == State.Menu) {
			menu.enable();
		} else {
			menu.disable();
		}
		
		if (state == State.Playing) {
			game.enable();
			game.start();
		} else {
			game.disable();
		}
	}
	
	public static void main(String args[]) {
		new Main();
	}

}
