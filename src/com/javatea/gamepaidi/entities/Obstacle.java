package com.javatea.gamepaidi.entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.javatea.gamepaidi.main.Game;
import com.javatea.gamepaidi.utils.Animation;

public class Obstacle extends GameEntity{
	private int x, y;
	private int width, height;
	private int entityID;
	
	private ArrayList<BufferedImage> textures;
	private Animation hazardSignal;
	
	public Obstacle(int x, int y, ArrayList<BufferedImage> obstacleTextures) {
		this.entityID = OBSTACLE;
		this.textures = obstacleTextures;
		this.hazardSignal = new Animation(7, 0, 4, obstacleTextures);
		
		this.width = this.textures.get(0).getWidth() * Game.SCALE;
		this.height = 12 * Game.SCALE;
		
		this.x = x;
		this.y = y + Game.TILESIZE - height;
		
		
	}

	
	//UPDATE
	public void update() {
		hazardSignal.runAnimation();
	}
	
	
	//DRAW
	public void draw(Graphics g) {
		obstacleDraw(g, hazardSignal.getCurrFrame());
	}
	private void obstacleDraw(Graphics g, BufferedImage imageToDraw) {
		g.drawImage(imageToDraw, 
				x - (imageToDraw.getWidth()*Game.SCALE-width)/2, 
				y - (Game.TILESIZE - height), 
				imageToDraw.getWidth() * Game.SCALE, 
				imageToDraw.getHeight() * Game.SCALE, 
				null);
	}
	
	//GETTERS-N-SETTERS
	public int getX() {return x;}
	public void setX(int x) {this.x = x;}
	public int getY() {return y;}
	public void setY(int y) {this.y = y;}
	public int getWidth() {return width;}
	public void setWidth(int width) {this.width = width;}
	public int getHeight() {return height;}
	public void setHeight(int height) {this.height = height;}
	public int getEntityID() {return this.entityID;}
	public void setEntityID(int object) {}
	public int getHealth() {return 0;}
	
	//COLLISION-BOUND GETTERS
	public Rectangle getTopBound() {
		return new Rectangle(x+width/10, y, 4*width/5, height);
	}
	public Rectangle getRightBound() {
		return new Rectangle(x+width/10, y, 4*width/5, height);
	}
	public Rectangle getBottomBound() {
		return new Rectangle(x+width/10, y, 4*width/5, height);
	}
	public Rectangle getLeftBound() {
		return new Rectangle(x+width/10, y, 4*width/5, height);	
	}
}
