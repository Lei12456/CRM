package com.briup.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.briup.bean.Chance;
import com.briup.bean.Plan;
import com.briup.bean.User;
import com.briup.dao.ChanceDao;
import com.briup.dao.PlanDao;
import com.briup.service.IPlanService;

/** 
* @author 作者 angel: 
* @version 创建时间：2020年4月1日 下午10:24:31 
* 类说明 
*/
@Service 
public class PlanServiceImpl implements IPlanService{
	@Autowired 
	private PlanDao planDao;
	@Autowired
	private ChanceDao chDao;
	
	@Override
	public Page<Chance> findAllChances(String address,User handler) {
		return findAllChances(address,handler,0);
	}

	@Override
	public Page<Chance> findAllChances(String address,User handler,Integer pageIndex) {
		if(address!=null && address.trim()!="") {
			return chDao.findByHandlerAndAddress(handler, address,PageRequest.of(pageIndex, 2));
		}else {
			return chDao.findByHandler(handler, PageRequest.of(pageIndex, 2));
		}
		
	}

	@Override
	public void savePlan(Plan plan) {
		planDao.save(plan);
	}

	@Override
	public void updateChanceById(Integer id) {
		chDao.updateChanceById(id);
	}
	@Override
	public void updateChanceById2(Integer id) {
		chDao.updateChanceById2(id);
	}
	@Override
	public List<Plan> findPlanByChance(Chance chance) {
		return planDao.findByChance(chance);
	}

	@Override
	public void deletePlanById(Integer id) {
		planDao.deleteById(id);
	}

	
	
}
