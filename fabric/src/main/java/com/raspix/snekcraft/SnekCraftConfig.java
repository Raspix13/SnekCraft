package com.raspix.snekcraft;


import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import com.google.gson.JsonObject;
import net.fabricmc.loader.api.FabricLoader;

import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;

public class SnekCraftConfig {

    private static File configFile = new File(FabricLoader.getInstance().getConfigDir().toFile(), "snekcraft_config.json");//new File("config/snekcraft_config.json");

    private static Map<String, Object> defaultValues = new HashMap<>(){{
        put("snakes_drop_items", true);
        put("hognose_spawn_weight", 30);
        put("hognose_desert_spawn_weight", 60);
        put("ballpython_spawn_weight", 30);
        put("cornsnake_spawn_weight", 30);
    }};

    public static boolean SNAKES_DROP_ITEMS = true;
    public static int HOGNOSE_SPAWN_WEIGHT;
    public static int HOGNOSE_DESERT_SPAWN_WEIGHT;
    public static int BALLPYTHON_SPAWN_WEIGHT;
    public static int CORNSNAKE_SPAWN_WEIGHT;


    public static void loadConfig() {
        try {

            System.out.println(configFile.getAbsolutePath());
            SnekCraft.logger.debug("File Name: ");
            if (!configFile.exists()) { //remove when changes are made
                createDefaultConfig();
            }
            JsonObject config = JsonParser.parseReader(new FileReader(configFile)).getAsJsonObject();

            SNAKES_DROP_ITEMS = getOrCreateValue("snakes_drop_items", config).getAsBoolean();
            HOGNOSE_SPAWN_WEIGHT = getOrCreateValue("hognose_spawn_weight", config).getAsInt();
            HOGNOSE_DESERT_SPAWN_WEIGHT = getOrCreateValue("hognose_desert_spawn_weight", config).getAsInt();
            BALLPYTHON_SPAWN_WEIGHT = getOrCreateValue("ballpython_spawn_weight", config).getAsInt();
            CORNSNAKE_SPAWN_WEIGHT = getOrCreateValue("cornsnake_spawn_weight", config).getAsInt();

            try (FileWriter writer = new FileWriter(configFile)) {
                writer.write(config.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the value from the config file or adds the default value to the json object and returns the default
     * @param valueName ghe id of the value
     * @param config the jsonobject to remake the config file
     * @return the config value
     */
    public static JsonElement getOrCreateValue(String valueName, JsonObject config){
        if(config.has(valueName)){
            return config.get(valueName);
        }else{
            config.addProperty(valueName, defaultValues.get(valueName).toString());
            return config.get(valueName);
        }
    }

    public static void createDefaultConfig() {
        JsonObject config = new JsonObject();
        config.addProperty("snakes_drop_items", true);
        config.addProperty("hognose_spawn_weight", 30);
        config.addProperty("hognose_desert_spawn_weight", 60);
        config.addProperty("ballpython_spawn_weight", 30);
        config.addProperty("cornsnake_spawn_weight", 30);

        try (FileWriter writer = new FileWriter(configFile)) {
            writer.write(config.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
