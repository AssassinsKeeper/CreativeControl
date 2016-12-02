/*    */ package me.kubqoa.creativecontrol.listeners.player;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import me.kubqoa.creativecontrol.helpers.Methods;
/*    */ import org.bukkit.GameMode;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.entity.Animals;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.ItemFrame;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.entity.minecart.StorageMinecart;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.player.PlayerInteractEntityEvent;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ 
/*    */ public class PlayerInteractEntity implements Listener
/*    */ {
/*    */   private final List<Material> animals;
/*    */   
/*    */   public PlayerInteractEntity()
/*    */   {
/* 24 */     this.animals = new ArrayList();
/*    */     
/* 26 */     this.animals.add(Material.SEEDS);
/* 27 */     this.animals.add(Material.CARROT_ITEM);
/* 28 */     this.animals.add(Material.GOLDEN_APPLE);
/* 29 */     this.animals.add(Material.GOLDEN_CARROT);
/* 30 */     this.animals.add(Material.YELLOW_FLOWER);
/* 31 */     this.animals.add(Material.WHEAT);
/*    */   }
/*    */   
/*    */   @EventHandler
/*    */   public void interact(PlayerInteractEntityEvent event) {
/* 36 */     Player player = event.getPlayer();
/* 37 */     if ((Methods.exclude(player.getLocation())) || (player.getGameMode() != GameMode.CREATIVE) || (Methods.perm(player, "*")) || (Methods.perm(player, "allow.*"))) return;
/* 38 */     Material material = player.getItemInHand().getType();
/* 39 */     Entity entity = event.getRightClicked();
/* 40 */     if (((entity instanceof ItemFrame)) && (!Methods.perm(player, "allow.itemframe"))) {
/* 41 */       event.setCancelled(true);
/* 42 */       Methods.send(player, "item_frame");
/* 43 */     } else if (((entity instanceof StorageMinecart)) && (!Methods.perm(player, "allow.container.minecart"))) {
/* 44 */       event.setCancelled(true);
/* 45 */       Methods.send(player, "container-minecart");
/* 46 */     } else if ((entity instanceof Animals)) {
/* 47 */       if ((this.animals.contains(material)) && (!Methods.perm(player, "allow.breed"))) {
/* 48 */         event.setCancelled(true);
/* 49 */         Methods.send(player, "breed");
/* 50 */         return; }
/* 51 */       if ((material == Material.SADDLE) && ((entity instanceof org.bukkit.entity.Pig)) && (!Methods.perm(player, "allow.saddle"))) {
/* 52 */         event.setCancelled(true);
/* 53 */         Methods.send(player, "saddle");
/* 54 */         return;
/*    */       }
/*    */     }
/*    */     
/* 58 */     if ((material == Material.EGG) && (!Methods.perm(player, "allow.chickenegg"))) {
/* 59 */       event.setCancelled(true);
/* 60 */       Methods.send(player, "chicken_egg");
/* 61 */     } else if ((material == Material.EYE_OF_ENDER) && (!Methods.perm(player, "allow.eyeofender"))) {
/* 62 */       event.setCancelled(true);
/* 63 */       Methods.send(player, "eye_of_ender");
/* 64 */     } else if ((material == Material.MONSTER_EGG) || ((material == Material.MONSTER_EGGS) && (!Methods.perm(player, "allow.monsteregg")))) {
/* 65 */       event.setCancelled(true);
/* 66 */       Methods.send(player, "monster_egg");
/* 67 */     } else if ((material == Material.FISHING_ROD) && (!Methods.perm(player, "allow.fish"))) {
/* 68 */       event.setCancelled(true);
/* 69 */       Methods.send(player, "fish");
/* 70 */     } else if ((material == Material.POTION) && (!Methods.perm(player, "allow.potion"))) {
/* 71 */       event.setCancelled(true);
/* 72 */       Methods.send(player, "potion");
/* 73 */     } else if ((material == Material.SNOW_BALL) && (!Methods.perm(player, "allow.snowball"))) {
/* 74 */       event.setCancelled(true);
/* 75 */       Methods.send(player, "snowball");
/* 76 */     } else if ((material == Material.BOW) && (!Methods.perm(player, "allow.shooting"))) {
/* 77 */       event.setCancelled(true);
/* 78 */       Methods.send(player, "shooting");
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Zack\Downloads\CreativeControl (6).jar!\me\kubqoa\creativecontrol\listeners\player\PlayerInteractEntity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */