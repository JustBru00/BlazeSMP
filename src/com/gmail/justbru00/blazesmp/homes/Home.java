package com.gmail.justbru00.blazesmp.homes;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;

import com.gmail.justbru00.blazesmp.main.Main;

public class Home {

	private String worldName;
	private double x;
	private double y;
	private double z;
	private float pitch;
	private float yaw;
	private ItemStack icon;
	
	
	public Home(String uuid, String homeName) {
		worldName = Main.getInstance().homesStorage.getString("data." + uuid + ".homes." + homeName + ".world");
		x = Main.getInstance().homesStorage.getDouble("data." + uuid + ".homes." + homeName + ".x");
		y = Main.getInstance().homesStorage.getDouble("data." + uuid + ".homes." + homeName + ".y");
		z = Main.getInstance().homesStorage.getDouble("data." + uuid + ".homes." + homeName + ".z");
		pitch = (float) Main.getInstance().homesStorage.getDouble("data." + uuid + ".homes." + homeName + ".pitch");
	}
	
	
	public Location getLocation() {
		return new Location(Bukkit.getWorld(worldName), x, y, z, yaw, pitch);
	}
}
