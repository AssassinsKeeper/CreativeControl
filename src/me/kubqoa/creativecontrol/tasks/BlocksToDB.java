/*    */ package me.kubqoa.creativecontrol.tasks;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import me.kubqoa.creativecontrol.Main;
/*    */ import me.kubqoa.creativecontrol.helpers.DatabaseHelper;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.World;
/*    */ import org.bukkit.scheduler.BukkitRunnable;
/*    */ 
/*    */ public class BlocksToDB extends BukkitRunnable
/*    */ {
/*    */   public void run()
/*    */   {
/* 15 */     if (Main.dbtype) {
/* 16 */       String sql = "INSERT INTO `" + Main.dbprefix + "blocks` (x,y,z,world,material) VALUES ";
/* 17 */       for (Location location : Main.WblocksLocation) {
/* 18 */         sql = sql + "(" + location.getX() + "," + location.getY() + "," + location.getZ() + ",'" + location.getWorld().getName() + "','" + Main.WblocksMaterial.get(location) + "'), ";
/*    */       }
/* 20 */       sql = sql.substring(0, sql.length() - 2);
/* 21 */       sql = sql + ";";
/* 22 */       DatabaseHelper.updateSQL(sql);
/*    */     } else {
/* 24 */       String sql = "INSERT INTO `" + Main.dbprefix + "blocks` (x,y,z,world,material) ";
/* 25 */       int i = 0;
/* 26 */       for (Location location : Main.WblocksLocation) {
/* 27 */         if (i == 0) {
/* 28 */           sql = sql + "SELECT '" + location.getX() + "' , '" + location.getY() + "' , '" + location.getZ() + "' , '" + location.getWorld().getName() + "' , '" + Main.WblocksMaterial.get(location) + "' ";
/*    */         } else {
/* 30 */           sql = sql + "UNION ALL SELECT '" + location.getX() + "' , '" + location.getY() + "' , '" + location.getZ() + "' , '" + location.getWorld().getName() + "' , '" + ((Material)Main.WblocksMaterial.get(location)).name() + "' ";
/*    */         }
/* 32 */         i++;
/*    */       }
/* 34 */       sql = sql.substring(0, sql.length() - 1);
/* 35 */       if (i > 0) {
/* 36 */         DatabaseHelper.updateSQL(sql);
/*    */       }
/*    */     }
/* 39 */     Main.WblocksLocation.clear();
/* 40 */     Main.WblocksMaterial.clear();
/*    */   }
/*    */ }


/* Location:              C:\Users\Zack\Downloads\CreativeControl (6).jar!\me\kubqoa\creativecontrol\tasks\BlocksToDB.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */