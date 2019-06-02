package org.rrtf.group.entity;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
/*
 *聊天群组类
 */
@Entity 
public class GroupChat {
	@Id
	//@GeneratedValue(strategy= GenerationType.AUTO)//自动生成主键
	private int groupId;//群组id
	private String groupName;//群组名称
	private Timestamp buildTime;//创建时间
	private int groupMaster;//群主id
	private String detail;//简介
	private String rule;//规则
	private int status;//仅用于测试，表示用户加入群与否的状态
	private int members;//仅用于统计人数，数据库默认为空
//	@ManyToMany(fetch=FetchType.LAZY)
//	@JoinTable(name="GroupMember",joinColumns=@JoinColumn(name="groupId"),inverseJoinColumns=@JoinColumn(name="userId"))
//	private List<User> userList;
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="typeId")
	private GroupType groupType;
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="teacherId")
	private Teacher teacher;
//	public List<User> getUserList() {
//		return userList;
//	}
//	public void setUserList(List<User> userList) {
//		this.userList = userList;
//	}
	public int getGroupId() {
		return groupId;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
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
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getMembers() {
		return members;
	}
	public void setMembers(int members) {
		this.members = members;
	}
	
	public GroupType getGroupType() {
		return groupType;
	}
	public void setGroupType(GroupType groupType) {
		this.groupType = groupType;
	}
	
	public Teacher getTeacher() {
		return teacher;
	}
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	@Override
	public String toString() {
		return "GroupChat [groupId=" + groupId + ", groupName="
				+ groupName + ", buildTime=" + buildTime + ", groupMaster=" + groupMaster + ", detail=" + detail
				+ ", rule=" + rule + ", status=" + status + ", members=" + members + ", userList=" + "]";
	}
	

	
}
