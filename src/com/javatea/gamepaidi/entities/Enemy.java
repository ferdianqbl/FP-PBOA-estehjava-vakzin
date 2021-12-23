package com.javatea.gamepaidi.entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.javatea.gamepaidi.main.Game;
import com.javatea.gamepaidi.utils.Animation;

public class Enemy extends GameEntity implements Collidable{
	private int x, y;
	private float velX = 1;
	private float velY = 0;
	private final float gravity = 0.3F;
	
	private int width, height;
	private int entityID;
	
	private Handler handler;
	
	private boolean facingRight = true;
	
	private ArrayList<BufferedImage> textures;
	private Animation idleLeft;
	private Animation idleRight;
	private Animation walkLeft;
	private Animation walkRight;
	
	private Animation currAnimation;
	private int pauseCounter;
	private int idleDuration = 12;
	private int walkDuration = 8;
	
	public Enemy(int x, int y, ArrayList<BufferedImage> textures, Handler handler) {
		this.entityID = ENEMY;
		
		this.x = x;
		this.y = y;
		this.width = 16 * Game.SCALE;
		this.height = 23 * Game.SCALE;
		
		this.textures = textures;
		this.idleRight = new Animation(idleDuration, 0, 4, this.textures);
		this.idleLeft = new Animation(idleDuration, 4, 4, this.textures);
		this.walkRight = new Animation(walkDuration, 8, 8, this.textures);
		this.walkLeft = new Animation(walkDuration, 16, 8, this.textures);
		
		this.currAnimation = walkRight;
		
		this.handler = handler;
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
	public float getVelX() {return velX;}
	public float getVelY() {return velY;}
	public int getEntityID() {return entityID;}
	public void setEntityID(int entityID) {this.entityID = entityID;}
	public int getHealth() {return 0;}
	
	//UPDATE
	public void update() {
		// TODO Auto-generated method stub
		x += velX;
		velY += gravity;
		y += velY;
		
		collide();
		
		pauseTimer();
		currAnimation.runAnimation();
	}

	
	//DRAW
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		enemyDraw(g, currAnimation.getCurrFrame());
	}
	private void enemyDraw(Graphics g, BufferedImage imageToDraw) {
		g.drawImage(imageToDraw, 
				x - (imageToDraw.getWidth()*Game.SCALE-width)/2, 
				y - (imageToDraw.getHeight()*Game.SCALE-height), 
				imageToDraw.getWidth() * Game.SCALE, 
				imageToDraw.getHeight() * Game.SCALE, 
				null);
	}
	
	
	//COLLISION
	public void collide() {
		for(GameEntity x : handler.getEntities()) {
			if(x.getEntityID() == BLOCK || x.getEntityID() == PATHBLOCKER) {
				if(getBottomBound().intersects(x.getTopBound())) {
					velY = 0;
					y = x.getY() - this.height;
				}if(getLeftBound().intersects(x.getRightBound())) {
					velX = 0;
					this.x = x.getX() + x.getWidth() + 1;
					currAnimation = idleLeft;
					pauseCounter = idleDuration*6*3;
				}if(getRightBound().intersects(x.getLeftBound())) {
					velX = 0;
					this.x = x.getX() - this.width - 1;
					currAnimation = idleRight;
					pauseCounter = idleDuration*6*3;
				}
			}
		}
	}
	
	
	//PAUSE WHEN HIT A BLOCK
	private void pauseTimer() {
		if(pauseCounter > 0) pauseCounter--;
		else if(pauseCounter == 0){
			pauseCounter = -1;
			stopPause();
		}
	}
	private void stopPause() {
		if(facingRight) {
			velX = -1;
			currAnimation = walkLeft;
			facingRight = false;
		}else {
			velX = 1;
			currAnimation = walkRight;
			facingRight = true;
		}
	}
	
	
	//COLLISION-BOUND GETTERS
	public Rectangle getTopBound() {
		return new Rectangle(x + width/6, y-1, 2*width/3, height/8);
	}
	public Rectangle getRightBound() {
		return new Rectangle(x + 7*width/8 + 1, y + height/6, width/8, 2*height/3);
	}
	public Rectangle getBottomBound() {
		return new Rectangle(x + width/6, y + 7*height/8 + 1, 2*width/3, height/8);
	}
	public Rectangle getLeftBound() {
		return new Rectangle(x-1, y + height/6, width/8, 2*height/3);
	}
}
