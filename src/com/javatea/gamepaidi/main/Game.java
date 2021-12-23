package com.javatea.gamepaidi.main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.io.File;

import javax.swing.JPanel;

import com.javatea.gamepaidi.gamestate.GameStateManager;
import com.javatea.gamepaidi.utils.KeyHandler;
import com.javatea.gamepaidi.utils.LoadSave;

@SuppressWarnings({ "serial", "unused" })
public class Game extends JPanel implements Runnable{
	
	public final static int WIDTH = 640, 
			HEIGHT = 360, 
			TRUTILESIZE = 16, 
			SCALE = 2,
			FPS = 60,
			TILESIZE = TRUTILESIZE * SCALE;
	
	private boolean running;
	private Thread thread;

	private KeyHandler keyHandler = new KeyHandler();
	private GameStateManager gameStateManager = new GameStateManager(keyHandler);
	
	public Game() {
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		this.setFocusable(true);
		this.addKeyListener(keyHandler);
		
		LoadSave.createFile();
	}
	
	public void start() {
		if(!running) {
			running = true;
			thread = new Thread(this);
			thread.start();
		}
	}
	
	@Override
	public void run() {
		
		this.requestFocus();
		long interval = 1000000000/FPS; 
		long currentTime, elapsedTime, waitTime;
		
		while(running) {
			currentTime = System.nanoTime();
			
			update();
			repaint();
			
			elapsedTime = System.nanoTime() - currentTime;
			waitTime = interval - elapsedTime;
			
			try {
				if(waitTime > 0)Thread.sleep(waitTime / 1000000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	//UPDATE
	public void update() {gameStateManager.update();}
	
	
	//DRAW
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		gameStateManager.draw(g);
		
		g.dispose();
	}
	
	public static void main(String[] args) {
		new Window(new Game());
	}
	
}
