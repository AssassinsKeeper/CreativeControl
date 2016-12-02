/*     */ package me.kubqoa.creativecontrol.utils.converter;
/*     */ 
/*     */ import java.sql.Connection;
/*     */ import java.sql.DriverManager;
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.Statement;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import me.kubqoa.creativecontrol.Main;
/*     */ import me.kubqoa.creativecontrol.helpers.DatabaseHelper;
/*     */ import me.kubqoa.creativecontrol.helpers.Methods;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.Server;
/*     */ import org.bukkit.World;
/*     */ import org.bukkit.command.CommandSender;
/*     */ 
/*     */ 
/*     */ class InsideConvert
/*     */ {
/*     */   private final CommandSender sender;
/*     */   
/*     */   public InsideConvert(CommandSender sender)
/*     */   {
/*  27 */     this.sender = sender;
/*     */   }
/*     */   
/*  30 */   private final List<Location> tempBlockLocation = new ArrayList();
/*  31 */   private final HashMap<Location, Material> tempBlockMaterial = new HashMap();
/*     */   
/*  33 */   private final List<Location> tempBlockLocation2 = new ArrayList();
/*     */   
/*     */   public void mysql() {
/*  36 */     if (Main.dbtype) {
/*     */       try {
/*  38 */         Methods.sendMsg(this.sender, "&cStarting conversion!");
/*  39 */         Class.forName("org.sqlite.JDBC");
/*  40 */         Connection c = DriverManager.getConnection("jdbc:sqlite:" + Main.folder + "/creativecontrol.db");
/*  41 */         Statement stmt = c.createStatement();
/*  42 */         Methods.sendMsg(this.sender, "&cReading data!");
/*  43 */         ResultSet rs = stmt.executeQuery("SELECT * FROM `" + Main.dbprefix + "blocks`");
/*  44 */         while (rs.next()) {
/*  45 */           double x = rs.getDouble("x");
/*  46 */           double y = rs.getDouble("y");
/*  47 */           double z = rs.getDouble("z");
/*  48 */           World world = Bukkit.getServer().getWorld(rs.getString("world"));
/*  49 */           Material material = Material.valueOf(rs.getString("material"));
/*  50 */           Location location = new Location(world, x, y, z);
/*  51 */           this.tempBlockLocation.add(location);
/*  52 */           this.tempBlockMaterial.put(location, material);
/*     */         }
/*  54 */         rs.close();
/*  55 */         stmt.close();
/*  56 */         c.close();
/*  57 */         stmt = Main.c.createStatement();
/*  58 */         rs = stmt.executeQuery("SELECT * FROM `" + Main.dbprefix + "blocks`");
/*  59 */         while (rs.next()) {
/*  60 */           double x = rs.getDouble("x");
/*  61 */           double y = rs.getDouble("y");
/*  62 */           double z = rs.getDouble("z");
/*  63 */           World world = Bukkit.getServer().getWorld(rs.getString("world"));
/*  64 */           Location location = new Location(world, x, y, z);
/*  65 */           this.tempBlockLocation2.add(location);
/*     */         }
/*  67 */         rs.close();
/*  68 */         stmt.close();
/*  69 */         Methods.sendMsg(this.sender, "&cWriting data!");
/*  70 */         String sql = "INSERT INTO `" + Main.dbprefix + "blocks` (x,y,z,world,material) VALUES ";
/*  71 */         String sql2 = "DELETE FROM `" + Main.dbprefix + "blocks` WHERE (x,y,z,world) IN (";
/*  72 */         int i2 = 0;
/*  73 */         for (Location location : this.tempBlockLocation) {
/*  74 */           if (this.tempBlockLocation2.contains(location)) {
/*  75 */             sql2 = sql2 + "(" + location.getX() + "," + location.getY() + "," + location.getZ() + ",'" + location.getWorld().getName() + "'),";
/*  76 */             i2++;
/*     */           }
/*  78 */           sql = sql + "(" + location.getX() + "," + location.getY() + "," + location.getZ() + ",'" + location.getWorld().getName() + "','" + this.tempBlockMaterial.get(location) + "'), ";
/*     */         }
/*  80 */         if (i2 > 0) {
/*  81 */           sql2 = sql2.substring(0, sql2.length() - 1);
/*  82 */           sql2 = sql2 + ")";
/*  83 */           DatabaseHelper.updateSQL(sql2);
/*     */         }
/*  85 */         sql = sql.substring(0, sql.length() - 2);
/*  86 */         sql = sql + ";";
/*  87 */         DatabaseHelper.updateSQL(sql);
/*  88 */         this.tempBlockMaterial.clear();
/*  89 */         this.tempBlockLocation.clear();
/*  90 */         this.tempBlockLocation2.clear();
/*  91 */         Methods.sendMsg(this.sender, "&cFinished!");
/*     */       } catch (Exception e) {
/*  93 */         Methods.sendMsg(this.sender, "&cError while converting from MySQL to SQLite");
/*  94 */         e.printStackTrace();
/*     */       }
/*     */     } else {
/*  97 */       Methods.sendMsg(this.sender, "&cYou can convert to MySQL only when you have MySQL configured as your active connection!");
/*     */     }
/*     */   }
/*     */   
/*     */   public void sqlite() {
/* 102 */     if (Main.dbtype) {
/*     */       try {
/* 104 */         Methods.sendMsg(this.sender, "&cStarting conversion!");
/* 105 */         Statement stmt = Main.c.createStatement();
/* 106 */         Methods.sendMsg(this.sender, "&cReading data!");
/* 107 */         ResultSet rs = stmt.executeQuery("SELECT * FROM `" + Main.dbprefix + "blocks`");
/* 108 */         while (rs.next()) {
/* 109 */           double x = rs.getDouble("x");
/* 110 */           double y = rs.getDouble("y");
/* 111 */           double z = rs.getDouble("z");
/* 112 */           World world = Bukkit.getServer().getWorld(rs.getString("world"));
/* 113 */           Material material = Material.valueOf(rs.getString("material"));
/* 114 */           Location location = new Location(world, x, y, z);
/* 115 */           this.tempBlockLocation.add(location);
/* 116 */           this.tempBlockMaterial.put(location, material);
/*     */         }
/* 118 */         rs.close();
/* 119 */         stmt.close();
/* 120 */         Class.forName("org.sqlite.JDBC");
/* 121 */         Connection c = DriverManager.getConnection("jdbc:sqlite:" + Main.folder + "/creativecontrol.db");
/* 122 */         stmt = c.createStatement();
/* 123 */         rs = stmt.executeQuery("SELECT * FROM `" + Main.dbprefix + "blocks`");
/* 124 */         while (rs.next()) {
/* 125 */           double x = rs.getDouble("x");
/* 126 */           double y = rs.getDouble("y");
/* 127 */           double z = rs.getDouble("z");
/* 128 */           World world = Bukkit.getServer().getWorld(rs.getString("world"));
/* 129 */           Location location = new Location(world, x, y, z);
/* 130 */           this.tempBlockLocation2.add(location);
/*     */         }
/* 132 */         rs.close();
/* 133 */         Methods.sendMsg(this.sender, "&cWriting data!");
/* 134 */         String sql = "INSERT INTO `" + Main.dbprefix + "blocks` (x,y,z,world,material) ";
/* 135 */         String sql2 = "DELETE FROM `" + Main.dbprefix + "blocks` WHERE ";
/* 136 */         int i = 0;
/* 137 */         int i2 = 0;
/* 138 */         for (Location location : this.tempBlockLocation) {
/* 139 */           if (this.tempBlockLocation2.contains(location)) {
/* 140 */             sql2 = sql2 + "(x=" + location.getX() + " AND y=" + location.getY() + " AND z=" + location.getZ() + " AND world='" + location.getWorld().getName() + "') OR ";
/* 141 */             i2++;
/*     */           }
/* 143 */           if (i == 0) {
/* 144 */             sql = sql + "SELECT '" + location.getX() + "' , '" + location.getY() + "' , '" + location.getZ() + "' , '" + location.getWorld().getName() + "' , '" + this.tempBlockMaterial.get(location) + "' ";
/*     */           } else {
/* 146 */             sql = sql + "UNION ALL SELECT '" + location.getX() + "' , '" + location.getY() + "' , '" + location.getZ() + "' , '" + location.getWorld().getName() + "' , '" + ((Material)this.tempBlockMaterial.get(location)).name() + "' ";
/*     */           }
/* 148 */           i++;
/*     */         }
/* 150 */         if (i2 > 0) {
/* 151 */           sql2 = sql2.substring(0, sql2.length() - 4);
/* 152 */           stmt.executeUpdate(sql2);
/*     */         }
/* 154 */         sql = sql.substring(0, sql.length() - 1);
/* 155 */         stmt.executeUpdate(sql);
/* 156 */         c.close();
/* 157 */         this.tempBlockMaterial.clear();
/* 158 */         this.tempBlockLocation.clear();
/* 159 */         Methods.sendMsg(this.sender, "&cFinished!");
/*     */       } catch (Exception e) {
/* 161 */         Methods.sendMsg(this.sender, "&cError while converting from SQLite to MySQL!");
/* 162 */         e.printStackTrace();
/*     */       }
/*     */     } else {
/* 165 */       Methods.sendMsg(this.sender, "&cYou can convert to SQLite only when you have MySQL configured as your active connection!");
/*     */     }
/*     */   }
/*     */ }