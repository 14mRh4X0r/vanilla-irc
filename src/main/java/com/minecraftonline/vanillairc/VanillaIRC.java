/*
 * Copyright (C) 2014 MinecraftOnline
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.minecraftonline.vanillairc;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lombok.Getter;
import net.minecraft.server.MinecraftServer;
import org.pircbotx.PircBotX;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VanillaIRC {
    @Getter(lazy=true) private static final Config config = getConfig0();
    private static final Logger log = LoggerFactory.getLogger(VanillaIRC.class);
    public static final String CONFIG_FILE = "ircbot.json";
    @Getter public static ChatHandler handler;

    private static Config getConfig0() {
        Config localConfig = null;
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
        try {
            localConfig = gson.fromJson(new FileReader(CONFIG_FILE), Config.class);
        } catch (FileNotFoundException e) {
            try {
                Files.copy(VanillaIRC.class.getResourceAsStream(CONFIG_FILE), Paths.get(CONFIG_FILE));
                log.warn("Configuration file \"{}\" did not exist, initialized with defaults", CONFIG_FILE);
            } catch (IOException ioe) {
                log.error("Configuration file did not exist and could not write defaults", ioe);
            }
            System.exit(1);
        } catch (JsonParseException e) {
            log.error("Error while parsing config", e);
        }
        return localConfig;
    }

    public static void main(String[] args) throws IOException {
        PircBotX bot = new PircBotX(getConfig().getConfiguration());
        handler = new ChatHandler(bot);
        bot.getConfiguration().getListenerManager().addListener(handler);

        @SuppressWarnings({"TooBroadCatch", "BroadCatchBlock", "UseSpecificCatch"})
        Thread botThread = new Thread(() -> {
            try {
                bot.startBot();
            } catch (Exception e) {
                log.warn("Exception in IRC thread", e);
            }
        }, "IRC bot");
        botThread.start();

        // No GUI by default
        List<String> argsList = new ArrayList<>(Arrays.asList(args));
        if (!argsList.remove("--gui") && !argsList.contains("--nogui")) {
            argsList.add("--nogui");
        }
        MinecraftServer.main(argsList.toArray(new String[0]));
    }
}
