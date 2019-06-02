package org.rrtf.flcb.controller;

import java.util.List;

import org.rrtf.flcb.dao.FlcbkcxxbRepository;
import org.rrtf.flcb.entity.Flcbkcxxb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jpa")
public class JpaController {
	@Autowired
	FlcbkcxxbRepository flcbRepo;
	
	@RequestMapping("/all")
	public List<Flcbkcxxb> findAll(){
		return flcbRepo.findAll();
	}
	
	@RequestMapping("/find/{lessonId}")
	public Flcbkcxxb findByLessonId(@PathVariable("lessonId") int lessonId) {
		return flcbRepo.findByLessonId(lessonId);
	}
	
	@RequestMapping("/del/{lessonId}")
	public void delFlcb(@PathVariable("lessonId") int lessonId) {
		flcbRepo.deleteByLessionId(lessonId);
	}
	
}
