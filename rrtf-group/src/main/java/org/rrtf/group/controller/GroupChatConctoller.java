package org.rrtf.group.controller;

import java.util.List;

import org.rrtf.group.dao.GroupChatDao;
import org.rrtf.group.dao.GroupTypeDao;
import org.rrtf.group.dao.TeacherDao;
import org.rrtf.group.dao.UserDao;
import org.rrtf.group.entity.GroupChat;
import org.rrtf.group.entity.GroupType;
import org.rrtf.group.entity.Teacher;
import org.rrtf.group.service.RoleTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/group")
public class GroupChatConctoller{
	
	@Autowired
	GroupChatDao groupChatDao;
	@Autowired
	GroupTypeDao groupTypeDao;
	@Autowired
	UserDao userDao;
	@Autowired
	TeacherDao teacherDao;
	@Autowired
	RoleTools roleTools;
	/*ValueOperations<String, Object> operations=redisTemplate.opsForValue();
	JSONObject object =(JSONObject) operations.get("user");
	User user=object.toJavaObject(User.class);*/
	/*
	 * 添加群聊的方法
	 */
	@RequestMapping("/add")
	public String add(int typeId,String groupName,String detail,String rule,int teacherId,Model model){
		int userId=roleTools.getSessionUserId();
		GroupType grouptype=new GroupType();
		grouptype.setTypeId(typeId);
		GroupChat entity=new GroupChat();
		entity.setDetail(detail);
		entity.setGroupMaster(teacherId);
		entity.setTeacher(teacherDao.findByTeacherId(teacherId));
		entity.setGroupName(groupName);
		entity.setRule(rule);
		entity.setGroupType(grouptype);
		groupChatDao.save(entity);
		return findOne(model,0,0);
	}
	@RequestMapping("/del")
	public String del(int id,Model model){
		groupChatDao.deleteByGroupid(id);
		return findOne(model, 0, 0);
	}
	
	/*
	 * 用户模式，只能查看网页
	*/
	@RequestMapping("/find2")
	public String find2(Model model, int id) {
		int userId=roleTools.getSessionUserId();
		GroupChat groupChat = groupChatDao.findByGroupId(id);
		groupChat.setMembers(groupChatDao.countNum(id));
		model.addAttribute("entity", groupChat);
		model.addAttribute("memberlist", userDao.findalluser(id));
		model.addAttribute("userId",userId);
		return "GroupDetail";
	}
	/*
	 * 聊天室的方法
	 */
	@RequestMapping("/chat")
	public String chat(int id,Model model) {
		int userId=roleTools.getSessionUserId();
		GroupChat entity=groupChatDao.findByGroupId(id);
		entity.setMembers(groupChatDao.countNum(id));
		model.addAttribute("entity", entity);
		model.addAttribute("memberlist", userDao.findalluser(id));
		model.addAttribute("userId",userId);
		return "groupchating";
	}
	
