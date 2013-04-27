package com.diquebutte.pacmangband.screens;

import java.awt.event.KeyEvent;

import asciiPanel.AsciiPanel;

public class DeathScreen implements Screen {

	@Override
	public void displayOutput(AsciiPanel terminal) {
		terminal.write("u died, lol", 1, 1);
		terminal.writeCenter("-- press [enter] to restart --", 22);
	}

	@Override
	public Screen respondToUserInput(KeyEvent key) {
		return key.getKeyCode() == KeyEvent.VK_ENTER ? new CharacterSelectScreen() : this;
	}

}
