/*    */ package me.kubqoa.creativecontrol.listeners.player;
/*    */ 
/*    */ import org.bukkit.GameMode;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.entity.PlayerDeathEvent;
/*    */ 
/*    */ 
/*    */ public class PlayerDeath
/*    */   implements Listener
/*    */ {
/*    */   @EventHandler
/*    */   public void death(PlayerDeathEvent event)
/*    */   {
/* 16 */     Player player = event.getEntity();
/* 17 */     if (player.getGameMode() != GameMode.CREATIVE) return;
/* 18 */     event.setKeepInventory(true);
/*    */   }
/*    */ }


/* Location:              C:\Users\Zack\Downloads\CreativeControl (6).jar!\me\kubqoa\creativecontrol\listeners\player\PlayerDeath.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */