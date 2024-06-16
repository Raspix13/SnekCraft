package com.raspix.snekcraft.blocks.eggs;

import com.raspix.snekcraft.entity.ModEntityTypes;
import com.raspix.snekcraft.entity.ball_python.BallPythonEntity;
import com.raspix.snekcraft.entity.generics.SnakeBase;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;

public class BallPythonEggBlock extends SnakeEggBlock{

    // 3 patterns, 0:normal, 1:piebald, 2:pinstripe
    /** private static final int normalColor = 0;
    private static final int pastelColor = 1;
    private static final int axanthicColor = 2;
    private static final int arcticColor = 3;
    private static final int snowColor = 4;
    private static final int superArcticColor = 5;
    private static final int subZeroColor = 6;
    private static final int yetiColor = 7;
    private static final int rainbowColor = 8;
    public static final IntegerProperty COLOR = IntegerProperty.create("color", 0, 8);
    public static final IntegerProperty PATTERN = IntegerProperty.create("pattern", 0, 2);
    public static final IntegerProperty COLOR_P2 = IntegerProperty.create("color_p2", 0, 8);
    public static final IntegerProperty PATTERN_P2 = IntegerProperty.create("pattern_p2", 0, 2);*/

    public BallPythonEggBlock(Properties pProperties) {
        super(pProperties);
        /**COLOR = IntegerProperty.create("color", 0, 11);
         PATTERN = IntegerProperty.create("pattern", 0, 2);
         COLOR_P2 = IntegerProperty.create("color_p2", 0, 11);
         PATTERN_P2 = IntegerProperty.create("pattern_p2", 0, 2);*/

    }

    @Override
    public EntityType GetSnakeType() {
        return ModEntityTypes.BALLPYTHON.get();
    }


    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        super.createBlockStateDefinition(pBuilder);
        //pBuilder.add(COLOR, PATTERN, COLOR_P2, PATTERN_P2);
    }

    /**public void setParentTraits(int p1Color, int p1Pattern, int p2Color, int p2Pattern, Level level, BlockPos pos){
        //System.out.println("setting traits parent 1:" + p1Color + "c & " + p1Pattern + "p and parent 2: " + p2Color + "c & " + p2Pattern);
        level.setBlock(pos, level.getBlockState(pos).setValue(COLOR, p1Color).setValue(PATTERN, p1Pattern).setValue(COLOR_P2, p2Color).setValue(PATTERN_P2, p2Pattern), 3);
    }*/



    @Override
    public int getOffspringColor(CompoundTag compoundTag) {
        return BallPythonEntity.colorGenetics
                [Math.min(Math.max(compoundTag.getInt("color"), 0), BallPythonEntity.GetMaxColor())]
                [Math.min(Math.max(compoundTag.getInt("color_p2"), 0), BallPythonEntity.GetMaxColor())]
                .GetGene(this.random.nextInt(SnakeBase.BREEDING_RANGE));

    }

    @Override
    public int getOffspringPattern(CompoundTag compoundTag) {
        return BallPythonEntity.patternGenetics
                [Math.min(Math.max(compoundTag.getInt("pattern"), 0), BallPythonEntity.GetMaxPattern())]
                [Math.min(Math.max(compoundTag.getInt("pattern_p2"), 0), BallPythonEntity.GetMaxPattern())]
                .GetGene(this.random.nextInt(SnakeBase.BREEDING_RANGE));
    }


}
