/*    */ package me.kubqoa.creativecontrol.listeners.player;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import me.kubqoa.creativecontrol.helpers.Methods;
/*    */ import org.bukkit.GameMode;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.ItemFrame;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.player.PlayerInteractEntityEvent;
/*    */ 
/*    */ public class PlayerInteractEntity_1_9 implements org.bukkit.event.Listener
/*    */ {
/*    */   private final List<Material> animals;
/*    */   
/*    */   public PlayerInteractEntity_1_9()
/*    */   {
/* 20 */     this.animals = new ArrayList();
/*    */     
/* 22 */     this.animals.add(Material.SEEDS);
/* 23 */     this.animals.add(Material.CARROT_ITEM);
/* 24 */     this.animals.add(Material.GOLDEN_APPLE);
/* 25 */     this.animals.add(Material.GOLDEN_CARROT);
/* 26 */     this.animals.add(Material.YELLOW_FLOWER);
/* 27 */     this.animals.add(Material.WHEAT);
/*    */   }
/*    */   
/*    */   @EventHandler
/*    */   public void interact(PlayerInteractEntityEvent event) {
/* 32 */     Player player = event.getPlayer();
/* 33 */     if ((Methods.exclude(player.getLocation())) || (player.getGameMode() != GameMode.CREATIVE) || (Methods.perm(player, "*")) || (Methods.perm(player, "allow.*"))) return;
/* 34 */     Material material = player.getInventory().getItemInMainHand().getType();
/* 35 */     Entity entity = event.getRightClicked();
/* 36 */     if (((entity instanceof ItemFrame)) && (!Methods.perm(player, "allow.itemframe"))) {
/* 37 */       event.setCancelled(true);
/* 38 */       Methods.send(player, "item_frame");
/* 39 */     } else if (((entity instanceof org.bukkit.entity.minecart.StorageMinecart)) && (!Methods.perm(player, "allow.container.minecart"))) {
/* 40 */       event.setCancelled(true);
/* 41 */       Methods.send(player, "container-minecart");
/* 42 */     } else if ((entity instanceof org.bukkit.entity.Animals)) {
/* 43 */       if ((this.animals.contains(material)) && (!Methods.perm(player, "allow.breed"))) {
/* 44 */         event.setCancelled(true);
/* 45 */         Methods.send(player, "breed");
/* 46 */         return; }
/* 47 */       if ((material == Material.SADDLE) && ((entity instanceof org.bukkit.entity.Pig)) && (!Methods.perm(player, "allow.saddle"))) {
/* 48 */         event.setCancelled(true);
/* 49 */         Methods.send(player, "saddle");
/* 50 */         return;
/*    */       }
/*    */     }
/* 53 */     if ((material == Material.EGG) && (!Methods.perm(player, "allow.chickenegg"))) {
/* 54 */       event.setCancelled(true);
/* 55 */       Methods.send(player, "chicken_egg");
/* 56 */     } else if ((material == Material.EYE_OF_ENDER) && (!Methods.perm(player, "allow.eyeofender"))) {
/* 57 */       event.setCancelled(true);
/* 58 */       Methods.send(player, "eye_of_ender");
/* 59 */     } else if ((material == Material.MONSTER_EGG) || ((material == Material.MONSTER_EGGS) && (!Methods.perm(player, "allow.monsteregg")))) {
/* 60 */       event.setCancelled(true);
/* 61 */       Methods.send(player, "monster_egg");
/* 62 */     } else if ((material == Material.FISHING_ROD) && (!Methods.perm(player, "allow.fish"))) {
/* 63 */       event.setCancelled(true);
/* 64 */       Methods.send(player, "fish");
/* 65 */     } else if ((material == Material.POTION) && (!Methods.perm(player, "allow.potion"))) {
/* 66 */       event.setCancelled(true);
/* 67 */       Methods.send(player, "potion");
/* 68 */     } else if ((material == Material.SNOW_BALL) && (!Methods.perm(player, "allow.snowball"))) {
/* 69 */       event.setCancelled(true);
/* 70 */       Methods.send(player, "snowball");
/* 71 */     } else if ((material == Material.BOW) && (!Methods.perm(player, "allow.shooting"))) {
/* 72 */       event.setCancelled(true);
/* 73 */       Methods.send(player, "shooting");
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Zack\Downloads\CreativeControl (6).jar!\me\kubqoa\creativecontrol\listeners\player\PlayerInteractEntity_1_9.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */