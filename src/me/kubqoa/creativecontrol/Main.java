 package me.kubqoa.creativecontrol;
 
 import java.io.File;
 import java.io.IOException;
 import java.sql.Connection;
 import java.sql.SQLException;
 import java.util.ArrayList;
 import java.util.HashMap;
 import java.util.List;
 import me.kubqoa.creativecontrol.commands.Commands;
 import me.kubqoa.creativecontrol.helpers.ConfigHelper;
 import me.kubqoa.creativecontrol.helpers.DatabaseHelper;
 import me.kubqoa.creativecontrol.helpers.Methods;
 import me.kubqoa.creativecontrol.helpers.SimpleConfig;
 import me.kubqoa.creativecontrol.listeners.Listeners;
 import me.kubqoa.creativecontrol.tasks.BlocksFromDB;
 import me.kubqoa.creativecontrol.tasks.HangingsFromDB;
 import me.kubqoa.creativecontrol.tasks.InventoriesToDB;
 import me.kubqoa.creativecontrol.tasks.Update;
 import me.kubqoa.creativecontrol.tasks.VehiclesFromDB;
 import me.kubqoa.creativecontrol.tasks.VehiclesToDB;
 import org.bukkit.Bukkit;
import org.bukkit.Location;
 import org.bukkit.Material;
 import org.bukkit.Server;
 import org.bukkit.entity.Player;
 import org.bukkit.plugin.Plugin;
 import org.bukkit.plugin.PluginManager;
 import org.bukkit.plugin.java.JavaPlugin;
