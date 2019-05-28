package org.rrtf.lesson.mapper;

import java.util.List;

import org.rrtf.lesson.entity.LessonData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface LessonDataRepository extends JpaRepository<LessonData,Integer> {
	List<LessonData> findByLessonId(int lessonId);
	//查找对应文件名称
	@Transactional
	@Query(value="select file_name from lesson_data where data_name=?1",nativeQuery=true)
	String findDataName(String dataName);
}
