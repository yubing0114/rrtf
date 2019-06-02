package org.rrtf.flcb.entity;

import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

@Entity
public class GroupChat {
	@Id
	private int groupId;
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="teacherId")
	private Teacher teacher;
	
	//private int teacherId;
	
	private int typeId;
	
	private String groupName;
	private Timestamp buildTime;
	private int groupMaster;
	private String detail;
	private String rule;
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="groupMember", joinColumns=@JoinColumn(name="groupId"),
			inverseJoinColumns=@JoinColumn(name="userId"))
	private Set<User> user;
	
	public GroupChat() {
	}
	public GroupChat(int groupId, Teacher teacher, int teacherId, int typeId, String groupName, Timestamp buildTime,
			int groupMaster, String detail, String rule) {
		this.groupId = groupId;
		this.teacher = teacher;
		//this.teacherId = teacherId;
		this.typeId = typeId;
		this.groupName = groupName;
		this.buildTime = buildTime;
		this.groupMaster = groupMaster;
		this.detail = detail;
		this.rule = rule;
	}
	public int getGroupId() {
		return groupId;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	public Set<User> getUser() {
		return user;
	}
	public void setUser(Set<User> user) {
		this.user = user;
	}
	public Teacher getTeacher() {
		return teacher;
	}
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
//	public int getTeacherId() {
//		return teacherId;
//	}
//	public void setTeacherId(int teacherId) {
//		this.teacherId = teacherId;
//	}
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
	public Timestamp getBuildTime() {
		return buildTime;
	}
	public void setBuildTime(Timestamp buildTime) {
		this.buildTime = buildTime;
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
		return "GroupChat [groupId=" + groupId + ", teacher=" + teacher + ", typeId=" + typeId + ", groupName="
				+ groupName + ", buildTime=" + buildTime + ", groupMaster=" + groupMaster + ", detail=" + detail
				+ ", rule=" + rule + ", user=" + user + "]";
	}

	
	
}
