/*    */ package me.kubqoa.creativecontrol.listeners;
/*    */ 
/*    */ import me.kubqoa.creativecontrol.api.BlockAPI;
/*    */ import me.kubqoa.creativecontrol.helpers.Methods;
/*    */ import org.bukkit.block.CreatureSpawner;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.entity.SpawnerSpawnEvent;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ class SpawnerSpawn
/*    */   implements Listener
/*    */ {
/*    */   @EventHandler
/*    */   public void spawner(SpawnerSpawnEvent event)
/*    */   {
/* 19 */     CreatureSpawner spawner = event.getSpawner();
/* 20 */     if ((Methods.exclude(spawner.getLocation())) || (!new BlockAPI(spawner.getBlock()).isCreativeBlock())) return;
/* 21 */     event.setCancelled(true);
/*    */   }
/*    */ }


/* Location:              C:\Users\Zack\Downloads\CreativeControl (6).jar!\me\kubqoa\creativecontrol\listeners\SpawnerSpawn.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */