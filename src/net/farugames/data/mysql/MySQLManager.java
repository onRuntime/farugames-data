package net.farugames.data.mysql;

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
				this.connection = DriverManager.getConnection(this.urlBase + this.host + "/" + this.database + "?autoReconnect=true",
						this.username, this.password);
				return;
			} catch (SQLException e) {
				System.out.println("");
				System.out.println("[MySQLManager] Connexion à la base de données MySQL impossible :");
				e.printStackTrace();
				System.out.println("");
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
				System.out.println("");
				System.out.println("[MySQLManager] Déconnexion de la base de données MySQL impossible :");
				e.printStackTrace();
				System.out.println("");
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
			System.out.println("");
			System.out.println("[MySQLManager] Détection de la connexion impossible :");
			e.printStackTrace();
			System.out.println("");
		}
		return false;
	}

	public static Connection getConnection() {
		return connection;
	}
}
