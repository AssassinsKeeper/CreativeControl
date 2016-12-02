/*    */ package me.kubqoa.creativecontrol.listeners.block;
/*    */ 
/*    */ import java.util.List;
/*    */ import me.kubqoa.creativecontrol.Main;
/*    */ import me.kubqoa.creativecontrol.api.BlockAPI;
/*    */ import me.kubqoa.creativecontrol.helpers.Methods;
/*    */ import me.kubqoa.creativecontrol.listeners.CreatureSpawn;
/*    */ import org.bukkit.GameMode;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.block.BlockPlaceEvent;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BlockPlace
/*    */   implements Listener
/*    */ {
/*    */   @EventHandler
/*    */   public void blockPlaceEvent(BlockPlaceEvent event)
/*    */   {
/* 27 */     Player player = event.getPlayer();
/* 28 */     if ((player.getGameMode() != GameMode.CREATIVE) || (Methods.perm(player, "*")) || (Methods.perm(player, "bypass.*")) || (Methods.perm(player, "bypass.place"))) return;
/* 29 */     Block block = event.getBlock();
/* 30 */     if (Methods.exclude(block.getLocation())) return;
/* 31 */     Material material = block.getType();
/* 32 */     if (Main.noTracking.contains(material)) return;
/* 33 */     if (Main.items.contains(material)) {
/* 34 */       Methods.send(player, "disabled-block");
/* 35 */       event.setCancelled(true);
/* 36 */       return;
/*    */     }
/* 38 */     if (material == Material.PUMPKIN) {
/* 39 */       CreatureSpawn.pumpkin.add(player);
/* 40 */     } else if ((material == Material.SKULL) && (block.getData() == 1)) {
/* 41 */       CreatureSpawn.wither.add(player);
/*    */     }
/* 43 */     BlockAPI blockAPI = new BlockAPI(block);
/* 44 */     if (!blockAPI.canBreak(player)) return;
/* 45 */     blockAPI.addBlock(material);
/*    */   }
/*    */ }


/* Location:              C:\Users\Zack\Downloads\CreativeControl (6).jar!\me\kubqoa\creativecontrol\listeners\block\BlockPlace.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */