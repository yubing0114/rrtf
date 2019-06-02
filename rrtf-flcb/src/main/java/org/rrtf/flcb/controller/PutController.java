package org.rrtf.flcb.controller;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;


import org.rrtf.flcb.dao.CommentRepository;
import org.rrtf.flcb.dao.FlcbkcxxbRepository;
import org.rrtf.flcb.dao.GroupChatRepository;
import org.rrtf.flcb.dao.MemberRepository;
import org.rrtf.flcb.dao.TeacherRepository;
import org.rrtf.flcb.dao.UserRepository;
import org.rrtf.flcb.entity.Comment;
import org.rrtf.flcb.entity.Fdata;
import org.rrtf.flcb.entity.Flcbkcxxb;
import org.rrtf.flcb.entity.GroupChat;
import org.rrtf.flcb.entity.Member;
import org.rrtf.flcb.entity.Teacher;
import org.rrtf.flcb.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class PutController {
	
	@Autowired
	FlcbkcxxbRepository flcbRepo;
	@Autowired
	GroupChatRepository groupRepo;
	@Autowired
	TeacherRepository teaRepo;
	@Autowired
	CommentRepository commRepo;
	@Autowired
	MemberRepository memberRepo;
	@Autowired
	UserRepository userRepo;
	
	
	//发布页-课程描述,上传群组信息
	@RequestMapping(value="/kcms-group.do", method=RequestMethod.POST)
	public List<GroupChat> putGroup(){
		List<GroupChat> group = groupRepo.findAll();
		return group;
	}
	
	//发布页-课程描述,上传教师信息
	@RequestMapping(value="/kcms-tea.do", method=RequestMethod.POST)
	public List<Teacher> putTea(){
		List<Teacher> teacher = teaRepo.findAll();
		return teacher;
	}
	
	/**
	 * 活动详细页->我的福利城堡相关功能
	 */
	//我的福利城堡页,按活动类型查找
	@RequestMapping(value="/wodeflcb-search/{activityType}/{memberId}", method=RequestMethod.POST)
	public List<Flcbkcxxb> wodeflcbSearch(@PathVariable("activityType") String activityType, 
			@PathVariable("memberId") int memberId) {
		System.out.println(activityType);
		List<Flcbkcxxb> flcbs = flcbRepo.findByActivityType(activityType);
		
		for(Flcbkcxxb flcb : flcbs) {			
			Flcbkcxxb flcb3 = new Flcbkcxxb();
			Set<Comment> commentSet = flcb.getComments();
			for(Comment comments : commentSet) {
				comments.setFlcbs(flcb3);
			}
			Set<Fdata> dataSet = flcb.getDatas();
			for(Fdata datas : dataSet) {
				datas.setFlcb(flcb3);
			}
		}
		System.out.println(flcbs);
		List<Flcbkcxxb> flcbList = new LinkedList<Flcbkcxxb>();
		for(Flcbkcxxb flcb1 : flcbs) {
			Set<Member> memberSet = flcb1.getJoinMembers();
			System.out.println(memberSet);
			for(Member member1: memberSet) {
				System.out.println(member1.getMemberId());
				if(member1.getMemberId()==memberId) {
					flcbList.add(flcb1);
				}
			}
		}
		List<Flcbkcxxb> flcbs1 = new LinkedList<Flcbkcxxb>();
		for(Flcbkcxxb flcb : flcbList) {
			if(flcb.getStatus()==1) {
				flcbs1.add(flcb);
			}
		}
		System.out.println(flcbs1);
		return flcbs1;
	}
	
	//个人中心->我的发布
	@RequestMapping(value="/findwodefabu/{userId}", method=RequestMethod.POST)
	public List<Flcbkcxxb> wodeFabu(@PathVariable("userId") int userId) {
		User user = userRepo.findByUserId(userId);
		Teacher teacher = teaRepo.findByUser(user);
		List<Flcbkcxxb> flcbs = flcbRepo.findByTeacher(teacher);
		for(Flcbkcxxb flcb : flcbs) {			
			Flcbkcxxb flcb3 = new Flcbkcxxb();
			Set<Comment> commentSet = flcb.getComments();
			for(Comment comments : commentSet) {
				comments.setFlcbs(flcb3);
			}
			Set<Fdata> dataSet = flcb.getDatas();
			for(Fdata datas : dataSet) {
				datas.setFlcb(flcb3);
			}
			
		}
		return flcbs;
	}
	
	//福利城堡页,按活动类型,课程类型查找
	@RequestMapping(value="/flcb-search/{activityType}/{lessonType}",method= {RequestMethod.POST,RequestMethod.GET})
	public List<Flcbkcxxb> flcbByActAndLes(@PathVariable("activityType") String activityType, @PathVariable("lessonType") String lessonType) {
		System.out.println(activityType + "|" + lessonType);
		List<Flcbkcxxb> flcbs = new LinkedList<Flcbkcxxb>();
		
		if(lessonType.equals("全部")) {
			List<Flcbkcxxb> flcbList =  flcbRepo.findByActivityType(activityType);
			for(Flcbkcxxb flcb : flcbList) {
				if(flcb.getStatus()==1) {
					flcbs.add(flcb);
				}
			}
		}else {
			List<Flcbkcxxb> flcbList = flcbRepo.findByActivityTypeAndLessonType(activityType, lessonType);
			for(Flcbkcxxb flcb : flcbList) {
				if(flcb.getStatus()==1) {
					flcbs.add(flcb);
				}
			}
		}
		for(Flcbkcxxb flcb : flcbs) {			
			Flcbkcxxb flcb3 = new Flcbkcxxb();
			Set<Comment> commentSet = flcb.getComments();
			for(Comment comments : commentSet) {
				comments.setFlcbs(flcb3);
			}
			Set<Fdata> dataSet = flcb.getDatas();
			for(Fdata datas : dataSet) {
				datas.setFlcb(flcb3);
			}
		}
		return flcbs;
	}
	
	/**
	 * 活动详细页相关功能
	 */
	//福利城堡活动详细页,添加考生评论
	@RequestMapping(value="/pinglun/{lessonId}/{memberId}/{commentContent}" , method= {RequestMethod.GET,RequestMethod.POST})
	public Flcbkcxxb pinglun(@PathVariable("lessonId") int lessonId, @PathVariable("memberId") int memberId, 
			@PathVariable("commentContent") String commentContent) {
		Comment comment = new Comment();
		comment.setCommentContent(commentContent);
		Member member = memberRepo.findByMemberId(memberId);
		Flcbkcxxb flcb1 = flcbRepo.findByLessonId(lessonId);
		comment.setMembers(member);
		comment.setFlcbs(flcb1);
		comment.setRegtime(new Timestamp(System.currentTimeMillis()));
		commRepo.save(comment);
		Flcbkcxxb flcb = flcbRepo.findByLessonId(lessonId);
		Flcbkcxxb flcb3 = new Flcbkcxxb();
		Set<Comment> commentSet = flcb.getComments();
		for(Comment comments : commentSet) {
			comments.setFlcbs(flcb3);
		}
		Set<Fdata> dataSet = flcb.getDatas();
		for(Fdata datas : dataSet) {
			datas.setFlcb(flcb3);
		}
		return flcb;
	}
	
	//福利城堡活动详细页,考生评论内容
	@RequestMapping(value="/find/pinglun/{lessonId}" , method= {RequestMethod.GET,RequestMethod.POST})
	public Flcbkcxxb findpinglun(@PathVariable("lessonId") int lessonId) {
		Flcbkcxxb flcb = flcbRepo.findByLessonId(lessonId);
		Flcbkcxxb flcb3 = new Flcbkcxxb();
		Set<Comment> commentSet = flcb.getComments();
		for(Comment comments : commentSet) {
			comments.setFlcbs(flcb3);
		}
		Set<Fdata> dataSet = flcb.getDatas();
		for(Fdata datas : dataSet) {
			datas.setFlcb(flcb3);
		}
		return flcb;
	}
	
	//福利城堡活动详细页,红心关注
	@RequestMapping(value="/find/guanzhu/{lessonId}" , method= {RequestMethod.GET,RequestMethod.POST})
	public Flcbkcxxb findGuanzhu(@PathVariable("lessonId") int lessonId) {
		Flcbkcxxb flcb = flcbRepo.findByLessonId(lessonId);
		Flcbkcxxb flcb3 = new Flcbkcxxb();
		Set<Comment> commentSet = flcb.getComments();
		for(Comment comments : commentSet) {
			comments.setFlcbs(flcb3);
		}
		Set<Fdata> dataSet = flcb.getDatas();
		for(Fdata datas : dataSet) {
			datas.setFlcb(flcb3);
		}
		return flcb;
	}
	
	//福利城堡-活动详细页,灰->红
	@RequestMapping(value="/change/red/{lessonId}/{memberId}" , method= {RequestMethod.GET,RequestMethod.POST})
	public Flcbkcxxb changeRed(@PathVariable("lessonId") int lessonId, @PathVariable("memberId") int memberId) {
		Flcbkcxxb flcb = flcbRepo.findByLessonId(lessonId);
		List<Member> members = flcb.getMembers();
		Member member = memberRepo.findByMemberId(memberId);
		members.add(member);
		flcb.setMembers(members);
		flcbRepo.save(flcb);
		Flcbkcxxb flcb3 = new Flcbkcxxb();
		Set<Comment> commentSet = flcb.getComments();
		for(Comment comments : commentSet) {
			comments.setFlcbs(flcb3);
		}
		Set<Fdata> dataSet = flcb.getDatas();
		for(Fdata datas : dataSet) {
			datas.setFlcb(flcb3);
		}
		return flcb;
	}
	
	//福利城堡-活动详细页,红->灰
	@RequestMapping(value="/change/gray/{lessonId}/{memberId}" , method= {RequestMethod.GET,RequestMethod.POST})
	public Flcbkcxxb changeGray(@PathVariable("lessonId") int lessonId, @PathVariable("memberId") int memberId) {
		Flcbkcxxb flcb = flcbRepo.findByLessonId(lessonId);
		List<Member> members = flcb.getMembers();
		Member member = memberRepo.findByMemberId(memberId);
		int index = members.indexOf(member);
		members.remove(index);
		flcbRepo.save(flcb);
		Flcbkcxxb flcb3 = new Flcbkcxxb();
		Set<Comment> commentSet = flcb.getComments();
		for(Comment comments : commentSet) {
			comments.setFlcbs(flcb3);
		}
		Set<Fdata> dataSet = flcb.getDatas();
		for(Fdata datas : dataSet) {
			datas.setFlcb(flcb3);
		}
		return flcb;
	}
	
	//活动详细页->加入群组
	@RequestMapping(value="/joingroup/{lessonId}/{userId}" , method= {RequestMethod.GET,RequestMethod.POST})
	public void joinGroup(@PathVariable("lessonId") int lessonId, @PathVariable("userId") int userId) {
		Flcbkcxxb flcb = flcbRepo.findByLessonId(lessonId);
		int groupId = flcb.getGroupChat().getGroupId();
		GroupChat group = groupRepo.findByGroupId(groupId);
		User user = userRepo.findByUserId(userId);
		Set<User> users = new HashSet<User>();
		users.add(user);
		group.setUser(users);
		groupRepo.save(group);
		System.out.println("保存成功"+group.toString());
//		return "保存成功";
	}
	
	//支付一
	@RequestMapping("/findPrice/{lessonId}")
	public Flcbkcxxb flcbPay1(@PathVariable("lessonId") int lessonId) {
		System.out.println(lessonId);
		Flcbkcxxb flcb = flcbRepo.findByLessonId(lessonId);
		Flcbkcxxb flcb3 = new Flcbkcxxb();
		Set<Comment> commentSet = flcb.getComments();
		for(Comment comments : commentSet) {
			comments.setFlcbs(flcb3);
		}
		Set<Fdata> dataSet = flcb.getDatas();
		for(Fdata datas : dataSet) {
			datas.setFlcb(flcb3);
		}
		flcb.setDatas(dataSet);
		flcb.setComments(commentSet);
		return flcb;
	}
	
	//0.0
	@RequestMapping(value="/member-or-teacher/{userId}" , method= {RequestMethod.GET,RequestMethod.POST})
	public Object memberOrTeacher(Model model,@PathVariable("userId") int userId) {
		User user = userRepo.findByUserId(userId);
		String name = user.getName();
		if(name.equals("member")) {			
			Member member = memberRepo.findByUser(user);
			model.addAttribute("member", member);
			return member;
		}/*else if(name.equals("teacher")) {
			Teacher teacher = teaRepo.findByUserId(userId);
			return teacher;
		}*/
		return null;
	}
	

	
	//发布页->上传资料
	@RequestMapping(value="/fileupload" , method= {RequestMethod.GET,RequestMethod.POST})
	public Flcbkcxxb findfile() {
		List<Flcbkcxxb> flcbs = flcbRepo.findAll();
		Flcbkcxxb flcb = flcbs.get(flcbs.size()-1);
		
		Flcbkcxxb flcb3 = new Flcbkcxxb();
		Set<Comment> commentSet = flcb.getComments();
		for(Comment comments : commentSet) {
			comments.setFlcbs(flcb3);
		}
		Set<Fdata> dataSet = flcb.getDatas();
		for(Fdata datas : dataSet) {
			datas.setFlcb(flcb3);
		}
		return flcb;
	}
	
	@RequestMapping(value="/filedownload/{lessonId}" , method= {RequestMethod.GET,RequestMethod.POST})
	public Flcbkcxxb findfile(@PathVariable("lessonId") int lessonId) {
		Flcbkcxxb flcb = flcbRepo.findByLessonId(lessonId);
		Flcbkcxxb flcb3 = new Flcbkcxxb();
		Set<Comment> commentSet = flcb.getComments();
		for(Comment comments : commentSet) {
			comments.setFlcbs(flcb3);
		}
		Set<Fdata> dataSet = flcb.getDatas();
		for(Fdata datas : dataSet) {
			datas.setFlcb(flcb3);
		}
		return flcb;
	}
	
	//所有分页功能->返回当前页码和总页数
	@RequestMapping(value="/getPage/{no}/{totalPages}" , method= {RequestMethod.GET,RequestMethod.POST})
	public LinkedHashMap getPage(@PathVariable("no") int no, @PathVariable("totalPages") int totalPages) {
		System.out.println(no);
		LinkedHashMap pageMap = new LinkedHashMap();
		pageMap.put("no", no);
		pageMap.put("totalPages",totalPages);
		return pageMap;
	}
	
	/**
	 * 管理员相关功能
	 */
	//管理员页,按活动类型查找
	@RequestMapping(value="/admin-search/{activityType}",method= {RequestMethod.POST,RequestMethod.GET})
	public List<Flcbkcxxb> adminSearchByActiType(@PathVariable("activityType") String activityType) {
		List<Flcbkcxxb> flcbs = new LinkedList<Flcbkcxxb>();
		if(activityType.equals("全部")) {
			flcbs =  flcbRepo.findAll();
		}else {
			flcbs = flcbRepo.findByActivityType(activityType);
		}
		for(Flcbkcxxb flcb : flcbs) {			
			Flcbkcxxb flcb3 = new Flcbkcxxb();
			Set<Comment> commentSet = flcb.getComments();
			for(Comment comments : commentSet) {
				comments.setFlcbs(flcb3);
			}
			Set<Fdata> dataSet = flcb.getDatas();
			for(Fdata datas : dataSet) {
				datas.setFlcb(flcb3);
			}
		}
		return flcbs;
		}
		
	//管理员页,按课程名称,讲师,课程类别查找
	@RequestMapping(value="/admin-button-search/{activityType}/{lessonTitle}/{teacherName}/{lessonType}",method= {RequestMethod.POST,RequestMethod.GET})
	public List<Flcbkcxxb> adminSearchByActiType(@PathVariable("activityType") String activityType, 
			@PathVariable("lessonTitle") String lessonTitle, @PathVariable("teacherName") String teacherName, 
			@PathVariable("lessonType") String lessonType) {
		List<Flcbkcxxb> flcbs = new LinkedList<Flcbkcxxb>();
		System.out.println(lessonTitle);
		System.out.println(teacherName);
		System.out.println(lessonType);
		System.out.println(activityType);
		if(lessonTitle.equals(" ")) {
			flcbs =  flcbRepo.findAll();
		}else {
			flcbs = flcbRepo.findByLessonTitleLike("%"+lessonTitle+"%");
		}
		for(Flcbkcxxb flcb : flcbs) {			
			Flcbkcxxb flcb3 = new Flcbkcxxb();
			Set<Comment> commentSet = flcb.getComments();
			for(Comment comments : commentSet) {
				comments.setFlcbs(flcb3);
			}
			Set<Fdata> dataSet = flcb.getDatas();
			for(Fdata datas : dataSet) {
				datas.setFlcb(flcb3);
			}
		}
		List<Flcbkcxxb> flcbList = new LinkedList<Flcbkcxxb>();
		for(Flcbkcxxb flcb : flcbs) {
			if(activityType.equals("全部")) {
				if((flcb.getLessonType().equals(lessonType))&&(flcb.getTeacher().getRealname().equals(teacherName))) {
					flcbList.add(flcb);
				}
			}else if((flcb.getLessonType().equals(lessonType))&&(flcb.getTeacher().getRealname().equals(teacherName))
					&&(flcb.getActivityType().equals(activityType))) {
				flcbList.add(flcb);
			}
		}
		
		System.out.println(flcbs);
		System.out.println(flcbList);
		return flcbList;
	}
	
	//管理员页面->福利城堡->关闭按钮功能
	@RequestMapping(value="/admin-close-flcb/{lessonId}",method= {RequestMethod.POST,RequestMethod.GET})
	public Flcbkcxxb adminCloseFlcb(@PathVariable("lessonId") int lessonId) {
		
		flcbRepo.modifyFlcbkcxxbByLessionId2(0, lessonId);
		Flcbkcxxb flcb = flcbRepo.findByLessonId(lessonId);
		Flcbkcxxb flcb3 = new Flcbkcxxb();
		Set<Comment> commentSet = flcb.getComments();
		for(Comment comments : commentSet) {
			comments.setFlcbs(flcb3);
		}
		Set<Fdata> dataSet = flcb.getDatas();
		for(Fdata datas : dataSet) {
			datas.setFlcb(flcb3);
		}
		System.out.println(flcb);
		return flcb;
	}
	
	//管理员页面->福利城堡->恢复按钮功能
	@RequestMapping(value="/admin-recover-flcb/{lessonId}",method= {RequestMethod.POST,RequestMethod.GET})
	public Flcbkcxxb adminRecoverFlcb(@PathVariable("lessonId") int lessonId) {
		
		flcbRepo.modifyFlcbkcxxbByLessionId2(1, lessonId);
		Flcbkcxxb flcb = flcbRepo.findByLessonId(lessonId);
		Flcbkcxxb flcb3 = new Flcbkcxxb();
		Set<Comment> commentSet = flcb.getComments();
		for(Comment comments : commentSet) {
			comments.setFlcbs(flcb3);
		}
		Set<Fdata> dataSet = flcb.getDatas();
		for(Fdata datas : dataSet) {
			datas.setFlcb(flcb3);
		}
		System.out.println(flcb);
		return flcb;
	}
	
	@RequestMapping(value="/admin-delete-flcb/{lessonId}",method= {RequestMethod.POST,RequestMethod.GET})
	public String adminDeleteFlcb(@PathVariable("lessonId") int lessonId) {
		flcbRepo.deleteByLessionId(lessonId);
		return "删除成功!";
	}
}
