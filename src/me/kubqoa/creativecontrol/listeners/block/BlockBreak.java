/*    */ package me.kubqoa.creativecontrol.listeners.block;
/*    */ 
/*    */ import me.kubqoa.creativecontrol.Main;
/*    */ import me.kubqoa.creativecontrol.api.BlockAPI;
/*    */ import me.kubqoa.creativecontrol.helpers.Methods;
/*    */ import me.kubqoa.creativecontrol.helpers.SimpleConfig;
/*    */ import org.bukkit.GameMode;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.EventPriority;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.block.BlockBreakEvent;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BlockBreak
/*    */   implements Listener
/*    */ {
/*    */   @EventHandler(priority=EventPriority.HIGH)
/*    */   public void blockBreakEvent(BlockBreakEvent event)
/*    */   {
/* 25 */     Player player = event.getPlayer();
/* 26 */     Block block = event.getBlock();
/* 27 */     if ((Methods.perm(player, "*")) || (Methods.perm(player, "bypass.*")) || (Methods.perm(player, "bypass.break"))) return;
/* 28 */     if (Methods.exclude(block.getLocation())) return;
/* 29 */     BlockAPI blockAPI = new BlockAPI(block);
/* 30 */     if (!blockAPI.canBreak(player)) return;
/* 31 */     if (blockAPI.isCreativeBlock()) {
/* 32 */       blockAPI.removeBlock();
/* 33 */       if (player.getGameMode() != GameMode.CREATIVE) {
/* 34 */         event.setCancelled(true);
/* 35 */         block.setType(Material.AIR);
/* 36 */         Methods.sendMsg(player, Main.messages.getString("block-break"));
/*    */       }
/*    */     }
/* 39 */     blockAPI.blocksAbove();
/*    */   }
/*    */ }


/* Location:              C:\Users\Zack\Downloads\CreativeControl (6).jar!\me\kubqoa\creativecontrol\listeners\block\BlockBreak.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */