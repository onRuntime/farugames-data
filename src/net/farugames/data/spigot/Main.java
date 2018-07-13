package net.farugames.data.spigot;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;

import net.farugames.data.mysql.MySQLManager;

public class Main extends JavaPlugin implements PluginMessageListener {
	
	private static Main instance;
	
	public void onLoad() {
		instance = this;
		new MySQLManager("149.202.102.63", "3306", "farugames", "proxy", "HCK2u7a8Up4d").connection();
		
		this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
		this.getServer().getMessenger().registerIncomingPluginChannel(this, "BungeeCord", this);
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

	@Override
	public void onPluginMessageReceived(String arg, Player player, byte[] args) {
		// PluginMessages event.
	}
}
