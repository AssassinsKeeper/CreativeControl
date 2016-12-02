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
/*     */ import org.bukkit.event.block.BlockPistonExtendEvent;
/*     */ 
/*     */ public class PistonExtend implements Listener
/*     */ {
/*     */   private List<Material> list;
/*     */   
/*     */   public PistonExtend()
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
/*     */   public void extend(BlockPistonExtendEvent event) {
/*  33 */     if (Methods.exclude(event.getBlock().getLocation())) return;
/*  34 */     switch (event.getDirection()) {
/*     */     case SOUTH: 
/*  36 */       for (Block block : event.getBlocks()) {
/*  37 */         BlockAPI blockAPI = new BlockAPI(block);
/*  38 */         Location location = block.getLocation();
/*  39 */         if (blockAPI.isCreativeBlock()) {
/*  40 */           if (this.list.contains(block.getType())) {
/*  41 */             blockAPI.removeBlock();
/*  42 */             block.setType(Material.AIR);
/*     */           } else {
/*  44 */             blockAPI.updateBlock(location, new Location(location.getWorld(), location.getX(), location.getY(), location.getZ() + 1.0D));
/*     */           }
/*     */         }
/*     */       }
/*  48 */       break;
/*     */     case NORTH: 
/*  50 */       for (Block block : event.getBlocks()) {
/*  51 */         BlockAPI blockAPI = new BlockAPI(block);
/*  52 */         Location location = block.getLocation();
/*  53 */         if (blockAPI.isCreativeBlock()) {
/*  54 */           if (this.list.contains(block.getType())) {
/*  55 */             blockAPI.removeBlock();
/*  56 */             block.setType(Material.AIR);
/*     */           } else {
/*  58 */             blockAPI.updateBlock(location, new Location(location.getWorld(), location.getX(), location.getY(), location.getZ() - 1.0D));
/*     */           }
/*     */         }
/*     */       }
/*  62 */       break;
/*     */     case EAST: 
/*  64 */       for (Block block : event.getBlocks()) {
/*  65 */         BlockAPI blockAPI = new BlockAPI(block);
/*  66 */         Location location = block.getLocation();
/*  67 */         if (blockAPI.isCreativeBlock()) {
/*  68 */           if (this.list.contains(block.getType())) {
/*  69 */             blockAPI.removeBlock();
/*  70 */             block.setType(Material.AIR);
/*     */           } else {
/*  72 */             blockAPI.updateBlock(location, new Location(location.getWorld(), location.getX() + 1.0D, location.getY(), location.getZ()));
/*     */           }
/*     */         }
/*     */       }
/*  76 */       break;
/*     */     case WEST: 
/*  78 */       for (Block block : event.getBlocks()) {
/*  79 */         BlockAPI blockAPI = new BlockAPI(block);
/*  80 */         Location location = block.getLocation();
/*  81 */         if (blockAPI.isCreativeBlock()) {
/*  82 */           if (this.list.contains(block.getType())) {
/*  83 */             blockAPI.removeBlock();
/*  84 */             block.setType(Material.AIR);
/*     */           } else {
/*  86 */             blockAPI.updateBlock(location, new Location(location.getWorld(), location.getX() - 1.0D, location.getY(), location.getZ()));
/*     */           }
/*     */         }
/*     */       }
/*  90 */       break;
/*     */     case UP: 
/*  92 */       for (Block block : event.getBlocks()) {
/*  93 */         BlockAPI blockAPI = new BlockAPI(block);
/*  94 */         Location location = block.getLocation();
/*  95 */         if (blockAPI.isCreativeBlock()) {
/*  96 */           if (this.list.contains(block.getType())) {
/*  97 */             blockAPI.removeBlock();
/*  98 */             block.setType(Material.AIR);
/*     */           } else {
/* 100 */             blockAPI.updateBlock(location, new Location(location.getWorld(), location.getX(), location.getY() + 1.0D, location.getZ()));
/*     */           }
/*     */         }
/*     */       }
/* 104 */       break;
/*     */     case DOWN: 
/* 106 */       for (Block block : event.getBlocks()) {
/* 107 */         BlockAPI blockAPI = new BlockAPI(block);
/* 108 */         Location location = block.getLocation();
/* 109 */         if (blockAPI.isCreativeBlock()) {
/* 110 */           if (this.list.contains(block.getType())) {
/* 111 */             blockAPI.removeBlock();
/* 112 */             block.setType(Material.AIR);
/*     */           } else {
/* 114 */             blockAPI.updateBlock(location, new Location(location.getWorld(), location.getX(), location.getY() - 1.0D, location.getZ()));
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Zack\Downloads\CreativeControl (6).jar!\me\kubqoa\creativecontrol\listeners\piston\PistonExtend.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */