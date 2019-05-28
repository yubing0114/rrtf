package org.rrtf.lesson.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class GroupType {
	
	@Id
	private int typeId;
	private String typeName;
	public GroupType() {}
	public int getTypeId() {
		return typeId;
	}
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	@Override
	public String toString() {
		return "GroupType [typeId=" + typeId + ", typeName=" + typeName + "]";
	}
}
