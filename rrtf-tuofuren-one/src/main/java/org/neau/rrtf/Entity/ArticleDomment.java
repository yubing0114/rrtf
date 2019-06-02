package org.neau.rrtf.Entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class ArticleDomment implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int cid;
	private String ccontent;
	private Timestamp ctime;
	private int userId;
	private int articleId;

	@Override
	public String toString() {
		return "articleDomment [id=" + cid + ", ccontent=" + ccontent + ", ctime=" + ctime + ", userId=" + userId
				+ ", articleId=" + articleId + "]";
	}

	public ArticleDomment() {
		
	}

	public ArticleDomment(int cid, String ccontent, Timestamp ctime, int userId, int articleId) {
		super();
		this.cid = cid;
		this.ccontent = ccontent;
		this.ctime = ctime;
		this.userId = userId;
		this.articleId = articleId;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public String getCcontent() {
		return ccontent;
	}

	public void setCcontent(String ccontent) {
		this.ccontent = ccontent;
	}

	public Timestamp getCtime() {
		return ctime;
	}

	public void setCtime(Timestamp ctime) {
		this.ctime = ctime;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getArticleId() {
		return articleId;
	}

	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}
}