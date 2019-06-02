package org.rrtf.flcb.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.rrtf.flcb.dao.CommentRepository;
import org.rrtf.flcb.dao.FdataRepository;
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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;




@Controller
public class FlcbController {
	
	@Value("${upload.location}")
	String UPLOAD_LOCATION;
	
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
	@Autowired
	FdataRepository fdataRepo;
	/**
	 * 页面跳转(携带相关信息跳转)
	 */
	//首页跳转到到福利城堡
	@RequestMapping("/toflcb")
	public String toFlcb(HttpServletRequest request,HttpSession session, 
			Model model, @PathVariable("userId") int userId) {
		Cookie[] cookies = request.getCookies();
		String username = "";
		String userkey = "";
		for(Cookie c :cookies ){
			if(c.getName().equals("user")) {
				userkey = c.getName();
				username = c.getValue();
			}
		}
		User user = userRepo.findByUsername(username);
		session.setAttribute("user", user);
		
		String name = user.getName();
		if(name.equals("member")) {			
			Member member = memberRepo.findByUser(user);
			model.addAttribute("member", member);
		}else if(name.equals("teacher")) {
			Teacher teacher = teaRepo.findByUser(user);
			model.addAttribute("teacher", teacher);
		}
		
		return "tuoFuTest/福利城堡/福利城堡-福利城堡";
	}
	
	//首页跳转到到用户中心
		@RequestMapping("/indextoyhzx/{userId}")
		public String indexToYhzx(Model model, @PathVariable("userId") int userId) {
			User user = userRepo.findByUserId(userId);
			String name = user.getName();
			if(name.equals("member")) {			
				Member member = memberRepo.findByUser(user);
				model.addAttribute("member", member);
			}else if(name.equals("teacher")) {
				Teacher teacher = teaRepo.findByUser(user);
				model.addAttribute("teacher", teacher);
			}
			return "tuoFuTest/个人资料/普通用户-首页";
		}
		//个人中心跳转到我的福利城堡
		@RequestMapping(value="/towodeflcb/{userId}" , method= {RequestMethod.GET,RequestMethod.POST})
		public String toWodeFlcb(Model model, @PathVariable("userId") int userId) {
			User user = userRepo.findByUserId(userId);
			String name = user.getName();
			if(name.equals("member")) {			
				Member member = memberRepo.findByUser(user);
				model.addAttribute("member", member);
			}else if(name.equals("teacher")) {
				Teacher teacher = teaRepo.findByUser(user);
				model.addAttribute("teacher", teacher);
			}
			return "tuoFuTest/个人资料/用户中心-我的福利城堡";
		}
		
		//我的福利城堡跳转到发布
		@RequestMapping("/tofabu/{userId}")
		public String toFabu(Model model, @PathVariable("userId") int userId) {
			User user = userRepo.findByUserId(userId);
			String name = user.getName();
			if(name.equals("member")) {			
				Member member = memberRepo.findByUser(user);
				model.addAttribute("member", member);
			}else if(name.equals("teacher")) {
				Teacher teacher = teaRepo.findByUser(user);
				model.addAttribute("teacher", teacher);
			}
			List<Flcbkcxxb> flcbs = flcbRepo.findAll();
			Flcbkcxxb flcb = flcbs.get(flcbs.size()-1);
			model.addAttribute("flcbfabu", flcb);
			return "tuoFuTest/福利城堡/福利城堡-发布";
		}
	/**
	 * 	发布页相关功能
	 */
	//发布-基本信息
	@RequestMapping("/add-basic.do")
	public String addFlcbBasic(String lessonTitle,Date startDate,Date endDate,String dayOfWeek,String startTime,
			String endTime,String studentType,int lessonPrice,String activityType,String lessonType) {
		Flcbkcxxb flcb = new Flcbkcxxb();
		flcb.setLessonTitle(lessonTitle);
		flcb.setStartDate(startDate);
		flcb.setEndDate(endDate);
		flcb.setDayOfWeek(dayOfWeek);
		flcb.setStartTime(startTime+":00");
		flcb.setEndTime(endTime+":00");
		flcb.setStudentType(studentType);
		flcb.setLessonPrice(lessonPrice);
		flcb.setActivityType(activityType);
		flcb.setLessonType(lessonType);
		Teacher teacher = teaRepo.findAll().get(0);
		flcb.setTeacher(teacher);
		GroupChat group = groupRepo.findAll().get(0);
		flcb.setGroupChat(group);
		flcb.setTalkId(1);
		flcb.setLessonPicture(" ");
		flcb.setTeachingOutline("最近土豪牛大了，昨晚说今天南北车还要板，今天果然又板！");
		flcb.setTeachingWay("每周都要在yy上面上课");
		Set<Fdata> datas = new HashSet<Fdata>();
		flcb.setDatas(datas);
		flcb.setStatus(1);
		flcbRepo.save(flcb);
		return "tuoFuTest/福利城堡/福利城堡-发布";
	}
	
	//发布-课程描述
	@RequestMapping(value="/add-desc.do", method= {RequestMethod.GET,RequestMethod.POST})
	public String addFlcbDesc(int teacherId, int groupId, @RequestParam("lessonPictureFile")MultipartFile lessonPictureFile, String teachingOutline,
			String teachingWay) throws IOException {
		List<Flcbkcxxb> flcb = flcbRepo.findAll();
		// 获取根目录
		File path1 = new File(ResourceUtils.getURL("classpath:").getPath());
		if (!path1.exists()) {
			path1 = new File("");
		}
		// 如果上传目录为/static/tuoFu/img/，则可以如下获取：
		File upload = new File(path1.getAbsolutePath(), "static/tuoFuTest/img/");
		if (!upload.exists()) {
			upload.mkdirs();
		}
		byte[] bytes = lessonPictureFile.getBytes();//1
		String filename = lessonPictureFile.getOriginalFilename();//2
		Path path = Paths.get(upload.getAbsolutePath(), filename);
		Files.write(path, bytes);//3
		String lessonPicture = "/tuoFuTest/img/"+filename;
		int lessonId = flcb.get(flcb.size()-1).getLessonId();
		flcbRepo.modifyFlcbkcxxbByLessionId1(teacherId, groupId, lessonPicture, teachingOutline, teachingWay, lessonId);
		return "tuoFuTest/福利城堡/福利城堡-发布";
	}
	
	//发布页->上传资料
	@RequestMapping(value="/add-file.do", method= {RequestMethod.GET,RequestMethod.POST})
	public String addFlcbFile(@RequestParam("dataFile")MultipartFile dataFile) throws IOException {
		List<Flcbkcxxb> flcbs = flcbRepo.findAll();
		// 获取根目录
		File path1 = new File(ResourceUtils.getURL("classpath:").getPath());
		if (!path1.exists()) {
			path1 = new File("");
		}
		// 如果上传目录为/static/tuoFu/data/，则可以如下获取：
		File upload = new File(path1.getAbsolutePath(), "static/tuoFuTest/data/");
		if (!upload.exists()) {
			upload.mkdirs();
		}
		byte[] bytes = dataFile.getBytes();//1
		String filename = dataFile.getOriginalFilename();//2
		Path path = Paths.get(upload.getAbsolutePath(), filename);
		Files.write(path, bytes);//3
		String dataUrl = "/tuoFuTest/data/"+filename;
		Fdata data = new Fdata();
		data.setDataUrl(dataUrl);
		int lessonId = flcbs.get(flcbs.size()-1).getLessonId();
		Flcbkcxxb flcb = flcbRepo.findByLessonId(lessonId);
		data.setFlcb(flcb);
		fdataRepo.save(data);
		return "tuoFuTest/福利城堡/福利城堡-发布";
	}
	
	/**
	 * 福利城堡页相关功能
	 */
	//福利城堡页,按类型查找
	@RequestMapping("/find-hdxq/{lessonId}/{userId}")
	public String findHdxq(Model model,@PathVariable("lessonId") int lessonId,@PathVariable("userId") int userId) {
		Flcbkcxxb flcb = flcbRepo.findByLessonId(lessonId);
		String activityType = flcb.getActivityType();
		String lessonType = flcb.getLessonType();
		List<Flcbkcxxb> flcbTuiJian = flcbRepo.findByActivityTypeAndLessonType(activityType, lessonType);
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
		
		User user = userRepo.findByUserId(userId);
		String name = user.getName();
		if(name.equals("member")) {			
			Member member = memberRepo.findByUser(user);
			model.addAttribute("member", member);
		}else if(name.equals("teacher")) {
			Teacher teacher = teaRepo.findByUser(user);
			model.addAttribute("teacher", teacher);
		}
		
		
		Set<Member> joinMemberSet = flcb.getJoinMembers();
		int memberNum = joinMemberSet.size();
		System.out.println(memberNum);
		model.addAttribute("memberNum", memberNum);
		
		model.addAttribute("flcbTuiJian", flcbTuiJian);
		model.addAttribute("flcb", flcb);
		return "tuoFuTest/福利城堡/福利城堡-活动详细页";
	}
	
