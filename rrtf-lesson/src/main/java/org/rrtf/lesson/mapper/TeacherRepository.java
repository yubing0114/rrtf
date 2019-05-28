package org.rrtf.lesson.mapper;

import java.util.List;

import org.rrtf.lesson.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface TeacherRepository extends JpaRepository<Teacher,Integer> {
	//查找所有老师的姓名
	@Transactional
	@Query(value="select realname from teacher",nativeQuery=true)
	List<String> findTeacherNames();
	//通过teacherId查找某一个老师
	Teacher findByTeacherId(int teacherId);
	//通过userId查找某一个老师
	Teacher findByUserId(int userId);
	//通过teacherId查找教师的userId
	@Transactional
	@Query(value="select user_id from teacher where teacher_id=?1",nativeQuery=true)
	int findUserIdByTeacherId(int userId);
}
