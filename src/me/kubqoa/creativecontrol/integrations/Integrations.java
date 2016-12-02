 package me.kubqoa.creativecontrol.integrations;
 
 import me.kubqoa.creativecontrol.Main;
 import org.bukkit.block.Block;
 import org.bukkit.entity.Player;
 
 
 
 
 public class Integrations
 {
   public static boolean canBreak(Block block, Player player)
   {
     boolean can = true;
     if (Main.factions != null) {
       can = Factions.factions(player, block.getLocation());
     }
     if ((can) && (Main.griefPrevention != null)) {
       can = GriefPrevention.griefPrevention(player, block);
     }
     if ((can) && (Main.residence != null)) {
       can = Residence.residence(player, block.getLocation());
     }
     if ((can) && (Main.towny != null)) {
       can = Towny.towny(player, block);
     }
     if ((can) && (Main.worldGuard != null)) {
       can = WorldGuard.worldGuard(player, block.getLocation(), Main.pm);
     }
     return can;
   }
 }


