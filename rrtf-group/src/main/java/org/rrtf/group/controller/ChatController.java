package org.rrtf.group.controller;

import java.sql.Time;
import java.util.List;

import org.rrtf.group.dao.ChatDao;
import org.rrtf.group.entity.Chat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/a")
public class ChatController {
	@Autowired
	ChatDao chatMapper;
	@RequestMapping("/aa")
	public List<Chat> asda() {
		Chat chat=new Chat();
		chat.setGourpId(111);
		chat.setCcontent("sfdaafaf");
		Time time=new Time(0);
		chat.setCtime(time);
		chat.setUserId(1111);
		chatMapper.save(chat);
		return chatMapper.findAll();
		
	}
}
