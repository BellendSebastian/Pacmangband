package com.diquebutte.pacmangband;

import java.awt.Color;
import java.util.List;

import asciiPanel.AsciiPanel;

public class StuffFactory {
	private World world;
	
	public StuffFactory(World world) {
		this.world = world;
	}
	
	public Creature newPlayer(List<String> messages, FieldOfView fov, String playerClass) {
		Creature player = new Creature(world, '@', AsciiPanel.brightYellow, 100, 20, 0, playerClass);
		player.visionRadius = 5;
		if (playerClass != "Pacman") {
			Item bow = new Item('+', AsciiPanel.brightRed, "Ms. Pacman's Bow", ItemType.ARMOUR);
			bow.modifyDefenseValue(0);
			player.startingItem(bow);
		}
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
		world.addAtEmptySpawnPoint(ghost, depth);
		new GhostAi(ghost, player);
		return ghost;
	}
	
	public Item newPowerpill(int depth) {
		Item powerpill = new Item((char)249, AsciiPanel.brightWhite, "Power Pill", ItemType.FOOD);
		powerpill.modifyFoodValue(100);
		world.addAtEmptyLocation(powerpill, depth);
		return powerpill;
	}
	
	public Item newMacguffin(int depth) {
		Item item = new Item('*', AsciiPanel.brightYellow, "MacGuffin", ItemType.POINTLESS);
		world.addAtEmptyLocation(item, depth);
		return item;
	}
	
	public Item debugWeapon(int depth) {
		Item item = new Item(')', AsciiPanel.white, "Sharpened teeth", ItemType.WEAPON);
		item.modifyAttackValue(10);
		world.addAtEmptyLocation(item, depth);
		return item;
	}
}
