package com.raspix.snekcraft.sounds;

import com.raspix.snekcraft.SnekCraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class SoundInit {

    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, SnekCraft.MOD_ID);

    public static RegistryObject<SoundEvent> SNEK_HURT = SOUNDS.register("snek_hurt", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(SnekCraft.MOD_ID, "snek_hurt")));

    public static RegistryObject<SoundEvent> SNEK_ATTACK = SOUNDS.register("snek_attack", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(SnekCraft.MOD_ID, "snek_attack")));
    public static RegistryObject<SoundEvent> SNEK_LAY = SOUNDS.register("snek_lay", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("entity.turtle.lay_egg")));
}
