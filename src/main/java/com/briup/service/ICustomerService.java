package com.briup.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.briup.bean.Customer;
import com.briup.bean.User;

/** 
* @author 作者 angel: 
* @version 创建时间：2020年4月1日 上午10:43:33 
* 类说明  
*/
public interface ICustomerService{

 	//点到客户信息的时候查询
	Page<Customer> findCustomer(String name,String level,String region,User manager);
	//对查询的信息进行分页管理
	Page<Customer> findCustomer(String name,String level,String region,User manager,Integer pageIndex);
	//用过Id查询客户
	Customer findById(Integer id);
	//通过Id删除用户
	void deleteById(Integer id);
	//添加客户
	void saveCustomer(Customer customer);
	
	//通过经理查询旗下所有的客户
	List<Customer> findAllByManager(User manager);

}
