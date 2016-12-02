 package me.kubqoa.creativecontrol.integrations;
 
 import com.bekvon.bukkit.residence.protection.FlagPermissions;
 import org.bukkit.Location;
 import org.bukkit.World;
 import org.bukkit.entity.Player;
 
 
 
 class Residence
 {
   public static boolean residence(Player player, Location loc)
   {
     FlagPermissions permissions = com.bekvon.bukkit.residence.Residence.getPermsByLocForPlayer(loc, player);
     return permissions.playerHas(player.getName(), loc.getWorld().getName(), "build", true);
   }
 }


