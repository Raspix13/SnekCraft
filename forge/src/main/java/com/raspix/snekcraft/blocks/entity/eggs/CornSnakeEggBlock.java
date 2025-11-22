package com.raspix.snekcraft.blocks.entity.eggs;

import com.raspix.snekcraft.entity.ModEntityTypes;
import com.raspix.snekcraft.entity.ball_python.BallPythonEntity;
import com.raspix.snekcraft.entity.corn.CornSnakeEntity;
import com.raspix.snekcraft.entity.generics.SnakeBase;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;

public class CornSnakeEggBlock extends SnakeEggBlock{


    public CornSnakeEggBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public EntityType<?> GetSnakeType() {
        return ModEntityTypes.CORNSNAKE.get();
    }


    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        super.createBlockStateDefinition(pBuilder);
    }

    @Override
    public int getOffspringColor(CompoundTag compoundTag) {
        return CornSnakeEntity.colorGenetics
                [Math.min(Math.max(compoundTag.getInt("color"), 0), CornSnakeEntity.GetMaxColor())]
                [Math.min(Math.max(compoundTag.getInt("color_p2"), 0), CornSnakeEntity.GetMaxColor())]
                .GetGene(this.random.nextInt(SnakeBase.BREEDING_RANGE));
    }

    @Override
    public int getOffspringPattern(CompoundTag compoundTag) {
        return CornSnakeEntity.patternGenetics
                [Math.min(Math.max(compoundTag.getInt("pattern"), 0), CornSnakeEntity.GetMaxPattern())]
                [Math.min(Math.max(compoundTag.getInt("pattern_p2"), 0), CornSnakeEntity.GetMaxPattern())]
                .GetGene(this.random.nextInt(SnakeBase.BREEDING_RANGE));
    }

}
