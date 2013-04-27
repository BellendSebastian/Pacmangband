package com.diquebutte.pacmangband;

import java.awt.Color;

public class Item {
	private char glyph;
	private Color color;
	private String name;
	private int foodValue;

	public void modifyFoodValue(int amount) {
		foodValue += amount;
	}
	
	public int foodValue() {
		return foodValue;
	}
	
	public char glyph() {
		return glyph;
	}
	
	public Color color() {
		return color;
	}
	
	public String name() {
		return name;
	}
	
	public Item(char glyph, Color color, String name) {
		this.glyph = glyph;
		this.color = color;
		this.name = name;
	}
}
