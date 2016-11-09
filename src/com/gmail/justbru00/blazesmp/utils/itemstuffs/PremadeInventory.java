package com.gmail.justbru00.blazesmp.utils.itemstuffs;

import java.util.ArrayList;
import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import com.gmail.justbru00.blazesmp.main.Main;
import com.gmail.justbru00.blazesmp.utils.Debug;
import com.gmail.justbru00.blazesmp.utils.Messager;
import com.gmail.justbru00.blazesmp.utils.team.TeamChangeRequest;
import com.gmail.justbru00.blazesmp.utils.team.TeamManager;

public class PremadeInventory {

	public static ItemStack enabled = new ItemStack(Material.INK_SACK, 1, (short) 10);
	public static ItemStack disabled = new ItemStack(Material.INK_SACK, 1, (short) 8);
	public static final String TEAM_REQUESTS_NAME = Messager.color("&eTeam Change Requests");
	public static final String TEAM_REQUESTS_CONFIRM_PREFIX = Messager.color("&eTeam Change Request #");
	public static ItemStack accept = new ItemStack(Material.EMERALD_BLOCK);
	public static ItemStack deny = new ItemStack(Material.REDSTONE_BLOCK);
	public static ItemStack backArrow = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);

	public static Inventory confirmTeamRequest(ItemStack clickedItem) {
		
		int ID = -1;

		try {
		String temp = clickedItem.getItemMeta().getDisplayName();
		ID = Integer.parseInt(temp.substring(temp.length() - 1));
		} catch (Exception e) {
			Inventory error = Bukkit.createInventory(null, 54, Messager.color("&c&lERROR WHILE GETTING ID"));
			return error;
		}

		Inventory i = Bukkit.createInventory(null, 54, TEAM_REQUESTS_CONFIRM_PREFIX + ID);

		i.setItem(9, accept);
		i.setItem(10, accept);
		
		i.setItem(16, deny);
		i.setItem(17, deny);
		
		i.setItem(18, accept);
		i.setItem(19, accept);
		
		i.setItem(22, clickedItem);
		
		i.setItem(25, deny);
		i.setItem(26, deny);
		
		i.setItem(27, accept);
		i.setItem(28, accept);
		
		i.setItem(34, deny);
		i.setItem(35, deny);
		
		i.setItem(36, accept);
		i.setItem(37, accept);
		
		i.setItem(43, deny);
		i.setItem(44, deny);
		
		i.setItem(45, backArrow);

		
		return i;
	}

	public static Inventory teamRequests() {
		Inventory i = Bukkit.createInventory(null, 54, TEAM_REQUESTS_NAME);
		
		i.setItem(45, backArrow);

		int numOfRequests = 1;

		for (TeamChangeRequest tcr : TeamManager.getTeamChangeRequests()) {			
			if (numOfRequests > 53) {
				Debug.send("Too many to fit in one inventory");
				break;
			}

			if (!tcr.isDenied() && !tcr.isAccepted()) {
				i.addItem(tcr.getItem());
			}

			numOfRequests++;
		}

		
		return i;
	}

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
		i.setItem(10, ItemMaker.createItemStack("&eTeam Change Requests", "PAPER",
				"&7Shows all pending team change requests.", "&7Deny any that have no reason."));
		i.setItem(13, ItemMaker.createItemStack("&bStaff Mode", "GOLDEN_APPLE",
				"&7Clicking this will toggle staff mode.", ""));

		if (!Main.getInstance().getConfig()
				.getBoolean("players.data." + sender.getUniqueId().toString() + ".admin.notifications.staffmode"))
			i.setItem(14, disabled);
		if (Main.getInstance().getConfig()
				.getBoolean("players.data." + sender.getUniqueId().toString() + ".admin.notifications.staffmode"))
			i.setItem(14, enabled);

		i.setItem(16, ItemMaker.createItemStack("&bNo Cheat Notifications", "BARRIER",
				"&7Toggles No Cheat Notifications", ""));

		if (Main.getInstance().getConfig()
				.getBoolean("players.data." + sender.getUniqueId().toString() + ".admin.notifications.nocheat"))
			i.setItem(17, enabled);
		if (!Main.getInstance().getConfig()
				.getBoolean("players.data." + sender.getUniqueId().toString() + ".admin.notifications.nocheat"))
			i.setItem(17, disabled);

		i.setItem(19, ItemMaker.createItemStack("&c&lWAR", "DIAMOND_AXE", "&7Change war options", ""));

		i.setItem(25, ItemMaker.createItemStack("&bOres found notifications", "BARRIER",
				"&7Toggles ores found notifications.", "&4WORK IN PROGRESS. (COMING SOON.)"));

		if (!Main.getInstance().getConfig()
				.getBoolean("players.data." + sender.getUniqueId().toString() + ".admin.notifications.ores"))
			i.setItem(26, disabled);
		if (Main.getInstance().getConfig()
				.getBoolean("players.data." + sender.getUniqueId().toString() + ".admin.notifications.ores"))
			i.setItem(26, enabled);

		// Stop Adding Stuff

		return i;
	}

	public static void readyItems() {

		// Enabled Dye
		ItemMeta eMeta = enabled.getItemMeta();
		eMeta.setDisplayName(Messager.color("&aEnabled"));
		enabled.setItemMeta(eMeta);

		// Disabled Dye
		ItemMeta dMeta = enabled.getItemMeta();
		dMeta.setDisplayName(Messager.color("&8Disabled"));
		disabled.setItemMeta(dMeta);

		// Accept Button
		ItemMeta acceptMeta = accept.getItemMeta();
		acceptMeta.setDisplayName(Messager.color("&a&lAccept"));
		acceptMeta.setLore(Arrays.asList(Messager.color("&cWarning!!! This cannot be undone.")));
		accept.setItemMeta(acceptMeta);

		// Deny Button
		ItemMeta denyMeta = deny.getItemMeta();
		denyMeta.setDisplayName(Messager.color("&c&lDeny"));
		denyMeta.setLore(Arrays.asList(Messager.color("&cWarning!!! This cannot be undone.")));
		deny.setItemMeta(denyMeta);

		// Back Arrow
		SkullMeta backMeta = (SkullMeta) backArrow.getItemMeta();
		backMeta.setDisplayName(Messager.color("&cBack"));
		backMeta.setLore(Arrays.asList(Messager.color("&7&oGoes back to the last page.")));
		backMeta.setOwner("MHF_ArrowLeft");
		backArrow.setItemMeta(backMeta);

	}

}
