package com.javatea.gamepaidi.gamestate;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.javatea.gamepaidi.entities.Block;
import com.javatea.gamepaidi.entities.Enemy;
import com.javatea.gamepaidi.entities.Flag;
import com.javatea.gamepaidi.entities.GameEntity;
import com.javatea.gamepaidi.entities.Handler;
import com.javatea.gamepaidi.entities.Heart;
import com.javatea.gamepaidi.entities.Obstacle;
import com.javatea.gamepaidi.entities.PathBlocker;
import com.javatea.gamepaidi.entities.Player;
import com.javatea.gamepaidi.main.Game;
import com.javatea.gamepaidi.utils.Camera;
import com.javatea.gamepaidi.utils.ImageLoader;
import com.javatea.gamepaidi.utils.KeyHandler;
import com.javatea.gamepaidi.utils.Texture;

public class Level {
	
	private ImageLoader loader;
	private BufferedImage levelLayout;
	private BufferedImage backGround;
	private KeyHandler keyHandler;
	private Handler handler;
	private GameStateManager gsm;
	private Texture textures;
	private Camera camera = new Camera(0,0);
	
	public static final int MAX_HEALTH = 3;
	private int playerHealth = MAX_HEALTH;
	private ArrayList<Heart> playerLife;
	
	public Level(ImageLoader loader, KeyHandler keyHandler, Handler handler, GameStateManager gsm,
			String levelLayoutPath, Texture textures, String path) {
		this.loader = loader;
		this.keyHandler = keyHandler;
		this.handler = handler;
		this.textures = textures;
		this.gsm = gsm;
		
		this.backGround = loader.load(path);
		handlerInit(levelLayoutPath);
	}

	public void handlerInit(String path) {
		this.levelLayout = loader.load(path);
		
		int w = levelLayout.getWidth();
		int h = levelLayout.getHeight();		
		
		for(int x = 0; x < w; x++) {
			for(int y = 0; y < h; y++) {
				int pixel = levelLayout.getRGB(x, y);
				int red = (pixel >> 16) & 255;
				int green = (pixel >> 8) & 255;
				int blue = pixel & 255;
				
				
				//BLOCK
				if(red == 200 && green == 200 && blue == 200) {
					handler.add(new Block(Game.TILESIZE*x, Game.TILESIZE*y, 0, textures.getBlockTextures()));
				}if(red == 230 && green == 230 && blue == 230) {
					handler.add(new Block(Game.TILESIZE*x, Game.TILESIZE*y, 1, textures.getBlockTextures()));
				}if(red == 255 && green == 255 && blue == 255) {
					handler.add(new Block(Game.TILESIZE*x, Game.TILESIZE*y, 2, textures.getBlockTextures()));
				}if(red == 145 && green == 145 && blue == 145) {
					handler.add(new Block(Game.TILESIZE*x, Game.TILESIZE*y, 3, textures.getBlockTextures()));
				}if(red == 175 && green == 175 && blue == 175) {
					handler.add(new Block(Game.TILESIZE*x, Game.TILESIZE*y, 4, textures.getBlockTextures()));
				}if(red == 205 && green == 205 && blue == 205) {
					handler.add(new Block(Game.TILESIZE*x, Game.TILESIZE*y, 5, textures.getBlockTextures()));
				}if(red == 95 && green == 95 && blue == 95) {
					handler.add(new Block(Game.TILESIZE*x, Game.TILESIZE*y, 6, textures.getBlockTextures()));
				}if(red == 125 && green == 125 && blue == 125) {
					handler.add(new Block(Game.TILESIZE*x, Game.TILESIZE*y, 7, textures.getBlockTextures()));
				}if(red == 155 && green == 155 && blue == 155) {
					handler.add(new Block(Game.TILESIZE*x, Game.TILESIZE*y, 8, textures.getBlockTextures()));
				}
				
				if(red == 170 && green == 89 && blue == 89) {
					handler.add(new Block(Game.TILESIZE*x, Game.TILESIZE*y, 9, textures.getBlockTextures()));
				}if(red == 112 && green == 58 && blue == 58) {
					handler.add(new Block(Game.TILESIZE*x, Game.TILESIZE*y, 10, textures.getBlockTextures()));
				}if(red == 63 && green == 35 && blue == 35) {
					handler.add(new Block(Game.TILESIZE*x, Game.TILESIZE*y, 11, textures.getBlockTextures()));
				}
				
				if(red == 102 && green == 188 && blue == 121) {
					handler.add(new Block(Game.TILESIZE*x, Game.TILESIZE*y, 12, textures.getBlockTextures()));
				}if(red == 84 && green == 161 && blue == 101) {
					handler.add(new Block(Game.TILESIZE*x, Game.TILESIZE*y, 13, textures.getBlockTextures()));
				}if(red == 51 && green == 98 && blue == 62) {
					handler.add(new Block(Game.TILESIZE*x, Game.TILESIZE*y, 14, textures.getBlockTextures()));
				}
				
				if(red == 101 && green == 107 && blue == 36) {
					handler.add(new Block(Game.TILESIZE*x, Game.TILESIZE*y, 15, textures.getBlockTextures()));
				}
				
				//FLAG 
				if(red == 0 && green == 255 && blue == 0) {
					handler.add(new Flag(Game.TILESIZE*x, Game.TILESIZE*y, textures.getFlagTextures()));
				}
				
				
				//VIRUS
				if(red == 255 && green == 0 && blue == 0) {
					handler.add(new Enemy(Game.TILESIZE * x, Game.TILESIZE*y, textures.getVirusTextures(), handler));
				} 
				
				
				//PATHBLOCKER
				if(red == 0 && green == 235 && blue == 255) {
					handler.add(new PathBlocker(Game.TILESIZE * x, Game.TILESIZE * y));
				}
				
				
				//OBSTACLE
				if(red == 235 && green == 255 && blue == 0) {
					handler.add(new Obstacle(Game.TILESIZE*x, Game.TILESIZE*y, textures.getObstacleTextures()));
				}
				
				
				//PLAYER
				if(red == 0 && green == 0 && blue == 255) {
					handler.add(new Player(Game.TILESIZE*x, Game.TILESIZE*y, keyHandler, handler, textures, gsm));
				}
				
				
				//PLAYER HEARTS
				playerLife = new ArrayList<Heart>();
				ArrayList<BufferedImage> heartImg = textures.getHeartTextures();
				for(int i = 0; i < MAX_HEALTH; i++) playerLife.add(new Heart(10 + (heartImg.get(0).getWidth() * Game.SCALE + 10) * i, 10, heartImg, i+1));
			}
		}
	}
	
	
	//UPDATE
	public void update() {
		handler.update();
		for(GameEntity x : handler.getEntities()) {
			if(x.getEntityID() == GameEntity.PLAYER) {
				camera.update(x);
				playerHealth = x.getHealth();
			}
		}
	}
	
	
	//DRAW
	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g.drawImage(backGround, 0, 0, Game.WIDTH, Game.HEIGHT, null);
		g2d.translate(camera.getX(), camera.getY());
		for (GameEntity x : handler.getEntities()) {
			x.draw(g);
		}
		g2d.translate(-camera.getX(), -camera.getY());
	
		for(int i = 0; i < MAX_HEALTH; i++) {
			playerLife.get(i).draw(g, playerHealth);
		}
	}
}
