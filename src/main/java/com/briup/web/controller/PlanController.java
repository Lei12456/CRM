package com.briup.web.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.briup.bean.Chance;
import com.briup.bean.Plan;
import com.briup.bean.User;
import com.briup.service.IChanceService;
import com.briup.service.IPlanService;

/** 
* @author 作者 angel: 
* @version 创建时间：2020年4月1日 下午9:29:06 
*  类说明 商机开发计划的web
*/
@Controller
public class PlanController {
	@Autowired
	private IPlanService planService;
	@Autowired
	private IChanceService chService;
	@RequestMapping("toPlan")
	public String toPlan(HttpSession session,String address,Integer id,Integer id_edit) {
		User handler = (User)session.getAttribute("user");
		session.setAttribute("address", address);
		//这里是更新销售商机状态变为处理中
		planService.updateChanceById(id);
		planService.updateChanceById2(id_edit);
		session.setAttribute("plan_Chances",planService.findAllChances(address,handler));
		return "pages/plan";
	}
	@RequestMapping("toPlan_add")
	public String toPlan_add(HttpSession session,Integer id) {
		session.setAttribute("plan_add_chance", chService.findById(id));
		return "pages/plan_add";
	}
	@RequestMapping("toPlan_edit")
	public String toPlan_edit(HttpSession session,Integer id) {
		Chance chance = new Chance();
		chance.setId(id);
		//查询当前销售商机的所有开发计划
		session.setAttribute("plan_edit_chance", chService.findById(id));
		session.setAttribute("plans",planService.findPlanByChance(chance));
		return "pages/plan_edit";
	}
	@RequestMapping("toPlan_detail")
	public String toPlan_detail(HttpSession session,Integer id) {
		Chance chance = new Chance();
		chance.setId(id);
		session.setAttribute("plan_detail_chance", chService.findById(id));
		session.setAttribute("plans_detail",planService.findPlanByChance(chance));
		return "pages/plan_detail";
	}
	@RequestMapping("updatePageByPlan")
	public String updatePageByPlan(HttpSession session,Integer pageIndex) {
		User handler = (User)session.getAttribute("user");
		String address = (String)session.getAttribute("address");
		session.setAttribute("plan_Chances", planService.findAllChances(address,handler,pageIndex));
		return "pages/plan";
	}
	//保存或者更新开发计划
	@RequestMapping("saveOrUpdatePlan")
	@ResponseBody
	public String saveOrUpdatePlan(Plan plan) {
		if(plan.getId() == null) {
			planService.savePlan(plan);
			return "添加成功";
		}else {
			planService.savePlan(plan);
			return "修改成功";
		}
	}

	//通过Id删除开发计划
	@RequestMapping("deletePlanById")
	@ResponseBody
	public String deletePlanById(Integer id) {
		planService.deletePlanById(id);
		return "删除成功";
	}

	
}
