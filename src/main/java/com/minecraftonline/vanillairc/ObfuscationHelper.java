/*
 * Copyright (C) 2014-2015 MinecraftOnline
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

import java.lang.reflect.Method;
import java.text.MessageFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Utility class to help with obfuscated method calls.
 * @author Willem Mulder
 */
public class ObfuscationHelper {
    private static final Logger log = LoggerFactory.getLogger(ObfuscationHelper.class);
    private static Object loginManager;
    private static Method getPlayerCount;
    private static Method getPlayersString;
    private static Method sendFromJson;

    public static void setLoginManager(Object manager, Class clazz) {
        loginManager = manager;

        try {
            getPlayerCount = clazz.getDeclaredMethod("o");
            getPlayersString = clazz.getDeclaredMethod("b", boolean.class);
            sendFromJson = clazz.getDeclaredMethod("sendFromJson", String.class);
        } catch (NoSuchMethodException e) {
            log.error("LoginManager methods not found", e);
        }
    }

    public static String getPlayersString() {
        int players = 0;
        String playerList = "";

        try {
            players = (Integer) getPlayerCount.invoke(loginManager);
            playerList = (String) getPlayersString.invoke(loginManager, false);
        } catch (ReflectiveOperationException e) {
            log.error("Could not retrieve player list", e);
        }

        return MessageFormat.format(
                "{0,choice,0#No players|1#1 player|1<{0} players} online{0,choice,0#.|0<: {1}}",
                players, playerList);
    }

    public static void sendMessageFromJson(String json) {
        try {
            sendFromJson.invoke(loginManager, json);
        } catch (ReflectiveOperationException e) {
            log.error("Could not send message", e);
        }
    }
}
