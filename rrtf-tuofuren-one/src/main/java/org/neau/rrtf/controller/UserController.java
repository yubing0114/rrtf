package org.neau.rrtf.controller;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.neau.rrtf.ArtiJPA.ArticleRepository;
import org.neau.rrtf.ArtiJPA.TeacherRepo;
import org.neau.rrtf.ArtiJPA.UserRepository;
import org.neau.rrtf.ArtiJPA.articleDoRepo;
import org.neau.rrtf.Entity.Article;
import org.neau.rrtf.Entity.ArticleDomment;
import org.neau.rrtf.Entity.Teacher;
import org.neau.rrtf.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

@Controller

public class UserController {

	@Autowired
	ArticleRepository artireop;
	@Autowired
	TeacherRepo teacherRepo;
	@Autowired
	articleDoRepo artrepo;
	@Autowired
	UserRepository userrepo;
	@Resource
	RedisTemplate redisTemplate;

	// 点击某篇文章进入详情页
	@RequestMapping("/todetail")
	public String todetail(Model model, int articleId) {
		Article article = artireop.findByArticleId(articleId);
		int aid = artrepo.countByArticleId(articleId);
		int teacherId = article.getTeacherId();
		String realname = teacherRepo.findByTeacherId(teacherId).getRealname();
		model.addAttribute("article", article);
		model.addAttribute("aid", aid);
		model.addAttribute("realname", realname);
		return "托福人详情页";
	}

	@RequestMapping("/redis")
	@ResponseBody
	public void redisGetKey() {
		ValueOperations operations = redisTemplate.opsForValue();
		JSONObject object = (JSONObject) operations.get("user1");
		User user = object.toJavaObject(User.class);
		System.out.println(object);

	}

	@RequestMapping("/index")
	public String index() {
		return "首页";
	}

