package com.diquebutte.pacmangband;

import java.awt.Color;
import java.util.List;

import asciiPanel.AsciiPanel;

public class CreatureFactory {
	private World world;
	
	public CreatureFactory(World world) {
		this.world = world;
	}
	
	public Creature newPlayer(List<String> messages) {
		Creature player = new Creature(world, '@', AsciiPanel.brightYellow, 100, 20, 5, "Pacman");
		world.addAtEmptyLocation(player);
		new PlayerAi(player, messages);
		return player;
	}
	
	public Creature newFungus() {
		Creature fungus = new Creature(world, 'f', AsciiPanel.brightGreen, 10, 0, 0, "Fungus");
		world.addAtEmptyLocation(fungus);
		new FungusAi(fungus, this);
		return fungus;
	}
	
	public Creature newGhost() {
		Creature ghost = new Creature(world, 'G', Color.lightGray, 10, 0, 0, "Ghost");
		world.addAtEmptyLocation(ghost);
		new GhostAi(ghost);
		return ghost;
	}
}
