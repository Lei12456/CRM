package com.briup.service;


import java.util.List;

import org.springframework.data.domain.Page;

import com.briup.bean.Chance;
import com.briup.bean.Plan;
import com.briup.bean.User;

/** 
* @author 作者 angel: 
* @version 创建时间：2020年4月1日 下午10:21:41 
* 类说明 商机开发计划
*/
public interface IPlanService {
	//查询所有计划
	Page<Chance> findAllChances(String address,User handler);
	//分页查询
	Page<Chance> findAllChances(String address,User handler,Integer pageIndex);
	//添加开发计划
	void savePlan(Plan plan);
	//修改销售商机状态为处理中
	void updateChanceById(Integer id);
	//修改销售商机状态为已处理完
	void updateChanceById2(Integer id);
	//查询当前销售商机的开发计划
	List<Plan> findPlanByChance(Chance chance);
	//通过Id删除开发计划
	void deletePlanById(Integer id);
		
}
