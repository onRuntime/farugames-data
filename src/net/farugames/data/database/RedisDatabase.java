package net.farugames.data.database;

import java.util.logging.Level;
import java.util.logging.Logger;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisDatabase {
	private final String host, password;
	private final int port;

	private JedisPool jedisPool;
	
	public RedisDatabase(String host, String password, int port) {
		this.host = host;
		this.password = password;
		this.port = port;
	}
	
	public void connect() {
		JedisPoolConfig jedisConfiguration = new JedisPoolConfig();
        jedisConfiguration.setMaxTotal(999999999);
        jedisConfiguration.setMaxWaitMillis(5000);

        Logger logger = Logger.getLogger(JedisPool.class.getName());
        logger.setLevel(Level.OFF);

        this.jedisPool = new JedisPool(jedisConfiguration, host, port, 5000,password);
        /*
//		ClassLoader previous = Thread.currentThread().getContextClassLoader();
//		Thread.currentThread().setContextClassLoader(Jedis.class.getClassLoader());
		jedisPool = new JedisPool(new JedisPoolConfig(), host, port, 2000,password);
//		Thread.currentThread().setContextClassLoader(previous);
		try (Jedis jedisConnect = jedisPool.getResource()) {
			System.out.println("[RedisDatabase] FaruGames vient de se connecter à Redis.");
		} catch (Exception e) {
			System.out.println("[RedisDatabase] Erreur: impossible de se connecter à Redis.");
		}*/
	}
	
	public void disconnect() {
		try {
			jedisPool.close();
			System.out.println("[RedisDatabase] FaruGames vient de se déconnecter à Redis.");
		} catch (Exception e) {
			System.out.println("[RedisDatabase] Erreur: impossible de se déconnecter à Redis.");
			return;
		}
	}
	
	public JedisPool getJedisPool() {
		try (Jedis jedisConnect = jedisPool.getResource()) {
			return jedisPool;
		}catch (Exception e) {
			System.out.println("[RedisDatabase] Erreur: impossible de se connecter à Redis.");
			return null;
		}
	}
}
