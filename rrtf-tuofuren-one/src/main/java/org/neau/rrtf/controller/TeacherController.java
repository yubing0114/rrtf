package org.neau.rrtf.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.neau.rrtf.ArtiJPA.ArticleRepository;
import org.neau.rrtf.ArtiJPA.TeacherRepo;
import org.neau.rrtf.ArtiJPA.UserRepository;
import org.neau.rrtf.ArtiJPA.articleDoRepo;
import org.neau.rrtf.Entity.Article;
import org.neau.rrtf.Entity.Teacher;
import org.neau.rrtf.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Controller
public class TeacherController {
	@Value("${upload.location}")
	String UPLOAD_LOCATION;
	@Autowired
	ArticleRepository artireop;
	@Autowired
	TeacherRepo teacherRepo;
	@Autowired
	articleDoRepo artrepo;
	@Autowired
	UserRepository userrepo;

	// 搜索我发布的文章(此处的我是老师
	@RequestMapping("/searchMypub")
	@ResponseBody
	public Map<String, Object> ToMyTOFU(HttpServletRequest request, HttpServletResponse response, Integer no) {
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
		/* User user = (User) session.getAttribute("user"); */
		List<Integer> alist = new ArrayList<Integer>();
		int id = user.getUserId();
		Teacher teacher = teacherRepo.findByUserId(id);
		int teacherId = teacher.getTeacherId();
		String realname = teacher.getRealname();
		int size = 5;
		Pageable pageable = new PageRequest(no - 1, size);
		Page<Article> mylist = artireop.findByTeacherIdOrderByRegtime(teacherId, pageable);
		for (int i = 0; i < mylist.getContent().size(); i++) {
			int articleId = mylist.getContent().get(i).getArticleId();
			Integer like = artireop.selectTotalike(articleId).size();
			alist.add(like);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("realname", realname);
		map.put("mylist", mylist);
		map.put("alist", alist);

		return map;
	}

	// 用户中心-我的托福人 教师删除文章
	@RequestMapping("/deletemyart")
	@ResponseBody
	public int deletemyart(Integer articleId) {
		artireop.deleteart(articleId);
		System.out.println(articleId + "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		return 1;

	}

	// 进入编辑文章页面
	@RequestMapping("/updatemyart")
	public String updatemyart(int articleId, Model model) {
		Article article = artireop.findByArticleId(articleId);
		model.addAttribute("article", article);
		return "文章编辑页";
	}

	// 托福人发布页
	@RequestMapping("/pubArt")
	public String ToPub(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("articlePicture") MultipartFile file, Model model) throws IOException {
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
		Article article = new Article();
		byte[] bytes = file.getBytes();
		String articlePicture = file.getOriginalFilename();
		Path path = Paths.get(UPLOAD_LOCATION, articlePicture);
		Files.write(path, bytes);
		String articleType = request.getParameter("articleType");
		String articleTitle = request.getParameter("articleTitle");
		String articleDetail = request.getParameter("articleDetail");
		article.setArticlePicture(articlePicture);
		article.setArticleTitle(articleTitle);
		article.setArticleType(articleType);
		article.setStatus(0);
		article.setPageview(0);
		article.setArticleDetail(articleDetail);

		article.setRegtime(new Timestamp(System.currentTimeMillis()));

		int id = user.getUserId();
		Teacher teacher = teacherRepo.findByUserId(id);
		int teacherId = teacher.getTeacherId();
		List<Article> alllist = artireop.findByTeacherId(teacherId);
		article.setTeacherId(teacherId);
		artireop.save(article);
		model.addAttribute("article", article);
		System.out.println(article.toString());
		return "用户中心-我的托福人";
	}

	// 修改编辑文章
	@RequestMapping("/upsave")
	@ResponseBody
	public String upsave(HttpServletRequest request) throws IOException {
		int articleId = Integer.parseInt(request.getParameter("articleId"));
		Article article = artireop.findByArticleId(articleId);
		File path1 = new File(ResourceUtils.getURL("classpath:").getPath());
		File upload = new File(path1.getAbsolutePath(), "static/i/");
		article.setArticleType(request.getParameter("articleType"));
		article.setArticleTitle(request.getParameter("articleTitle"));
		article.setArticleDetail(request.getParameter("articleDetail"));
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		MultipartFile file = multipartRequest.getFile("myFile");
		if (file != null)
			if (!file.isEmpty()) {// 如果非空,也就是说用户修改了图片
				String filename = file.getOriginalFilename();
				String fileForm = filename.substring(filename.lastIndexOf("."));
				// 文件重命名
				String newFilename = UUID.randomUUID().toString() + fileForm;
				// 保存文件
				Path path = Paths.get(upload.getAbsolutePath(), newFilename);
				byte[] bytes = file.getBytes();

				Files.write(path, bytes);// 写入最终保存的位置
				article.setArticlePicture(newFilename);

			}
		article.setArticleId(articleId);
		artireop.save(article);
		return "保存成功!";

	}

	// 我最近发布的文章 排序
	@RequestMapping("/descmypub")
	@ResponseBody
	public Map<String, Object> descpub(HttpServletRequest request, HttpServletResponse response, Integer no) {
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
		List<Integer> alist = new ArrayList<Integer>();
		int id = user.getUserId();
		Teacher teacher = teacherRepo.findByUserId(id);
		int teacherId = teacher.getTeacherId();
		String realname = teacher.getRealname();
		int size = 5;
		Pageable pageable = new PageRequest(no - 1, size);
		System.out.println(pageable);
		Page<Article> mydesclist = artireop.findByTeacherIdOrderByRegtimeDesc(teacherId, pageable);

		// 从user_article中间表根据articleId查询这篇文章有多少人收藏(我写复杂了，好像countBy就可以
		for (int i = 0; i < mydesclist.getContent().size(); i++) {
			int articleId = mydesclist.getContent().get(i).getArticleId();
			Integer like = artireop.selectTotalike(articleId).size();
			alist.add(like);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("realname", realname);
		map.put("mydesclist", mydesclist);
		map.put("alist", alist);
		return map;
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
					String value2 = cookie.getValue();
					System.out.println(value);
					System.out.println(value2);
				}
			}
		}
	}

}
