package org.rrtf.group.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.rrtf.group.entity.GroupChat;
import org.rrtf.group.entity.GroupType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
/*
 * 群组接口
 */
public interface GroupChatDao extends JpaRepository<GroupChat, Long>{
	/*
	 * 通过群组id查找到单个群组
	 */
	GroupChat findByGroupId(int groupid);
	@Transactional
	@Modifying
	@Query("delete from GroupChat where group_id=?1")
	/*
	 * 通过群组id删除群组
	 */
	int deleteByGroupid(int id);
	/*
	 * 通过群组种类查找群
	 */
	Page<GroupChat> findByGroupType(GroupType type,Pageable pageable);
	
	@Transactional
	@Modifying
	@Query("update GroupChat set group_name=?1,detail=?2,rule=?3 where group_id=?4")
	/*
	 * 修改群信息
	 */
	int modifyGroupNameAndGroupDetailAndRuleByGroupId(String GroupName,String detail,String rule,int id);
	
	@Query(value="select gc.* from group_chat gc where group_id in (select group_id from group_member where user_id = ?1)"
			+ " /*#pageable*/ ORDER BY group_id"
		, countQuery="select count(group_id) from group_member where user_id = ?1"
		, nativeQuery=true)
	Page<GroupChat> findByGroupIdIn(int userId,Pageable pageable);	
	
	@Query(value="select count(group_id) from group_member where user_id=?1 and group_id=?2", nativeQuery=true)
	int belongToGroup(int userId,int groupId);
	
	@Query(value="select count(user_id) from group_member where group_id=?1",nativeQuery=true)
	int countNum(int groupId);
	
	@Transactional
	@Modifying
	@Query(value="insert into group_member(user_id,group_id) value(?1,?2)",nativeQuery=true)
	int addIntoGroup(int userId,int groupId);
	
	@Transactional
	@Modifying
	@Query(value="delete from group_member where user_id=?1 and group_id=?2",nativeQuery=true)
	int delUser(int userId,int groupId);
	
	@Query(value="select * from group_chat where group_name like ?1 and build_time between ?2 and ?3"
			+ " /*#pageable*/ ORDER BY group_id"
			,countQuery="select count(group_id) from group_chat where group_name like ?1 and build_time between ?2 and ?3"
			, nativeQuery=true)
	Page<GroupChat> findByGroupNameAndTime(String name,String time,String time2,Pageable pageable);
	@Transactional
	@Modifying
	@Query(value="delete from group_chat where group_id in (?1)",nativeQuery=true)
	int delgroup(List<Integer> list);
	
}



