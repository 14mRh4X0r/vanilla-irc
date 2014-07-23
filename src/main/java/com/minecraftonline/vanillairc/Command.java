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

import java.util.HashMap;
import java.util.Locale;
import org.pircbotx.PircBotX;
import org.pircbotx.hooks.events.MessageEvent;

/**
 *
 * @author willem
 */
public enum Command {

    LIST((MessageEvent<PircBotX> event) -> {
        event.respond(ObfuscationHelper.getPlayersString());
    }, "players", "who");

    private final CommandHandler handler;
    private static HashMap<String, Command> commands;

    @SuppressWarnings("LeakingThisInConstructor")
    private Command(CommandHandler handler, String... aliases) {
        this.handler = handler;

        add(name().toLowerCase(Locale.ROOT), this);
        for (String alias : aliases) {
            add(alias, this);
        }
    }

    private static void add(String alias, Command command) {
        if (commands == null) {
            commands = new HashMap<>();
        }

        commands.put(alias, command);
    }

    public void handleCommand(MessageEvent<PircBotX> event) throws Exception {
        this.handler.handleCommand(event);
    }

    public static Command fromString(String command) {
        return commands.get(command.toLowerCase(Locale.ROOT));
    }

    public static interface CommandHandler {
        void handleCommand(MessageEvent<PircBotX> event) throws Exception;
    }
}
