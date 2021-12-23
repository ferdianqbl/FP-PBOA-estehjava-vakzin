package com.javatea.gamepaidi.gamestate;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.javatea.gamepaidi.main.Game;

public class MenuButton {
	private int x;
	private int y;
	private BufferedImage active;
	private BufferedImage idle;
	private int id;
	
	public final static int LOCKED = 0;
	public final static int UNLOCKED = 1;
	
	private int charMargin = 5;
	private ArrayList<Character> chars;
	private int[] xCoordinates;
	private int[] yCoordinates;
	
	private int status;
	private ArrayList<BufferedImage> textImages;
	private BufferedImage lockedImage;
	
	public MenuButton(int x, int y, BufferedImage idle, BufferedImage active, int id, ArrayList<BufferedImage> textImages, int status, BufferedImage lockedImage){
		this.x = x;
		this.y = y;
		
		this.active = active;
		this.idle = idle;
		
		this.id = id;
		this.status = status;
		
		if(textImages != null) {
			this.textImages = textImages;
			chars = new ArrayList<Character>();
			
			extractChars();
			determineX();
			determineY();
		}
		
		if(status == LOCKED) {
			setLockedImage(lockedImage);
		}
	}
	
	public MenuButton(int x, int y, int id){
		this.x = x;
		this.y = y;
		
		this.id = id;
	}
	
	public void draw(Graphics g, int currSelection){
		
		if(status == LOCKED && lockedImage != null) {
			g.drawImage(lockedImage, x, y, lockedImage.getWidth() * Game.SCALE, lockedImage.getHeight() * Game.SCALE, null);
		}
		else {
			if(currSelection == this.id) {
				g.drawImage(active, x, y, active.getWidth() * Game.SCALE, active.getHeight() * Game.SCALE, null);
			}
			else g.drawImage(idle, x, y, idle.getWidth() * Game.SCALE, idle.getHeight() * Game.SCALE, null);
			
			if(this.textImages != null) {
				for(int i = 0; i < chars.size(); i++) {
					System.out.println(chars.get(i) + "int value = " + Character.getNumericValue(chars.get(i)));
					g.drawImage(textImages.get(Character.getNumericValue(chars.get(i))), xCoordinates[i], yCoordinates[i], textImages.get(Character.getNumericValue(chars.get(i))).getWidth() * Game.SCALE, textImages.get(Character.getNumericValue(chars.get(i))).getHeight() * Game.SCALE, null);
				}
			}
		}
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getId() {
		return id;
	}
	
	public BufferedImage getImage() {
		return active;
	}
	
	private void extractChars() {
		Integer temp = id;
		String tempS = Integer.toString(temp);
		for(int i = 0; i < tempS.length(); i++) {
			chars.add(tempS.charAt(i));
		}
		xCoordinates = new int[chars.size()];
		yCoordinates = new int[chars.size()];
	}
	
	private void determineX() {
		int charWidth = textImages.get(0).getWidth() ;
		int totalWidth = (charWidth + charMargin) * chars.size() - charMargin;
		int start = active.getWidth() * Game.SCALE / 2 - totalWidth/2;
		for(int i = 0; i < chars.size(); i++) {
			xCoordinates[i] = start + (charMargin + charWidth) * i + x - 5;
		}
	}
	
	private void determineY() {
		int charHeight = textImages.get(0).getWidth() * Game.SCALE;
		int buttonHeight = active.getHeight() * Game.SCALE;
		int start = buttonHeight/2 - charHeight/2;
		for(int i = 0; i < chars.size(); i++) {
			yCoordinates[i] = start + y - 5;
		}
	}
	
	public void setLockedImage(BufferedImage lockedImage) {
		this.lockedImage = lockedImage;
	}
}
