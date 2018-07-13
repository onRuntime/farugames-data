package net.farugames.data.database;

public class ProxyData {

	public ProxyData(boolean proxyMaintenance) {
		this.proxyMaintenance = proxyMaintenance;
	}

	private boolean proxyMaintenance;

	public boolean isProxyMaintenance() {
		return proxyMaintenance;
	}

	public void setProxyMaintenance(boolean proxyMaintenance) {
		this.proxyMaintenance = proxyMaintenance;
	}

}
