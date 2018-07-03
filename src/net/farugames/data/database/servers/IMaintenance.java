package net.farugames.data.database.servers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import net.farugames.data.mysql.MySQLManager;

public class IMaintenance {

	private static String table = "maintenance";
	
	public static String getTable() {
		return table;
	}
	
	public static void setState(Boolean maintenance) {
		try {
			final Connection connection = MySQLManager.getConnection();
			PreparedStatement preparedStatement = connection
					.prepareStatement("UPDATE " + table + " SET state = ? WHERE 1");
			preparedStatement.setBoolean(1, maintenance);
			preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (SQLException e) {
			System.out.print("");
			System.out.print("[IMaintenance] Connexion � la base de donn�es par la table " + table + " impossible : ");
			e.printStackTrace();
			System.out.print("");
		}
	}
	
	public static Boolean isEnable() {
		try {
			final Connection connection = MySQLManager.getConnection();
			PreparedStatement preparedStatement = connection
					.prepareStatement("SELECT state FROM " + table + " WHERE state = " + true);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			System.out.print("");
			System.out.print("[IMaintenance] Connexion � la base de donn�es par la table " + table + " impossible : ");
			e.printStackTrace();
			System.out.print("");
		}
		return false;
	}
}
