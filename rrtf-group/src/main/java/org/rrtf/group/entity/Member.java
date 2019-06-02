package org.rrtf.group.entity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
/*
 * 成员信息类
 */
@Entity
public class Member {
	@Id
	//@GeneratedValue(strategy= GenerationType.AUTO)//自动生成主键
	private int memberId;//用户id
	private int gradeId;//等级id
	private String username;
	private String email;//成员邮箱
	private String sex;//成员性别
	private String telephone;
	private Date birthday;
	private String signature;
	public Member() {}
}
