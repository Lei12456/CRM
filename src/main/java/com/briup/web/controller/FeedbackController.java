package com.briup.web.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.briup.bean.Service;
import com.briup.bean.User;
import com.briup.service.IServiceService;

import ch.qos.logback.core.status.Status;

/** 
* @author 作者 angel: 
* @version 创建时间：2020年4月18日 下午5:34:35 
* 类说明 
*/
@Controller
public class FeedbackController {
	@Autowired
	private IServiceService service;
	
	@RequestMapping("toFeedback")
	public String toFeedback(HttpSession session,String status,String type) {
		session.setAttribute("status", status);
		session.setAttribute("type", type);
		session.setAttribute("services_feedback", service.findAllService(type, status));
		return "pages/feedback";
	}
	@RequestMapping("toServiceDetails2")
	public String toServiceDetail2(Integer id,HttpSession session) {
		session.setAttribute("service_feedback", service.findServiceById(id));	
		return "pages/serviceDetails2";
	}
	@RequestMapping("updateFeedbackPage")
	public String updateFeedbackPage(HttpSession session,Integer pageIndex) {
		String status = (String)session.getAttribute("status");
		String type = (String)session.getAttribute("type");
		session.setAttribute("services_feedback", service.findAllService(type, status, pageIndex));
		return "pages/feedback";
	}
	
	@RequestMapping("updateService")
	@ResponseBody
	public String updateService(Integer id,String satisfy) {
		service.updateServiceById(id, satisfy);
		return "反馈成功";
	}
	
	
	
	
	
	
	
	
	
	
	
}
