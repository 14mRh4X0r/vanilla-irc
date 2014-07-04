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

import com.google.gson.Gson;
import java.io.FileReader;
import java.io.IOException;
import net.minecraft.server.MinecraftServer;
import org.pircbotx.Configuration;
import org.pircbotx.Configuration.Builder;
import org.pircbotx.PircBotX;
import org.pircbotx.exception.IrcException;
import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl;

public class VanillaIRC {
    public static void main(String[] args) throws IOException, IrcException {
        Configuration<PircBotX> conf = getConfiguration();
        PircBotX bot = new PircBotX(conf);
        MinecraftServer.main(args);
    }

    @SuppressWarnings("unchecked")
    private static Configuration<PircBotX> getConfiguration() throws IOException {
        return ((Builder<PircBotX>) new Gson().fromJson(new FileReader("ircbot.json"),
                ParameterizedTypeImpl.make(Builder.class, new Class<?>[]{PircBotX.class}, null))).buildConfiguration();
    }
}
