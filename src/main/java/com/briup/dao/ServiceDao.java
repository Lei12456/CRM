package com.briup.dao;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.briup.bean.Customer;
import com.briup.bean.Service;
import com.briup.bean.User;

/** 
* @author 作者 angel: 
* @version 创建时间：2020年4月16日 下午10:13:35 
* 类说明 
*/
public interface ServiceDao extends JpaRepository<Service,Integer> {
	//通过客户名称模糊查询
	@Query(value = "select * from service s where s.customer_id=?1 and creator_id=?2",nativeQuery = true)
	Page<Service> findByCustomerLike(Integer cusId,User creator,Pageable pageable);
	//通过服务类型查询
	Page<Service> findByTypeAndCreator(String type,User creator,Pageable pageable);
	//通过客户名称和类型一起查询
	@Query(value = "select * from service s where s.customer_id =1 and s.creator_id=?2 and s.type=?3",nativeQuery = true)
	Page<Service> findByCustomerLikeAndType(Integer cusId,User creator,String type,Pageable pageable);
	//通过创建者查询
	Page<Service> findByCreator(User creator,Pageable pageable);
	
	
	//用于服务反馈模块
	//通过服务类型和状态一起查询,,一下都要进行分页处理
	Page<Service> findByTypeAndStatus(String type,String status,Pageable pageable);
	//通过类型查询
	Page<Service>  findByType(String type,Pageable pageable);
	//通过状态查询
	Page<Service>  findByStatus(String status,Pageable pageable);
 	
	
	//通过id更新服务 更新谁成已处理状态
	@Transactional
	@Modifying
	@Query(value = "update Service s set s.status='已处理'   where s.id=?1")
	void updateServiceByStatus(Integer id);
	
	//通过id更新服务,更新成已反馈状态
	@Transactional
	@Modifying
	@Query(value = "update Service s set s.status='已反馈' ,s.satisfy=?1  where s.id=?2")
	void updateServiceById(String satisfy , Integer id);

}
