/*     */ package me.kubqoa.creativecontrol.api;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import me.kubqoa.creativecontrol.Main;
/*     */ import me.kubqoa.creativecontrol.helpers.BlockHelper;
/*     */ import me.kubqoa.creativecontrol.integrations.Integrations;
/*     */ import me.kubqoa.creativecontrol.tasks.BlocksFromDB;
/*     */ import me.kubqoa.creativecontrol.tasks.BlocksToDB;
/*     */ import me.kubqoa.creativecontrol.utils.lists.list_1_7;
/*     */ import me.kubqoa.creativecontrol.utils.lists.list_1_8;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.block.BlockFace;
/*     */ import org.bukkit.block.BlockState;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.material.Attachable;
/*     */ import org.bukkit.material.MaterialData;
/*     */ import org.bukkit.material.Rails;
/*     */ 
/*     */ public class BlockAPI
/*     */ {
/*     */   private final Block block;
/*     */   private List<Material> above;
/*     */   
/*     */   public BlockAPI(Block b)
/*     */   {
/*  29 */     if (Main.serverVersion.equals("1.9")) {
/*  30 */       this.above = me.kubqoa.creativecontrol.utils.lists.list_1_9.above;
/*  31 */     } else if (Main.serverVersion.equals("1.8")) {
/*  32 */       this.above = list_1_8.above;
/*  33 */     } else if (Main.serverVersion.equals("1.7")) {
/*  34 */       this.above = list_1_7.above;
/*     */     }
/*  36 */     this.block = b;
/*     */   }
/*     */   
/*     */   public void blocksAbove() {
/*  40 */     Block relative = this.block.getRelative(BlockFace.UP);
/*  41 */     BlockAPI relativeAPI = new BlockAPI(relative);
/*  42 */     if ((this.above.contains(relative.getType())) && (relativeAPI.isCreativeBlock())) {
/*  43 */       relativeAPI.removeBlock();
/*  44 */       relative.setType(Material.AIR);
/*     */     }
/*  46 */     relative = this.block.getRelative(BlockFace.WEST);
/*  47 */     MaterialData data = relative.getState().getData();
/*  48 */     relativeAPI = new BlockAPI(relative);
/*  49 */     if (((data instanceof Attachable)) && (relativeAPI.isCreativeBlock())) {
/*  50 */       BlockFace attached = ((Attachable)data).getAttachedFace();
/*  51 */       Location original = relative.getRelative(attached).getLocation();
/*  52 */       if (original.equals(this.block.getLocation())) {
/*  53 */         relativeAPI.removeBlock();
/*  54 */         relative.setType(Material.AIR);
/*     */       }
/*  56 */     } else if ((data instanceof Rails)) {
/*  57 */       Rails rails = (Rails)data;
/*  58 */       if (rails.isOnSlope()) {
/*  59 */         BlockFace blockFace = rails.getDirection();
/*  60 */         Location original = relative.getRelative(blockFace).getLocation();
/*  61 */         if (original.equals(this.block.getLocation())) {
/*  62 */           relativeAPI.removeBlock();
/*  63 */           relative.setType(Material.AIR);
/*     */         }
/*     */       }
/*     */     }
/*  67 */     relative = this.block.getRelative(BlockFace.EAST);
/*  68 */     data = relative.getState().getData();
/*  69 */     relativeAPI = new BlockAPI(relative);
/*  70 */     if (((data instanceof Attachable)) && (relativeAPI.isCreativeBlock())) {
/*  71 */       BlockFace attached = ((Attachable)data).getAttachedFace();
/*  72 */       Location original = relative.getRelative(attached).getLocation();
/*  73 */       if (original.equals(this.block.getLocation())) {
/*  74 */         relativeAPI.removeBlock();
/*  75 */         relative.setType(Material.AIR);
/*     */       }
/*  77 */     } else if ((data instanceof Rails)) {
/*  78 */       Rails rails = (Rails)data;
/*  79 */       if (rails.isOnSlope()) {
/*  80 */         BlockFace blockFace = rails.getDirection();
/*  81 */         Location original = relative.getRelative(blockFace).getLocation();
/*  82 */         if (original.equals(this.block.getLocation())) {
/*  83 */           relativeAPI.removeBlock();
/*  84 */           relative.setType(Material.AIR);
/*     */         }
/*     */       }
/*     */     }
/*  88 */     relative = this.block.getRelative(BlockFace.NORTH);
/*  89 */     data = relative.getState().getData();
/*  90 */     relativeAPI = new BlockAPI(relative);
/*  91 */     if (((data instanceof Attachable)) && (relativeAPI.isCreativeBlock())) {
/*  92 */       BlockFace attached = ((Attachable)data).getAttachedFace();
/*  93 */       Location original = relative.getRelative(attached).getLocation();
/*  94 */       if (original.equals(this.block.getLocation())) {
/*  95 */         relativeAPI.removeBlock();
/*  96 */         relative.setType(Material.AIR);
/*     */       }
/*  98 */     } else if ((data instanceof Rails)) {
/*  99 */       Rails rails = (Rails)data;
/* 100 */       if (rails.isOnSlope()) {
/* 101 */         BlockFace blockFace = rails.getDirection();
/* 102 */         Location original = relative.getRelative(blockFace).getLocation();
/* 103 */         if (original.equals(this.block.getLocation())) {
/* 104 */           relativeAPI.removeBlock();
/* 105 */           relative.setType(Material.AIR);
/*     */         }
/*     */       }
/*     */     }
/* 109 */     relative = this.block.getRelative(BlockFace.SOUTH);
/* 110 */     data = relative.getState().getData();
/* 111 */     relativeAPI = new BlockAPI(relative);
/* 112 */     if (((data instanceof Attachable)) && (relativeAPI.isCreativeBlock())) {
/* 113 */       BlockFace attached = ((Attachable)data).getAttachedFace();
/* 114 */       Location original = relative.getRelative(attached).getLocation();
/* 115 */       if (original.equals(this.block.getLocation())) {
/* 116 */         relativeAPI.removeBlock();
/* 117 */         relative.setType(Material.AIR);
/*     */       }
/* 119 */     } else if ((data instanceof Rails)) {
/* 120 */       Rails rails = (Rails)data;
/* 121 */       if (rails.isOnSlope()) {
/* 122 */         BlockFace blockFace = rails.getDirection();
/* 123 */         Location original = relative.getRelative(blockFace).getLocation();
/* 124 */         if (original.equals(this.block.getLocation())) {
/* 125 */           relativeAPI.removeBlock();
/* 126 */           relative.setType(Material.AIR);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean isCreativeBlock() {
/* 133 */     Location location = this.block.getLocation();
/* 134 */     if (Main.blocksLocation.contains(location)) {
/* 135 */       Material material = (Material)Main.blocksMaterial.get(location);
/* 136 */       if (material == this.block.getType())
/* 137 */         return true;
/* 138 */       if ((material == Material.GRASS) && (this.block.getType() == Material.DIRT))
/* 139 */         return true;
/* 140 */       if ((material == Material.DIRT) && (this.block.getType() == Material.GRASS)) {
/* 141 */         return true;
/*     */       }
/* 143 */     } else if (Main.WblocksLocation.contains(location)) {
/* 144 */       Material material = (Material)Main.WblocksMaterial.get(location);
/* 145 */       if (material == this.block.getType())
/* 146 */         return true;
/* 147 */       if ((material == Material.GRASS) && (this.block.getType() == Material.DIRT))
/* 148 */         return true;
/* 149 */       if ((material == Material.DIRT) && (this.block.getType() == Material.GRASS)) {
/* 150 */         return true;
/*     */       }
/* 152 */     } else if (BlockHelper.isCreativeBlock(location)) {
/* 153 */       return true;
/*     */     }
/*     */     
/* 156 */     return false;
/*     */   }
/*     */   
/*     */   public boolean canBreak(Player player) {
/* 160 */     return Integrations.canBreak(this.block, player);
/*     */   }
/*     */   
/*     */   public void removeBlock() {
/* 164 */     Location location = this.block.getLocation();
/* 165 */     if (Main.blocksLocation.contains(location)) {
/* 166 */       Main.blocksLocation.remove(location);
/* 167 */       Main.blocksMaterial.remove(location);
/*     */     }
/* 169 */     if (Main.WblocksLocation.contains(location)) {
/* 170 */       Main.WblocksLocation.remove(location);
/* 171 */       Main.WblocksMaterial.remove(location);
/*     */     }
/* 173 */     if (BlockHelper.isCreativeBlock(location)) {
/* 174 */       Main.RblocksLocation.add(location);
/* 175 */       Main.RblocksMaterial.put(location, location.getBlock().getType());
/* 176 */       if (Main.RblocksLocation.size() >= 50) {
/* 177 */         new BlocksFromDB().runTaskAsynchronously(Main.thisPlugin);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public void addBlock(Material material) {
/* 183 */     Location location = this.block.getLocation();
/* 184 */     if (Main.blocksLocation.size() < Main.blockCache) {
/* 185 */       Main.blocksLocation.add(location);
/* 186 */       Main.blocksMaterial.put(location, material);
/*     */     }
/* 188 */     Main.WblocksLocation.add(location);
/* 189 */     Main.WblocksMaterial.put(location, material);
/* 190 */     if (Main.WblocksLocation.size() >= Main.loggingInterval) {
/* 191 */       new BlocksToDB().runTaskAsynchronously(Main.thisPlugin);
/*     */     }
/*     */   }
/*     */   
/*     */   public void updateBlock(Location oldLocation, Location newLocation) {
/* 196 */     if (Main.blocksLocation.contains(oldLocation)) {
/* 197 */       Main.blocksLocation.add(newLocation);
/* 198 */       Main.blocksMaterial.put(newLocation, Main.blocksMaterial.get(oldLocation));
/* 199 */       Main.blocksLocation.remove(oldLocation);
/* 200 */       Main.blocksMaterial.remove(oldLocation);
/*     */     }
/* 202 */     if (Main.WblocksLocation.contains(oldLocation)) {
/* 203 */       Main.WblocksLocation.add(newLocation);
/* 204 */       Main.WblocksMaterial.put(newLocation, Main.WblocksMaterial.get(oldLocation));
/* 205 */       Main.WblocksLocation.remove(oldLocation);
/* 206 */       Main.WblocksMaterial.remove(oldLocation);
/* 207 */     } else if (BlockHelper.isCreativeBlock(oldLocation)) {
/* 208 */       BlockHelper.updateBlock(oldLocation, newLocation);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Zack\Downloads\CreativeControl (6).jar!\me\kubqoa\creativecontrol\api\BlockAPI.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */