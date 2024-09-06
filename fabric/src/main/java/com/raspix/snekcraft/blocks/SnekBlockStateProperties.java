package com.raspix.snekcraft.blocks;

import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.StringIdentifiable;

public class SnekBlockStateProperties {
	public static final IntProperty UP = IntProperty.of("up", 0, 2);
	public static final IntProperty DOWN = IntProperty.of("down", 0, 2);
	public static final IntProperty NORTH = IntProperty.of("north", 0, 3); // 0 = wall, 1 = empty, 2 = invisible, 3 = right corner?
	public static final IntProperty EAST = IntProperty.of("east", 0, 3);
	public static final IntProperty SOUTH = IntProperty.of("south", 0, 3);
	public static final IntProperty WEST = IntProperty.of("west", 0, 3);
	
	public static IntProperty EGG_COLOR = IntProperty.of("color", 0, 10);
	public static IntProperty EGG_PATTERN = IntProperty.of("pattern", 0, 4);
	
	public static final EnumProperty<MergeBlockState> FORM = EnumProperty.of("form", MergeBlockState.class);
	
	public enum MergeBlockState implements StringIdentifiable {
		INVISIBLE("invisible"), // has mergeables on all sides
		ALONE("alone"), // fully by itself, all sides visible
		
        // has solid top and bottom
        ONE("one"),
        CORNER("corner"),
        OPPOSITE("opposite"),
        THREE("three"),
        FOUR("four"),
        
        // has solid bottom
        ONE_TOP("one_t"),
        CORNER_TOP("corner_t"),
        OPPOSITE_TOP("opposite_t"),
        THREE_TOP("three_t"),
        FOUR_TOP("four_t"),
        
        // has solid top
        ONE_BOTTOM("one_b"),
        CORNER_BOTTOM("corner_b"),
        OPPOSITE_BOTTOM("opposite_b"),
        THREE_BOTTOM("three_b"),
        FOUR_BOTTOM("four_b"),
        
        // non-solid top and bottom
        ONE_VERT("one_v"),
        CORNER_VERT("corner_v"),
        OPPOSITE_VERT("opposite_v"),
        THREE_VERT("three_v"),
        FOUR_VERT("four_v");
        
		private final String name;
		
		MergeBlockState(String name){
			this.name = name;
		}
		
		public String toString() {
			return name;
		}
		
		@Override
		public String asString() {
			return name;
		}
	}
}
