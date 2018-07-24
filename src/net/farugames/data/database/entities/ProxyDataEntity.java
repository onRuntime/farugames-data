package net.farugames.data.database.entities;

public class ProxyDataEntity {

	private String name;
	private boolean proxyMaintenance;
	private int port;
	private String serverIp;
	public ProxyDataEntity(String name, boolean proxyMaintenance,String serverIp, int port) {
		this.name = name;
		this.proxyMaintenance = proxyMaintenance;
		this.port = port;
		this.serverIp = serverIp;
	}
	
	public String getName() {
		return name;
	}
	
	public boolean isProxyMaintenance() {
		return proxyMaintenance;
	}

	public void setProxyMaintenance(boolean proxyMaintenance) {
		this.proxyMaintenance = proxyMaintenance;
	}

	public int getPort() {
		return port;
	}

	public String getServerIp() {
		return serverIp;
	}


}
