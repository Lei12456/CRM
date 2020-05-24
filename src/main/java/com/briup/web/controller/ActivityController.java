package com.briup.web.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.briup.bean.Activity;
import com.briup.service.IActivityService;

/** 
* @author 作者 angel: 
* @version 创建时间：2020年4月6日 下午8:51:22 
* 类说明 
*/

@Controller
@RequestMapping("activity")
public class ActivityController {
	@Autowired
	private IActivityService service;
	
	@RequestMapping("toActivity")
	public String toActivity(HttpSession session,Integer cusId) {
		System.out.println(cusId);
		session.setAttribute("cusId", cusId);
		session.setAttribute("activities", service.findByCustomer(cusId));
		return "pages/activities";
	}
	@RequestMapping("updatePage")
	public String updatePage(HttpSession session,Integer pageIndex) {
		Integer cusId = (Integer)session.getAttribute("cusId");
		session.setAttribute("activities", service.findByCustomer(cusId,pageIndex));
		return "pages/activities";
	}
	
	@RequestMapping("saveActivity")
	@ResponseBody
	public String saveActivity(Activity activity) {
		if(activity.getId() == null) {
			service.saveActivities(activity);
			return "添加成功";
		}else {
			service.saveActivities(activity);
			return "修改成功";
		}
	
	}
	@RequestMapping("findById")
	@ResponseBody
	public Activity saveActivity(Integer id) {
		return service.findById(id);
	}
	@RequestMapping("deleteById")
	@ResponseBody
	public String deleteById(Integer id) {
		service.deleteById(id);
		return "删除成功";
	}
	
	
}
