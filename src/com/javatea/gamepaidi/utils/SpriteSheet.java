package com.javatea.gamepaidi.utils;

import java.awt.image.BufferedImage;

public class SpriteSheet {
	BufferedImage sheet;
	
	public SpriteSheet(ImageLoader imageLoader, String path) {
		sheet = imageLoader.load(path);
	}
	
	public BufferedImage getSubImage(int row, int col, int width, int height) {
		BufferedImage image = sheet.getSubimage(col*width, row*height, width, height);
		return image;
	}
	
	public BufferedImage getSubImage(int row, int col, int width, int height, int xOffset, int yOffset) {
		BufferedImage image = sheet.getSubimage(col*width + xOffset, row*height + yOffset, width, height);
		return image;
	}
}
