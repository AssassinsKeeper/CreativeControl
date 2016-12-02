/*    */ package me.kubqoa.creativecontrol.listeners.block;
/*    */ 
/*    */ import java.util.List;
/*    */ import me.kubqoa.creativecontrol.api.BlockAPI;
/*    */ import me.kubqoa.creativecontrol.helpers.Methods;
/*    */ import me.kubqoa.creativecontrol.utils.lists.list_1_7;
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
/*    */ public class BlockFall_1_7 implements Listener
/*    */ {
/*    */   private List<Material> willbreak;
/*    */   
/*    */   public BlockFall_1_7()
/*    */   {
/* 23 */     this.willbreak = list_1_7.willbreak;
/*    */   }
/*    */   
/*    */   @EventHandler
/*    */   public void onBlockFall(EntityChangeBlockEvent event) {
/* 28 */     if (event.getEntityType() == EntityType.FALLING_BLOCK) {
/* 29 */       FallingBlock fallingBlock = (FallingBlock)event.getEntity();
/* 30 */       if (fallingBlock.getMaterial() != Material.AIR) {
/* 31 */         BlockAPI blockAPI = new BlockAPI(event.getBlock());
/* 32 */         Location original = event.getBlock().getLocation();
/* 33 */         if (blockAPI.isCreativeBlock()) {
/* 34 */           Location under = original.clone();
/* 35 */           under.setY(under.getY() - 1.0D);
/* 36 */           while ((under.getBlock().getType() == Material.AIR) || (under.getBlock().getType() == Material.WATER) || (under.getBlock().getType() == Material.STATIONARY_WATER) || (under.getBlock().getType() == Material.LAVA) || (under.getBlock().getType() == Material.STATIONARY_LAVA)) {
/* 37 */             under.setY(under.getY() - 1.0D);
/*    */           }
/* 39 */           if ((fallingBlock.getMaterial() != Material.ANVIL) && (this.willbreak.contains(under.getBlock().getType()))) {
/* 40 */             fallingBlock.setDropItem(false);
/* 41 */             blockAPI.removeBlock();
/* 42 */             return;
/*    */           }
/* 44 */           if (list_universal.willreplace.contains(under.getBlock().getType())) {
/* 45 */             if (under.getBlock().getType() == Material.DOUBLE_PLANT) {
/* 46 */               under.setY(under.getY() - 1.0D);
/*    */             }
/*    */           } else {
/* 49 */             under.setY(under.getY() + 1.0D);
/* 50 */             Methods.console(under.toString());
/*    */           }
/* 52 */           if (!under.equals(original)) {
/* 53 */             blockAPI.blocksAbove();
/* 54 */             byte data = fallingBlock.getBlockData();
/* 55 */             Material material = fallingBlock.getMaterial();
/* 56 */             fallingBlock.remove();
/* 57 */             under.getBlock().setType(material);
/* 58 */             under.getBlock().setData(data);
/* 59 */             blockAPI.updateBlock(original, under);
/*    */           }
/*    */         }
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Zack\Downloads\CreativeControl (6).jar!\me\kubqoa\creativecontrol\listeners\block\BlockFall_1_7.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */