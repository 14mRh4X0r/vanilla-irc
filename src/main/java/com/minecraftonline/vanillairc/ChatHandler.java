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

import com.google.gson.Gson;
import org.pircbotx.Channel;
import org.pircbotx.PircBotX;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.ActionEvent;
import org.pircbotx.hooks.events.MessageEvent;

/**
 *
 * @author willem
 */
public class ChatHandler extends ListenerAdapter<PircBotX> {
    private final PircBotX bot;
    private final Config config = VanillaIRC.getConfig();

    private static String jsonEscape(String str) {
        String json = new Gson().toJson(str);
        return json.substring(1, json.length() - 1);
    }

    public ChatHandler(PircBotX bot) {
        this.bot = bot;
    }

    @Override
    public void onAction(ActionEvent<PircBotX> event) throws Exception {
        String formatted = config.getActionFormatString()
                .replace("{user}", jsonEscape(event.getUser().getNick()))
                .replace("{message}", jsonEscape(event.getMessage()));
        ObfuscationHelper.sendMessageFromJson(formatted);
    }

    @Override
    public void onMessage(MessageEvent<PircBotX> event) throws Exception {
        String message = event.getMessage();
        if (message.charAt(0) == config.getPrefix()) {
            int spaceIdx = message.indexOf(' ');
            String command = spaceIdx > -1 ? message.substring(1, spaceIdx) : message.substring(1);
            Command c = Command.fromString(command);
            if (c != null) {
                c.handleCommand(event);
            } else if (config.isSendNoSuchCommand()) {
                event.respond("No such command");
            }
        } else {
            String formatted = config.getPrivmsgFormatString()
                    .replace("{user}", jsonEscape(event.getUser().getNick()))
                    .replace("{message}", jsonEscape(message));
            ObfuscationHelper.sendMessageFromJson(formatted);
        }
    }

    public void handleGameMessage(String translated) {
        for (Channel chan : bot.getUserChannelDao().getAllChannels()) {
            chan.send().message(translated);
        }
    }
}
