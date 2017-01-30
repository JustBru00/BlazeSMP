package com.gmail.justbru00.blazesmp.homes;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import com.gmail.justbru00.blazesmp.main.Main;
import com.gmail.justbru00.blazesmp.utils.PluginFile;

public class Home {

	private String worldName;
	private double x;
	private double y;
	private double z;
	private float pitch;
	private float yaw;
	private ItemStack icon;
	private String ownerUUID;
	private String homeName;
	
	/**
	 * Use this constuctor for a new home. Don't forget to save the name of this home in the players homes list.
	 * @param _uuid
	 * @param _homeName
	 * @param _worldName
	 * @param _x
	 * @param _y
	 * @param _z
	 * @param _pitch
	 * @param _yaw
	 * @param _icon
	 */
	public Home(String _uuid, String _homeName, Location loc, Material _icon) {
		worldName = loc.getWorld().getName();
		x = loc.getX();
		y = loc.getY();
		z = loc.getZ();
		pitch = loc.getPitch();
		yaw = loc.getYaw();
		ownerUUID = _uuid;
		homeName = _homeName;
		
		icon = new ItemStack(_icon);	
		writeToDisk();
	}
	
	/**
	 * Use this constuctor for getting a saved home.
	 * @param uuid The UUID of the player as a string
	 * @param _homeName The name of the home
	 */
	public Home(String uuid, String _homeName) {		
		worldName = Main.getInstance().homesStorage.getString("data." + uuid + ".homes." + _homeName + ".world");
		x = Main.getInstance().homesStorage.getDouble("data." + uuid + ".homes." + _homeName + ".x");
		y = Main.getInstance().homesStorage.getDouble("data." + uuid + ".homes." + _homeName + ".y");
		z = Main.getInstance().homesStorage.getDouble("data." + uuid + ".homes." + _homeName + ".z");
		pitch = (float) Main.getInstance().homesStorage.getDouble("data." + uuid + ".homes." + _homeName + ".pitch");
		icon = new ItemStack(Material.getMaterial(Main.getInstance().homesStorage.getString("data." + uuid + ".homes." + _homeName + ".icon")));
		ownerUUID = uuid;
		homeName = _homeName;
	}
	
	public void writeToDisk() {
		PluginFile pf = Main.getInstance().homesStorage;
		
		pf.set("data." + ownerUUID + ".homes." + homeName + ".world", worldName);
		pf.set("data." + ownerUUID + ".homes." + homeName + ".x", x);
		pf.set("data." + ownerUUID + ".homes." + homeName + ".y", y);
		pf.set("data." + ownerUUID + ".homes." + homeName + ".z", z);
		pf.set("data." + ownerUUID + ".homes." + homeName + ".pitch", pitch);
		pf.set("data." + ownerUUID + ".homes." + homeName + ".yaw", yaw);
		pf.set("data." + ownerUUID + ".homes." + homeName + ".icon", icon.getType().toString());
		pf.save();
	}
	
	
	public Location getLocation() {
		return new Location(Bukkit.getWorld(worldName), x, y, z, yaw, pitch);
	}
}
