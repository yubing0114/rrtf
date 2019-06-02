package org.rrtf.flcb.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;

@Entity
public class Comment {
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)//自动生成主键
	private int commentId;
	private String commentContent;
	private Timestamp regtime;
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinTable(name="comMem", joinColumns=@JoinColumn(name="commentId"),
	inverseJoinColumns=@JoinColumn(name="memberId"))
	private Member members;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinTable(name="flcbComment", joinColumns=@JoinColumn(name="commentId"),
			inverseJoinColumns=@JoinColumn(name="lessonId"))
	private Flcbkcxxb flcbs;
	
	public Comment() {
	}



	public Flcbkcxxb getFlcbs() {
		return flcbs;
	}



	public void setFlcbs(Flcbkcxxb flcbs) {
		this.flcbs = flcbs;
	}



	public Member getMembers() {
		return members;
	}

	public void setMembers(Member members) {
		this.members = members;
	}

	public Timestamp getRegtime() {
		return regtime;
	}
	public void setRegtime(Timestamp regtime) {
		this.regtime = regtime;
	}
	public int getCommentId() {
		return commentId;
	}
	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}

	public String getCommentContent() {
		return commentContent;
	}
	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}



	@Override
	public String toString() {
		return "Comment [commentId=" + commentId + ", commentContent=" + commentContent + ", regtime=" + regtime
				+ ", members=" + members + ", flcbs=" + flcbs + "]";
	}




}
