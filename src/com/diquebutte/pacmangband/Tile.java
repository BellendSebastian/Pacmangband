package com.diquebutte.pacmangband;

import java.awt.Color;

import asciiPanel.AsciiPanel;

public enum Tile {
	FLOOR((char)249, AsciiPanel.white),
	WALL((char)219, AsciiPanel.brightBlue),
	BOUNDS('x', AsciiPanel.brightBlack),
	STAIRS_DOWN('>', AsciiPanel.white),
	STAIRS_UP('<', AsciiPanel.white),
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
	
	public boolean isDiggable() {
		return this == Tile.WALL;
	}
	
	public boolean isGround() {
		return this != WALL && this != BOUNDS;
	}
}
