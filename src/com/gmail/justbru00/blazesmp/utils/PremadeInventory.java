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

	public static Inventory basMain(Player sender) {
		Inventory i = Bukkit.createInventory(null, 27, Messager.color(Main.PREFIX + "&aAdmin GUI"));
		
		// Player Head
		ItemStack senderSkull = new ItemStack(Material.SKULL_ITEM, (short) 3); // TODO Fix this gives 3 skeleton skulls
		SkullMeta senderSkullMeta = (SkullMeta) senderSkull.getItemMeta();
		senderSkullMeta.setOwner(sender.getName());
		senderSkullMeta.setDisplayName(Messager.color("&aWelcome " + sender.getDisplayName()));
		ArrayList<String> skullLore = new ArrayList<String>();
		skullLore.add(Messager.color("&fHope you are having a good day."));
		senderSkullMeta.setLore(skullLore);
		// End of Player Head		
		
		// Toggle Dyes
		ItemStack enabled = new ItemStack(Material.INK_SACK, (short) 8); //TODO Fix This
		ItemMeta eMeta = enabled.getItemMeta();
		eMeta.setDisplayName(Messager.color("&aEnabled"));
		
		ItemStack disabled = new ItemStack(Material.INK_SACK, (short) 10); // TODO Fix This
		ItemMeta dMeta = enabled.getItemMeta();
		dMeta.setDisplayName(Messager.color("&8Disabled"));
		
		// Start Adding Stuff
		i.setItem(4, senderSkull);
		i.setItem(10, ItemMaker.createItemStack("&eTeam Change Requests", "PAPER", "&7Shows all pending team change requests.", "&7Deny any that have no reason."));
		i.setItem(13, ItemMaker.createItemStack("&bStaff Mode", "GOLDEN_APPLE", "&7Clicking this toggle staff mode.", ""));
		i.setItem(14, disabled);
		i.setItem(16, ItemMaker.createItemStack("&bNo Cheat Notifications", "BARRIER", "&7Toggles No Cheat Notifications", ""));
		i.setItem(17, enabled);
		i.setItem(19, ItemMaker.createItemStack("&c&lWAR", "DIAMOND_AXE", "&7Change war options", ""));
		i.setItem(25, ItemMaker.createItemStack("&bOres found notifications", "BARRIER", "&7Toggles ores found notifications.", "&4WORK IN PROGRESS. (COMING SOON.)"));
		i.setItem(26, disabled);
		
		// Stop Adding Stuff
		
		return i;
	}
	
}
