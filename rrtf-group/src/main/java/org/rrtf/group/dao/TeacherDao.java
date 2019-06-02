package org.rrtf.group.dao;

import org.rrtf.group.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherDao extends JpaRepository<Teacher, Long>{
	Teacher findByTeacherId(int teacherId);
}
