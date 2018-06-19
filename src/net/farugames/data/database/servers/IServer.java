package net.farugames.data.database.servers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.faru.api.servers.FaruServerAPI;
import net.faru.api.servers.ServerType;
import net.faru.api.spigot.SpigotFaruAPI;
import net.farugames.data.mysql.MySQLManager;

public class IServer {

	private static String table = "servers";
	
	public static String getTable() {
		return table;
	}
	
	public static void createServer(ServerType serverType, Integer id) {
		try {
			final Connection connection = MySQLManager.getConnection();
			PreparedStatement preparedStatement = (PreparedStatement) connection
					.prepareStatement("INSERT INTO " + table + " ("
							+ " server_id,"
							+ " server_name"
							+ " server_name_id"
							+ " server_type,"
							+ " players,"
							+ " players_list)"
							+ " VALUES ("
							+ " ?,"
							+ " ?,"
							+ " ?,"
							+ " ?,"
							+ " ?)");
			preparedStatement.setInt(1, id);
			preparedStatement.setString(2, serverType.getName());
			preparedStatement.setString(3, serverType.getNameID() + id);
			preparedStatement.setString(3, serverType.toString());
			preparedStatement.setInt(4, 0);
			preparedStatement.setString(5, new ArrayList<String>().toString());
			preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (SQLException e) {
			System.out.print("");
			System.out.print("[IServer] Connexion à la base de données par la table " + table + " impossible : ");
			e.printStackTrace();
			System.out.print("");
		}
	}
	
	public static List<FaruServerAPI> getServers(ServerType serverType) {
		List<FaruServerAPI> list = new ArrayList<FaruServerAPI>();
		try {
			final Connection connection = MySQLManager.getConnection();
			PreparedStatement preparedStatement = (PreparedStatement) connection
					.prepareStatement("SELECT server_type FROM " + table + " WHERE server_type = " + serverType.toString());
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()) {
				list.add(SpigotFaruAPI.iFaruServers.get(rs.getString("server_name_id")) != null ? 
						SpigotFaruAPI.iFaruServers.get(serverType.getNameID() + rs.getInt("server_id")) : null);
			}
		} catch (SQLException e) {
			System.out.print("");
			System.out.print("[IServer] Connexion à la base de données par la table " + table + " impossible : ");
			e.printStackTrace();
			System.out.print("");
		}
		return list;
	}
	
	public static FaruServerAPI getServer(ServerType serverType, Integer id) {
		try {
			final Connection connection = MySQLManager.getConnection();
			PreparedStatement preparedStatement = (PreparedStatement) connection
					.prepareStatement("SELECT server_type FROM " + table + " WHERE server_type = " + serverType.toString());
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()) {
				if(rs.getInt("server_id") == id) return FaruServerAPI.getFaruServer(ServerType.getServerType(rs.getString("server_type")), rs.getInt("server_id"));
			}
			return null;
		} catch (SQLException e) {
			System.out.print("");
			System.out.print("[IServer] Connexion à la base de données par la table " + table + " impossible : ");
			e.printStackTrace();
			System.out.print("");
		}
		return null;
	}
}
