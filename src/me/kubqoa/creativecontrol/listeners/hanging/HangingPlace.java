/*    */ package me.kubqoa.creativecontrol.listeners.hanging;
/*    */ 
/*    */ import me.kubqoa.creativecontrol.api.HangingAPI;
/*    */ import me.kubqoa.creativecontrol.helpers.Methods;
/*    */ import org.bukkit.GameMode;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.entity.Hanging;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.hanging.HangingPlaceEvent;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class HangingPlace
/*    */   implements Listener
/*    */ {
/*    */   @EventHandler
/*    */   public void placeEvent(HangingPlaceEvent event)
/*    */   {
/* 21 */     Player player = event.getPlayer();
/* 22 */     if ((Methods.exclude(player.getLocation())) || (player.getGameMode() != GameMode.CREATIVE) || (Methods.perm(player, "*")) || (Methods.perm(player, "bypass.*")) || (Methods.perm(player, "bypass.hanging.*")) || (Methods.perm(player, "bypass.hanging.place"))) return;
/* 23 */     Location location = event.getEntity().getLocation();
/* 24 */     HangingAPI HangingAPI = new HangingAPI(location);
/* 25 */     HangingAPI.addHanging();
/*    */   }
/*    */ }


/* Location:              C:\Users\Zack\Downloads\CreativeControl (6).jar!\me\kubqoa\creativecontrol\listeners\hanging\HangingPlace.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */