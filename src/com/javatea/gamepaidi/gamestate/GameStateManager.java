package com.javatea.gamepaidi.gamestate;

import java.awt.Graphics;

import com.javatea.gamepaidi.utils.ImageLoader;
import com.javatea.gamepaidi.utils.KeyHandler;
import com.javatea.gamepaidi.utils.Texture;

public class GameStateManager {
	
	public final static int MENUSTATE = 0;
	public final static int LEVELMENUSTATE = 1;
	public final static int PLAYSTATE = 2;
	public final static int GAMEOVERSTATE = 3;
	public final static int LEVELCOMPLETESTATE = 4;
	
	GameState[] gameStates = new GameState[5];
	private int currentState = MENUSTATE;
	private ImageLoader loader = new ImageLoader();
	private Texture textures = new Texture(loader);
	
	public GameStateManager(KeyHandler keyHandler) {
		gameStates[MENUSTATE] = new MenuState(keyHandler, this, textures);
		gameStates[LEVELMENUSTATE] = new LevelMenuState(keyHandler, this, textures);
		gameStates[PLAYSTATE] = new PlayState(keyHandler, this, loader, textures);
		gameStates[GAMEOVERSTATE] = new GameOverState(keyHandler, this, loader, textures);
		gameStates[LEVELCOMPLETESTATE] = new LevelCompleteState(keyHandler, this, textures);
	}

	
	//GETTERS-N-SETTERS
	public void setCurrentState(int currentMenu) {currentState = currentMenu;}
	public PlayState getPlayState() {return (PlayState) gameStates[PLAYSTATE];}
	
	
	//UPDATE
	public void update() {
		gameStates[currentState].update();
	}
	
	
	//DRAW
	public void draw(Graphics g) {
		gameStates[currentState].draw(g);
	}
}
