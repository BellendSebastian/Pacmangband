package com.diquebutte.pacmangband.screens;

import com.diquebutte.pacmangband.Creature;
import com.diquebutte.pacmangband.Item;

public class InventoryScreen extends InventoryBasedScreen {

	public InventoryScreen(Creature player) {
		super(player);
	}

	@Override
	protected String getVerb() {
		return "Look 'pon yon inventory!";
	}

	@Override
	protected boolean isAcceptable(Item item) {
		return true;
	}

	@Override
	protected Screen use(Item item) {
		return null;
	}
	

}
