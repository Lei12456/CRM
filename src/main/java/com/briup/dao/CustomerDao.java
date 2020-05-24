package com.briup.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.briup.bean.Customer;
import com.briup.bean.User;

/** 
* @author 作者 angel: 
* @version 创建时间：2020年4月1日 上午11:11:10 
* 类说明 与客户相关的dao层
*/
public interface CustomerDao  extends JpaRepository<Customer, Integer>{
	
	//客户管理相关内容
	//通过客户名称模糊查询
	@Query(value = "select * from customer c  where c.name like %?1% and manager_id=?2",nativeQuery = true)
	Page<Customer>  findByNameLike(String name,User manager,Pageable pageable);
	//通过地区查询
	Page<Customer>  findByRegionAndManager(String region,User manager,Pageable pageable);
	//通过客户等级查询
	Page<Customer>  findByLevelAndManager(String level,User manager,Pageable pageable);
	//通过客户等级和地区一起查询
	Page<Customer>  findByLevelAndRegion(String name,Pageable pageable);
	//通过经理查询
	Page<Customer>  findByManager(User manager,Pageable pageable);
	//通过客户经理查询
	List<Customer> findByManager(User manager);
	//通过名字模糊查询,不进行分页处理
	List<Customer> findByNameLike(String name);
	
	
	
	
	//数据分析相关内容
	List<Customer> findByRegion(String region);
	List<Customer> findByLevel(String region);
	
}
