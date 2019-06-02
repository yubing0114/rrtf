package org.rrtf.group.entity;

import java.sql.Time;

import javax.persistence.Entity;
import javax.persistence.Id;

/*
 * 聊天记录
 */
@Entity
public class Chat {
	@Id
	//@GeneratedValue(strategy= GenerationType.AUTO)//自动生成主键
	private int cId;//聊天id
	private int gourpId;//群组id
	private int userId;//发布者id
	private String ccontent;//聊天内容
	private Time ctime;//发布时间
	
	public Chat() {
	}

	public int getcId() {
		return cId;
	}

	public void setcId(int cId) {
		this.cId = cId;
	}

	public int getGourpId() {
		return gourpId;
	}

	public void setGourpId(int gourpId) {
		this.gourpId = gourpId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getCcontent() {
		return ccontent;
	}

	public void setCcontent(String ccontent) {
		this.ccontent = ccontent;
	}

	public Time getCtime() {
		return ctime;
	}

	public void setCtime(Time ctime) {
		this.ctime = ctime;
	}

	@Override
	public String toString() {
		return "Chat [cId=" + cId + ", gourpId=" + gourpId + ", userId=" + userId + ", ccontent=" + ccontent
				+ ", ctime=" + ctime + "]";
	}
	
	
}
