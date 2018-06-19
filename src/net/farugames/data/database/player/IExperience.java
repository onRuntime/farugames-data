package net.farugames.data.database.player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import net.farugames.data.mysql.MySQLManager;

public class IExperience {
	
	private static String table = "experience";
	
	public static String getTable() {
		return table;
	}
	
	public static void createAccount(UUID uuid) {
		try {
			final Connection connection = MySQLManager.getConnection();
			PreparedStatement preparedStatement = connection
					.prepareStatement("SELECT experience " + " FROM " + table + " WHERE player_uuid = ?");
			preparedStatement.setString(1, uuid.toString());
			ResultSet rs = preparedStatement.executeQuery();
			if (!rs.next()) {
				preparedStatement.close();
				preparedStatement = connection.prepareStatement(
						"INSERT INTO " + table + " (player_uuid, experience) VALUES(?, ?)");
				preparedStatement.setString(1, uuid.toString());
				preparedStatement.setInt(2, 0);
				preparedStatement.executeUpdate();
				preparedStatement.close();
			}
		} catch (SQLException e) {
			System.out.print("");
			System.out.print("[IExperience] Connexion à la base de données par la table " + table + " impossible : ");
			e.printStackTrace();
			System.out.print("");
		}
	}
	
	public static void addExperience(UUID uuid, Integer value) {
		try {
			final Connection connection = MySQLManager.getConnection();
			PreparedStatement preparedStatement = connection
					.prepareStatement("UPDATE " + table + " SET experience = " + getExperience(uuid) + " + ? WHERE player_uuid = ?");
			preparedStatement.setInt(1, value);
			preparedStatement.setString(2, uuid.toString());
			preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (SQLException e) {
			System.out.print("");
			System.out.print("[IExperience] Connexion à la base de données par la table " + table + " impossible : ");
			e.printStackTrace();
			System.out.print("");
		}
	}
	
	public static void setExperience(UUID uuid, Integer values) {
		try {
			final Connection connection = MySQLManager.getConnection();
			PreparedStatement preparedStatement = connection
					.prepareStatement("UPDATE " + table + " SET experience = ? WHERE player_uuid = ?");
			preparedStatement.setInt(1, values);
			preparedStatement.setString(2, uuid.toString());
			preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (SQLException e) {
			System.out.print("");
			System.out.print("[IExperience] Connexion à la base de données par la table " + table + " impossible : ");
			e.printStackTrace();
			System.out.print("");
		}
	}
	
	public static Integer getExperience(UUID uuid) {
		Integer levelExperience = 0;
		try {
			final Connection connection = MySQLManager.getConnection();
			PreparedStatement preparedStatement = connection
					.prepareStatement("SELECT experience FROM " + table + " WHERE player_uuid = ?");
			preparedStatement.setString(1, uuid.toString());
			ResultSet rs = preparedStatement.executeQuery();
			if (!rs.next()) {
				connection.close();
				return Integer.valueOf(levelExperience);
			}
			levelExperience = rs.getInt("experience");
			preparedStatement.close();
		} catch (SQLException e) {
			System.out.print("");
			System.out.print("[IExperience] Connexion à la base de données par la table " + table + " impossible : ");
			e.printStackTrace();
			System.out.print("");
		}
		return Integer.valueOf(levelExperience);
	}
}