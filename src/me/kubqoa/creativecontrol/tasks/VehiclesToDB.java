/*    */ package me.kubqoa.creativecontrol.tasks;
/*    */ 
/*    */ import java.util.List;
/*    */ import me.kubqoa.creativecontrol.Main;
/*    */ import me.kubqoa.creativecontrol.helpers.DatabaseHelper;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.World;
/*    */ import org.bukkit.scheduler.BukkitRunnable;
/*    */ 
/*    */ public class VehiclesToDB
/*    */   extends BukkitRunnable
/*    */ {
/*    */   public void run()
/*    */   {
/* 15 */     if (Main.dbtype) {
/* 16 */       String sql = "INSERT INTO `" + Main.dbprefix + "vehicles` (x,y,z,world) VALUES ";
/* 17 */       for (Location location : Main.WvehiclesLocation) {
/* 18 */         sql = sql + "(" + location.getX() + "," + location.getY() + "," + location.getZ() + ",'" + location.getWorld().getName() + "'), ";
/*    */       }
/* 20 */       sql = sql.substring(0, sql.length() - 2);
/* 21 */       sql = sql + ";";
/* 22 */       DatabaseHelper.updateSQL(sql);
/*    */     } else {
/* 24 */       String sql = "INSERT INTO `" + Main.dbprefix + "vehicles` (x,y,z,world) ";
/* 25 */       int i = 0;
/* 26 */       for (Location location : Main.WvehiclesLocation) {
/* 27 */         if (i == 0) {
/* 28 */           sql = sql + "SELECT '" + location.getX() + "' , '" + location.getY() + "' , '" + location.getZ() + "' , '" + location.getWorld().getName() + "' ";
/*    */         } else {
/* 30 */           sql = sql + "UNION ALL SELECT '" + location.getX() + "' , '" + location.getY() + "' , '" + location.getZ() + "' , '" + location.getWorld().getName() + "' ";
/*    */         }
/* 32 */         i++;
/*    */       }
/* 34 */       sql = sql.substring(0, sql.length() - 1);
/* 35 */       DatabaseHelper.updateSQL(sql);
/*    */     }
/* 37 */     Main.WvehiclesLocation.clear();
/*    */   }
/*    */ }


/* Location:              C:\Users\Zack\Downloads\CreativeControl (6).jar!\me\kubqoa\creativecontrol\tasks\VehiclesToDB.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */