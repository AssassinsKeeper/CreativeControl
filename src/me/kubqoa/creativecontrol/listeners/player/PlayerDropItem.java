/*    */ package me.kubqoa.creativecontrol.listeners.player;
/*    */ 
/*    */ import me.kubqoa.creativecontrol.helpers.Methods;
/*    */ import org.bukkit.GameMode;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.player.PlayerDropItemEvent;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PlayerDropItem
/*    */   implements Listener
/*    */ {
/*    */   @EventHandler
/*    */   public void drop(PlayerDropItemEvent event)
/*    */   {
/* 20 */     Player player = event.getPlayer();
/* 21 */     if ((Methods.exclude(player.getLocation())) || (player.getGameMode() != GameMode.CREATIVE) || (Methods.perm(player, "*")) || (Methods.perm(player, "allow.*")) || (Methods.perm(player, "allow.drop"))) return;
/* 22 */     event.setCancelled(true);
/* 23 */     Methods.send(player, "drop");
/*    */   }
/*    */ }


/* Location:              C:\Users\Zack\Downloads\CreativeControl (6).jar!\me\kubqoa\creativecontrol\listeners\player\PlayerDropItem.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */