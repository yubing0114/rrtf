package org.rrtf.group.controller;

import java.util.List;

import org.rrtf.group.dao.GroupTypeDao;
import org.rrtf.group.entity.GroupType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/*
 * 针对群组类别的Controller
 */
@RestController
@RequestMapping("/grouptype")
public class GroupTypeController {
	@Autowired
	GroupTypeDao groupTypeMapper;
	@RequestMapping("/add")
	public List<GroupType> add() {
		GroupType entity=new GroupType();
		entity.setTypeName("同城考试群");
		groupTypeMapper.save(entity);
		/*GroupType entity1=new GroupType();
		entity.setTypeName("考试场次群");
		groupTypeMapper.save(entity1);
		GroupType entity2=new GroupType();
		entity.setTypeName("名师学堂群");
		groupTypeMapper.save(entity2);
		GroupType entity4=new GroupType();
		entity.setTypeName("结伴备考群");
		groupTypeMapper.save(entity4);*/
		return groupTypeMapper.findAll();
	}
	@RequestMapping("/update")
	public List<GroupType> update(int id){
		groupTypeMapper.modifyTypenameByTypeid("asdf33s", 1);
		return groupTypeMapper.findAll();
	}
	@RequestMapping("/del")
	public List<GroupType> update1(int id){
		groupTypeMapper.deleteByTypeid(id);
		return groupTypeMapper.findAll();
	}
	@RequestMapping("/search")
	public List<GroupType> search(){
		return groupTypeMapper.findAll();
	}
}
