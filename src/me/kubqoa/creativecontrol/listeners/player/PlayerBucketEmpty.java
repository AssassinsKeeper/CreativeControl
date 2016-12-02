/*    */ package me.kubqoa.creativecontrol.listeners.player;
/*    */ 
/*    */ import java.util.List;
/*    */ import me.kubqoa.creativecontrol.Main;
/*    */ import me.kubqoa.creativecontrol.api.BlockAPI;
/*    */ import me.kubqoa.creativecontrol.helpers.Methods;
/*    */ import org.bukkit.GameMode;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.player.PlayerBucketEmptyEvent;
/*    */ 
/*    */ 
/*    */ public class PlayerBucketEmpty
/*    */   implements Listener
/*    */ {
/*    */   @EventHandler
/*    */   public void onPlayerBucketEmpty(PlayerBucketEmptyEvent event)
/*    */   {
/* 23 */     Player player = event.getPlayer();
/* 24 */     if ((player.getGameMode() != GameMode.CREATIVE) || (Methods.perm(player, "*")) || (Methods.perm(player, "bypass.*")) || (Methods.perm(player, "bypass.bucketplace"))) return;
/* 25 */     Material material = event.getBucket();
/* 26 */     if (Main.noTracking.contains(material)) return;
/* 27 */     if (Main.items.contains(material)) {
/* 28 */       Methods.send(player, "disabled-block");
/* 29 */       event.setCancelled(true);
/* 30 */       return;
/*    */     }
/* 32 */     Block block = event.getBlockClicked().getRelative(event.getBlockFace());
/* 33 */     Methods.console(block.getLocation().toString());
/* 34 */     Methods.console(block.getType().name());
/* 35 */     BlockAPI blockAPI = new BlockAPI(block);
/* 36 */     if (blockAPI.canBreak(player)) {
/* 37 */       blockAPI.addBlock(material);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Zack\Downloads\CreativeControl (6).jar!\me\kubqoa\creativecontrol\listeners\player\PlayerBucketEmpty.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */