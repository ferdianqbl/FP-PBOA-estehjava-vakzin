package com.javatea.gamepaidi.utils;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageLoader {
	
	public BufferedImage load(String path) {
		BufferedImage image;
		try {
			image = ImageIO.read(getClass().getResourceAsStream(path));
			return image;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