	// 用户中心-我的托福人/我收藏的文章
	@RequestMapping("/likearticle")
	@ResponseBody
	public Map<String, Object> likeBy(HttpServletRequest request, HttpServletResponse response, Integer no) {
		run(request, response);
		User user = null;
		Cookie[] cookies = request.getCookies();
		if (null != cookies) {
			for (Cookie cookie : cookies) {
				if ("user".equals(cookie.getName())) {
					String username = cookie.getValue();
					user = userrepo.findByUsername(username);
				}
			}
		}
		

		
		
		
		
		int userId = user.getUserId();
		int size = 5;
		Pageable pageable = new PageRequest(no - 1, size);
		List<Integer> alist = new ArrayList<Integer>();
		// 联合查询,根据中间表 user_article的字段关联user_id查出article表所有信息
		Page<Article> artlist = artireop.select2(userId, pageable);
		List<String> namelist = new ArrayList<String>();
		for (int i = 0; i < artlist.getContent().size(); i++) {
			int teacherId = artlist.getContent().get(i).getTeacherId();
			int articleId = artlist.getContent().get(i).getArticleId();
			Integer like = artireop.selectTotalike(articleId).size();
			alist.add(like);
			System.out.println(like);
			System.out.println(artlist.getContent().size());
			Teacher teacher = teacherRepo.findByTeacherId(teacherId);
			String realname = teacher.getRealname();
			namelist.add(realname);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("artlist", artlist);
		map.put("namelist", namelist);
		map.put("alist", alist);

		return map;

	}

	// 托福人详情页的收藏文章
	@RequestMapping("/like")
	@ResponseBody
	public int likeart(HttpServletRequest request, HttpServletResponse response, int articleId) {
		run(request, response);
		User user = null;
		Cookie[] cookies = request.getCookies();
		if (null != cookies) {
			for (Cookie cookie : cookies) {
				if ("user".equals(cookie.getName())) {
					String username = cookie.getValue();
					user = userrepo.findByUsername(username);
					System.out.println(user);
				}
			}
		}
		int userId = user.getUserId();

		// 如果查询语句大于0，则再点一下取消收藏
		if (artireop.select1(userId, articleId).size() > 0) {
			artireop.delete1(userId, articleId);
			return 0;
		}

		else {
			artireop.save1(userId, articleId);
			return 1;
		}
	}

	// 一进来就加载 判断是否收藏了此篇文章
	@RequestMapping("/likeready")
	@ResponseBody
	public int likeready(HttpServletRequest request, HttpServletResponse response, int articleId) {
		run(request, response);
		User user = null;
		Cookie[] cookies = request.getCookies();
		if (null != cookies) {
			for (Cookie cookie : cookies) {
				if ("user".equals(cookie.getName())) {
					String username = cookie.getValue();
					user = userrepo.findByUsername(username);
				}
			}
		}

		int userId = user.getUserId();

		if (artireop.select1(userId, articleId).size() > 0) {

			return 0;
		}

		else {

			return 1;
		}
	}

	// 保存评论
	@RequestMapping("/comment")
	@ResponseBody
	public String comment(HttpServletRequest request, HttpServletResponse response, int articleId) {
		run(request, response);
		User user = null;
		Cookie[] cookies = request.getCookies();
		if (null != cookies) {
			for (Cookie cookie : cookies) {
				if ("user".equals(cookie.getName())) {
					String username = cookie.getValue();
					user = userrepo.findByUsername(username);
				}
			}
		}
		int userId = user.getUserId();
		String ccontent = request.getParameter("ccontent");
		String username = user.getUsername();
		ArticleDomment articledo = new ArticleDomment();
		articledo.setCtime(new Timestamp(System.currentTimeMillis()));
		articledo.setCcontent(ccontent);
		articledo.setArticleId(articleId);
		articledo.setUserId(userId);
		artrepo.save(articledo);
		return username;
	}

	// 统计评论数量
	@RequestMapping("/total")
	@ResponseBody
	public int total1(int articleId) {
		List<ArticleDomment> dolist = artrepo.findByArticleIdOrderByCtimeDesc(articleId);
		if (dolist.size() > 0) {
			return dolist.size();
		} else {
			return 0;
		}
	}

	// 根据评论事件将评论排序
	@RequestMapping("/conlist")
	@ResponseBody
	public Map<String, Object> total(int articleId, int no) {
		int size = 9;
		Pageable pageable = new PageRequest(no - 1, size);
		Page<ArticleDomment> colist = artrepo.findByArticleIdOrderByCtimeDesc(articleId, pageable);
		List<ArticleDomment> dolist = artrepo.findByArticleIdOrderByCtimeDesc(articleId);
		List<String> listname = new ArrayList<String>();
		for (int i = 0; i < dolist.size(); i++) {
			int userId = dolist.get(i).getUserId();
			User user = userrepo.findByUserId(userId);
			String username = user.getUsername();
			listname.add(username);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("listname", listname);
		map.put("colist", colist);
		return map;
	}

	// 托福人
	@RequestMapping("/top6")
	public String findTop(String articleType, String sort, Model model) {
		Sort sort1 = null;
		if ("asc".equals(sort))
			sort1 = new Sort(Direction.ASC, "regtime");
		else
			sort1 = new Sort(Direction.DESC, "regtime");
		String[] typelist = { "听力", "阅读", "词汇", "口语", "机经", "资讯", "活动", "写作" };
		List<Article> allist = artireop.findTop6ByArticleType(typelist[0], sort1);
		List<Article> allist1 = artireop.findTop6ByArticleType(typelist[1], sort1);
		List<Article> allist2 = artireop.findTop6ByArticleType(typelist[2], sort1);
		List<Article> allist3 = artireop.findTop6ByArticleType(typelist[3], sort1);
		List<Article> allist4 = artireop.findTop6ByArticleType(typelist[4], sort1);
		List<Article> allist5 = artireop.findTop6ByArticleType(typelist[5], sort1);
		List<Article> allist6 = artireop.findTop6ByArticleType(typelist[6], sort1);
		List<Article> allist7 = artireop.findTop6ByArticleType(typelist[7], sort1);
		model.addAttribute("allist", allist);
		model.addAttribute("allist1", allist1);
		model.addAttribute("allist2", allist2);
		model.addAttribute("allist3", allist3);
		model.addAttribute("allist4", allist4);
		model.addAttribute("allist5", allist5);
		model.addAttribute("allist6", allist6);
		model.addAttribute("allist7", allist7);

		return "托福人";
	}

	// thymeleaf的分页 年少不懂事才用thymeleaf 难用死惹😭
	@RequestMapping("/page/{articleType}")
	public String myPublicLesson(Model model, @PathVariable("articleType") String articleType, int no) {
		Pageable pageable = new PageRequest(no - 1, 4);// 稍微处理了一下
		List<Integer> blist = new ArrayList<Integer>();
		Map<String, Object> map = new HashMap<String, Object>();
		Page<Article> alist = artireop.findByArticleType(articleType, pageable);
		for (int i = 0; i < alist.getContent().size(); i++) {
			int articleId = alist.getContent().get(i).getArticleId();
			Integer like = artireop.selectTotalike(articleId).size();
			blist.add(like);
		}
		model.addAttribute("alist", alist);
		model.addAttribute("blist", blist);
		model.addAttribute("totalPages", alist.getTotalPages());
		if (no != 1) {
			model.addAttribute("pret", no - 1);
		}
		if (no != alist.getTotalPages()) {
			model.addAttribute("next", no + 1);
		}

		return "托福人分类页";
	}

	// ajax 系统管理 分类查询文章
	@RequestMapping("/typebyart")
	@ResponseBody
	public Map<String, Object> ByType(String articleType, Integer no) {
		int size = 5;
		Pageable pageable = new PageRequest(no - 1, size);
		List<Integer> alist = new ArrayList<Integer>();
		List<String> blist = new ArrayList<String>();
		Page<Article> bytlist = artireop.findByArticleType(articleType, pageable);
		for (int i = 0; i < bytlist.getContent().size(); i++) {
			int articleId = bytlist.getContent().get(i).getArticleId();
			int teacherId = bytlist.getContent().get(i).getTeacherId();
			Teacher teacher = teacherRepo.findByTeacherId(teacherId);
			String realname = teacher.getRealname();
			Integer like = artireop.selectTotalike(articleId).size();
			alist.add(like);
			blist.add(realname);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("bytlist", bytlist);
		map.put("alist", alist);
		map.put("blist", blist);
		return map;

	}

	@RequestMapping("/totype")
	public String todetype(String articleType) {
		List<Article> typearticle = artireop.findByArticleType(articleType);
		return "托福人分类页";
	}

	public void run(HttpServletRequest request, HttpServletResponse response) {
		System.out.println(1111);
		Cookie cookie1 = new Cookie("user", "范德萨");
		cookie1.setMaxAge(24 * 60 * 60);
		response.addCookie(cookie1);

		Cookie[] cookies = request.getCookies();
		System.out.println(cookies);
		if (null != cookies) {
			for (Cookie cookie : cookies) {
				if ("user".equals(cookie.getName())) {
					String value = cookie.getValue();					
					System.out.println(value);
				}
			}
		}
	}
	
	


}
