package org.rrtf.flcb.controller;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import org.rrtf.flcb.dao.FlcbkcxxbRepository;
import org.rrtf.flcb.dao.UserRepository;
import org.rrtf.flcb.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	
	@Autowired
	FlcbkcxxbRepository flcbRepo;
	@Autowired
	UserRepository userRepo;
	
	@RequestMapping("/index")
	public String index() {
		
			return "tuoFuTest/首页";
	}
	
	@RequestMapping("/fabu")
	public String fabu() {
		return "tuoFuTest/福利城堡/福利城堡-发布";
	}
	
	@RequestMapping("/flcb")
	public String flcb() {
		return "tuoFuTest/福利城堡/福利城堡-福利城堡";
	}
	
	@RequestMapping("/hdxx")
	public String hdxx() {
		return "tuoFuTest/福利城堡/福利城堡-活动详细页";
	}
	
	@RequestMapping("/yhzx-wdflcb")
	public String wdflcb() {
		return "tuoFuTest/个人资料/用户中心-我的福利城堡";
	}
	
	@RequestMapping("/pay1")
	public String pay1() {
		return "tuoFuTest/支付/购买流程-步骤一";
	}
	
	@RequestMapping("/pay2")
	public String pay2() {
		return "tuoFuTest/支付/购买流程-步骤二";
	}
	
	@RequestMapping("/pay3")
	public String pay3() {
		return "tuoFuTest/支付/购买流程-步骤三";
	}
	
	@RequestMapping("/admin-flcb")
	public String adminFlcb() {
		return "tuoFuTest/系统管理员/系统管理-福利城堡列表";
	}
	
	@RequestMapping("/admin-index")
	public String daminIndex() {
		return "tuoFuTest/系统管理员登录页";
	}
}
