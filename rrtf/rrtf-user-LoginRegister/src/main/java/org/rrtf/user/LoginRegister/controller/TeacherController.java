package org.rrtf.user.LoginRegister.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("teacher")
public class TeacherController {

	@RequestMapping("release")
	@RequiresPermissions("release")
	public String userinfo() {
		return "userinfo";
	}
}
