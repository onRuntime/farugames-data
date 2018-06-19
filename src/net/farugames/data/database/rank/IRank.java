package net.farugames.data.database.rank;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import net.faru.api.player.rank.Rank;
import net.farugames.data.mysql.MySQLManager;

public class IRank {

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
						"INSERT INTO " + table + " (player_uuid, rank_power) VALUES(?, ?)");
				preparedStatement.setString(1, uuid.toString());
				preparedStatement.setInt(2, 0);
				preparedStatement.executeUpdate();
				preparedStatement.close();
				return;
			}
		} catch (SQLException e) {
			System.out.print("");
			System.out.print("[IRank] Connexion à la base de données par la table " + table + " impossible : ");
			e.printStackTrace();
			System.out.print("");
		}
	}
	
	public static Rank getRank(UUID uuid) {
		Rank rank = null;
		try {
			final Connection connection = MySQLManager.getConnection();
			PreparedStatement preparedStatement = (PreparedStatement) connection
					.prepareStatement("SELECT rank_power FROM " + table + " WHERE uuid = ?");
			preparedStatement.setString(1, uuid.toString());
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()) {
				rank = Rank.getRankByPower(rs.getInt("rank_power")) != null ? Rank.getRankByPower(rs.getInt("rank_power")) : null;
			}
		} catch (SQLException e) {
			System.out.print("");
			System.out.print("[IRank] Connexion à la base de données par la table " + table + " impossible : ");
			e.printStackTrace();
			System.out.print("");
		}
		return rank;
	}
}
