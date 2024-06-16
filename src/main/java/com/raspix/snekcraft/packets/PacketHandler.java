package com.raspix.snekcraft.packets;

import com.raspix.snekcraft.SnekCraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

public final class PacketHandler {

    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(new ResourceLocation(SnekCraft.MOD_ID, "main"),
            () -> PROTOCOL_VERSION, PROTOCOL_VERSION::equals, PROTOCOL_VERSION::equals);

    private PacketHandler(){

    }

    public static void init(){
        int index = 0;
        INSTANCE.messageBuilder(ServerboundShoulderUpdate.class, index++, NetworkDirection.PLAY_TO_SERVER)
                .encoder(ServerboundShoulderUpdate::encode)
                .decoder(ServerboundShoulderUpdate::decode)
                .consumerNetworkThread(ServerboundShoulderUpdate.Handler::handle)
                .add();
        /**INSTANCE.messageBuilder(ServerboundEggUpdate.class, index++, NetworkDirection.PLAY_TO_SERVER)
                .encoder(ServerboundEggUpdate::encode)
                .decoder(ServerboundEggUpdate::decode)
                .consumer(ServerboundEggUpdate.Handler::handle)
                .add();*/
    }
}
