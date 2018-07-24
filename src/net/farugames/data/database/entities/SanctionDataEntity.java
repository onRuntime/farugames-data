package net.farugames.data.database.entities;

import net.farugames.api.bungee.sanctions.SanctionType;

import java.sql.Timestamp;
import java.util.UUID;

public class SanctionDataEntity {

	private UUID target;
	private UUID author;
	private SanctionType type;
	private String reason;
	private Timestamp dateStart;
	private Timestamp dateEnd;
	
	public SanctionDataEntity(UUID target, UUID author, SanctionType type, String reason, Timestamp dateStart, Timestamp dateEnd) {
		this.setTarget(target);
		this.setAuthor(author);
		this.setType(type);
		this.setReason(reason);
		this.setDateStart(dateStart);
		this.setDateEnd(dateEnd);
	}
	
	public UUID getTarget() {
		return target;
	}

	public void setTarget(UUID target) {
		this.target = target;
	}

	public UUID getAuthor() {
		return author;
	}

	public void setAuthor(UUID author) {
		this.author = author;
	}

	public SanctionType getType() {
		return type;
	}

	public void setType(SanctionType type) {
		this.type = type;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Timestamp getDateStart() {
		return dateStart;
	}

	public void setDateStart(Timestamp dateStart) {
		this.dateStart = dateStart;
	}

	public Timestamp getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(Timestamp dateEnd) {
		this.dateEnd = dateEnd;
	}
}
