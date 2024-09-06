package com.raspix.snekcraft.items;

import java.util.List;

import org.jetbrains.annotations.Nullable;

import com.raspix.snekcraft.entity.generics.SnakeBase;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.Registries;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class SnakeBagItem extends Item {
	
	
	public SnakeBagItem(Settings settings) {
		super(settings);
	}
	
	public static int getSnakesInStack(ItemStack itemStack) {
		int snakeCount = 0;
		if(itemStack.getNbt() != null) {
			for(String nbtInfo: itemStack.getNbt().getKeys()) {
				if(nbtInfo.contains("Snake")) {
					++snakeCount;
				}
			}
		}
		return snakeCount;
	}
	
	public static Entity getEntityFromNBT(NbtCompound nbtCompound, World world, boolean withInfo) {
		Entity entity = Registries.ENTITY_TYPE.get(new Identifier(nbtCompound.getString("id"))).create(world);
		if(withInfo) entity.readNbt(nbtCompound);
		return entity;
	}
	
	@Override
	public ActionResult useOnBlock(ItemUsageContext context) {
		PlayerEntity player = context.getPlayer();
		World world = context.getWorld();
		if(player == null || world.isClient) return ActionResult.FAIL;
		
		ItemStack itemStack = context.getStack();
		if(getSnakesInStack(itemStack) < 1) return ActionResult.FAIL;
		
		int snakeCount = 0;
		if(itemStack.getNbt() != null) {
			for(String nbtInfo: itemStack.getNbt().getKeys()) {
				if(nbtInfo.contains("Snake")) {
					++snakeCount;
					NbtCompound snakeNbt = itemStack.getNbt().getCompound(nbtInfo);
					
					if(getEntityFromNBT(snakeNbt, world, true) instanceof SnakeBase snake) {
						BlockPos blockPos = context.getBlockPos();
						snake.updatePositionAndAngles(blockPos.getX() + 0.5, blockPos.getY() + 1, blockPos.getZ() + 0.5, 0, 0);
						world.spawnEntity(snake);
					}
				}
			}
		}
		if(snakeCount > 0) {
			itemStack.setNbt(new NbtCompound());
			if(player != null && player.preferredHand != null) {
				player.swingHand(player.preferredHand);
			}
		}
		return ActionResult.SUCCESS;
	}
	
	@Override
	public void appendTooltip(ItemStack itemStack, @Nullable World world, List<Text> tooltipTexts, TooltipContext context) {
		super.appendTooltip(itemStack, world, tooltipTexts, context);
		int snakeNum = getSnakesInStack(itemStack);
		if(snakeNum > 0) {
			for(String nbtInfo: itemStack.getNbt().getKeys()) {
				if(nbtInfo.contains("Snake")) {
					NbtCompound snakeNbt = itemStack.getNbt().getCompound(nbtInfo);
					
					if(getEntityFromNBT(snakeNbt, world, true) instanceof SnakeBase snake) {
						String snakeType = snake.getSpeciesName(snakeNbt.getInt(SnakeBase.NBT_KEY_COLOR), snakeNbt.getInt(SnakeBase.NBT_KEY_PATTERN));
						tooltipTexts.add(Text.translatable("tooltip.snekcraft.snake_bag.tooltip.snake_type", snakeType).formatted(Formatting.GRAY));
					}
				}
			}
		}
	}
}
