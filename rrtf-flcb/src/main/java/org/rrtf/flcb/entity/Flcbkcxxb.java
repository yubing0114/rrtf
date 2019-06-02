package org.rrtf.flcb.entity;

import java.util.List;
import java.util.Set;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;



@Entity
public class Flcbkcxxb {
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)//自动生成主键
	private int lessonId;
	private String lessonTitle;
	private Date startDate;
	private Date endDate;
	private String dayOfWeek;
//	private Time startTime;
//	private Time endTime;
	private String startTime;
	private String endTime;
	private String studentType;
	private int lessonPrice;
	private String activityType;
	private String lessonType;
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="teacherId")
	private Teacher teacher;

	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="groupId")
	private GroupChat groupChat;
	
	private int talkId;
	private String lessonPicture;
	private String teachingOutline;
	private String teachingWay;
	private int status;
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="flcbFollow", joinColumns=@JoinColumn(name="lessonId"),
			inverseJoinColumns=@JoinColumn(name="memberId"))
	private List<Member> members;
	
	@OneToMany(fetch=FetchType.EAGER)
	@JoinTable(name="flcbComment", joinColumns=@JoinColumn(name="lessonId"),
			inverseJoinColumns=@JoinColumn(name="commentId"))
	private Set<Comment> comments;
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="flcbJoin", joinColumns=@JoinColumn(name="lessonId"),
			inverseJoinColumns=@JoinColumn(name="memberId"))
	private Set<Member> joinMembers;
	
	@OneToMany(fetch=FetchType.EAGER)
	@JoinTable(name="flcbData", joinColumns=@JoinColumn(name="lessonId"),
			inverseJoinColumns=@JoinColumn(name="dataId"))
	private Set<Fdata> datas;
	
	public Flcbkcxxb() {}
	public int getLessonId() {
		return lessonId;
	}
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Set<Member> getJoinMembers() {
		return joinMembers;
	}
	public void setJoinMembers(Set<Member> joinMembers) {
		this.joinMembers = joinMembers;
	}
	public Set<Comment> getComments() {
		return comments;
	}
	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}
	public List<Member> getMembers() {
		return members;
	}
	public void setMembers(List<Member> members) {
		this.members = members;
	}
	public void setLessonId(int lessonId) {
		this.lessonId = lessonId;
	}
	public String getLessonTitle() {
		return lessonTitle;
	}
	public void setLessonTitle(String lessonTitle) {
		this.lessonTitle = lessonTitle;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getDayOfWeek() {
		return dayOfWeek;
	}
	public void setDayOfWeek(String dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getStudentType() {
		return studentType;
	}
	public void setStudentType(String studentType) {
		this.studentType = studentType;
	}
	public int getLessonPrice() {
		return lessonPrice;
	}
	public void setLessonPrice(int lessonPrice) {
		this.lessonPrice = lessonPrice;
	}
	public String getActivityType() {
		return activityType;
	}
	public void setActivityType(String activityType) {
		this.activityType = activityType;
	}
	public String getLessonType() {
		return lessonType;
	}
	public void setLessonType(String lessonType) {
		this.lessonType = lessonType;
	}
	public Teacher getTeacher() {
		return teacher;
	}
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	public GroupChat getGroupChat() {
		return groupChat;
	}
	public void setGroupChat(GroupChat groupChat) {
		this.groupChat = groupChat;
	}
	public int getTalkId() {
		return talkId;
	}
	public void setTalkId(int talkId) {
		this.talkId = talkId;
	}
	public String getLessonPicture() {
		return lessonPicture;
	}
	public void setLessonPicture(String lessonPicture) {
		this.lessonPicture = lessonPicture;
	}
	public String getTeachingOutline() {
		return teachingOutline;
	}
	public void setTeachingOutline(String teachingOutline) {
		this.teachingOutline = teachingOutline;
	}
	public String getTeachingWay() {
		return teachingWay;
	}
	public void setTeachingWay(String teachingWay) {
		this.teachingWay = teachingWay;
	}
	public Set<Fdata> getDatas() {
		return datas;
	}
	public void setDatas(Set<Fdata> datas) {
		this.datas = datas;
	}
	@Override
	public String toString() {
		return "Flcbkcxxb [lessonId=" + lessonId + ", lessonTitle=" + lessonTitle + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", dayOfWeek=" + dayOfWeek + ", startTime=" + startTime + ", endTime="
				+ endTime + ", studentType=" + studentType + ", lessonPrice=" + lessonPrice + ", activityType="
				+ activityType + ", lessonType=" + lessonType + ", teacher=" + teacher + ", groupChat=" + groupChat
				+ ", talkId=" + talkId + ", lessonPicture=" + lessonPicture + ", teachingOutline=" + teachingOutline
				+ ", teachingWay=" + teachingWay + ", status=" + status + ", members=" + members + ", comments="
				+ comments + ", joinMembers=" + joinMembers + ", datas=" + datas + "]";
	}

	



}
