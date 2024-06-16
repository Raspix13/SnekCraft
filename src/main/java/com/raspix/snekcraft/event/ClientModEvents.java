package com.raspix.snekcraft.event;


import com.raspix.snekcraft.SnekCraft;
import com.raspix.snekcraft.util.KeyInit;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = SnekCraft.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientModEvents {

    private ClientModEvents(){

    }

    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent event){
        //KeyInit.init();
    }



}
