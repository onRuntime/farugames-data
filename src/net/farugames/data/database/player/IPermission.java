package net.farugames.data.database.player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import net.farugames.data.mysql.MySQLManager;

public class IPermission {

	private static String table = "rank";
	
	public static String getTable() {
		return table;
	}
	
	public static void createAccount(UUID uuid) {
		try {
			final Connection connection = MySQLManager.getConnection();
			PreparedStatement preparedStatement = (PreparedStatement) connection
					.prepareStatement("SELECT uuid FROM " + table + " WHERE uuid = ?");
			preparedStatement.setString(1, uuid.toString());
			ResultSet rs = preparedStatement.executeQuery();
			if(!rs.next()) {
				preparedStatement.close();
				preparedStatement = connection.prepareStatement(
						"INSERT INTO " + table + " (player_uuid, permissions) VALUES(?, ?)");
				preparedStatement.setString(1, uuid.toString());
				preparedStatement.setString(2, new ArrayList<String>().toString());
				preparedStatement.executeUpdate();
				preparedStatement.close();
				return;
			}
		} catch (SQLException e) {
			System.out.print("");
			System.out.print("[IPermission] Connexion à la base de données par la table " + table + " impossible : ");
			e.printStackTrace();
			System.out.print("");
		}
	}
	
	public static void setPermissions(UUID uuid, List<String> permission) {
		try {
			final Connection connection = MySQLManager.getConnection();
			PreparedStatement preparedStatement = (PreparedStatement) connection
					.prepareStatement("SELECT uuid FROM " + table + " WHERE uuid = ?");
			preparedStatement.setString(1, uuid.toString());
			ResultSet rs = preparedStatement.executeQuery();
			if(!rs.next()) {
				preparedStatement.close();
				preparedStatement = connection.prepareStatement(
						"INSERT INTO " + table + " (player_uuid, permissions) VALUES(?, ?)");
				preparedStatement.setString(1, uuid.toString());
				preparedStatement.setString(2, permission.toString());
				preparedStatement.executeUpdate();
				preparedStatement.close();
				return;
			}
		} catch (SQLException e) {
			System.out.print("");
			System.out.print("[IPermission] Connexion à la base de données par la table " + table + " impossible : ");
			e.printStackTrace();
			System.out.print("");
		}
	}
	
	public static void addPermission(UUID uuid, String permission) {
		try {
			final Connection connection = MySQLManager.getConnection();
			PreparedStatement preparedStatement = (PreparedStatement) connection
					.prepareStatement("SELECT uuid FROM " + table + " WHERE uuid = ?");
			preparedStatement.setString(1, uuid.toString());
			ResultSet rs = preparedStatement.executeQuery();
			if(!rs.next()) {
				preparedStatement.close();
				preparedStatement = connection.prepareStatement(
						"INSERT INTO " + table + " (player_uuid, permissions) VALUES(?, ?)");
				preparedStatement.setString(1, uuid.toString());
				preparedStatement.setString(2, permission);
				preparedStatement.executeUpdate();
				preparedStatement.close();
				return;
			}
		} catch (SQLException e) {
			System.out.print("");
			System.out.print("[IPermission] Connexion à la base de données par la table " + table + " impossible : ");
			e.printStackTrace();
			System.out.print("");
		}
	}
	
	public static List<String> getPermissions(UUID uuid) {
		List<String> list = new ArrayList<String>();
		try {
			final Connection connection = MySQLManager.getConnection();
			PreparedStatement preparedStatement = (PreparedStatement) connection
					.prepareStatement("SELECT permissions FROM " + table + " WHERE uuid = ?");
			preparedStatement.setString(1, uuid.toString());
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()) {
				list.add(rs.getString("permissions"));
			}
			preparedStatement.close();
		} catch (SQLException e) {
			System.out.print("");
			System.out.print("[IPermission] Connexion à la base de données par la table " + table + " impossible : ");
			e.printStackTrace();
			System.out.print("");
		}
		return list;
	}
}
