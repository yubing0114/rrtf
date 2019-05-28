package org.rrtf.lesson.entity;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class PubLesson {

	@Id
	@GeneratedValue
	private int lessonId;//课程Id
	private int teacherId;//教师Id
	private int groupId;//群Id
	private String lessonName;//课程标题
	private Timestamp date;//上课日期
	private String beginTime;//开始时间
	private String endTime;//结束时间
	private Date uploadTime;//发布时间
	private String suitStu;//适合学员
	private String lessonType;//课程分类
	private String teacher;//课程讲师
	private String lessonGroup;//课程群
	private String lessonIntro;//课程安排
	private String lessonWay;//上课方式
	private String lessonGuide;//听课指南
	private int lessonStatus;//课程状态
	private int lesStuNum;//报名人数
	private float lessonMoney;//课程价格
	private String lessonPicture;//宣传图片
	public PubLesson() {}
	public int getLessonId() {
		return lessonId;
	}
	public void setLessonId(int lessonId) {
		this.lessonId = lessonId;
	}
	public int getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(int teacherId) {
		this.teacherId = teacherId;
	}
	public int getGroupId() {
		return groupId;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	public String getLessonName() {
		return lessonName;
	}
	public void setLessonName(String lessonName) {
		this.lessonName = lessonName;
	}
	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp date) {
		this.date = date;
	}
	public String getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public Date getUploadTime() {
		return uploadTime;
	}
	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}
	public String getSuitStu() {
		return suitStu;
	}
	public void setSuitStu(String suitStu) {
		this.suitStu = suitStu;
	}
	public String getLessonType() {
		return lessonType;
	}
	public void setLessonType(String lessonType) {
		this.lessonType = lessonType;
	}
	public String getTeacher() {
		return teacher;
	}
	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}
	public String getLessonGroup() {
		return lessonGroup;
	}
	public void setLessonGroup(String lessonGroup) {
		this.lessonGroup = lessonGroup;
	}
	public String getLessonIntro() {
		return lessonIntro;
	}
	public void setLessonIntro(String lessonIntro) {
		this.lessonIntro = lessonIntro;
	}
	public String getLessonWay() {
		return lessonWay;
	}
	public void setLessonWay(String lessonWay) {
		this.lessonWay = lessonWay;
	}
	public String getLessonGuide() {
		return lessonGuide;
	}
	public void setLessonGuide(String lessonGuide) {
		this.lessonGuide = lessonGuide;
	}
	public int getLessonStatus() {
		return lessonStatus;
	}
	public void setLessonStatus(int lessonStatus) {
		this.lessonStatus = lessonStatus;
	}
	public int getLesStuNum() {
		return lesStuNum;
	}
	public void setLesStuNum(int lesStuNum) {
		this.lesStuNum = lesStuNum;
	}
	public float getLessonMoney() {
		return lessonMoney;
	}
	public void setLessonMoney(float lessonMoney) {
		this.lessonMoney = lessonMoney;
	}
	public String getLessonPicture() {
		return lessonPicture;
	}
	public void setLessonPicture(String lessonPicture) {
		this.lessonPicture = lessonPicture;
	}
	@Override
	public String toString() {
		return "PubLesson [lessonId=" + lessonId + ", teacherId=" + teacherId + ", groupId=" + groupId + ", lessonName="
				+ lessonName + ", date=" + date + ", beginTime=" + beginTime + ", endTime=" + endTime + ", uploadTime="
				+ uploadTime + ", suitStu=" + suitStu + ", lessonType=" + lessonType + ", teacher=" + teacher
				+ ", lessonGroup=" + lessonGroup + ", lessonIntro=" + lessonIntro + ", lessonWay=" + lessonWay
				+ ", lessonGuide=" + lessonGuide + ", lessonDatum=" + lessonStatus + ", lesStuNum=" + lesStuNum
				+ ", lessonMoney=" + lessonMoney + ", lessonPicture=" + lessonPicture + "]";
	}
}
