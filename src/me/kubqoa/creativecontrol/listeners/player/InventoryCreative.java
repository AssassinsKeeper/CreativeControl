/*    */ package me.kubqoa.creativecontrol.listeners.player;
/*    */ 
/*    */ import java.util.List;
/*    */ import me.kubqoa.creativecontrol.Main;
/*    */ import me.kubqoa.creativecontrol.helpers.Methods;
/*    */ import org.bukkit.GameMode;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.inventory.InventoryCreativeEvent;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class InventoryCreative
/*    */   implements Listener
/*    */ {
/*    */   @EventHandler
/*    */   public void inventoryItem(InventoryCreativeEvent event)
/*    */   {
/* 22 */     Player player = (Player)event.getWhoClicked();
/* 23 */     Material material = event.getCursor().getType();
/* 24 */     if ((Methods.exclude(player.getLocation())) || (player.getGameMode() != GameMode.CREATIVE) || (Methods.perm(player, "*")) || (Methods.perm(player, "item.*")) || (Methods.perm(player, "item." + material.name())) || (!Main.items.contains(material))) return;
/* 25 */     event.setCancelled(true);
/* 26 */     Methods.send(player, "disabled-item");
/*    */   }
/*    */ }


/* Location:              C:\Users\Zack\Downloads\CreativeControl (6).jar!\me\kubqoa\creativecontrol\listeners\player\InventoryCreative.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */