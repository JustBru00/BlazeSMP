package com.gmail.justbru00.blazesmp.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import com.gmail.justbru00.blazesmp.utils.Debug;

public class TempInteract implements Listener{


	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		Debug.send("Material = " + e.getClickedBlock().getType().toString());
		Debug.send("MetaData = " + e.getClickedBlock().getState().getData());
	}

}
