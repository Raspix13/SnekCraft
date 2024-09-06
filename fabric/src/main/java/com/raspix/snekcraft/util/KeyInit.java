package com.raspix.snekcraft.util;

import org.lwjgl.glfw.GLFW;

import com.raspix.snekcraft.SnekCraft;

import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;

public class KeyInit {
	private static final String CATEGORY = "key.categories." + SnekCraft.MOD_ID;
	
	public static KeyBinding shoulderKey = new KeyBinding("key." + SnekCraft.MOD_ID + ".drop_shoulder", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_X, CATEGORY);
	
	public static void init() {
		KeyBindingHelper.registerKeyBinding(shoulderKey);
	}
}
