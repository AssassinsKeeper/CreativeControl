 package me.kubqoa.creativecontrol.helpers;
 
 import java.util.HashMap;
 import java.util.List;
 import me.kubqoa.creativecontrol.Main;
 import org.bukkit.Location;
 import org.bukkit.World;
 
 public class VehicleHelper
 {
   public static boolean isCreativeVehicle(Location location)
   {
     if (Main.RvehiclesLocation.contains(location)) return false;
     if (Main.UvehiclesLocation2.containsKey(location)) location = (Location)Main.UvehiclesLocation2.get(location);
     return DatabaseHelper.selectSQL("SELECT * FROM `" + Main.dbprefix + "vehicles` WHERE x=" + location.getX() + " AND y=" + location.getY() + " AND z=" + location.getZ() + " AND world='" + location.getWorld().getName() + "'") > 0;
   }
   
   public static void removeVehicle(Location location) {
     DatabaseHelper.updateSQL("DELETE FROM `" + Main.dbprefix + "vehicle` WHERE x=" + location.getX() + " AND y=" + location.getY() + " AND z=" + location.getZ() + " AND world='" + location.getWorld().getName() + "'");
   }
   
   public static void updateVehicle(Location oldLocation, Location newLocation) {
     DatabaseHelper.updateSQL("UPDATE `" + Main.dbprefix + "vehicles` SET x = " + newLocation.getX() + ", y = " + newLocation.getY() + ", z = " + newLocation.getZ() + ", world = '" + newLocation.getWorld().getName() + "' WHERE x=" + oldLocation.getX() + " AND y=" + oldLocation.getY() + " AND z=" + oldLocation.getZ() + " AND world='" + oldLocation.getWorld().getName() + "'");
   }
 }


