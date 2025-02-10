package com.raspix.snekcraft;


import com.google.gson.JsonParser;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import com.google.gson.JsonObject;
import java.io.FileWriter;

public class SnekCraftConfig {

    private static File configFile = new File("config/snekcraft_config.json");

    public static boolean SNAKES_DROP_ITEMS = true;
    public static int HOGNOSE_SPAWN_WEIGHT;
    public static int HOGNOSE_DESERT_SPAWN_WEIGHT;
    public static int BALLPYTHON_SPAWN_WEIGHT;


    public static void loadConfig() {
        try {
            if (!configFile.exists()) { //remove when changes are made
                createDefaultConfig();
            }
            JsonObject config = JsonParser.parseReader(new FileReader(configFile)).getAsJsonObject();

            SNAKES_DROP_ITEMS = config.get("snakes_drop_items").getAsBoolean();
            HOGNOSE_SPAWN_WEIGHT = config.get("hognose_spawn_weight").getAsInt();
            HOGNOSE_DESERT_SPAWN_WEIGHT = config.get("hognose_desert_spawn_weight").getAsInt();
            BALLPYTHON_SPAWN_WEIGHT = config.get("ballpython_spawn_weight").getAsInt();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void createDefaultConfig() {
        JsonObject config = new JsonObject();
        config.addProperty("snakes_drop_items", true);
        config.addProperty("hognose_spawn_weight", 30);
        config.addProperty("hognose_desert_spawn_weight", 60);
        config.addProperty("ballpython_spawn_weight", 30);

        try (FileWriter writer = new FileWriter(configFile)) {
            writer.write(config.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
