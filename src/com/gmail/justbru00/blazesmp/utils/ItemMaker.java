package com.gmail.justbru00.blazesmp.utils;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.gmail.justbru00.blazesmp.main.Main;

public class ItemMaker {

	public static ItemStack createItemStack(String nocolordisplayname, String material, String lore, String lore2) {
		Material m;
		
	try {	
		m = Material.getMaterial(material);
	} catch (Exception e) {
		Main.clogger.sendMessage(Main.PREFIX + Messager.color("&cAn error happened when getting the material: " + material + ". Changed it to paper instead."));
		m = Material.PAPER;
	}
	
		ItemStack is = new ItemStack(m);		
		ItemMeta im = is.getItemMeta();
		
		im.setDisplayName(Messager.color(nocolordisplayname));
		
		ArrayList<String> lorelist = new ArrayList<String>();
		lorelist.add(Messager.color(lore));
		lorelist.add(Messager.color(lore2));
		
		im.setLore(lorelist);
		
		is.setItemMeta(im);
		
		return is;
	}
	
	public static ItemStack createItemStack(String nocolordisplayname, String material) {
		Material m;
		
	try {	
		m = Material.getMaterial(material);
	} catch (Exception e) {
		Main.clogger.sendMessage(Main.PREFIX + Messager.color("&cAn error happened when getting the material: " + material + ". Changed it to paper instead."));
		m = Material.PAPER;
	}
	
		ItemStack is = new ItemStack(m);		
		ItemMeta im = is.getItemMeta();
		
		im.setDisplayName(Messager.color(nocolordisplayname));

		
		is.setItemMeta(im);
		
		return is;
	}
}
