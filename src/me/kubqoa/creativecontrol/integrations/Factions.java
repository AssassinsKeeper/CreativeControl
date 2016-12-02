 package me.kubqoa.creativecontrol.integrations;
 
 import com.massivecraft.factions.engine.EngineMain;
 import com.massivecraft.massivecore.ps.PS;
 import org.bukkit.Location;
 import org.bukkit.entity.Player;
 
 
 class Factions
 {
   public static boolean factions(Player player, Location loc)
   {
     return EngineMain.canPlayerBuildAt(player, PS.valueOf(loc), false);
   }
 }


