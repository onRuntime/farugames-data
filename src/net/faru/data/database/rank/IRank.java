package net.faru.data.database.rank;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import net.faru.api.player.rank.Rank;
import net.faru.data.mysql.MySQLManager;

public class IRank {

	private static String table = "rank";
	
	public static String getTable() {
		return table;
	}
	
	public static void createAccount(UUID uuid) {
		try {
			final Connection connection = MySQLManager.getConnection();
			PreparedStatement preparedStatement = (PreparedStatement) connection
					.prepareStatement("SELECT player_uuid FROM " + table + " WHERE player_uuid = ?");
			preparedStatement.setString(1, uuid.toString());
			ResultSet rs = preparedStatement.executeQuery();
			if(!rs.next()) {
				preparedStatement.close();
				preparedStatement = connection.prepareStatement(
						"INSERT INTO " + table + " (player_uuid, rank_power) VALUES(?, ?)");
				preparedStatement.setString(1, uuid.toString());
				preparedStatement.setInt(2, 0);
				preparedStatement.executeUpdate();
				preparedStatement.close();
				return;
			}
		} catch (SQLException e) {
			System.err.print("[IRank] Error trying to connect to database to the table " + table + " : ");
			e.printStackTrace();
		}
	}
	
	public static Rank getRank(UUID uuid) {
		Rank rank = null;
		try {
			final Connection connection = MySQLManager.getConnection();
			PreparedStatement preparedStatement = (PreparedStatement) connection
					.prepareStatement("SELECT rank_power FROM " + table + " WHERE player_uuid = ?");
			preparedStatement.setString(1, uuid.toString());
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()) {
				return Rank.getRankByPower(rs.getInt("rank_power"));
			}
			preparedStatement.close();
		} catch (SQLException e) {
			System.err.print("[IRank] Error trying to connect to database to the table " + table + " : ");
			e.printStackTrace();
		}
		return rank;
	}
	
	public static void setRank(UUID uuid, Rank rank) {
		try {
			final Connection connection = MySQLManager.getConnection();
			PreparedStatement preparedStatement = (PreparedStatement) connection
					.prepareStatement("SELECT rank_power FROM " + table + " WHERE player_uuid = ?");
			preparedStatement.setString(1, uuid.toString());
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()) {
				preparedStatement.close();
				preparedStatement = (PreparedStatement) connection
						.prepareStatement("UPDATE " + table + " SET rank_power = ? WHERE player_uuid = ?");
				preparedStatement.setInt(1, rank.getPower());
				preparedStatement.setString(2, uuid.toString());
				preparedStatement.executeUpdate();
			}
			preparedStatement.close();
		} catch (SQLException e) {
			System.err.print("[IRank] Error trying to connect to database to the table " + table + " : ");
			e.printStackTrace();
		}
	}
}
