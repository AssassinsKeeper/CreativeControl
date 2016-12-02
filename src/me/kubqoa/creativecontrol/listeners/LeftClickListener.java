/*    */ package me.kubqoa.creativecontrol.listeners;
/*    */ 
/*    */ import java.util.List;
/*    */ import me.kubqoa.creativecontrol.Main;
/*    */ import me.kubqoa.creativecontrol.api.BlockAPI;
/*    */ import me.kubqoa.creativecontrol.helpers.Methods;
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.block.Action;
/*    */ import org.bukkit.event.player.PlayerInteractEvent;
/*    */ 
/*    */ class LeftClickListener
/*    */   implements Listener
/*    */ {
/*    */   @EventHandler
/*    */   public void leftClick(PlayerInteractEvent event)
/*    */   {
/* 20 */     Player player = event.getPlayer();
/* 21 */     Block block = event.getClickedBlock();
/* 22 */     if (event.getAction() == Action.LEFT_CLICK_BLOCK) {
/* 23 */       if (Main.addMode.contains(player)) {
/* 24 */         Main.addMode.remove(player);
/* 25 */         event.setCancelled(true);
/* 26 */         BlockAPI blockAPI = new BlockAPI(block);
/* 27 */         if (blockAPI.isCreativeBlock()) {
/* 28 */           Methods.sendMsg(player, "&4This block is already in database!");
/* 29 */           return;
/*    */         }
/* 31 */         blockAPI.addBlock(block.getType());
/* 32 */         Methods.sendMsg(player, "&4Block added!");
/* 33 */       } else if (Main.removeMode.contains(player)) {
/* 34 */         Main.removeMode.remove(player);
/* 35 */         event.setCancelled(true);
/* 36 */         BlockAPI blockAPI = new BlockAPI(block);
/* 37 */         if (!blockAPI.isCreativeBlock()) {
/* 38 */           Methods.sendMsg(player, "&4This block is not in database, so it couldn't have been deleted!");
/* 39 */           return;
/*    */         }
/* 41 */         blockAPI.removeBlock();
/* 42 */         Methods.sendMsg(player, "&4Block removed!");
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Zack\Downloads\CreativeControl (6).jar!\me\kubqoa\creativecontrol\listeners\LeftClickListener.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */