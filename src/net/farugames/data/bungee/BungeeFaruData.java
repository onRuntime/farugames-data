package net.farugames.data.bungee;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import net.farugames.data.database.BungeeDatabase;
import net.farugames.data.database.RedisDatabase;
import net.md_5.bungee.api.plugin.Plugin;

public class BungeeFaruData extends Plugin {

	private static BungeeFaruData instance;

	public static Gson getGson = new GsonBuilder().setPrettyPrinting().create();

	private RedisDatabase redisDatabase;

	public void onLoad() {
		instance = this;
		redisDatabase = new RedisDatabase("149.202.102.63", "HCK2u7a8Up4d", 6379);

		redisDatabase.connect();
		
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
	
	public RedisDatabase getRedisDatabase() {return redisDatabase;}
	
	public BungeeDatabase getBungeeDatabase() {return new BungeeDatabase();}

}
