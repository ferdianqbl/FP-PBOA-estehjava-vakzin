package com.javatea.gamepaidi.utils;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyHandler extends KeyAdapter{
	
	private boolean W = false,
			A = false,
			S = false,
			D = false,
			Space = false,
			Enter = false;
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		if(key == KeyEvent.VK_W) W = true;
		if(key == KeyEvent.VK_A) A = true;
		if(key == KeyEvent.VK_S) S = true;
		if(key == KeyEvent.VK_D) D = true;
		if(key == KeyEvent.VK_SPACE) Space = true;
		if(key == KeyEvent.VK_ENTER) Enter = true;
	}
	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		if(key == KeyEvent.VK_W) W = false;
		if(key == KeyEvent.VK_A) A = false;
		if(key == KeyEvent.VK_S) S = false;
		if(key == KeyEvent.VK_D) D = false;
		if(key == KeyEvent.VK_SPACE) Space = false;
		if(key == KeyEvent.VK_ENTER) Enter = false;
	}
	
	public boolean getW() {return W;}
	public boolean getA() {return A;}
	public boolean getS() {return S;}
	public boolean getD() {return D;}
	public boolean getSpace() {return Space;}
	public boolean getEnter() {return Enter;}

	public void setW(boolean b) {this.W = b;}
	public void setA(boolean b) {this.A = b;}
	public void setS(boolean b) {this.S = b;}
	public void setD(boolean b) {this.D = b;}
	public void setSpace(boolean b) {this.Space = b;}
	public void setEnter(boolean b) {this.Enter = b;}
}
