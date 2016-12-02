package me.kubqoa.creativecontrol.helpers;

import java.io.ByteArrayInputStream;
/*    */ import java.io.ByteArrayOutputStream;
/*    */ import java.util.HashMap;
/*    */ import java.util.UUID;
/*    */ import me.kubqoa.creativecontrol.Base64Coder;
/*    */ import me.kubqoa.creativecontrol.Main;
/*    */ import org.bukkit.GameMode;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import org.bukkit.inventory.PlayerInventory;
/*    */ import org.bukkit.util.io.BukkitObjectInputStream;
/*    */ import org.bukkit.util.io.BukkitObjectOutputStream;

 public class InventoryHelper
 {
   private final Player player;

   public InventoryHelper(Player p)
   {
     this.player = p;
   }
   public String encodeInventory() {
     ItemStack[] itemStacks = this.player.getInventory().getContents();
     try {
       ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
       BukkitObjectOutputStream dataOutput = new BukkitObjectOutputStream(outputStream);

       dataOutput.writeInt(itemStacks.length);


       for (ItemStack itemStack : itemStacks) {
         dataOutput.writeObject(itemStack);
       }

       dataOutput.close();
       return Base64Coder.encodeLines(outputStream.toByteArray());
     } catch (Exception e) {
       throw new IllegalStateException("Unable to save item stacks.", e);
     }
   }

   public ItemStack[] decodeInventory(GameMode gameMode) {
     String uuid = this.player.getUniqueId().toString();
     String data;
       if ((gameMode == GameMode.ADVENTURE) && (Main.aInventory.containsKey(uuid))) { data = (String)Main.aInventory.get(uuid); }
       else { String data1;
       if ((gameMode == GameMode.CREATIVE) && (Main.cInventory.containsKey(uuid))) { data = (String)Main.cInventory.get(uuid); }
       else { String data2;
         if ((gameMode == GameMode.SURVIVAL) && (Main.sInventory.containsKey(uuid))) data = (String)Main.sInventory.get(uuid); else
           return null; } }
     try { String data3;
       ByteArrayInputStream inputStream = new ByteArrayInputStream(Base64Coder.decodeLines(data));
       BukkitObjectInputStream dataInput = new BukkitObjectInputStream(inputStream);
       int size = dataInput.readInt();
       ItemStack[] itemStacks = new ItemStack[size];

       for (int i = 0; i < size; i++) {
         itemStacks[i] = ((ItemStack)dataInput.readObject());
       }
       dataInput.close();
       return itemStacks;
     } catch (Exception e) {
       e.printStackTrace();
     }
     return null;
 }
 }