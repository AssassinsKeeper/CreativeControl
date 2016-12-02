/*     */ package me.kubqoa.creativecontrol.listeners.player;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import me.kubqoa.creativecontrol.helpers.Methods;
/*     */ import me.kubqoa.creativecontrol.utils.lists.list_universal;
/*     */ import org.bukkit.GameMode;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.EventHandler;
/*     */ import org.bukkit.event.Listener;
/*     */ import org.bukkit.event.block.Action;
/*     */ import org.bukkit.event.player.PlayerInteractEvent;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.PlayerInventory;
/*     */ import org.bukkit.material.MaterialData;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PlayerInteract_1_9
/*     */   implements Listener
/*     */ {
/*  24 */   List<Material> seeds = new ArrayList();
/*  25 */   List<Material> recordings = new ArrayList();
/*     */   
/*     */   public PlayerInteract_1_9() {
/*  28 */     this.seeds = list_universal.seeds;
/*  29 */     this.recordings = list_universal.recordings;
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onPlayerInteract(PlayerInteractEvent event) {
/*  34 */     Player player = event.getPlayer();
/*  35 */     if ((Methods.exclude(player.getLocation())) || (player.getGameMode() != GameMode.CREATIVE) || (Methods.perm(player, "*")) || (Methods.perm(player, "allow.*"))) return;
/*  36 */     ItemStack hand = player.getInventory().getItemInMainHand();
/*  37 */     Material material = hand.getType();
/*  38 */     if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
/*  39 */       Material clicked = event.getClickedBlock().getType();
/*  40 */       if ((clicked == Material.BEACON) && (!Methods.perm(player, "allow.beacon"))) {
/*  41 */         event.setCancelled(true);
/*  42 */         Methods.send(player, "beacon");
/*  43 */       } else if ((material == Material.EGG) && (!Methods.perm(player, "allow.chickenegg"))) {
/*  44 */         event.setCancelled(true);
/*  45 */         Methods.send(player, "chicken_egg");
/*  46 */       } else if ((material == Material.EXP_BOTTLE) && (!Methods.perm(player, "allow.expbottle"))) {
/*  47 */         event.setCancelled(true);
/*  48 */         Methods.send(player, "exp_bottle");
/*  49 */       } else if ((material == Material.EYE_OF_ENDER) && (clicked == Material.ENDER_PORTAL_FRAME) && (!Methods.perm(player, "allow.fillenderportal"))) {
/*  50 */         event.setCancelled(true);
/*  51 */         Methods.send(player, "ender_portal");
/*  52 */       } else if ((material == Material.EYE_OF_ENDER) && (!Methods.perm(player, "allow.eyeofender"))) {
/*  53 */         event.setCancelled(true);
/*  54 */         Methods.send(player, "eye_of_ender");
/*  55 */       } else if ((material == Material.FISHING_ROD) && (!Methods.perm(player, "allow.fish"))) {
/*  56 */         event.setCancelled(true);
/*  57 */         Methods.send(player, "fish");
/*  58 */       } else if ((material == Material.FLINT_AND_STEEL) && (!Methods.perm(player, "allow.ignite"))) {
/*  59 */         event.setCancelled(true);
/*  60 */         Methods.send(player, "ignite");
/*  61 */       } else if ((this.recordings.contains(material)) && (clicked == Material.JUKEBOX) && (!Methods.perm(player, "allow.jukebox"))) {
/*  62 */         event.setCancelled(true);
/*  63 */         Methods.send(player, "jukebox");
/*  64 */       } else if ((material == Material.MONSTER_EGG) || ((material == Material.MONSTER_EGGS) && (!Methods.perm(player, "allow.monsteregg")))) {
/*  65 */         event.setCancelled(true);
/*  66 */         Methods.send(player, "monster_egg");
/*  67 */       } else if ((this.seeds.contains(material)) && (clicked == Material.SOIL) && (!Methods.perm(player, "allow.plant"))) {
/*  68 */         event.setCancelled(true);
/*  69 */         Methods.send(player, "plant");
/*     */       }
/*  71 */       else if ((hand.getData().toString().equals("BROWN DYE(3)")) && (clicked == Material.LOG) && (event.getClickedBlock().getData() == 3) && (!Methods.perm(player, "allow.plant"))) {
/*  72 */         event.setCancelled(true);
/*  73 */         Methods.send(player, "plant");
/*  74 */       } else if ((material == Material.POTION) && (!Methods.perm(player, "allow.potion"))) {
/*  75 */         event.setCancelled(true);
/*  76 */         Methods.send(player, "potion");
/*  77 */       } else if ((material == Material.SNOW_BALL) && (!Methods.perm(player, "allow.snowball"))) {
/*  78 */         event.setCancelled(true);
/*  79 */         Methods.send(player, "snowball");
/*  80 */       } else if ((material == Material.BOW) && (!Methods.perm(player, "allow.shooting"))) {
/*  81 */         event.setCancelled(true);
/*  82 */         Methods.send(player, "shooting");
/*     */       }
/*  84 */     } else if (event.getAction() == Action.RIGHT_CLICK_AIR) {
/*  85 */       if ((material == Material.EGG) && (!Methods.perm(player, "allow.chickenegg"))) {
/*  86 */         event.setCancelled(true);
/*  87 */         Methods.send(player, "chicken_egg");
/*  88 */       } else if ((material == Material.EXP_BOTTLE) && (!Methods.perm(player, "allow.expbottle"))) {
/*  89 */         event.setCancelled(true);
/*  90 */         Methods.send(player, "exp_bottle");
/*  91 */       } else if ((material == Material.EYE_OF_ENDER) && (!Methods.perm(player, "allow.eyeofender"))) {
/*  92 */         event.setCancelled(true);
/*  93 */         Methods.send(player, "eye_of_ender");
/*  94 */       } else if ((material == Material.MONSTER_EGG) || ((material == Material.MONSTER_EGGS) && (!Methods.perm(player, "allow.monsteregg")))) {
/*  95 */         event.setCancelled(true);
/*  96 */         Methods.send(player, "monster_egg");
/*  97 */       } else if ((material == Material.FISHING_ROD) && (!Methods.perm(player, "allow.fish"))) {
/*  98 */         event.setCancelled(true);
/*  99 */         Methods.send(player, "fish");
/* 100 */       } else if ((material == Material.POTION) && (!Methods.perm(player, "allow.potion"))) {
/* 101 */         event.setCancelled(true);
/* 102 */         Methods.send(player, "potion");
/* 103 */       } else if ((material == Material.SNOW_BALL) && (!Methods.perm(player, "allow.snowball"))) {
/* 104 */         event.setCancelled(true);
/* 105 */         Methods.send(player, "snowball");
/* 106 */       } else if ((material == Material.BOW) && (!Methods.perm(player, "allow.shooting"))) {
/* 107 */         event.setCancelled(true);
/* 108 */         Methods.send(player, "shooting");
/*     */       }
/* 110 */     } else if ((event.getAction() == Action.PHYSICAL) && 
/* 111 */       (event.getClickedBlock().getType() == Material.SOIL) && (!Methods.perm(player, "allow.destroyfarmland"))) {
/* 112 */       event.setCancelled(true);
/* 113 */       Methods.send(player, "destroy-farmland");
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Zack\Downloads\CreativeControl (6).jar!\me\kubqoa\creativecontrol\listeners\player\PlayerInteract_1_9.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */