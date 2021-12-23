package com.javatea.gamepaidi.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

abstract public class GameEntity {
	
	
	//ID CODEs
	public static final int BLOCK = 0;
	public static final int PLAYER = 1;
	public static final int ENEMY = 2;
	public static final int OBSTACLE = 3;
	public static final int FLAG = 4;
	public static final int PATHBLOCKER = 9999;
	
	
	//UPDATE
	abstract public void update();
	
	
	//DRAW
	abstract public void draw(Graphics g);
	
	
	//GETTERS-N-SETTERS
	abstract public int getX();
	abstract public void setX(int x);
	abstract public int getY();
	abstract public void setY(int y);
	abstract public int getWidth();
	abstract public void setWidth(int width);
	abstract public int getHeight();
	abstract public void setHeight(int height);
	abstract public int getEntityID();
	abstract public void setEntityID(int object);
	abstract public int getHealth();
	
	
	//COLLISION-BOUND GETTERS
	abstract public Rectangle getTopBound();
	abstract public Rectangle getRightBound();
	abstract public Rectangle getBottomBound();
	abstract public Rectangle getLeftBound();
	
	
	//COLLISION-BOUND TROUBLESHOOTING
	public void drawBounds(Graphics g) {
		g.setColor(Color.RED);
		g.drawRect(getTopBound().x, getTopBound().y, getTopBound().width, getTopBound().height);
		g.drawRect(getRightBound().x, getRightBound().y, getRightBound().width, getRightBound().height);
		g.setColor(Color.GREEN);
		g.drawRect(getBottomBound().x, getBottomBound().y, getBottomBound().width, getBottomBound().height);
		g.drawRect(getLeftBound().x, getLeftBound().y, getLeftBound().width, getLeftBound().height);
	}
}
