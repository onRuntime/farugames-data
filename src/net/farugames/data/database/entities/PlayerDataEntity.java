package net.farugames.data.database.entities;

import java.util.UUID;

public class PlayerDataEntity {

	private UUID playerUUID;
	private String playerName;
	private String playerNickname;
	private String playerSkinUrl;
	private String playerRankName;
	private int playerRankLevel;
	private Integer playerExperience;
	private long playerCandy;
	private long playerCookie;

	public PlayerDataEntity(UUID playerUUID, String playerName, String playerNickname, String playerSkinUrl, String playerRankName,
							int playerRankLevel,Integer playerExperience, long playerCandy, long playerCookie) {
		this.playerUUID = playerUUID;
		this.playerName = playerName;
		this.playerNickname = playerNickname;
		this.playerSkinUrl = playerSkinUrl;
		this.playerRankName = playerRankName;
		this.playerRankLevel = playerRankLevel;
		this.playerExperience = playerExperience;
		this.playerCandy = playerCandy;
		this.playerCookie = playerCookie;
	}
	
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

	public Integer getPlayerExperience() {
		return playerExperience;
	}

	public void setPlayerExperience(Integer playerExperience) {
		this.playerExperience = playerExperience;
	}
	
	public long addPlayerExperience(long playerExperience) {
		return getPlayerExperience() + playerExperience;
	}
	
	public long removePlayerExperience(long playerExperience) {
		return getPlayerExperience() - playerExperience;
	}

	public String getPlayerSkinUrl() {
		return playerSkinUrl;
	}
}
