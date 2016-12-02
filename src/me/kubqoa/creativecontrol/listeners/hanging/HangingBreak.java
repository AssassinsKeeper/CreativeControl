/*    */ package me.kubqoa.creativecontrol.listeners.hanging;
/*    */ 
/*    */ import me.kubqoa.creativecontrol.api.HangingAPI;
/*    */ import me.kubqoa.creativecontrol.helpers.Methods;
/*    */ import org.bukkit.entity.Hanging;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.hanging.HangingBreakEvent;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class HangingBreak
/*    */   implements Listener
/*    */ {
/*    */   @EventHandler
/*    */   public void breakEvent(HangingBreakEvent event)
/*    */   {
/* 19 */     Hanging entity = event.getEntity();
/* 20 */     if (Methods.exclude(entity.getLocation())) return;
/* 21 */     HangingAPI HangingAPI = new HangingAPI(entity.getLocation());
/* 22 */     if (HangingAPI.isCreativeHanging()) {
/* 23 */       HangingAPI.removeHanging();
/* 24 */       entity.remove();
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Zack\Downloads\CreativeControl (6).jar!\me\kubqoa\creativecontrol\listeners\hanging\HangingBreak.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */