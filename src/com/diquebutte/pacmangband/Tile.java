package com.diquebutte.pacmangband;

import java.awt.Color;

import asciiPanel.AsciiPanel;

public enum Tile {
	FLOOR('.', AsciiPanel.brightBlack),
	WALL((char)219, AsciiPanel.brightBlue),
	BOUNDS('x', AsciiPanel.brightBlack),
	STAIRS_DOWN('>', AsciiPanel.white),
	STAIRS_UP('<', AsciiPanel.white),
	SPAWN('.', AsciiPanel.red),
	UNKNOWN(' ', AsciiPanel.white);
	
	private char glyph;
	private Color color;

	public char glyph() {
		return glyph;
	}
	
	public Color color() {
		return color;
	}
	
	Tile(char glyph, Color color) {
		this.glyph = glyph;
		this.color = color;
	}

	public boolean isGround() {
		return this != WALL && this != BOUNDS;
	}
	
	public boolean isSpawn() {
		return this != SPAWN;
	}
	
	public boolean isStairs() {
		return this != STAIRS_DOWN && this != STAIRS_UP;
	}
}
