package com.raspix.snekcraft.entity.generics;

import com.raspix.snekcraft.blocks.*;
import com.raspix.snekcraft.blocks.eggs.SnakeEggBlock;
import com.raspix.snekcraft.blocks.entity.SnakeEggBlockEntity;
import com.raspix.snekcraft.items.ItemInit;
import com.raspix.snekcraft.items.SnakeBagItem;
import com.raspix.snekcraft.packets.PacketHandler;
import com.raspix.snekcraft.packets.ServerboundShoulderUpdate;
import com.raspix.snekcraft.sounds.SoundInit;
import com.raspix.snekcraft.util.KeyInit;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;

import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.AnimationState;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.builder.ILoopType;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;

import java.util.EnumSet;
import java.util.Random;

public abstract class SnakeBase extends Animal implements IAnimatable {

    // Entity Save Data
    private static final Ingredient FOOD_ITEMS = Ingredient.of(Items.CHICKEN, Items.EGG, Items.RABBIT);
    private static final EntityDataAccessor<Integer> COLOR = SynchedEntityData.defineId(SnakeBase.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> PATTERN = SynchedEntityData.defineId(SnakeBase.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Boolean> RESTING = SynchedEntityData.defineId(SnakeBase.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> HAS_EGG = SynchedEntityData.defineId(SnakeBase.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> LAYING_EGG = SynchedEntityData.defineId(SnakeBase.class, EntityDataSerializers.BOOLEAN);

    // Values
    public static final int BREEDING_RANGE = 100;

    //private AnimationFactory factory = new AnimationFactory(this); // it will run even with this error
    public AnimationFactory factory = GeckoLibUtil.createFactory(this);
    int layEggCounter;
    private int partnerColor = 0;
    private int partnerPattern = 0;
    public int shedTime = this.random.nextInt(24000) + 12000;
    public int bleleleTime = this.random.nextInt(1000) + 1000;
    private boolean isSittingOnShoulder;

    private final EntityDimensions size;

    //TODO: must set these in each child
    private static GenePool[][] colorGenetics;
    private static GenePool[][] patternGenetics;


    protected SnakeBase(EntityType<? extends Animal> pEntityType, Level pLevel, EntityDimensions size) {
        super(pEntityType, pLevel);
        this.size = size;
    }

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }

    @Override
    public AttributeMap getAttributes() {
        return super.getAttributes();
    }

    @Override
    protected void registerGoals(){
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.25D));
        this.goalSelector.addGoal(1, new SnakeBreedGoal(this, 1.0D));
        this.goalSelector.addGoal(1, new SnakeLayEggGoal(this, 1.0D));
        //this.goalSelector.addGoal(1, new SnakeShoulderGoal(this));
        this.goalSelector.addGoal(4, new TemptGoal(this, 1.2D, FOOD_ITEMS, false));
        this.goalSelector.addGoal(5, new MeleeAttackGoal(this, 1.0D, true));
        this.goalSelector.addGoal(6, new FindRestSpotGoal(this, 1.0D));
        this.goalSelector.addGoal(7, new CurlGoal(this));
        this.goalSelector.addGoal(8, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(9, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(10, new RandomLookAroundGoal(this));
    }

    public static AttributeSupplier setAttributes(){
        return Animal.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20f)
                .add(Attributes.ATTACK_DAMAGE, 3f)
                .add(Attributes.ATTACK_SPEED, 1.0f)
                .add(Attributes.MOVEMENT_SPEED, 0.25f).build();
    }

    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);

        if (itemstack.getItem() == ItemInit.SNAKE_BAG.get()) {
            //System.out.println("snake had bag used on it");
            CompoundTag compound = itemstack.getTag();
            if (compound == null) {
                compound = new CompoundTag();
                itemstack.setTag(compound);
            }
            CompoundTag snakeTag = new CompoundTag();
            this.addAdditionalSaveData(snakeTag);
            this.addSpeciesSaveData(snakeTag);
            int numSnakesInSack = SnakeBagItem.getSnakesInStack(itemstack);
            if(numSnakesInSack > 0){
                return InteractionResult.FAIL;
            }
            int currentSnake = numSnakesInSack + 1;
            compound.put("Snake_" + currentSnake, snakeTag);
            //this.playSound(SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 1, 1);
            this.discard();
            player.swing(hand);
            //System.out.println("Bag should now have snake");
            //System.out.println(compound.getAllKeys().toString());
            return InteractionResult.SUCCESS;
        }
        if (this.isFood(itemstack) && this.getHealth() < this.getMaxHealth()) {
            this.heal((float)itemstack.getFoodProperties(this).getNutrition());
            if (!player.getAbilities().instabuild) {
                itemstack.shrink(1);
            }
            this.gameEvent(GameEvent.MOB_INTERACT, this.eyeBlockPosition());
            return InteractionResult.SUCCESS;
        }

        if (player.isSecondaryUseActive() && !this.isPassenger()) {
            if (player.getPassengers().size() < 1) {
                this.startRiding(player, true);
                player.displayClientMessage(new TranslatableComponent("component.snekcraft.drop_instructions", (KeyInit.shoulderKey.getKey().getDisplayName())), true);
                this.setSittingOnShoulder(true);
            }
            return InteractionResult.sidedSuccess(this.getLevel().isClientSide());
        }

        /**if(!level.isClientSide() && hand.equals(InteractionHand.MAIN_HAND) && itemstack.isEmpty()){
            ServerPlayer serverplayer = (ServerPlayer) player;
            boolean flag = serverplayer != null && !serverplayer.isSpectator() && !serverplayer.getAbilities().flying && !serverplayer.isInWater() && !serverplayer.isInPowderSnow;
            if (serverplayer.getPassengers().size() >= 3 || !flag || this.isPassenger()) {
                System.out.println("failed");
                return InteractionResult.FAIL;
            }
            //this.isSittingOnShoulder = this.setEntityOnShoulder((ServerPlayer) player);
            System.out.println("test");
            if(this.startRiding(player, false)){
                //player.displayClientMessage(Component.translatable("entity.rats.rat.dismount_instructions"), true);
                //player.startRiding(this);
                //player.displayClientMessage(new TextComponent(("picking up" + player.getPassengers().size() + "snakes")), false);
                serverplayer.displayClientMessage(new TranslatableComponent("component.snekcraft.drop_instructions"), true);
                System.out.println("Riding " + this.getVehicle().getName().getString() + " with " +
                        this.getVehicle().getPassengers().size() + " passengers");

            }else {
                System.out.println("false");
            }
            //this.getNavigation().stop();

            return InteractionResult.sidedSuccess(this.level.isClientSide);
        }*/

        return super.mobInteract(player, hand);
    }

