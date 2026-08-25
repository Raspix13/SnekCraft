package com.raspix.snekcraft.items;

import com.raspix.snekcraft.entity.generics.SnakeBase;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SnakeBagItem extends Item {

    private final java.util.function.Supplier<? extends EntityType<?>> entityTypeSupplier;

    public SnakeBagItem(Properties properties) {
        super(properties);
        this.entityTypeSupplier = null;
    }

    public SnakeBagItem(java.util.function.Supplier<? extends EntityType<?>> entitySupplier, Item.Properties properties) {
        super(properties);
        this.entityTypeSupplier = entitySupplier;
    }

    /**
     * this should always be either 0 or 1 now since bags can only have 1 snake
     */
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
        ServerLevel level = (ServerLevel) world;

        if (entityTypeSupplier == null && getSnakesInStack(stack)<1) return InteractionResult.FAIL; //needs to have entity if normal bag

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
                        snake.setUUID(Mth.createInsecureUUID(world.getRandom())); // fixes duplicate issue for creative function
                        world.addFreshEntity(snake);
                    }
                }
            }
        }else if(entityTypeSupplier != null){ // if from creative
            Entity entity = entityTypeSupplier.get().spawn(level, stack, player, context.getClickedPos(), MobSpawnType.BUCKET, true, false);
            snakeCount++;
        }
        if (snakeCount > 0) {
            //stack.setTag(new CompoundTag());
            //player.setItemInHand(context.getHand(), new ItemStack(ItemInit.SNAKE_BAG.get()));
            player.setItemInHand(context.getHand(), getEmptySuccessItem(stack, player));
            if(player != null && player.swingingArm != null){ // it is not always true despite what error says
                player.swing(player.swingingArm);
            }
        }
        return InteractionResult.SUCCESS;

    }

    @Override
    public boolean hasCraftingRemainingItem() {
        return true;
    }

    @Override
    public ItemStack getCraftingRemainingItem(ItemStack itemStack) {
        // Replace Items.BUCKET with your custom empty item (e.g., ModItems.MY_EMPTY_CAN.get())
        return new ItemStack(ItemInit.SNAKE_BAG.get());
    }

    // for some reason cant reuse full bag when entity is already out, so should not use this intead of straight item for now
    public static ItemStack getEmptySuccessItem(ItemStack bagStack, Player pPlayer) {
        return !pPlayer.getAbilities().instabuild ? new ItemStack(ItemInit.SNAKE_BAG.get()) : bagStack;
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
