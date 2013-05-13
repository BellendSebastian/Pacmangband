package com.diquebutte.pacmangband.screens;

import java.awt.event.KeyEvent;

import asciiPanel.AsciiPanel;

import com.diquebutte.pacmangband.Creature;

public class CharacterScreen implements Screen {
	protected Creature player;

	public CharacterScreen(Creature player) {
		this.player = player;
	}
	
	public void displayOutput(AsciiPanel terminal) {
		terminal.clear();
		terminal.write("--Equipment--", 1, 1);
		terminal.write("-------------", 1, 2);
		if (player.armour() == null) {
			terminal.write("Helm: None", 1, 4);
		} else {
			terminal.write(String.format("Helm: %s (+%d)", player.armour().name(), player.armour().defenseValue()), 1, 4);	
		}
		terminal.write("Necklace: You have no neck", 1, 5);
		terminal.write("Pauldrons: You have no shoulders", 1, 6);
		terminal.write("Breastplate: You have no torso", 1, 7);
		terminal.write("Belt: You have no waist", 1, 8);
		terminal.write("Gauntlets: You have no hands", 1, 9);
		terminal.write("Cuisse: You have no legs", 1, 10);
		terminal.write("Greives: You have no legs", 1, 11);
		if (player.weapon() == null) {
			terminal.write("Weapon: None", 1, 13);
		} else {
			terminal.write(String.format("Weapon: %s (+%d)", player.weapon().name(), player.weapon().attackValue()), 1, 13);
		}
		terminal.writeCenter("Press [escape] to return to the game.", 22);
		
	}
	
	
	public Screen respondToUserInput(KeyEvent key) {
		return key.getKeyCode() == KeyEvent.VK_ESCAPE ? null : this;
	}
}
