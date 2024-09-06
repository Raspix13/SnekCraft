package com.raspix.snekcraft.event;

import com.raspix.snekcraft.entity.generics.SnakeBase;
import com.raspix.snekcraft.packet.ServerboundShoulderUpdate;
import com.raspix.snekcraft.util.KeyInit;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.network.PacketByteBuf;

public class ClientEvents {
	
	
	public static void init() {
		ClientTickEvents.END_CLIENT_TICK.register(ClientEvents::onClientEndTick);
	}
	
	public static void onClientEndTick(MinecraftClient client) {
		ClientPlayerEntity player = client.player;
		if(KeyInit.shoulderKey.wasPressed() && player.getWorld().isClient() && !player.getPassengerList().isEmpty()) {
			for(Entity passenger: player.getPassengerList()) {
				if(passenger instanceof SnakeBase snek) {
					PacketByteBuf buf = PacketByteBufs.create();
					buf.writeInt(snek.getId());
					ClientPlayNetworking.send(ServerboundShoulderUpdate.PACKET_ID, buf);
					ServerboundShoulderUpdate.execute(player, snek);
				}
			}
		}
	}
}
