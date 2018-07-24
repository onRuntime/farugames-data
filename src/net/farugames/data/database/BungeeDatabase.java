package net.farugames.data.database;

import net.farugames.api.bungee.sanctions.Sanction;
import net.farugames.api.bungee.servers.FaruServerAPI;
import net.farugames.api.spigot.player.data.DataType;
import net.farugames.data.bungee.BungeeFaruData;
import net.farugames.data.database.entities.*;
import org.bukkit.entity.Player;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Response;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class BungeeDatabase {

    public PlayerDataEntity getPlayerData(Player player) {
        return BungeeFaruData.getGson.fromJson(BungeeFaruData.getInstance().getRedisDatabase().getJedisPool().getResource().get("PlayerData:" + player.getUniqueId()), PlayerDataEntity.class);
    }

    public PlayerDataEntity getPlayerData(UUID player) {
        return BungeeFaruData.getGson.fromJson(BungeeFaruData.getInstance().getRedisDatabase().getJedisPool().getResource().get("PlayerData:" + player), PlayerDataEntity.class);
    }

    public ProxyDataEntity getProxyData(String name) {
        return BungeeFaruData.getGson.fromJson(BungeeFaruData.getInstance().getRedisDatabase().getJedisPool().getResource().get("ServerData:" + name), ProxyDataEntity.class);
    }

    public ServerDataEntity getServerData(String name) {
        return BungeeFaruData.getGson.fromJson(BungeeFaruData.getInstance().getRedisDatabase().getJedisPool().getResource().get("ServerData:" + name), ServerDataEntity.class);
    }

    public SanctionDataEntity getSanctionData(Player player) {
        return BungeeFaruData.getGson.fromJson(BungeeFaruData.getInstance().getRedisDatabase().getJedisPool().getResource().get("SanctionData:" + player.getUniqueId()), SanctionDataEntity.class);
    }

    public SanctionDataEntity getSanctionData(UUID player) {
        return BungeeFaruData.getGson.fromJson(BungeeFaruData.getInstance().getRedisDatabase().getJedisPool().getResource().get("SanctionData:" + player), SanctionDataEntity.class);
    }

    public Object getData(UUID playerUUID, DataType dataType){
        return BungeeFaruData.getGson.fromJson(BungeeFaruData.getInstance().getRedisDatabase().getJedisPool().getResource().get("PlayerData:Preference:"+playerUUID+""+dataType.getColumn()), Object.class);
    }

    public void setData(UUID playerUUID, DataType dataType, Object value){
        BungeeFaruData.getInstance().getRedisDatabase().getJedisPool().getResource().set("PlayerData:Preference:"+playerUUID+":"+dataType.getColumn(), BungeeFaruData.getGson.toJson(value));
    }

    public void request(ServerType serverType) {
        try (Jedis j = BungeeFaruData.getInstance().getRedisDatabase().getJedisPool().getResource()) {
            j.sadd("ServerManager:RequestedServers", serverType.getName());
        }
    }

    public boolean exists(String name) {
        try (Jedis j = BungeeFaruData.getInstance().getRedisDatabase().getJedisPool().getResource()) {
            return j.exists("ServerManager:ServerData:" + name);
        }
    }

    public void update(FaruServerAPI server) {
        try (Jedis j = BungeeFaruData.getInstance().getRedisDatabase().getJedisPool().getResource()) {
            j.set("ServerManager:ServerData:" + server.getName(), BungeeFaruData.getGson.toJson(new ProxyDataEntity(server.getName(), server.isJoinable(), server.getHost().getHostAddress(), server.getPort())));
        }

    }

    public static void create(Sanction sanction) {
        try (Jedis j = BungeeFaruData.getInstance().getRedisDatabase().getJedisPool().getResource()) {
            j.set("SanctionData:" + sanction.getTarget().getUUID(), BungeeFaruData.getGson.toJson(new SanctionDataEntity(sanction.getTarget().getUUID(), sanction.getAuthor().getUUID(), sanction.getSanction(), sanction.getReason(), Timestamp.valueOf(System.currentTimeMillis() + ""), Timestamp.valueOf(sanction.getTime() + ""))));
        }
    }

    public static List<FaruServerAPI> getFSAServers() {
        List<FaruServerAPI> list = new ArrayList<FaruServerAPI>();
        try (Jedis j = BungeeFaruData.getInstance().getRedisDatabase().getJedisPool().getResource()) {

            List<String> ids = j.smembers("ServerManager:ServerID").stream().map(str -> str)
                    .collect(Collectors.toList());
            String[] i = new String[ids.size()];
            for (int k = 0; k < i.length; k++) {
                i[k] = ids.get(k);
            }
            list.addAll(getServerFSA(i));
        }
        return list;
    }

    public static List<FaruServerAPI> getServerFSA(String... names) {
        if (names.length == 0)
            return new ArrayList<>();
        List<ServerDataEntity> i = new ArrayList<>();
        List<FaruServerAPI> a = new ArrayList<>();
        try (Jedis j = BungeeFaruData.getInstance().getRedisDatabase().getJedisPool().getResource()) {
            Pipeline p = j.pipelined();
            List<Response<String>> r = new ArrayList<>();
            for (String id : names) {
                r.add(p.get("ServerManager:ServerData:" + id));
            }
            p.sync();
            for (Response<String> s : r) {
                i.add(BungeeFaruData.getGson.fromJson(s.get(), ServerDataEntity.class));
            }
        }
        for (ServerDataEntity sde : i)
            a.add(FaruServerAPI.getServer(sde.getServerName(), sde.getServerIp(), sde.getServerPort()));
        return a;
    }

    public static class Config {
        private static Jedis jedis = BungeeFaruData.getInstance().getRedisDatabase().getJedisPool().getResource();

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