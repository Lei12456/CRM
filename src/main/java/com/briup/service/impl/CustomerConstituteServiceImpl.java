package com.briup.service.impl;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.bean.Customer;
import com.briup.bean.CustomerConstitute;
import com.briup.dao.CustomerDao;
import com.briup.service.ICustomerConstituteService;

/** 
* @author 作者 angel: 
* @version 创建时间：2020年4月3日 上午10:44:31 
* 类说明 
*/
@Service
public class CustomerConstituteServiceImpl implements ICustomerConstituteService{
	
	@Autowired
	private CustomerDao cusDao;
	@Override
	public List<CustomerConstitute> regionAnalyze() {
		//查询出所有信息
		List<CustomerConstitute> list = new ArrayList<>();
		List<Customer> customers = cusDao.findAll();
		float zong = customers.size();
		String regions[] = {"华中","华北","华南","华东"};
		for (String region : regions) {
			float num = cusDao.findByRegion(region).size();
			float y = num/zong * 100;
			CustomerConstitute customerConstitute = new CustomerConstitute(region,y,region);
			list.add(customerConstitute);
		}
		return list;
	}

	@Override
	public List<CustomerConstitute> levelAnalyze() {
		List<CustomerConstitute> list = new ArrayList<>();
		float zong = cusDao.findAll().size();
		String levels[] = {"普通客户","重点开发客户","大客户","合作伙伴","战略合作伙伴"};
		for (String level : levels) {
			float num = cusDao.findByLevel(level).size();
			float y = num/zong * 100;
			CustomerConstitute customerConstitute = new CustomerConstitute(level,y,level);
			list.add(customerConstitute);
		}
		return list;
	}
	
}
