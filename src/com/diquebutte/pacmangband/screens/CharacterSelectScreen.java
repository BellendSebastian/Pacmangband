package com.diquebutte.pacmangband.screens;

import java.awt.event.KeyEvent;

import asciiPanel.AsciiPanel;

public class CharacterSelectScreen implements Screen {

	@Override
	public void displayOutput(AsciiPanel terminal) {
		terminal.write("-- Choose your class --", 1, 1);
		terminal.write("-----------------------", 1, 2);
		terminal.write("[a] Pacman", 1, 4);
		terminal.write("[b] Ms. Pacman", 1, 5);
	}

	@Override
	public Screen respondToUserInput(KeyEvent key) {
		Screen ret = this;
		switch(key.getKeyCode()) {
		case KeyEvent.VK_A: ret = new PlayScreen("Pacman"); break;
		case KeyEvent.VK_B: ret = new PlayScreen("Ms. Pacman"); break;
		}
		return ret;
	}

}
