package org.rrtf.flcb.entity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Member {
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)//自动生成主键
	private int memberId;
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="userId")
	private User user;
	
	private int gradeId;
	private String email;
	private String sex;
	private String telephone;
	private Date birthday;
	private String signature;
	private String realname;

	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public int getMemberId() {
		return memberId;
	}
	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}
	
	public int getGradeId() {
		return gradeId;
	}
	public void setGradeId(int gradeId) {
		this.gradeId = gradeId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@Override
	public String toString() {
		return "Member [memberId=" + memberId + ", user=" + user + ", gradeId=" + gradeId + ", email=" + email
				+ ", sex=" + sex + ", telephone=" + telephone + ", birthday=" + birthday + ", signature=" + signature
				+ ", realname=" + realname + "]";
	}

	
}
