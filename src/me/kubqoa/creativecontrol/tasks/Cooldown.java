/*    */ package me.kubqoa.creativecontrol.tasks;
/*    */ 
/*    */ import java.util.List;
/*    */ import me.kubqoa.creativecontrol.Main;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.scheduler.BukkitRunnable;
/*    */ 
/*    */ public class Cooldown extends BukkitRunnable
/*    */ {
/*    */   private final Player player;
/*    */   private final String msg;
/*    */   
/*    */   public Cooldown(Player p, String message)
/*    */   {
/* 15 */     this.player = p;this.msg = message;
/*    */   }
/*    */   
/*    */   public void run() {
/* 19 */     int i = 0;
/* 20 */     for (String string : Main.cooldownsS) {
/* 21 */       if ((string.equalsIgnoreCase(this.msg)) && 
/* 22 */         (Main.cooldownsP.get(i) == this.player)) {
/*    */         break;
/*    */       }
/*    */       
/* 26 */       i++;
/*    */     }
/* 28 */     Main.cooldownsS.remove(i);
/* 29 */     Main.cooldownsP.remove(i);
/*    */   }
/*    */ }


/* Location:              C:\Users\Zack\Downloads\CreativeControl (6).jar!\me\kubqoa\creativecontrol\tasks\Cooldown.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */