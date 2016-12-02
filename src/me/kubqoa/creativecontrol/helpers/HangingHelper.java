 package me.kubqoa.creativecontrol.helpers;
 
 import java.util.List;
 import me.kubqoa.creativecontrol.Main;
 import org.bukkit.Location;
 import org.bukkit.World;
 
 public class HangingHelper
 {
   public static boolean isCreativeHanging(Location location)
   {
     return (!Main.RhangingsLocation.contains(location)) && (DatabaseHelper.selectSQL("SELECT * FROM `" + Main.dbprefix + "hangings` WHERE x=" + location.getX() + " AND y=" + location.getY() + " AND z=" + location.getZ() + " AND world='" + location.getWorld().getName() + "'") > 0);
   }
   
   public static void removeHanging(Location location) {
     DatabaseHelper.updateSQL("DELETE FROM `" + Main.dbprefix + "hangings` WHERE x=" + location.getX() + " AND y=" + location.getY() + " AND z=" + location.getZ() + " AND world='" + location.getWorld().getName() + "'");
   }
 }


