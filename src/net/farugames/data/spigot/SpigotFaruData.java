package net.farugames.data.spigot;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import net.farugames.data.database.RedisDatabase;
import net.farugames.data.database.SpigotDatabase;

public class SpigotFaruData extends JavaPlugin implements PluginMessageListener {
	
	private static SpigotFaruData instance;

	public static Gson getGson = new GsonBuilder().setPrettyPrinting().create();
	
	private RedisDatabase redisDatabase;
	
	public void onLoad() {
		instance = this;
		redisDatabase = new RedisDatabase("149.202.102.63","b4z5MT4Nk6hA",6379);
		
		redisDatabase.connect();
		
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
	
	public static SpigotFaruData getInstance() {
		return instance;
	}

	@Override
	public void onPluginMessageReceived(String arg, Player player, byte[] args) {
		// PluginMessages event.
	}
	
	public RedisDatabase getRedisDatabase() {return redisDatabase;}
	
	public SpigotDatabase getSpigotDatabase() {return new SpigotDatabase();}
}
