package com.diquebutte.pacmangband.screens;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import com.diquebutte.pacmangband.Creature;
import com.diquebutte.pacmangband.CreatureFactory;
import com.diquebutte.pacmangband.World;
import com.diquebutte.pacmangband.WorldBuilder;

import asciiPanel.AsciiPanel;

public class PlayScreen implements Screen {

	private World world;
	private int screenWidth;
	private int screenHeight;
	private Creature player;
	private List<String> messages;
	
	public PlayScreen() {
		screenWidth = 80;
		screenHeight = 23;
		messages = new ArrayList<String>();
		createWorld();
		CreatureFactory cf = new CreatureFactory(world);
		createCreatures(cf);
	}
	
	private void createCreatures(CreatureFactory creatureFactory) {
		player = creatureFactory.newPlayer(messages);
		for (int z = 0; z < world.depth(); z++) {
			for (int i = 0; i < 8; i++) {
				creatureFactory.newGhost(z);
			}
		}
	}
	
	private void createWorld() {
		world = new WorldBuilder(90, 31, 5).makeCaves().build();
	}
	
	public int getScrollX() {
		return Math.max(0, Math.min(player.x - screenWidth / 2, world.width() - screenWidth));
	}
	
	public int getScrollY() {
		return Math.max(0, Math.min(player.y - screenHeight / 2, world.height() - screenHeight));
	}
	
	private void displayTiles(AsciiPanel terminal, int left, int top) {
		for (int x = 0; x < screenWidth; x++) {
			for (int y = 0; y < screenHeight; y++) {
				int wx = x + left;
				int wy = y + top;

				Creature creature = world.creature(wx, wy, player.z);
				if (creature != null) {
					terminal.write(creature.glyph(), creature.x - left, creature.y - top, creature.color());
				} else {
					terminal.write(world.glyph(wx, wy, player.z), x, y, world.color(wx, wy, player.z));
				}
			}
		}
	}
	
	@Override
	public void displayOutput(AsciiPanel terminal) {
		int left = getScrollX();
		int top = getScrollY();
		displayTiles(terminal, left, top);
		displayMessages(terminal, messages);
		terminal.write(player.glyph(), player.x - left, player.y - top, player.color());
		String stats = String.format("%3d/%3d hp", player.hp(), player.maxHp());
		terminal.write(stats, 1, 23);
	}
	
	private void displayMessages(AsciiPanel terminal, List<String> messages) {
		int top = screenHeight - messages.size();
		for (int i = 0; i < messages.size(); i++) {
			terminal.writeCenter(messages.get(i), top + i);
		}

		messages.clear();
	}

	@Override
	public Screen respondToUserInput(KeyEvent key) {
		switch(key.getKeyCode()) {
		case KeyEvent.VK_ESCAPE: return new LoseScreen();
		case KeyEvent.VK_ENTER: return new WinScreen();
		case KeyEvent.VK_LEFT:
        case KeyEvent.VK_H: player.moveBy(-1, 0, 0); break;
        case KeyEvent.VK_RIGHT:
        case KeyEvent.VK_L: player.moveBy(1, 0, 0); break;
        case KeyEvent.VK_UP:
        case KeyEvent.VK_K: player.moveBy(0, -1, 0); break;
        case KeyEvent.VK_DOWN:
        case KeyEvent.VK_J: player.moveBy(0, 1, 0); break;
        case KeyEvent.VK_Y: player.moveBy(-1, -1, 0); break;
        case KeyEvent.VK_U: player.moveBy(1, -1, 0); break;
        case KeyEvent.VK_B: player.moveBy(-1, 1, 0); break;
        case KeyEvent.VK_N: player.moveBy(1, 1, 0); break;
		}
		switch(key.getKeyChar()) {
        case '<': player.moveBy(0, 0, -1); break;
        case '>': player.moveBy(0, 0, 1); break;
		}
		world.update();
		return this;
	}
	
}
