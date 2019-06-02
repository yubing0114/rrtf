package org.rrtf.flcb.dao;

import java.util.List;

import org.rrtf.flcb.entity.Flcbkcxxb;
import org.rrtf.flcb.entity.Teacher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface FlcbkcxxbRepository extends JpaRepository<Flcbkcxxb, Long> {
	
	Flcbkcxxb findByLessonId(int lessonId);//通过课程ID查询课程内容
	
	@Transactional
	@Modifying//只需要加在update和delete sql注解前
	@Query("delete from Flcbkcxxb where lessonId=?1")
	int deleteByLessionId(int lessonId);//通过ID删除课程

	
	@Transactional
	@Modifying//只需要加在update和delete sql注解前
	@Query("update Flcbkcxxb set teacher_id=?1,"
			+ " group_id=?2, lesson_picture=?3, teaching_outline=?4,"
			+ " teaching_way=?5 where lesson_id=?6")
	int modifyFlcbkcxxbByLessionId1(int teacherId, int groupId, String lessonPicture, String teachingOutline,
	String teachingWay, int lessonId);//通过ID修改福利城堡属性
	
	@Transactional
	@Modifying//只需要加在update和delete sql注解前
	@Query("update Flcbkcxxb set status=?1 where lesson_id=?2")
	int modifyFlcbkcxxbByLessionId2(int status, int lessonId);//通过ID修改福利城堡属性
	
//	@Transactional
//	@Modifying//只需要加在update和delete sql注解前
//	@Query(value = "select * from Flcbkcxxb where activity_type=?1 and lesson_type=?2 ORDER BY ?#{#pageable}",
//			countQuery = "SELECT count(*) FROM Flcbkcxxb where activity_type=?1 and lesson_type=?2",
//		    nativeQuery = true)
//	Page<Flcbkcxxb> findByActivityTypeAndLessonType(String activityType, String lessonType, Pageable pageable);
	
	List<Flcbkcxxb> findByLessonType(String lessonType);//通过课程类型查询信息
	
	List<Flcbkcxxb> findByActivityType(String activityType);//通过课程类型查询信息
	
	List<Flcbkcxxb> findByActivityTypeAndLessonType(String activityType, String lessonType);
	
	List<Flcbkcxxb> findByTeacher(Teacher teacher);
	
	List<Flcbkcxxb> findByLessonTitleLike(String lessonTitle);//模糊查询
}
