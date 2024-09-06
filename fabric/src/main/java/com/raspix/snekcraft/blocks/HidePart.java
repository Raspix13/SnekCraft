package com.raspix.snekcraft.blocks;

import net.minecraft.util.StringIdentifiable;

public enum HidePart implements StringIdentifiable {
	ENTRANCE("enterance"), // left 1, misspelled for compat
	ENTRANCE2("enterance2"), // left 2, misspelled for compat
	SIDE("side"), // right 1
	SIDE2("side2"); // right 2
	
	private final String name;
	
	private HidePart(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return name;
	}
	
	@Override
	public String asString() {
		return name;
	}
}
