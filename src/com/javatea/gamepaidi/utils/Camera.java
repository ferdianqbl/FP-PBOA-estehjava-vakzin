package com.javatea.gamepaidi.utils;

import com.javatea.gamepaidi.entities.GameEntity;
import com.javatea.gamepaidi.main.Game;

public class Camera {
	
	private int x;
	private int y;
	
	public Camera(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {return x;}
	public void setX(int x) {this.x = x;}
	public int getY() {return y;}
	public void setY(int y) {this.y = y;}
	
	public void update(GameEntity player) {
		if(player.getX() >= Game.WIDTH/2)  this.x = -player.getX() + Game.WIDTH/2;
		this.y = -player.getY() + Game.HEIGHT/2;
	}
}
