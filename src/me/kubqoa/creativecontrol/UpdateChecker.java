/*    */ package me.kubqoa.creativecontrol;
/*    */ 
/*    */ import java.io.BufferedReader;
/*    */ import java.io.IOException;
/*    */ import java.io.InputStreamReader;
/*    */ import java.io.OutputStream;
/*    */ import java.net.HttpURLConnection;
/*    */ import java.net.URL;
/*    */ import java.util.regex.Pattern;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class UpdateChecker
/*    */ {
/*    */   private String latestVersion;
/*    */   
/*    */   private boolean checkHigher(String currentVersion, String newVersion)
/*    */   {
/* 19 */     String current = toReadable(currentVersion);
/* 20 */     String newVers = toReadable(newVersion);
/* 21 */     return current.compareTo(newVers) < 0;
/*    */   }
/*    */   
/*    */   public void checkUpdate(String currentVersion) {
/* 25 */     String version = getVersion("98BE0FE67F88AB82B4C197FAF1DC3B69206EFDCC4D3B80FC83A00037510B99B4", 9988);
/* 26 */     if (checkHigher(currentVersion, version))
/* 27 */       this.latestVersion = version;
/*    */   }
/*    */   
/*    */   public String getLatestVersion() {
/* 31 */     return this.latestVersion;
/*    */   }
/*    */   
/*    */   private String getVersion(String key, int resourceId) {
/* 35 */     String version = null;
/*    */     try {
/* 37 */       HttpURLConnection con = (HttpURLConnection)new URL("http://www.spigotmc.org/api/general.php").openConnection();
/* 38 */       con.setDoOutput(true);
/* 39 */       con.setRequestMethod("POST");
/* 40 */       con.getOutputStream().write(("key=" + key + "&resource=" + resourceId).getBytes("UTF-8"));
/* 41 */       version = new BufferedReader(new InputStreamReader(con.getInputStream())).readLine();
/*    */     } catch (IOException ex) {
/* 43 */       ex.printStackTrace();
/*    */     }
/* 45 */     return version;
/*    */   }
/*    */   
/*    */   private String toReadable(String version) {
/* 49 */     String[] split = Pattern.compile(".", 16).split(version.replace("v", ""));
/* 50 */     version = "";
/* 51 */     for (String s : split)
/* 52 */       version = version + String.format("%4s", new Object[] { s });
/* 53 */     return version;
/*    */   }
/*    */ }


/* Location:              C:\Users\Zack\Downloads\CreativeControl (6).jar!\me\kubqoa\creativecontrol\UpdateChecker.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */