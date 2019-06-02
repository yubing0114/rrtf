package org.rrtf.flcb.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class User {
	@Id
	private int userId;
	private String username;
	private String password;
	private String salt;
	private String name;
	private Timestamp regtime;
	private String picture;
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Timestamp getRegtime() {
		return regtime;
	}
	public void setRegtime(Timestamp regtime) {
		this.regtime = regtime;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", username=" + username + ", password=" + password + ", salt=" + salt
				+ ", name=" + name + ", regtime=" + regtime + ", picture=" + picture + "]";
	}
	
	
}
