package com.raspix.snekcraft.entity.generics;

import java.util.Random;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.raspix.snekcraft.SnekCraft;
import com.raspix.snekcraft.entity.goal.CurlGoal;
import com.raspix.snekcraft.entity.goal.FindRestSpotGoal;
import com.raspix.snekcraft.entity.goal.SnakeBreedGoal;
import com.raspix.snekcraft.entity.goal.SnakeLayEggGoal;
import com.raspix.snekcraft.items.ItemInit;
import com.raspix.snekcraft.items.SnakeBagItem;
import com.raspix.snekcraft.sounds.SoundInit;
import com.raspix.snekcraft.util.KeyInit;

import net.minecraft.block.Block;
import net.minecraft.entity.AnimationState;
import net.minecraft.entity.EntityData;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.EscapeDangerGoal;
import net.minecraft.entity.ai.goal.LookAroundGoal;
import net.minecraft.entity.ai.goal.LookAtEntityGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.WanderAroundFarGoal;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.effect.StatusEffectUtil;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.packet.s2c.play.EntityAnimationS2CPacket;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.world.ServerChunkManager;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.event.GameEvent;

public abstract class SnakeBase extends AnimalEntity {
	public final AnimationState slitherAnimationState = new AnimationState();
	public final AnimationState idleAnimationState = new AnimationState();
	public final AnimationState bleleleAnimationState = new AnimationState();
	public final AnimationState strikeAnimationState = new AnimationState();
	public final AnimationState hideAnimationState = new AnimationState();
	public final AnimationState shoulderAnimationState = new AnimationState();
	
	public static final String NBT_KEY_COLOR = "Color";
	public static final String NBT_KEY_PATTERN = "Pattern";
	public static final String NBT_KEY_SHED_TIME = "ShedTime";
	public static final String NBT_KEY_HAS_EGG = "HasEgg";
	public static final String NBT_KEY_RESTING = "Resting";
	
	//private static final Ingredient FOOD_ITEMS = Ingredient.ofItems(Items.CHICKEN, Items.EGG, Items.RABBIT);
	private static final TrackedData<Integer> COLOR = DataTracker.registerData(SnakeBase.class, TrackedDataHandlerRegistry.INTEGER);
	private static final TrackedData<Integer> PATTERN = DataTracker.registerData(SnakeBase.class, TrackedDataHandlerRegistry.INTEGER);
	private static final TrackedData<Boolean> RESTING = DataTracker.registerData(SnakeBase.class, TrackedDataHandlerRegistry.BOOLEAN);
	private static final TrackedData<Boolean> HAS_EGG = DataTracker.registerData(SnakeBase.class, TrackedDataHandlerRegistry.BOOLEAN);
	private static final TrackedData<Boolean> LAYING_EGG = DataTracker.registerData(SnakeBase.class, TrackedDataHandlerRegistry.BOOLEAN);
	
	public static final int BREEDING_RANGE = 100;
	
	public int layEggCounter;
	public int partnerColor = 0;
	public int partnerPattern = 0;
	public int shedTime = random.nextInt(24000) + 12000;
	public int bleleleTime = random.nextInt(500) + 500;
	public boolean isSittingOnShoulder;
	
	private final EntityDimensions size;
	
	private static GenePool[][] colorGenetics;
	private static GenePool[][] patternGenetics;
	
	protected SnakeBase(EntityType<? extends AnimalEntity> entityType, World world, EntityDimensions size) {
		super(entityType, world);
		this.size = size;
	}
	
	@Override
	public AttributeContainer getAttributes() {
		return super.getAttributes();
	}
	
	@Override
	protected void initGoals() {
		goalSelector.add(0, new SwimGoal(this));
		goalSelector.add(1, new EscapeDangerGoal(this, 1.25));
		goalSelector.add(1, new SnakeBreedGoal(this, 1.0));
        goalSelector.add(1, new SnakeLayEggGoal(this, 1.0));
        goalSelector.add(5, new MeleeAttackGoal(this, 1.0, true));
        goalSelector.add(6, new FindRestSpotGoal(this, 1.0));
        goalSelector.add(7, new CurlGoal(this));
        goalSelector.add(8, new WanderAroundFarGoal(this, 1.0));
        goalSelector.add(9, new LookAtEntityGoal(this, PlayerEntity.class, 6.0f));
        goalSelector.add(10, new LookAroundGoal(this));
	}
	
