package org.rrtf.lesson.controller;

import java.util.List;

import org.rrtf.lesson.entity.MyLessons;
import org.rrtf.lesson.mapper.MyLessonsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/myLesson喵")
public class MyLessonsController {//暂时没用上
	
	@Autowired
	MyLessonsRepository myLessonsRepository;
	
	//查找我的公开课,返回课程Id:http://localhost:8080/myLesson/myPubLesson?userId=2
	/*@RequestMapping("/myPubLesson喵")
	public List<Integer> myPubLesson(int userId) {
		return myLessonsRepository.findMyLessons(userId);
	}*/
}
