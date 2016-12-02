/*    */ package me.kubqoa.creativecontrol.listeners.player;
/*    */ 
/*    */ import java.util.UUID;
/*    */ import me.kubqoa.creativecontrol.Main;
/*    */ import me.kubqoa.creativecontrol.api.PlayerAPI;
/*    */ import me.kubqoa.creativecontrol.helpers.Methods;
/*    */ import me.kubqoa.creativecontrol.helpers.SimpleConfig;
/*    */ import org.bukkit.GameMode;
/*    */ import org.bukkit.Server;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.player.PlayerQuitEvent;
/*    */ 
/*    */ public class PlayerQuit implements Listener
/*    */ {
/*    */   @EventHandler
/*    */   public void leave(PlayerQuitEvent event)
/*    */   {
/* 20 */     Player player = event.getPlayer();
/* 21 */     if ((Methods.perm(player, "*")) || (Methods.perm(player, "bypass.*")) || (Methods.perm(player, "bypass.gamemode.*"))) return;
/* 22 */     GameMode gameMode = Main.thisPlugin.getServer().getDefaultGameMode();
/* 23 */     if (player.getGameMode() != gameMode) new PlayerAPI(player).logInv();
/* 24 */     Main.players.set(player.getUniqueId().toString() + "-gamemode", player.getGameMode().name());
/* 25 */     Main.players.saveConfig();
/*    */   }
/*    */ }


/* Location:              C:\Users\Zack\Downloads\CreativeControl (6).jar!\me\kubqoa\creativecontrol\listeners\player\PlayerQuit.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */