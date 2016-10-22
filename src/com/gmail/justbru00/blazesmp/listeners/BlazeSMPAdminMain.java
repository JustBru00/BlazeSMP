package com.gmail.justbru00.blazesmp.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.gmail.justbru00.blazesmp.main.Main;
import com.gmail.justbru00.blazesmp.utils.Debug;
import com.gmail.justbru00.blazesmp.utils.Messager;
import com.gmail.justbru00.blazesmp.utils.PremadeInventory;

public class BlazeSMPAdminMain implements Listener {

	@EventHandler
	public void onClick(InventoryClickEvent e) {
		Inventory i = e.getClickedInventory();	
		Player player = (Player) e.getWhoClicked();
		
		if (i == null) {
			Debug.send("Player clicked is not in inventory.");
			return;
		}
		
		if (i.getName().equals(Messager.color(Main.PREFIX + "&aAdmin GUI"))) {
			
			e.setCancelled(true);
			
			ItemStack currentItem = e.getCurrentItem();
			
			if (currentItem == null ||currentItem.getType() == Material.AIR || currentItem.getType() == Material.SKULL_ITEM || currentItem.getType() == Material.INK_SACK) {
				return;
			}
			
			if (currentItem.getType() == Material.GOLDEN_APPLE) { // Staff Mode
				Messager.msgPlayer("&cSorry that feature is not ready yet.", player);
				return;
			} else if (currentItem.getType() == Material.PAPER) { // Team requests
				Messager.msgPlayer("&cSorry that feature is not ready yet.", player);
				return;
			} else if (currentItem.getType() == Material.DIAMOND_AXE) { // War options
				Messager.msgPlayer("&cSorry that feature is not ready yet.", player);
				return;
			} else if (currentItem.getType() == Material.BARRIER) { // Notifcations
				if (currentItem.getItemMeta().getDisplayName().equals(Messager.color("&bNo Cheat Notifications"))) { // NoCheat Notifcations
					Debug.send("NoCheat Item");
					if (i.getItem(17).equals(PremadeInventory.enabled)) {
						Main.getInstance().getConfig().set("players.data." + player.getUniqueId().toString() + ".admin.notifications.nocheat", false);
						Main.getInstance().saveConfig();
						i.setItem(17, PremadeInventory.disabled);						
						// TODO Enable/disable notifications
						Debug.send("No cheat off");
						return;
					} else {
						Main.getInstance().getConfig().set("players.data." + player.getUniqueId().toString() + ".admin.notifications.nocheat", true);
						Main.getInstance().saveConfig();
						i.setItem(17, PremadeInventory.enabled);
						Debug.send("NoCheat is now on");
						return;
					}
				} else if (true /** Stuff here **/){
					
				}
			}
			
		}
	}
}
