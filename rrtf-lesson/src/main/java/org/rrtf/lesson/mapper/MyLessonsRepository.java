package org.rrtf.lesson.mapper;

import java.util.List;

import org.rrtf.lesson.entity.MyLessons;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface MyLessonsRepository extends JpaRepository<MyLessons,Integer> {
	//在中间表查找我报名的课程
	@Transactional
	@Query(value="select lesson_id from my_lessons where member_id=?1",nativeQuery=true)
	List<Integer> findMyLessons(int userId);
}
