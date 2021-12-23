package com.javatea.gamepaidi.gamestate;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.javatea.gamepaidi.main.Game;
import com.javatea.gamepaidi.utils.Animation;
import com.javatea.gamepaidi.utils.KeyHandler;
import com.javatea.gamepaidi.utils.Texture;

public class LevelCompleteState extends GameState{
	
	private ArrayList<BufferedImage> levelCompleteImages;
	private Animation levelCompleteAnimation;
	private int timer;
	
	public LevelCompleteState(KeyHandler keyHandler, GameStateManager gsm, Texture textures) {
		this.keyHandler = keyHandler;
		this.gameStateManager = gsm;
		this.textures = textures;
		
		this.levelCompleteImages = textures.getLevelCompleteTextures();
		this.levelCompleteAnimation = new Animation(8, 0, 6, this.levelCompleteImages);
	}
	
	
	//UPDATE
	public void update() {
		if(levelCompleteAnimation.getCurrFrameIndex() == 5) {
			if(timer == 1) {
				timer = 0;
				levelCompleteAnimation.setCurrFrameIndex(0);
				gameStateManager.getPlayState().levelUp();
				gameStateManager.setCurrentState(GameStateManager.PLAYSTATE);
			}
			animationTimer();
		}else levelCompleteAnimation.runAnimation();
	}

	
	//DRAW
	public void draw(Graphics g) {
		g.drawImage(levelCompleteAnimation.getCurrFrame(),
				0,
				0,
				Game.WIDTH,
				Game.HEIGHT,
				null);
	}
	
	
	private void animationTimer() {
		if(timer == 0) timer = 50;
		else timer--;
	}
}
