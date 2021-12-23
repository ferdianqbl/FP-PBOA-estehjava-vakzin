package com.javatea.gamepaidi.entities;

import java.awt.Graphics;
import java.awt.Rectangle;

import com.javatea.gamepaidi.main.Game;

public class PathBlocker extends GameEntity{
	//Invisible block to define path of enemies
	
	private final int x, y;
	private final int width = Game.TILESIZE,
			height = Game.TILESIZE;
	private final int entityID = PATHBLOCKER;
			
	public PathBlocker(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	
	//GETTERS-N-SETTERS
	public int getX() {return x;}
	public void setX(int x) {}
	public int getY() {return y;}
	public void setY(int y) {}
	public int getWidth() {return width;}
	public void setWidth(int width) {}
	public int getHeight() {return height;}
	public void setHeight(int height) {}
	public int getEntityID() {return entityID;}
	public void setEntityID(int object) {}
	public int getHealth() {return 0;}
		
		
	//UPDATE
	public void update() {}


	//DRAW
	public void draw(Graphics g) {}
	
	
	//COLLISION-BOUND GETTERS
	public Rectangle getTopBound() {
		return new Rectangle(x, y, width, height);
	}
	public Rectangle getRightBound() {
		return new Rectangle(x, y, width, height);
	}
	public Rectangle getBottomBound() {
		return new Rectangle(x, y, width, height);
	}
	public Rectangle getLeftBound() {
		return new Rectangle(x, y, width, height);
	}
	
}
