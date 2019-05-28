package org.rrtf.lesson.controller;

import java.util.List;

import org.rrtf.lesson.mapper.GroupChatRepository;
import org.rrtf.lesson.mapper.GroupTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/groupChat")
public class GroupChatController {
	
	@Autowired
	GroupChatRepository groupChatRepository;

	@RequestMapping("/groupName")//查找组名:localhost:8080/groupChat/groupName
	public List<String> groupName() {
		return groupChatRepository.findGroupName();
	}
}
