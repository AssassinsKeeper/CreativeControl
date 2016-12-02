 package me.kubqoa.creativecontrol.integrations;
 
 import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
 import org.bukkit.Location;
 import org.bukkit.entity.Player;
 import org.bukkit.plugin.Plugin;
 import org.bukkit.plugin.PluginManager;
 
 
 
 
 class WorldGuard
 {
   public static boolean worldGuard(Player player, Location loc, PluginManager pm)
   {
     return getWorldGuard(pm).canBuild(player, loc);
   }
   
   private static WorldGuardPlugin getWorldGuard(PluginManager pm) {
     Plugin plugin = pm.getPlugin("WorldGuard");
     
 
     if ((plugin == null) || (!(plugin instanceof WorldGuardPlugin))) {
       return null;
     }
     
     return (WorldGuardPlugin)plugin;
   }
 }


