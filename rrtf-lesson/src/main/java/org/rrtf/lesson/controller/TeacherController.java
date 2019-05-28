package org.rrtf.lesson.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.rrtf.lesson.entity.Teacher;
import org.rrtf.lesson.mapper.TeacherRepository;
import org.rrtf.lesson.mapper.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teacher")
public class TeacherController {

	@Autowired
	TeacherRepository teacherRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@RequestMapping("/teachersName")//查找所有教师姓名:localhost:8080/teacher/teachersName
	public List<String> teacher() {
		return teacherRepository.findTeacherNames();
	}
	
	//应该是被废弃了的
	@RequestMapping("/showTeacher")//显示老师信息(不含头像):localhost:8080/teacher/showTeacher?teacherId=1
	public Teacher showTeacher(int teacherId) {
		return teacherRepository.findByTeacherId(teacherId);
	}
	
	@RequestMapping("/showTeacherAndPicture")//显示老师信息(含头像):localhost:8080/teacher/showTeacher?teacherId=1
	public Map<String,Object> showTeacherAndPicture(int teacherId) {
		Map<String,Object> map = new HashMap<String, Object>();
		Teacher teacher = teacherRepository.findByTeacherId(teacherId);
		map.put("teacher",teacher);
		int userId = teacher.getTeacherId();
		String picture = userRepository.findPictureByUserId(userId);
		map.put("picture", picture);
		return map;
	}
	
	@RequestMapping("/showTeacherPicture")//获取老师头像路径:localhost:8080/teacher/showTeacherPicture?teacherId=1
	public String showTeacherPicture(int teacherId) {
		int userId = teacherRepository.findUserIdByTeacherId(teacherId);
		return userRepository.findPictureByUserId(userId);
	}
}
