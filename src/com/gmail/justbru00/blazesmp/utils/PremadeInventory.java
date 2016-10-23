package com.gmail.justbru00.blazesmp.utils;

import java.util.ArrayList;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import com.gmail.justbru00.blazesmp.main.Main;

public class PremadeInventory {
	
	public static ItemStack enabled = new ItemStack(Material.INK_SACK, 1, (short) 10); 
	public static ItemStack disabled = new ItemStack(Material.INK_SACK, 1, (short) 8); 

	public static Inventory basMain(Player sender) {
		readyItems();
		
		Inventory i = Bukkit.createInventory(null, 27, Messager.color(Main.PREFIX + "&aAdmin GUI"));
		
		// Player Head
		ItemStack senderSkull = new ItemStack(Material.SKULL_ITEM, 1, (short) 3); 
		SkullMeta senderSkullMeta = (SkullMeta) senderSkull.getItemMeta();
		senderSkullMeta.setOwner(sender.getName());
		senderSkullMeta.setDisplayName(Messager.color("&aWelcome " + sender.getDisplayName()));
		ArrayList<String> skullLore = new ArrayList<String>();
		skullLore.add(Messager.color("&fHope you are having a good day."));
		senderSkullMeta.setLore(skullLore);
		senderSkull.setItemMeta(senderSkullMeta);
		// End of Player Head			
		
		// Start Adding Stuff
		i.setItem(4, senderSkull);
		i.setItem(10, ItemMaker.createItemStack("&eTeam Change Requests", "PAPER", "&7Shows all pending team change requests.", "&7Deny any that have no reason."));
		i.setItem(13, ItemMaker.createItemStack("&bStaff Mode", "GOLDEN_APPLE", "&7Clicking this will toggle staff mode.", ""));
		
		if (!Main.getInstance().getConfig().getBoolean("players.data." + sender.getUniqueId().toString() + ".admin.notifications.staffmode"))	i.setItem(14, disabled);
		if (Main.getInstance().getConfig().getBoolean("players.data." + sender.getUniqueId().toString() + ".admin.notifications.staffmode"))	i.setItem(14, enabled);
		
		i.setItem(16, ItemMaker.createItemStack("&bNo Cheat Notifications", "BARRIER", "&7Toggles No Cheat Notifications", ""));
		
		if (Main.getInstance().getConfig().getBoolean("players.data." + sender.getUniqueId().toString() + ".admin.notifications.nocheat")) i.setItem(17, enabled);
		if (!Main.getInstance().getConfig().getBoolean("players.data." + sender.getUniqueId().toString() + ".admin.notifications.nocheat")) i.setItem(17, disabled);
		
		i.setItem(19, ItemMaker.createItemStack("&c&lWAR", "DIAMOND_AXE", "&7Change war options", ""));
		
		i.setItem(25, ItemMaker.createItemStack("&bOres found notifications", "BARRIER", "&7Toggles ores found notifications.", "&4WORK IN PROGRESS. (COMING SOON.)"));
		
		if (!Main.getInstance().getConfig().getBoolean("players.data." + sender.getUniqueId().toString() + ".admin.notifications.ores"))	i.setItem(26, disabled);
		if (Main.getInstance().getConfig().getBoolean("players.data." + sender.getUniqueId().toString() + ".admin.notifications.ores"))	i.setItem(26, enabled);
		
		// Stop Adding Stuff
		
		return i;
	}
	
	public static void readyItems() {
		// Toggle Dyes				
				ItemMeta eMeta = enabled.getItemMeta();
				eMeta.setDisplayName(Messager.color("&aEnabled"));
				enabled.setItemMeta(eMeta);				
				
				ItemMeta dMeta = enabled.getItemMeta();
				dMeta.setDisplayName(Messager.color("&8Disabled"));
				disabled.setItemMeta(dMeta);
	}
	
}
