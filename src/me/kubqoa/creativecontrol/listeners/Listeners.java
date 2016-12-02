/*     */ package me.kubqoa.creativecontrol.listeners;
/*     */ 
/*     */ import java.util.List;
/*     */ import me.kubqoa.creativecontrol.Main;
/*     */ import me.kubqoa.creativecontrol.listeners.block.BlockBreakBedrock;
/*     */ import me.kubqoa.creativecontrol.listeners.hanging.HangingBreakByEntity;
/*     */ import me.kubqoa.creativecontrol.listeners.player.InventoryOpen;
/*     */ import me.kubqoa.creativecontrol.listeners.player.PlayerGamemodeChange;
/*     */ import me.kubqoa.creativecontrol.listeners.player.PlayerInteractEntity;
/*     */ import org.bukkit.plugin.PluginManager;
/*     */ import org.bukkit.plugin.java.JavaPlugin;
/*     */ 
/*     */ public class Listeners
/*     */ {
/*     */   private final PluginManager pm;
/*     */   private final JavaPlugin pl;
/*     */   
/*     */   public Listeners(PluginManager pluginManager, JavaPlugin plugin)
/*     */   {
/*  20 */     this.pm = pluginManager;this.pl = plugin;
/*     */   }
/*     */   
/*  23 */   public void init() { if (Main.enabledFeatures.contains("BlockBreak")) {
/*  24 */       this.pm.registerEvents(new me.kubqoa.creativecontrol.listeners.block.BlockBreak(), this.pl);
/*     */     }
/*  26 */     if (Main.enabledFeatures.contains("BedrockBreak")) {
/*  27 */       this.pm.registerEvents(new BlockBreakBedrock(), this.pl);
/*     */     }
/*  29 */     if (Main.enabledFeatures.contains("BlockPlace")) {
/*  30 */       this.pm.registerEvents(new me.kubqoa.creativecontrol.listeners.block.BlockPlace(), this.pl);
/*     */     }
/*  32 */     if (Main.enabledFeatures.contains("BlockFall")) {
/*  33 */       if (!Main.serverVersion.equals("1.7")) {
/*  34 */         this.pm.registerEvents(new me.kubqoa.creativecontrol.listeners.block.BlockFall(), this.pl);
/*     */       } else {
/*  36 */         this.pm.registerEvents(new me.kubqoa.creativecontrol.listeners.block.BlockFall_1_7(), this.pl);
/*     */       }
/*     */     }
/*  39 */     if ((Main.enabledFeatures.contains("BlockExplode")) && (!Main.serverVersion.equals("1.7"))) {
/*  40 */       this.pm.registerEvents(new me.kubqoa.creativecontrol.listeners.block.BlockExplode(), this.pl);
/*     */     }
/*     */     
/*  43 */     if (Main.enabledFeatures.contains("HangingPlace")) {
/*  44 */       this.pm.registerEvents(new me.kubqoa.creativecontrol.listeners.hanging.HangingPlace(), this.pl);
/*     */     }
/*  46 */     if (Main.enabledFeatures.contains("HangingBreak")) {
/*  47 */       this.pm.registerEvents(new me.kubqoa.creativecontrol.listeners.hanging.HangingBreak(), this.pl);
/*  48 */       this.pm.registerEvents(new HangingBreakByEntity(), this.pl);
/*     */     }
/*     */     
/*  51 */     if (Main.enabledFeatures.contains("VehicleCreate")) {
/*  52 */       if (!Main.serverVersion.equals("1.9")) {
/*  53 */         this.pm.registerEvents(new me.kubqoa.creativecontrol.listeners.vehicle.VehicleCreate(), this.pl);
/*     */       } else {
/*  55 */         this.pm.registerEvents(new me.kubqoa.creativecontrol.listeners.vehicle.VehicleCreate_1_9(), this.pl);
/*     */       }
/*     */     }
/*  58 */     if (Main.enabledFeatures.contains("VehicleDestroy")) {
/*  59 */       this.pm.registerEvents(new me.kubqoa.creativecontrol.listeners.vehicle.VehicleDestroy(), this.pl);
/*     */     }
/*  61 */     if (Main.enabledFeatures.contains("VehicleMove")) {
/*  62 */       this.pm.registerEvents(new me.kubqoa.creativecontrol.listeners.vehicle.VehicleMove(), this.pl);
/*     */     }
/*     */     
/*  65 */     if ((Main.enabledFeatures.contains("PlayerArmorStandManipulate")) && (!Main.serverVersion.equals("1.7"))) {
/*  66 */       this.pm.registerEvents(new me.kubqoa.creativecontrol.listeners.player.PlayerArmorStandManipulate(), this.pl);
/*     */     }
/*  68 */     if (Main.enabledFeatures.contains("PlayerCommandRestrict")) {
/*  69 */       this.pm.registerEvents(new me.kubqoa.creativecontrol.listeners.player.PlayerCommandPreprocess(), this.pl);
/*     */     }
/*  71 */     if (Main.enabledFeatures.contains("PlayerDamageEntity")) {
/*  72 */       this.pm.registerEvents(new me.kubqoa.creativecontrol.listeners.player.PlayerDamageEntity(), this.pl);
/*     */     }
/*  74 */     if (Main.enabledFeatures.contains("PlayerDeathNoDrop")) {
/*  75 */       this.pm.registerEvents(new me.kubqoa.creativecontrol.listeners.player.PlayerDeath(), this.pl);
/*     */     }
/*  77 */     if (Main.enabledFeatures.contains("PlayerDropItem")) {
/*  78 */       this.pm.registerEvents(new me.kubqoa.creativecontrol.listeners.player.PlayerDropItem(), this.pl);
/*     */     }
/*  80 */     if (Main.enabledFeatures.contains("PlayerGamemodeChange")) {
/*  81 */       this.pm.registerEvents(new PlayerGamemodeChange(), this.pl);
/*     */     }
/*  83 */     if (Main.enabledFeatures.contains("PlayerJoin")) {
/*  84 */       this.pm.registerEvents(new me.kubqoa.creativecontrol.listeners.player.PlayerJoin(), this.pl);
/*     */     }
/*  86 */     if (Main.enabledFeatures.contains("PlayerPickupItem")) {
/*  87 */       this.pm.registerEvents(new me.kubqoa.creativecontrol.listeners.player.PlayerPickupItem(), this.pl);
/*     */     }
/*  89 */     if (Main.enabledFeatures.contains("PlayerQuit")) {
/*  90 */       this.pm.registerEvents(new me.kubqoa.creativecontrol.listeners.player.PlayerQuit(), this.pl);
/*     */     }
/*  92 */     if (Main.enabledFeatures.contains("PlayerOpenRestrictedInventory")) {
/*  93 */       this.pm.registerEvents(new InventoryOpen(), this.pl);
/*     */     }
/*  95 */     if (Main.enabledFeatures.contains("PlayerBannedItemFromInventory")) {
/*  96 */       this.pm.registerEvents(new me.kubqoa.creativecontrol.listeners.player.InventoryCreative(), this.pl);
/*     */     }
/*  98 */     if (Main.serverVersion.equals("1.9")) {
/*  99 */       this.pm.registerEvents(new me.kubqoa.creativecontrol.listeners.player.PlayerInteractEntity_1_9(), this.pl);
/*     */     } else {
/* 101 */       this.pm.registerEvents(new PlayerInteractEntity(), this.pl);
/*     */     }
/* 103 */     if (Main.serverVersion.equals("1.9")) {
/* 104 */       this.pm.registerEvents(new me.kubqoa.creativecontrol.listeners.player.PlayerInteract_1_9(), this.pl);
/*     */     } else {
/* 106 */       this.pm.registerEvents(new me.kubqoa.creativecontrol.listeners.player.PlayerInteract(), this.pl);
/*     */     }
/*     */     
/* 109 */     if (Main.enabledFeatures.contains("SpawnerSpawnEntity")) {
/* 110 */       this.pm.registerEvents(new SpawnerSpawn(), this.pl);
/*     */     }
/*     */     
/* 113 */     if (Main.enabledFeatures.contains("PistonExtend")) {
/* 114 */       this.pm.registerEvents(new me.kubqoa.creativecontrol.listeners.piston.PistonExtend(), this.pl);
/*     */     }
/* 116 */     if (Main.enabledFeatures.contains("PistonRetract")) {
/* 117 */       if (Main.serverVersion.equals("1.7")) {
/* 118 */         this.pm.registerEvents(new me.kubqoa.creativecontrol.listeners.piston.PistonRetract_1_7(), this.pl);
/*     */       } else {
/* 120 */         this.pm.registerEvents(new me.kubqoa.creativecontrol.listeners.piston.PistonRetract(), this.pl);
/*     */       }
/*     */     }
/*     */     
/* 124 */     this.pm.registerEvents(new LeftClickListener(), this.pl);
/*     */   }
/*     */ }


/* Location:              C:\Users\Zack\Downloads\CreativeControl (6).jar!\me\kubqoa\creativecontrol\listeners\Listeners.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */