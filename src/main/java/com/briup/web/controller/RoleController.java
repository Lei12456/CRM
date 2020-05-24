package com.briup.web.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.briup.bean.Role;
import com.briup.service.IRoleService;

/** 
* @author 作者 angel: 
* @version 创建时间：2020年3月27日 上午11:51:05 
* 类说明 :角色的web层代码，角色管理模块
*/
@Controller
public class RoleController {
	
	@Autowired
	private IRoleService service;
	
	@RequestMapping("toRole")
	public  String toRole(HttpSession session,Role role) {
		//查询数据库的角色 传给前台页面
		Page<Role> roles = service.findAllRoles();
		session.setAttribute("roles", roles);
		return "pages/role";
	}
	
	@RequestMapping("saveRole")
	@ResponseBody
	public  String saveRole(Role role) {
		service.saveOrUpdateRole(role);
		return "添加成功";
	}
	@RequestMapping("deleteRole")
	@ResponseBody
	public  String deleteRole(String id) {
		service.deleteRole(Integer.parseInt(id));
		System.out.println(id);
		return "删除成功";
	}
	@RequestMapping("updateRole")
	@ResponseBody
	public  String updateRole(Role role) {
		System.out.println(role);
		service.saveOrUpdateRole(role);
		return "修改成功";
	}
	@RequestMapping("fenyeRole")
	public  String fenyeRole(Integer pageIndex,HttpSession session) {
		Page<Role> roles = service.findAllRoles(pageIndex);
		session.setAttribute("roles", roles);
		return "pages/role";
	}
	
	@RequestMapping("findRoleById")
	@ResponseBody
	public Role findRoleById(Integer id) {
		return service.findRoleById(id);
	}
}
