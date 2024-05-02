package com.raspix.snekcraft.blocks.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

public class SnakeEggBlockEntity extends BlockEntity {

    private int COLOR;
    private int PATTERN;
    private int COLOR_P2;
    private int PATTERN_P2;

    public SnakeEggBlockEntity(BlockEntityType<?> pType, BlockPos pPos, BlockState pBlockState) {
        super(pType, pPos, pBlockState);
    }


    //these are needed when actually using block entities
    public SnakeEggBlockEntity(BlockPos pPos, BlockState pBlockState, int color) {
        super(BlockEntityInit.SNAKE_EGG.get(), pPos, pBlockState);
        //this.COLOR = color;
    }

    public SnakeEggBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(BlockEntityInit.SNAKE_EGG.get(), pPos, pBlockState);
    }

    public SnakeEggBlockEntity(BlockPos pPos, BlockState pBlockState, int color, int color_2, int pattern, int pattern_2) {
        super(BlockEntityInit.SNAKE_EGG.get(), pPos, pBlockState);
        /**this.COLOR = color;
        this.COLOR_P2 = color_2;
        this.PATTERN = pattern;
        this.PATTERN_P2 = pattern_2;
        System.out.println("new egg with colors " + this.COLOR + " and " + this.COLOR_P2);*/
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        this.COLOR = tag.getInt("color");
        this.COLOR_P2 = tag.getInt("color_p2");
        this.PATTERN = tag.getInt("pattern");
        this.PATTERN_P2 = tag.getInt("pattern_p2");

    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.putInt("color", this.COLOR);
        tag.putInt("color_p2", this.COLOR_P2);
        tag.putInt("pattern", this.PATTERN);
        tag.putInt("pattern_p2", this.PATTERN_P2);
    }

    public int getCOLOR(){
        return COLOR;
    }

    public int getCOLOR_P2(){
        return COLOR_P2;
    }

    public int getPATTERN(){
        System.out.println("Pattern was set in EggBlockEntity");
        return PATTERN;
    }

    public int getPATTERN_P2(){
        return PATTERN_P2;
    }

    public void setCOLOR(int val){
        this.getUpdateTag().putInt("color", val);
        System.out.println("Color was set to " + val + "in EggBlockEntity");
        this.COLOR = val;
    }

    public void setCOLOR_P2(int val){
        System.out.println("Color2 was set to " + val + "in EggBlockEntity");
        this.COLOR_P2 = val;
    }

    public void setPATTERN(int val){
        PATTERN = val;
    }

    public void setPATTERN_P2(int val){
        PATTERN_P2 = val;
    }

    public void setStats(int color, int color_p2, int pattern, int pattern_p2) {
        this.COLOR = color;
        this.COLOR_P2 = color_p2;
        this.PATTERN = pattern;
        this.PATTERN_P2 = pattern_p2;
    }

    public void setStats(CompoundTag tag) {
        this.COLOR = tag.getInt("color");
        this.COLOR_P2 = tag.getInt("color_p2");
        this.PATTERN = tag.getInt("pattern");
        this.PATTERN_P2 = tag.getInt("pattern");
    }

    public void PrintOutStats(){
        System.out.println("SnakeBlockEntity Stats: c=" + this.COLOR + ", c2=" + this.COLOR_P2 + ", p=" + this.PATTERN + ", p2=" + this.PATTERN_P2);
    }

}
