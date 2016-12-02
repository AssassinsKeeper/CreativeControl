/*    */ package me.kubqoa.creativecontrol.utils.converter;
/*    */ 
/*    */ import me.kubqoa.creativecontrol.helpers.Methods;
/*    */ import org.bukkit.command.CommandSender;
/*    */ 
/*    */ 
/*    */ public class Converter
/*    */ {
/*    */   private final String from;
/*    */   private final String to;
/*    */   private final CommandSender sender;
/*    */   
/*    */   public Converter(String from, String to, CommandSender sender)
/*    */   {
/* 15 */     this.from = from;
/* 16 */     this.to = to;
/* 17 */     this.sender = sender;
/*    */   }
/*    */   
/*    */   public void start() {
/* 21 */     if (this.from.equalsIgnoreCase("oldcc")) {
/* 22 */       if (this.to.equalsIgnoreCase("current")) {
/* 23 */         Methods.sendMsg(this.sender, "&cNow not supported. Sorry.");
/*    */       } else {
/* 25 */         Methods.sendMsg(this.sender, "&6oldcc &ccan only be used in combination with &6current&c.");
/*    */       }
/* 27 */       return;
/*    */     }
/* 29 */     InsideConvert insideConvert = new InsideConvert(this.sender);
/* 30 */     if (this.from.equalsIgnoreCase("mysql")) {
/* 31 */       if (this.to.equalsIgnoreCase("sqlite")) {
/* 32 */         insideConvert.sqlite();
/*    */       } else {
/* 34 */         Methods.sendMsg(this.sender, "&6mysql &ccan only be used in combination with &6sqlite&c.");
/*    */       }
/* 36 */     } else if (this.from.equalsIgnoreCase("sqlite")) {
/* 37 */       if (this.to.equalsIgnoreCase("mysql")) {
/* 38 */         insideConvert.mysql();
/*    */       } else {
/* 40 */         Methods.sendMsg(this.sender, "&6sqlite &ccan only be used in combination with &6mysql&c.");
/*    */       }
/*    */     } else {
/* 43 */       Methods.sendMsg(this.sender, "&cIncorrect attribute &6from&c.");
/* 44 */       Methods.sendMsg(this.sender, "&c<from> &6- From can be either &coldcc&6, &csqlite&6 or &cmysql");
/*    */     }
/*    */   }
/*    */ }