package org.rrtf.flcb.controller;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CookieController {
	@RequestMapping("/setcookie/{name}/{value}")
	public void addCookie(HttpServletResponse response, @PathVariable("name") String name, @PathVariable("value") String value) {
			Cookie cookie = new Cookie(name, value);
			cookie.setPath("/");
			int maxAge = 3600;
			cookie.setMaxAge(maxAge);
			response.addCookie(cookie);
	}
	
	@RequestMapping("/getcookie/{name}")
	public String getCookieValue(HttpServletRequest request, @PathVariable("name") String name) {
		Cookie[] cookies = request.getCookies();
		for(Cookie c :cookies ){
			if(c.getName().equals("user")) {
				System.out.println(c.getName()+"--->"+c.getValue());
			}
		}
		return "tuoFuTest/系统管理员登录页";
	}



}
