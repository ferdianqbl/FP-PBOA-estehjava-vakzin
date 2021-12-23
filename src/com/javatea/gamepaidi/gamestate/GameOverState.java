package com.javatea.gamepaidi.gamestate;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.javatea.gamepaidi.main.Game;
import com.javatea.gamepaidi.utils.Animation;
import com.javatea.gamepaidi.utils.ImageLoader;
import com.javatea.gamepaidi.utils.KeyHandler;
import com.javatea.gamepaidi.utils.Texture;

public class GameOverState extends GameState{
	private Texture textures;
	private ArrayList<BufferedImage> backGroundImages;
	private Animation backGroundAnimation;
	
	private MenuButton button;
	private int currSelection = 0;
	
	public GameOverState(KeyHandler keyHandler, GameStateManager gameStateManager, ImageLoader loader, Texture textures) {
		this.keyHandler = keyHandler;
		this.gameStateManager = gameStateManager;
		this.textures = textures;
		
		BufferedImage idleButtonImage = this.textures.getButtonTextures().get(7);
		BufferedImage activeButtonImage = this.textures.getButtonTextures().get(8);
		
		int x = Game.WIDTH - activeButtonImage.getWidth() * Game.SCALE + 20;
		int y = Game.HEIGHT - activeButtonImage.getHeight() * Game.SCALE;
		
		this.button = new MenuButton(x, y, idleButtonImage, activeButtonImage, 1, null, MenuButton.UNLOCKED, null);
		
		this.backGroundImages = this.textures.getLevelCompleteTextures();
		this.backGroundAnimation = new Animation(8, 6, 6, backGroundImages);
	}
	
	
	//UPDATE
	public void update() {
		if(backGroundAnimation.getCurrFrameIndex() != 5) backGroundAnimation.runAnimation();
		
		else {
			if(currSelection != 1) {
				if(keyHandler.getW()) {
					keyHandler.setW(false);
					currSelection = 1;
				}
				if(keyHandler.getA()) {
					keyHandler.setA(false);
					currSelection = 1;
				}
				if(keyHandler.getS()) {
					keyHandler.setS(false);
					currSelection = 1;
				}
				if(keyHandler.getD()) {
					keyHandler.setD(false);
					currSelection = 1;
				}
				if((keyHandler.getSpace() || keyHandler.getEnter())) {
					keyHandler.setEnter(false);
					keyHandler.setSpace(false);
					currSelection = 1;
				}
			}else {
				if((keyHandler.getSpace() || keyHandler.getEnter())) {
					keyHandler.setEnter(false);
					keyHandler.setSpace(false);
					gameStateManager.setCurrentState(GameStateManager.MENUSTATE);
				}
			}
		}
	}

	
	//DRAW
	public void draw(Graphics g) {
		g.drawImage(backGroundAnimation.getCurrFrame(), 0, 0, Game.WIDTH, Game.HEIGHT, null);
		button.draw(g, currSelection);
	}

}
