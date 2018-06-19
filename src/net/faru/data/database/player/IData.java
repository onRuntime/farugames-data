package net.faru.data.database.player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.bukkit.craftbukkit.v1_9_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;

import net.faru.api.player.data.DataType;
import net.faru.api.player.languages.Languages;
import net.faru.data.mysql.MySQLManager;

public class IData {

	private static String table = "data";
	
	public static String getTable() {
		return table;
	}
	
	public static void createAccount(UUID uuid, Player player) {
		try {
			final Connection connection = MySQLManager.getConnection();
			PreparedStatement preparedStatement = (PreparedStatement) connection
					.prepareStatement("SELECT uuid FROM " + table + " WHERE uuid = ?");
			preparedStatement.setString(1, uuid.toString());
			ResultSet rs = preparedStatement.executeQuery();
			if(!rs.next()) {
				preparedStatement.close();
				preparedStatement = (PreparedStatement) connection
						.prepareStatement("INSERT INTO " + table + " ("
								+ " player_uuid," /* 1 */
								+ " first_login," /* 2 */
								+ " first_ip," /* 3 */
								+ " last_login," /* 4 */
								+ " last_ip," /* 5 */
								+ " language," /* 6 */
								+ " afk_statut," /* 7 */
								+ " allow_private_messages," /* 8 */
								+ " allow_party," /* 9 */
								+ " allow_party_follow," /* 10 */
								+ " allow_friend," /* 11 */
								+ " allow_guilds," /* 12 */
								+ " allow_chat," /* 13 */
								+ " allow_player_visibility," /* 14 */
								+ " allow_lobby_double_process_command," /* 15 */
								+ " allow_chat_mention" /* 16 */
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
								+ " ?,"
								+ " ?,"
								+ " ?)");
				preparedStatement.setString(1, uuid.toString());
				preparedStatement.setString(2, new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date()));
				preparedStatement.setString(3, new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date()));
				preparedStatement.setString(4, ((CraftPlayer) player).getAddress().getHostString());
				preparedStatement.setString(5, ((CraftPlayer) player).getAddress().getHostString());
				preparedStatement.setString(6, Languages.FRENCH.getFile());
				preparedStatement.setBoolean(7, false);
				preparedStatement.setBoolean(8, true);
				preparedStatement.setBoolean(9, true);
				preparedStatement.setBoolean(10, true);
				preparedStatement.setBoolean(11, true);
				preparedStatement.setBoolean(12, true);
				preparedStatement.setBoolean(13, true);
				preparedStatement.setBoolean(14, true);
				preparedStatement.setBoolean(15, false);
				preparedStatement.setBoolean(16, true);
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
