package net.farugames.data.database;

import java.util.UUID;

public class PlayerData {

	public PlayerData(UUID playerUUID, String playerName, String playerNickname, String playerRankName,
			int playerRankLevel, String playerFirstHost, String playerLastHost, String playerLoginFirst,
			String playerLoginLast, String playerLanguage, long playerCandy, long playerCookie) {
		this.playerUUID = playerUUID;
		this.playerName = playerName;
		this.playerNickname = playerNickname;
		this.playerRankName = playerRankName;
		this.playerRankLevel = playerRankLevel;
		this.playerFirstHost = playerFirstHost;
		this.playerLastHost = playerLastHost;
		this.playerLoginFirst = playerLoginFirst;
		this.playerLoginLast = playerLoginLast;
		this.playerLanguage = playerLanguage;
		this.playerCandy = playerCandy;
		this.playerCookie = playerCookie;
	}

	private UUID playerUUID;
	private String playerName;
	private String playerNickname;
	private String playerRankName;
	private int playerRankLevel;
	private String playerFirstHost;
	private String playerLastHost;
	private String playerLoginFirst;
	private String playerLoginLast;
	private String playerLanguage;
	private long playerCandy;
	private long playerCookie;
	private long playerExperience;

	public UUID getPlayerUUID() {
		return playerUUID;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public String getPlayerNickname() {
		return playerNickname;
	}

	public void setPlayerNickname(String playerNickname) {
		this.playerNickname = playerNickname;
	}

	public String getPlayerRankName() {
		return playerRankName;
	}

	public void setPlayerRankName(String playerRankName) {
		this.playerRankName = playerRankName;
	}

	public int getPlayerRankLevel() {
		return playerRankLevel;
	}

	public void setPlayerRankLevel(int playerRankLevel) {
		this.playerRankLevel = playerRankLevel;
	}

	public String getPlayerFirstHost() {
		return playerFirstHost;
	}

	public void setPlayerFirstHost(String playerFirstHost) {
		this.playerFirstHost = playerFirstHost;
	}

	public String getPlayerLastHost() {
		return playerLastHost;
	}

	public void setPlayerLastHost(String playerLastHost) {
		this.playerLastHost = playerLastHost;
	}

	public String getPlayerLoginFirst() {
		return playerLoginFirst;
	}

	public void setPlayerLoginFirst(String playerLoginFirst) {
		this.playerLoginFirst = playerLoginFirst;
	}

	public String getPlayerLoginLast() {
		return playerLoginLast;
	}

	public void setPlayerLoginLast(String playerLoginLast) {
		this.playerLoginLast = playerLoginLast;
	}

	public String getPlayerLanguage() {
		return playerLanguage;
	}

	public void setPlayerLanguage(String playerLanguage) {
		this.playerLanguage = playerLanguage;
	}

	public long getPlayerCandy() {
		return playerCandy;
	}

	public void setPlayerCandy(long playerCandy) {
		this.playerCandy = playerCandy;
	}

	public long addPlayerCandy(long playerCandy) {
		return getPlayerCandy() + playerCandy;
	}

	public long removePlayerCandy(long playerCandy) {
		return getPlayerCandy() - playerCandy;
	}

	public long getPlayerCookie() {
		return playerCookie;
	}

	public void setPlayerCookie(long playerCookie) {
		this.playerCookie = playerCookie;
	}

	public long addPlayerCookie(long playerCookie) {
		return getPlayerCookie() + playerCookie;
	}

	public long removePlayerCookie(long playerCookie) {
		return getPlayerCookie() - playerCookie;
	}

	public long getPlayerExperience() {
		return playerExperience;
	}

	public void setPlayerExperience(long playerExperience) {
		this.playerExperience = playerExperience;
	}
	
	public long addPlayerExperience(long playerExperience) {
		return getPlayerExperience() + playerExperience;
	}
	
	public long removePlayerExperience(long playerExperience) {
		return getPlayerExperience() - playerExperience;
	}

}
