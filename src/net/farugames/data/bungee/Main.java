package net.farugames.data.bungee;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import net.farugames.api.bungee.proxiedplayer.FaruBungeePlayer;
import net.farugames.data.mysql.MySQLManager;
import net.md_5.bungee.api.plugin.Plugin;

public class Main extends Plugin {

	private static Main instance;
	
	public static Map<UUID, FaruBungeePlayer> iFaruPlayer = new HashMap<UUID, FaruBungeePlayer>();
	
	public void onLoad() {
		instance = this;
		new MySQLManager("149.202.102.63", "3306", "farugames", "proxy", "HCK2u7a8Up4d").connection();
		
		super.onLoad();
	}
	
	public void onEnable() {
		
		super.onEnable();
	}
	
	public void onDisable() {
		super.onDisable();
	}
	
	public static Main getInstance() {
		return instance;
	}
}