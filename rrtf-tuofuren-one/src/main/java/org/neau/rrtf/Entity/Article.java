package org.neau.rrtf.Entity;

import java.io.Serializable;
import java.sql.Timestamp;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Article implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int articleId;

	private String articlePicture;
	private String articleTitle;
	private String articleType;
	private String articleDetail;
	private Timestamp regtime;
	private int status;
	private int pageview;

	public int getPageview() {
		return pageview;
	}

	public void setPageview(int pageview) {
		this.pageview = pageview;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Timestamp getRegtime() {
		return regtime;
	}

	public void setRegtime(Timestamp regtime) {
		this.regtime = regtime;
	}

	// @ManyToOne(targetEntity = Teacher.class)
	// @JoinColumn(name = "teacherId",referencedColumnName="teacherId")
	private int teacherId;

	public int getArticleId() {
		return articleId;
	}

	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}

	public String getArticlePicture() {
		return articlePicture;
	}

	public void setArticlePicture(String articlePicture) {
		this.articlePicture = articlePicture;
	}

	public String getArticleTitle() {
		return articleTitle;
	}

	public void setArticleTitle(String articleTitle) {
		this.articleTitle = articleTitle;
	}

	public String getArticleType() {
		return articleType;
	}

	public void setArticleType(String articleType) {
		this.articleType = articleType;
	}

	public String getArticleDetail() {
		return articleDetail;
	}

	public void setArticleDetail(String articleDetail) {
		this.articleDetail = articleDetail;
	}

	public int getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(int teacherId) {
		this.teacherId = teacherId;
	}

	public Article() {
	}

	@Override
	public String toString() {
		return "Article [articleId=" + articleId + ", articlePicture=" + articlePicture + ", articleTitle="
				+ articleTitle + ", articleType=" + articleType + ", articleDetail=" + articleDetail + ", regtime="
				+ regtime + ", status=" + status + ", pageview=" + pageview + ", teacherId=" + teacherId + "]";
	}


}
