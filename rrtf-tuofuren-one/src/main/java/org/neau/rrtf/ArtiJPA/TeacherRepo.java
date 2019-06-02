package org.neau.rrtf.ArtiJPA;



import org.neau.rrtf.Entity.Teacher;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepo extends JpaRepository<Teacher, Integer> {
	
	Teacher findByUserId(Integer id);

	Teacher findByTeacherId(int teacherId);
	
	Teacher findByRealname(String realname);

}
