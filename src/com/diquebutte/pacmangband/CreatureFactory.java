package com.diquebutte.pacmangband;

import java.awt.Color;
import java.util.List;

import asciiPanel.AsciiPanel;

public class CreatureFactory {
	private World world;
	
	public CreatureFactory(World world) {
		this.world = world;
	}
	
	public Creature newPlayer(List<String> messages, FieldOfView fov) {
		Creature player = new Creature(world, '@', AsciiPanel.brightYellow, 100, 20, 5, "Pacman");
		world.addAtEmptyLocation(player, 0);
		new PlayerAi(player, messages, fov);
		return player;
	}
	
	public Creature newFungus(int depth) {
		Creature fungus = new Creature(world, 'f', AsciiPanel.brightGreen, 10, 0, 0, "Fungus");
		world.addAtEmptyLocation(fungus, depth);
		new FungusAi(fungus, this);
		return fungus;
	}
	
	public Creature newGhost(int depth) {
		Creature ghost = new Creature(world, 'G', Color.lightGray, 10, 0, 0, "Ghost");
		world.addAtEmptyLocation(ghost, depth);
		new GhostAi(ghost);
		return ghost;
	}
}
