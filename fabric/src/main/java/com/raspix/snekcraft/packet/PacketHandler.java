package com.raspix.snekcraft.packet;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;

public class PacketHandler {
	
	
	public static void init() {
		ServerPlayNetworking.registerGlobalReceiver(ServerboundShoulderUpdate.PACKET_ID, ServerboundShoulderUpdate::handle);
		//ServerPlayNetworking.registerGlobalReceiver(ServerboundEggUpdate.PACKET_ID, ServerboundEggUpdate::handle);
	}
}
