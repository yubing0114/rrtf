package org.neau.rrtf.Entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;


@Entity
public class Teacher implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private int teacherId;
	private String email;
	public int getUserId() {
		return userId;
	}

	public void setUser(int userId) {
		this.userId = userId;
	}

	private String edu;
	private String realname;
	private String tel;
	private Date birth;
//	@OneToOne(targetEntity = User.class)
//	@JoinColumn(name = "userId", referencedColumnName = "userId")
	private int userId;

	public Teacher() {

	}

	@Override
	public String toString() {
		return "Teacher [teacherId=" + teacherId + ", email=" + email + ", edu=" + edu + ", realname=" + realname
				+ ", tel=" + tel + ", birth=" + birth + ", user=" + userId + ", introduce=" + introduce + "]";
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

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	private String introduce;

}
