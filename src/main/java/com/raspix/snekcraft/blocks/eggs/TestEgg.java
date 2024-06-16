package com.raspix.snekcraft.blocks.eggs;

import com.raspix.snekcraft.blocks.entity.SnakeEggBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEventListener;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

public class TestEgg  extends BaseEntityBlock {


    public TestEgg(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {

        if (!pLevel.isClientSide() && pHand == InteractionHand.MAIN_HAND) {
            System.out.println("Hello from test egg");
            CompoundTag compoundTag = pLevel.getBlockEntity(pPos).getPersistentData();
            if(compoundTag != null){
                int p1 = compoundTag.getInt("pattern");
                int p2 = compoundTag.getInt("pattern_p2");
                int c1 = compoundTag.getInt("color");
                int c2 = compoundTag.getInt("color_p2");
                System.out.println("The egg has ps:" + p1 + ", " + p2 + ", and cs: " + c1 + ", " + c2 + " stored");
            }else {
                System.out.println("compound was null");
            }

        }

        return super.use(pState, pLevel, pPos, pPlayer, pHand, pHit);
    }


    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new SnakeEggBlockEntity(pPos, pState);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        return super.getTicker(pLevel, pState, pBlockEntityType);
    }

}
