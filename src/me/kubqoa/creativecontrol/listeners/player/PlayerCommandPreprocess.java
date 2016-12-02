/*    */ package me.kubqoa.creativecontrol.listeners.player;
/*    */ 
/*    */ import java.util.List;
/*    */ import me.kubqoa.creativecontrol.Main;
/*    */ import me.kubqoa.creativecontrol.helpers.Methods;
/*    */ import me.kubqoa.creativecontrol.helpers.SimpleConfig;
/*    */ import org.bukkit.ChatColor;
/*    */ import org.bukkit.GameMode;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.player.PlayerCommandPreprocessEvent;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PlayerCommandPreprocess
/*    */   implements Listener
/*    */ {
/*    */   @EventHandler
/*    */   public void command(PlayerCommandPreprocessEvent event)
/*    */   {
/* 22 */     Player player = event.getPlayer();
/* 23 */     String cmd = event.getMessage().split(" ")[0];
/* 24 */     if ((player.getGameMode() != GameMode.CREATIVE) || (Methods.exclude(player.getLocation())) || (Methods.perm(player, "cmd." + cmd)) || (Methods.perm(player, "cmd.*")) || (Methods.perm(player, "*"))) return;
/* 25 */     if (Main.excludeCMD.contains(cmd)) {
/* 26 */       event.setCancelled(true);
/* 27 */       Methods.sendMsg(player, ChatColor.translateAlternateColorCodes('&', Main.messages.getString("command").replaceAll("%cmd%", cmd)));
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Zack\Downloads\CreativeControl (6).jar!\me\kubqoa\creativecontrol\listeners\player\PlayerCommandPreprocess.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */