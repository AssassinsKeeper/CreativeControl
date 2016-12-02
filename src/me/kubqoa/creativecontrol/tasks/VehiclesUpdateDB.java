/*    */ package me.kubqoa.creativecontrol.tasks;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.HashMap;
/*    */ import java.util.List;
/*    */ import me.kubqoa.creativecontrol.Main;
/*    */ import me.kubqoa.creativecontrol.helpers.VehicleHelper;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.scheduler.BukkitRunnable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class VehiclesUpdateDB
/*    */   extends BukkitRunnable
/*    */ {
/*    */   public void run()
/*    */   {
/* 19 */     List<Location> locations = new ArrayList(Main.UvehiclesLocation2.keySet());
/* 20 */     for (Location location : locations) {
/* 21 */       VehicleHelper.updateVehicle(location, (Location)Main.UvehiclesLocation2.get(location));
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Zack\Downloads\CreativeControl (6).jar!\me\kubqoa\creativecontrol\tasks\VehiclesUpdateDB.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */