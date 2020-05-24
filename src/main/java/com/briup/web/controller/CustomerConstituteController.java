package com.briup.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.briup.bean.CustomerConstitute;
import com.briup.service.ICustomerConstituteService;
import com.briup.service.ICustomerService;

/** 
* @author 作者 angel: 
* @version 创建时间：2020年4月3日 上午10:42:02 
* 类说明 客户构成分析的Controller
*/
@Controller
public class CustomerConstituteController {
	@Autowired
	private ICustomerConstituteService service;
	
	
	@RequestMapping("toCustomerConstitute")
	public String toCustomerConstitute() {
		return "pages/customerConstitute";
	}
	
	@RequestMapping("regionAnalyze")
	@ResponseBody
	public List<CustomerConstitute> regionAnalyze() {
		return service.regionAnalyze();
	}
	
	@RequestMapping("levelAnalyze")
	@ResponseBody
	public List<CustomerConstitute> levelAnalyze() {
		return service.levelAnalyze();
	}
}