    public void aiStep() {
        super.aiStep();
        if (!this.level.isClientSide && this.isAlive() && --this.shedTime <= 0) {
            this.spawnAtLocation(ItemInit.SNAKE_SKIN.get());
            if(this.random.nextInt(5) == 0){
                this.spawnAtLocation(ItemInit.SNAKE_TOOTH.get());
            }
            if(!this.isBaby()){
                this.shedTime = this.random.nextInt(24000) + 24000;
            }else{
                this.shedTime = this.random.nextInt(480000) + 48000; // adults dont shed as much
            }
        }

        /**if (this.isAlive() && this.isLayingEgg() && this.layEggCounter >= 1 && this.layEggCounter % 5 == 0) {
         BlockPos blockpos = this.blockPosition();
         if (TurtleEggBlock.onSand(this.level, blockpos)) {
         this.level.levelEvent(2001, blockpos, Block.getId(this.level.getBlockState(blockpos.below())));
         }
         }*/

    }

    public static boolean canSpawn(EntityType entity, LevelAccessor levelAccess, MobSpawnType spawnType, BlockPos pos, Random random){
        return checkSnakeSpawnRules(entity, levelAccess, spawnType, pos, random);
    }

    public static boolean checkSnakeSpawnRules(EntityType entity, LevelAccessor pLevel, MobSpawnType pSpawnType, BlockPos pPos, Random pRandom) {
        return (pLevel.getBlockState(pPos.below()).is(BlockTags.AZALEA_GROWS_ON)) && isBrightEnoughToSpawn(pLevel, pPos);
    }

