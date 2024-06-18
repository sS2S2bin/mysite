package com.poscodx.mysite.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.poscodx.mysite.dto.JsonResult;
import com.poscodx.mysite.service.UserService;
import com.poscodx.mysite.vo.UserVo;

@Controller("userApiController")
@RequestMapping("/user/api")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@ResponseBody
	@RequestMapping("/checkemail")
	public JsonResult checkEmail(@RequestParam(value="email", required=true, defaultValue="") String email) {
		UserVo vo = userService.getUser(email);
		
		return JsonResult.success(vo!=null);
	}
}