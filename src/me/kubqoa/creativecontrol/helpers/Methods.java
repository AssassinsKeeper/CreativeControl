 package me.kubqoa.creativecontrol.helpers;
 
 import java.util.List;
 import me.kubqoa.creativecontrol.Main;
 import me.kubqoa.creativecontrol.integrations.Vault;
 import me.kubqoa.creativecontrol.tasks.Cooldown;
 import org.bukkit.Bukkit;
 import org.bukkit.ChatColor;
 import org.bukkit.Location;
 import org.bukkit.Server;
 import org.bukkit.command.CommandSender;
 import org.bukkit.command.ConsoleCommandSender;
 import org.bukkit.entity.Player;
 
 public class Methods
 {
   public static void console(String msg)
   {
     Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', msg));
   }
   
   public static void send(Player player, String config) {
     send(player, config, true);
   }
   
   private static void send(Player player, String config, boolean withPrefix) {
     if ((!Main.cooldownsS.contains(config)) || (!Main.cooldownsP.contains(player))) {
       if (Main.cooldown == 0L) {
         if (withPrefix) {
           player.sendMessage(Main.prefix + ChatColor.translateAlternateColorCodes('&', Main.messages.getString(config)));
         } else {
           player.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.messages.getString(config)));
         }
       } else {
         Main.cooldownsP.add(player);
         Main.cooldownsS.add(config);
         new Cooldown(player, config).runTaskLaterAsynchronously(Main.thisPlugin, Main.cooldown * 20L);
         if (withPrefix) {
           player.sendMessage(Main.prefix + ChatColor.translateAlternateColorCodes('&', Main.messages.getString(config)));
         } else
           player.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.messages.getString(config)));
       }
     }
   }
   
   public static void sendMsg(Player player, String message) {
     sendMsg(player, message, true);
   }
   
   private static void sendMsg(Player player, String message, boolean withPrefix) {
     if (withPrefix) {
       player.sendMessage(Main.prefix + ChatColor.translateAlternateColorCodes('&', message));
     } else {
       player.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
     }
   }
   
   public static void sendMsg(CommandSender sender, String message) { sendMsg(sender, message, true); }
   
   public static void sendMsg(CommandSender sender, String message, boolean withPrefix) {
     if (withPrefix) {
       sender.sendMessage(Main.prefix + ChatColor.translateAlternateColorCodes('&', message));
     } else {
       sender.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
     }
   }
   
   public static boolean perm(Player player, String perm) {
     if (Main.vault != null) {
       return Vault.hasPermission(player, "cc." + perm);
     }
     return player.hasPermission("cc." + perm);
   }
   
   public static boolean perm(CommandSender sender, String perm)
   {
     if (sender == Main.thisPlugin.getServer().getConsoleSender()) return true;
     Player player = (Player)sender;
     if (Main.vault != null) {
       return Vault.hasPermission(player, "cc." + perm);
     }
     return player.hasPermission("cc." + perm);
   }
   
   public static boolean exclude(Location location)
   {
     return Main.exclude.contains(location.getWorld().getName());
   }
 }


