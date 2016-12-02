/*    */ package me.kubqoa.creativecontrol.listeners.block;
/*    */ 
/*    */ import java.util.List;
/*    */ import me.kubqoa.creativecontrol.Main;
/*    */ import me.kubqoa.creativecontrol.api.BlockAPI;
/*    */ import me.kubqoa.creativecontrol.helpers.Methods;
/*    */ import me.kubqoa.creativecontrol.utils.lists.list_1_8;
/*    */ import me.kubqoa.creativecontrol.utils.lists.list_1_9;
/*    */ import me.kubqoa.creativecontrol.utils.lists.list_universal;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.entity.EntityType;
/*    */ import org.bukkit.entity.FallingBlock;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.entity.EntityChangeBlockEvent;
/*    */ 
/*    */ public class BlockFall implements Listener
/*    */ {
/*    */   private List<Material> willbreak;
/*    */   
/*    */   public BlockFall()
/*    */   {
/* 25 */     if (Main.serverVersion.equals("1.9")) {
/* 26 */       this.willbreak = list_1_9.willbreak;
/* 27 */     } else if (Main.serverVersion.equals("1.8")) {
/* 28 */       this.willbreak = list_1_8.willbreak;
/*    */     }
/*    */   }
/*    */   
/*    */   @EventHandler
/*    */   public void onBlockFall(EntityChangeBlockEvent event) {
/* 34 */     if (event.getEntityType() == EntityType.FALLING_BLOCK) {
/* 35 */       FallingBlock fallingBlock = (FallingBlock)event.getEntity();
/* 36 */       if (fallingBlock.getMaterial() != Material.AIR) {
/* 37 */         BlockAPI blockAPI = new BlockAPI(event.getBlock());
/* 38 */         Location original = event.getBlock().getLocation();
/* 39 */         if (blockAPI.isCreativeBlock()) {
/* 40 */           Location under = original.clone();
/* 41 */           under.setY(under.getY() - 1.0D);
/* 42 */           while ((under.getBlock().getType() == Material.AIR) || (under.getBlock().getType() == Material.WATER) || (under.getBlock().getType() == Material.STATIONARY_WATER) || (under.getBlock().getType() == Material.LAVA) || (under.getBlock().getType() == Material.STATIONARY_LAVA)) {
/* 43 */             under.setY(under.getY() - 1.0D);
/*    */           }
/* 45 */           if ((fallingBlock.getMaterial() != Material.ANVIL) && (this.willbreak.contains(under.getBlock().getType()))) {
/* 46 */             fallingBlock.setDropItem(false);
/* 47 */             blockAPI.removeBlock();
/* 48 */             return;
/*    */           }
/* 50 */           if (list_universal.willreplace.contains(under.getBlock().getType())) {
/* 51 */             if (under.getBlock().getType() == Material.DOUBLE_PLANT) {
/* 52 */               under.setY(under.getY() - 1.0D);
/*    */             }
/*    */           } else {
/* 55 */             under.setY(under.getY() + 1.0D);
/* 56 */             Methods.console(under.toString());
/*    */           }
/* 58 */           if (!under.equals(original)) {
/* 59 */             blockAPI.blocksAbove();
/* 60 */             fallingBlock.teleport(under);
/* 61 */             blockAPI.updateBlock(original, under);
/*    */           }
/*    */         }
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Zack\Downloads\CreativeControl (6).jar!\me\kubqoa\creativecontrol\listeners\block\BlockFall.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */