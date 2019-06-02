package org.rrtf.flcb.dao;


import org.rrtf.flcb.entity.Teacher;
import org.rrtf.flcb.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
	Teacher findByUser(User user);
}
