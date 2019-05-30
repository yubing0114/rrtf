package org.rrtf.lesson.controller;

import org.rrtf.lesson.mapper.MyLessonsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/myLesson喵")
public class MyLessonsController {//暂时没用上
	
	@Autowired
	MyLessonsRepository myLessonsRepository;
	
}
