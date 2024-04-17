package com.raspix.snekcraft.packets;

import com.raspix.snekcraft.blocks.entity.SnakeEggBlockEntity;
import com.raspix.snekcraft.entity.generics.SnakeBase;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class ServerboundEggUpdate {

    private final BlockPos pos;
    private final int color;
    private final int color_p2;
    private final int pattern;
    private final int pattern_p2;


    public ServerboundEggUpdate(BlockPos pos, int color, int color_p2, int pattern, int pattern_p2){
        this.pos = pos;
        this.color = color;
        this.color_p2 = color_p2;
        this.pattern = pattern;
        this.pattern_p2 = pattern_p2;
    }

    public static ServerboundEggUpdate decode(FriendlyByteBuf buf) {
        return new ServerboundEggUpdate(buf.readBlockPos(), buf.readInt(),  buf.readInt(), buf.readInt(), buf.readInt());
    }

    public static void encode(ServerboundEggUpdate packet, FriendlyByteBuf buf) {
        buf.writeBlockPos(packet.pos);
        buf.writeInt(packet.color);
        buf.writeInt(packet.color_p2);
        buf.writeInt(packet.pattern);
        buf.writeInt(packet.pattern_p2);
    }

    public static class Handler {
        public static void handle(ServerboundEggUpdate packet, Supplier<NetworkEvent.Context> context) {
            context.get().enqueueWork(() -> {
                Player player = context.get().getSender();
                if (player != null) {
                    /**Entity entity = player.getLevel().getEntity(packet.snekId);
                    if (entity instanceof SnakeBase snek) {
                        snek.stopRiding();
                        Vec3 dismountPos = snek.getDismountLocationForPassenger(player);
                        snek.setPos(dismountPos.x(), dismountPos.y(), dismountPos.z());
                    }*/
                    SnakeEggBlockEntity egg = ((SnakeEggBlockEntity)player.getLevel().getBlockEntity(packet.pos));
                    egg.setStats(packet.color, packet.color_p2, packet.pattern, packet.pattern_p2);


                }


            });
            context.get().setPacketHandled(true);
        }
    }
}
