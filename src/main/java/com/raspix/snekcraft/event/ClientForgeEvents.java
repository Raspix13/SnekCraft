package com.raspix.snekcraft.event;

import com.raspix.snekcraft.SnekCraft;
import com.raspix.snekcraft.entity.generics.SnakeBase;
import com.raspix.snekcraft.packets.PacketHandler;
import com.raspix.snekcraft.packets.ServerboundShoulderUpdate;
import com.raspix.snekcraft.util.KeyInit;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = SnekCraft.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ClientForgeEvents {

    private ClientForgeEvents(){

    }

    @SubscribeEvent
    public static void clientTick(TickEvent.ClientTickEvent event){
        Player player = Minecraft.getInstance().player;
        if(KeyInit.shoulderKey.isDown()){
            if(player.level().isClientSide() && !player.getPassengers().isEmpty()){
                for (Entity passenger : player.getPassengers()) {
                    if (passenger instanceof SnakeBase) {
                        PacketHandler.INSTANCE.sendToServer(new ServerboundShoulderUpdate(passenger.getId()));
                        passenger.stopRiding();
                        Vec3 dismountPos = passenger.getDismountLocationForPassenger(player);
                        passenger.setPos(dismountPos.x(), dismountPos.y(), dismountPos.z());
                    }
                }
            }

        }
    }

}