import org.spigotmc.Metrics;


 public class Main extends JavaPlugin {
   public static Plugin factions;
   public static Plugin griefPrevention;
   public static Plugin residence;
   public static Plugin towny;
   public static Plugin vault;
   public static Plugin worldGuard;
   public static JavaPlugin thisPlugin;
   public static PluginManager pm;
   public static SimpleConfig config;
   public static SimpleConfig messages;
   public static SimpleConfig players;
   public static SimpleConfig disable;
   public static List<org.bukkit.GameMode> disabledGamemodes = new ArrayList();
   public static List<String> exclude = new ArrayList();
   public static List<String> excludeCMD = new ArrayList();
   public static List<Material> items = new ArrayList();
   public static List<Material> noTracking = new ArrayList();
   public static List<String> addperms = new ArrayList();
   public static List<String> removeperms = new ArrayList();
   public static List<String> enabledFeatures = new ArrayList();
   
   public static final List<Location> blocksLocation = new ArrayList();
   public static final HashMap<Location, Material> blocksMaterial = new HashMap();
   
   public static final List<Location> WblocksLocation = new ArrayList();
   public static final HashMap<Location, Material> WblocksMaterial = new HashMap();
   
   public static final List<Location> RblocksLocation = new ArrayList();
   public static final HashMap<Location, Material> RblocksMaterial = new HashMap();
   
   public static final List<Location> hangingsLocation = new ArrayList();
   
   public static final List<Location> WhangingsLocation = new ArrayList();
   
   public static final List<Location> RhangingsLocation = new ArrayList();
   
   public static final List<Location> vehiclesLocation = new ArrayList();
   
   public static final List<Location> WvehiclesLocation = new ArrayList();
   
   public static final List<Location> RvehiclesLocation = new ArrayList();
   
   public static final HashMap<Location, Location> UvehiclesLocation1 = new HashMap();
   public static final HashMap<Location, Location> UvehiclesLocation2 = new HashMap();
   
   public static final HashMap<String, String> cInventory = new HashMap();
   public static final HashMap<String, String> sInventory = new HashMap();
   public static final HashMap<String, String> aInventory = new HashMap();
   
   public static final HashMap<String, String> wcInventory = new HashMap();
   public static final HashMap<String, String> wsInventory = new HashMap();
   public static final HashMap<String, String> waInventory = new HashMap();
   
   public static int loggingInterval = 0;
   public static final int removingInterval = 50;
   public static final int updateInterval = 50;
   public static int blockCache = 0;
   public static int vehicleCache = 0;
   public static int hangingCache = 0;
   
   public static boolean dbtype = false;
   public static Connection c = null;
   
   public static File folder;
   public static final List<Player> addMode = new ArrayList();
   public static final List<Player> removeMode = new ArrayList();
   
   public static String protectionType;
   
   public static String prefix;
   public static String dbprefix;
   public static final List<Player> cooldownsP = new ArrayList();
   public static final List<String> cooldownsS = new ArrayList();
   public static long cooldown = 0L;
   
   public static String serverVersion;

 
   public void onEnable()
   {
     super.onEnable();
     Methods.console("&4[------------------[&cCreative Control&4]------------------]");
     Methods.console("&6verison: &c" + getDescription().getVersion() + "&6 by &cKubqoA");
     Methods.console("&6If you have found some in-game &cbugs &6report them to the issue tracker.");
     Methods.console("&6Be sure to leave review on plugin page on SpigotMC.");
     Methods.console("");
     pm = super.getServer().getPluginManager();
     folder = new File(getDataFolder().getParent() + "/CreativeControl");
     if ((!folder.exists()) && (!folder.isDirectory())) {
       Methods.console("&6Plugin directory&c not found, creating one!");
       if (!folder.mkdirs()) {
         Methods.console("&cFailed to create plugin directory!");
       }
     }


     serverVersion = Bukkit.getBukkitVersion().split("-")[0];
     serverVersion = serverVersion.substring(0, serverVersion.lastIndexOf("."));
     
 
     ConfigHelper configHelper = new ConfigHelper(this);
     configHelper.start();
     config = configHelper.config;
     messages = configHelper.messages;
     players = configHelper.players;
     disable = configHelper.disable;
     protectionType = configHelper.protectionType;
     prefix = org.bukkit.ChatColor.translateAlternateColorCodes('&', configHelper.prefix) + " ";
     dbprefix = configHelper.dbprefix;
     cooldown = configHelper.cooldown;
     loggingInterval = configHelper.logwhen;
     disabledGamemodes = configHelper.disabledGamemodes;
     exclude = configHelper.exclude;
     excludeCMD = configHelper.excludeCMD;
     items = configHelper.items;
     addperms = configHelper.addperms;
     removeperms = configHelper.removeperms;
     noTracking = configHelper.noTracking;
     blockCache = configHelper.blockCache;
     vehicleCache = configHelper.vehicleCache;
     hangingCache = configHelper.hangingCache;
     disabledGamemodes = configHelper.disabledGamemodes;
     enabledFeatures = configHelper.enabledFeatures;
     if (config.getString("db-type").equalsIgnoreCase("mysql")) { dbtype = true;
     }
     
     DatabaseHelper databaseHelper = new DatabaseHelper();
     databaseHelper.start();
     databaseHelper.checkTables();
     
 
     factions = pm.getPlugin("Factions");
     griefPrevention = pm.getPlugin("GriefPrevention");
     residence = pm.getPlugin("Residence");
     towny = pm.getPlugin("Towny");
     vault = pm.getPlugin("Vault");
     worldGuard = pm.getPlugin("WorldGuard");
     thisPlugin = (JavaPlugin)pm.getPlugin("CreativeControlByKubqoA");
     
     if (factions != null) {
       Plugin massivecore = pm.getPlugin("MassiveCore");
       if (massivecore != null) {
         Methods.console("&cFound MassiveCore plugin! Implementing it!");
         if (!massivecore.isEnabled()) {
           Methods.console("&cMassiveCore not enabled! Enabling!");
           pm.enablePlugin(massivecore);
         }
         Methods.console("&cFound Factions plugin! Implementing it!");
         if (!factions.isEnabled()) {
           Methods.console("&cFactions not enabled! Enabling!");
           pm.enablePlugin(factions);
         }
       } else {
         Methods.console("&cFound Factions but not MassiveCore! Error!");
       }
     }
     
     if (griefPrevention != null) {
       Methods.console("&cFound GriefPrevention plugin! Implementing it!");
       if (!griefPrevention.isEnabled()) {
         Methods.console("&cGriefPrevention not enabled! Enabling!");
         pm.enablePlugin(griefPrevention);
       }
     }
     
     if (residence != null) {
       Methods.console("&cFound Residence plugin! Implementing it!");
       if (!residence.isEnabled()) {
         Methods.console("&cResidence not enabled! Enabling!");
         pm.enablePlugin(residence);
       }
     }
     
     if (towny != null) {
       Methods.console("&cFound Towny plugin! Implementing it!");
       if (!towny.isEnabled()) {
         Methods.console("&cTowny not enabled! Enabling!");
         pm.enablePlugin(towny);
       }
     }
     
     if (vault != null) {
       Methods.console("&cFound Vault plugin! Implementing it!");
       if (!vault.isEnabled()) {
         Methods.console("&cVault not enabled! Enabling!");
         pm.enablePlugin(vault);
       }
       new me.kubqoa.creativecontrol.integrations.Vault().setup();
     }
     
     if (worldGuard != null) {
       Methods.console("&cFound WorldGuard plugin! Implementing it!");
       if (!worldGuard.isEnabled()) {
         Methods.console("&cWorldGuard not enabled! Enabling!");
         pm.enablePlugin(worldGuard);
       }
     }
     
 
     me.kubqoa.creativecontrol.tasks.LoadFromDB.load();
     
 
     Methods.console("&cRegistering listeners!");
     Listeners listeners = new Listeners(pm, this);
     listeners.init();
     Methods.console("&cDone!");
     
 
     Methods.console("&cRegistering commands!");
     Commands commands = new Commands(this);
     commands.init();
     Methods.console("&cDone!");
     
     Methods.console("&cInitializing Metrics!");
     try {
       Metrics metrics = new Metrics();
       Metrics.Graph dbtype = metrics.createGraph("Database type");
       dbtype.addPlotter(new Metrics.Plotter("MySQL")
       {
         public int getValue() {
           if (Main.dbtype) return 1;
           return 0;
         }
       });
       dbtype.addPlotter(new Metrics.Plotter("SQLite")
       {
         public int getValue() {
           if (!Main.dbtype) return 1;
           return 0;
         }
       });
       metrics.start();
       Methods.console("&cDone!");
     }
     catch (IOException e) {}
     
 
     Methods.console("&cPlugin init completed!");
     Methods.console("&4[------------------------------------------------------]");
     
 
     if (config.getBoolean("check-for-updates")) {
       new Update().runTaskTimerAsynchronously(this, 0L, 864000L);
     }
   }
   
   public void onDisable()
   {
     super.onDisable();
     if (WblocksLocation.size() > 0) {
       new me.kubqoa.creativecontrol.tasks.BlocksToDB().run();
     }
     if (WvehiclesLocation.size() > 0) {
       new VehiclesToDB().run();
     }
     if (WhangingsLocation.size() > 0) {
       new me.kubqoa.creativecontrol.tasks.HangingsToDB().run();
     }
     if (RblocksLocation.size() > 0) {
       new BlocksFromDB().run();
     }
     if (RvehiclesLocation.size() > 0) {
       new VehiclesFromDB().run();
     }
     if (RhangingsLocation.size() > 0) {
       new HangingsFromDB().run();
     }
     if (UvehiclesLocation1.size() > 0) {
       new me.kubqoa.creativecontrol.tasks.VehiclesUpdateDB().run();
     }
     if ((wsInventory.size() > 0) && (waInventory.size() > 0) && (wcInventory.size() > 0)) {
       new InventoriesToDB("all").run();
     } else if (wsInventory.size() > 0) {
       new InventoriesToDB("SURVIVAL").run();
     } else if (wcInventory.size() > 0) {
       new InventoriesToDB("CREATIVE").run();
     } else if (waInventory.size() > 0) {
       new InventoriesToDB("ADVENTURE").run();
     }
     disable.set("old-db-prefix", dbprefix);
     disable.saveConfig();
     try {
       c.close();
     } catch (SQLException e) {
       e.printStackTrace();
     }
   }
 }


