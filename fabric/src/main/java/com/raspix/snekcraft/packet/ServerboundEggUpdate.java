package com.raspix.snekcraft.packet;

import com.raspix.snekcraft.SnekCraft;
import com.raspix.snekcraft.blocks.entity.SnakeEggBlockEntity;

import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

public class ServerboundEggUpdate {
	public static final Identifier PACKET_ID = Identifier.of(SnekCraft.MOD_ID, "egg_update");
	
	public static void handle(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender) {
		if(player != null) {
			BlockPos pos = buf.readBlockPos();
			int color = buf.readInt();
			int color_p2 = buf.readInt();
			int pattern = buf.readInt();
			int pattern_p2 = buf.readInt();
			SnakeEggBlockEntity egg = ((SnakeEggBlockEntity) player.getWorld().getBlockEntity(pos));
			egg.setStats(color, color_p2, pattern, pattern_p2);
		}
	}
}
