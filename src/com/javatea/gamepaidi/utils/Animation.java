package com.javatea.gamepaidi.utils;

import com.javatea.gamepaidi.main.Game;

import java.awt.image.BufferedImage;

import java.util.ArrayList;

public class Animation {
	private ArrayList<BufferedImage> animationSet;
	private int count;
	private int currCount;
	private int frames;
	private int currFrame;
	
	public Animation(int frameDuration, int startIndex, int length, ArrayList<BufferedImage> animationSet) {
		this.count = frameDuration * Game.FPS/60;
		this.currCount = 0;
		this.frames = length;
		this.currFrame = 0;
		this.animationSet = new ArrayList<BufferedImage>();
		for(int i = startIndex; i < startIndex + length; i++) {
			this.animationSet.add(animationSet.get(i));
		}
	}
	
	public void runAnimation() {
		currCount++;
		if(currCount > count) {
			currCount = 0;
			currFrame++;
		}if(currFrame >= frames) currFrame = 0;
	}
	
	public ArrayList<BufferedImage> getAnimationSet() {return animationSet;}
	public int getCurrFrameIndex() {return currFrame;}
	public void setCurrFrameIndex(int i) {currFrame = i;}
	public BufferedImage getCurrFrame() {return animationSet.get(currFrame);}
}
