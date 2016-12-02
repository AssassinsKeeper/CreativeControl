/*    */ package me.kubqoa.creativecontrol.listeners.player;
/*    */ 
/*    */ import me.kubqoa.creativecontrol.Main;
/*    */ import me.kubqoa.creativecontrol.helpers.Methods;
/*    */ import me.kubqoa.creativecontrol.helpers.SimpleConfig;
/*    */ import org.bukkit.GameMode;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.inventory.InventoryOpenEvent;
/*    */ import org.bukkit.inventory.Inventory;
/*    */ 
/*    */ public class InventoryOpen
/*    */   implements Listener
/*    */ {
/*    */   @EventHandler
/*    */   public void inventoryOpen(InventoryOpenEvent event)
/*    */   {
/* 19 */     Player player = (Player)event.getPlayer();
/* 20 */     String name = event.getInventory().getName().replace("container.", "").replace("mob.", "").toLowerCase();
/* 21 */     if (Main.messages.get("container-" + name) == null) return;
/* 22 */     if ((Methods.exclude(player.getLocation())) || (player.getGameMode() != GameMode.CREATIVE) || (Methods.perm(player, "*")) || (Methods.perm(player, "allow.*")) || (Methods.perm(player, "allow.container.*")) || (Methods.perm(player, "allow.container." + name))) return;
/* 23 */     event.setCancelled(true);
/* 24 */     Methods.send(player, "container-" + name);
/*    */   }
/*    */ }


/* Location:              C:\Users\Zack\Downloads\CreativeControl (6).jar!\me\kubqoa\creativecontrol\listeners\player\InventoryOpen.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */