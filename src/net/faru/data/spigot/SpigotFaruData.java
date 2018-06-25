package net.faru.data.spigot;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.plugin.java.JavaPlugin;

import net.faru.api.player.FaruPlayer;
import net.faru.data.mysql.MySQLManager;

public class SpigotFaruData extends JavaPlugin {
	
	public static Map<UUID, FaruPlayer> iFaruPlayer = new HashMap<UUID, FaruPlayer>();
	
	private static SpigotFaruData instance;
	
	public void onLoad() {
		instance = this;
		new MySQLManager("localhost", "3306", "farugames", "root", "b4z5MT4Nk6hA").connection();
		
		this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
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
