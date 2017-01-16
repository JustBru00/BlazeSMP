package com.gmail.justbru00.blazesmp.utils.team;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.gmail.justbru00.blazesmp.enums.Team;
import com.gmail.justbru00.blazesmp.main.Main;
import com.gmail.justbru00.blazesmp.utils.Messager;
import com.gmail.justbru00.blazesmp.utils.PluginFile;
import com.gmail.justbru00.blazesmp.utils.timestuffs.TimeGetter;

public class TeamChangeRequest {

	private String requesterUUID = null;
	private Team teamToChangeTo = Team.NONE;
	private String timeRequestedFormated = null;
	private long timeRequested = -1;
	private String reasonForChange = null;
	
	private boolean denied = false;
	private int id = -1;
	
	private boolean accepted = false;
	private String accepterUUID = null;
	private long acceptedTime = -1;

	/**
	 * Used for creating a {@link TeamChangeRequest} object. This constructor is for requesting.
	 * @param _requesterUUID The UUID of the player who wants to switch to a different team.
	 * @param _teamToChangeTo The {@link Team} the player wants to switch to.
	 * @param reasonForChange The reason the player wants to switch.
	 */
	public TeamChangeRequest(String _requesterUUID, Team _teamToChangeTo, String _reasonForChange, int ID) {
		requesterUUID = _requesterUUID;
		teamToChangeTo = _teamToChangeTo;
		timeRequestedFormated = TimeGetter.getCurrentTimeStamp();
		timeRequested = System.currentTimeMillis();
		reasonForChange = _reasonForChange;
		setId(ID);
	}
	
	/**
	 * Creates a TeamChangeRequest from the config.
	 * @param ID The id you want.
	 */
	public TeamChangeRequest(int ID) {
		PluginFile file = Main.getInstance().teamRequestsFile;
		
		id = ID;
		
		requesterUUID = file.getString(getPathFor("requesterUUID"));
		teamToChangeTo = TeamManager.getTeamFromString(file.getString(getPathFor("teamToChangeTo")));
		timeRequestedFormated = file.getString(getPathFor("timeRequestedFormated"));
		timeRequested = file.getLong(getPathFor("timeRequested"));
		reasonForChange = file.getString(getPathFor("reasonForChange"));
		denied = file.getBoolean(getPathFor("denied"));
		accepted = file.getBoolean(getPathFor("accepted"));
		accepterUUID = file.getString(getPathFor("accepterUUID"));
		acceptedTime = file.getLong(getPathFor("acceptedTime"));
		
	}
	
	public ItemStack getItem() {
		
		ItemStack i = new ItemStack(Material.PAPER);
		ItemMeta im = i.getItemMeta();
		im.setDisplayName(Messager.color("&eTeam Change Request #" + id));
		ArrayList<String> lore = new ArrayList<String>();
		
		lore.add(Messager.color("&eRequester: &7" + Bukkit.getOfflinePlayer(UUID.fromString(requesterUUID)).getName()));
		
		if (teamToChangeTo == Team.ICE) {
			lore.add(Messager.color("&eChange to: &bICE"));
		} else if (teamToChangeTo == Team.NETHER) {
			lore.add(Messager.color("&eChange to: &cNETHER"));
		}
		
		lore.add(Messager.color("&eRequested at: &7" + timeRequestedFormated));
		lore.add(Messager.color("&eReason: &7" + reasonForChange));
		
		im.setLore(lore);
		i.setItemMeta(im);
		
		return i;
	}
	
	/**
	 * Saves all varibles to config.
	 */
	public void writeToConfig() {
		PluginFile file = Main.getInstance().teamRequestsFile;
		
		file.set(getPathFor("requesterUUID"), requesterUUID);
		file.set(getPathFor("teamToChangeTo"), teamToChangeTo.toString());
		file.set(getPathFor("timeRequestedFormated"), timeRequestedFormated);
		file.set(getPathFor("timeRequested"), timeRequested);
		file.set(getPathFor("reasonForChange"), reasonForChange);
		file.set(getPathFor("denied"), denied);
		file.set(getPathFor("id"), id);
		file.set(getPathFor("accepted"), accepted);
		file.set(getPathFor("accepterUUID"), accepterUUID);
		file.set(getPathFor("acceptedTime"), acceptedTime);
		file.save();
		
	}
	
	public String getPathFor(String varible) {
		return "requests." + id + "." + varible;
	}
	
	public TeamChangeRequest() {
		
	}

	// Start Getters and Setters
	
	public String getRequesterUUID() {
		return requesterUUID;
	}
	
	public Team getTeamToChangeTo() {
		return teamToChangeTo;
	}
	
	public long getTimeRequested() {
		return timeRequested;
	}

	public String getTimeRequestedFormated() {
		return timeRequestedFormated;
	}

	public String getReasonForChange() {
		return reasonForChange;
	}

	public boolean isDenied() {
		return denied;
	}

	public void setDenied(boolean denied) {
		this.denied = denied;
	}

	public boolean isAccepted() {
		return accepted;
	}

	public void setAccepted(boolean accepted) {
		this.accepted = accepted;
	}

	public String getAccepterUUID() {
		return accepterUUID;
	}

	public void setAccepterUUID(String accepterUUID) {
		this.accepterUUID = accepterUUID;
	}

	public long getAcceptedTime() {
		return acceptedTime;
	}

	public void setAcceptedTime(long acceptedTime) {
		this.acceptedTime = acceptedTime;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	// END Getters and Setters
	
}
