package net.faru.data.database.player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import net.faru.api.sanctions.Sanction;
import net.faru.data.mysql.MySQLManager;

public class ISanction {

	private static String table = "sanctions";
	
	public static String getTable() {
		return table;
	}
	
	public static void create(Sanction sanction) {
		try {
			final Connection connection = MySQLManager.getConnection();
			PreparedStatement preparedStatement = connection
					.prepareStatement("INSERT INTO " + table + " ("
							+ " target_uuid,"
							+ " author_uuid,"
							+ " sanction_type,"
							+ " sanction_reason,"
							+ " sanction_date,"
							+ " sanction_end)"
							+ " VALUES ("
							+ " ?,"
							+ " ?,"
							+ " ?,"
							+ " ?,"
							+ " ?,"
							+ " DATE_ADD(NOW(), INTERVAL " + sanction.getTime() + " HOUR))");
			preparedStatement.setString(1, sanction.getTarget().getUUID().toString());
			preparedStatement.setString(2, sanction.getAuthor().getUUID().toString());
			preparedStatement.setString(3, sanction.getSanction().toString());
			preparedStatement.setString(4, sanction.getReason());
			preparedStatement.setString(5, new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(sanction.getDate()));
			preparedStatement.executeUpdate();
			preparedStatement.close();
			switch(sanction.getSanction()) {
			case SERVER_BAN:
				sanction.getTarget().setBanned(true);
				break;
			case GAMES_BAN:
				sanction.getTarget().setBanned(true);
				break;
			case MUTE:
				sanction.getTarget().setMuted(true);
				break;
			default:
				break;
			}
		} catch (SQLException e) {
			System.out.print("");
			System.out.print("[ISanction] Connexion à la base de données par la table " + table + " impossible : ");
			e.printStackTrace();
			System.out.print("");
		}
	}
}
