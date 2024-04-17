package com.raspix.snekcraft.packets;

import com.raspix.snekcraft.entity.generics.SnakeBase;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class ServerboundShoulderUpdate {

    public final int snekId;

    public ServerboundShoulderUpdate(int snekId){
        this.snekId = snekId;
    }

    public static ServerboundShoulderUpdate decode(FriendlyByteBuf buf) {
        return new ServerboundShoulderUpdate(buf.readInt());
    }

    public static void encode(ServerboundShoulderUpdate packet, FriendlyByteBuf buf) {
        buf.writeInt(packet.snekId);
    }

    public static class Handler {
        public static void handle(ServerboundShoulderUpdate packet, Supplier<NetworkEvent.Context> context) {
            context.get().enqueueWork(() -> {
                Player player = context.get().getSender();
                if (player != null) {
                    Entity entity = player.getLevel().getEntity(packet.snekId);
                    if (entity instanceof SnakeBase snek) {
                        snek.stopRiding();
                        Vec3 dismountPos = snek.getDismountLocationForPassenger(player);
                        snek.setPos(dismountPos.x(), dismountPos.y(), dismountPos.z());
                    }
                }
            });
            context.get().setPacketHandled(true);
        }
    }
}
