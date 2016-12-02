 package me.kubqoa.creativecontrol.commands;
 
 import me.kubqoa.creativecontrol.Main;
 import me.kubqoa.creativecontrol.helpers.Methods;
 import org.bukkit.command.CommandSender;
 import org.bukkit.command.PluginCommand;
 import org.bukkit.plugin.java.JavaPlugin;
 
 public class Commands
 {
   private final JavaPlugin plugin;
   
   public Commands(JavaPlugin plugin)
   {
     this.plugin = plugin;
   }
   
   public void init() {
     this.plugin.getCommand("creativecontrol").setExecutor(new creativecontrolCMD());
     this.plugin.getCommand("ccpermissions").setExecutor(new ccpermissionsCMD());
     this.plugin.getCommand("cccommands").setExecutor(new cccommandsCMD());
     this.plugin.getCommand("ccconvert").setExecutor(new ccconvertCMD());
   }
   
   public static void endLine(CommandSender sender) {
     if (sender == Main.thisPlugin.getServer().getConsoleSender()) {
       Methods.sendMsg(sender, "&4[------------------------------------------------------]", false);
     } else {
       Methods.sendMsg(sender, "&4[---------------------------------------------------]", false);
     }
   }
 }


