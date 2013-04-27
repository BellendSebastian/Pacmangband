package com.diquebutte.pacmangband;

public class GhostAi extends CreatureAi {

	public GhostAi(Creature creature) {
		super(creature);
	}
	
	public void onUpdate() {
		wander();
	}

}
