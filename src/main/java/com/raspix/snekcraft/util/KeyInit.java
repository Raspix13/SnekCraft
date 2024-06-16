package com.raspix.snekcraft.util;

import com.mojang.blaze3d.platform.InputConstants;
import com.raspix.snekcraft.SnekCraft;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.settings.KeyConflictContext;

public final class KeyInit {

    private KeyInit(){

    }

    /**public static KeyMapping shoulderKey;

    public static void init(){
        shoulderKey = registerKey("drop_shoulder", KeyMapping.CATEGORY_GAMEPLAY, InputConstants.KEY_X);
    }



    private static KeyMapping registerKey(String name, String category, int keycode){
        final KeyMapping key = new KeyMapping("key." + SnekCraft.MOD_ID + "." + name, keycode, category);
        //ClientRegistry.registerKeyBinding(key);
        return key;
    }*/

     /**public static void init(){
        shoulderKey = registerKey("drop_shoulder", KeyMapping.CATEGORY_GAMEPLAY, InputConstants.KEY_X);
     }*/

     private static  final String CATEGORY = "key.categories." + SnekCraft.MOD_ID;
    public static KeyMapping shoulderKey = new KeyMapping("key." + SnekCraft.MOD_ID + "." + "drop_shoulder",
             KeyConflictContext.IN_GAME, InputConstants.getKey(InputConstants.KEY_X, -1),
             CATEGORY);

}
