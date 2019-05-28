package org.rrtf.lesson.controller;

import java.util.List;

import org.rrtf.lesson.mapper.GroupTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/groupType")
public class GroupTypeController {
	@Autowired
	GroupTypeRepository groupTypeRepository;

	@RequestMapping("/typeName")//查找群组类别:localhost:8080/groupType/typeName
	public List<String> groupType() {
		return groupTypeRepository.findGroupType();
	}
}