	public static DefaultAttributeContainer.Builder createLivingAttributes(){
		return MobEntity.createMobAttributes()
				.add(EntityAttributes.GENERIC_MAX_HEALTH, 20f)
				.add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 3f)
				.add(EntityAttributes.GENERIC_ATTACK_SPEED, 1.0f)
				.add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.25f);
	}
	
	public ActionResult interactMob(PlayerEntity player, Hand hand) {
		ItemStack itemStack = player.getStackInHand(hand);
		
		if(itemStack.getItem() == ItemInit.SNAKE_BAG) {
			NbtCompound compound = itemStack.getNbt();
			if(compound == null) {
				compound = new NbtCompound();
				itemStack.setNbt(compound);
			}
			NbtCompound snakeNbt = new NbtCompound();
			writeCustomDataToNbt(snakeNbt);
			addSpeciesSaveData(snakeNbt);
			int numSnakesInSack = SnakeBagItem.getSnakesInStack(itemStack);
			if(numSnakesInSack > 0) {
				return ActionResult.FAIL;
			}
			int currentSnake = numSnakesInSack + 1;
			compound.put("Snake_" + currentSnake, snakeNbt);
			discard();
			player.swingHand(hand);
			return ActionResult.SUCCESS;
		}
		
		if(isBreedingItem(itemStack) && getHealth() < getMaxHealth()) {
			heal((float) itemStack.getItem().getFoodComponent().getHunger());
			if(!player.getAbilities().creativeMode) {
				itemStack.decrement(1);
			}
			emitGameEvent(GameEvent.EAT, this);
			return ActionResult.SUCCESS;
		}
		
		if(player.isSneaking() && !hasVehicle()) {
			if(player.getPassengerList().size() < 1) {
				startRiding(player, true);
				player.sendMessage(Text.translatable("component.snekcraft.drop_instructions", (KeyInit.shoulderKey.getBoundKeyLocalizedText())), true);
				setSittingOnShoulder(true);
			}
			return ActionResult.success(getWorld().isClient());
		}
		
		return super.interactMob(player, hand);
	}
	
	@Override
	public void tickMovement() {
		super.tickMovement();
		tickHandSwing();
		if(!getWorld().isClient() && isAlive() && --shedTime <= 0) {
			dropItem(ItemInit.SNAKE_SKIN);
			if(random.nextInt(5) == 0) {
				dropItem(ItemInit.SNAKE_TOOTH);
			}
			if(!isBaby()) {
				shedTime = random.nextInt(24000) + 24000;
			}else {
				shedTime = this.random.nextInt(480000) + 48000;
			}
		}
	}
	
	// Does nothing? Not on Forge either (not overridden)
	public static boolean canMobSpawn(EntityType<? extends MobEntity> entityType, WorldAccess worldAccess, SpawnReason spawnReason, BlockPos position, Random random) {
		return (worldAccess.getBlockState(position.down()).isIn(BlockTags.AZALEA_GROWS_ON) && isLightLevelValidForNaturalSpawn(worldAccess, position));
	}
	
	@Nullable
	@Override
	public EntityData initialize(ServerWorldAccess worldAccess, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData, @Nullable NbtCompound nbt) {
		setColor(0);
		setPattern(0);
		return super.initialize(worldAccess, difficulty, spawnReason, entityData, nbt);
	}
	
	@Nullable
	@Override
	public PassiveEntity createChild(ServerWorld world, PassiveEntity otherEntity) {
		SnakeBase snake = (SnakeBase) getType().create(world);
		if(snake == null) {
			SnekCraft.logger.error("FAILED TO GET SNAKEBASE OFFSPRING, WHAT DID I DO WRONG IN MY CODE?");
			return null;
		}
		
		int p1Color = getColor();
		int p2Color  = ((SnakeBase) otherEntity).getColor();
		
		int p1Pattern = getPattern();
		int p2Pattern = ((SnakeBase) otherEntity).getPattern();
		
		snake.setColor(getOffspringColor(p1Color, p2Color));
		snake.setPattern(getOffspringPattern(p1Pattern, p2Pattern));
		return snake;
	}
	
	public int getOffspringColor(int p1Color, int p2Color) {
		return colorGenetics[p1Color][p2Color].getGene(random.nextInt(BREEDING_RANGE));
	}
	
	public int getOffspringPattern(int p1Pattern, int p2Pattern) {
		return patternGenetics[p1Pattern][p2Pattern].getGene(random.nextInt(BREEDING_RANGE));
	}
	
	@Override
	protected SoundEvent getDeathSound() {
		return SoundInit.SNEK_HURT;
	}
	
	@Override
	protected SoundEvent getHurtSound(DamageSource damageSource) {
		return SoundInit.SNEK_HURT;
	}
	
	@Override
	protected void initDataTracker() {
		super.initDataTracker();
		dataTracker.startTracking(COLOR, 0);
		dataTracker.startTracking(PATTERN, 0);
		dataTracker.startTracking(HAS_EGG, false);
		dataTracker.startTracking(LAYING_EGG, false);
		dataTracker.startTracking(RESTING, false);
	}
	
	public void writeCustomDataToNbt(NbtCompound compound) {
		super.writeCustomDataToNbt(compound);
		compound.putInt(NBT_KEY_COLOR, getColor());
		compound.putInt(NBT_KEY_PATTERN, getPattern());
		compound.putInt(NBT_KEY_SHED_TIME, shedTime);
		compound.putBoolean(NBT_KEY_HAS_EGG, hasEgg());
		compound.putBoolean(NBT_KEY_RESTING, isResting());
	}
	
	public abstract void addSpeciesSaveData(NbtCompound compound);
	
	@Override
	public void readCustomDataFromNbt(NbtCompound compound) {
		super.readCustomDataFromNbt(compound);
		setColor(compound.getInt(NBT_KEY_COLOR));
		setPattern(compound.getInt(NBT_KEY_PATTERN));
		if(compound.contains(NBT_KEY_SHED_TIME)) {
			shedTime = compound.getInt(NBT_KEY_SHED_TIME);
		}
		setHasEgg(compound.getBoolean(NBT_KEY_HAS_EGG));
		setResting(compound.getBoolean(NBT_KEY_RESTING));
	}
	
	@Override
	public void stopRiding() {
		setSittingOnShoulder(false);
		super.stopRiding();
	}
	
	public abstract Block getEggType();
	
	public int getColor() {
		return dataTracker.get(COLOR);
	}
	
	public void setColor(int color) {
		dataTracker.set(COLOR, Integer.valueOf(color));
	}
	
	public int getPattern() {
		return dataTracker.get(PATTERN);
	}
	
	public void setPattern(int pattern) {
		dataTracker.set(PATTERN, Integer.valueOf(pattern));
	}
	
	public boolean isResting() {
		return dataTracker.get(RESTING);
	}
	
	public void setResting(boolean resting) {
		dataTracker.set(RESTING, resting);
	}
	
	@Override
	public boolean canEat() {
		return super.canEat() && !hasEgg();
	}
	
	public boolean hasEgg() {
		return dataTracker.get(HAS_EGG);
	}
	
	public void setHasEgg(boolean hasEgg) {
		dataTracker.set(HAS_EGG, hasEgg);
	}
	
	public boolean isLayingEgg() {
		return dataTracker.get(LAYING_EGG);
	}
	
	public void setLayingEgg(boolean isLayingEgg) {
		layEggCounter = isLayingEgg ? 1 : 0;
		dataTracker.set(LAYING_EGG, isLayingEgg);
	}
	
	public void setSittingOnShoulder(boolean isSittingOnShoulder) {
		this.isSittingOnShoulder = isSittingOnShoulder;
		if(isSittingOnShoulder) {
			setPose(EntityPose.CROUCHING);
		}else {
			setPose(EntityPose.STANDING);
		}
	}
	
	public boolean isSittingOnShoulder() {
		return isSittingOnShoulder;
	}
	
	@Override
	public void tickRiding() {
		super.tickRiding();
		if(getVehicle() instanceof PlayerEntity player) {
			updateRiding(player);
		}
	}
	
	public void updateRiding(PlayerEntity player) {
		int i = player.getPassengerList().indexOf(this);
		float radius = (i == 0 ? 0f : 0.4f) + (player.isFallFlying() ? 2 : 0);
		float angle = (01745329251f * player.bodyYaw + (i == 2 ? -92.5f : i == 1 ? 92.5f : 0));
		double extraX = (radius) * Math.sin((float) (Math.PI + angle));
        double extraY = (player.isInSneakingPose() ? 1.1d : 1.4d);
        double extraZ = (radius) * Math.cos(angle);
        Vec3d vec3 = new Vec3d(-0.2d, 0.0d, 0.0d).rotateY(-player.bodyYaw * ((float) Math.PI / 180f) - ((float) Math.PI / 2f));
        setBodyYaw(player.headYaw);
        headYaw = player.headYaw;
        prevBodyYaw = player.headYaw;
        setPos(player.getX() + extraX + vec3.x, player.getY() + extraY, player.getZ() + extraZ + vec3.z);
	}
	
	@Override
	@NotNull
	public EntityDimensions getDimensions(EntityPose pose) {
		if(pose == EntityPose.CROUCHING) {
			return EntityDimensions.changing(0.1f, 0.1f);
		}
		return size;
	}
	
	@Override
	public void tick() {
		if(getWorld().isClient()) {
			if(isSittingOnShoulder) {
				shoulderAnimationState.start(age);
			}else {
				shoulderAnimationState.stop();
			}
			
			if(isResting() && !isSittingOnShoulder()) {
				hideAnimationState.start(age);
			}else {
				hideAnimationState.stop();
			}
			
			if(bleleleTime <= 0) {
				bleleleTime = random.nextInt(500) + 500;
				bleleleAnimationState.start(age);
			}else {
				--bleleleTime;
			}
			
			if(handSwinging) {
				playSound(SoundInit.SNEK_HURT, 1.0f, 1f);
				strikeAnimationState.startIfNotRunning(age);
			}else {
				strikeAnimationState.stop();
			}
			
			slitherAnimationState.setRunning(!isResting() && !isSittingOnShoulder() && limbAnimator.isLimbMoving(), age);
			idleAnimationState.setRunning(!isResting() && !isSittingOnShoulder() && strikeAnimationState.isRunning(), age);
		}
		super.tick();
	}
	
	private int getCurAdditionalSwingDur() {
		if(StatusEffectUtil.hasHaste(this)) {
			return 6 - (1 + StatusEffectUtil.getHasteAmplifier(this));
		}else {
			return hasStatusEffect(StatusEffects.MINING_FATIGUE) ? 6 + (1 + getStatusEffect(StatusEffects.MINING_FATIGUE).getAmplifier()) * 2 : 6;
		}
	}
	
	@Override
	public void swingHand(Hand hand) {
		this.swingHand(hand, false);
	}
	
	@Override
	public void swingHand(Hand hand, boolean updateSelf) {
		ItemStack itemStack = getStackInHand(hand);
		if(!itemStack.isEmpty() || handSwingTicks >= getCurAdditionalSwingDur() / 2 || handSwingTicks < 0) {
			handSwingTicks = -1;
			handSwinging = true;
			preferredHand = hand;
			if(getWorld() instanceof ServerWorld serverWorld) {
				EntityAnimationS2CPacket entityAnimationPacket = new EntityAnimationS2CPacket(this, hand == Hand.MAIN_HAND ? 0 : 3);
				ServerChunkManager serverChunkManager = serverWorld.getChunkManager();
				if(updateSelf) {
					serverChunkManager.sendToNearbyPlayers(this, entityAnimationPacket);
				}else {
					serverChunkManager.sendToOtherNearbyPlayers(this, entityAnimationPacket);
				}
			}
		}
	}
	
	public abstract String getSpeciesName(int color, int pattern);
}
