package com.javatea.gamepaidi.entities;

import java.util.ArrayList;

public class Handler {
	private ArrayList<GameEntity> entities = new ArrayList<GameEntity>();
	
	public void add(GameEntity newEntity) {
		entities.add(newEntity);
	}
	
	public void remove(int index) {
		entities.remove(index);
	}
	
	public void clear() {
		entities.clear();
	}
	
	public ArrayList<GameEntity> getEntities(){
		return entities;
	}

	public void update() {
		for(GameEntity x : entities) {
			x.update();
		}
	}
}
