package org.rrtf.group.service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.rrtf.group.dao.TeacherDao;
import org.rrtf.group.dao.UserDao;
import org.rrtf.group.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;

@Component
public class RoleTools {
	
	@Autowired
	private HttpServletResponse response;
	@Autowired
	private HttpServletRequest request;

	@Autowired//尝试
    private RedisTemplate redisTemplate;
	
	@Autowired//查找teacher表用的,被调用了,暂时先放这里
	TeacherDao teacherDao;
	@Autowired//查找user表用的,被调用了,暂时先放这里
	UserDao userDao;
	
	//基础,从cookie中获取String
	private String readCookie(String name) {
		//System.out.println(response+"-----"+request);
		Cookie[] cookies = request.getCookies();
		if (null != cookies) {
			for (Cookie cookie : cookies) {
				if(cookie.getName().equals(name)) {
					System.out.println("cookie的值:"+cookie.getValue());
					return cookie.getValue();
				}
			}
		}
		return null;
	}
	//基础,从redis中获取user
	private User getUser(String username) {
		ValueOperations opsForValue = redisTemplate.opsForValue();
		JSONObject object = (JSONObject) opsForValue.get(username);
		if(object!=null) {
			User user = object.toJavaObject(User.class);
			System.out.println("user--"+user);
			return user;
			//return ((JSONObject) redisTemplate.opsForValue().get(username)).toJavaObject(User.class);
		}
		return null;
	}
	//基础,获取userId
	public int getSessionUserId() {
		addCookie();//创建cookie,测试用
		String username = readCookie("name");//从cookie中获取-username为"user"
		System.out.println(username);
		if(username!=null) {
			User user = getUser(username);
			System.out.println(user);
			if(user!=null) {
				System.out.println("userId"+user.getUserId());
				return user.getUserId();//从redis中获取-userId为2
			}
		}
		return -1;
	}
	//偷懒,看是否登陆(这里的name后期要改)
	public boolean isLanding() {
		addCookie();//创建cookie,临时方法
		String username = readCookie("name");//从cookie中获取-username为"user"
		System.out.println(username!=null&&getUser(username)!=null);
		return username!=null&&getUser(username)!=null;//两个都非空则处于登录状态
	}
	//偷懒,看是否管理员(这里的name2后期要改)
	public boolean isAdmin() {
		addCookie2();//创建cookie,临时方法
		String adminname = readCookie("name2");
		System.out.println(adminname);
		System.out.println(getUser(adminname));
		boolean isAdmin = adminname!=null&&getUser(adminname)!=null;
		System.out.println("是管理员吗?"+isAdmin);
		return isAdmin;//两个都非空是管理员
	}
	//临时方法添加cookie
	public void addCookie() {
		Cookie cookie = new Cookie("name", "admin");//(key,value)
	    cookie.setPath("/");// 这个要设置
	    cookie.setMaxAge(365 * 24 * 60 * 60);// 不设置的话，则cookies不写入硬盘,而是写在内存,只在当前页面有用,以秒为单位
	    //cookie.setMaxAge(0);
	    response.addCookie(cookie);//添加Cookie
	}
	//临时方法添加cookie
	public void addCookie2() {
		Cookie cookie = new Cookie("name2", "user1");//(key,value)
	    cookie.setPath("/");// 这个要设置
	    cookie.setMaxAge(365 * 24 * 60 * 60);// 不设置的话，则cookies不写入硬盘,而是写在内存,只在当前页面有用,以秒为单位
	    //cookie.setMaxAge(0);
	    response.addCookie(cookie);//添加Cookie
	}
}
