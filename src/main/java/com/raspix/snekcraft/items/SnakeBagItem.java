package com.raspix.snekcraft.items;

import com.raspix.snekcraft.entity.ModEntityTypes;
import com.raspix.snekcraft.entity.generics.SnakeBase;
import com.raspix.snekcraft.entity.hognose.HognoseEntity;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
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
        Entity entity = Registry.ENTITY_TYPE.get(new ResourceLocation(getEntityID(nbt))).create(world);
        if (withInfo) entity.load(nbt);
        return entity;
    }

    public static String getEntityID(CompoundTag nbt) {
        return nbt.getString("id");
    }


    //called when placed
    @Override
    public InteractionResult useOn(UseOnContext context) {
        //System.out.println("snake bag was used");

        Player player = context.getPlayer();
        Level world = context.getLevel();
        if (player == null || world.isClientSide)return InteractionResult.FAIL;
        ItemStack stack = context.getItemInHand();

        if (getSnakesInStack(stack)<1) return InteractionResult.FAIL; //needs to have entity

        //Entity entity = getEntityFromStack(stack, world, true);

        //System.out.println("snake bag has snakes");
        int snakeCount = 0;
        if (stack.getTag() != null) {
            //System.out.println("snake bag has "+ stack.getTag().getAllKeys().toString());
            for (String tagInfo : stack.getTag().getAllKeys()) {
                if (tagInfo.contains("Snake")) {
                    snakeCount++;
                    CompoundTag snakeTag = stack.getTag().getCompound(tagInfo);
                    //HognoseEntity snake = new HognoseEntity(ModEntityTypes.HOGNOSE.get(), world);



                    SnakeBase snake = null;
                    try {
                        snake = (SnakeBase) getEntityFromNBT(snakeTag, world, true);
                    }catch (ClassCastException e){
                        /**HognoseEntity snake_old = new HognoseEntity(ModEntityTypes.HOGNOSE.get(), world);
                        BlockPos blockPos = context.getClickedPos();
                        snake_old.readAdditionalSaveData(snakeTag);
                        snake_old.absMoveTo(blockPos.getX() + 0.5, blockPos.getY() + 1, blockPos.getZ() + 0.5, 0, 0);
                        //snake.setLocationAndAngles(offset.getX() + 0.5D, offset.getY(), offset.getZ() + 0.5D, 0, 0);
                        world.addFreshEntity(snake_old);*/
                    }

                    /**try {
                        System.out.println("Getting Class Name");
                        String className = snakeTag.getString("Class");
                        System.out.println("Getting Class");
                        Class<?> clazz =  Class.forName(className);
                        //Class<?> clazz = snakeTag.getClass();
                        System.out.println("Class called "+ className);
                        System.out.println("Getting Constructor");
                        Constructor<?> constructor = clazz.getConstructor(EntityType.class, Level.class);
                        System.out.println("Getting Instance");
                        snake = (SnakeBase) constructor.newInstance(clazz.getComponentType(), world);
                        System.out.println("Got Instance");
                    }catch (Exception e){
                        e.printStackTrace();
                        return InteractionResult.FAIL;
                    }*/

                    if(snake != null){
                        BlockPos blockPos = context.getClickedPos();
                        //snake.readAdditionalSaveData(snakeTag);
                        snake.absMoveTo(blockPos.getX() + 0.5, blockPos.getY() + 1, blockPos.getZ() + 0.5, 0, 0);
                        //snake.setLocationAndAngles(offset.getX() + 0.5D, offset.getY(), offset.getZ() + 0.5D, 0, 0);
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

            //context.getPlayer().sendStatusMessage(new TranslationTextComponent("entity.rats.rat.sack.release", ratCount), true);
        }

        //CompoundNBT ratTag = stack.getTag().getCompound(tagInfo);
        //EntityRat rat = new EntityRat(RatsEntityRegistry.RAT, context.getWorld());
        //rat.readAdditional(ratTag);

        //BlockPos blockPos = context.getClickedPos();
        //entity.absMoveTo(blockPos.getX() + 0.5, blockPos.getY() + 1, blockPos.getZ() + 0.5, 0, 0);
        //stack.setTag(null);
        //world.addFreshEntity(entity);
        //if (this.canBeDepleted()) {
        //    stack.hurtAndBreak(1,player,playerEntity -> playerEntity.broadcastBreakEvent(context.getHand()));
        //}
        return InteractionResult.SUCCESS;

        //System.out.println(context.);
        //return super.useOn(context);
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
        //if(Screen.hasShiftDown()){
            //pTooltipComponents.add(new TranslatableComponent("tooltip.snekcraft.snake_bag.tooltip"));
        //pTooltipComponents.add(new TranslatableComponent("tooltip.snekcraft.snake_bag.tooltip."));
        //}
        int snakeNum = getSnakesInStack(pStack);
        if(snakeNum > 0 ){
            pTooltipComponents.add(new TranslatableComponent("tooltip.snekcraft.snake_bag.tooltip.has_snakes", (snakeNum + "")).withStyle(ChatFormatting.GRAY));
        }
    }

    /**@Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        return super.use(level, player, usedHand);

        ItemStack stack = player.getItemInHand(usedHand);
        CompoundTag snakeTag = new CompoundTag();
        if (stack.getTag() != null && stack.getTag().getCompound("Snake") != null) {
            snakeTag = stack.getTag().getCompound("Snake");
        }
        HognoseEntity snake = new HognoseEntity(ModEntityTypes.SNEKER.get(), level.getWorld());
        BlockPos offset = level.getPos().offset(context.getFace());
        snake.readAdditionalSaveData(snakeTag);
        snake.pos
        snake.setPos(offset.getX() + 0.5D, offset.getY(), offset.getZ() + 0.5D);
        if (!context.getWorld().isRemote) {
            context.getWorld().addEntity(snake);
        }
        stack.shrink(1);
        player.setItemInHand(usedHand, new ItemStack(Items.ARROW));
        player.swing(usedHand);
        return ActionResultType.SUCCESS;
    }*/



    /**public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack itemstack = playerIn.getHeldItem(handIn);
        ItemStack itemstack1 = playerIn.isCreative() ? itemstack.copy() : itemstack.split(1);
        worldIn.playSound(null, playerIn.getPosX(), playerIn.getPosY(), playerIn.getPosZ(), SoundEvents.ENTITY_SPLASH_POTION_THROW, SoundCategory.PLAYERS, 0.5F, 0.4F / (random.nextFloat() * 0.4F + 0.8F));
        EntityRatCaptureNet entitypotion = new EntityRatCaptureNet(worldIn, playerIn);
        Vector3d vector3d = playerIn.getLook(1.0F);
        Vector3f vector3f = new Vector3f(vector3d);

        entitypotion.shoot((double)vector3f.getX(), (double)vector3f.getY(), (double)vector3f.getZ(), 1.0F, 0.5F);
        worldIn.addEntity(entitypotion);
        return new ActionResult<ItemStack>(ActionResultType.SUCCESS, itemstack);
    }





    @Override
    public void onCreated(ItemStack itemStack, World world, PlayerEntity player) {
        itemStack.setTag(new CompoundNBT());
    }

    @Override
    public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        if (stack.getTag() == null) {
            stack.setTag(new CompoundNBT());
        }
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        List<String> ratNames = new ArrayList<>();
        if (stack.getTag() != null) {
            CompoundNBT ratTag = stack.getTag().getCompound("Rat");
            String ratName = I18n.format("entity.rats.rat");
            if (!ratTag.getString("CustomName").isEmpty() && !ratTag.getString("CustomName").startsWith("TextComponent")) {
                ITextComponent ratNameTag = ITextComponent.Serializer.func_240643_a_(ratTag.getString("CustomName"));
                if (ratNameTag != null) {
                    ratName = ratNameTag.getString();
                }
            }
        }
    }


    public ActionResultType onItemUse(ItemUseContext context) {
        ItemStack stack = context.getPlayer().getHeldItem(context.getHand());
        CompoundNBT ratTag = new CompoundNBT();
        if (stack.getTag() != null && stack.getTag().getCompound("Rat") != null) {
            ratTag = stack.getTag().getCompound("Rat");
        }
        EntityRat rat = new EntityRat(RatsEntityRegistry.RAT,context.getWorld());
        BlockPos offset = context.getPos().offset(context.getFace());
        rat.readAdditional(ratTag);
        rat.setLocationAndAngles(offset.getX() + 0.5D, offset.getY(), offset.getZ() + 0.5D, 0, 0);
        if (!context.getWorld().isRemote) {
            context.getWorld().addEntity(rat);
        }
        stack.shrink(1);
        context.getPlayer().setHeldItem(context.getHand(), new ItemStack(Items.ARROW));
        context.getPlayer().swingArm(context.getHand());
        return ActionResultType.SUCCESS;
    }

    public AbstractArrowEntity createArrow(World worldIn, ItemStack stack, LivingEntity shooter) {
        EntityRatArrow arrow = new EntityRatArrow(RatsEntityRegistry.RAT_ARROW, worldIn, shooter, stack);
        return arrow;
    }

    public boolean isInfinite(ItemStack stack, ItemStack bow, net.minecraft.entity.player.PlayerEntity player) {
        return false;
    }*/
}
