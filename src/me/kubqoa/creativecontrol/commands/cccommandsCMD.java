 package me.kubqoa.creativecontrol.commands;
 
 import java.util.List;
 import me.kubqoa.creativecontrol.Main;
 import me.kubqoa.creativecontrol.helpers.Methods;
 import me.kubqoa.creativecontrol.helpers.SimpleConfig;
 import org.bukkit.command.Command;
 import org.bukkit.command.CommandExecutor;
 import org.bukkit.command.CommandSender;
 
 class cccommandsCMD
   implements CommandExecutor
 {
   public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
   {
     if ((label.equalsIgnoreCase("cccmds")) || (label.equalsIgnoreCase("cccommands"))) {
       if (args.length > 0) {
         if (((args[0].equalsIgnoreCase("add")) && (Methods.perm(sender, "*"))) || (Methods.perm(sender, "cccmds.*")) || (Methods.perm(sender, "cccmds.add"))) {
           if (args.length > 1) {
             add(args[1], sender);
           } else {
             insufficientArgs(sender, "cccmds add");
           }
         } else if (((args[0].equalsIgnoreCase("del")) && (Methods.perm(sender, "*"))) || (Methods.perm(sender, "cccmds.*")) || (Methods.perm(sender, "cccmds.add"))) {
           if (args.length > 1) {
             del(args[1], sender);
           } else {
             insufficientArgs(sender, "cccmds del");
           }
         } else {
           sendHelp(sender);
         }
       } else {
         sendHelp(sender);
       }
     }
     return true;
   }
   
   private void sendHelp(CommandSender sender) {
     Methods.sendMsg(sender, "&4[------------------[&cCreative Control&4]------------------]", false);
     Methods.sendMsg(sender, "", false);
     Methods.sendMsg(sender, "&c/ccperms add <command> &6- Adds command to disabled command list.", false);
     Methods.sendMsg(sender, "&c/ccperms del <command> &6- Deletes command from disabled command list.", false);
     Methods.sendMsg(sender, "", false);
     Commands.endLine(sender);
   }
   
   private void insufficientArgs(CommandSender sender, String command) {
     Methods.sendMsg(sender, "&cInsufficient arguments given!", false);
     Methods.sendMsg(sender, "&cCorrect usage is: &6/" + command + " <command>", false);
   }
   
   private void add(String command, CommandSender sender) {
     if (!Main.excludeCMD.contains(command)) {
       Main.excludeCMD.add(command);
       Main.config.set("disabled-commands", Main.excludeCMD);
       Methods.sendMsg(sender, "&cAdded!");
     } else {
       Methods.sendMsg(sender, "&cCommand &6" + command + "&c is already in list of disabled-commands!");
     }
   }
   
   private void del(String command, CommandSender sender) {
     if (Main.excludeCMD.contains(command)) {
       Main.excludeCMD.remove(command);
       Main.config.set("disabled-commands", Main.excludeCMD);
       Methods.sendMsg(sender, "&cRemoved!");
     } else {
       Methods.sendMsg(sender, "&cCommand &6" + command + "&c is not in list of disabled-commands!");
     }
   }
 }


