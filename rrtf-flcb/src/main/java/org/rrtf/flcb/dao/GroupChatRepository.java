package org.rrtf.flcb.dao;

import org.rrtf.flcb.entity.GroupChat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupChatRepository extends JpaRepository<GroupChat, Long> {
	GroupChat findByGroupId(int groupId);
}