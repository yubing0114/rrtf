package org.neau.rrtf.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.neau.rrtf.ArtiJPA.ArticleRepository;
import org.neau.rrtf.ArtiJPA.TeacherRepo;
import org.neau.rrtf.Entity.Article;
import org.neau.rrtf.Entity.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AdminController {
	@Autowired
	ArticleRepository artireop;
	@Autowired
	TeacherRepo teacherepo;

	@RequestMapping("/bytit")
	@ResponseBody
	public Map<String, Object> findBytit(String articleType, String articleTitle, String realname, int no) {
		/*
		 * Teacher teacher = teacherepo.findByRealname(realname); int teacherId =
		 * teacher.getTeacherId();
		 */
		int size = 5;
		Map<String, Object> map = new HashMap<String, Object>();
		Pageable pageable = new PageRequest(no - 1, size);
		List<Integer> alist = new ArrayList<Integer>();
		Teacher teacher = teacherepo.findByRealname(realname);
		int teacherId = teacher.getTeacherId();
		//这里真的卡了我半天 服了!
		if (articleTitle == null || articleTitle.equals("")) {
			Page<Article> list = artireop.findByArticleTypeAndTeacherId(articleType, teacherId, pageable);
			for (int i = 0; i < 5; i++) {
				int articleId = list.getContent().get(i).getArticleId();
				Integer like = artireop.selectTotalike(articleId).size();
				alist.add(like);
				System.out.println(like);
			}
			map.put("list", list);
			map.put("alist", alist);
		}
		if (null != articleTitle && !articleTitle.equals("")) {

			Page<Article> list = artireop.findByArticleTitleAndArticleTypeAndTeacherId(articleTitle, articleType,
					teacherId, pageable);
			for (int i = 0; i < list.getContent().size(); i++) {
				int articleId = list.getContent().get(i).getArticleId();
				Integer like = artireop.selectTotalike(articleId).size();
				alist.add(like);
				System.out.println(like);
			}
				map.put("list", list);
				map.put("alist", alist);
		}

		 return map;
	}

		@RequestMapping("/byType")
		@ResponseBody
		public Map<String, Object> findBytype(String articleType, int no) {
			int size = 5;
			Pageable pageable = new PageRequest(no - 1, size);
			Page<Article> typelist = artireop.findByArticleType(articleType, pageable);
			List<Integer> alist = new ArrayList<Integer>();
			List<String> namelist = new ArrayList<String>();
			System.out.println(typelist.getTotalElements());
			System.out.println(typelist.getContent());
			for (int i = 0; i < typelist.getContent().size(); i++) {
				Article article = typelist.getContent().get(i);
				int teacherId = article.getTeacherId();
				int articleId = article.getArticleId();
				Integer like = artireop.selectTotalike(articleId).size();
				alist.add(like);
				Teacher teacher = teacherepo.findByTeacherId(teacherId);
				String realname = teacher.getRealname();
				namelist.add(realname);
			}
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("typelist", typelist);
				map.put("namelist", namelist);
				map.put("alist", alist);
				return map;
	
		}

		@RequestMapping("/allart/{no}")
		@ResponseBody
		public Map<String, Object> findall(@PathVariable("no") int no) {
			System.out.println(no);
			int size = 5;
			Pageable pageable = new PageRequest(no - 1, size);
			List<String> namelist = new ArrayList<String>();
			Page<Article> allist = artireop.findAll(pageable);
			for (int i = 0; i < allist.getContent().size(); i++) {
			   Article article = allist.getContent().get(i);
			   int teacherId = article.getTeacherId();
			   Teacher teacher = teacherepo.findByTeacherId(teacherId);
			   String realname = teacher.getRealname();
			   namelist.add(realname);
			}
			  Map<String, Object> map = new HashMap<String, Object>();
			  map.put("allist", allist);
			  map.put("namelist", namelist);
			  return map;
		}

		
			@RequestMapping("/delete")
			@ResponseBody
			public String remove(@RequestParam("ids") List<Integer> ids) {
					System.out.println(ids.toString());
					artireop.deleteArticle(ids);
					System.out.println(ids.toString());
					return "success";
		
			}
		
			
			@RequestMapping("/update")
			@ResponseBody
			public String frezze(int status, @RequestParam("ids") List<Integer> ids) {
					artireop.updateByStatus(status, ids);
					return "wonderful!";
		
			}
		
			@RequestMapping("/pageview")
			@ResponseBody
			public String totalpage(int articleId) {
				artireop.updatePagev(articleId);
				return "update success!";
			}
		
		}
