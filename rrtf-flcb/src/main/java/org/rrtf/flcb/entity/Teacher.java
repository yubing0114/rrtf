package org.rrtf.flcb.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
@Entity
public class Teacher {
	@Id
	private int teacherId;
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="userId")
	private User user;
	private String email;
	private String edu;
	private String realname;
	private String tel;
	private Date birth;
	private String intruduce;
	
	public Teacher() {
	}

	

	public User getUser() {
		return user;
	}



	public void setUser(User user) {
		this.user = user;
	}



	public int getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(int teacherId) {
		this.teacherId = teacherId;
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

	public String getIntruduce() {
		return intruduce;
	}

	public void setIntruduce(String intruduce) {
		this.intruduce = intruduce;
	}



	@Override
	public String toString() {
		return "Teacher [teacherId=" + teacherId + ", user=" + user + ", email=" + email + ", edu=" + edu
				+ ", realname=" + realname + ", tel=" + tel + ", birth=" + birth + ", intruduce=" + intruduce + "]";
	}

	
}