	//福利城堡活动详细页,考生评论
	@RequestMapping(value="/del-pinglun/{commentId}/{lessonId}/{userId}" , method= {RequestMethod.GET,RequestMethod.POST})
	public String delPinglun(Model model, @PathVariable("commentId") int commentId, 
			@PathVariable("lessonId") int lessonId, @PathVariable("userId") int userId) {
		commRepo.deleteByCommentId(commentId);
		Flcbkcxxb flcb = flcbRepo.findByLessonId(lessonId);
		String activityType = flcb.getActivityType();
		String lessonType = flcb.getLessonType();
		List<Flcbkcxxb> flcbTuiJian = flcbRepo.findByActivityTypeAndLessonType(activityType, lessonType);
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
		
		User user = userRepo.findByUserId(userId);
		String name = user.getName();
		if(name.equals("member")) {			
			Member member = memberRepo.findByUser(user);
			model.addAttribute("member", member);
		}else if(name.equals("teacher")) {
			Teacher teacher = teaRepo.findByUser(user);
			model.addAttribute("teacher", teacher);
		}
		
		model.addAttribute("flcbTuiJian", flcbTuiJian);
		model.addAttribute("flcb", flcb);
		return "tuoFuTest/福利城堡/福利城堡-活动详细页";

	}
	
	//活动详细页->支付一
	@RequestMapping("/flcb-pay1/{lessonId}")
	public String flcbPay1(Model model,@PathVariable("lessonId") int lessonId) {
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
		model.addAttribute("flcb", flcb);
		return "tuoFuTest/支付/购买流程-步骤一";
	}
		
	//支付一->支付二
	@RequestMapping(value="/topay2/{lessonId}/{name}/{tel}/{rpb}/{dRpb}/{realPay}" , method= {RequestMethod.GET,RequestMethod.POST})
	public String toPay2(Model model,@PathVariable("lessonId") int lessonId,@PathVariable("name") String name,@PathVariable("tel") String tel,
			@PathVariable("rpb") String rpb,@PathVariable("dRpb") String dRpb,
			@PathVariable("realPay") String realPay) {
		model.addAttribute("name",name);
		model.addAttribute("tel",tel);
		model.addAttribute("rpb",rpb);
		model.addAttribute("dRpb",dRpb);
		model.addAttribute("realPay",realPay);
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
		model.addAttribute("flcb", flcb);
		return "tuoFuTest/支付/购买流程-步骤二";
	}
	
	//支付二->支付三
	@RequestMapping(value="/topay3/{lessonId}/{rpb}/{dRpb}/{realPay}" , method= {RequestMethod.GET,RequestMethod.POST})
	public String toPay3(Model model, @PathVariable("rpb") String rpb,@PathVariable("dRpb") String dRpb,
			@PathVariable("realPay") String realPay,@PathVariable("lessonId") int lessonId) {
		model.addAttribute("rpb",rpb);
		model.addAttribute("dRpb",dRpb);
		model.addAttribute("realPay",realPay);
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
		model.addAttribute("flcb", flcb);
		return "tuoFuTest/支付/购买流程-步骤三";
	}
	
	//支付三页面->完成支付跳转到用户信息页
	@RequestMapping(value="/paytoyhzx/{lessonId}/{userId}" , method= {RequestMethod.GET,RequestMethod.POST})
	public String toYhzx(Model model, @PathVariable("lessonId") int lessonId, @PathVariable("userId") int userId) {
		Flcbkcxxb flcb = flcbRepo.findByLessonId(lessonId);
		Flcbkcxxb flcb3 = new Flcbkcxxb();
		User user = userRepo.findByUserId(userId);
		Member member = memberRepo.findByUser(user);
		Set<Member> members = new HashSet<Member>();
		members.add(member);
		flcb.setJoinMembers(members);
		flcbRepo.save(flcb);
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
		model.addAttribute("member", member);
		return "tuoFuTest/个人资料/普通用户-首页";
	}
}
