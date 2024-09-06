package com.raspix.snekcraft.blocks;

import net.minecraft.util.StringRepresentable;

public enum HidePart implements StringRepresentable {
    ENTERANCE("enterance"), //left 1
    ENTERANCE2("enterance2"), //left 2
    SIDE("side"), //right 1
    SIDE2("side2"), //right 2
    ; //head

    private final String name;

    private HidePart(String pName) {
        this.name = pName;
    }

    public String toString() {
        return this.name;
    }

    @Override
    public String getSerializedName() {
        return this.name;
    }

    ;


}
