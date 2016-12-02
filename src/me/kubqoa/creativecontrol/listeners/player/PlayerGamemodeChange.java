/*    */ package me.kubqoa.creativecontrol.listeners.player;
/*    */ 
/*    */ import java.util.List;
/*    */ import me.kubqoa.creativecontrol.Main;
/*    */ import me.kubqoa.creativecontrol.api.PlayerAPI;
/*    */ import me.kubqoa.creativecontrol.helpers.Methods;
/*    */ import org.bukkit.GameMode;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.player.PlayerGameModeChangeEvent;
/*    */ 
/*    */ 
/*    */ public class PlayerGamemodeChange
/*    */   implements Listener
/*    */ {
/*    */   @EventHandler
/*    */   public void gamemodeChange(PlayerGameModeChangeEvent event)
/*    */   {
/* 20 */     Player player = event.getPlayer();
/* 21 */     if ((Methods.perm(player, "bypass.gamemode.*")) || (Methods.perm(player, "bypass.*")) || (Methods.perm(player, "*"))) return;
/* 22 */     if ((Main.disabledGamemodes.contains(event.getNewGameMode())) && (!Methods.perm(player, "bypass.gamemode." + event.getNewGameMode().name()))) {
/* 23 */       event.setCancelled(true);
/*    */     } else {
/* 25 */       new PlayerAPI(player).changeGM(event.getNewGameMode());
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Zack\Downloads\CreativeControl (6).jar!\me\kubqoa\creativecontrol\listeners\player\PlayerGamemodeChange.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */