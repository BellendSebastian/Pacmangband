package com.diquebutte.pacmangband.screens;

import com.diquebutte.pacmangband.Creature;
import com.diquebutte.pacmangband.Item;

public class EquipScreen extends InventoryBasedScreen {

	public EquipScreen(Creature player) {
		super(player);
	}

	@Override
	protected String getVerb() {
		return "What would ye like to wear or wield?";
	}

	@Override
	protected boolean isAcceptable(Item item) {
		return item.attackValue() > 0 || item.defenseValue() > 0;
	}

	@Override
	protected Screen use(Item item) {
		player.equip(item);
		return null;
	}

}
