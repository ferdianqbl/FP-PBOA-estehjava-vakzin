package com.javatea.gamepaidi.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class LoadSave {
	
	
	//NGGAWE FILE, NEK WES ONO YOWES
	public static void createFile() {
		File gameDataFile = new File("GameData.txt");
		
		try {
			gameDataFile.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	} 
	
	
	//TULIS NG FILE LEVEL TERAKHIR
	public static void saveLevelProgress(int level) {
		File gameDataFile = new File("GameData.txt");
		
		try {
			PrintWriter write = new PrintWriter(gameDataFile);
			write.print(level);
			write.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	
	//NGE-LOAD LEVEL E USER WES TEKAN PIRO
	public static int loadLevelProgress() {
		try {
			File gameDataFile = new File("GameData.txt");
			Scanner fileReader = new Scanner(gameDataFile);
			
			try {
				int lastLevel = fileReader.nextInt();
				fileReader.close();
				System.out.println("lastLevel was = " + lastLevel);
				return lastLevel;
			}catch(Exception e) {
				saveLevelProgress(1);
				return 1;
			}
		} catch (FileNotFoundException e) {
			createFile();
			saveLevelProgress(1);
			return 1;
		}
	}
}
