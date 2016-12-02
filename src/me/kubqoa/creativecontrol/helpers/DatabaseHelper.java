 package me.kubqoa.creativecontrol.helpers;
 
 import java.sql.Connection;
 import java.sql.DriverManager;
 import java.sql.ResultSet;
 import java.sql.Statement;
 import me.kubqoa.creativecontrol.Main;
 
 
 
 
 
 public class DatabaseHelper
 {
   private Connection c;
   private final boolean dbtype;
   
   public DatabaseHelper()
   {
     this.dbtype = Main.dbtype;
   }
   
   public void start() {
     try {
       if (this.dbtype) {
         Methods.console("&cSetting up MySQL connection!");
         Class.forName("com.mysql.jdbc.Driver");
         String host = Main.config.getString("db-host");
         String port = Main.config.getString("db-port");
         String user = Main.config.getString("db-user");
         String password = Main.config.getString("db-password");
         String database = Main.config.getString("db-database");
         String jdbc = "jdbc:mysql:";
         this.c = DriverManager.getConnection(jdbc, user, password);
       } else {
         Methods.console("&cSetting up SQLite connection!");
         Class.forName("org.sqlite.JDBC");
         this.c = DriverManager.getConnection("jdbc:sqlite:" + Main.folder + "/creativecontrol.db");
       }
     } catch (Exception e) {
       Methods.console("&cError occurred while initializing database!");
       e.printStackTrace();
     }
     Main.c = this.c;
     oldCC();
     String oldPrefix = Main.disable.getString("old-db-prefix");
     if (!Main.dbprefix.equals(oldPrefix)) {
       changePrefix(oldPrefix, Main.dbprefix);
     }
     Methods.console("&cSuccessful!");
   }
   
   public void checkTables()
   {
     if (this.dbtype) {
       if (selectSQL("SHOW TABLES LIKE '" + Main.dbprefix + "blocks'") == 0) {
         updateSQL("CREATE TABLE `" + Main.dbprefix + "blocks` ( `index` INT NOT NULL AUTO_INCREMENT , `x` REAL NOT NULL , `y` REAL NOT NULL , `z` REAL NOT NULL , `world` TEXT NOT NULL , `material` TEXT NOT NULL , PRIMARY KEY (`index`))");
       }
       if (selectSQL("SHOW TABLES LIKE '" + Main.dbprefix + "inventories'") == 0) {
         updateSQL("CREATE TABLE `" + Main.dbprefix + "inventories` ( `index` INT NOT NULL AUTO_INCREMENT , `uuid` TEXT NOT NULL, `gamemode` TEXT NOT NULL, `inventory` TEXT NOT NULL, `armor` TEXT NOT NULL, PRIMARY KEY (`index`))");
       }
       if (selectSQL("SHOW TABLES LIKE '" + Main.dbprefix + "vehicles'") == 0) {
         updateSQL("CREATE TABLE `" + Main.dbprefix + "vehicles` ( `index` INT NOT NULL AUTO_INCREMENT , `world` TEXT NOT NULL , `x` REAL NOT NULL , `y` REAL NOT NULL , `z` REAL NOT NULL , `uuid` TEXT , `timestamp` TEXT , PRIMARY KEY (`index`))");
       }
       if (selectSQL("SHOW TABLES LIKE '" + Main.dbprefix + "hangings'") == 0) {
         updateSQL("CREATE TABLE `" + Main.dbprefix + "hangings` ( `index` INT NOT NULL AUTO_INCREMENT , `world` TEXT NOT NULL , `x` REAL NOT NULL , `y` REAL NOT NULL , `z` REAL NOT NULL , `uuid` TEXT , `timestamp` TEXT , PRIMARY KEY (`index`))");
       }
     } else {
       if (selectSQL("SELECT name FROM sqlite_master WHERE type='table' AND name='" + Main.dbprefix + "blocks'") == 0) {
         updateSQL("CREATE TABLE `" + Main.dbprefix + "blocks` ( `index` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT , `x` REAL , `y` REAL , `z` REAL , 'world' TEXT , 'material' TEXT )");
       }
       if (selectSQL("SELECT name FROM sqlite_master WHERE type='table' AND name='" + Main.dbprefix + "inventories'") == 0) {
         updateSQL("CREATE TABLE `" + Main.dbprefix + "inventories` ( `index`\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, `uuid`\tTEXT, `gamemode`\tTEXT, `inventory`\tTEXT, `armor`\tTEXT );");
       }
       if (selectSQL("SELECT name FROM sqlite_master WHERE type='table' AND name='" + Main.dbprefix + "vehicles'") == 0) {
         updateSQL("CREATE TABLE `" + Main.dbprefix + "vehicles` (`index`  INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,`world`  TEXT,`x`  REAL,`y`  REAL,`z`  REAL , `uuid` TEXT , `timestamp` TEXT)");
       }
       if (selectSQL("SELECT name FROM sqlite_master WHERE type='table' AND name='" + Main.dbprefix + "hangings'") == 0) {
         updateSQL("CREATE TABLE `" + Main.dbprefix + "hangings` (`index`  INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,`world`  TEXT,`x`  REAL,`y`  REAL,`z`  REAL , `uuid` TEXT , `timestamp` TEXT)");
       }
     }
   }
   
   public static int selectSQL(String sql) {
     int i = 0;
     try {
       Statement stmt = Main.c.createStatement();
       ResultSet rs = stmt.executeQuery(sql);
       while (rs.next()) {
         i++;
       }
       rs.close();
       stmt.close();
     } catch (Exception e) {
       e.printStackTrace();
     }
     return i;
   }
   
   public static void updateSQL(String sql) {
     try {
       Statement stmt = Main.c.createStatement();
       stmt.executeUpdate(sql);
       stmt.close();
     } catch (Exception e) {
       e.printStackTrace();
     }
   }
   
   private void changePrefix(String oldPrefix, String newPrefix) {
     if (this.dbtype) {
       if (selectSQL("SHOW TABLES LIKE '" + oldPrefix + "blocks'") > 0) {
         updateSQL("RENAME TABLE " + oldPrefix + "blocks TO " + newPrefix + "blocks");
       }
       if (selectSQL("SHOW TABLES LIKE '" + oldPrefix + "inventories'") > 0) {
         updateSQL("RENAME TABLE " + oldPrefix + "inventories TO " + newPrefix + "inventories");
       }
       if (selectSQL("SHOW TABLES LIKE '" + oldPrefix + "vehicles'") > 0) {
         updateSQL("RENAME TABLE " + oldPrefix + "vehicles TO " + newPrefix + "vehicles");
       }
       if (selectSQL("SHOW TABLES LIKE '" + oldPrefix + "hangings'") > 0) {
         updateSQL("RENAME TABLE " + oldPrefix + "hangings TO " + newPrefix + "hangings");
       }
     } else {
       if (selectSQL("SELECT name FROM sqlite_master WHERE type='table' AND name='" + oldPrefix + "blocks'") > 0) {
         updateSQL("ALTER TABLE '" + oldPrefix + "blocks' RENAME TO '" + newPrefix + "blocks'");
       }
       if (selectSQL("SELECT name FROM sqlite_master WHERE type='table' AND name='" + oldPrefix + "inventories'") > 0) {
         updateSQL("ALTER TABLE '" + oldPrefix + "inventories' RENAME TO '" + newPrefix + "inventories'");
       }
       if (selectSQL("SELECT name FROM sqlite_master WHERE type='table' AND name='" + oldPrefix + "vehicles'") > 0) {
         updateSQL("ALTER TABLE '" + oldPrefix + "vehicles' RENAME TO '" + newPrefix + "vehicles'");
       }
       if (selectSQL("SELECT name FROM sqlite_master WHERE type='table' AND name='" + oldPrefix + "hangings'") > 0) {
         updateSQL("ALTER TABLE '" + oldPrefix + "hangings' RENAME TO '" + newPrefix + "hangings'");
       }
     }
   }
   
   private void oldCC() {
     if (this.dbtype) {
       if (selectSQL("SHOW TABLES LIKE '" + Main.dbprefix + "minecarts'") > 0) {
         updateSQL("RENAME TABLE " + Main.dbprefix + "minecarts TO " + Main.dbprefix + "vehicles");
         editBlockTable();
       }
       if (selectSQL("SHOW TABLES LIKE '" + Main.dbprefix + "itemframes'") > 0) {
         updateSQL("RENAME TABLE " + Main.dbprefix + "itemframes TO " + Main.dbprefix + "hangings");
       }
     } else {
       if (selectSQL("SELECT name FROM sqlite_master WHERE type='table' AND name='" + Main.dbprefix + "minecarts'") > 0) {
         updateSQL("ALTER TABLE '" + Main.dbprefix + "minecarts' RENAME TO '" + Main.dbprefix + "vehicles'");
       }
       if (selectSQL("SELECT name FROM sqlite_master WHERE type='table' AND name='" + Main.dbprefix + "itemframes'") > 0) {
         updateSQL("ALTER TABLE '" + Main.dbprefix + "itemframes' RENAME TO '" + Main.dbprefix + "hangings'");
       }
     }
   }
   
   private void editBlockTable() {
     if (this.dbtype) {
       updateSQL("ALTER TABLE `" + Main.dbprefix + "blocks` DROP uuid");
       updateSQL("ALTER TABLE `" + Main.dbprefix + "blocks` DROP timestamp");
     }
   }
 }


