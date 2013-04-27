package com.diquebutte.pacmangband;

import java.awt.Color;
import java.util.List;

import asciiPanel.AsciiPanel;

public class StuffFactory {
	private World world;
	
	public StuffFactory(World world) {
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
	
	public Creature newGhost(int depth, Creature player) {
		Creature ghost = new Creature(world, 'G', Color.lightGray, 50, 10, 10, "Ghost");
		world.addAtEmptyLocation(ghost, depth);
		new GhostAi(ghost, player);
		return ghost;
	}
	
	public Item newPowerpill(int depth) {
		Item powerpill = new Item((char)249, AsciiPanel.brightWhite, "Power Pill");
		powerpill.modifyFoodValue(100);
		world.addAtEmptyLocation(powerpill, depth);
		return powerpill;
	}
	
	public Item newMacguffin(int depth) {
		Item item = new Item('*', AsciiPanel.brightYellow, "MacGuffin");
		world.addAtEmptyLocation(item, depth);
		return item;
	}
	
	public Item debugWeapon(int depth) {
		Item item = new Item(')', AsciiPanel.white, "Debug Weapon");
		item.modifyAttackValue(5);
		world.addAtEmptyLocation(item, depth);
		return item;
	}
}
