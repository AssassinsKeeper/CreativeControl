/*    */ package me.kubqoa.creativecontrol.utils.lists;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import org.bukkit.Material;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class list_universal
/*    */ {
/* 13 */   public static final List<Material> willreplace = new ArrayList();
/*    */
/* 15 */   static { willreplace.add(Material.VINE);
/* 16 */     willreplace.add(Material.SNOW);
/* 17 */     willreplace.add(Material.DEAD_BUSH);
/* 18 */     willreplace.add(Material.LONG_GRASS);
/* 19 */     willreplace.add(Material.DOUBLE_PLANT);
/*    */     }
/* 21 */     public static final List<Material> minecarts = new ArrayList();
/*    */     static {
/* 23 */     minecarts.add(Material.MINECART);
/* 24 */     minecarts.add(Material.COMMAND_MINECART);
/* 25 */     minecarts.add(Material.EXPLOSIVE_MINECART);
/* 26 */     minecarts.add(Material.HOPPER_MINECART);
/* 27 */     minecarts.add(Material.POWERED_MINECART);
/* 28 */     minecarts.add(Material.STORAGE_MINECART);
/*    */     }
/* 30 */     public static final List<Material> recordings = new ArrayList();
/*    */     static {
/* 32 */     recordings.add(Material.RECORD_3);
/* 33 */     recordings.add(Material.RECORD_4);
/* 34 */     recordings.add(Material.RECORD_5);
/* 35 */     recordings.add(Material.RECORD_6);
/* 36 */     recordings.add(Material.RECORD_7);
/* 37 */     recordings.add(Material.RECORD_8);
/* 38 */     recordings.add(Material.RECORD_9);
/* 39 */     recordings.add(Material.RECORD_10);
/* 40 */     recordings.add(Material.RECORD_11);
/* 41 */     recordings.add(Material.RECORD_12);
/* 42 */     recordings.add(Material.GOLD_RECORD);
/* 43 */     recordings.add(Material.GREEN_RECORD);
/*    */     }
/* 45 */     public static final List<Material> seeds = new ArrayList();
/*    */     static {
/* 47 */
    seeds.add(Material.PUMPKIN_SEEDS);
/* 48 */
    seeds.add(Material.MELON_SEEDS);
/* 49 */
    seeds.add(Material.POTATO_ITEM);
/* 50 */
    seeds.add(Material.CARROT_ITEM);
/* 51 */
    seeds.add(Material.SEEDS);
}
/*    */   }
/*    */   
/*       public static final List<Material> minecraft;
/*       public static final List<Material> recordings;
/*       public static final List<Material> seeds; */