package org.rrtf.lesson.controller;

import org.rrtf.lesson.entity.PubLesson;
import org.rrtf.lesson.mapper.PubLessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ThymeleafLessonInformation {//thymeleaf版本,还是废弃掉比较好

	@Autowired
	PubLessonRepository lessonRepository;
	
	//我的全部公开课http://localhost:8080/myPubLesson/1
	@RequestMapping("/myPubLesson/{teacherId}")
	public String myPublicLesson(Model model,@PathVariable("teacherId") int teacherId,int no) {
		Pageable pageable = new PageRequest(no-1, 3);//稍微处理了一下
		//return userrepo.findAll(pageable);
		Page<PubLesson> lessonList = lessonRepository.findByTeacherIdAndLessonStatus(teacherId,pageable,1);
		model.addAttribute("onesLessons", lessonList);
		model.addAttribute("totalPages",lessonList.getTotalPages());
		if(no!=1) {
			model.addAttribute("pret",no-1);
		}
		if(no!=lessonList.getTotalPages()) {
			model.addAttribute("next",no+1);
		}
		return "selfInformation/myLesson";
	}
	
	//所有人的公开课
	@RequestMapping("/allPubLesson")
	public String allPublicLesson(Model model,int no) {
		Pageable pageable = new PageRequest(no-1, 3);
		Page<PubLesson> lessonList = lessonRepository.findAll(pageable);
		model.addAttribute("allLessons", lessonList);
		model.addAttribute("totalPages",lessonList.getTotalPages());
		if(no!=1) {
			model.addAttribute("pret",no-1);
		}
		if(no!=lessonList.getTotalPages()) {
			model.addAttribute("next",no+1);
		}
		return "selfInformation/myLesson";
	}
}
