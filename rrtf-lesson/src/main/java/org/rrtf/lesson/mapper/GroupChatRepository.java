package org.rrtf.lesson.mapper;

import java.util.List;

import org.rrtf.lesson.entity.GroupType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface GroupChatRepository extends JpaRepository<GroupType,Integer> {
	
	@Transactional
	@Query(value="select group_name from group_chat",nativeQuery=true)
	List<String> findGroupName();
}
