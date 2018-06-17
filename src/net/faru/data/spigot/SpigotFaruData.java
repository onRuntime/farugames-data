package net.faru.data.spigot;

import org.bukkit.plugin.java.JavaPlugin;

public class SpigotFaruData extends JavaPlugin {

	private static SpigotFaruData instance;
	
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
	
	public static SpigotFaruData getInstance() {
		return instance;
	}
}
