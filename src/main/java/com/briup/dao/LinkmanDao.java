package com.briup.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.briup.bean.Customer;
import com.briup.bean.Linkman;

/** 
* @author 作者 angel: 
* @version 创建时间：2020年4月5日 下午10:54:20 
* 类说明 
*/
public interface LinkmanDao  extends JpaRepository<Linkman, Integer>{
	Page<Linkman> findByCustomer(Customer customer,Pageable page);
}
