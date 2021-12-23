package com.javatea.gamepaidi.gamestate;

import java.awt.Graphics;

import com.javatea.gamepaidi.entities.Handler;
import com.javatea.gamepaidi.utils.ImageLoader;
import com.javatea.gamepaidi.utils.KeyHandler;
import com.javatea.gamepaidi.utils.LoadSave;
import com.javatea.gamepaidi.utils.Texture;

public class PlayState extends GameState {
	Level level;
	ImageLoader loader;
	Texture textures;
	private int currentLevel = 1;
	private final int maxLevel = MAXLEVEL;
	private String mapLayoutPath;
	
	private int currLevel;
	
	public PlayState(KeyHandler keyHandler, GameStateManager gameStateManager, ImageLoader loader, Texture textures) {
		this.keyHandler = keyHandler;
		this.gameStateManager = gameStateManager;
		this.loader = loader;
		this.textures = textures;
		this.currLevel = LoadSave.loadLevelProgress();
	}

	public void levelInit() {
		setMapLayout();
		level = new Level(loader, keyHandler, new Handler(), gameStateManager, mapLayoutPath, textures, "/VakzinGAMEBG.png");
	}

	
	//UPDATE
	public void update() {
		System.out.println(currentLevel);
		level.update();

	}


	//DRAW
	public void draw(Graphics g) {
		level.draw(g);
	}

	public void levelUp() {
		currentLevel++;
		if(currentLevel > currLevel) LoadSave.saveLevelProgress(currentLevel);
		if (currentLevel > maxLevel) System.exit(0); // BELUM SELESAI
		levelInit();
	}

	private void setMapLayout() {
		switch(currentLevel) {
			case 1:
				this.mapLayoutPath = "/map1.png";
				break;
			case 2:
				this.mapLayoutPath = "/map2.png";
				break;
			case 3:
				this.mapLayoutPath = "/map3.png";
				break;
			case 4:
				this.mapLayoutPath = "/map4.png";
				break;
			case 5:
				this.mapLayoutPath = "/map5.png";
				break;
		}
	}
	
	public void setCurrentLevel(int level) {
		this.currentLevel = level;
	}
}
