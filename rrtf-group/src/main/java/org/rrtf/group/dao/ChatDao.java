package org.rrtf.group.dao;

import org.rrtf.group.entity.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

/*
 * 聊天记录的接口
 */
public interface ChatDao extends JpaRepository<Chat, Long>{
	
}
