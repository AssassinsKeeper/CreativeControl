/*    */ package me.kubqoa.creativecontrol.listeners.player;
/*    */ 
/*    */ import java.util.UUID;
/*    */ import me.kubqoa.creativecontrol.Main;
/*    */ import me.kubqoa.creativecontrol.api.PlayerAPI;
/*    */ import me.kubqoa.creativecontrol.helpers.Methods;
/*    */ import me.kubqoa.creativecontrol.helpers.SimpleConfig;
/*    */ import org.bukkit.GameMode;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.player.PlayerJoinEvent;
/*    */ 
/*    */ public class PlayerJoin
/*    */   implements Listener
/*    */ {
/*    */   @EventHandler
/*    */   public void join(PlayerJoinEvent event)
/*    */   {
/* 20 */     Player player = event.getPlayer();
/* 21 */     if ((Methods.perm(player, "*")) || (Methods.perm(player, "bypass.*")) || (Methods.perm(player, "bypass.gamemode.*"))) return;
/* 22 */     String gamemode = Main.players.getString(player.getUniqueId().toString() + "-gamemode");
/* 23 */     if (gamemode == null) return;
/* 24 */     GameMode gm = player.getGameMode();
/* 25 */     GameMode old = GameMode.valueOf(gamemode);
/* 26 */     if (gm != old) {
/* 27 */       PlayerAPI playerAPI = new PlayerAPI(player);
/* 28 */       if ((Main.vault != null) && (!Methods.perm(player, "bypass.gamemode.permissions"))) {
/* 29 */         playerAPI.perms(gm);
/*    */       }
/* 31 */       if (!Methods.perm(player, "bypass.gamemode.inventory")) {
/* 32 */         if (Main.serverVersion.equals("1.7")) {
/* 33 */           playerAPI.setInv(gm);
/* 34 */         } else if (old != GameMode.SPECTATOR) {
/* 35 */           playerAPI.setInv(gm);
/*    */         }
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Zack\Downloads\CreativeControl (6).jar!\me\kubqoa\creativecontrol\listeners\player\PlayerJoin.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */