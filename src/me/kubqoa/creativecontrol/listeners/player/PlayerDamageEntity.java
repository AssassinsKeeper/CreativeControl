/*    */ package me.kubqoa.creativecontrol.listeners.player;
/*    */ 
/*    */ import me.kubqoa.creativecontrol.helpers.Methods;
/*    */ import org.bukkit.GameMode;
/*    */ import org.bukkit.entity.Damageable;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.entity.EntityDamageByEntityEvent;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PlayerDamageEntity
/*    */   implements Listener
/*    */ {
/*    */   @EventHandler
/*    */   public void hit(EntityDamageByEntityEvent event)
/*    */   {
/* 22 */     Entity damager = event.getDamager();
/* 23 */     Entity entity = event.getEntity();
/* 24 */     if (event.isCancelled()) return;
/* 25 */     if (((damager instanceof Player)) && ((entity instanceof Damageable))) {
/* 26 */       String name = entity.getName().toLowerCase();
/* 27 */       name = name.replaceAll(" ", "-");
/* 28 */       if (name.contains("endercrystal")) name = "ender-crystal";
/* 29 */       Player player = (Player)damager;
/* 30 */       if ((Methods.exclude(player.getLocation())) || (player.getGameMode() != GameMode.CREATIVE) || (Methods.perm(player, "*")) || (Methods.perm(player, "allow.*")) || (Methods.perm(player, "allow.damage.*")) || (Methods.perm(player, "allow.damage." + name))) return;
/* 31 */       event.setCancelled(true);
/* 32 */       Methods.send(player, "damage-" + name);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Zack\Downloads\CreativeControl (6).jar!\me\kubqoa\creativecontrol\listeners\player\PlayerDamageEntity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */