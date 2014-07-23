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

import com.google.common.base.Supplier;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.pircbotx.Configuration;
import org.pircbotx.Configuration.Builder;
import org.pircbotx.PircBotX;

/**
 *
 * @author willem
 */
@NoArgsConstructor
public @Data class Config {
    private Builder<PircBotX> botConfig = new Builder<>();
    private char prefix = '.';
    private JsonElement privmsgFormat = new JsonParser().parse("[{\"text\":\"[IRC] \",\"color\":\"gray\"},"
            + "{\"text\":\"<{user}> {message}\", \"color\":\"white\"}]");
    private JsonElement actionFormat = new JsonParser().parse("[{\"text\":\"[IRC] \",\"color\":\"gray\"},"
            + "{\"text\":\"* {user} {message}\", \"color\":\"white\"}]");

    @Getter(lazy=true) private final String privmsgFormatString = ((Supplier<String>) ()-> {
        return new Gson().toJson(getPrivmsgFormat());
    }).get();
    @Getter(lazy=true) private final String actionFormatString = ((Supplier<String>) ()-> {
        return new Gson().toJson(getActionFormat());
    }).get();

    public Configuration<PircBotX> getConfiguration() {
        return botConfig.buildConfiguration();
    }
}
