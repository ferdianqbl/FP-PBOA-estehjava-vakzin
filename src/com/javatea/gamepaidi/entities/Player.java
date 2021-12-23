package com.javatea.gamepaidi.entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.javatea.gamepaidi.gamestate.GameStateManager;
import com.javatea.gamepaidi.main.Game;
import com.javatea.gamepaidi.utils.Animation;
import com.javatea.gamepaidi.utils.KeyHandler;
import com.javatea.gamepaidi.utils.Texture;

public class Player extends GameEntity implements Collidable{
	private int x, y;
	private float velX, velY;
	private int width, height;
	private int entityID;
	private int health = 3;
	
	private float gravity = 0.3F;
	private boolean facingRight = true;
	private boolean falling = true;
	private boolean jumping = false;
	private boolean invincible = false;
	private boolean infected = false;
	private int invincibleCounter = 0;
	
	private KeyHandler keyHandler;
	private Handler handler;
	private ArrayList<BufferedImage> textures;
	
	private Animation currAnimation;
	private Animation idleRight;
	private Animation idleLeft;
	private Animation runLeft;
	private Animation runRight;
	
	private Animation infectedIdleRight;
	private Animation infectedIdleLeft;
	private Animation infectedRunLeft;
	private Animation infectedRunRight;
	
	private GameStateManager gsm;
	
	public Player(int x, int y, KeyHandler keyHandler, Handler handler, Texture textures, GameStateManager gsm) {
		this.x = x;
		this.y = y;
		this.width = 15 * Game.SCALE;
		this.height = 31 * Game.SCALE;
		
		this.keyHandler = keyHandler;
		this.entityID = PLAYER;
		this.handler = handler;
		
		this.textures = textures.getPlayerTextures();
		this.idleRight = new Animation(10, 0, 4, this.textures);
		this.idleLeft = new Animation(10, 4, 4, this.textures);
		this.runRight = new Animation(4, 8, 8, this.textures);
		this.runLeft = new Animation(4, 16, 8, this.textures);
		
		this.infectedIdleRight = new Animation(10, 24, 4, this.textures);
		this.infectedIdleLeft = new Animation(10, 28, 4, this.textures);
		this.infectedRunRight = new Animation(4, 32, 8, this.textures);
		this.infectedRunLeft = new Animation(4, 40, 8, this.textures);

		
		this.currAnimation = idleRight;
		this.gsm = gsm;
	}
	
	
	//GETTERS-N-SETTERS
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
	public int getHealth() {
		return health;
	}
	
	
	//COLLISION-BOUND GETTERS
	public Rectangle getTopBound() {
		return new Rectangle(this.x + this.width/6, this.y-1, 2*this.width/3, this.height/10);
	}
	public Rectangle getRightBound() {
		return new Rectangle(this.x + 7*this.width/8, this.y+this.height/6, this.width/8, 2*this.height/3);
	}
	public Rectangle getBottomBound() {
		return new Rectangle(this.x + this.width/6, this.y+9*this.height/10+1, 2*this.width/3, this.height/10);
	}
	public Rectangle getLeftBound() {
		return new Rectangle(this.x-1, this.y+this.height/6, this.width/8, 2*this.height/3);
	}
	
	
	//RENDER
	public void draw(Graphics g) {
		playerDraw(g, currAnimation.getCurrFrame());
	}
	private void playerDraw(Graphics g, BufferedImage imageToDraw) {
		g.drawImage(imageToDraw, 
				x - (imageToDraw.getWidth()*Game.SCALE-width)/2, 
				y - (imageToDraw.getHeight()*Game.SCALE-height), 
				imageToDraw.getWidth() * Game.SCALE, 
				imageToDraw.getHeight() * Game.SCALE, 
				null);
	}
	
	
	//UPDATE
	public void update() {
		invincibleTimer();
		playerDead();
		setVel();
		
		if(falling) velY += gravity;
		
		x += velX;
		y += velY;
		
		collide();
		
		currAnimation.runAnimation();
	}
	private void setVel() {
		if(keyHandler.getW() && !jumping) {
			velY = -7;
			jumping = true;
			falling = true;
		}
		if(keyHandler.getA()) {
			velX = -4;
			if(!infected) currAnimation = runLeft;
			else currAnimation = infectedRunLeft;
			facingRight = false;
		}
		if(keyHandler.getS()) velY = 4;
		if(keyHandler.getD()) {
			velX = 4;
			if(!infected) currAnimation = runRight;
			else currAnimation = infectedRunRight;
			facingRight = true;
		}
		if(!keyHandler.getW() && !keyHandler.getS() && !falling) velY = 0;
		if(!keyHandler.getA() && !keyHandler.getD()) {
			velX = 0;
			if(!infected) {
				if(facingRight) currAnimation = idleRight;
				else currAnimation = idleLeft;
			} else {
				if(facingRight) currAnimation = infectedIdleRight;
				else currAnimation = infectedIdleLeft;
			}
		}
	}
	private void invincibleTimer() {
		if(invincibleCounter > 0) invincibleCounter--;
		if(invincibleCounter <= 0) {
			infected = false;
			invincible = false;
		}
	}
	
	
	//COLLISION
	public void collide() {
		for(GameEntity x : handler.getEntities()) {
			if(x.getEntityID() == BLOCK) {
				if(this.getTopBound().intersects(x.getBottomBound())) {
					velY = 0;
					this.y = x.getY() + x.getHeight() + 1;
				}if(this.getBottomBound().intersects(x.getTopBound())) {
					velY = 0;
					this.jumping = false;
					this.y = x.getY() - this.getHeight();
				}
				
				if(this.getLeftBound().intersects(x.getRightBound())) {
					velX = 0;
					this.x = x.getX() + x.getWidth() + 1;
				}if(this.getRightBound().intersects(x.getLeftBound())) {
					velX = 0;
					this.x = x.getX() - this.getWidth();
				}
			}
			
			if(x.getEntityID() == OBSTACLE || x.getEntityID() == ENEMY) {
				if(this.getTopBound().intersects(x.getBottomBound()) ||
					this.getBottomBound().intersects(x.getTopBound()) ||
					this.getLeftBound().intersects(x.getRightBound()) ||
					this.getRightBound().intersects(x.getLeftBound())) {
					
					if(!invincible) {
						this.health--;
						infected = true;
						invincible = true;
						invincibleCounter = 20;
					}
				}
			}
			
			if(x.getEntityID() == FLAG) {
				if(this.getTopBound().intersects(x.getBottomBound()) ||
					this.getBottomBound().intersects(x.getTopBound()) ||
					this.getLeftBound().intersects(x.getRightBound()) ||
					this.getRightBound().intersects(x.getLeftBound())) {
					velX = 0;
					velY = 0;
					this.falling = false;
					
					gsm.setCurrentState(GameStateManager.LEVELCOMPLETESTATE);
				}
			}
		}
	}
	
	
	
	public void playerDead() {
		if(outOfGame() || health <= 0) {
			GameOverProtocol();
		}
	}
	private boolean outOfGame() {
		if(y > 5000) {
			return true;
		}return false;
	}
	private void GameOverProtocol() {
		gsm.setCurrentState(GameStateManager.GAMEOVERSTATE);
	}
}