package com.raspix.snekcraft.packet;

import com.raspix.snekcraft.SnekCraft;
import com.raspix.snekcraft.entity.generics.SnakeBase;

import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;

public class ServerboundShoulderUpdate {
	public static final Identifier PACKET_ID = Identifier.of(SnekCraft.MOD_ID, "shoulder_update");
	
	public static void handle(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender) {
		if(player != null) {
			int snekId = buf.readInt();
			Entity snekEntity = player.getWorld().getEntityById(snekId);
			if(snekEntity instanceof SnakeBase snek) {
				execute(player, snek);
			}
		}
	}
	
	public static void execute(PlayerEntity player, SnakeBase snekEntity) {
		snekEntity.stopRiding();
		Vec3d dismountPos = snekEntity.updatePassengerForDismount(player);
		snekEntity.setPosition(dismountPos.getX(), dismountPos.getY(), dismountPos.getZ());
	}
}
