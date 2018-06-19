package net.farugames.data.bungee;

import net.md_5.bungee.api.plugin.Plugin;

public class BungeeFaruData extends Plugin {

	private static BungeeFaruData instance;
	
	public void onLoad() {
		instance = this;
		
		super.onLoad();
	}
	
	public void onEnable() {
		super.onEnable();
	}
	
	public void onDisable() {
		super.onDisable();
	}
	
	public static BungeeFaruData getInstance() {
		return instance;
	}
}
