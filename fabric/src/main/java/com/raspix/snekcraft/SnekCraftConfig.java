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
    public static int LIKED_POFFIN_FRIEND_INCREMENT;
    public static int NEUTRAL_POFFIN_FRIEND_INCREMENT;
    public static int DISLIKED_POFFIN_FRIEND_DECREMENT;
    public static int FOUL_POFFIN_FRIEND_DECREMENT;

    public static void loadConfig() {
        try {
            if (!configFile.exists()) { //remove when changes are made
                createDefaultConfig();
            }
            JsonObject config = JsonParser.parseReader(new FileReader(configFile)).getAsJsonObject();

            SNAKES_DROP_ITEMS = config.get("snakes_drop_items").getAsBoolean();
            LIKED_POFFIN_FRIEND_INCREMENT = config.get("liked_poffin_friend_increment").getAsInt();
            NEUTRAL_POFFIN_FRIEND_INCREMENT = config.get("neutral_poffin_friend_increment").getAsInt();
            DISLIKED_POFFIN_FRIEND_DECREMENT = config.get("disliked_poffin_friend_decrement").getAsInt();
            FOUL_POFFIN_FRIEND_DECREMENT = config.get("foul_poffin_friend_decrement").getAsInt();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void createDefaultConfig() {
        JsonObject config = new JsonObject();
        config.addProperty("snakes_drop_items", true);
        config.addProperty("liked_poffin_friend_increment", 5);
        config.addProperty("neutral_poffin_friend_increment", 1);
        config.addProperty("disliked_poffin_friend_decrement", 5);
        config.addProperty("foul_poffin_friend_decrement", 20);

        try (FileWriter writer = new FileWriter(configFile)) {
            writer.write(config.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
