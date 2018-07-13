package net.farugames.data.database;

import java.util.List;

public class ServerData {

	public ServerData(String serverName, String serverIp, String serverPort, String serverHost, String serverMode,
			String serverStatus, int serverOnlinePlayers, List<String> serverOnlinePlayersNames) {
		this.serverName = serverName;
		this.serverIp = serverIp;
		this.serverPort = serverPort;
		this.serverMode = serverHost;
		this.serverStatus = serverStatus;
		this.serverOnlinePlayers = serverOnlinePlayers;
		this.serverOnlinePlayersNames = serverOnlinePlayersNames;
	}

	private String serverName;
	private String serverIp;
	private String serverPort;
	private String serverHost;
	private String serverMode;
	private String serverStatus;
	private int serverOnlinePlayers;
	private List<String> serverOnlinePlayersNames;

	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	public String getServerIp() {
		return serverIp;
	}

	public void setServerIp(String serverIp) {
		this.serverIp = serverIp;
	}

	public String getServerPort() {
		return serverPort;
	}

	public void setServerPort(String serverPort) {
		this.serverPort = serverPort;
	}

	public String getServerHost() {
		return serverHost;
	}

	public void setServerHost(String serverHost) {
		this.serverHost = serverHost;
	}

	public String getServerMode() {
		return serverMode;
	}

	public void setServerMode(String serverMode) {
		this.serverMode = serverMode;
	}

	public String getServerStatus() {
		return serverStatus;
	}

	public void setServerStatus(String serverStatus) {
		this.serverStatus = serverStatus;
	}

	public int getServerOnlinePlayers() {
		return serverOnlinePlayers;
	}

	public void setServerOnlinePlayers(int serverOnlinePlayers) {
		this.serverOnlinePlayers = serverOnlinePlayers;
	}

	public List<String> getServerOnlinePlayersNames() {
		return serverOnlinePlayersNames;
	}

	public void setServerOnlinePlayersNames(List<String> serverOnlinePlayersNames) {
		this.serverOnlinePlayersNames = serverOnlinePlayersNames;
	}
}
