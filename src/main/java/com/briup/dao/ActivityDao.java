package com.briup.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.briup.bean.Activity;
import com.briup.bean.Customer;

/** 
* @author 作者 angel: 
* @version 创建时间：2020年4月6日 下午9:04:35 
* 类说明 
*/
public interface ActivityDao extends JpaRepository<Activity, Integer>{
	
	Page<Activity> findByCustomer(Customer customer,Pageable page);
	
	
	
}
