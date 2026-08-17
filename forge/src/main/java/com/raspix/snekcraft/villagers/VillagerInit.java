package com.raspix.snekcraft.villagers;

import com.google.common.collect.ImmutableSet;
import com.raspix.snekcraft.SnekCraft;
import com.raspix.snekcraft.blocks.BlockInit;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import javax.annotation.concurrent.Immutable;

public class VillagerInit {

    public static final DeferredRegister<PoiType> POI_TYPES = DeferredRegister.create(ForgeRegistries.POI_TYPES, SnekCraft.MOD_ID);
    public static final DeferredRegister<VillagerProfession> VILLAGER_PROFESSIONS = DeferredRegister.create(ForgeRegistries.VILLAGER_PROFESSIONS, SnekCraft.MOD_ID);


    public static final RegistryObject<PoiType> HERPETOLOGY_POI = POI_TYPES.register("herpetology_poi", () -> new PoiType(ImmutableSet.copyOf(BlockInit.HERPETOLOGY_TABLE.get().getStateDefinition().getPossibleStates()), 1, 1));


    public static final RegistryObject<VillagerProfession> HERPETOLOGIST = VILLAGER_PROFESSIONS.register("herpetologist",
            () -> new VillagerProfession("herpetologist", holder -> holder.get() == HERPETOLOGY_POI.get(),
                    holder -> holder.get() == HERPETOLOGY_POI.get(), ImmutableSet.of(), ImmutableSet.of(), SoundEvents.VILLAGER_WORK_SHEPHERD));


    public static void register(IEventBus eventBus){
        POI_TYPES.register(eventBus);
        VILLAGER_PROFESSIONS.register(eventBus);
    }

}
