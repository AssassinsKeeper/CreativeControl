/*    */ package me.kubqoa.creativecontrol.listeners.vehicle;
/*    */ 
/*    */ import me.kubqoa.creativecontrol.api.VehicleAPI;
/*    */ import me.kubqoa.creativecontrol.helpers.Methods;
/*    */ import org.bukkit.entity.Vehicle;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.vehicle.VehicleMoveEvent;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class VehicleMove
/*    */   implements Listener
/*    */ {
/*    */   @EventHandler
/*    */   public void vehicleMove(VehicleMoveEvent event)
/*    */   {
/* 18 */     Vehicle vehicle = event.getVehicle();
/* 19 */     if (Methods.exclude(vehicle.getLocation())) return;
/* 20 */     VehicleAPI vehicleAPI = new VehicleAPI(vehicle);
/* 21 */     vehicleAPI.updateVehicle(event.getFrom(), event.getTo());
/*    */   }
/*    */ }


/* Location:              C:\Users\Zack\Downloads\CreativeControl (6).jar!\me\kubqoa\creativecontrol\listeners\vehicle\VehicleMove.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */