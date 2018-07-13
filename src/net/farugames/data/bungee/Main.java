package net.farugames.data.bungee;

import net.farugames.data.mysql.MySQLManager;
import net.md_5.bungee.api.plugin.Plugin;

public class Main extends Plugin {

	private static Main instance;
	
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
