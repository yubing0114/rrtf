package org.rrtf.group.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
/*
 * 群类别
 */
@Entity
public class GroupType {
	@Id
	//@GeneratedValue(strategy= GenerationType.AUTO)//自动生成主键
	private int typeId;//群种类id
	private String typeName;//群类别名
	public int getTypeId() {
		return typeId;
	}
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}
	public String getTypeName() {
		return typeName;
	}
	public GroupType() {
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	@Override
	public String toString() {
		return "GroupType [typeId=" + typeId + ", typeName=" + typeName + "]";
	}
	
}
