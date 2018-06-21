package net.faru.data.bungee;

import net.faru.data.mysql.MySQLManager;
import net.md_5.bungee.api.plugin.Plugin;

public class BungeeFaruData extends Plugin {

	private static BungeeFaruData instance;
	
	public void onLoad() {
		instance = this;
		new MySQLManager("127.0.0.1", "3306", "farugames", "root", "2y6bj7LMu2JT").connection();
		
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
