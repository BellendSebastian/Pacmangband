package com.diquebutte.pacmangband;

public class GhostAi extends CreatureAi {
	private Creature player;

	public GhostAi(Creature creature, Creature player) {
		super(creature);
		this.player = player;
	}
	
	public void onUpdate() {
		if (Math.random() < 0.2) {
			return;
		}
		
		if (creature.canSee(player.x, player.y, player.z)) {
			hunt(player);
		} else {
			wander();
		}
	}

}
