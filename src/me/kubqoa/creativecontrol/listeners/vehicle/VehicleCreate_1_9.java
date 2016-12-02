/*    */ package me.kubqoa.creativecontrol.listeners.vehicle;
/*    */ 
/*    */ import java.util.List;
/*    */ import me.kubqoa.creativecontrol.api.VehicleAPI;
/*    */ import me.kubqoa.creativecontrol.helpers.Methods;
/*    */ import me.kubqoa.creativecontrol.utils.lists.list_universal;
/*    */ import org.bukkit.GameMode;
/*    */ import org.bukkit.entity.Boat;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.entity.Vehicle;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.vehicle.VehicleCreateEvent;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import org.bukkit.inventory.PlayerInventory;
/*    */ 
/*    */ 
/*    */ public class VehicleCreate_1_9
/*    */   implements Listener
/*    */ {
/*    */   @EventHandler
/*    */   public void vehicleCreate(VehicleCreateEvent event)
/*    */   {
/* 25 */     Vehicle vehicle = event.getVehicle();
/*    */     
/* 27 */     if (Methods.exclude(vehicle.getLocation())) return;
/* 28 */     if ((vehicle instanceof Boat)) return;
/* 29 */     for (Entity ent : event.getVehicle().getNearbyEntities(4.0D, 4.0D, 4.0D)) {
/* 30 */       if ((ent instanceof Player)) {
/* 31 */         Player player = (Player)ent;
/* 32 */         if ((player.getGameMode() == GameMode.CREATIVE) && (!Methods.perm(player, "*")) && (!Methods.perm(player, "bypass.*")) && (!Methods.perm(player, "bypass.vehicles.*")) && (!Methods.perm(player, "bypass.vehicles.place")) && 
/* 33 */           (list_universal.minecarts.contains(player.getInventory().getItemInMainHand().getType()))) {
/* 34 */           new VehicleAPI(vehicle).addVehicle();
/* 35 */           return;
/*    */         }
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Zack\Downloads\CreativeControl (6).jar!\me\kubqoa\creativecontrol\listeners\vehicle\VehicleCreate_1_9.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */