/*    */ package me.kubqoa.creativecontrol.api;
/*    */ 
/*    */ import java.util.List;
/*    */ import me.kubqoa.creativecontrol.Main;
/*    */ import me.kubqoa.creativecontrol.helpers.HangingHelper;
/*    */ import me.kubqoa.creativecontrol.integrations.Integrations;
/*    */ import me.kubqoa.creativecontrol.tasks.HangingsFromDB;
/*    */ import me.kubqoa.creativecontrol.tasks.HangingsToDB;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class HangingAPI
/*    */ {
/*    */   private final Location loc;
/*    */   
/* 18 */   public HangingAPI(Location location) { this.loc = location; }
/*    */   
/*    */   public boolean isCreativeHanging() {
/* 21 */     if (Main.hangingsLocation.contains(this.loc))
/* 22 */       return true;
/* 23 */     if (Main.WhangingsLocation.contains(this.loc))
/* 24 */       return true;
/* 25 */     if (HangingHelper.isCreativeHanging(this.loc)) {
/* 26 */       return true;
/*    */     }
/*    */     
/* 29 */     return false;
/*    */   }
/*    */   
/*    */   public boolean canBreak(Player player) {
/* 33 */     return Integrations.canBreak(this.loc.getBlock(), player);
/*    */   }
/*    */   
/*    */   public void removeHanging() {
/* 37 */     if (Main.WhangingsLocation.contains(this.loc)) {
/* 38 */       Main.WhangingsLocation.remove(this.loc);
/*    */     }
/* 40 */     if (Main.hangingsLocation.contains(this.loc)) {
/* 41 */       Main.hangingsLocation.remove(this.loc);
/*    */     }
/* 43 */     if (HangingHelper.isCreativeHanging(this.loc)) {
/* 44 */       Main.RhangingsLocation.add(this.loc);
/* 45 */       if (Main.RhangingsLocation.size() >= 50) {
/* 46 */         new HangingsFromDB().runTaskAsynchronously(Main.thisPlugin);
/*    */       }
/*    */     }
/*    */   }
/*    */   
/*    */   public void addHanging() {
/* 52 */     if (Main.hangingsLocation.size() < Main.hangingCache) {
/* 53 */       Main.hangingsLocation.add(this.loc);
/*    */     }
/* 55 */     Main.WhangingsLocation.add(this.loc);
/* 56 */     if (Main.WhangingsLocation.size() >= Main.loggingInterval) {
/* 57 */       new HangingsToDB().runTaskAsynchronously(Main.thisPlugin);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Zack\Downloads\CreativeControl (6).jar!\me\kubqoa\creativecontrol\api\HangingAPI.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */