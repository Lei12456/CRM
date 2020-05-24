package com.briup.web.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.briup.bean.Chance;
import com.briup.bean.User;
import com.briup.service.IChanceService;
import com.briup.service.IUserService;

/** 
* @author 作者 angel: 
* @version 创建时间：2020年4月1日 下午1:48:09 
* 类说明  销售商机的controller
*/
@Controller
public class ChanceController {
		@Autowired
		private IChanceService chService;
		@Autowired
		private IUserService userService;
	   //显示刚进去的所有用户所有用户
		@RequestMapping("toSales")
		public String toUser(HttpSession session,String customerName,String address){
			session.setAttribute("customerName", customerName);
			session.setAttribute("address", address);
			session.setAttribute("chances", chService.findAllChances(customerName, address));
			session.setAttribute("jinlis", userService.findUsersByRole(4));
			return "pages/sales";
		}
		
		@RequestMapping("updatePage")
		public String updatePage(HttpSession session,Integer pageIndex){
			String name  = (String) session.getAttribute("customerName");
			String address  = (String) session.getAttribute("address");
			session.setAttribute("chances", chService.findAllChances(name,address,pageIndex));
			return "pages/sales";
		}
		
		
		
		//添加商机
		@RequestMapping("saveChance")
		@ResponseBody
		public String saveChance(Chance chance){
			if(chance.getId()==null) {
				chService.saveChance(chance);
				return "添加成功";
			}else {
				chService.saveChance(chance);
				return "修改成功";
			}
			
			
		}
		//通过Id查找
		@RequestMapping("findChanceById")
		@ResponseBody
		public Chance findChanceById(Integer id){
			return chService.findById(id);
		}
		//重置页面
		@RequestMapping("resetSale")
		@ResponseBody
		public String resetSale(HttpSession session){
			session.removeAttribute("customeName");
			session.removeAttribute("address");
			return "重置成功";
		}
		//通过Id删除
		@RequestMapping("deleteById")
		@ResponseBody
		public String deleteById(Integer id){
			chService.delelteById(id);
			return "删除成功";
		}
		
		
		
		
		
		
		
		
		
		
		
		
}
