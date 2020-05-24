package com.briup.dao;





import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.briup.bean.Chance;
import com.briup.bean.User;

/** 
* @author 作者 angel: 
* @version 创建时间：2020年4月1日 下午2:07:40 
* 类说明 
*/
public interface ChanceDao  extends JpaRepository<Chance, Integer>{

	//根据潜在客户名，查询对应的销售商机并分页
	@Query(value = "select * from chance c  where c.customer like %?1%",nativeQuery = true)
	Page<Chance> findByCustomerLike(String customer,Pageable pageable);
		
	//根据区域名，查询对应的销售商机并分页
	Page<Chance> findByAddress(String address,Pageable pageable);
	
	//用于商机开发计划模块
	//根据客户经理，查询对应的销售商机并分页
	Page<Chance> findByHandler(User handler,Pageable pageable);
		
	//根据客户名称和区域，一起查询对应的销售商机并分页
	@Query(value = "select * from chance c  where c.customer like %?1% and address=?2",nativeQuery = true)
	Page<Chance> findByCustomerAndAddress(String customer,String address,Pageable pageable);
	
	//用于商机开发计划模块
	//根据客户经理，查询符合地区的销售商机并进行分页处理
	Page<Chance> findByHandlerAndAddress(User handler,String address,Pageable pageable);
	//修改销售商机通过Id
	@Transactional
	@Modifying
	@Query(value = "update  Chance c set c.status = '处理中' where c.id=?1")
	void updateChanceById(Integer id);
	//修改销售商机通过Id
	@Transactional
	@Modifying
	@Query(value = "update  Chance c set c.status = '处理成功' where c.id=?1")
	void updateChanceById2(Integer id);
}
