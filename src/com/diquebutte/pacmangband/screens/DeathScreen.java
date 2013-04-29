package com.diquebutte.pacmangband.screens;

import java.awt.event.KeyEvent;

import com.diquebutte.pacmangband.Audio;


import asciiPanel.AsciiPanel;

public class DeathScreen implements Screen {

	private boolean playedSound = false;
	
	@Override
	public void displayOutput(AsciiPanel terminal) {
		if (!playedSound) {
			Audio a = new Audio();
			a.playMp3("death");
			playedSound = true;
		}
		terminal.write("u died, lol", 1, 1);
		terminal.writeCenter("-- press [enter] to restart --", 22);
	}

	@Override
	public Screen respondToUserInput(KeyEvent key) {
		return key.getKeyCode() == KeyEvent.VK_ENTER ? new CharacterSelectScreen() : this;
	}

}
