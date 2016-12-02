 package me.kubqoa.creativecontrol.api;
 
 import java.util.HashMap;
 import java.util.List;
 import me.kubqoa.creativecontrol.Main;
 import me.kubqoa.creativecontrol.helpers.VehicleHelper;
 import me.kubqoa.creativecontrol.tasks.VehiclesFromDB;
 import me.kubqoa.creativecontrol.tasks.VehiclesToDB;
 import me.kubqoa.creativecontrol.tasks.VehiclesUpdateDB;
 import org.bukkit.Location;
 import org.bukkit.entity.Vehicle;
 
 
 public class VehicleAPI
 {
   private final Vehicle vehicle;
   
   public VehicleAPI(Vehicle vehicle)
   {
     this.vehicle = vehicle;
   }
   
   public boolean isCreativeVehicle() {
     Location location = this.vehicle.getLocation();
     if (Main.vehiclesLocation.contains(location))
       return true;
     if (Main.WvehiclesLocation.contains(location))
       return true;
     if (VehicleHelper.isCreativeVehicle(location)) {
       return true;
     }
     return false;
   }
   
   public void addVehicle() {
     Location location = this.vehicle.getLocation();
     if (Main.vehiclesLocation.size() < Main.vehicleCache) {
       Main.vehiclesLocation.add(location);
     }
     Main.WvehiclesLocation.add(location);
     if (Main.WvehiclesLocation.size() >= Main.loggingInterval) {
       new VehiclesUpdateDB().runTaskAsynchronously(Main.thisPlugin);
       new VehiclesToDB().runTaskAsynchronously(Main.thisPlugin);
     }
   }
   
   public void removeVehicle() {
     Location location = this.vehicle.getLocation();
     if (Main.vehiclesLocation.contains(location)) {
       Main.vehiclesLocation.remove(location);
     }
     if (Main.WvehiclesLocation.contains(location)) {
       Main.WvehiclesLocation.remove(location);
     }
     if (VehicleHelper.isCreativeVehicle(location)) {
       Main.RvehiclesLocation.add(location);
       Main.UvehiclesLocation2.remove(Main.UvehiclesLocation1.get(location));
       Main.UvehiclesLocation1.remove(location);
       if (Main.RvehiclesLocation.size() >= 50) {
         new VehiclesUpdateDB().runTaskAsynchronously(Main.thisPlugin);
         new VehiclesFromDB().runTaskAsynchronously(Main.thisPlugin);
       }
     }
   }
   
   public void updateVehicle(Location oldLoc, Location newLoc) {
     if (Main.vehiclesLocation.contains(oldLoc)) {
       Main.vehiclesLocation.add(newLoc);
       Main.vehiclesLocation.remove(oldLoc);
     }
     if (Main.WvehiclesLocation.contains(oldLoc)) {
       Main.WvehiclesLocation.add(newLoc);
       Main.WvehiclesLocation.remove(oldLoc);
     }
     if (Main.UvehiclesLocation1.containsKey(oldLoc)) {
       Location original = (Location)Main.UvehiclesLocation1.get(oldLoc);
       if (Main.UvehiclesLocation2.containsKey(original)) {
         Main.UvehiclesLocation2.remove(original);
         Main.UvehiclesLocation2.put(original, newLoc);
       }
       Main.UvehiclesLocation1.remove(oldLoc);
       Main.UvehiclesLocation1.put(newLoc, original);
     } else {
       Main.UvehiclesLocation1.put(newLoc, oldLoc);
       Main.UvehiclesLocation2.put(oldLoc, newLoc);
     }
     if (Main.UvehiclesLocation1.size() >= 50) {
       new VehiclesUpdateDB().runTaskAsynchronously(Main.thisPlugin);
     }
   }
 }


