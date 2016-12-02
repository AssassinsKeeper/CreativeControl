 package me.kubqoa.creativecontrol.commands;
 
 import me.kubqoa.creativecontrol.helpers.Methods;
 import me.kubqoa.creativecontrol.utils.converter.Converter;
 import org.bukkit.command.Command;
 import org.bukkit.command.CommandExecutor;
 import org.bukkit.command.CommandSender;
 
 
 
 
 class ccconvertCMD
   implements CommandExecutor
 {
   public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
   {
     if (label.equalsIgnoreCase("ccconvert")) {
       if (args.length > 0) {
         if (args.length > 1) {
           Converter converter = new Converter(args[0], args[1], sender);
           converter.start();
         } else {
           insufficientArgs(sender);
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
     Methods.sendMsg(sender, "&c/ccconvert <from> <to> &6- Converts <from> to <to>.", false);
     Methods.sendMsg(sender, "", false);
     Methods.sendMsg(sender, "&c<from> &6- From can be either &coldcc&6, &csqlite&6 or &cmysql", false);
     Methods.sendMsg(sender, "&c<to> &6- From can be either &ccurrent&6, &csqlite&6 or &cmysql", false);
     Methods.sendMsg(sender, "&cWarning: &6oldcc &ccan only be used in combination with &6current&c", false);
     Methods.sendMsg(sender, "&cand &6mysql &ccan only be used in combination with &6sqlite&c and vice versa.", false);
     Methods.sendMsg(sender, "", false);
     Commands.endLine(sender);
   }
   
   private static void insufficientArgs(CommandSender sender) {
     Methods.sendMsg(sender, "&cInsufficient arguments given!", false);
     Methods.sendMsg(sender, "&cCorrect usage is: &6/ccconvert <from> <to>", false);
   }
 }


