package com.diquebutte.pacmangband.screens;

import java.awt.event.KeyEvent;

import asciiPanel.AsciiPanel;

public class StartScreen implements Screen {

	public  void displayOutput(AsciiPanel terminal) {
		terminal.writeCenter("-- Pacmangband --", 3);
		terminal.writeCenter("-- [enter] to start --", 22);
	}

	@Override
	public Screen respondToUserInput(KeyEvent key) {
		return key.getKeyCode() == KeyEvent.VK_ENTER ? new CharacterSelectScreen() : this;
	}

}
