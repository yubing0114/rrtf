package org.rrtf.group.dao;

import org.rrtf.group.entity.GroupType;
import org.springframework.data.jpa.repository.*;
import org.springframework.transaction.annotation.Transactional;
/*
 * 编辑群种类
 */
public interface GroupTypeDao extends JpaRepository<GroupType, Long>{
	@Transactional//管理事务
	@Modifying//只需要加在update和delete sql注解前
	@Query("update GroupType set type_name=?1 where type_id=?2")
	/*
	 * 通过群种类id修改群种类
	 */
	int modifyTypenameByTypeid(String typename,int id);
	@Transactional//管理事务
	@Modifying//只需要加在update和delete sql注解前
	@Query("delete from GroupType where type_id=?1")
	/*
	 * 通过群种类id删除群种类
	 */
	int deleteByTypeid(int id);
	GroupType findByTypeId(int id);
}
