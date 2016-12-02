/*    */ package me.kubqoa.creativecontrol.listeners.block;
/*    */ 
/*    */ import me.kubqoa.creativecontrol.api.BlockAPI;
/*    */ import me.kubqoa.creativecontrol.helpers.Methods;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.block.BlockExplodeEvent;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BlockExplode
/*    */   implements Listener
/*    */ {
/*    */   @EventHandler
/*    */   public void onBlockExplode(BlockExplodeEvent event)
/*    */   {
/* 19 */     Block block = event.getBlock();
/* 20 */     if (Methods.exclude(block.getLocation())) return;
/* 21 */     BlockAPI blockAPI = new BlockAPI(block);
/* 22 */     if (blockAPI.isCreativeBlock()) {
/* 23 */       event.setCancelled(true);
/* 24 */       block.setType(Material.AIR);
/* 25 */       blockAPI.removeBlock();
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Zack\Downloads\CreativeControl (6).jar!\me\kubqoa\creativecontrol\listeners\block\BlockExplode.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */