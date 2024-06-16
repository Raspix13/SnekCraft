package com.raspix.snekcraft.items;

import com.raspix.snekcraft.entity.generics.SnakeBase;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SnakeBagItem extends Item {

    public SnakeBagItem(Properties properties) {
        super(properties);
    }

    public static int getSnakesInStack(ItemStack stack) {
        int snakeCount = 0;
        if (stack.getTag() != null) {
            for (String tagInfo : stack.getTag().getAllKeys()) {
                if (tagInfo.contains("Snake")) {
                    snakeCount++;
                }
            }
        }
        return snakeCount;
    }

    public static Entity getEntityFromNBT(CompoundTag nbt, Level world, boolean withInfo) {
        Entity entity = ForgeRegistries.ENTITY_TYPES.getValue(new ResourceLocation(getEntityID(nbt))).create(world);
        if (withInfo) entity.load(nbt);
        return entity;
    }

    public static String getEntityID(CompoundTag nbt) {
        return nbt.getString("id");
    }


    //called when placed
    @Override
    public InteractionResult useOn(UseOnContext context) {

        Player player = context.getPlayer();
        Level world = context.getLevel();
        if (player == null || world.isClientSide)return InteractionResult.FAIL;
        ItemStack stack = context.getItemInHand();

        if (getSnakesInStack(stack)<1) return InteractionResult.FAIL; //needs to have entity

        int snakeCount = 0;
        if (stack.getTag() != null) {
            for (String tagInfo : stack.getTag().getAllKeys()) {
                if (tagInfo.contains("Snake")) {
                    snakeCount++;
                    CompoundTag snakeTag = stack.getTag().getCompound(tagInfo);

                    SnakeBase snake = null;
                    try {
                        snake = (SnakeBase) getEntityFromNBT(snakeTag, world, true);
                    }catch (ClassCastException e){
                    }


                    if(snake != null){
                        BlockPos blockPos = context.getClickedPos();
                        snake.absMoveTo(blockPos.getX() + 0.5, blockPos.getY() + 1, blockPos.getZ() + 0.5, 0, 0);
                        world.addFreshEntity(snake);
                    }

                }
            }
        }
        if (snakeCount > 0) {
            stack.setTag(new CompoundTag());
            if(player != null && player.swingingArm != null){ // it is not always true
                player.swing(player.swingingArm);
            }
        }
        return InteractionResult.SUCCESS;

    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
        int snakeNum = getSnakesInStack(pStack);
        if(snakeNum > 0 ){
            //pTooltipComponents.add(Component.translatable("tooltip.snekcraft.snake_bag.tooltip.has_snakes", (snakeNum + "")).withStyle(ChatFormatting.GRAY));

            for (String tagInfo : pStack.getTag().getAllKeys()) {
                if (tagInfo.contains("Snake")) {
                    CompoundTag snakeTag = pStack.getTag().getCompound(tagInfo);

                    SnakeBase snake = null;
                    try {
                        snake = (SnakeBase) getEntityFromNBT(snakeTag, pLevel, true);
                        String snakeType = snake.getSpeciesName(snakeTag.getInt("Color"), snakeTag.getInt("Pattern"));
                        pTooltipComponents.add(Component.translatable("tooltip.snekcraft.snake_bag.tooltip.snake_type", snakeType).withStyle(ChatFormatting.GRAY));
                    }catch (ClassCastException e){
                    }

                }
            }


        }
    }

}
