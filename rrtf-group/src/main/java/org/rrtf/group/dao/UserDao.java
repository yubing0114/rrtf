package org.rrtf.group.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.rrtf.group.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface UserDao extends JpaRepository<User, Long>{
	User findByUserId(int id);
	@Query(value="select * from user where user_id in (select user_id from group_member where group_id=?1)",
			nativeQuery=true)
	List<User> findalluser(int groupId);
	
	@Transactional
	@Modifying
	@Query(value="delete from group_member where group_id=?2 and user_id in (?1)",nativeQuery=true)
	int admindel(List<Integer> list,int groupId);
}
