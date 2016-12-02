 package me.kubqoa.creativecontrol.helpers;
 
 import java.io.File;
 import java.util.ArrayList;
 import java.util.Arrays;
 import java.util.List;
 import me.kubqoa.creativecontrol.Main;
 import org.bukkit.GameMode;
 import org.bukkit.Material;
 import org.bukkit.plugin.java.JavaPlugin;
 
 public class ConfigHelper
 {
   private final JavaPlugin plugin;
   private final File folder;
   public SimpleConfig config;
   public SimpleConfig messages;
   public SimpleConfig players;
   public SimpleConfig disable;
   public long cooldown;
   public int logwhen;
   public String prefix;
   public String dbprefix;
   public String protectionType;
   public final List<GameMode> disabledGamemodes = new ArrayList();
   public final List<String> exclude = new ArrayList();
   public final List<String> excludeCMD = new ArrayList();
   public final List<String> addperms = new ArrayList();
   public final List<String> removeperms = new ArrayList();
   public final List<Material> items = new ArrayList();
   public final List<Material> noTracking = new ArrayList();
   public final List<String> enabledFeatures = new ArrayList();
   public int blockCache = 0; public int vehicleCache = 0; public int hangingCache = 0;
   public ConfigHelper(JavaPlugin javaPlugin) { this.folder = Main.folder;this.plugin = javaPlugin;
   }
   
   public void start() {
     if (((!this.folder.exists()) || (!this.folder.isDirectory())) && 
       (!this.folder.mkdirs())) {
       Methods.console("&cFailed to create plugin directory!");
     }
     
 
     SimpleConfigManager manager = new SimpleConfigManager(this.plugin);
     boolean exists = new File(this.folder + "/config.yml").exists();
     this.config = manager.getNewConfig("config.yml", new String[] { "CreativeControl", "by KubqoA" });
     if (exists) {
       checkConfig();
     } else {
       Methods.console("&6config.yml&c not found, creating one!");
       createConfig();
     }
     exists = new File(this.folder + "/messages.yml").exists();
     this.messages = manager.getNewConfig("messages.yml", new String[] { "CreativeControl", "by KubqoA" });
     if (exists) {
       oldMessages();
       checkMessages();
     } else {
       Methods.console("&6messages.yml&c not found, creating one!");
       createMessages();
     }
     this.players = manager.getNewConfig("players.yml", new String[] { "CreativeControl", "by KubqoA", "DO NOT MODIFY THIS FILE" });
     this.disable = manager.getNewConfig("disable.yml", new String[] { "CreativeControl", "by KubqoA", "DO NOT MODIFY THIS FILE" });
     
     this.cooldown = this.config.getInt("message-cooldown");
     this.protectionType = this.config.getString("protection-type");
     this.prefix = this.config.getString("message-prefix");
     this.dbprefix = this.config.getString("db-prefix");
     this.logwhen = this.config.getInt("logging-interval");
     this.blockCache = this.config.getInt("block-memory-limit");
     this.vehicleCache = this.config.getInt("vehicle-memory-limit");
     this.hangingCache = this.config.getInt("hanging-memory-limit");
     for (Object o : this.config.getList("disabled-gamemodes")) {
       this.disabledGamemodes.add(GameMode.valueOf(o.toString()));
     }
     for (Object o : this.config.getList("disabled-worlds")) {
       this.exclude.add(o.toString());
     }
     for (Object o : this.config.getList("disabled-commands")) {
       this.excludeCMD.add(o.toString());
     }
     for (Object o : this.config.getList("disabled-items")) {
       this.items.add(Material.getMaterial(o.toString()));
     }
     for (Object o : this.config.getList("add-permissions")) {
       this.addperms.add(o.toString());
     }
     for (Object o : this.config.getList("remove-permissions")) {
       this.removeperms.add(o.toString());
     }
     for (Object o : this.config.getList("excluded-blocks")) {
       this.noTracking.add(Material.valueOf(o.toString()));
     }
     for (Object o : this.config.getList("enabled-features")) {
       this.enabledFeatures.add(o.toString());
     }
   }
   
   private void createConfig() {
     this.config.set("disabled-commands", Arrays.asList(new String[] { "/command1", "/command2" }), new String[] { "Put here all the commands you want to ", "disable in creative! If you want to", "enable these commands for specific players", "give them permission cc.cmd./command_here", "(e.g. cc.cmd./command1)" });
     this.config.set("disabled-gamemodes", Arrays.asList(new String[] { "ADVENTURE", "SPECTATOR" }), new String[] { "Here you can define which gamemodes you", " want to disable!", "You can also create node disabled-gamemodes-worldname", "and specify disabled gamemodes for each world." });
     this.config.set("disabled-items", Arrays.asList(new String[] { "TNT", "BEDROCK" }), new String[] { "Here you can put blocks or items which you", " want to disable in creative.", "List of blocks you can disable can be", "found on this website", "https:" });
     this.config.set("disabled-worlds", Arrays.asList(new String[] { "world1", "world2" }), new String[] { "Here you can define worlds in which should", "be the functions of this plugin disabled!" });
     this.config.set("disable-creative-spawners", Boolean.valueOf(true), new String[] { "Set to true if you want to prevent", "creative placed spawners from spawning mobs." });
     this.config.set("remove-permissions", Arrays.asList(new String[] { "permissionnode1", "permission.node.2" }), new String[] { "Here you can define which permissions will be", "taken when switched to creative mode. e.g. essentials.tpa" });
     this.config.set("add-permissions", Arrays.asList(new String[] { "permissionnode1", "permission.node.2" }), new String[] { "Here you can define which permissions will be", "added when switched to creative mode. e.g. essentials.tpa" });
     this.config.set("db-type", "sqlite", new String[] { "Here you can set if you want to use", "sqlite DB or mysql DB" });
     this.config.set("db-host", "localhost", new String[] { "Set MySQL address here", "(only when db type set to mysql)" });
     this.config.set("db-port", "3306", new String[] { "Set MySQL server port here", "(only when db type set to mysql)" });
     this.config.set("db-user", "user", new String[] { "Set the user login for mysql database", "(only when db type set to mysql" });
     this.config.set("db-password", "password", new String[] { "Set user password", "(only when db type set to mysql)" });
     this.config.set("db-database", "database", new String[] { "Set MySQL database name", "(only when db type set to mysql)" });
     this.config.set("db-prefix", "cc_", new String[] { "Set tables prefix here", "(only when db type set to mysql)" });
     this.config.set("message-prefix", "&4[CC]&c", "Set message prefix here (supports colors)");
     this.config.set("inventory-switching", Boolean.valueOf(true), new String[] { "Set to true to enable inventory switching", "Set to false to disable it" });
     this.config.set("check-for-updates", Boolean.valueOf(true), new String[] { "Set to true to enable automatic update checking", "Set to false to disable it" });
     this.config.set("message-cooldown", Long.valueOf(2L), new String[] { "Here you can define time in seconds for", "which will be all messages in cooldown", "before sending them again to the player.", "Set to 0 to disable." });
     this.config.set("logging-interval", Integer.valueOf(10), new String[] { "Here you can define after how many creative", "placed blocks should they be written to", "database. If this limit is not reached", "they will be logged after 5 minutes." });
     this.config.set("excluded-blocks", Arrays.asList(new String[] { "DIRT", "SAND" }), new String[] { "Place here blocks you want to be", "excluded from being tracked when", "placed in creative." });
     this.config.set("block-memory-limit", Integer.valueOf(10000), new String[] { "Set how many blocks can be stored", "in memory and the others will", "be stored in database.", "set to 0 to disable this memory", "set to -1 to use only memory." });
     this.config.set("vehicle-memory-limit", Integer.valueOf(10000), new String[] { "Set how many vehicles can be stored", "in memory and the others will", "be stored in database.", "set to 0 to disable this memory", "set to -1 to use only memory." });
     this.config.set("hanging-memory-limit", Integer.valueOf(10000), new String[] { "Set how many hangings can be stored", "in memory and the others will", "be stored in database.", "set to 0 to disable this memory", "set to -1 to use only memory." });
     this.config.set("enabled-features", Arrays.asList(new String[] { "BlockBreak", "BlockFall", "BlockPlace", "BlockExplode", "BedrockBreak", "HangingBreak", "HangingPlace", "VehicleCreate", "VehicleDestroy", "VehicleMove", "PlayerArmorStandManipulate", "PlayerCommandRestrict", "PlayerBannedItemFromInventory", "PlayerDamageEntity", "PlayerDeathNoDrop", "PlayerDropItem", "PlayerGamemodeChange", "PlayerJoin", "PlayerOpenRestrictedInventory", "PlayerPickupItem", "PlayerQuit", "SpawnerSpawnEntity", "CreatureSpawn", "PistonExtend", "PistonRetract" }), new String[] { "Remove or comment out specific", "features to disable them." });
     this.config.saveConfig();
   }
   
   private void createMessages() {
     this.messages.set("container-chest", "You are not allowed to open chest in creative!");
     this.messages.set("container-chestdouble", "You are not allowed to open chest in creative!");
     this.messages.set("container-furnace", "You are not allowed to open furnace in creative!");
     this.messages.set("container-crafting", "You are not allowed to open crafting table in creative!");
     this.messages.set("container-enderchest", "You are not allowed to open enderchest in creative!");
     this.messages.set("container-minecart", "You are not allowed to open minecart in creative!");
     this.messages.set("container-dispenser", "You are not allowed to open dispenser in creative!");
     this.messages.set("container-dropper", "You are not allowed to open dropper in creative!");
     this.messages.set("container-hopper", "You are not allowed to open hopper in creative!");
     this.messages.set("container-horse", "You are not allowed to open horse inventory in creative!");
     this.messages.set("container-donkey", "You are not allowed to open donkey inventory in creative!");
     this.messages.set("container-enchant", "You are not allowed to open enchantment table in creative!");
     this.messages.set("container-repair", "You are not allowed to open anvil in creative!");
     this.messages.set("container-villager", "You are not allowed to trade with villager in creative!");
     this.messages.set("container-brewing", "You are not allowed to open brewing stand in creative!");
     this.messages.set("damage-creeper", "You are not allowed to damage creepers while in creative!");
     this.messages.set("damage-skeleton", "You are not allowed to damage skeletons while in creative!");
     this.messages.set("damage-spider", "You are not allowed to damage spiders while in creative!");
     this.messages.set("damage-zombie", "You are not allowed to damage zombies while in creative!");
     this.messages.set("damage-slime", "You are not allowed to damage slimes while in creative!");
     this.messages.set("damage-ghast", "You are not allowed to damage ghasts while in creative!");
     this.messages.set("damage-zombie-pigman", "You are not allowed to damage zombie pigmans while in creative!");
     this.messages.set("damage-enderman", "You are not allowed to damage endermans while in creative!");
     this.messages.set("damage-cave-spider", "You are not allowed to damage cave spiderss while in creative!");
     this.messages.set("damage-silverfish", "You are not allowed to damage silverfish while in creative!");
     this.messages.set("damage-blaze", "You are not allowed to damage blazes while in creative!");
     this.messages.set("damage-magma-cube", "You are not allowed to damage magma cubes while in creative!");
     this.messages.set("damage-bat", "You are not allowed to damage bats while in creative!");
     this.messages.set("damage-witch", "You are not allowed to damage witches while in creative!");
     this.messages.set("damage-endermite", "You are not allowed to damage endermites while in creative!");
     this.messages.set("damage-guardian", "You are not allowed to damage guardians while in creative!");
     this.messages.set("damage-pig", "You are not allowed to damage pigs while in creative!");
     this.messages.set("damage-sheep", "You are not allowed to damage sheep while in creative!");
     this.messages.set("damage-cow", "You are not allowed to damage cows while in creative!");
     this.messages.set("damage-chicken", "You are not allowed to damage chickens while in creative!");
     this.messages.set("damage-squid", "You are not allowed to damage squids while in creative!");
     this.messages.set("damage-wolf", "You are not allowed to damage wolfs while in creative!");
     this.messages.set("damage-mooshroom", "You are not allowed to damage mooshrooms while in creative!");
     this.messages.set("damage-ocelot", "You are not allowed to damage ocelots while in creative!");
     this.messages.set("damage-donkey", "You are not allowed to damage donkeys while in creative!");
     this.messages.set("damage-horse", "You are not allowed to damage horses while in creative!");
     this.messages.set("damage-mule", "You are not allowed to damage mules while in creative!");
     this.messages.set("damage-rabbit", "You are not allowed to damage rabbits while in creative!");
     this.messages.set("damage-villager", "You are not allowed to damage villagers while in creative!");
     this.messages.set("damage-giant", "You are not allowed to damage giants while in creative!");
     this.messages.set("damage-iron-golem", "You are not allowed to damage iron golems while in creative!");
     this.messages.set("damage-ender-dragon", "You are not allowed to damage ender dragons while in creative!");
     this.messages.set("damage-snow-golem", "You are not allowed to damage snow golems while in creative!");
     this.messages.set("damage-wither", "You are not allowed to damage withers while in creative!");
     this.messages.set("damage-ender-crystal", "You are not allowed to destroy ender crystals while in creative!");
     this.messages.set("damage-player", "You are not allowed to damage players while in creative!");
     this.messages.set("damage-shulker", "You are not allowed to damage shulkers while in creative!");
     this.messages.set("disabled-gamemode", "You can't use this gamemode because it is disabled!");
     this.messages.set("block-break-bedrock", "You are not allowed to break bedrock while in creative!");
     this.messages.set("hanging-break", "This item frame was placed in creative, meaning you can't get the drops from it!");
     this.messages.set("block-break", "This block was placed in creative, meaning you can't get the drops from it!");
     this.messages.set("vehicle-break", "This minecart was place in creative, meaning you can't get the drops from it!");
     this.messages.set("pickup", "You can't pick up items while in creative");
     this.messages.set("ender_portal", "You can't activate ender portal while in creative!");
     this.messages.set("eye_of_ender", "You are not allowed to use eye of ender while in creative!");
     this.messages.set("snowball", "You are not allowed to use snowballs while in creative!");
     this.messages.set("monster_egg", "You can't spawn monsters in creative!");
     this.messages.set("ignite", "You can't ignite while in creative!");
     this.messages.set("exp_bottle", "You can't use xp bottles while in creative!");
     this.messages.set("chicken_egg", "You can't use chicken eggs while in creative!");
     this.messages.set("potion", "You can't use potions while in creative!");
     this.messages.set("jukebox", "You can't use jukeboxes while in creative!");
     this.messages.set("beacon", "You can't manipulate beacons while in creative!");
     this.messages.set("item_frame", "You can't put items to itemframes while in creative!");
     this.messages.set("armor_stand", "You can't edit armor stands in creative!");
     this.messages.set("saddle", "You can't put saddle on pigs in creative mode!");
     this.messages.set("command", "You are not allowed to execute command %cmd% while in creative!");
     this.messages.set("disabled-item", "You can't use this item while in creative!");
     this.messages.set("disabled-block", "You can't place this block while in creative!");
     this.messages.set("plant", "You can't plant while in creative!");
     this.messages.set("destroy-farmland", "You can't destroy farmland while in creative!");
     this.messages.set("breed", "You can't breed animals in creative!");
     this.messages.set("fish", "You are not allowed to fish while in creative!");
     this.messages.set("drop", "You can't drop items while in creative!");
     this.messages.set("mob-create", "You can't create mobs while in creative!");
     this.messages.set("shooting", "You can't shoot from bow while in creative!");
     this.messages.saveConfig();
   }
   
   private void checkConfig() {
     if (this.config.getList("disabled-commands") == null) {
       this.config.set("disabled-commands", Arrays.asList(new String[] { "/command1", "/command2" }), new String[] { "Put here all the commands you want to ", "disable in creative! If you want to", "enable these commands for specific players", "give them permission cc.cmd./command_here", "(e.g. cc.cmd./command1)" });
     }
     if (this.config.get("disabled-gamemodes") == null) {
       this.config.set("disabled-gamemodes", Arrays.asList(new String[] { "ADVENTURE", "SPECTATOR" }), new String[] { "Here you can define which gamemodes you", " want to disable!", "You can also create node disabled-gamemodes-worldname", "and specify disabled gamemodes for each world." });
     }
     if (this.config.getList("disabled-items") == null) {
       this.config.set("disabled-items", Arrays.asList(new String[] { "TNT", "BEDROCK" }), new String[] { "Here you can put blocks or items which you", " want to disable in creative.", "List of blocks you can disable can be", "found on this website", "https:" });
     }
     if (this.config.getList("disabled-worlds") == null) {
       this.config.set("disabled-worlds", Arrays.asList(new String[] { "world1", "world2" }), new String[] { "Here you can define worlds in which should", "be the functions of this plugin disabled!" });
     }
     if (this.config.get("disable-creative-spawners") == null) {
       this.config.set("disable-creative-spawners", Boolean.valueOf(true), new String[] { "Set to true if you want to prevent", "creative placed spawners from spawning mobs." });
     }
     if (this.config.getList("remove-permissions") == null) {
       this.config.set("remove-permissions", Arrays.asList(new String[] { "permissionnode1", "permission.node.2" }), new String[] { "Here you can define which permissions will be", "taken when switched to creative mode. e.g. essentials.tpa" });
     }
     if (this.config.getList("add-permissions") == null) {
       this.config.set("add-permissions", Arrays.asList(new String[] { "permissionnode1", "permission.node.2" }), new String[] { "Here you can define which permissions will be", "added when switched to creative mode. e.g. essentials.tpa" });
     }
     if (this.config.get("db-type") == null) {
       this.config.set("db-type", "sqlite", new String[] { "Here you can set if you want to use", "sqlite DB or mysql DB" });
     }
     if (this.config.get("db-host") == null) {
       this.config.set("db-host", "localhost", new String[] { "Set MySQL address here", "(only when db type set to mysql)" });
     }
     if (this.config.get("db-port") == null) {
       this.config.set("db-port", "3306", new String[] { "Set MySQL server port here", "(only when db type set to mysql)" });
     }
     if (this.config.get("db-user") == null) {
       this.config.set("db-user", "user", new String[] { "Set the user login for mysql database", "(only when db type set to mysql" });
     }
     if (this.config.get("db-password") == null) {
       this.config.set("db-password", "password", new String[] { "Set user password", "(only when db type set to mysql)" });
     }
     if (this.config.get("db-database") == null) {
       this.config.set("db-database", "database", new String[] { "Set MySQL database name", "(only when db type set to mysql)" });
     }
     if (this.config.get("db-prefix") == null) {
       this.config.set("db-prefix", "cc_", new String[] { "Set tables prefix here", "(only when db type set to mysql)" });
     }
     if (this.config.get("message-prefix") == null) {
       this.config.set("message-prefix", "&4[CC]&c", "Set message prefix here (supports colors)");
     }
     if (this.config.get("inventory-switching") == null) {
       this.config.set("inventory-switching", Boolean.valueOf(true), new String[] { "Set to true if you want to enable inventory switching", "Set to false to disable it" });
     }
     if (this.config.get("check-for-updates") == null) {
       this.config.set("check-for-updates", Boolean.valueOf(true), new String[] { "Set to true to enable automatic update checking", "Set to false to disable it" });
     }
     if (this.config.get("logging-interval") == null) {
       this.config.set("logging-interval", Integer.valueOf(10), new String[] { "Here you can define after how many creative", "placed blocks should they be written to database.", "If this limit is not reached they", "will be logged after 5 minutes." });
     }
     if (this.config.get("message-cooldown") == null) {
       this.config.set("message-cooldown", Long.valueOf(2L), new String[] { "Here you can define time in seconds for", "which will be all messages in cooldown", "before sending them again to the player." });
     }
     if (this.config.get("excluded-blocks") == null) {
       this.config.set("excluded-blocks", Arrays.asList(new String[] { "DIRT", "SAND" }), new String[] { "Place here blocks you want to be", "excluded from being tracked when", "placed in creative." });
     }
     if (this.config.get("block-memory-limit") == null) {
       this.config.set("block-memory-limit", Integer.valueOf(10000), new String[] { "Set how many blocks can be stored", "in memory and the others will", "be stored in database.", "set to 0 to disable this memory", "set to -1 to use only memory." });
     }
     if (this.config.get("vehicle-memory-limit") == null) {
       this.config.set("vehicle-memory-limit", Integer.valueOf(10000), new String[] { "Set how many vehicles can be stored", "in memory and the others will", "be stored in database.", "set to 0 to disable this memory", "set to -1 to use only memory." });
     }
     if (this.config.get("hanging-memory-limit") == null) {
       this.config.set("hanging-memory-limit", Integer.valueOf(10000), new String[] { "Set how many hangings can be stored", "in memory and the others will", "be stored in database.", "set to 0 to disable this memory", "set to -1 to use only memory." });
     }
     if (this.config.get("enabled-features") == null)
       this.config.set("enabled-features", Arrays.asList(new String[] { "BlockBreak", "BlockFall", "BlockPlace", "BlockExplode", "BedrockBreak", "HangingBreak", "HangingPlace", "VehicleCreate", "VehicleDestroy", "VehicleMove", "PlayerArmorStandManipulate", "PlayerCommandRestrict", "PlayerBannedItemFromInventory", "PlayerDamageEntity", "PlayerDeathNoDrop", "PlayerDropItem", "PlayerGamemodeChange", "PlayerJoin", "PlayerOpenRestrictedInventory", "PlayerPickupItem", "PlayerQuit", "SpawnerSpawnEntity", "CreatureSpawn", "PistonExtend", "PistonRetract" }), new String[] { "Remove or comment out specific", "features to disable them." });
     if (this.config.get("track-worldedit") != null) {
       this.config.removeKey("track-worldedit");
     }
     this.config.saveConfig();
   }
   
   private void checkMessages() {
     if (this.messages.get("container-chest") == null) {
       this.messages.set("container-chest", "You are not allowed to open chest in creative!");
     }
     if (this.messages.get("container-chestdouble") == null) {
       this.messages.set("container-chestdouble", "You are not allowed to open chest in creative!");
     }
     if (this.messages.get("container-furnace") == null) {
       this.messages.set("container-furnace", "You are not allowed to open furnace in creative!");
     }
     if (this.messages.get("container-crafting") == null) {
       this.messages.set("container-crafting", "You are not allowed to open crafting table in creative!");
     }
     if (this.messages.get("container-enderchest") == null) {
       this.messages.set("container-enderchest", "You are not allowed to open enderchest in creative!");
     }
     if (this.messages.get("container-minecart") == null) {
       this.messages.set("container-minecart", "You are not allowed to open minecart in creative!");
     }
     if (this.messages.get("container-dispenser") == null) {
       this.messages.set("container-dispenser", "You are not allowed to open dispenser in creative!");
     }
     if (this.messages.get("container-dropper") == null) {
       this.messages.set("container-dropper", "You are not allowed to open dropper in creative!");
     }
     if (this.messages.get("container-hopper") == null) {
       this.messages.set("container-hopper", "You are not allowed to open hopper in creative!");
     }
     if (this.messages.get("container-horse") == null) {
       this.messages.set("container-horse", "You are not allowed to open horse inventory in creative!");
     }
     if (this.messages.get("container-donkey") == null) {
       this.messages.set("container-donkey", "You are not allowed to open donkey inventory in creative!");
     }
     if (this.messages.get("container-enchant") == null) {
       this.messages.set("container-enchant", "You are not allowed to open enchantment table in creative!");
     }
     if (this.messages.get("container-repair") == null) {
       this.messages.set("container-repair", "You are not allowed to open repair in creative!");
     }
     if (this.messages.get("container-villager") == null) {
       this.messages.set("container-villager", "You are not allowed to trade with villager in creative!");
     }
     if (this.messages.get("container-brewing") == null) {
       this.messages.set("container-brewing", "You are not allowed to open brewing stand in creative!");
     }
     if (this.messages.get("damage-creeper") == null) {
       this.messages.set("damage-creeper", "You are not allowed to damage creepers while in creative!");
     }
     if (this.messages.get("damage-skeleton") == null) {
       this.messages.set("damage-skeleton", "You are not allowed to damage skeletons while in creative!");
     }
     if (this.messages.get("damage-spider") == null) {
       this.messages.set("damage-spider", "You are not allowed to damage spiders while in creative!");
     }
     if (this.messages.get("damage-zombie") == null) {
       this.messages.set("damage-zombie", "You are not allowed to damage zombies while in creative!");
     }
     if (this.messages.get("damage-slime") == null) {
       this.messages.set("damage-slime", "You are not allowed to damage slimes while in creative!");
     }
     if (this.messages.get("damage-ghast") == null) {
       this.messages.set("damage-ghast", "You are not allowed to damage ghasts while in creative!");
     }
     if (this.messages.get("damage-zombie-pigman") == null) {
       this.messages.set("damage-zombie-pigman", "You are not allowed to damage zombie pigmans while in creative!");
     }
     if (this.messages.get("damage-enderman") == null) {
       this.messages.set("damage-enderman", "You are not allowed to damage endermans while in creative!");
     }
     if (this.messages.get("damage-cave-spider") == null) {
       this.messages.set("damage-cave-spider", "You are not allowed to damage cave spiderss while in creative!");
     }
     if (this.messages.get("damage-silverfish") == null) {
       this.messages.set("damage-silverfish", "You are not allowed to damage silverfish while in creative!");
     }
     if (this.messages.get("damage-blaze") == null) {
       this.messages.set("damage-blaze", "You are not allowed to damage blazes while in creative!");
     }
     if (this.messages.get("damage-magma-cube") == null) {
       this.messages.set("damage-magma-cube", "You are not allowed to damage magma cubes while in creative!");
     }
     if (this.messages.get("damage-bat") == null) {
       this.messages.set("damage-bat", "You are not allowed to damage bats while in creative!");
     }
     if (this.messages.get("damage-witch") == null) {
       this.messages.set("damage-witch", "You are not allowed to damage witches while in creative!");
     }
     if (this.messages.get("damage-endermite") == null) {
       this.messages.set("damage-endermite", "You are not allowed to damage endermites while in creative!");
     }
     if (this.messages.get("damage-guardian") == null) {
       this.messages.set("damage-guardian", "You are not allowed to damage guardians while in creative!");
     }
     if (this.messages.get("damage-pig") == null) {
       this.messages.set("damage-pig", "You are not allowed to damage pigs while in creative!");
     }
     if (this.messages.get("damage-sheep") == null) {
       this.messages.set("damage-sheep", "You are not allowed to damage sheep while in creative!");
     }
     if (this.messages.get("damage-cow") == null) {
       this.messages.set("damage-cow", "You are not allowed to damage cows while in creative!");
     }
     if (this.messages.get("damage-chicken") == null) {
       this.messages.set("damage-chicken", "You are not allowed to damage chickens while in creative!");
     }
     if (this.messages.get("damage-squid") == null) {
       this.messages.set("damage-squid", "You are not allowed to damage squids while in creative!");
     }
     if (this.messages.get("damage-wolf") == null) {
       this.messages.set("damage-wolf", "You are not allowed to damage wolfs while in creative!");
     }
     if (this.messages.get("damage-mooshroom") == null) {
       this.messages.set("damage-mooshroom", "You are not allowed to damage mooshrooms while in creative!");
     }
     if (this.messages.get("damage-ocelot") == null) {
       this.messages.set("damage-ocelot", "You are not allowed to damage ocelots while in creative!");
     }
     if (this.messages.get("damage-donkey") == null) {
       this.messages.set("damage-donkey", "You are not allowed to damage donkeys while in creative!");
     }
     if (this.messages.get("damage-horse") == null) {
       this.messages.set("damage-horse", "You are not allowed to damage horses while in creative!");
     }
     if (this.messages.get("damage-mule") == null) {
       this.messages.set("damage-mule", "You are not allowed to damage mules while in creative!");
     }
     if (this.messages.get("damage-rabbit") == null) {
       this.messages.set("damage-rabbit", "You are not allowed to damage rabbits while in creative!");
     }
     if (this.messages.get("damage-villager") == null) {
       this.messages.set("damage-villager", "You are not allowed to damage villagers while in creative!");
     }
     if (this.messages.get("damage-giant") == null) {
       this.messages.set("damage-giant", "You are not allowed to damage giants while in creative!");
     }
     if (this.messages.get("damage-iron-golem") == null) {
       this.messages.set("damage-iron-golem", "You are not allowed to damage iron golems while in creative!");
     }
     if (this.messages.get("damage-ender-dragon") == null) {
       this.messages.set("damage-ender-dragon", "You are not allowed to damage ender dragons while in creative!");
     }
     if (this.messages.get("damage-snow-golem") == null) {
       this.messages.set("damage-snow-golem", "You are not allowed to damage snow golems while in creative!");
     }
     if (this.messages.get("damage-wither") == null) {
       this.messages.set("damage-wither", "You are not allowed to damage withers while in creative!");
     }
     if (this.messages.get("damage-ender-crystal") == null) {
       this.messages.set("damage-ender-crystal", "You are not allowed to destroy ender crystals while in creative!");
     }
     if (this.messages.get("damage-player") == null) {
       this.messages.set("damage-player", "You are not allowed to damage players while in creative!");
     }
     if (this.messages.get("damage-shulker") == null) {
       this.messages.set("damage-shulker", "You are not allowed to damage shulkers while in creative!");
     }
     if (this.messages.get("disabled-gamemode") == null) {
       this.messages.set("disabled-gamemode", "You can't use this gamemode because it is disabled!");
     }
     if (this.messages.get("block-break-bedrock") == null) {
       this.messages.set("block-break-bedrock", "You are not allowed to break bedrock while in creative!");
     }
     if (this.messages.get("hanging-break") == null) {
       this.messages.set("hanging-break", "This item frame was placed in creative, meaning you can't get the drops from it!");
     }
     if (this.messages.get("block-break") == null) {
       this.messages.set("block-break", "This block was placed in creative, meaning you can't get the drops from it!");
     }
     if (this.messages.get("vehicle-break") == null) {
       this.messages.set("vehicle-break", "This minecart was place in creative, meaning you can't get the drops from it!");
     }
     if (this.messages.get("drop") == null) {
       this.messages.set("drop", "You can't drop items in creative mode!");
     }
     if (this.messages.get("pickup") == null) {
       this.messages.set("pickup", "You can't pick up items while in creative");
     }
     if (this.messages.get("ender_portal") == null) {
       this.messages.set("ender_portal", "You can't activate ender portal while in creative!");
     }
     if (this.messages.get("eye_of_ender") == null) {
       this.messages.set("eye_of_ender", "You are not allowed to use eye of ender while in creative!");
     }
     if (this.messages.get("snowball") == null) {
       this.messages.set("snowball", "You are not allowed to use snowballs while in creative!");
     }
     if (this.messages.get("monster_egg") == null) {
       this.messages.set("monster_egg", "You can't spawn monsters in creative!");
     }
     if (this.messages.get("ignite") == null) {
       this.messages.set("ignite", "You can't ignite while in creative!");
     }
     if (this.messages.get("exp_bottle") == null) {
       this.messages.set("exp_bottle", "You can't use xp bottles while in creative!");
     }
     if (this.messages.get("chicken_egg") == null) {
       this.messages.set("chicken_egg", "You can't use chicken eggs while in creative!");
     }
     if (this.messages.get("potion") == null) {
       this.messages.set("potion", "You can't use potions while in creative!");
     }
     if (this.messages.get("jukebox") == null) {
       this.messages.set("jukebox", "You can't use jukeboxes while in creative!");
     }
     if (this.messages.get("beacon") == null) {
       this.messages.set("beacon", "You can't manipulate beacons while in creative!");
     }
     if (this.messages.get("item_frame") == null) {
       this.messages.set("item_frame", "You can't put items to itemframes while in creative!");
     }
     if (this.messages.get("armor_stand") == null) {
       this.messages.set("armor_stand", "You can't edit armor stands in creative!");
     }
     if (this.messages.get("saddle") == null) {
       this.messages.set("saddle", "You can't put saddle on pigs in creative mode!");
     }
     if (this.messages.get("command") == null) {
       this.messages.set("command", "You are not allowed to execute command %cmd% while in creative!");
     }
     if (this.messages.get("disabled_item") == null) {
       this.messages.set("disabled_item", "You can't use this item while in creative!");
     }
     if (this.messages.get("plant") == null) {
       this.messages.set("plant", "You can't plant while in creative!");
     }
     if (this.messages.get("destroy_farmland") == null) {
       this.messages.set("destroy_farmland", "You can't destroy farmland while in creative!");
     }
     if (this.messages.get("breed") == null) {
       this.messages.set("breed", "You can't breed animals in creative!");
     }
     if (this.messages.get("fish") == null) {
       this.messages.set("fish", "You are not allowed to fish while in creative!");
     }
     if (this.messages.get("mob-create") == null) {
       this.messages.set("mob-create", "You can't create mobs while in creative!");
     }
     if (this.messages.get("shooting") == null) {
       this.messages.set("shooting", "You can't shoot from bow while in creative!");
     }
     this.messages.saveConfig();
   }
   
   private void oldMessages() {
     if (this.messages.get("blockbreak") != null) {
       this.messages.set("block-break", this.messages.get("blockbreak"));
       this.messages.removeKey("blockbreak");
     }
     if (this.messages.get("minecart") != null) {
       this.messages.set("vehicle-break", this.messages.get("minecart"));
       this.messages.removeKey("minecart");
     }
     if (this.messages.get("itemframedestroy") != null) {
       this.messages.set("hanging-break", this.messages.get("itemframedestroy"));
       this.messages.removeKey("itemframedestroy");
     }
     if (this.messages.get("bedrock") != null) {
       this.messages.set("block-break-bedrock", this.messages.get("bedrock"));
       this.messages.removeKey("bedrock");
     }
     if (this.messages.get("monsteregg") != null) {
       this.messages.set("monster_egg", this.messages.get("monsteregg"));
       this.messages.removeKey("monsteregg");
     }
     if (this.messages.get("expbottle") != null) {
       this.messages.set("exp_bottle", this.messages.get("expbottle"));
       this.messages.removeKey("expbottle");
     }
     if (this.messages.get("chickenegg") != null) {
       this.messages.set("chicken_egg", this.messages.get("chickenegg"));
       this.messages.removeKey("chickenegg");
     }
     if (this.messages.get("itemframe") != null) {
       this.messages.set("item_frame", this.messages.get("itemframe"));
       this.messages.removeKey("itemframe");
     }
     if (this.messages.get("armorstand") != null) {
       this.messages.set("armor_stand", this.messages.get("armorstand"));
       this.messages.removeKey("armorstand");
     }
     if (this.messages.get("disableditem") != null) {
       this.messages.set("disabled_item", this.messages.get("disableditem"));
       this.messages.removeKey("disableditem");
     }
     if (this.messages.get("destroyfarmland") != null) {
       this.messages.set("destroy_farmland", this.messages.get("destroyfarmland"));
       this.messages.removeKey("destroyfarmland");
     }
     if (this.messages.get("fillenderportal") != null) {
       this.messages.set("ender_portal", this.messages.get("fillenderportal"));
       this.messages.removeKey("fillenderportal");
     }
     if (this.messages.get("eyeofender") != null) {
       this.messages.set("eye_of_ender", this.messages.get("eyeofender"));
       this.messages.removeKey("eyeofender");
     }
     if (this.messages.get("enderpearl") != null) {
       this.messages.set("ender_pearl", this.messages.get("enderpearl"));
       this.messages.removeKey("enderpearl");
     }
     if (this.messages.get("cantplace") != null) {
       this.messages.set("disabled-block", this.messages.get("cantplace"));
       this.messages.removeKey("cantplace");
     }
     this.messages.saveConfig();
   }
 }


