package com.briup.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.briup.bean.Customer;
import com.briup.bean.User;
import com.briup.dao.CustomerDao;
import com.briup.service.ICustomerService;

/** 
* @author 作者 angel: 
* @version 创建时间：2020年4月1日 上午11:10:13 
* 类说明  客户模块的service 实现类
*/
@Service
public class CustomerServiceImpl implements ICustomerService {
	@Autowired
	private CustomerDao cusDao;

	@Override
	public Page<Customer> findCustomer(String name, String level,String region,User manager) {
		return findCustomer(name, level, region,manager, 0);
	}

	@Override
	public Page<Customer> findCustomer(String name, String level, String region,User manager, Integer pageIndex) {
		if(name!=null && name.trim()!="") {
			return cusDao.findByNameLike(name,manager, PageRequest.of(pageIndex, 2));
		}
		if(level!=null && level.trim()!=""){
			return cusDao.findByLevelAndManager(level,manager, PageRequest.of(pageIndex, 2));
		}
		if(region!=null && region.trim()!=""){
			return cusDao.findByRegionAndManager(region,manager, PageRequest.of(pageIndex, 2));
		}
		if(name==null && region == null && level == null){
			return cusDao.findByManager(manager,PageRequest.of(pageIndex, 2));
		}
		return null;
	}

	
	
	@Override
	public void saveCustomer(Customer customer) {
		cusDao.save(customer);
	}

	@Override
	public Customer findById(Integer id) {
		return cusDao.getOne(id);
	}

	@Override
	public void deleteById(Integer id) {
		cusDao.deleteById(id);
	}

	@Override
	public List<Customer> findAllByManager(User manager) {
		return cusDao.findByManager(manager);
	}
	

}
