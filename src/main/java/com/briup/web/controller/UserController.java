package com.briup.web.controller;



import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.briup.bean.Role;
import com.briup.bean.User;
import com.briup.service.IRoleService;
import com.briup.service.IUserService;

/** 
* @author 作者 angel: 
* @version 创建时间：2020年3月26日 下午4:32:17 
* 类说明 
*/
@Controller
public class UserController {
	
	@Autowired
	private IUserService service;
	
	@Autowired
	private IRoleService roleService;
	
	//获取前台用户名和密码 进行验证
	@SuppressWarnings("unused")
	@RequestMapping("user/login")
	@ResponseBody
	public String login(String name,String password,HttpSession session){
		
		User user = service.findByName(name);
		session.setAttribute("user", user);
//		if(user.getRole().getId() == 3){
//		session.setAttribute("loginName", user.getName());
//		session.setAttribute("loginId", user.getId());
//		}
		
		if (user == null) {
			return "用户名不存在！";
		}		
		//判断密码是否正确
		if(!user.getPassword().equals(password)) {
			return "密码错误";	
		}
		//判断用户状态
		if(user.getFlag()!=1) {
			return "用户名已经注销";
		}
		return "success";
	}
	//退出功能
	@RequestMapping("logout")
	public String logout(HttpSession session){
		session.removeAttribute("user");
		session.invalidate();  
		return "login";
	}
	//显示所有用户
	@RequestMapping("toUser")
	public String toUser(HttpSession session,Integer roleId){
		session.setAttribute("roleId", roleId);
		session.setAttribute("users", service.findUsersByRole(roleId));
		session.setAttribute("roles_user", roleService.findRoles());
		return "pages/user";
	}
	
	//分页管理
	@RequestMapping("selectPage")
	public String selectPage(Integer pageIndex,HttpSession session){
		Integer roleId = (Integer) session.getAttribute("roleId");
		Page<User> users = service.findUsersByRole(roleId, pageIndex);
		session.setAttribute("users", users);
		return "pages/user";
	}
	//重置
	@RequestMapping("resetUser")
	@ResponseBody
	public String resetUser(HttpSession session){
		session.removeAttribute("roleId");
		return "重置成功";
	}
		
	
	
	//新增用户
	@RequestMapping("saveUser")
	@ResponseBody
	public String saveUser(User user){
		if (user.getId()==null) {
			service.saveUser(user);
			return "添加成功";
		}else {
			service.saveUser(user);
			return "修改成功";
		}
		
		
	}
	//通过Id查询用户
	@RequestMapping("findUserById")
	@ResponseBody
	public User findUserById(Integer id){
		return service.findUserById(id);
	}
	//通过Id删除用户
	@RequestMapping("deleteUserById")
	@ResponseBody
	public String deleteUserById(Integer id){
		service.deleteUserById(id);
		return "删除成功";
	}

}
