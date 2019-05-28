package org.rrtf.lesson.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class LessonData {//课程资料表

	@Id
	@GeneratedValue
	private int dataId;
	private String dataName;//资料名(给人看的
	private String fileName;//存储时的文件名(给系统看的
	private int lessonId;
	public LessonData() {}
	public int getDataId() {
		return dataId;
	}
	public void setDataId(int dataId) {
		this.dataId = dataId;
	}
	public String getDataName() {
		return dataName;
	}
	public void setDataName(String dataName) {
		this.dataName = dataName;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public int getLessonId() {
		return lessonId;
	}
	public void setLessonId(int lessonId) {
		this.lessonId = lessonId;
	}
	@Override
	public String toString() {
		return "LessonData [dataId=" + dataId + ", dataName=" + dataName + ", fileName=" + fileName + ", lessonId="
				+ lessonId + "]";
	}
}
