package com.raspix.snekcraft.blocks.entity.eggs;

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

    public BallPythonEggBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public EntityType<?> GetSnakeType() {
        return ModEntityTypes.BALLPYTHON.get();
    }


    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        super.createBlockStateDefinition(pBuilder);
    }

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
