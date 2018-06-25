package net.faru.data.bungee;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import net.faru.api.bungee.player.FaruBungeePlayer;
import net.faru.data.mysql.MySQLManager;
import net.md_5.bungee.api.plugin.Plugin;

public class BungeeFaruData extends Plugin {

	private static BungeeFaruData instance;
	
	public static Map<UUID, FaruBungeePlayer> iFaruPlayer = new HashMap<UUID, FaruBungeePlayer>();
	
	public void onLoad() {
		instance = this;
		new MySQLManager("localhost", "3306", "farugames", "root", "b4z5MT4Nk6hA").connection();
		
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
