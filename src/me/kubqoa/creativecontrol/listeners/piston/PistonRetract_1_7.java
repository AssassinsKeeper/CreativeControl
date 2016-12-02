/*     */ package me.kubqoa.creativecontrol.listeners.piston;
/*     */ 
/*     */ import java.util.List;
/*     */ import me.kubqoa.creativecontrol.Main;
/*     */ import me.kubqoa.creativecontrol.api.BlockAPI;
/*     */ import me.kubqoa.creativecontrol.helpers.Methods;
/*     */ import me.kubqoa.creativecontrol.utils.lists.list_1_7;
/*     */ import me.kubqoa.creativecontrol.utils.lists.list_1_8;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.event.EventHandler;
/*     */ import org.bukkit.event.Listener;
/*     */ import org.bukkit.event.block.BlockPistonRetractEvent;
/*     */ 
/*     */ public class PistonRetract_1_7 implements Listener
/*     */ {
/*     */   private List<Material> list;
/*     */   
/*     */   public PistonRetract_1_7()
/*     */   {
/*  22 */     if (Main.serverVersion.equals("1.9")) {
/*  23 */       this.list = me.kubqoa.creativecontrol.utils.lists.list_1_9.list;
/*  24 */     } else if (Main.serverVersion.equals("1.8")) {
/*  25 */       this.list = list_1_8.list;
/*  26 */     } else if (Main.serverVersion.equals("1.7")) {
/*  27 */       this.list = list_1_7.list;
/*     */     }
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void retract(BlockPistonRetractEvent event) {
/*  33 */     if (Methods.exclude(event.getBlock().getLocation())) return;
/*     */     Block block;
/*     */     BlockAPI blockAPI;
/*     */     Location location;
/*  37 */     switch (event.getDirection()) {
/*     */     case SOUTH: 
/*  39 */       block = event.getBlock();
/*  40 */       blockAPI = new BlockAPI(block);
/*  41 */       location = block.getLocation();
/*  42 */       if (blockAPI.isCreativeBlock()) {
/*  43 */         if (this.list.contains(block.getType())) {
/*  44 */           blockAPI.removeBlock();
/*  45 */           block.setType(Material.AIR);
/*     */         } else {
/*  47 */           blockAPI.updateBlock(location, new Location(location.getWorld(), location.getX(), location.getY(), location.getZ() + 1.0D));
/*     */         }
/*     */       }
/*     */       break;
/*     */     case NORTH: 
/*  52 */       block = event.getBlock();
/*  53 */       blockAPI = new BlockAPI(block);
/*  54 */       location = block.getLocation();
/*  55 */       if (blockAPI.isCreativeBlock()) {
/*  56 */         if (this.list.contains(block.getType())) {
/*  57 */           blockAPI.removeBlock();
/*  58 */           block.setType(Material.AIR);
/*     */         } else {
/*  60 */           blockAPI.updateBlock(location, new Location(location.getWorld(), location.getX(), location.getY(), location.getZ() - 1.0D));
/*     */         }
/*     */       }
/*     */       break;
/*     */     case EAST: 
/*  65 */       block = event.getBlock();
/*  66 */       blockAPI = new BlockAPI(block);
/*  67 */       location = block.getLocation();
/*  68 */       if (blockAPI.isCreativeBlock()) {
/*  69 */         if (this.list.contains(block.getType())) {
/*  70 */           blockAPI.removeBlock();
/*  71 */           block.setType(Material.AIR);
/*     */         } else {
/*  73 */           blockAPI.updateBlock(location, new Location(location.getWorld(), location.getX() + 1.0D, location.getY(), location.getZ()));
/*     */         }
/*     */       }
/*     */       break;
/*     */     case WEST: 
/*  78 */       block = event.getBlock();
/*  79 */       blockAPI = new BlockAPI(block);
/*  80 */       location = block.getLocation();
/*  81 */       if (blockAPI.isCreativeBlock()) {
/*  82 */         if (this.list.contains(block.getType())) {
/*  83 */           blockAPI.removeBlock();
/*  84 */           block.setType(Material.AIR);
/*     */         } else {
/*  86 */           blockAPI.updateBlock(location, new Location(location.getWorld(), location.getX() - 1.0D, location.getY(), location.getZ()));
/*     */         }
/*     */       }
/*     */       break;
/*     */     case UP: 
/*  91 */       block = event.getBlock();
/*  92 */       blockAPI = new BlockAPI(block);
/*  93 */       location = block.getLocation();
/*  94 */       if (blockAPI.isCreativeBlock()) {
/*  95 */         if (this.list.contains(block.getType())) {
/*  96 */           blockAPI.removeBlock();
/*  97 */           block.setType(Material.AIR);
/*     */         } else {
/*  99 */           blockAPI.updateBlock(location, new Location(location.getWorld(), location.getX(), location.getY() + 1.0D, location.getZ()));
/*     */         }
/*     */       }
/*     */       break;
/*     */     case DOWN: 
/* 104 */       block = event.getBlock();
/* 105 */       blockAPI = new BlockAPI(block);
/* 106 */       location = block.getLocation();
/* 107 */       if (blockAPI.isCreativeBlock()) {
/* 108 */         if (this.list.contains(block.getType())) {
/* 109 */           blockAPI.removeBlock();
/* 110 */           block.setType(Material.AIR);
/*     */         } else {
/* 112 */           blockAPI.updateBlock(location, new Location(location.getWorld(), location.getX(), location.getY() - 1.0D, location.getZ()));
/*     */         }
/*     */       }
/*     */       break;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Zack\Downloads\CreativeControl (6).jar!\me\kubqoa\creativecontrol\listeners\piston\PistonRetract_1_7.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */