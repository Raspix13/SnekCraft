package com.raspix.snekcraft.sounds;

import com.raspix.snekcraft.SnekCraft;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class SoundInit {
	public static SoundEvent SNEK_HURT = SoundEvent.of(Identifier.of(SnekCraft.MOD_ID, "snek_hurt"));
	public static SoundEvent SNEK_ATTACK = SoundEvent.of(Identifier.of(SnekCraft.MOD_ID, "snek_attack"));
	//public static SoundEvent SNEK_LAY = SoundEvent.of(new Identifier("entity.turtle.lay_egg"));
	
	public static void init() {
		Registry.register(Registries.SOUND_EVENT, SNEK_HURT.getId(), SNEK_HURT);
		Registry.register(Registries.SOUND_EVENT, SNEK_ATTACK.getId(), SNEK_ATTACK);
		//Registry.register(Registries.SOUND_EVENT, SNEK_LAY.getId(), SNEK_LAY);
	}
}
