package com.javatea.gamepaidi.gamestate;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.javatea.gamepaidi.main.Game;
import com.javatea.gamepaidi.utils.Animation;
import com.javatea.gamepaidi.utils.KeyHandler;
import com.javatea.gamepaidi.utils.Texture;

public class MenuState extends GameState{
	//BUTTON CODEs
	public final static int PLAY = 1,
			EXIT = 2;
	
	private int currentMenu = 0;
	private int menus = 2;
	
	private Texture textures;
	private ArrayList<BufferedImage> buttonTextures;
	private ArrayList<BufferedImage> menuTextures;
	
	private BufferedImage titleImage;
	
	private Animation menuAnimation;
	private Animation menuShineAnimation;
	private int playShineCounter = 0;

	private ArrayList<MenuButton> buttons = new ArrayList<MenuButton>();
	
	public MenuState(KeyHandler keyHandler, GameStateManager gameStateManager, Texture textures) {
		this.keyHandler = keyHandler;
		this.gameStateManager = gameStateManager;
		this.textures = textures;
		
		this.titleImage = this.textures.getTitleTextures().get(0);
		this.menuTextures = this.textures.getMenuTextures();
		this.buttonTextures = this.textures.getButtonTextures();
		
		this.menuAnimation = new Animation(5, 0, 4, menuTextures);
		this.menuShineAnimation = new Animation(5, 4, 4, menuTextures);
		
		//assuming buttons are uniform size and don't change
		int buttonWidth = buttonTextures.get(0).getWidth() * Game.SCALE;
		int margin = 10;
		int x = Game.WIDTH/2 - (buttonWidth*menus + margin * (menus-1))/2;
		
		buttons.add(new MenuButton(x, 
				Game.HEIGHT - 50, 
				buttonTextures.get(0), 
				buttonTextures.get(1), 
				PLAY, 
				null, 
				MenuButton.UNLOCKED, 
				null));
		buttons.add(new MenuButton(x + (buttonWidth + margin), 
				Game.HEIGHT - 50, 
				buttonTextures.get(2), 
				buttonTextures.get(3), 
				EXIT, 
				null, 
				MenuButton.UNLOCKED, 
				null));
	}
	
	
	//UPDATE
	public void update() {
		// TODO Auto-generated method stub
		if((keyHandler.getW() || keyHandler.getS() || keyHandler.getA() || keyHandler.getD()) && (currentMenu < 1 || currentMenu > menus)) currentMenu = 1;
		if((keyHandler.getW() || keyHandler.getA()) && currentMenu != 1) { 
			currentMenu--;
			keyHandler.setA(false);
			keyHandler.setW(false);
		}
		if((keyHandler.getS() || keyHandler.getD()) && currentMenu != menus) {
			currentMenu++;
			keyHandler.setD(false);
			keyHandler.setS(false);
		}if(keyHandler.getSpace() || keyHandler.getEnter()) {
			keyHandler.setEnter(false);
			keyHandler.setSpace(false);
			
			System.out.println("SPACE/ENTER WAS PRESSED IN MENU");
			
			if(currentMenu == EXIT) {
				System.exit(0);
			}
			else if(currentMenu == PLAY) gameStateManager.setCurrentState(GameStateManager.LEVELMENUSTATE);
		}
		
		playShineCounter++;
		if(playShineCounter > 120) playShineCounter = 0;
		menuAnimation.runAnimation();
		menuShineAnimation.runAnimation();
	}


	//DRAW
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		if(playShineCounter >= 100 && playShineCounter <= 120) {
			g.drawImage(menuShineAnimation.getCurrFrame(), 0, 0, Game.WIDTH, Game.HEIGHT, null);
		}
		else g.drawImage(menuAnimation.getCurrFrame(), 0, 0, Game.WIDTH, Game.HEIGHT, null);
		
		g.drawImage(titleImage, 
				Game.WIDTH/2 - titleImage.getWidth(), 
				10, 
				titleImage.getWidth()*Game.SCALE, 
				titleImage.getHeight()*Game.SCALE, 
				null);
		
		for(MenuButton x : buttons) {
			x.draw(g, currentMenu);
		}
	}
	
	
}
