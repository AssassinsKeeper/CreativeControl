 package me.kubqoa.creativecontrol.commands;
 
 import java.util.List;
 import me.kubqoa.creativecontrol.Main;
 import me.kubqoa.creativecontrol.helpers.Methods;
 import me.kubqoa.creativecontrol.helpers.SimpleConfig;
 import org.bukkit.command.Command;
 import org.bukkit.command.CommandExecutor;
 import org.bukkit.command.CommandSender;
 
 
 
 class ccpermissionsCMD
   implements CommandExecutor
 {
   public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
   {
     if ((label.equalsIgnoreCase("ccperms")) || (label.equalsIgnoreCase("ccpermissions"))) {
       if (args.length > 0) {
         if (((args[0].equalsIgnoreCase("add")) && (Methods.perm(sender, "*"))) || (Methods.perm(sender, "ccperms.*")) || (Methods.perm(sender, "ccperms.add"))) {
           if (args.length > 2) {
             add(args[1], args[2], sender);
           } else {
             insufficientArgs(sender, "ccperms add");
           }
         } else if (((args[0].equalsIgnoreCase("del")) && (Methods.perm(sender, "*"))) || (Methods.perm(sender, "ccperms.*")) || (Methods.perm(sender, "ccperms.add"))) {
           if (args.length > 2) {
             del(args[1], args[2], sender);
           } else {
             insufficientArgs(sender, "ccperms del");
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
     Methods.sendMsg(sender, "&c/ccperms add <list> <permission> &6- Adds permission to specified list.", false);
     Methods.sendMsg(sender, "&c/ccperms del <list> <permission> &6- Deletes permission from specified list.", false);
     Methods.sendMsg(sender, "", false);
     Methods.sendMsg(sender, "&c<list> &6- List can be &cremove-permissions &6or &cadd-permissions&6.", false);
     Methods.sendMsg(sender, "", false);
     Commands.endLine(sender);
   }
   
   private static void insufficientArgs(CommandSender sender, String command) {
     Methods.sendMsg(sender, "&cInsufficient arguments given!", false);
     Methods.sendMsg(sender, "&cCorrect usage is: &6/" + command + " <list> <permission>", false);
   }
   
   private List<String> getPermissionList(String name) {
     if (name.equals("remove-permissions"))
       return Main.removeperms;
     if (name.equals("add-permissions")) {
       return Main.addperms;
     }
     return null;
   }
   
   private void add(String name, String permission, CommandSender sender) {
     List<String> perms = getPermissionList(name);
     if (perms == null) {
       Methods.sendMsg(sender, "&cInvalid list name!");
       return;
     }
     if (!perms.contains(permission)) {
       perms.add(permission);
       Main.config.set(name.toLowerCase(), perms);
       Methods.sendMsg(sender, "&cAdded!");
     } else {
       Methods.sendMsg(sender, "&cPermission &6" + permission + "&c is already in list &6" + name + "&c!");
     }
   }
   
   private void del(String name, String permission, CommandSender sender) {
     List<String> perms = getPermissionList(name);
     if (perms == null) {
       Methods.sendMsg(sender, "&cInvalid list name!");
       return;
     }
     if (perms.contains(permission)) {
       perms.remove(permission);
       Main.config.set(name.toLowerCase(), perms);
       Methods.sendMsg(sender, "&cRemoved!");
     } else {
       Methods.sendMsg(sender, "&cPermission &6" + permission + "&c is not in list &6" + name + "&c!");
     }
   }
 }


