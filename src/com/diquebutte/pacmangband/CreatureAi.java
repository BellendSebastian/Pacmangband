package com.diquebutte.pacmangband;

public class CreatureAi {
	protected Creature creature;
	
	public CreatureAi(Creature creature) {
		this.creature = creature;
		this.creature.setCreatureAi(this);
	}
	
	public void onUpdate() {}
	
	public void onEnter(int x, int y, Tile tile) {}
	
	public void onNotify(String message) {}
}
