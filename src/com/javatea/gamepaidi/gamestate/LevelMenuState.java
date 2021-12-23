package com.javatea.gamepaidi.gamestate;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.javatea.gamepaidi.main.Game;
import com.javatea.gamepaidi.utils.KeyHandler;
import com.javatea.gamepaidi.utils.LoadSave;
import com.javatea.gamepaidi.utils.Texture;

public class LevelMenuState extends GameState{
	private int currSelection;
	private int maxLevel = MAXLEVEL;
	private int currLevel;
	private int rows = 3, cols = 3;
	private int distance = 25;
	
	private KeyHandler keyHandler;
	private ArrayList<MenuButton> buttons;
	private GameStateManager gameStateManager;

	private Texture textures;
	private BufferedImage titleImage;
	
	public LevelMenuState(KeyHandler keyHandler, GameStateManager gameStateManager, Texture textures) {
		this.keyHandler = keyHandler;
		this.gameStateManager = gameStateManager;
		
		this.currSelection = 1;
		this.currLevel = LoadSave.loadLevelProgress();
		
		this.textures = textures;
		this.titleImage = textures.getTitleTextures().get(1);
		
		BufferedImage idleButtonImage = this.textures.getButtonTextures().get(4);
		BufferedImage activeButtonImage = this.textures.getButtonTextures().get(5);
		BufferedImage lockedButtonImage = this.textures.getButtonTextures().get(6);
		
		this.buttons = new ArrayList<MenuButton>();
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < cols; j++) {
				System.out.println(3*i+j+1 + ">" + currLevel);
				int status = (3*i+j+1 > currLevel || 3*i+j+1 > maxLevel) ? MenuButton.LOCKED : MenuButton.UNLOCKED;
				System.out.println(status);
				buttons.add(new MenuButton(
						Game.WIDTH/2 - ((cols * activeButtonImage.getWidth() * Game.SCALE + (cols-1) * distance))/2 + j * (distance + activeButtonImage.getWidth() * Game.SCALE), 
						Game.HEIGHT/2 - (rows * activeButtonImage.getHeight()*Game.SCALE + (rows-1) * distance)/2 + i*(distance + activeButtonImage.getHeight() * Game.SCALE) + distance,
						idleButtonImage,
						activeButtonImage,
						3*i+j+1,
						textures.getNumberTextures(),
						status,
						lockedButtonImage));
			}
		}
	}
	
	
	//UPDATE
	public void update() {
		if(keyHandler.getW() && currSelection >= 4) {
			keyHandler.setW(false);
			currSelection -= 3;
		}
		if(keyHandler.getA() && currSelection > 1) {
			keyHandler.setA(false);
			currSelection--;
		}
		if(keyHandler.getS() && currSelection <= maxLevel - 3) {
			keyHandler.setS(false);
			currSelection += 3;
		}
		if(keyHandler.getD() && currSelection < maxLevel) {
			keyHandler.setD(false);
			currSelection++;
		}
		if((keyHandler.getSpace() || keyHandler.getEnter())  && currSelection <= currLevel) {
			keyHandler.setEnter(false);
			keyHandler.setSpace(false);
			
			gameStateManager.getPlayState().setCurrentLevel(currSelection);
			gameStateManager.getPlayState().levelInit();
			gameStateManager.setCurrentState(GameStateManager.PLAYSTATE);
		}
	}

	
	//DRAW
	public void draw(Graphics g) {
		g.setColor(new Color(23, 32, 56));
		g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
		
		g.drawImage(titleImage, Game.WIDTH/2 - titleImage.getWidth()*Game.SCALE/2, 15, titleImage.getWidth() * Game.SCALE, titleImage.getHeight() * Game.SCALE, null);
		
		for(MenuButton button : buttons) {
			button.draw(g, currSelection);
		}
	}
	
}
