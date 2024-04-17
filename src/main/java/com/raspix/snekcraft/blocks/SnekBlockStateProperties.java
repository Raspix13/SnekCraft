package com.raspix.snekcraft.blocks;

import net.minecraft.util.StringRepresentable;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

public class SnekBlockStateProperties {


    public static final IntegerProperty UP = IntegerProperty.create("up", 0, 2);
    public static final IntegerProperty DOWN = IntegerProperty.create("down", 0, 2);
    public static final IntegerProperty NORTH = IntegerProperty.create("north", 0, 3); //0=wall, 1=empty, 2=invisible, 3=right corner?
    public static final IntegerProperty EAST = IntegerProperty.create("east", 0, 3);
    public static final IntegerProperty SOUTH = IntegerProperty.create("south", 0, 3);
    public static final IntegerProperty WEST = IntegerProperty.create("west", 0, 3);

    public static IntegerProperty EGG_COLOR = IntegerProperty.create("color", 0, 10);
    public static IntegerProperty EGG_PATTERN = IntegerProperty.create("pattern", 0, 4);



    public static final EnumProperty<MergeBlockState> FORM = EnumProperty.create("form", MergeBlockState.class);

    public enum MergeBlockState implements StringRepresentable{

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
        FOUR_VERT("four_v"),
        ;


        private final String name;

        MergeBlockState(String name) {
            this.name = name;
        }

        public String toString() {
            return this.name;
        }

        @Override
        public String getSerializedName() {
            return this.name;
        }


    }

}
