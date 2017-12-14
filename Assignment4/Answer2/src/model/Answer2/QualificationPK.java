package model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the qualification database table.
 * 
 */
@Embeddable
public class QualificationPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false)
	private int canRepair;

	@Column(insertable=false, updatable=false)
	private int canBeRepairedBy;

	public QualificationPK() {
	}
	public int getCanRepair() {
		return this.canRepair;
	}
	public void setCanRepair(int canRepair) {
		this.canRepair = canRepair;
	}
	public int getCanBeRepairedBy() {
		return this.canBeRepairedBy;
	}
	public void setCanBeRepairedBy(int canBeRepairedBy) {
		this.canBeRepairedBy = canBeRepairedBy;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof QualificationPK)) {
			return false;
		}
		QualificationPK castOther = (QualificationPK)other;
		return 
			(this.canRepair == castOther.canRepair)
			&& (this.canBeRepairedBy == castOther.canBeRepairedBy);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.canRepair;
		hash = hash * prime + this.canBeRepairedBy;
		
		return hash;
	}
}