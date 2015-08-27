VanillaIRC
==========

Reliable IRC for your (snapshot) server!

What does it do?
----------------

It's quite simple -- this little mod adds IRC to your server. Due to its small code footprint, it's easy to update this mod for every release of Minecraft, even snapshots!

Commands
--------
Only one command for now; _list_, _who_ or _players_ lists the players on the server.

Configuration
-------------

When you first start the server with the mod, it'll create a config file and exit. This config file is in [JSON](http://json.org), and has several options:

* `"bot_config"` - This is where the configuration for the bot itself resides. For more options, see [the PircBotX javadoc on Configuration.Builder](http://thelq.github.io/pircbotx/latest/apidocs/org/pircbotx/Configuration.Builder.html).
  - `"web_irc_enabled"` - Indicates whether to connect through a WEBIRC interface. When in doubt, leave as `false`
  - `"name"` - The nick of the bot
  - `"login"` - The ident of the bot
  - `"real_name"` - The "real name" (a.k.a. gecos) of the bot
  - `"server_hostname"` - The hostname of the server to connect to, e.g. `"irc.esper.net"`
  - `"auto_join_channels"` - The channels to join when connecting. format: `"#channel": "key"`
  - `"nickserv_password"` - The password to authenticate with NickServ. When in doubt, leave as `null`
  - `"auto_reconnect"` - Indicates whether to automatically reconnect to the server if the connection dies
* `"prefix"` - The prefix for the commands. For example, using `"."` here makes the command to list the players `.list`
* `"privmsg_format"` - The format used to show ordinary messages in Minecraft. This uses the new chat format in JSON (see [this page](http://wiki.vg/Chat#Current_system_.28JSON_Chat.29)). The tokens `{user}` and `{message}` will be replaced with the nick and message of the sender, respectively.
* `"action_format"` - The format used to show action messages (`/me does stuff`) in Minecraft. It uses the same system as `"privmsg_format"`

Running
-------

Simply run this jar instead of the minecraft server.
