package com.raspix.snekcraft.event;

import com.raspix.snekcraft.SnekCraft;
import com.raspix.snekcraft.entity.generics.SnakeBase;
import com.raspix.snekcraft.packets.PacketHandler;
import com.raspix.snekcraft.packets.ServerboundShoulderUpdate;
import com.raspix.snekcraft.util.KeyInit;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.TextComponent;
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
            //System.out.println("There are " + player.getPassengers().size() + " passengers");
            if(player.level.isClientSide() && !player.getPassengers().isEmpty()){
                //System.out.println("cool test 1");
                for (Entity passenger : player.getPassengers()) {
                    if (passenger instanceof SnakeBase) {
                        //((SnakeBase) passenger).Dismount(player);
                        PacketHandler.INSTANCE.sendToServer(new ServerboundShoulderUpdate(passenger.getId()));
                        passenger.stopRiding();
                        Vec3 dismountPos = passenger.getDismountLocationForPassenger(player);
                        passenger.setPos(dismountPos.x(), dismountPos.y(), dismountPos.z());
                        //System.out.println("Dropping snake (cfEvents)");

                        //RatsNetworkHandler.CHANNEL.sendToServer(new DismountRatPacket(passenger.getId()));
                    }
                }
            }


            //player.displayClientMessage(new TextComponent(("dropping" + player.getPassengers().size() + "shoulder pets")), false);
            //player.ejectPassengers();
        }
    }

    /**@SubscribeEvent
    public static void onPlayerLeftClick(PlayerInteractEvent.LeftClickEmpty event) {
        if (event.getLevel().isClientSide()) {
            if (event.getEntity().isShiftKeyDown() && !event.getEntity().getPassengers().isEmpty()) {
                for (Entity passenger : event.getEntity().getPassengers()) {
                    if (passenger instanceof TamedRat) {
                        passenger.stopRiding();
                        Vec3 dismountPos = passenger.getDismountLocationForPassenger(event.getEntity());
                        passenger.setPos(dismountPos.x(), dismountPos.y(), dismountPos.z());
                        RatsNetworkHandler.CHANNEL.sendToServer(new DismountRatPacket(passenger.getId()));
                    }
                }
            }
            handleArmSwing(event.getItemStack(), event.getEntity());
            RatsNetworkHandler.CHANNEL.sendToServer(new SyncArmSwingPacket(event.getItemStack()));
        }
    }*/
}
