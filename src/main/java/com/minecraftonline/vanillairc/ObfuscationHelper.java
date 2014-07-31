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

import java.text.MessageFormat;
import java.util.List;
import net.minecraft.server.MinecraftServer;

/**
 *
 * @author willem
 */
public class ObfuscationHelper {
    public static String getPlayersString() {
        MinecraftServer mcs = MinecraftServer.L();
        List<?> players = mcs.am().e;
        return MessageFormat.format("{0,choice,0#No players|1#1 player|1<{0} players} online{0,choice,0#.|0<: {1}}",
                                    players.size(), mcs.am().f());
    }

    public static void sendMessageFromJson(String json) {
        MinecraftServer.L().am().sendFromJson(json);
    }
}
