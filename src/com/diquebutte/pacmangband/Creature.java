 package com.diquebutte.pacmangband;

import java.awt.Color;

public class Creature {
	private World world;
	private char glyph;
	private Color color;
	private CreatureAi ai;
	private int maxHp;
	private int hp;
	private int attackValue;
	private int defenseValue;
	private String name;
	public int visionRadius;
	private Inventory inventory;
	private int maxFood;
	private int food;
	
	public int x;
	public int y;
	public int z;
	
	public Creature(World world, char glyph, Color color, int maxHp, int attackValue, int defenseValue, String name) {
		this.world = world;
		this.glyph = glyph;
		this.color = color;
		this.hp = maxHp;
		this.maxHp = maxHp;
		this.visionRadius = 9;
		this.attackValue = attackValue;
		this.defenseValue = defenseValue;
		this.name = name;
		this.inventory = new Inventory(20);
		this.maxFood = 1000;
		this.food = maxFood / 3 * 2;
	}
	
	public int maxFood() {
		return maxFood;
	}
	
	public int food() {
		return food;
	}
	
	public void pickup() {
		Item item = world.item(x, y, z);
		if (inventory.isFull() || item == null) {
			doAction("grabs at the ground");
		} else {
			doAction("pickup a %s", item.name());
			world.remove(x, y, z);
			inventory.add(item);
		}
	}
	
	public void drop(Item item) {
		doAction("drop a " + item.name());
		inventory.remove(item);
		world.addAtEmptySpace(item, x, y, z);
	}
	
	public Inventory inventory() {
		return inventory;
	}
	
	public boolean canSee(int wx, int wy, int wz) {
		return ai.canSee(wx, wy, wz);
	}
	
	public Tile tile(int wx, int wy, int wz) {
		return world.tile(wx, wy, wz);
	}
	
	public int visionRadius() {
		return visionRadius;
	}
	
	public String name() {
		return name;
	}
	
	public char glyph() {
		return glyph;
	}
	
	public Color color() {
		return color;
	}
	
	public void setCreatureAi(CreatureAi ai) {
		this.ai = ai;
	}
	
	public void dig(int wx, int wy, int wz) {
		world.dig(wx, wy, wz);
	}
	
	public void moveBy(int mx, int my, int mz) {
        Tile tile = world.tile(x + mx, y + my, z + mz);
        if (mx == 0 && my == 0 && mz == 0) {
        	return;
        }
        if (mz == -1){
            if (tile == Tile.STAIRS_DOWN) {
                doAction("walk up the stairs to level %d", z + mz + 1);
            } else {
                doAction("try to go up but are stopped by the cave ceiling");
                return;
            }
        } else if (mz == 1){
            if (tile == Tile.STAIRS_UP) {
                doAction("walk down the stairs to level %d", z + mz + 1);
            } else {
                doAction("try to go down but are stopped by the cave floor");
                return;
            }
        }
     
        Creature other = world.creature(x + mx, y + my, z + mz);
     
        if (other == null) {
            ai.onEnter(x + mx, y + my, z + mz, tile);
        } else {
            attack(other);
        }
	}
	
	public Creature creature(int wx, int wy, int wz) {
		return world.creature(wx, wy, wz);
	}
	
	public void doAction(String message, Object ... params) {
		int r = 9;
		for (int ox = -r; ox < r + 1; ox++) {
			for (int oy = -r; oy < r + 1; oy++) {
				if (ox * ox + oy * oy > r * r) {
					continue;
				}
				Creature other = world.creature(x + ox, y + oy, z);
				
				if (other == null) {
					continue;
				}
				
				if (other == this) {
					other.notify("You " + message + ".", params);
				} else if (other.canSee(x, y, z)) {
					other.notify(String.format("The '%s' %s.", name, makeSecondPerson(message)), params);
				}
			}
		}
	}
	
	private String makeSecondPerson(String text) {
		String[] words = text.split(" ");
		words[0] = words[0] + "s";
		StringBuilder builder = new StringBuilder();
		for (String word : words) {
			builder.append(" ");
			builder.append(word);
		}
		return builder.toString().trim();
	}
	
	public void attack(Creature other) {
		int amount = Math.max(0,  attackValue() - other.defenseValue());
		amount = (int)(Math.random() * amount) + 1;
		other.modifyHp(-amount);
		notify("You attack the '%s' for %d damage.", other.name, amount);
		other.notify("The '%s' attacks you for %d damage.", name, amount);
	}
	
	public void modifyHp(int amount) {
		hp += amount;
		if (hp < 1) {
			doAction("die");
			leaveCorpse();
			world.remove(this);
		}
	}
	
	private void leaveCorpse() {
		Item corpse = new Item('%', color, name + " corpse");
		corpse.modifyFoodValue(maxHp * 3);
		world.addAtEmptySpace(corpse, x, y, z);
	}
	
	public void modifyFood(int amount) {
		food += amount;
		if (food > maxFood) {
			food = maxFood + 1;
			modifyHp(-1);
		} else if (food < 1 && isPlayer()) {
			modifyHp(-1000);
		}
	}
	
	private boolean isPlayer() {
		return glyph == '@';
	}
	
	public void eat(Item item) {
		modifyFood(item.foodValue());
		inventory.remove(item);
	}
	
	public void update() {
		modifyFood(-1);
		ai.onUpdate();
	}
	
	public boolean canEnter(int wx, int wy, int wz) {
		return world.tile(wx, wy, wz).isGround() && world.creature(wx, wy, wz) == null;
	}
	
	public int hp() {
		return hp;
	}
	
	public int maxHp() {
		return maxHp;
	}
	
	public int attackValue() {
		return attackValue;
	}
	
	public int defenseValue() {
		return defenseValue;
	}
	
	public void notify(String message, Object ... params) {
		ai.onNotify(String.format(message, params));
	}
}
