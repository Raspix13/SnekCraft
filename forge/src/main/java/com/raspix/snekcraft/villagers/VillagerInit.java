package com.raspix.snekcraft.villagers;

import com.google.common.collect.ImmutableSet;
import com.raspix.snekcraft.SnekCraft;
import com.raspix.snekcraft.blocks.BlockInit;
import com.raspix.snekcraft.items.ItemInit;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import javax.annotation.concurrent.Immutable;
import java.util.List;

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


    // Called from ModEvents in Snekcraft
    public static void addCustomTrades(VillagerTradesEvent event) {
        if(event.getType() == VillagerInit.HERPETOLOGIST.get()) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();

            /**ItemStack bagStack = new ItemStack(ItemInit.SNAKE_BAG.get());
            CompoundTag tag = bagStack.getOrCreateTag();
            CompoundTag snakeTag = new CompoundTag();
            tag.putBoolean("Filled", true);
            bagStack.setTag(tag);

            trades.get(1).add((pTrader, pRandom) -> new SnakeBagMerchantOffer(
                    bagStack,
                    new ItemStack(Items.EMERALD, 1),
                    12, 2, 0.05f));*/
            trades.get(1).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(ItemInit.HOGNOSE_SNAKE_BAG.get(), 1),
                    new ItemStack(Items.EMERALD, 1),
                    12, 2, 0.05f));
            trades.get(1).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(ItemInit.BALL_PYTHON_SNAKE_BAG.get(), 1),
                    new ItemStack(Items.EMERALD, 1),
                    12, 2, 0.05f));
            trades.get(1).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(ItemInit.CORN_SNAKE_BAG.get(), 1),
                    new ItemStack(Items.EMERALD, 1),
                    12, 2, 0.05f));
            trades.get(1).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(ItemInit.SNAKE_TOOTH.get(), 32),
                    new ItemStack(Items.EMERALD, 1),
                    12, 2, 0.05f));
            trades.get(1).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(ItemInit.SNAKE_SKIN.get(), 32),
                    new ItemStack(Items.EMERALD, 1),
                    12, 2, 0.05f));
            trades.get(1).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 1),
                    new ItemStack(ItemInit.FROG_LEG.get(), 8),
                    16, 1, 0.05f));
            trades.get(1).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 1),
                    new ItemStack(Items.RABBIT, 8),
                    16, 1, 0.05f));
            trades.get(1).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 1),
                    new ItemStack(Items.CHICKEN, 8),
                    16, 1, 0.05f));


            trades.get(2).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 2),
                    new ItemStack(ItemInit.SNAKE_BAG.get(), 1),
                    12, 5, 0.05f));
            trades.get(2).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 2),
                    new ItemStack(ItemInit.TERRARIUM_ITEM.get(), 16),
                    12, 5, 0.05f));
            trades.get(2).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 2),
                    new ItemStack(ItemInit.AQUARIUM_ITEM.get(), 16),
                    12, 5, 0.05f));
            trades.get(2).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 2),
                    new ItemStack(ItemInit.WIRE_CAGE_ITEM.get(), 16),
                    12, 5, 0.05f));
            trades.get(2).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 4),
                    new ItemStack(ItemInit.LAVAQUARIUM_ITEM.get(), 8),
                    12, 10, 0.05f));


            ItemStack hoggie_egg = new ItemStack(ItemInit.HOGGIE_EGG.get());
            ItemStack ball_python_egg = new ItemStack(ItemInit.BALL_PYTHON_EGG.get());
            ItemStack corn_snake_egg = new ItemStack(ItemInit.CORN_SNAKE_EGG.get());
            CompoundTag hoggie_egg_tag = hoggie_egg.getOrCreateTag();
            CompoundTag ball_python_egg_tag = ball_python_egg.getOrCreateTag();
            CompoundTag corn_snake_egg_tag = corn_snake_egg.getOrCreateTag();
            CompoundTag eggTag = new CompoundTag(); // reusing this should be fine but look here is problems
            eggTag.putInt("color", 0);
            eggTag.putInt("color_p2", 0);
            eggTag.putInt("pattern", 0);
            eggTag.putInt("pattern_p2", 0);
            hoggie_egg_tag.put("BlockStateTag", eggTag);
            ball_python_egg_tag.put("BlockStateTag", eggTag);
            corn_snake_egg_tag.put("BlockStateTag", eggTag);

            trades.get(3).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 6),
                    hoggie_egg,
                    12, 10, 0.05f));
            trades.get(3).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 6),
                    ball_python_egg,
                    12, 10, 0.05f));
            trades.get(3).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 6),
                    corn_snake_egg,
                    12, 10, 0.05f));


            trades.get(4).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 10),
                    new ItemStack(Items.FROGSPAWN, 1),
                    12, 15, 0.05f));


            trades.get(5).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 16),
                    new ItemStack(Items.TURTLE_EGG, 1),
                    12, 30, 0.05f));
        }


    }

}
