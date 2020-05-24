package com.briup.web.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.briup.bean.Customer;
import com.briup.bean.User;
import com.briup.service.ICustomerService;
import com.briup.service.IUserService;

/** 
* @author 作者 angel: 
* @version 创建时间：2020年4月3日 下午3:11:53 
* 类说明 客户信息相关的web层
*/
@Controller
@RequestMapping("customer")
public class CustomerController {
	@Autowired
	private ICustomerService cusService;
	
	@RequestMapping("toCustomer")
	public String toCustomer(HttpSession session,
			String name,String region,String level) {
		session.setAttribute("name", name);
		session.setAttribute("region", region);
		session.setAttribute("level", level);
		User manager = (User) session.getAttribute("user");
		session.setAttribute("customers", cusService.findCustomer(name, level, region,manager));
		return "pages/customer";
	}
	//分页查询
	@RequestMapping("updatePage")
	public String selectPage(HttpSession session,Integer pageIndex) {
		User manager = (User) session.getAttribute("user");
		String name = (String)session.getAttribute("name");
		String region = (String)session.getAttribute("region");
		String level = (String)session.getAttribute("level");
		
		session.setAttribute("customers", cusService.findCustomer(name,level,region,manager,pageIndex));
		return "pages/customer";
	}
	
	@RequestMapping("saveCustomer")
	@ResponseBody
	public String saveCustomer(Customer customer) {
		if(customer.getId()==null) {
			cusService.saveCustomer(customer);
			return "添加成功";
		}else {
			cusService.saveCustomer(customer);
			return "修改成功";
		}

		
	}
	@RequestMapping("findById")
	@ResponseBody
	public Customer findById(Integer id) {
		return cusService.findById(id);
	}
	
	
	@RequestMapping(value = "deleteCus",method = RequestMethod.DELETE)
	@ResponseBody
	public String deleteCus(Integer id) {
		cusService.deleteById(id);
		return "删除成功";
	}
	
	
	
	
	
}
