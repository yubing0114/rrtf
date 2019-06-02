package org.rrtf.flcb.controller;

import java.sql.Date;
import java.sql.Timestamp;

import org.rrtf.flcb.dao.GroupChatRepository;
import org.rrtf.flcb.dao.TeacherRepository;
import org.rrtf.flcb.dao.UserRepository;
import org.rrtf.flcb.entity.GroupChat;
import org.rrtf.flcb.entity.Teacher;
import org.rrtf.flcb.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/group")
public class GroupController {
	
	@Autowired
	GroupChatRepository groupRepo;
	@Autowired
	TeacherRepository teacherRepo;
	@Autowired
	UserRepository userRepo;
	
	@RequestMapping("/add")
	public void insertGroup() {
		Teacher teacher = new Teacher();
		User user = userRepo.findByUserId(2);
		teacher.setTeacherId(2);
		teacher.setUser(user);
		teacher.setEmail("120179118@qq.com");
		teacher.setEdu("本科");
		teacher.setRealname("杨逍");
		teacher.setTel("123456");
		teacher.setBirth(new Date(2019, 5, 7));
		teacher.setIntruduce("掌握乾坤大挪移");
		teacherRepo.save(teacher);
		
		GroupChat group = new GroupChat();
		group.setGroupId(2);
		group.setTypeId(2);
		group.setGroupName("桃花岛");
		group.setBuildTime(new Timestamp(System.currentTimeMillis()));
		group.setGroupMaster(3);
		group.setDetail("大发放");
		group.setRule("方法所发生的");
		group.setTeacher(teacher);
		
		groupRepo.save(group);
		
	}
	
}
