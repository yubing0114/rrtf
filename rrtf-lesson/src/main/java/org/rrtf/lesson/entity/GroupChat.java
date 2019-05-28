package org.rrtf.lesson.entity;

import java.security.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class GroupChat {

	@Id
	private int groupId;
	private int teacherId;
	private int typeId;
	private String groupName;
	private Timestamp build_time;
	private int groupMaster;
	private String detail;
	private String rule;
	public GroupChat() {}
	public int getGroupId() {
		return groupId;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	public int getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(int teacherId) {
		this.teacherId = teacherId;
	}
	public int getTypeId() {
		return typeId;
	}
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public Timestamp getBuild_time() {
		return build_time;
	}
	public void setBuild_time(Timestamp build_time) {
		this.build_time = build_time;
	}
	public int getGroupMaster() {
		return groupMaster;
	}
	public void setGroupMaster(int groupMaster) {
		this.groupMaster = groupMaster;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getRule() {
		return rule;
	}
	public void setRule(String rule) {
		this.rule = rule;
	}
	@Override
	public String toString() {
		return "GroupChat [groupId=" + groupId + ", teacherId=" + teacherId + ", typeId=" + typeId + ", groupName="
				+ groupName + ", build_time=" + build_time + ", groupMaster=" + groupMaster + ", detail=" + detail
				+ ", rule=" + rule + "]";
	}
}
