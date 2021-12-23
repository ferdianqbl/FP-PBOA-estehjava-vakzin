package com.javatea.gamepaidi.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.javatea.gamepaidi.main.Game;

public class Heart {
	private int x, y;
	private ArrayList<BufferedImage> textures;
	private int id;
	
	public Heart(int x, int y, ArrayList<BufferedImage> heartImg, int id) {
		this.x = x;
		this.y = y;
		this.textures = heartImg;
		this.id = id;
	}
	
	public void draw(Graphics g, int healthAmount) {
		if(healthAmount >= id) {
			g.drawImage(textures.get(1), x, y, 
					textures.get(1).getWidth() * Game.SCALE, 
					textures.get(1).getHeight() * Game.SCALE,
					null);
		}else {
			g.drawImage(textures.get(0), x, y, 
					textures.get(0).getWidth() * Game.SCALE, 
					textures.get(0).getHeight() * Game.SCALE,
					null);
		}
	}
}
