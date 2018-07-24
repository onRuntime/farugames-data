package net.farugames.data.database.entities;

import java.util.List;

public class ServerDataEntity {

	private String serverName;
	private String serverIp;
	private Integer serverPort;
	private String serverHost;
	private ServerType serverType;
	private String serverStatus;
	private int serverOnlinePlayers;
	private List<String> serverOnlinePlayersNames;

	public ServerDataEntity(String serverName, String serverIp, Integer serverPort, String serverHost, ServerType serverType,
			String serverStatus, int serverOnlinePlayers, List<String> serverOnlinePlayersNames) {
		this.serverName = serverName;
		this.serverIp = serverIp;
		this.serverPort = serverPort;
		this.serverHost = serverHost;
		this.serverType = serverType;
		this.serverStatus = serverStatus;
		this.serverOnlinePlayers = serverOnlinePlayers;
		this.serverOnlinePlayersNames = serverOnlinePlayersNames;
	}
	
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

	public int getServerPort() {
		return serverPort;
	}

	public void setServerPort(int serverPort) {
		this.serverPort = serverPort;
	}

	public String getServerHost() {
		return serverHost;
	}

	public void setServerHost(String serverHost) {
		this.serverHost = serverHost;
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

	public ServerType getServerType() {
		return serverType;
	}


}