    @javax.annotation.Nullable
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor pLevel, DifficultyInstance pDifficulty, MobSpawnType pReason, @javax.annotation.Nullable SpawnGroupData pSpawnData, @javax.annotation.Nullable CompoundTag pDataTag) {
        this.setColor(0);//this.getRandom().nextInt(5));
        this.setPattern(0);//this.getRandom().nextInt(4)); //not inclusive
        return super.finalizeSpawn(pLevel, pDifficulty, pReason, pSpawnData, pDataTag);
    }



    //TODO: figure out if this will work and spawn correct species
    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob otherParent) {
        SnakeBase snake = (SnakeBase) getType().create(level);
        if(snake == null){
            System.out.println("FAILED TO GET SNAKEBASE OFFSPRING, WHAT DID I DO WRONG IN MY CODE?");
            return null;
        }

        int p1Color = this.getColor();
        int p2Color = ((SnakeBase)otherParent).getColor();

        int p1Pattern = this.getPattern();
        int p2Pattern = ((SnakeBase)otherParent).getPattern();

        snake.setColor(getOffspringColor(p1Color, p2Color));
        snake.setPattern(getOffspringPattern(p1Pattern, p2Pattern));
        return snake;
    }

    public int getOffspringColor(int p1Color, int p2Color){
        return colorGenetics[p1Color][p2Color].GetGene(this.random.nextInt(BREEDING_RANGE));
    }
    public int getOffspringPattern(int p1Pattern, int p2Pattern){
        return patternGenetics[p1Pattern][p2Pattern].GetGene(this.random.nextInt(BREEDING_RANGE));
    }


    //TODO: Modify mobInteract

    /** For each child needs:
     *      - private static boolean isTemptingItem(ItemStack pStack)
     *      - @Nullable @Override public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob otherParent)
     *      - public static AttributeSupplier setAttributes()
     *      - public boolean isFood(ItemStack pStack) {return isTemptingItem(pStack);}
     *
     */

    @Override
    protected SoundEvent getDeathSound(){
        return SoundInit.SNEK_HURT.get();
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn){
        return SoundInit.SNEK_HURT.get();
    }

    // Data Savers
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(COLOR, 0);
        this.entityData.define(PATTERN, 0);
        this.entityData.define(HAS_EGG, false);
        this.entityData.define(LAYING_EGG, false);
        this.entityData.define(RESTING, false);
    }

    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putInt("Color", this.getColor());
        compound.putInt("Pattern", this.getPattern());
        compound.putInt("ShedTime", this.shedTime);
        compound.putBoolean("HasEgg", this.hasEgg());
        compound.putBoolean("Resting", this.isResting());
    }

    public abstract void addSpeciesSaveData(CompoundTag compound);

    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        this.setColor(compound.getInt("Color"));
        this.setPattern(compound.getInt("Pattern"));
        if (compound.contains("ShedTime")) {
            this.shedTime = compound.getInt("ShedTime");
        }
        this.setHasEgg(compound.getBoolean("HasEgg"));
        this.setResting(compound.getBoolean("Resting"));
    }

    @Override
    public void stopRiding(){
        this.setSittingOnShoulder(false);
        super.stopRiding();
    }


    //<editor-fold desc="Getters and Setters/ Data value managers">



    public abstract Block GetEggType(); //return BlockInit.SNAKE_EGG.get();

    public int getColor(){
        return this.entityData.get(COLOR);
    }

    public void setColor(int colorNum){
        this.entityData.set(COLOR, Integer.valueOf(colorNum));
    }

    public int getPattern(){
        return this.entityData.get(PATTERN);
    }

    public void setPattern(int patternNum){
        this.entityData.set(PATTERN, Integer.valueOf(patternNum));
    }

    public boolean isResting(){
        return this.entityData.get(RESTING);
    }
    public void setResting(boolean setRest){
        this.entityData.set(RESTING, setRest);
    }

    public boolean canFallInLove() {
        boolean loveSet = super.canFallInLove() && !this.hasEgg();
        return loveSet;
    }

    public boolean hasEgg() {
        return this.entityData.get(HAS_EGG);
    }

    void setHasEgg(boolean pHasEgg) {
        this.entityData.set(HAS_EGG, pHasEgg);
    }

    public boolean isLayingEgg() {
        return this.entityData.get(LAYING_EGG);
    }

    void setLayingEgg(boolean pIsLayingEgg) {
        this.layEggCounter = pIsLayingEgg ? 1 : 0;
        this.entityData.set(LAYING_EGG, pIsLayingEgg);
    }

    void setSittingOnShoulder(boolean shoulder){
        this.isSittingOnShoulder = shoulder;
        if(this.isSittingOnShoulder){
            setPose(Pose.CROUCHING);
        }else{
            setPose(Pose.STANDING);
        }
    }

    public boolean isSittingOnShoulder(){
        return this.isSittingOnShoulder;
    }

    //</editor-fold>


    //<editor-fold desc="Goals">

    static class SnakeBreedGoal extends BreedGoal {
        private final SnakeBase snake;

        public SnakeBreedGoal(SnakeBase pSnake, double pSpeedModifier) {
            super(pSnake, pSpeedModifier);
            this.snake = pSnake;
        }

        public boolean canUse() {
            return super.canUse() && !this.snake.hasEgg();
        }

        //paris

        protected void breed() {
            ServerPlayer serverplayer = this.animal.getLoveCause();
            if (serverplayer == null && this.partner.getLoveCause() != null) {
                serverplayer = this.partner.getLoveCause();
            }

            if (serverplayer != null) {
                serverplayer.awardStat(Stats.ANIMALS_BRED);
                CriteriaTriggers.BRED_ANIMALS.trigger(serverplayer, this.animal, this.partner, (AgeableMob)null);
            }
            this.snake.setHasEgg(true);
            this.animal.setAge(6000);
            this.partner.setAge(6000);
            this.animal.resetLove();
            this.partner.resetLove();
            ((SnakeBase)this.animal).partnerColor = ((SnakeBase)this.partner).getColor();
            ((SnakeBase)this.animal).partnerPattern = ((SnakeBase)this.partner).getPattern();
            Random random = this.animal.getRandom();
            if (this.level.getGameRules().getBoolean(GameRules.RULE_DOMOBLOOT)) {
                this.level.addFreshEntity(new ExperienceOrb(this.level, this.animal.getX(), this.animal.getY(), this.animal.getZ(), random.nextInt(7) + 1));
            }

        }
    }

    static class SnakeLayEggGoal extends MoveToBlockGoal {
        private final SnakeBase snake;

        SnakeLayEggGoal(SnakeBase pSnake, double pSpeedModifier) {
            super(pSnake, pSpeedModifier, 16);
            this.snake = pSnake;
        }

        public boolean canUse() {
            return this.snake.hasEgg() && super.canUse();
        }

        public boolean canContinueToUse() {
            return super.canContinueToUse() && this.snake.hasEgg();
        }


        public void tick() {
            super.tick();
            BlockPos blockpos = this.snake.blockPosition();
            if (!this.snake.isInWater() && this.isReachedTarget()) {
                if (this.snake.layEggCounter < 1) {
                    this.snake.setLayingEgg(true);
                } else if (this.snake.layEggCounter > this.adjustedTickDelay(200)) {
                    Level level = this.snake.level;
                    level.playSound((Player)null, blockpos, SoundInit.SNEK_LAY.get(), SoundSource.BLOCKS, 0.3F, 0.9F + level.random.nextFloat() * 0.2F);

                    CreateEgg2(level);
                    /**level.setBlock(this.blockPos.above(), BlockInit.SNAKE_EGG.get().defaultBlockState()
                            .setValue(SnakeEggBlock.EGGS, Integer.valueOf(this.snake.random.nextInt(4) + 1))
                            .setValue(SnakeEggBlock.COLOR, ((SnakeBase)this.mob).getColor())
                            .setValue(SnakeEggBlock.PATTERN, ((SnakeBase)this.mob).getPattern())
                            .setValue(SnakeEggBlock.COLOR_P2, ((SnakeBase)this.mob).partnerColor)
                            .setValue(SnakeEggBlock.PATTERN_P2, ((SnakeBase)this.mob).partnerPattern)
                            , 3);*/
                    //block.SetEggStats(((SnakeBase)this.mob).getColor(), ((SnakeBase)this.mob).partnerColor, ((SnakeBase)this.mob).getPattern(), ((SnakeBase)this.mob).partnerPattern);

                    //((SnakeEggBlock)(level.getBlockState(this.blockPos.above()).getBlock())).setParentTraits(((HognoseEntity)this.mob).getColor(), ((HognoseEntity)this.mob).getPattern(), ((HognoseEntity)this.mob).partnerColor, ((HognoseEntity)this.mob).partnerPattern, level, this.blockPos.above());
                    this.snake.setHasEgg(false);
                    this.snake.setLayingEgg(false);
                    //this.snake.setInLoveTime(600);
                }

                if (this.snake.isLayingEgg()) {
                    ++this.snake.layEggCounter;
                }
            }

        }

        /**public void CreateEgg(Level level){
            level.setBlock(this.blockPos.above(), this.snake.GetEggType().defaultBlockState()
                            .setValue(SnakeEggBlock.EGGS, Integer.valueOf(this.snake.random.nextInt(4) + 1))
                            .setValue(SnakeEggBlock.COLOR, ((SnakeBase)this.mob).getColor())
                            .setValue(SnakeEggBlock.PATTERN, ((SnakeBase)this.mob).getPattern())
                            .setValue(SnakeEggBlock.COLOR_P2, ((SnakeBase)this.mob).partnerColor)
                            .setValue(SnakeEggBlock.PATTERN_P2, ((SnakeBase)this.mob).partnerPattern)
                    , 3);

            //testing this

            CompoundTag compoundtag = level.getBlockEntity(this.blockPos.above()).getTileData();
            if(compoundtag != null){
                System.out.println("Compoundtag found");
            }else {
                System.out.println("Compoundtag not found");
            }



            /**PacketHandler.INSTANCE.sendToServer(new ServerboundEggUpdate(this.blockPos.above(),
                    ((SnakeBase)this.mob).getColor(), ((SnakeBase)this.mob).getPattern(),
                    ((SnakeBase)this.mob).partnerColor, ((SnakeBase)this.mob).partnerPattern));*/
            /**((SnakeEggBlockEntity)level.getBlockEntity(this.blockPos.above())).setCOLOR(((SnakeBase)this.mob).getColor());
            ((SnakeEggBlock)level.getBlockState(this.blockPos.above()).getBlock())
                    .setParentTraits(((SnakeBase)this.mob).getColor(), ((SnakeBase)this.mob).getPattern(),
                            ((SnakeBase)this.mob).partnerColor, ((SnakeBase)this.mob).partnerPattern, level, this.blockPos.above());*/
        //}

        public void CreateEgg2(Level level) {
            level.setBlock(this.blockPos.above(), this.snake.GetEggType().defaultBlockState(), 3);

            SnakeEggBlockEntity tile = (SnakeEggBlockEntity)level.getBlockEntity(this.blockPos.above());
            CompoundTag compoundtag = tile.getTileData();
            if (compoundtag != null) {
                System.out.println("Compoundtag found");
                compoundtag.putInt("color", ((SnakeBase)this.mob).getColor());
                compoundtag.putInt("color_p2", ((SnakeBase)this.mob).partnerColor);
                compoundtag.putInt("pattern", ((SnakeBase)this.mob).getPattern());
                compoundtag.putInt("pattern_p2", ((SnakeBase)this.mob).partnerPattern);

                tile.setStats(((SnakeBase)this.mob).getColor(),
                        ((SnakeBase)this.mob).partnerColor,
                        ((SnakeBase)this.mob).getPattern(),
                        ((SnakeBase)this.mob).partnerPattern);
            } else {
                System.out.println("Compoundtag not found");
            }

        }



            protected boolean isValidTarget(LevelReader level, BlockPos pPos) {
            if(level.isEmptyBlock(pPos.above())) {
                //Block blockType =  level.getBlockState(pPos).getBlock();
                BlockState state = level.getBlockState(pPos);
                return state.is(BlockTags.DIRT) || state.is(BlockTags.SAND);
                //return blockType == Blocks.GRASS_BLOCK || blockType == Blocks.SAND;
            }
            return false;
        }
    }


    class FindRestSpotGoal extends MoveToBlockGoal{

        private final SnakeBase snake;
        private static final int REST_CHANCE = 10;

        public FindRestSpotGoal(SnakeBase snake, double pSpeedModifier) {
            super(snake, pSpeedModifier, 8);
            this.snake = snake;
        }

        @Override
        public boolean canUse() {
            //System.out.println("seeing if can look for hide: "+ !this.snake.isResting());
            return !this.snake.isResting() && this.snake.getRandom().nextInt(REST_CHANCE) == 1 && super.canUse();
        }

        @Override
        public boolean canContinueToUse() {
            return !this.snake.isResting() && super.canContinueToUse();
        }

        @Override
        protected boolean isValidTarget(LevelReader level, BlockPos pPos) {

            Block block = level.getBlockState(pPos).getBlock();
            if (block instanceof CaveHideBlock || block instanceof TunnelHideBlock || block instanceof MediumHideBlock) { //should use tags instead
                //System.out.println("found hide");
                return true;
            }else if(!level.canSeeSky(pPos)){// && level.getBlockState(pPos.above()).getBlock() instanceof AirBlock){
                BlockPos abovePos = pPos.above();
                for(int i = 0; i<5; i++){
                    if(level.getBlockState(abovePos).getBlock() instanceof HeatLampBlock){
                        return true;
                    }
                    abovePos = abovePos.above();
                }

            }


            return false;
        }

        public void start() {
            //System.out.println("finding hide");
            super.start();
            this.snake.setResting(false);
        }

        /**
         * Reset the task's internal state. Called when this task is interrupted by another one
         */
        public void stop() {
            //System.out.println("bad stop");
            super.stop();
            //this.snake.setResting(false);
        }

        public void tick() {
            super.tick();
            if(this.isReachedTarget()){
                //System.out.println("reached target");
                this.snake.setResting(true);
                //System.out.println(this.snake.isResting());
            }
        }

        @Override
        public double acceptedDistance() {
            return 2.0D;
        }
    }

    class CurlGoal extends Goal{
        private static final int REST_TIME = reducedTickDelay(300);
        private int countdown;

        private final SnakeBase snake;

        public CurlGoal(SnakeBase snake){
            super();
            this.snake = snake;
            this.setFlags(EnumSet.of(Goal.Flag.JUMP, Goal.Flag.MOVE, Flag.LOOK));
        }

        @Override
        public boolean canUse() {
            //System.out.println("checking curl" + this.snake.isResting());
            return this.snake.isResting();
        }

        public boolean canContinueToUse() {
            return this.snake.isResting() && super.canContinueToUse();
        }

        @Override
        public void start() {
            //System.out.println("sitting");
            countdown = snake.getRandom().nextInt(REST_TIME) + 300;
            super.start();
        }

        public void stop() {
            super.stop();
            this.snake.setResting(false);
        }

        @Override
        public void tick() {
            super.tick();

            if (this.countdown > 0) {
                --this.countdown;
            } else {
                //System.out.println("getting up");
                this.snake.setResting(false);
            }

            /**BlockState blockState = snake.level.getBlockState(snake.blockPosition());
             Block block = snake.level.getBlockState(snake.blockPosition()).getBlock();
             if(!(block instanceof CaveHideBlock || block instanceof TunnelHideBlock)){
             System.out.println("getting up");
             this.snake.setResting(false);
             }*/

            /**Block block2 = snake.level.getBlockState(snake.blockPosition().above()).getBlock();
             if(!(block instanceof CaveHideBlock || block instanceof TunnelHideBlock)){
             System.out.println("getting up2");
             this.snake.setResting(false);
             }*/
        }
        /**public boolean requiresUpdateEveryTick() {
         return true;
         }*/
    }

    class SnakeWanderGoal extends WaterAvoidingRandomStrollGoal{

        public SnakeWanderGoal(PathfinderMob pMob, double pSpeedModifier) {
            super(pMob, pSpeedModifier);
        }

        @Override
        public boolean canUse() {
            return !((SnakeBase)this.mob).isResting() && super.canUse();
        }

        @Override
        public boolean canContinueToUse() {
            return !((SnakeBase)this.mob).isResting() && super.canContinueToUse();
        }
    }

    class SnakeShoulderGoal extends Goal{

        public SnakeShoulderGoal(SnakeBase snake){

            this.setFlags(EnumSet.of(Goal.Flag.JUMP, Goal.Flag.MOVE, Flag.LOOK));
        }

        @Override
        public boolean canUse() {

            return isSittingOnShoulder;
        }
        @Override
        public boolean canContinueToUse() {
            return super.canContinueToUse() && canUse();
        }

    }

    //</editor-fold>


    //<editor-fold desc="Animation & Positioning Stuff">

    // registerControllers and predicate are part of geckolib animations
    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        AnimationController<E> controller = event.getController();
        if(this.isSittingOnShoulder()){
            controller.markNeedsReload();
            controller.setAnimation(new AnimationBuilder().addAnimation("shoulder", ILoopType.EDefaultLoopTypes.LOOP));
        } else if(this.isResting()){
            controller.markNeedsReload();
            controller.setAnimation(new AnimationBuilder().addAnimation("hide", ILoopType.EDefaultLoopTypes.HOLD_ON_LAST_FRAME));
        }else if(this.swinging){ // is it attacking?
            this.playSound(SoundInit.SNEK_HURT.get(), 1.0F, 1f);
            controller.markNeedsReload();
            controller.setAnimation(new AnimationBuilder().addAnimation("strike", ILoopType.EDefaultLoopTypes.PLAY_ONCE));
            this.swinging = false;
        }else if(controller.getCurrentAnimation() == null || !(controller.getCurrentAnimation().animationName.equals("strike")
                && controller.getAnimationState().equals(AnimationState.Running))){ //is attack animation done?
            if (event.isMoving()) {
                controller.setAnimation(new AnimationBuilder().addAnimation("slither", ILoopType.EDefaultLoopTypes.LOOP));
                return PlayState.CONTINUE;
            }else{
                controller.setAnimation(new AnimationBuilder().addAnimation("idle", ILoopType.EDefaultLoopTypes.LOOP));
            }
        }
        return PlayState.CONTINUE;
    }

    private <E extends IAnimatable> PlayState blelelePredicate(AnimationEvent<E> event) {
        if(--this.bleleleTime <= 0 ) {
            event.getController().markNeedsReload(); //means that animation can go again
            event.getController().setAnimation(new AnimationBuilder().addAnimation("blelele", ILoopType.EDefaultLoopTypes.PLAY_ONCE));
            this.bleleleTime = this.random.nextInt(500) + 1000;
        }
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController(this, "controller",
                0, this::predicate));
        data.addAnimationController(new AnimationController(this, "attackController",
                0, this::blelelePredicate));
    }

    @Override
    public void rideTick() {
        super.rideTick();
        if (this.getVehicle() instanceof Player player) {
            this.updateRiding(player);
        }
    }

    public void updateRiding(Player riding) { //shoulder riding position
        int i = riding.getPassengers().indexOf(this);
        float radius = (i == 0 ? 0F : 0.4F) + (riding.isFallFlying() ? 2 : 0);
        float angle = (0.01745329251F * riding.yBodyRot + (i == 2 ? -92.5F : i == 1 ? 92.5F : 0));
        double extraX = (radius) * Mth.sin((float) (Math.PI + angle));
        double extraZ = (radius) * Mth.cos(angle);
        double extraY = (riding.isCrouching() ? 1.1D : 1.4D);
        Vec3 vec3 = (new Vec3((double)-0.2f, 0.0D, 0.0D)).yRot(-riding.yBodyRot * ((float)Math.PI / 180F) - ((float)Math.PI / 2F));
        this.setYRot(riding.yHeadRot);
        this.yHeadRot = riding.yHeadRot;
        this.yRotO = riding.yHeadRot;
        this.setPos(riding.getX() + extraX + vec3.x, riding.getY() + extraY, riding.getZ() + extraZ + vec3.z);
    }
    //</editor-fold>

    @Override
    @NotNull
    public  EntityDimensions getDimensions(Pose pPose) {
        if(pPose == Pose.CROUCHING){
            return EntityDimensions.scalable(0.1f, 0.1f);
        }else {
            return this.size;
        }
    }


}
