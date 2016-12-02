/*    */ package me.kubqoa.creativecontrol.tasks;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import me.kubqoa.creativecontrol.Main;
/*    */ import me.kubqoa.creativecontrol.helpers.DatabaseHelper;
/*    */ import org.bukkit.scheduler.BukkitRunnable;
/*    */ 
/*    */ public class InventoriesToDB extends BukkitRunnable
/*    */ {
/*    */   private String gamemode;
/*    */   
/*    */   public InventoriesToDB(String gamemode)
/*    */   {
/* 14 */     this.gamemode = gamemode;
/*    */   }
/*    */   
/*    */   public void run()
/*    */   {
/* 19 */     if (this.gamemode.equalsIgnoreCase("all")) {
/* 20 */       survival();creative();adventure();
/* 21 */     } else if (this.gamemode.equalsIgnoreCase("SURVIVAL")) {
/* 22 */       survival();
/* 23 */     } else if (this.gamemode.equalsIgnoreCase("CREATIVE")) {
/* 24 */       creative();
/* 25 */     } else if (this.gamemode.equalsIgnoreCase("ADVENTURE")) {
/* 26 */       adventure();
/*    */     }
/*    */   }
/*    */   
/*    */   private void survival()
/*    */   {
/* 32 */     this.gamemode = "SURVIVAL";
/* 33 */     for (String uuid : Main.wsInventory.keySet()) {
/* 34 */       String inv = (String)Main.wsInventory.get(uuid);
/* 35 */       if (DatabaseHelper.selectSQL("SELECT * FROM `" + Main.dbprefix + "inventories` WHERE uuid='" + uuid + "' AND gamemode='" + this.gamemode + "'") > 0) {
/* 36 */         DatabaseHelper.updateSQL("UPDATE `" + Main.dbprefix + "inventories` SET inventory='" + inv + "' WHERE uuid='" + uuid + "' AND gamemode='" + this.gamemode + "'");
/*    */       } else {
/* 38 */         DatabaseHelper.updateSQL("INSERT INTO `" + Main.dbprefix + "inventories` (uuid, gamemode, inventory) VALUES ('" + uuid + "','" + this.gamemode + "','" + inv + "')");
/*    */       }
/*    */     }
/*    */   }
/*    */   
/*    */   private void creative()
/*    */   {
/* 45 */     this.gamemode = "CREATIVE";
/* 46 */     for (String uuid : Main.wcInventory.keySet()) {
/* 47 */       String inv = (String)Main.wcInventory.get(uuid);
/* 48 */       if (DatabaseHelper.selectSQL("SELECT * FROM `" + Main.dbprefix + "inventories` WHERE uuid='" + uuid + "' AND gamemode='" + this.gamemode + "'") > 0) {
/* 49 */         DatabaseHelper.updateSQL("UPDATE `" + Main.dbprefix + "inventories` SET inventory='" + inv + "' WHERE uuid='" + uuid + "' AND gamemode='" + this.gamemode + "'");
/*    */       } else {
/* 51 */         DatabaseHelper.updateSQL("INSERT INTO `" + Main.dbprefix + "inventories` (uuid, gamemode, inventory) VALUES ('" + uuid + "','" + this.gamemode + "','" + inv + "')");
/*    */       }
/*    */     }
/*    */   }
/*    */   
/*    */   private void adventure()
/*    */   {
/* 58 */     this.gamemode = "ADVENTURE";
/* 59 */     for (String uuid : Main.waInventory.keySet()) {
/* 60 */       String inv = (String)Main.waInventory.get(uuid);
/* 61 */       if (DatabaseHelper.selectSQL("SELECT * FROM `" + Main.dbprefix + "inventories` WHERE uuid='" + uuid + "' AND gamemode='" + this.gamemode + "'") > 0) {
/* 62 */         DatabaseHelper.updateSQL("UPDATE `" + Main.dbprefix + "inventories` SET inventory='" + inv + "' WHERE uuid='" + uuid + "' AND gamemode='" + this.gamemode + "'");
/*    */       } else {
/* 64 */         DatabaseHelper.updateSQL("INSERT INTO `" + Main.dbprefix + "inventories` (uuid, gamemode, inventory) VALUES ('" + uuid + "','" + this.gamemode + "','" + inv + "')");
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Zack\Downloads\CreativeControl (6).jar!\me\kubqoa\creativecontrol\tasks\InventoriesToDB.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */