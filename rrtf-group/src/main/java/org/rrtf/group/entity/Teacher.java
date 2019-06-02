package org.rrtf.group.entity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
/*
 * 教师类
 */
@Entity
public class Teacher {
	@Id
	//@GeneratedValue(strategy= GenerationType.AUTO)//自动生成主键
	private int teacherId;//教师id
	private int userId;//用户id
	private String email;//教师邮箱
	private String edu;//教师学历
	private String realname;//教师真实姓名
	private String tel;//教师电话
	private Date birth;//教师生日
	private String introduce;//教师简介
	public int getTeacherId() {
		return teacherId;
	}
	public Teacher() {
	}
	public void setTeacherId(int teacherId) {
		this.teacherId = teacherId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEdu() {
		return edu;
	}
	public void setEdu(String edu) {
		this.edu = edu;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public Date getBirth() {
		return birth;
	}
	public void setBirth(Date birth) {
		this.birth = birth;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	@Override
	public String toString() {
		return "Teacher [teacherId=" + teacherId + ", userId=" + userId + ", email=" + email + ", edu=" + edu
				+ ", realname=" + realname + ", tel=" + tel + ", birth=" + birth + ", introduce=" + introduce + "]";
	}
	
}
