package com.javatea.gamepaidi.utils;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Texture {
	private SpriteSheet blockSheet;
	private SpriteSheet playerSheet;
	private SpriteSheet virusSheet;
	private SpriteSheet flagSheet;
	
	private SpriteSheet menuSheet;
	private SpriteSheet levelCompleteSheet;
	
	private SpriteSheet buttonSheet;
	private SpriteSheet titlesSheet;
	private SpriteSheet numbersSheet;
	
	private SpriteSheet heartSheet;
	
	private ArrayList<BufferedImage> blockTextures = new ArrayList<BufferedImage>();  
	private ArrayList<BufferedImage> playerTextures = new ArrayList<BufferedImage>();
	private ArrayList<BufferedImage> obstacleTextures = new ArrayList<BufferedImage>();
	private ArrayList<BufferedImage> virusTextures = new ArrayList<BufferedImage>();
	private ArrayList<BufferedImage> flagTextures = new ArrayList<BufferedImage>();
	
	private ArrayList<BufferedImage> menuTextures = new ArrayList<BufferedImage>();
	private ArrayList<BufferedImage> levelCompleteTextures = new ArrayList<BufferedImage>();
	
	private ArrayList<BufferedImage> titleTextures = new ArrayList<BufferedImage>();
	private ArrayList<BufferedImage> buttonTextures = new ArrayList<BufferedImage>();
	private ArrayList<BufferedImage> numberTextures = new ArrayList<BufferedImage>();
	
	private ArrayList<BufferedImage> heartTextures = new ArrayList<BufferedImage>();
	
	ImageLoader loader;
	
	public Texture(ImageLoader loader) {
		this.loader = loader;
		blockSheet = new SpriteSheet(loader, "/VakzinTILES.png");
		flagSheet = new SpriteSheet(loader, "/VakzinPORTAL.png");
		
		playerSheet = new SpriteSheet(loader, "/VakzinPLAYER.png");
		virusSheet = new SpriteSheet(loader, "/VakzinVIRUS.png");
		
		menuSheet = new SpriteSheet(loader, "/VakzinTITLESCREEN.png");
		levelCompleteSheet = new SpriteSheet(loader, "/VakzinLEVELCOMPLETE.png");
		
		buttonSheet = new SpriteSheet(loader, "/VakzinTITLEBUTTONS.png");
		titlesSheet = new SpriteSheet(loader, "/VakzinTITLE.png");
		numbersSheet = new SpriteSheet(loader, "/VakzinNUMBERFONT.png");
		heartSheet = new SpriteSheet(loader, "/VakzinHEARTS.png");
		
		init();
	}
	
	private void init() {
		//BLOCKS
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				blockTextures.add(blockSheet.getSubImage(i, j, 16, 16));
			}
		}
		
		for(int i = 0; i < 3; i++) {
			blockTextures.add(blockSheet.getSubImage(i, 3, 16, 16));
		}for(int i = 0; i < 4; i++) {
			blockTextures.add(blockSheet.getSubImage(3, i, 16, 16));
		}

		//OBSTACLE
		for(int i = 0; i < 4; i++) {
			obstacleTextures.add(blockSheet.getSubImage(4, i, 16, 16));
		}
		
		//FLAG
		for(int i = 0; i < 6; i++) {
			flagTextures.add(flagSheet.getSubImage(0, i, 16, 32));
		}
		
		//PLAYER
		for(int i = 0; i < 8; i++) {
			playerTextures.add(playerSheet.getSubImage(0, i, 16, 32));
		}for(int i = 0; i < 8; i++) {
			playerTextures.add(playerSheet.getSubImage(1, i, 32, 32));
		}for(int i = 0; i < 8; i++) {
			playerTextures.add(playerSheet.getSubImage(2, i, 32, 32));
		}for(int i = 0; i < 8; i++) {
			playerTextures.add(playerSheet.getSubImage(3, i, 16, 32));
		}for(int i = 0; i < 8; i++) {
			playerTextures.add(playerSheet.getSubImage(4, i, 32, 32));
		}for(int i = 0; i < 8; i++) {
			playerTextures.add(playerSheet.getSubImage(5, i, 32, 32));
		}
		
		//VIRUS
		for(int i = 0; i < 8; i++) {
			virusTextures.add(virusSheet.getSubImage(0, i, 16, 32));
		}for(int i = 0; i < 8; i++) {
			virusTextures.add(virusSheet.getSubImage(1, i, 32, 32));
		}for(int i = 0; i < 8; i++) {
			virusTextures.add(virusSheet.getSubImage(2, i, 32, 32));
		}
		
		//MENU
		for(int i = 0; i < 2; i++) {
			menuTextures.add(menuSheet.getSubImage(0, i, 320, 180));
			menuTextures.add(menuSheet.getSubImage(1, i, 320, 180));
			menuTextures.add(menuSheet.getSubImage(2, i, 320, 180));
			menuTextures.add(menuSheet.getSubImage(3, i, 320, 180));
		}
		
		//LEVEL COMPLETE
		for(int i = 0; i < 4; i++) {
			levelCompleteTextures.add(levelCompleteSheet.getSubImage(i, 0, 640, 360));
			levelCompleteTextures.add(levelCompleteSheet.getSubImage(i, 1, 640, 360));
			levelCompleteTextures.add(levelCompleteSheet.getSubImage(i, 2, 640, 360));
		}
		
		//Button
		for(int i = 0; i < 4; i++) {
			buttonTextures.add(buttonSheet.getSubImage(i, 0, 44, 17));
		}
		for(int i = 0; i < 2; i++) {
			buttonTextures.add(buttonSheet.getSubImage(i, 0, 75, 30, 0, 4*17));
		}buttonTextures.add(buttonSheet.getSubImage(0, 0, 75, 30, 44, 0));
		for(int i = 0; i < 2; i++) {
			buttonTextures.add(buttonSheet.getSubImage(i, 0, 117, 79, 0, 128));
		}
		
		//TITLE
		titleTextures.add(titlesSheet.getSubImage(0, 0, 130, 48));
		titleTextures.add(titlesSheet.getSubImage(0, 0, 100, 29, 0, 48));
		
		//NUMBERS
		for(int i = 0; i < 2; i++) {
			for(int j = 0; j < 5; j++) {
				numberTextures.add(numbersSheet.getSubImage(i, j, 9, 14));
			}
		}
		
		//HEARTS
		for(int i = 0; i < 2; i++) {
			heartTextures.add(heartSheet.getSubImage(0, i, 16, 16));
		}
	}
	
	public ArrayList<BufferedImage> getBlockTextures() {return blockTextures;}
	public ArrayList<BufferedImage> getObstacleTextures() {return obstacleTextures;}
	public ArrayList<BufferedImage> getFlagTextures() {return flagTextures;}
	
	public ArrayList<BufferedImage> getPlayerTextures() {return playerTextures;}
	public ArrayList<BufferedImage> getVirusTextures() {return virusTextures;}
	
	public ArrayList<BufferedImage> getButtonTextures() {return buttonTextures;}
	public ArrayList<BufferedImage> getMenuTextures() {return menuTextures;}
	public ArrayList<BufferedImage> getLevelCompleteTextures() {return levelCompleteTextures;}
	
	public ArrayList<BufferedImage> getTitleTextures() {return titleTextures;}
	public ArrayList<BufferedImage> getNumberTextures() {return numberTextures;}
	
	public ArrayList<BufferedImage> getHeartTextures() {return heartTextures;}
}
