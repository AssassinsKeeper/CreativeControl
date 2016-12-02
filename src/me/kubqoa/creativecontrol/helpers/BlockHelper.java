 package me.kubqoa.creativecontrol.helpers;
 
 import java.util.List;
 import me.kubqoa.creativecontrol.Main;
 import org.bukkit.Location;
 import org.bukkit.World;
 
 public class BlockHelper
 {
   public static boolean isCreativeBlock(Location location)
   {
     if ((Main.RblocksLocation.contains(location)) && 
       (Main.RblocksMaterial.get(location) == location.getBlock().getType())) {
       return false;
     }
     
     return DatabaseHelper.selectSQL("SELECT * FROM `" + Main.dbprefix + "blocks` WHERE x=" + location.getX() + " AND y=" + location.getY() + " AND z=" + location.getZ() + " AND world='" + location.getWorld().getName() + "'") > 0;
   }
   
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
   public static void removeBlock(Location location)
   {
     DatabaseHelper.updateSQL("DELETE FROM `" + Main.dbprefix + "blocks` WHERE x=" + location.getX() + " AND y=" + location.getY() + " AND z=" + location.getZ() + " AND world='" + location.getWorld().getName() + "'");
   }
   
   public static void updateBlock(Location oldLocation, Location newLocation) {
     if (!Main.RblocksLocation.contains(oldLocation)) {
       DatabaseHelper.updateSQL("UPDATE `" + Main.dbprefix + "blocks` SET x = " + newLocation.getX() + ", y = " + newLocation.getY() + ", z = " + newLocation.getZ() + ", world = '" + newLocation.getWorld().getName() + "' WHERE x=" + oldLocation.getX() + " AND y=" + oldLocation.getY() + " AND z=" + oldLocation.getZ() + " AND world='" + oldLocation.getWorld().getName() + "'");
     }
   }
 }


