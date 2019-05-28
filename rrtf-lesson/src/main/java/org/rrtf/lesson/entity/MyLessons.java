package org.rrtf.lesson.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MyLessons {//中间表,表示收藏的课程(非老师)

	@Id
	private int lessonId;
	private int userId;
	public MyLessons() {}
	public int getLessonId() {
		return lessonId;
	}
	public void setLessonId(int lessonId) {
		this.lessonId = lessonId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	@Override
	public String toString() {
		return "MyLesson [lessonId=" + lessonId + ", userId=" + userId + "]";
	}
}
