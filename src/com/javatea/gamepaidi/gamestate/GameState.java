package com.javatea.gamepaidi.gamestate;

import java.awt.Graphics;

import com.javatea.gamepaidi.utils.KeyHandler;
import com.javatea.gamepaidi.utils.Texture;

public abstract class GameState {
	protected KeyHandler keyHandler;
	protected GameStateManager gameStateManager;
	protected Texture textures;
	
	public final static int MAXLEVEL = 5;
	
	abstract public void update();
	abstract public void draw(Graphics g);
}
