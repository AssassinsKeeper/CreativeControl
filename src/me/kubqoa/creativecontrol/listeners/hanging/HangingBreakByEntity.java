/*    */ package me.kubqoa.creativecontrol.listeners.hanging;
/*    */ 
/*    */ import me.kubqoa.creativecontrol.api.HangingAPI;
/*    */ import me.kubqoa.creativecontrol.helpers.Methods;
/*    */ import org.bukkit.GameMode;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.Hanging;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.hanging.HangingBreakByEntityEvent;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class HangingBreakByEntity
/*    */   implements Listener
/*    */ {
/*    */   @EventHandler
/*    */   public void breakByEntityEvent(HangingBreakByEntityEvent event)
/*    */   {
/* 24 */     Entity remover = event.getRemover();
/* 25 */     Location location = event.getEntity().getLocation();
/* 26 */     if (Methods.exclude(location)) return;
/* 27 */     HangingAPI HangingAPI = new HangingAPI(location);
/* 28 */     if (HangingAPI.isCreativeHanging()) {
/* 29 */       HangingAPI.removeHanging();
/* 30 */       event.setCancelled(true);
/* 31 */       if ((remover instanceof Player)) {
/* 32 */         Player player = (Player)remover;
/* 33 */         if ((player.getGameMode() == GameMode.CREATIVE) || (Methods.perm(player, "*")) || (Methods.perm(player, "bypass.*")) || (Methods.perm(player, "bypass.hanging.*")) || (Methods.perm(player, "bypass.hanging.break"))) return;
/* 34 */         if (HangingAPI.canBreak(player)) {
/* 35 */           event.getEntity().remove();
/* 36 */           Methods.send(player, "hanging-break");
/*    */         }
/*    */       } else {
/* 39 */         event.getEntity().remove();
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Zack\Downloads\CreativeControl (6).jar!\me\kubqoa\creativecontrol\listeners\hanging\HangingBreakByEntity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */