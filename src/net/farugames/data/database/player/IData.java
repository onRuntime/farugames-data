package net.farugames.data.database.player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import net.farugames.api.spigot.player.data.DataType;
import net.farugames.api.spigot.player.languages.Lang;
import net.farugames.data.mysql.MySQLManager;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class IData {

	private static String table = "data";
	
	public static String getTable() {
		return table;
	}
	
	public static void createAccount(UUID uuid, ProxiedPlayer player) {
		try {
			final Connection connection = MySQLManager.getConnection();
			PreparedStatement preparedStatement = (PreparedStatement) connection
					.prepareStatement("SELECT player_uuid FROM " + table + " WHERE player_uuid = ?");
			preparedStatement.setString(1, uuid.toString());
			ResultSet rs = preparedStatement.executeQuery();
			if(!rs.next()) {
				preparedStatement.close();
				preparedStatement = (PreparedStatement) MySQLManager.getConnection()
						.prepareStatement("INSERT INTO " + table + " ("
								+ " player_uuid," /* 1 */
								+ " first_login," /* 2 */
								+ " last_login," /* 3 */
								+ " language," /* 4 */
								+ " afk_statut," /* 5 */
								+ " allow_private_messages," /* 6 */
								+ " allow_party," /* 7 */
								+ " allow_party_follow," /* 8 */
								+ " allow_friend," /* 9 */
								+ " allow_guilds," /* 10 */
								+ " allow_chat," /* 11 */
								+ " allow_player_visibility," /* 12 */
								+ " allow_lobby_double_process_command," /* 13 */
								+ " allow_chat_mention" /* 14 */
								+ ") VALUES ("
								+ " ?,"
								+ " ?,"
								+ " ?,"
								+ " ?,"
								+ " ?,"
								+ " ?,"
								+ " ?,"
								+ " ?,"
								+ " ?,"
								+ " ?,"
								+ " ?,"
								+ " ?,"
								+ " ?,"
								+ " ?)");
				preparedStatement.setString(1, uuid.toString());
				preparedStatement.setString(2, new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date()));
				preparedStatement.setString(3, new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date()));
				preparedStatement.setString(4, Lang.ENGLISH.toString());
				preparedStatement.setBoolean(5, false);
				preparedStatement.setBoolean(6, true);
				preparedStatement.setBoolean(7, true);
				preparedStatement.setBoolean(8, true);
				preparedStatement.setBoolean(9, true);
				preparedStatement.setBoolean(10, true);
				preparedStatement.setBoolean(11, true);
				preparedStatement.setBoolean(12, true);
				preparedStatement.setBoolean(13, false);
				preparedStatement.setBoolean(14, true);
				preparedStatement.executeUpdate();
				preparedStatement.close();
			}
		} catch (SQLException e) {
			System.out.print("");
			System.out.print("[IData] Connexion à la base de données par la table " + table + " impossible : ");
			e.printStackTrace();
			System.out.print("");
		}
	}
	
	public static void setData(UUID uuid, DataType dataType, Object value) {
		try {
			final Connection connection = MySQLManager.getConnection();
			PreparedStatement preparedStatement = connection
					.prepareStatement("UPDATE " + table + " SET " + dataType.getColumn() + " = ? WHERE player_uuid = ?");
			preparedStatement.setObject(1, value);
			preparedStatement.setString(2, uuid.toString());
			preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (SQLException e) {
			System.out.print("");
			System.out.print("[IData] Connexion à la base de données par la table " + table + " impossible : ");
			e.printStackTrace();
			System.out.print("");
		}
	}
	
	public static Object getData(UUID uuid, DataType dataType) {
		Object value = null;
		try {
			final Connection connection = MySQLManager.getConnection();
			PreparedStatement preparedStatement = connection
					.prepareStatement("SELECT " + dataType.getColumn() + " FROM " + table + " WHERE player_uuid = ?");
			preparedStatement.setString(1, uuid.toString());
			ResultSet rs = preparedStatement.executeQuery();
			if (!rs.next()) {
				connection.close();
				return value;
			}
			value = rs.getObject(dataType.getColumn());
			preparedStatement.close();
		} catch (SQLException e) {
			System.out.print("");
			System.out.print("[IData] Connexion à la base de données par la table " + table + " impossible : ");
			e.printStackTrace();
			System.out.print("");
		}
		return value;
	}
}
