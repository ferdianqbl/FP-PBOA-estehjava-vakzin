package com.javatea.gamepaidi.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.javatea.gamepaidi.main.Game;

public class Block extends GameEntity{
	private int x, y;
	private int width, height;
	private int entityID;
	
	private BufferedImage texture;
	private int type;
	
	public Block(int x, int y, int type, ArrayList<BufferedImage> blockTextures) {
		this.x = x;
		this.y = y;
		this.width = Game.TILESIZE;
		this.height = Game.TILESIZE;
		this.type = type;
		this.texture = blockTextures.get(type);
		
		this.entityID = BLOCK;
	}
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getEntityID() {
		return entityID;
	}
	public void setEntityID(int object) {
		this.entityID = object;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	@Override
	public int getHealth() {return 0;}
	
	
	public void draw(Graphics g) {
		
		try {
			g.drawImage(this.texture, x, y, width, height, null);
		}catch(Exception e) {
			g.setColor(Color.BLUE);
			g.fillRect(x, y, width, height);
			
			g.setColor(Color.BLACK);
			g.drawRect(x,y,width,height);
		}
	}

	@Override
	public void update() {}

	@Override
	public Rectangle getTopBound() {
		return new Rectangle(this.x, this.y, this.width, this.height);
	}

	@Override
	public Rectangle getRightBound() {
		return new Rectangle(this.x, this.y, this.width, this.height);
	}

	@Override
	public Rectangle getBottomBound() {
		return new Rectangle(this.x, this.y, this.width, this.height);
	}

	@Override
	public Rectangle getLeftBound() {
		return new Rectangle(this.x, this.y, this.width, this.height);
	}
}
