/*    */ package me.kubqoa.creativecontrol.tasks;
/*    */ 
/*    */ import java.util.List;
/*    */ import me.kubqoa.creativecontrol.Main;
/*    */ import me.kubqoa.creativecontrol.helpers.DatabaseHelper;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.World;
/*    */ import org.bukkit.scheduler.BukkitRunnable;
/*    */ 
/*    */ public class HangingsFromDB
/*    */   extends BukkitRunnable
/*    */ {
/*    */   public void run()
/*    */   {
/* 15 */     if (Main.dbtype) {
/* 16 */       String sql = "DELETE FROM `" + Main.dbprefix + "hangings` WHERE (x,y,z,world) IN (";
/* 17 */       for (Location location : Main.RhangingsLocation) {
/* 18 */         sql = sql + "(" + location.getX() + "," + location.getY() + "," + location.getZ() + ",'" + location.getWorld().getName() + "'),";
/*    */       }
/* 20 */       sql = sql.substring(0, sql.length() - 1);
/* 21 */       sql = sql + ")";
/* 22 */       DatabaseHelper.updateSQL(sql);
/*    */     } else {
/* 24 */       String sql = "DELETE FROM `" + Main.dbprefix + "hangings` WHERE ";
/* 25 */       for (Location location : Main.RhangingsLocation) {
/* 26 */         sql = sql + "(x=" + location.getX() + " AND y=" + location.getY() + " AND z=" + location.getZ() + " AND world='" + location.getWorld().getName() + "') OR ";
/*    */       }
/* 28 */       sql = sql.substring(0, sql.length() - 4);
/* 29 */       DatabaseHelper.updateSQL(sql);
/*    */     }
/* 31 */     Main.RhangingsLocation.clear();
/*    */   }
/*    */ }


/* Location:              C:\Users\Zack\Downloads\CreativeControl (6).jar!\me\kubqoa\creativecontrol\tasks\HangingsFromDB.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */