 package me.kubqoa.creativecontrol.api;
 
 import java.util.HashMap;
 import java.util.Iterator;
 import java.util.List;
 import java.util.UUID;
 import me.kubqoa.creativecontrol.Main;
 import me.kubqoa.creativecontrol.helpers.InventoryHelper;
 import me.kubqoa.creativecontrol.helpers.Methods;
 import me.kubqoa.creativecontrol.integrations.Vault;
 import me.kubqoa.creativecontrol.tasks.InventoriesToDB;
 import org.bukkit.GameMode;
 import org.bukkit.entity.Player;
 import org.bukkit.inventory.ItemStack;
 import org.bukkit.inventory.PlayerInventory;
 
 public class PlayerAPI
 {
   private final Player player;
   
   public PlayerAPI(Player p)
   {
     this.player = p;
   }
   
   public void changeGM(GameMode newG) {
     if (!Methods.perm(this.player, "bypass.gamemode.inventory")) {
       if (Main.serverVersion.equals("1.7")) {
         logInv();
       } else if (this.player.getGameMode() != GameMode.SPECTATOR) {
         logInv();
       }
       clearInv();
       if (Main.serverVersion.equals("1.7")) {
         setInv(newG);
       } else if (newG != GameMode.SPECTATOR) {
         setInv(newG);
       }
     }
     
 
 
     if ((Main.vault != null) && (!Methods.perm(this.player, "bypass.gamemode.permissions"))) {
       perms(newG);
     }
   }
   
   public void logInv() {
     InventoryHelper inventoryHelper = new InventoryHelper(this.player);
     String inv = inventoryHelper.encodeInventory();
     GameMode gameMode = this.player.getGameMode();
     String uuid = this.player.getUniqueId().toString();
     if (gameMode == GameMode.SURVIVAL) {
       Main.sInventory.put(uuid, inv);
       Main.wsInventory.put(uuid, inv);
       if (Main.wsInventory.size() >= Main.loggingInterval) {
         new InventoriesToDB("SURVIVAL").runTaskAsynchronously(Main.thisPlugin);
       }
     } else if (gameMode == GameMode.CREATIVE) {
       Main.cInventory.put(uuid, inv);
       Main.wcInventory.put(uuid, inv);
       if (Main.wcInventory.size() >= Main.loggingInterval) {
         new InventoriesToDB("CREATIVE").runTaskAsynchronously(Main.thisPlugin);
       }
     } else if (gameMode == GameMode.ADVENTURE) {
       Main.aInventory.put(uuid, inv);
       Main.waInventory.put(uuid, inv);
       if (Main.waInventory.size() >= Main.loggingInterval) {
         new InventoriesToDB("ADVENTURE").runTaskAsynchronously(Main.thisPlugin);
       }
     }
   }
   
   public void setInv(GameMode newG) {
     InventoryHelper inventoryHelper = new InventoryHelper(this.player);
     ItemStack[] itemStacks = inventoryHelper.decodeInventory(newG);
     if (itemStacks != null) {
       this.player.getInventory().setContents(itemStacks);
     }
   }
   
   private void clearInv() {
     this.player.getInventory().clear();
   }
   
   public void perms(GameMode newG) {
     if (newG == GameMode.CREATIVE) {
       addperms(Main.addperms);
       removeperms(Main.removeperms);
     }
     if (newG != GameMode.CREATIVE) {
       addperms(Main.removeperms);
       removeperms(Main.addperms);
     }
   }
   
   private void addperms(List<String> perms) { String perm;
     for (Iterator i$ = perms.iterator(); i$.hasNext(); Vault.addPermission(this.player, perm)) perm = (String)i$.next(); }
   
   private void removeperms(List<String> perms) { String perm;
     for (Iterator i$ = perms.iterator(); i$.hasNext(); Vault.removePermission(this.player, perm)) perm = (String)i$.next();
   }
 }


