/*    */ package me.kubqoa.creativecontrol.listeners;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import me.kubqoa.creativecontrol.helpers.Methods;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.LivingEntity;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.entity.CreatureSpawnEvent;
/*    */ import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
/*    */ 
/*    */ 
/*    */ public class CreatureSpawn
/*    */   implements Listener
/*    */ {
/* 18 */   public static List<Player> pumpkin = new ArrayList();
/* 19 */   public static List<Player> wither = new ArrayList();
/*    */   
/*    */   @EventHandler
/*    */   public void onCreatureSpawn(CreatureSpawnEvent event) {
/* 23 */     CreatureSpawnEvent.SpawnReason reason = event.getSpawnReason();
/* 24 */     if ((reason.equals(CreatureSpawnEvent.SpawnReason.BUILD_IRONGOLEM)) || ((reason.equals(CreatureSpawnEvent.SpawnReason.BUILD_SNOWMAN)) && (pumpkin.size() > 0))) {
/* 25 */       List<Entity> entities = event.getEntity().getNearbyEntities(7.0D, 7.0D, 7.0D);
/* 26 */       for (Entity entity : entities) {
/* 27 */         if ((entity instanceof Player)) {
/* 28 */           Player player = (Player)entity;
/* 29 */           if ((pumpkin.contains(player)) && (!Methods.perm(player, "*")) && (!Methods.perm(player, "allow.*")) && (!Methods.perm(player, "allow.mobspawn"))) {
/* 30 */             Methods.send(player, "mob-create");
/* 31 */             event.setCancelled(true);
/* 32 */             return;
/*    */           }
/*    */         }
/*    */       }
/* 36 */       pumpkin.clear();
/* 37 */     } else if ((reason.equals(CreatureSpawnEvent.SpawnReason.BUILD_WITHER)) && (wither.size() > 0)) {
/* 38 */       List<Entity> entities = event.getEntity().getNearbyEntities(7.0D, 7.0D, 7.0D);
/* 39 */       for (Entity entity : entities) {
/* 40 */         if ((entity instanceof Player)) {
/* 41 */           Player player = (Player)entity;
/* 42 */           if ((wither.contains(player)) && (!Methods.perm(player, "*")) && (!Methods.perm(player, "allow.*")) && (!Methods.perm(player, "allow.mobspawn"))) {
/* 43 */             Methods.send(player, "mob-create");
/* 44 */             event.setCancelled(true);
/* 45 */             return;
/*    */           }
/*    */         }
/*    */       }
/* 49 */       wither.clear();
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Zack\Downloads\CreativeControl (6).jar!\me\kubqoa\creativecontrol\listeners\CreatureSpawn.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */