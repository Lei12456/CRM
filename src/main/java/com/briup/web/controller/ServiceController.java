package com.briup.web.controller;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.briup.bean.Service;
import com.briup.bean.User;
import com.briup.service.ICustomerService;
import com.briup.service.IServiceService;

/** 
* @author 作者 angel: 
* @version 创建时间：2020年4月16日 下午9:26:27 
* 类说明 
*/
@Controller
public class ServiceController {
	@Autowired
	private IServiceService sservice;
	@Autowired
	private ICustomerService cusService;
	
	@RequestMapping("toService")
	public String toService(HttpSession session,String cusName,String type) {
		session.setAttribute("cusName", cusName);
		session.setAttribute("type", type);
		User creator = (User)session.getAttribute("user");
		Page<Service> services = sservice.findAll(cusName, type, creator);
		session.setAttribute("services", services);
		session.setAttribute("customers_service", cusService.findAllByManager(creator));
		return "pages/service";
	}
	//进入服务详情页
	@RequestMapping("toServiceDetails")
	public String toServiceDetail(Integer id,HttpSession session) {
		session.setAttribute("service", findServiceById(id));	
		return "pages/serviceDetails";
	}
	@RequestMapping("updateServicePage")
	public String updatePage(HttpSession session,Integer pageIndex) {
		String cusName = (String)session.getAttribute("cusName");
		String type = (String)session.getAttribute("type");
		User creator = (User)session.getAttribute("user");
		Page<Service> services = sservice.findAll(cusName, type, creator, pageIndex);
		session.setAttribute("services", services);
		return "pages/service";
	}
	
	@RequestMapping("saveService")
	@ResponseBody
	public String saveService(Service service) {
		if (service.getId()==null) {
			sservice.saveService(service);
			return "添加成功";
		}
		else {
			sservice.saveService(service);
			return "修改成功";
		}
		
	}
	@RequestMapping("updateStatus")
	@ResponseBody
	public String updateStatus(Integer id) {
		sservice.updateServiceByStatus(id);
		return "修改完成";
	}
	@RequestMapping("findServiceById")
	@ResponseBody
	public Service findServiceById(Integer id) {
		return sservice.findServiceById(id);
	}
	
}
