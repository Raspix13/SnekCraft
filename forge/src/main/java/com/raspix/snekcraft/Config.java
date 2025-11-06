package com.raspix.snekcraft;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

// An example config class. This is not required, but it's a good idea to have one to keep your config organized.
// Demonstrates how to use Forge's config APIs
@Mod.EventBusSubscriber(modid = SnekCraft.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Config {

    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();


    private static final ForgeConfigSpec.BooleanValue SNAKES_DROP_ITEMS = BUILDER
            .comment("Whether the snakes should drop sheds and teeth")
            .define("snakes_drop_items", true);

    private static final ForgeConfigSpec.IntValue HOGNOSE_SPAWN_WEIGHT = BUILDER
            .comment("The spawn weight of hognoses not in deserts, higher is more common")
            .defineInRange("hognose_spawn_weight", 30, 0, 1000);

    private static final ForgeConfigSpec.IntValue HOGNOSE_DESERT_SPAWN_WEIGHT = BUILDER
            .comment("The spawn weight of hognoses in deserts, higher is more common")
            .defineInRange("hognose_desert_spawn_weight", 60, 0, 1000);

    private static final ForgeConfigSpec.IntValue BALLPYTHON_SPAWN_WEIGHT = BUILDER
            .comment("The spawn weight of ball pythons, higher is more common")
            .defineInRange("ballpython_spawn_weight", 30, 0, 1000);
    private static final ForgeConfigSpec.IntValue CORNSNAKE_SPAWN_WEIGHT = BUILDER
            .comment("The spawn weight of corn snakes, higher is more common")
            .defineInRange("corn_snake_spawn_weight", 30, 0, 1000);


    /**public static final ForgeConfigSpec.ConfigValue<String> MAGIC_NUMBER_INTRODUCTION = BUILDER
            .comment("What you want the introduction message to be for the magic number")
            .define("magicNumberIntroduction", "The magic number is... ");

    // a list of strings that are treated as resource locations for items
    private static final ForgeConfigSpec.ConfigValue<List<? extends String>> ITEM_STRINGS = BUILDER
            .comment("A list of items to log on common setup.")
            .defineListAllowEmpty("items", List.of("minecraft:iron_ingot"), Config::validateItemName);*/

    static final ForgeConfigSpec SPEC = BUILDER.build();

    public static boolean snakesDropItems;
    public static int hognoseSpawnWeight;
    public static int hognoseDesertSpawnWeight;
    public static int ballpythonSpawnWeight;
    public static int cornSnakeSpawnWeight;

    private static boolean validateItemName(final Object obj)
    {
        return obj instanceof final String itemName && ForgeRegistries.ITEMS.containsKey(new ResourceLocation(itemName));
    }

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event)
    {

        snakesDropItems = SNAKES_DROP_ITEMS.get();
        hognoseSpawnWeight = HOGNOSE_SPAWN_WEIGHT.get();
        hognoseDesertSpawnWeight = HOGNOSE_DESERT_SPAWN_WEIGHT.get();
        ballpythonSpawnWeight = BALLPYTHON_SPAWN_WEIGHT.get();
        cornSnakeSpawnWeight = CORNSNAKE_SPAWN_WEIGHT.get();



        // convert the list of strings into a set of items
        /**items = ITEM_STRINGS.get().stream()
                .map(itemName -> ForgeRegistries.ITEMS.getValue(new ResourceLocation(itemName)))
                .collect(Collectors.toSet());*/
    }
}
