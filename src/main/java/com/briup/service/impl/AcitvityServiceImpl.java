package com.briup.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.briup.bean.Activity;
import com.briup.bean.Customer;
import com.briup.dao.ActivityDao;
import com.briup.service.IActivityService;

/** 
* @author 作者 angel: 
* @version 创建时间：2020年4月6日 下午9:03:09 
* 类说明  交往记录的service层
*/
@Service
public class AcitvityServiceImpl implements IActivityService {
	@Autowired
	private ActivityDao dao;
	
	@Override
	public void saveActivities(Activity activity) {
		dao.save(activity);
	}

	@Override
	public Page<Activity> findByCustomer(Integer cusId) {
		return findByCustomer(cusId,0);
	}

	@Override
	public Page<Activity> findByCustomer(Integer cusId, Integer pageIndex) {
		Customer customer = new Customer();
		customer.setId(cusId);
		return dao.findByCustomer(customer, PageRequest.of(pageIndex, 2));
	}

	@Override
	public Activity findById(Integer id) {
		return dao.getOne(id);
	}

	@Override
	public void deleteById(Integer id) {
		dao.deleteById(id);
	}

}
