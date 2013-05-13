package com.diquebutte.pacmangband.screens;

import com.diquebutte.pacmangband.Creature;
import com.diquebutte.pacmangband.Item;
import com.diquebutte.pacmangband.ItemType;

public class EatScreen extends InventoryBasedScreen {

	public EatScreen(Creature player) {
		super(player);
	}

	@Override
	protected String getVerb() {
		return "What scarfeth thou?";
	}

	@Override
	protected boolean isAcceptable(Item item) {
		return item.type() == ItemType.FOOD || item.type() == ItemType.CORPSE;
	}

	@Override
	protected Screen use(Item item) {
		player.eat(item);
		return null;
	}
	
}
