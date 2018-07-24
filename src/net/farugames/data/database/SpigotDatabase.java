package net.farugames.data.database;

import net.farugames.api.bungee.servers.ServerStatut;
import net.farugames.api.spigot.player.data.DataType;
import net.farugames.data.database.entities.*;
import net.farugames.data.spigot.SpigotFaruData;
import org.bukkit.entity.Player;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class SpigotDatabase {

    public PlayerDataEntity getPlayerData(Player player) {
        return SpigotFaruData.getGson.fromJson(SpigotFaruData.getInstance().getRedisDatabase().getJedisPool().getResource().get("PlayerData:" + player.getUniqueId()), PlayerDataEntity.class);
    }

    public PlayerDataEntity getPlayerData(UUID player) {
        return SpigotFaruData.getGson.fromJson(SpigotFaruData.getInstance().getRedisDatabase().getJedisPool().getResource().get("PlayerData:" + player), PlayerDataEntity.class);
    }

    public ProxyDataEntity getProxyData(String name) {
        return SpigotFaruData.getGson.fromJson(SpigotFaruData.getInstance().getRedisDatabase().getJedisPool().getResource().get("ProxyData:" + name), ProxyDataEntity.class);
    }

    public ServerDataEntity getServerData(String id) {
        return SpigotFaruData.getGson.fromJson(SpigotFaruData.getInstance().getRedisDatabase().getJedisPool().getResource().get("ServerManager:ServerData:" + id), ServerDataEntity.class);
    }

    public SanctionDataEntity getSanctionData(Player player) {
        return SpigotFaruData.getGson.fromJson(SpigotFaruData.getInstance().getRedisDatabase().getJedisPool().getResource().get("SanctionData:" + player.getUniqueId()), SanctionDataEntity.class);
    }

    public SanctionDataEntity getSanctionData(UUID player) {
        return SpigotFaruData.getGson.fromJson(SpigotFaruData.getInstance().getRedisDatabase().getJedisPool().getResource().get("SanctionData:" + player), SanctionDataEntity.class);
    }

    public void request(ServerType serverType) {
        try (Jedis j = SpigotFaruData.getInstance().getRedisDatabase().getJedisPool().getResource()) {
            j.sadd("ServerManager:RequestedServers", serverType.getName());
        }
    }

    public void update(String serverName, ServerStatut status) {
        try (Jedis j = SpigotFaruData.getInstance().getRedisDatabase().getJedisPool().getResource()) {
            ServerDataEntity sDE = getServerData(serverName);
            sDE.setServerStatus(status.name());
            j.set("ServerManager:ServerData:" + serverName, SpigotFaruData.getGson.toJson(sDE));
        }
    }

    public Object getData(UUID playerUUID, DataType dataType) {
        return SpigotFaruData.getInstance().getRedisDatabase().getJedisPool().getResource().get("PlayerData:Preference:" + playerUUID + "" + dataType.getName());
    }

    public void setData(UUID playerUUID, DataType dataType, Object value) {
        SpigotFaruData.getInstance().getRedisDatabase().getJedisPool().getResource().set("PlayerData:Preference:" + playerUUID + ":" + dataType.getName(), SpigotFaruData.getGson.toJson(value));
    }

    public List<String> getPermissions(UUID uuid) {
        List<String> s = new ArrayList<>();
        try (Jedis j = SpigotFaruData.getInstance().getRedisDatabase().getJedisPool().getResource()) {
            List<String> ids = j.smembers("PlayerData:Permissions:" + uuid).stream().map(str -> str)
                    .collect(Collectors.toList());
            String[] i = new String[ids.size()];
            for (int k = 0; k < i.length; k++) {
                i[k] = ids.get(k);
            }
            s.addAll(Arrays.asList(i));
        }
        return s;
    }

    public void addPermission(UUID uuid, String permission) {
        SpigotFaruData.getInstance().getRedisDatabase().getJedisPool().getResource().sadd("PlayerData:Permissions:" + uuid, permission);
    }

    public void removePermission(UUID uuid, String permission) {
        try (Jedis j = SpigotFaruData.getInstance().getRedisDatabase().getJedisPool().getResource()) {
            j.srem("PlayerData:Permissions:" + uuid, permission);
        }

    }

    public void setPermissions(UUID uuid, List<String> permissions) {
        try (Jedis j = SpigotFaruData.getInstance().getRedisDatabase().getJedisPool().getResource()) {
            j.srem("PlayerData:Permissions:" + uuid);
            for (String perm : permissions)
                j.sadd("PlayerData:Permissions:" + uuid, perm);
        }
    }

    public static class Config {
        private static Jedis jedis = SpigotFaruData.getInstance().getRedisDatabase().getJedisPool().getResource();

        public static String getString(String configPath) {
            return jedis.get(configPath.replaceAll(".", ":"));
        }

        public static boolean getBoolean(String configPath) {
            return Boolean.valueOf(jedis.get(configPath.replaceAll(".", ":")));
        }

        public static void setBoolean(String configPath, boolean value) {
            jedis.set(configPath.replaceAll(".", ":"), value + "");
        }

        public static void setString(String configPath, String value) {
            jedis.set(configPath.replaceAll(".", ":"), value);
        }
    }

}
