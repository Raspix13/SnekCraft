package com.raspix.snekcraft.event;

import com.raspix.snekcraft.SnekCraft;
import com.raspix.snekcraft.entity.generics.SnakeBase;
import com.raspix.snekcraft.packets.PacketHandler;
import com.raspix.snekcraft.packets.ServerboundShoulderUpdate;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = SnekCraft.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ForgeEvents {

    /**@SubscribeEvent
    public static void onPlayerLeftClick(PlayerInteractEvent.LeftClickEmpty event) {

        if (event.getPlayer().getLevel().isClientSide()) {
            Entity player = event.getEntity();
            System.out.println("Hey there");
            System.out.println("There are " + player.getPassengers().size() + " passengers on " + player.getName().getString());
            if (event.getEntity().isShiftKeyDown() && !event.getEntity().getPassengers().isEmpty()) {
                System.out.println("Hey there 2");
                for (Entity passenger : event.getEntity().getPassengers()) {
                    if (passenger instanceof SnakeBase) {
                        passenger.stopRiding();
                        Vec3 dismountPos = passenger.getDismountLocationForPassenger((LivingEntity) event.getEntity());
                        passenger.setPos(dismountPos.x(), dismountPos.y(), dismountPos.z());
                        PacketHandler.INSTANCE.sendToServer(new ServerboundShoulderUpdate(passenger.getId()));
                        //RatsNetworkHandler.CHANNEL.sendToServer(new DismountRatPacket(passenger.getId()));
                    }
                }
            }
            //handleArmSwing(event.getItemStack(), event.getEntity());
            //RatsNetworkHandler.CHANNEL.sendToServer(new SyncArmSwingPacket(event.getItemStack()));
        }
    }*/
}