	/*
	 * 群组网页通过群组种类id查找群信息，并查找群组人数
	 */
	@RequestMapping("/findid")
	public String findOne(Model model,int id,int no){
		int userId=roleTools.getSessionUserId();
		System.out.println(userId);
		Pageable pageable=new PageRequest(no, 5);
		Page<GroupChat> list=null;
		List<Teacher> teacherlist=teacherDao.findAll();
		if(id>0) {
			GroupType groupType = groupTypeDao.findByTypeId(id);
			list = groupChatDao.findByGroupType(groupType, pageable);
			for(GroupChat gc:list) {
				gc.setMembers(groupChatDao.countNum(gc.getGroupId()));
				gc.setStatus(groupChatDao.belongToGroup(userId, gc.getGroupId()));
			}
			model.addAttribute("grouplist", list);
		}else if(id==0) {
			list=groupChatDao.findAll(pageable);
			for(GroupChat gc:list) {
				gc.setMembers(groupChatDao.countNum(gc.getGroupId()));
				gc.setStatus(groupChatDao.belongToGroup(userId, gc.getGroupId()));
			}
			model.addAttribute("grouplist",list);
		}
		System.out.println(list);
		model.addAttribute("id",id);
		model.addAttribute("no",no);
		model.addAttribute("userId",userId);
		model.addAttribute("type",groupTypeDao.findAll());
		model.addAttribute("grouptypelist",groupTypeDao.findAll());
		model.addAttribute("teacherlist",teacherlist);
		return "group-chat";
	}
	/*
	 * 查找自己的群
	*/
	@ResponseBody
	@RequestMapping("/mygroup")
	public Page<GroupChat> mygroup(int no) {
		//int userId=roleTools.getSessionUserId();
		int userId=roleTools.getSessionUserId();
		Pageable pageable=new PageRequest(no, 5);
		System.out.println(userId);
		Page<GroupChat> list=groupChatDao.findByGroupIdIn(userId, pageable);
		for(GroupChat gc:list) {
			gc.setMembers(groupChatDao.countNum(gc.getGroupId()));
		}
		return list;
	}
	/*
	 * 退群时的操作
	 */
	@RequestMapping("/delusr")
	public String delusr(int groupId,Model model) {
		int userId=roleTools.getSessionUserId();
		groupChatDao.delUser(userId, groupId);
		return findOne(model, 0, 0);
	}
	/*
	 * 加入群组
	 */
	@RequestMapping("/addinto")
	public String addgroup(int groupId,Model model){
		int userId=roleTools.getSessionUserId();
		groupChatDao.addIntoGroup(userId, groupId);
		return find2(model, groupId);
	}
	/*
	 * 管理员批量删除群成员
	*/
	@ResponseBody
	@RequestMapping("/updateuser")
	public void updateuser(@RequestParam("useridlist")List<Integer> useridlist,int groupId) {
		userDao.admindel(useridlist, groupId);
	}
	/*
	 * 管理员修改群信息
	*/
	@ResponseBody
	@RequestMapping("/update")
	public void update(int groupId,String detail,String rule,String groupName){
		GroupChat entity=null;
		entity=groupChatDao.findByGroupId(groupId);
		entity.setGroupName(groupName);
		entity.setDetail(detail);
		entity.setRule(rule);
		groupChatDao.save(entity);
	}
	/*
	 * 管理员、老师模式下能对群组信息进行修改的查看方法
	 */
	@RequestMapping("/find1")
	public String find1(int groupId,Model model) {
		GroupChat entity=groupChatDao.findByGroupId(groupId);
		entity.setMembers(groupChatDao.countNum(groupId));
		model.addAttribute("userList",userDao.findalluser(groupId));
		model.addAttribute("entity", entity);
		return "groupedit";
	}
	@ResponseBody
	@RequestMapping("/managegroup")
	public Page<GroupChat> manage(String groupName,String date,int no) {
		Pageable pageable=new PageRequest(no, 5);
		Page<GroupChat> list=null;
		if(groupName==""&date=="") {
			list=groupChatDao.findAll(pageable);
		}else {
			list=groupChatDao.findByGroupNameAndTime("%"+groupName+"%", date+" 00:00:00", date+" 23:59:59",pageable);
		}
		for(GroupChat gc:list) {
			gc.setMembers(groupChatDao.countNum(gc.getGroupId()));
		}
		return list;
	}
	@ResponseBody
	@RequestMapping("/form-inline")
	public Page<GroupChat> searchlike(String groupName,String date,int no) {
		Pageable pageable=new PageRequest(no, 5);
		Page<GroupChat> list=groupChatDao.findByGroupNameAndTime("%"+groupName+"%", date+" 00:00:00", date+" 23:59:59",pageable);
		for(GroupChat gc:list) {
			gc.setMembers(groupChatDao.countNum(gc.getGroupId()));
		}
		return list;
	}
	@RequestMapping("/delgroup")
	public String delgroup(@RequestParam("grouplist")List<Integer> list) {
		groupChatDao.delgroup(list);
		return "managegroup";
	}
	/*@ResponseBody
	@RequestMapping("/deltest")
	public int del(){
		List<Integer> list=new LinkedList<Integer>();
		list.add(6);
		return groupChatDao.delgroup(list);
	}*/
	/*@ResponseBody
	@RequestMapping("/test")
	public Page<GroupChat> find(String groupName, String date, int no) {
		Pageable pageable=new PageRequest(no, 5);
		return groupChatDao.findByGroupNameAndTime("%"+groupName+"%", date+" 00:00:00", date+" 23:59:59",pageable);
	}*/
	/*@RequestMapping("/testPage")
	@ResponseBody
	public Page<GroupChat> find2(int userId,int pageNo) {
		Pageable pageable=new PageRequest(pageNo, 5);
		return groupChatDao.findByGroupIdIn(userId, pageable);
	}
	public int count(int id) {
		return groupChatDao.countNum(id);
	}
	public List<User> findall(int groupId){
		System.out.println(groupId);
		return userDao.findalluser(groupId);
	}
	@RequestMapping("/test1")
	@ResponseBody
	public int belong() {
		return groupChatDao.belongToGroup(1, 2);
	}*/
}