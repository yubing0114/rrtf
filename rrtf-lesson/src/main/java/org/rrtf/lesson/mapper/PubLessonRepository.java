package org.rrtf.lesson.mapper;

import java.sql.Timestamp;
import java.util.List;

import org.rrtf.lesson.entity.PubLesson;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface PubLessonRepository extends JpaRepository<PubLesson,Integer> {
	//查找某一门课程
	PubLesson findByLessonIdAndLessonStatus(int pubLesson,int lessonStatus);
	//分页查找某一位老师的课程
	Page<PubLesson> findByTeacherIdAndLessonStatus(int teacherId,Pageable pageable,int lessonStatus);
	//分页查找我的公开课
	Page<PubLesson> findByLessonIdInAndLessonStatus(List<Integer> lessonIdList,Pageable pageable,int lessonStatus);
	//查找未开始的公开课
	List<PubLesson> findByDateAfterAndLessonStatus(Timestamp date,int lessonStatus);
	//修改公开课(用传入id后save的方法代替了
	/*@Transactional
	@Modifying//只需要加在update和delete sql注解前
	@Query(value="update pub_lesson set password=?1,status=?2 where username=?3",nativeQuery=true)
	int modifyPubLessonByLessonId(int LessonId);*/
	//删除公开课(jpa本身就有根据id删除的方法delete
	/*@Transactional
	@Modifying//只需要加在update和delete sql注解前
	@Query("delete from pub_lesson where lesson_id=?1")
	int deleteByLessonId(int lessonId);*/
	//查找前三个最新的公开课(用于首页)
	Page<PubLesson> findByDateAfterAndLessonStatus(Timestamp ts,Pageable pageable,int lessonStatus);
	//名称,讲师,分类多条件模糊查询,分页
	Page<PubLesson> findByLessonNameContainingAndTeacherContainingAndLessonTypeContaining(String lessonName,String teacher,String lessonType,Pageable pageable);
	//管理员关闭与管理员恢复功能
	@Transactional
	@Modifying//只需要加在update和delete sql注解前
	@Query(value="update pub_lesson set lesson_status=?1 where lesson_id in (?2)",nativeQuery=true)
	int adminStatus(int lessonStatus,List<Integer> lessonId);
	//管理员删除功能
	@Transactional
	@Modifying//只需要加在update和delete sql注解前
	@Query(value="delete from pub_lesson where lesson_id in (?1)", nativeQuery=true)
	int adminDel(List<Integer> lessonId);
	//购买人数加一
	@Transactional
	@Modifying//只需要加在update和delete sql注解前
	@Query(value="update pub_lesson set les_stu_num=les_stu_num+1 where lesson_id=?1 and lesson_status=1", nativeQuery=true)
	int lesStuNumAdd(int lessonId);
}
