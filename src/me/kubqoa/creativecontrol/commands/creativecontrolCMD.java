 package me.kubqoa.creativecontrol.commands;
 
 import java.sql.Connection;
 import java.sql.SQLException;
 import java.util.List;
 import me.kubqoa.creativecontrol.Main;
 import me.kubqoa.creativecontrol.UpdateChecker;
 import me.kubqoa.creativecontrol.helpers.ConfigHelper;
 import me.kubqoa.creativecontrol.helpers.DatabaseHelper;
 import me.kubqoa.creativecontrol.helpers.Methods;
 import me.kubqoa.creativecontrol.helpers.SimpleConfig;
 import org.bukkit.command.Command;
 import org.bukkit.command.CommandExecutor;
 import org.bukkit.command.CommandSender;
 import org.bukkit.command.ConsoleCommandSender;
 import org.bukkit.entity.Player;
 import org.bukkit.plugin.PluginDescriptionFile;
 import org.bukkit.plugin.java.JavaPlugin;
 
 class creativecontrolCMD implements CommandExecutor
 {
   public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
   {
     if ((label.equalsIgnoreCase("ccontrol")) || (label.equalsIgnoreCase("creativecontrol"))) {
       if (args.length > 0) {
         if ((args[0].equalsIgnoreCase("reload")) && (Methods.perm(sender, "cc.reload")) && (Methods.perm(sender, "cc.*")) && (Methods.perm(sender, "*"))) {
           Methods.sendMsg(sender, "&4Getting new config.");
           Main.disable.set("old-db-prefix", Main.dbprefix);
           
           ConfigHelper configHelper = new ConfigHelper(Main.thisPlugin);
           configHelper.start();
           Main.config = configHelper.config;
           Main.messages = configHelper.messages;
           Main.players = configHelper.players;
           Main.disable = configHelper.disable;
           Main.protectionType = configHelper.protectionType;
           Main.prefix = org.bukkit.ChatColor.translateAlternateColorCodes('&', configHelper.prefix) + " ";
           Main.dbprefix = configHelper.dbprefix;
           Main.cooldown = configHelper.cooldown;
           Main.loggingInterval = configHelper.logwhen;
           Main.disabledGamemodes = configHelper.disabledGamemodes;
           Main.exclude = configHelper.exclude;
           Main.excludeCMD = configHelper.excludeCMD;
           Main.items = configHelper.items;
           Main.addperms = configHelper.addperms;
           Main.removeperms = configHelper.removeperms;
           Main.noTracking = configHelper.noTracking;
           Main.blockCache = configHelper.blockCache;
           Main.vehicleCache = configHelper.vehicleCache;
           Main.hangingCache = configHelper.hangingCache;
           if (Main.config.getString("db-type").equalsIgnoreCase("mysql")) Main.dbtype = true;
           Methods.sendMsg(sender, "&4Re-connecting to database server.");
           try
           {
             Main.c.close();
           } catch (SQLException e) {
             e.printStackTrace();
           }
           DatabaseHelper databaseHelper = new DatabaseHelper();
           databaseHelper.start();
           databaseHelper.checkTables();
           Methods.sendMsg(sender, "&6Reloading finished! (&4v." + Main.thisPlugin.getDescription().getVersion() + "&6)");
         } else if ((args[0].equalsIgnoreCase("memory")) && (Methods.perm(sender, "cc.memory")) && (Methods.perm(sender, "cc.*")) && (Methods.perm(sender, "*"))) {
           Methods.sendMsg(sender, "&cUsed memory &6/&a Total memory", false);
           Methods.sendMsg(sender, "&6Blocks memory: &c" + String.valueOf(Main.blocksLocation.size()) + "&6 / &a" + Main.blockCache, false);
           Methods.sendMsg(sender, "&6Hangings memory: &c" + String.valueOf(Main.hangingsLocation.size()) + "&6 / &a" + Main.hangingCache, false);
           Methods.sendMsg(sender, "&6Vehicles memory: &c" + String.valueOf(Main.vehiclesLocation.size()) + "&6 / &a" + Main.vehicleCache, false);
         } else if ((args[0].equalsIgnoreCase("add")) && (Methods.perm(sender, "cc.add")) && (Methods.perm(sender, "cc.*")) && (Methods.perm(sender, "*"))) {
           if ((sender instanceof ConsoleCommandSender)) {
             Methods.sendMsg(sender, "&4Only in-game players can use this command!");
             return true;
           }
           Player player = (Player)sender;
           Methods.sendMsg(sender, "&4Now please left click a block you wish to add to the database.");
           Main.addMode.add(player);
         } else if ((args[0].equalsIgnoreCase("remove")) && (Methods.perm(sender, "cc.remove")) && (Methods.perm(sender, "cc.*")) && (Methods.perm(sender, "*"))) {
           if ((sender instanceof ConsoleCommandSender)) {
             Methods.sendMsg(sender, "&4Only in-game players can use this command!");
             return true;
           }
           Player player = (Player)sender;
           Methods.sendMsg(sender, "&4Now please left click a block you wish to remove from the database.");
           Main.removeMode.add(player);
         } else if (args[0].equalsIgnoreCase("update"))
         {
           String currentVersion = "v" + Main.thisPlugin.getDescription().getVersion();
           try {
             UpdateChecker updateChecker = new UpdateChecker();
             updateChecker.checkUpdate(currentVersion);
             String latestVersion = updateChecker.getLatestVersion();
             if (latestVersion != null) {
               latestVersion = "v" + latestVersion;
               Methods.sendMsg(sender, "&cNew update found! Current version &6" + currentVersion + "&c latest version &6" + latestVersion + "&c! Download here:");
               Methods.sendMsg(sender, "&6https:");
             } else {
               Methods.sendMsg(sender, "&cNo updates found!");
             }
           } catch (Exception ex) {
             Methods.sendMsg(sender, "&cFailed to check for update!");
           }
         } else if (args[0].equalsIgnoreCase("help")) {
           sendHelp(sender);
         }
       } else {
         Methods.sendMsg(sender, "&4[------------------[&cCreative Control&4]------------------]", false);
         Methods.sendMsg(sender, "", false);
         Methods.sendMsg(sender, "&cThis server is running &6" + Main.thisPlugin.getDescription().getName() + " &cv: &6" + Main.thisPlugin.getDescription().getVersion(), false);
         Methods.sendMsg(sender, "", false);
         if ((Methods.perm(sender, "cc.help")) || (Methods.perm(sender, "cc.*")) || (Methods.perm(sender, "*"))) {
           Methods.sendMsg(sender, "&cTo view available commands do &6/cc help&c.", false);
           Methods.sendMsg(sender, "", false);
         }
         Commands.endLine(sender);
       }
       return true;
     }
     return false;
   }
   
   private void sendHelp(CommandSender sender) {
     Methods.sendMsg(sender, "&4[------------------[&cCreative Control&4]------------------]", false);
     Methods.sendMsg(sender, "", false);
     Methods.sendMsg(sender, "&c/ccontrol reload &6- Reloads plugin's configuration.", false);
     Methods.sendMsg(sender, "&c/ccontrol memory &6- Shows active memory usage for each cache.", false);
     Methods.sendMsg(sender, "&c/ccontrol add &6- Adds clicked block to database.", false);
     Methods.sendMsg(sender, "&c/ccontrol remove &6- Removes clicked block from database.", false);
     Methods.sendMsg(sender, "&c/ccontrol update &6- Checks if update is available.", false);
     Methods.sendMsg(sender, "&c/ccontrol help &6- Displays this help.", false);
     Methods.sendMsg(sender, "&c/ccperms &6- Displays help for this subcommand.", false);
     Methods.sendMsg(sender, "&c/cccmds &6- Displays help for this subcommand.", false);
     Methods.sendMsg(sender, "&c/ccconvert &6- Displays help for this subcommand.", false);
     Methods.sendMsg(sender, "", false);
     Commands.endLine(sender);
   }
 }


