package org.rrtf.lesson.mapper;

import org.rrtf.lesson.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepository extends JpaRepository<User,Integer> {
	//通过userId查找用户头像
	@Transactional
	@Query(value="select picture from user where user_id=?1",nativeQuery=true)
	String findPictureByUserId(int userId);
}
