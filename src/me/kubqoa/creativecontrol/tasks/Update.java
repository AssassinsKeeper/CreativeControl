/*    */ package me.kubqoa.creativecontrol.tasks;
/*    */ 
/*    */ import me.kubqoa.creativecontrol.Main;
/*    */ import me.kubqoa.creativecontrol.UpdateChecker;
/*    */ import me.kubqoa.creativecontrol.helpers.Methods;
/*    */ import org.bukkit.plugin.PluginDescriptionFile;
/*    */ import org.bukkit.plugin.java.JavaPlugin;
/*    */ import org.bukkit.scheduler.BukkitRunnable;
/*    */ 
/*    */ public class Update
/*    */   extends BukkitRunnable
/*    */ {
/*    */   public void run()
/*    */   {
/* 15 */     Methods.console(Main.prefix + "&cChecking for updates..");
/*    */     
/* 17 */     String currentVersion = "v" + Main.thisPlugin.getDescription().getVersion();
/*    */     try {
/* 19 */       UpdateChecker updateChecker = new UpdateChecker();
/* 20 */       updateChecker.checkUpdate(currentVersion);
/* 21 */       String latestVersion = updateChecker.getLatestVersion();
/* 22 */       if (latestVersion != null) {
/* 23 */         latestVersion = "v" + latestVersion;
/* 24 */         Methods.console(Main.prefix + "&cNew update found! Current version &6" + currentVersion + "&c latest version &6" + latestVersion + "&c! Download here:");
/* 25 */         Methods.console(Main.prefix + "&6https://www.spigotmc.org/resources/creativecontrol.9988/");
/*    */       } else {
/* 27 */         Methods.console(Main.prefix + "&cNo updates found!");
/*    */       }
/*    */     } catch (Exception ex) {
/* 30 */       Methods.console(Main.prefix + "&cFailed to check for update!");
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Zack\Downloads\CreativeControl (6).jar!\me\kubqoa\creativecontrol\tasks\Update.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */