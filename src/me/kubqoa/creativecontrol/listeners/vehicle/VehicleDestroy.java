/*    */ package me.kubqoa.creativecontrol.listeners.vehicle;
/*    */ 
/*    */ import me.kubqoa.creativecontrol.api.VehicleAPI;
/*    */ import me.kubqoa.creativecontrol.helpers.Methods;
/*    */ import org.bukkit.GameMode;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.entity.Vehicle;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.vehicle.VehicleDestroyEvent;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class VehicleDestroy
/*    */   implements Listener
/*    */ {
/*    */   @EventHandler
/*    */   public void vehicleDestroy(VehicleDestroyEvent event)
/*    */   {
/* 21 */     Vehicle vehicle = event.getVehicle();
/* 22 */     if (Methods.exclude(vehicle.getLocation())) return;
/* 23 */     VehicleAPI vehicleAPI = new VehicleAPI(vehicle);
/* 24 */     if (vehicleAPI.isCreativeVehicle()) {
/* 25 */       vehicleAPI.removeVehicle();
/* 26 */       if ((event.getAttacker() instanceof Player)) {
/* 27 */         Player player = (Player)event.getAttacker();
/* 28 */         if ((player.getGameMode() != GameMode.SURVIVAL) || (Methods.perm(player, "*")) || (Methods.perm(player, "bypass.*")) || (Methods.perm(player, "bypass.vehicles.*")) || (Methods.perm(player, "bypass.vehicles.break"))) return;
/* 29 */         event.setCancelled(true);
/* 30 */         vehicle.remove();
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Zack\Downloads\CreativeControl (6).jar!\me\kubqoa\creativecontrol\listeners\vehicle\VehicleDestroy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */