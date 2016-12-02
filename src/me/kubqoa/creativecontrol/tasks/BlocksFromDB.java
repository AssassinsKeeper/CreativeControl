/*    */ package me.kubqoa.creativecontrol.tasks;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import java.util.List;
/*    */ import me.kubqoa.creativecontrol.Main;
/*    */ import me.kubqoa.creativecontrol.helpers.DatabaseHelper;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.World;
/*    */ import org.bukkit.scheduler.BukkitRunnable;
/*    */ 
/*    */ public class BlocksFromDB extends BukkitRunnable
/*    */ {
/*    */   public void run()
/*    */   {
/* 15 */     if (Main.dbtype) {
/* 16 */       String sql = "DELETE FROM `" + Main.dbprefix + "blocks` WHERE (x,y,z,world,material) IN (";
/* 17 */       for (Location location : Main.RblocksLocation) {
/* 18 */         sql = sql + "(" + location.getX() + "," + location.getY() + "," + location.getZ() + ",'" + location.getWorld().getName() + "','" + Main.RblocksMaterial.get(location) + "'),";
/*    */       }
/* 20 */       sql = sql.substring(0, sql.length() - 1);
/* 21 */       sql = sql + ")";
/* 22 */       DatabaseHelper.updateSQL(sql);
/*    */     } else {
/* 24 */       String sql = "DELETE FROM `" + Main.dbprefix + "blocks` WHERE ";
/* 25 */       for (Location location : Main.RblocksLocation) {
/* 26 */         sql = sql + "(x=" + location.getX() + " AND y=" + location.getY() + " AND z=" + location.getZ() + " AND world='" + location.getWorld().getName() + "' AND material='" + Main.RblocksMaterial.get(location) + "') OR ";
/*    */       }
/* 28 */       sql = sql.substring(0, sql.length() - 4);
/* 29 */       DatabaseHelper.updateSQL(sql);
/*    */     }
/* 31 */     Main.RblocksLocation.clear();
/* 32 */     Main.RblocksMaterial.clear();
/*    */   }
/*    */ }


/* Location:              C:\Users\Zack\Downloads\CreativeControl (6).jar!\me\kubqoa\creativecontrol\tasks\BlocksFromDB.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */