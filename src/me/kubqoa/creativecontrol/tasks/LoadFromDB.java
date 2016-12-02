/*    */ package me.kubqoa.creativecontrol.tasks;
/*    */ 
/*    */ import java.sql.ResultSet;
/*    */ import java.sql.Statement;
/*    */ import java.util.HashMap;
/*    */ import java.util.List;
/*    */ import me.kubqoa.creativecontrol.Main;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.Server;
/*    */ import org.bukkit.World;
/*    */ 
/*    */ public class LoadFromDB
/*    */ {
/*    */   public static void load()
/*    */   {
/*    */     try
/*    */     {
/* 19 */       Statement stmt = Main.c.createStatement();
/* 20 */       ResultSet rs = stmt.executeQuery("SELECT * FROM `" + Main.dbprefix + "blocks`");
/* 21 */       int i = 0;
/* 22 */       while ((rs.next()) && (i < Main.blockCache)) {
/* 23 */         double x = rs.getDouble("x");
/* 24 */         double y = rs.getDouble("y");
/* 25 */         double z = rs.getDouble("z");
/* 26 */         World world = Bukkit.getServer().getWorld(rs.getString("world"));
/* 27 */         org.bukkit.Material material = org.bukkit.Material.valueOf(rs.getString("material"));
/* 28 */         Location location = new Location(world, x, y, z);
/* 29 */         Main.blocksLocation.add(location);
/* 30 */         Main.blocksMaterial.put(location, material);
/* 31 */         i++;
/*    */       }
/* 33 */       rs.close();
/* 34 */       rs = stmt.executeQuery("SELECT * FROM `" + Main.dbprefix + "vehicles`");
/* 35 */       i = 0;
/* 36 */       while ((rs.next()) && (i < Main.vehicleCache)) {
/* 37 */         double x = rs.getDouble("x");
/* 38 */         double y = rs.getDouble("y");
/* 39 */         double z = rs.getDouble("z");
/* 40 */         World world = Bukkit.getServer().getWorld(rs.getString("world"));
/* 41 */         Location location = new Location(world, x, y, z);
/* 42 */         Main.vehiclesLocation.add(location);
/* 43 */         i++;
/*    */       }
/* 45 */       rs.close();
/* 46 */       rs = stmt.executeQuery("SELECT * FROM `" + Main.dbprefix + "hangings`");
/* 47 */       i = 0;
/* 48 */       while ((rs.next()) && (i < Main.hangingCache)) {
/* 49 */         double x = rs.getDouble("x");
/* 50 */         double y = rs.getDouble("y");
/* 51 */         double z = rs.getDouble("z");
/* 52 */         World world = Bukkit.getServer().getWorld(rs.getString("world"));
/* 53 */         Location location = new Location(world, x, y, z);
/* 54 */         Main.hangingsLocation.add(location);
/* 55 */         i++;
/*    */       }
/* 57 */       rs.close();
/* 58 */       rs = stmt.executeQuery("SELECT * FROM `" + Main.dbprefix + "inventories`");
/* 59 */       while (rs.next()) {
/* 60 */         String uuid = rs.getString("uuid");
/* 61 */         String inventory = rs.getString("inventory");
/* 62 */         String s = rs.getString("gamemode");
/* 63 */         if (s.equals("SURVIVAL")) {
/* 64 */           Main.sInventory.put(uuid, inventory);
/* 65 */         } else if (s.equals("CREATIVE")) {
/* 66 */           Main.cInventory.put(uuid, inventory);
/* 67 */         } else if (s.equals("ADVENTURE")) {
/* 68 */           Main.aInventory.put(uuid, inventory);
/*    */         }
/*    */       }
/* 71 */       rs.close();
/* 72 */       stmt.close();
/*    */     } catch (Exception e) {
/* 74 */       throw new IllegalStateException("Error while loading from DB!", e);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Zack\Downloads\CreativeControl (6).jar!\me\kubqoa\creativecontrol\tasks\LoadFromDB.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */