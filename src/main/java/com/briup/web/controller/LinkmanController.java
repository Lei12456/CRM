package com.briup.web.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.briup.bean.Linkman;
import com.briup.service.ILinkmanService;

/** 
* @author 作者 angel: 
* @version 创建时间：2020年4月5日 下午10:26:50 
* 类说明 
*/
@Controller
@RequestMapping("linkman")
public class LinkmanController {
	@Autowired
	private ILinkmanService service;
	
	@RequestMapping("toLinkman")
	public String toLinkman(HttpSession session,Integer cusId) {
		session.setAttribute("cusId", cusId);
		session.setAttribute("linkmans", service.findAllLinkman(cusId));
		return "pages/linkman";
	}
	@RequestMapping("updatePage")
	public String updatePage(HttpSession session,Integer pageIndex) {
		Integer cusId = (Integer)session.getAttribute("cusId");
		session.setAttribute("linkmans", service.findAllLinkman(cusId,pageIndex));
		return "pages/linkman";
	}
	@RequestMapping("saveLinkman")
	@ResponseBody
	public String saveLinkman(Linkman linkman) {
		if(linkman.getId()==null) {
			service.saveLinkman(linkman);
			return "添加成功";
		}else {
			service.saveLinkman(linkman);
			return "修改成功";
		}
	}
	@RequestMapping("findById")
	@ResponseBody
	public Linkman findById(Integer id) {
		return service.findById(id);
	}
	
	@RequestMapping("deleteById")
	@ResponseBody
	public String deleteById(Integer id) {
		service.deleteById(id);
		return "删除成功";
	}
	
	
}	
