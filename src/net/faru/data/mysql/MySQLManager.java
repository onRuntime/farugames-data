package net.faru.data.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLManager {

	private String urlBase;
	private String host;
	private String database;
	private String username;
	private String password;
	private static Connection connection;

	public MySQLManager(String urlBase, String host, String database, String username, String password) {
		this.urlBase = urlBase;
		this.host = host;
		this.database = database;
		this.username = username;
		this.password = password;
	}

	@SuppressWarnings("static-access")
	public void connection() {
		if (!isConnected()) {
			try {
				this.connection = DriverManager.getConnection("jdbc:mysql://" + this.urlBase + ":" + this.host + "/" + this.database + "?autoReconnect=true",
						this.username, this.password);
				return;
			} catch (SQLException e) {
				System.out.println("[MySQLManager] Error trying to connect to database :");
				e.printStackTrace();
				return;
			}
		}
	}

	@SuppressWarnings("static-access")
	public void deconnection() {
		if (isConnected()) {
			try {
				this.connection.close();
				return;
			} catch (SQLException e) {
				System.out.println("[MySQLManager] Error trying to disconnect from database :");
				e.printStackTrace();
				return;
			}
		}
	}

	@SuppressWarnings("static-access")
	public boolean isConnected() {
		try {
			if ((this.connection == null) || (this.connection.isClosed()) || (this.connection.isValid(5))) {
				return false;
			}
			return true;
		} catch (SQLException e) {
			System.out.println("[MySQLManager] Error trying detect connection with database :");
			e.printStackTrace();
		}
		return false;
	}

	public static Connection getConnection() {
		return connection;
	}
}
