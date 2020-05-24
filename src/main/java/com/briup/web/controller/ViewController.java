package com.briup.web.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller; 
import org.springframework.web.bind.annotation.RequestMapping;

import com.briup.bean.User;
/**
 * 用来映射thymeaf页面
 * @author angel
 *
 */
@Controller
public class ViewController {
	@RequestMapping("login")
	public String toLogin() {
		return "login";
	}
	@RequestMapping("index")
	public String toIndex(HttpSession session) {
		User user = (User) session.getAttribute("user");
		if (user == null) {
			return "login";
		}
		return "index";
	}
	@RequestMapping("test")
	public String test() {
		return "test";
	}
}
