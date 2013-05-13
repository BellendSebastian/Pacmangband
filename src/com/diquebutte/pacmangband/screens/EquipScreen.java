package com.diquebutte.pacmangband.screens;

import com.diquebutte.pacmangband.Creature;
import com.diquebutte.pacmangband.Item;
import com.diquebutte.pacmangband.ItemType;

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
		return item.type() == ItemType.ARMOUR || item.type() == ItemType.WEAPON;
	}

	@Override
	protected Screen use(Item item) {
		player.equip(item);
		return null;
	}

}
