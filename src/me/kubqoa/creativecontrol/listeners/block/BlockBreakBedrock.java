/*    */ package me.kubqoa.creativecontrol.listeners.block;
/*    */ 
/*    */ import me.kubqoa.creativecontrol.helpers.Methods;
/*    */ import org.bukkit.GameMode;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.World;
/*    */ import org.bukkit.World.Environment;
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.block.BlockBreakEvent;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BlockBreakBedrock
/*    */   implements Listener
/*    */ {
/*    */   @EventHandler
/*    */   public void bedrock(BlockBreakEvent event)
/*    */   {
/* 23 */     Player player = event.getPlayer();
/* 24 */     Block block = event.getBlock();
/* 25 */     if ((Methods.exclude(block.getLocation())) || (player.getGameMode() != GameMode.CREATIVE) || (Methods.perm(player, "*")) || (Methods.perm(player, "bypass.*")) || (Methods.perm(player, "bypass.bedrock"))) return;
/* 26 */     if (block.getType() == Material.BEDROCK) {
/* 27 */       double y = block.getLocation().getY();
/* 28 */       World.Environment world = block.getWorld().getEnvironment();
/* 29 */       if (world == World.Environment.NETHER) {
/* 30 */         if (y >= 122.0D) {
/* 31 */           event.setCancelled(true);
/* 32 */           Methods.send(player, "block-break-bedrock");
/*    */         }
/* 34 */       } else if ((world == World.Environment.NORMAL) && 
/* 35 */         (y <= 5.0D)) {
/* 36 */         event.setCancelled(true);
/* 37 */         Methods.send(player, "block-break-bedrock");
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Zack\Downloads\CreativeControl (6).jar!\me\kubqoa\creativecontrol\listeners\block\BlockBreakBedrock.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */